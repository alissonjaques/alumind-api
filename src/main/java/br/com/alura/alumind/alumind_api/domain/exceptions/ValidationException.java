package br.com.alura.alumind.alumind_api.domain.exceptions;

public class ValidationException extends RuntimeException {
    private Integer status;
    public ValidationException(String message, Integer status) {
        super(message);
        this.status = status;
    }

    public Integer getStatus(){
        return this.status;
    }
}
