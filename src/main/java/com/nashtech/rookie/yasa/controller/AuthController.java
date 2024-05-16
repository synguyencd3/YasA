package com.nashtech.rookie.yasa.controller;

import com.nashtech.rookie.yasa.dto.request.RegisterDto;
import com.nashtech.rookie.yasa.dto.response.UserDto;
import com.nashtech.rookie.yasa.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> register(@RequestBody RegisterDto dto){
        return ResponseEntity.ok(authService.Register(dto));
    }
}
