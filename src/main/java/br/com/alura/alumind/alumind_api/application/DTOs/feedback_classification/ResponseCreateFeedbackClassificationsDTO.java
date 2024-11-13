package br.com.alura.alumind.alumind_api.application.DTOs.feedback_classification;

import br.com.alura.alumind.alumind_api.application.DTOs.requested_features.RequestedFeaturesDTO;
import br.com.alura.alumind.alumind_api.domain.enums.Sentiment;

import java.util.List;

public record ResponseCreateFeedbackClassificationsDTO(Long id, Sentiment sentiment, List<RequestedFeaturesDTO> requestedFeatures, String customResponse) { }
