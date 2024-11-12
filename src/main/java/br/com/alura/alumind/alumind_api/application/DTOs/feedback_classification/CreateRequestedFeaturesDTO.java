package br.com.alura.alumind.alumind_api.application.DTOs.feedback_classification;

import br.com.alura.alumind.alumind_api.domain.model.FeedbackClassifications;

public record CreateRequestedFeaturesDTO(String code, String reason, FeedbackClassifications feedbackClassifications) { }
