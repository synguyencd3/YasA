package com.nashtech.rookie.yasa.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CantLoginException extends RuntimeException{
    private String message = "Username or password incorrect";

    public CantLoginException(){};

    public CantLoginException(String message) {
        this.message = message;
    }
}
