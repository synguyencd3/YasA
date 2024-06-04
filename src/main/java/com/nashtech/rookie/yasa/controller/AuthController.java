package com.nashtech.rookie.yasa.controller;

import com.nashtech.rookie.yasa.dto.request.LoginDto;
import com.nashtech.rookie.yasa.dto.request.RegisterDto;
import com.nashtech.rookie.yasa.dto.response.UserDto;
import com.nashtech.rookie.yasa.service.user.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> register(@RequestBody @Valid RegisterDto dto){
        return ResponseEntity.ok(userService.register(dto,"user"));
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<UserDto> login(@RequestBody @Valid LoginDto dto)
    {
        return ResponseEntity.ok(userService.login(dto));
    }

    @PostMapping("admin/login")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<UserDto> adminLogin(@RequestBody @Valid LoginDto dto)
    {
        return ResponseEntity.ok(userService.adminLogin(dto));
    }

    @PostMapping("admin/register")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<UserDto> adminRegister(@RequestBody @Valid RegisterDto dto)
    {
        return ResponseEntity.ok(userService.adminRegister(dto));
    }

    @GetMapping("admin")
    public ResponseEntity<Page<UserDto>> getAllUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "asc") String sort,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return ResponseEntity.ok(userService.getAllUser(page, size, sort, sortBy));
    }

    @PostMapping("admin")
    public ResponseEntity<String> banUser(@RequestParam() String username) {
        userService.banUser(username);
        return ResponseEntity.noContent().build();
    }

}
