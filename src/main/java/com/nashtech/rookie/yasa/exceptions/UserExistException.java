package com.nashtech.rookie.yasa.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserExistException extends RuntimeException {
    private String message = "username already exist";
}
