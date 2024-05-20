package com.nashtech.rookie.yasa.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingNotFoundException extends RuntimeException{
    private String message = "Rating not found";
}
