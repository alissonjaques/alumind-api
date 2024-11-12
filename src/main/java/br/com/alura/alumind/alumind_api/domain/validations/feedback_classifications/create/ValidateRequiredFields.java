package br.com.alura.alumind.alumind_api.domain.validations.feedback_classifications.create;

import br.com.alura.alumind.alumind_api.application.DTOs.feedback_classification.CreateFeedbackClassificationsDTO;
import br.com.alura.alumind.alumind_api.domain.exceptions.ValidationException;
import org.springframework.stereotype.Component;

@Component()
public class ValidateRequiredFields implements ICreateFeedbackClassficationsValidation {
    public void validate(CreateFeedbackClassificationsDTO createFeedbackClassificationsDTO){
        if(createFeedbackClassificationsDTO.feedback() == null || createFeedbackClassificationsDTO.feedback().isBlank()){
            throw new ValidationException("Não foi possível adicionar o feedback. Motivo: o campo feedback é obrigatório.",400);
        }
    }
}
