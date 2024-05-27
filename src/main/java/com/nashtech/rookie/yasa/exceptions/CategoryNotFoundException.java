package com.nashtech.rookie.yasa.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such category")
public class CategoryNotFoundException extends RuntimeException{
    private String message = "Category not found";
}
