package com.nashtech.rookie.yasa.dto.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
@Value
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ErrorResponse {

    private Integer code;
    private String message;
    private Object errors;


}