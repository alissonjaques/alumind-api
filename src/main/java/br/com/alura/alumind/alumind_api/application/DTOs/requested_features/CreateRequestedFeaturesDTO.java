package br.com.alura.alumind.alumind_api.application.DTOs.requested_features;

import br.com.alura.alumind.alumind_api.domain.model.Feedback;

public record CreateRequestedFeaturesDTO(String code, String reason, Feedback feedback) { }
