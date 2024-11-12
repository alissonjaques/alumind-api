package br.com.alura.alumind.alumind_api.application.DTOs.feedback_classification;

import br.com.alura.alumind.alumind_api.domain.enums.Sentiment;
import br.com.alura.alumind.alumind_api.domain.model.FeedbackClassifications;
import br.com.alura.alumind.alumind_api.domain.model.RequestedFeatures;

import java.util.List;

public record ResponseCreateFeedbackClassificationsDTO(Long id, Sentiment sentiment) {
    public ResponseCreateFeedbackClassificationsDTO(FeedbackClassifications feedbackClassifications){
        this(feedbackClassifications.getId(), feedbackClassifications.getSentiment());
    }
}
