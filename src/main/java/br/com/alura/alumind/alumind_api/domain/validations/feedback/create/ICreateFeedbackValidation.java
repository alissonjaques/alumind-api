package br.com.alura.alumind.alumind_api.domain.validations.feedback.create;

import br.com.alura.alumind.alumind_api.application.DTOs.feedback.CreateFeedbackDTO;

public interface ICreateFeedbackValidation {
    void validate(CreateFeedbackDTO createFeedbackDTO);
}
