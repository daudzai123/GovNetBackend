package com.project.GovNetMISApplication.ExceptionHandlingFiles;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE)
public class UserStatusExcetion extends AuthenticationException{
    public UserStatusExcetion(String message){
        super(message);
    }
}