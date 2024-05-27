package com.nashtech.rookie.yasa.controller;

import com.nashtech.rookie.yasa.dto.request.LoginDto;
import com.nashtech.rookie.yasa.dto.request.RegisterDto;
import com.nashtech.rookie.yasa.dto.response.UserDto;
import com.nashtech.rookie.yasa.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public ResponseEntity<UserDto> register(@RequestBody @Valid RegisterDto dto){
        return ResponseEntity.ok(userService.register(dto));
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @CrossOrigin
    public ResponseEntity<UserDto> login(@RequestBody @Valid LoginDto dto)
    {
        return ResponseEntity.ok(userService.login(dto));
    }
}
