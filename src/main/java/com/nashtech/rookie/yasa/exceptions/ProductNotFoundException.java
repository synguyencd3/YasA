package com.nashtech.rookie.yasa.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFoundException extends RuntimeException{
    private String message = "Product not found";
}
