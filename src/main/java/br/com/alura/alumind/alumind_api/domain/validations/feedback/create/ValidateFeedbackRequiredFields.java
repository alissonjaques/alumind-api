package br.com.alura.alumind.alumind_api.domain.validations.feedback.create;

import br.com.alura.alumind.alumind_api.application.DTOs.feedback.CreateFeedbackDTO;
import br.com.alura.alumind.alumind_api.domain.exceptions.ValidationException;
import org.springframework.stereotype.Component;

@Component()
public class ValidateFeedbackRequiredFields implements ICreateFeedbackValidation {
    public void validate(CreateFeedbackDTO createFeedbackDTO){
        if(createFeedbackDTO.feedback() == null || createFeedbackDTO.feedback().isBlank()){
            throw new ValidationException("Não foi possível adicionar o feedback. Motivo: o campo feedback é obrigatório.",400);
        }
    }
}
