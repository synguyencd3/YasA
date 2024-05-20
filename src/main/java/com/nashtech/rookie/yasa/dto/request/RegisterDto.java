package com.nashtech.rookie.yasa.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterDto {
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "name is required")
    private String username;
    @NotBlank(message = "password is required")
    private String password;
}
