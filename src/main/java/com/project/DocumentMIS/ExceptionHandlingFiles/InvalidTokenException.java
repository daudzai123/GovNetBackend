package com.project.DocumentMIS.ExceptionHandlingFiles;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message) {
        super(message);
    }
}
