package com.nashtech.rookie.yasa.service;

import com.nashtech.rookie.yasa.dto.request.LoginDto;
import com.nashtech.rookie.yasa.dto.request.RegisterDto;
import com.nashtech.rookie.yasa.dto.response.UserDto;
import com.nashtech.rookie.yasa.entity.Cart;
import com.nashtech.rookie.yasa.entity.User;
import com.nashtech.rookie.yasa.repository.CartRepository;
import com.nashtech.rookie.yasa.repository.UserRepository;
import com.nashtech.rookie.yasa.service.cart.CartServiceImpl;
import com.nashtech.rookie.yasa.service.user.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private CartServiceImpl cartService;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    public void reset_mock() {
        userService = mock(UserServiceImpl.class);
    }

    @Test
    public void RegsiterTest() {
        RegisterDto dto = new RegisterDto();
        dto.setUsername("test");
        dto.setPassword("1234");
        when(cartService.createCart(any(User.class))).thenReturn(new Cart());
        when(userRepository.save(any(User.class))).thenReturn(new User());
        UserDto user = userService.register(dto);

        assertNotNull(user);
    }

}
