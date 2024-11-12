package br.com.alura.alumind.alumind_api.domain.validations.feedback_classifications.create;

import br.com.alura.alumind.alumind_api.application.DTOs.feedback_classification.CreateFeedbackClassificationsDTO;
import org.springframework.stereotype.Component;

public interface ICreateFeedbackClassficationsValidation {
    void validate(CreateFeedbackClassificationsDTO createFeedbackClassificationsDTO);
}
