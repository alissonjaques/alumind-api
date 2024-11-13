package br.com.alura.alumind.alumind_api.application.DTOs.feedback;

import br.com.alura.alumind.alumind_api.domain.enums.Sentiment;
import br.com.alura.alumind.alumind_api.domain.model.RequestedFeatures;

import java.util.List;

public record FeedbackDTO(Sentiment sentiment, String customResponse, List<RequestedFeatures> requestedFeatures) { }
