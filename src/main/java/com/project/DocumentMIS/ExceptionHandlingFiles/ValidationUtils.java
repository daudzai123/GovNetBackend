package com.project.DocumentMIS.ExceptionHandlingFiles;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.HashMap;
import java.util.Map;

@Service
public class ValidationUtils {
    private final Validator validator;

    public ValidationUtils(Validator validator) {
        this.validator = validator;
    }

    public ResponseEntity<?> validateAndHandleErrors(Object target, String targetName) {
        Errors errors = new BeanPropertyBindingResult(target, targetName);
        validator.validate(target, errors);

        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(getValidationErrors(errors));
        }

        return null; // No validation errors
    }

    private Map<String, String> getValidationErrors(Errors errors) {
        Map<String, String> validationErrors = new HashMap<>();
        errors.getFieldErrors().forEach(error ->
                validationErrors.put(error.getField(), error.getDefaultMessage()));
        return validationErrors;
    }
}
