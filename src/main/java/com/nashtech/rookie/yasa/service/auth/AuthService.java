package com.nashtech.rookie.yasa.service.auth;

import com.nashtech.rookie.yasa.dto.request.LoginDto;
import com.nashtech.rookie.yasa.dto.request.RegisterDto;
import com.nashtech.rookie.yasa.dto.response.UserDto;

public interface AuthService {

    UserDto Register(RegisterDto dto);
    UserDto Login(LoginDto dto);
}
