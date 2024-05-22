package com.nashtech.rookie.yasa.dto.request;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class UpdateUserDto{
    @NotBlank(message = "name is required")
    private String name;
    @NotBlank(message = "username is required")
    private String username;
    @NotBlank(message = "password is required")
    private String password;
}
