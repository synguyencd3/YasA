package com.nashtech.rookie.yasa.service.auth;

import com.nashtech.rookie.yasa.dto.request.LoginDto;
import com.nashtech.rookie.yasa.entity.User;
import com.nashtech.rookie.yasa.repository.UserRepository;
import com.nashtech.rookie.yasa.util.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthServiceImpl {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    public String login(User user) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getSecret());
        authenticationManager.authenticate(token);
        return JWTService.createJWT(user);
    }
}
