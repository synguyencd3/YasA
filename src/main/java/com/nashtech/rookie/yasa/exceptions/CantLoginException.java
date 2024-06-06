package com.nashtech.rookie.yasa.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;

@Getter
@Setter
public class CantLoginException extends AuthenticationException {

    public CantLoginException(){
        super("Username or password incorrect");
    };

    public CantLoginException(String message) {
        super(message);
    }
}
