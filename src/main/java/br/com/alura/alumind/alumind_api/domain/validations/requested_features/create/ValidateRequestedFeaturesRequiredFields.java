package br.com.alura.alumind.alumind_api.domain.validations.requested_features.create;

import br.com.alura.alumind.alumind_api.application.DTOs.feedback_classification.CreateRequestedFeaturesDTO;
import br.com.alura.alumind.alumind_api.domain.exceptions.ValidationException;
import org.springframework.stereotype.Component;

@Component()
public class ValidateRequestedFeaturesRequiredFields implements IRequestFeaturesValidation {
    public void validate(CreateRequestedFeaturesDTO createRequestedFeaturesDTO){
        if(createRequestedFeaturesDTO.code() == null || createRequestedFeaturesDTO.code().isBlank()){
            throw new ValidationException("Não foi possível adicionar a melhoria. Motivo: o campo code é obrigatório.",400);
        }
        if(createRequestedFeaturesDTO.reason() == null || createRequestedFeaturesDTO.reason().isBlank()){
            throw new ValidationException("Não foi possível adicionar a melhoria. Motivo: o campo reason é obrigatório.",400);
        }
        if(createRequestedFeaturesDTO.reason() == null || createRequestedFeaturesDTO.reason().isBlank()){
            throw new ValidationException("Não foi possível adicionar a melhoria. Motivo: o campo reason é obrigatório.",400);
        }
        if(createRequestedFeaturesDTO.feedbackClassifications().getId() == null){
            throw new ValidationException("Não foi possível adicionar a melhoria. Motivo: o campo feedbackId é obrigatório.",400);
        }
    }
}
