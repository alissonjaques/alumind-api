package br.com.alura.alumind.alumind_api.domain.validations.requested_features.create;

import br.com.alura.alumind.alumind_api.application.DTOs.feedback_classification.CreateRequestedFeaturesDTO;

public interface IRequestFeaturesValidation {
    void validate(CreateRequestedFeaturesDTO createRequestedFeaturesDTO);
}
