package br.com.alura.alumind.alumind_api.infra.exception;

import br.com.alura.alumind.alumind_api.application.DTOs.exceptions.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ValidationErrorData::new).toList());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGeneralError(Exception ex) {
        System.out.println("Erro: " + ex.getMessage());
        ex.printStackTrace();
        return ResponseEntity.internalServerError().body(new ExceptionDTO("Não foi possível realizar a operação. Motivo: erro interno no servidor.",500));
    }

    private record ValidationErrorData(String campo, String mensagem) {
        public ValidationErrorData(FieldError erro){
            this(erro.getField(),erro.getDefaultMessage());
        }
    }
}
