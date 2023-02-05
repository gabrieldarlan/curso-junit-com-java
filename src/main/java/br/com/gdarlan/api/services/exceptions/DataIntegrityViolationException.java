package br.com.gdarlan.api.services.exceptions;

public class DataIntegrityViolationException extends  RuntimeException{
    public DataIntegrityViolationException(String message) {
        super(message);
    }
}
