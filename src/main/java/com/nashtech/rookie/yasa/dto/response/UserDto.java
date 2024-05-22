package com.nashtech.rookie.yasa.dto.response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private int id;
    private String name;
    private String username;
    private String role;
    private CartDto cart;
}
