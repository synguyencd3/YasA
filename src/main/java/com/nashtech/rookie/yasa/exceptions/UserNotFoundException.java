package com.nashtech.rookie.yasa.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserNotFoundException extends RuntimeException{
    private String message = "User not found";
}
