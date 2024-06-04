package com.nashtech.rookie.yasa.service.user;

import com.nashtech.rookie.yasa.dto.request.LoginDto;
import com.nashtech.rookie.yasa.dto.request.RegisterDto;
import com.nashtech.rookie.yasa.dto.response.UserDto;
import org.springframework.data.domain.Page;

public interface UserService {

    UserDto register(RegisterDto dto, String role);
    UserDto login(LoginDto dto);
    UserDto adminLogin(LoginDto dto);
    UserDto adminRegister(RegisterDto dto);

    Page<UserDto> getAllUser(int page, int size, String sort, String sortBy);

    void banUser(String username);

    void unbanUser(String username);
}
