package br.com.alura.alumind.alumind_api.domain.service;

import br.com.alura.alumind.alumind_api.application.DTOs.feedback.*;
import br.com.alura.alumind.alumind_api.application.DTOs.requested_features.CreateRequestedFeaturesDTO;
import br.com.alura.alumind.alumind_api.application.DTOs.requested_features.RequestedFeaturesDTO;
import br.com.alura.alumind.alumind_api.domain.interfaces.FeedbackRepository;
import br.com.alura.alumind.alumind_api.domain.interfaces.RequestedFeaturesRepository;
import br.com.alura.alumind.alumind_api.domain.model.Feedback;
import br.com.alura.alumind.alumind_api.domain.model.RequestedFeatures;
import br.com.alura.alumind.alumind_api.domain.utils.MethodLibrary;
import br.com.alura.alumind.alumind_api.domain.utils.SystemPrompts;
import br.com.alura.alumind.alumind_api.domain.validations.feedback.create.ICreateFeedbackValidation;
import br.com.alura.alumind.alumind_api.infra.openai.OpenAIClient;
import br.com.alura.alumind.alumind_api.infra.openai.RateFeedbackDataChatCompletion;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private RequestedFeaturesRepository requestedFeaturesRepository;
    @Autowired
    List<ICreateFeedbackValidation> createFeedbackValidation;

    private OpenAIClient client;

    public FeedbackService(OpenAIClient client) {
        this.client = client;
    }
    public ResponseCreateFeedbackDTO create(CreateFeedbackDTO createFeedbackDTO) {
        try{
            createFeedbackValidation.forEach(v -> v.validate(createFeedbackDTO));
            var data = new RateFeedbackDataChatCompletion(SystemPrompts.FEEDBACK_PROMPT, createFeedbackDTO.feedback());
            var response = client.rateFeedbackChatCompletion(data);
            if (response == null || response.trim().isEmpty() || response.equalsIgnoreCase("null")) {
                return null;
            }
            response = MethodLibrary.formatJsonOpenAI(response);
            ObjectMapper objectMapper = new ObjectMapper();
            FeedbackDTO feedback = objectMapper.readValue(response, FeedbackDTO.class);
            var newFeedback = new Feedback(createFeedbackDTO.feedback(),feedback);
            feedbackRepository.save(newFeedback);

            List<RequestedFeaturesDTO> newListRequestFeatures = new ArrayList<RequestedFeaturesDTO>();
            for (RequestedFeatures feature : feedback.requestedFeatures()) {
                CreateRequestedFeaturesDTO requestedFeaturesDTO = new CreateRequestedFeaturesDTO(feature.getCode(), feature.getReason(), newFeedback);
                var newRequestedFeatures = new RequestedFeatures(requestedFeaturesDTO);
                requestedFeaturesRepository.save(newRequestedFeatures);
                newListRequestFeatures.add(new RequestedFeaturesDTO(newRequestedFeatures.getCode(),newRequestedFeatures.getReason()));
            }

            var responseCreateFeedbackDTO = new ResponseCreateFeedbackDTO(newFeedback.getId(), newFeedback.getSentiment(), newListRequestFeatures, newFeedback.getCustomResponse());
            return responseCreateFeedbackDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
