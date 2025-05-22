package com.project.barbeshop.dto;

import java.time.LocalDateTime;
import java.util.Map;

// Nueva clase para manejar errores de validacion con detalles de campo
public class ValidationErrorResponse extends ErrorResponse {


    private Map<String, String> fieldErrors;

    public ValidationErrorResponse(String message, String code,LocalDateTime timestamp, Map<String, String> fieldErrors) {
        super(message, code, timestamp);
        this.fieldErrors = fieldErrors;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}

