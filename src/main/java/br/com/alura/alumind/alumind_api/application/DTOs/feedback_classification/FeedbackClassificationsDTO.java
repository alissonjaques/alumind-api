package br.com.alura.alumind.alumind_api.application.DTOs.feedback_classification;

import br.com.alura.alumind.alumind_api.domain.enums.Sentiment;
import br.com.alura.alumind.alumind_api.domain.model.FeedbackClassifications;
import br.com.alura.alumind.alumind_api.domain.model.RequestedFeatures;

import java.util.List;

public record FeedbackClassificationsDTO(Sentiment sentiment, String response, List<RequestedFeatures> requestedFeatures) { }
