package br.com.alura.alumind.alumind_api.domain.service;

import br.com.alura.alumind.alumind_api.application.DTOs.feedback_classification.CreateFeedbackClassificationsDTO;
import br.com.alura.alumind.alumind_api.application.DTOs.feedback_classification.CreateRequestedFeaturesDTO;
import br.com.alura.alumind.alumind_api.application.DTOs.feedback_classification.FeedbackClassificationsDTO;
import br.com.alura.alumind.alumind_api.domain.enums.Sentiment;
import br.com.alura.alumind.alumind_api.domain.interfaces.FeedbackClassificationsRepository;
import br.com.alura.alumind.alumind_api.domain.interfaces.RequestedFeaturesRepository;
import br.com.alura.alumind.alumind_api.domain.model.FeedbackClassifications;
import br.com.alura.alumind.alumind_api.domain.model.RequestedFeatures;
import br.com.alura.alumind.alumind_api.domain.utils.SystemPrompts;
import br.com.alura.alumind.alumind_api.domain.validations.feedback_classifications.create.ICreateFeedbackClassficationsValidation;
import br.com.alura.alumind.alumind_api.infra.openai.OpenAIClient;
import br.com.alura.alumind.alumind_api.infra.openai.RateFeedbackDataChatCompletion;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackClassificationsService {
    @Autowired
    private FeedbackClassificationsRepository feedbackClassificationsRepository;

    @Autowired
    private RequestedFeaturesRepository requestedFeaturesRepository;
    @Autowired
    List<ICreateFeedbackClassficationsValidation> createFeedbackClassificationsValidation;

    private OpenAIClient client;

    public FeedbackClassificationsService(OpenAIClient client) {
        this.client = client;
    }
    public String create(CreateFeedbackClassificationsDTO createFeedbackClassificationsDTO) {
        createFeedbackClassificationsValidation.forEach(v -> v.validate(createFeedbackClassificationsDTO));
        var data = new RateFeedbackDataChatCompletion(SystemPrompts.FEEDBACK_CLASSIFICATION_PROMPT, createFeedbackClassificationsDTO.feedback());
        var response = client.rateFeedbackChatCompletion(data);
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            FeedbackClassificationsDTO feedback = objectMapper.readValue(response, FeedbackClassificationsDTO.class);
            var newFeedbackClassifications = new FeedbackClassifications(createFeedbackClassificationsDTO.feedback(),feedback);
            feedbackClassificationsRepository.save(newFeedbackClassifications);

            for (RequestedFeatures feature : feedback.requestedFeatures()) {
                CreateRequestedFeaturesDTO requestedFeaturesDTO = new CreateRequestedFeaturesDTO(feature.getCode(), feature.getReason(), newFeedbackClassifications);
                var newRequestedFeatures = new RequestedFeatures(requestedFeaturesDTO);
                requestedFeaturesRepository.save(newRequestedFeatures);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}