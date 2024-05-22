package com.nashtech.rookie.yasa.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderNotFoundException extends RuntimeException{
    private String message = "order not found";
}
