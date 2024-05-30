package com.nashtech.rookie.yasa.service.user;

import com.nashtech.rookie.yasa.dto.request.LoginDto;
import com.nashtech.rookie.yasa.dto.request.RegisterDto;
import com.nashtech.rookie.yasa.dto.response.UserDto;

public interface UserService {

    UserDto register(RegisterDto dto);
    UserDto login(LoginDto dto);
    UserDto adminLogin(LoginDto dto);
}
