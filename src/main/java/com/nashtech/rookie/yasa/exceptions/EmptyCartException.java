package com.nashtech.rookie.yasa.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmptyCartException extends RuntimeException{
    private String message = "Cart is empty";
}
