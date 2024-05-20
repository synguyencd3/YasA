package com.nashtech.rookie.yasa.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartNotFoundException extends RuntimeException{
    private String message = "Cart not found";
}
