package com.project.GovNetMISApplication.ExceptionHandlingFiles;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message) {
        super(message);
    }
}
