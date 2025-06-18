package com.project.DocumentMIS.ExceptionHandlingFiles;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;

import java.util.Map;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@ComponentScan(basePackages = "com.project.DocumentMIS.ExceptionHandlingFiles")
public class CustomizedResponseEntityExceptionHandller{

// extends ResponseEntityExceptionHandler
     // Handling validation errors
     @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<String> handleInvalidFormatException(InvalidFormatException ex) {
        String fieldName = ex.getPath().get(0).getFieldName();
        String message =   fieldName +"  not Selected ";

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
    //formatting Exception according to our ErrorDetails Class properties
    //NOt Found
    // Handling custom exception MyNotFoundException
    @ExceptionHandler(MyNotFoundException.class)
    public final ResponseEntity<ErrorRespond> handleNotFoundException(MyNotFoundException ex, HttpServletRequest request) {

        ErrorRespond errorResponse = new ErrorRespond(
                new Date(),
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(UserStatusExcetion.class)
    public final ResponseEntity<ErrorRespond> handleUserStatusException(UserStatusExcetion ex, HttpServletRequest request) {

        ErrorRespond errorResponse = new ErrorRespond(
                new Date(),
                HttpStatus.NOT_ACCEPTABLE.value(),
                HttpStatus.NOT_ACCEPTABLE.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);

    }

    @ExceptionHandler(InvalidTokenException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> handlException(InvalidTokenException ex) {
        System.out.println("in handlind not valid token!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        return new ResponseEntity<>("Invalid token: khan what you do!", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({UsernameNotFoundException.class , BadCredentialsException.class, InternalAuthenticationServiceException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public final ResponseEntity<ErrorRespond> handleAuthentecationException(Exception ex, HttpServletRequest request) {
        ErrorRespond errorResponse = new ErrorRespond(
                new Date(),
                HttpStatus.UNAUTHORIZED.value(),
                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
                "Username or password is false!",
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccountStatusException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public final ResponseEntity<String> handleAccountStatusException(AccountStatusException ex) {
        return new ResponseEntity<>("User Account is Abnormal!", HttpStatus.UNAUTHORIZED);
    }
    
    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ErrorRespond> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request) {
        ErrorRespond errorResponse = new ErrorRespond(
                new Date(),
                HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.getReasonPhrase(),
                "You dont have permission to access this endpoint!",
                request.getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(InsufficientAuthenticationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public final ResponseEntity<String> handleAuthRequiredException(InsufficientAuthenticationException ex) {
        return new ResponseEntity<>("Full authentication is required to access this resource!", HttpStatus.FORBIDDEN);
    }

    // @ExceptionHandler(Exception.class)
    // @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    // public final ResponseEntity<ErrorRespond> handleOtherException(Exception ex, HttpServletRequest request) {
    //     ErrorRespond errorResponse = new ErrorRespond(
    //             new Date(),
    //             HttpStatus.INTERNAL_SERVER_ERROR.value(),
    //             HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
    //             ex.getMessage(),
    //             request.getRequestURI()
    //     );

    //     return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    // }

    

    // @ExceptionHandler(JwtException.class)
    // @ResponseStatus(HttpStatus.UNAUTHORIZED)
    // public ResponseEntity<String> handleTokenException(JwtException ex) {
    //     return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    // }
    
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ResponseEntity<Object> handleOtherException(DataIntegrityViolationException ex, HttpServletRequest request) {
         Map<String, String> errors = new HashMap<>();
        
            errors.put("Email", "Email already exsit in the database!");
        
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);

    }

    
    
}
