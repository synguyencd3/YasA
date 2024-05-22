package com.nashtech.rookie.yasa.controller;

import com.nashtech.rookie.yasa.dto.request.LoginDto;
import com.nashtech.rookie.yasa.dto.request.RegisterDto;
import com.nashtech.rookie.yasa.dto.response.UserDto;
import com.nashtech.rookie.yasa.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> register(@RequestBody @Valid RegisterDto dto){
        return ResponseEntity.ok(userService.register(dto));
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<UserDto> login(@RequestBody @Valid LoginDto dto)
    {
        return ResponseEntity.ok(userService.login(dto));
    }
}
