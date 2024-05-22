package com.nashtech.rookie.yasa.dto.response;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
public class UserDto {
    private int id;
    private String name;
    private String username;
    private String role;
    private CartDto cart;
    private String accessKey;
}
