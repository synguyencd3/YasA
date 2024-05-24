package com.nashtech.rookie.yasa.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotAuthenticatedException extends RuntimeException{
    private String message = "your are not authenticated";
}
