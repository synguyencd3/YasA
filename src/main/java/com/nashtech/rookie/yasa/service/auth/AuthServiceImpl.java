package com.nashtech.rookie.yasa.service.auth;
import com.nashtech.rookie.yasa.dto.request.LoginDto;
import com.nashtech.rookie.yasa.dto.request.RegisterDto;
import com.nashtech.rookie.yasa.dto.response.UserDto;
import com.nashtech.rookie.yasa.entity.User;
import com.nashtech.rookie.yasa.exceptions.CantRegisterException;
import com.nashtech.rookie.yasa.mapper.UserMapper;
import com.nashtech.rookie.yasa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

@Service
public class AuthServiceImpl implements AuthService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDto Register(RegisterDto dto) {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new CantRegisterException();
        }
        md.update(salt);
        byte[] hashedPassword = md.digest(dto.getPassword().getBytes(StandardCharsets.UTF_8));
        User user = new User();
        System.out.println(dto.getPassword());
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setRole("user");
        user.setSalt(new String(salt, StandardCharsets.UTF_8));
        user.setSecret(new String(hashedPassword, StandardCharsets.UTF_8));
        user =userRepository.save(user);
        return UserMapper.INSTANCE.toDto(user);
    }

    @Override
    public UserDto Login(LoginDto dto) {
        return null;
    }
}
