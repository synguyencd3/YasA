package com.nashtech.rookie.yasa.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;

@Getter
@Setter
public class UserNotAuthenticatedException extends AuthenticationException {

    public UserNotAuthenticatedException() {
        super("your are not authenticated");
    }

    public UserNotAuthenticatedException(String msg) {
        super(msg);
    }
}
