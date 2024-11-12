package br.com.alura.alumind.alumind_api.domain.service;

import br.com.alura.alumind.alumind_api.application.DTOs.feedback_classification.CreateFeedbackClassificationsDTO;
import br.com.alura.alumind.alumind_api.application.DTOs.feedback_classification.FeedbackClassificationsDTO;
import br.com.alura.alumind.alumind_api.domain.enums.Sentiment;
import br.com.alura.alumind.alumind_api.domain.interfaces.FeedbackClassificationsRepository;
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
            System.out.println("Sentiment: " + feedback.sentiment());
            System.out.println("Response: " + feedback.response());

            for (RequestedFeatures feature : feedback.requestedFeatures()) {
                System.out.println("Feature Code: " + feature.getCode());
                System.out.println("Feature Reason: " + feature.getReason());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
