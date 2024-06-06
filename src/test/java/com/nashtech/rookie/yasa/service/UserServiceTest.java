package com.nashtech.rookie.yasa.service;

import com.nashtech.rookie.yasa.dto.request.LoginDto;
import com.nashtech.rookie.yasa.dto.request.RegisterDto;
import com.nashtech.rookie.yasa.dto.response.UserDto;
import com.nashtech.rookie.yasa.entity.Cart;
import com.nashtech.rookie.yasa.entity.User;
import com.nashtech.rookie.yasa.exceptions.CantLoginException;
import com.nashtech.rookie.yasa.mapper.UserMapper;
import com.nashtech.rookie.yasa.repository.UserRepository;
import com.nashtech.rookie.yasa.service.cart.CartServiceImpl;
import com.nashtech.rookie.yasa.service.user.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;

import static org.assertj.core.api.Assertions.assertThat;
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

    public User getSampleUser() {
        User sample = new User();
        sample.setUsername("test");
        sample.setId(1);
        sample.setSalt("/VHCCusTTo4uIHm7nVmXKw==");
        sample.setSecret("JxJauxsatPb6w2kHlutNs5ViVX/MOmNfZuh440hYIuGbknLF4JfbFk/dxVCzqtBRtqzFpkvdSgBiR5lF6d/39A==");
        sample.setCart(getSampleCart());
        return sample;
    }

    public Cart getSampleCart() {
        Cart sample = new Cart();
        sample.setId(1);
        return sample;
    }

    @Test
    public void RegsiterTest() {
        RegisterDto dto = new RegisterDto();
        dto.setUsername("test");
        dto.setPassword("1234");

        var user = getSampleUser();
        var cart = getSampleCart();
        when(cartService.createCart(user)).thenReturn(cart);
        when(userRepository.save(any(User.class))).thenReturn(user);
        var userDto = userService.register(dto, "user");

        assertNotNull(userDto);
        assertThat(userDto.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void LoginTest() {
        LoginDto dto = new LoginDto();
        dto.setUsername("test");
        dto.setPassword("123456");
        var sample= getSampleUser();
        when(userRepository.findByUsername(dto.getUsername())).thenReturn(sample);

        var actual = userService.login(dto);

        assertNotNull(actual);
        assertThat(actual.getUsername()).isEqualTo(dto.getUsername());

    }

    @Test(expected = CantLoginException.class)
    public void LoginTest_ShouldThrow_Exception() {
        LoginDto dto = new LoginDto();
        dto.setUsername("test");
        dto.setPassword("wrongPassword");
        var sample= getSampleUser();
        when(userRepository.findByUsername(dto.getUsername())).thenReturn(sample);

        var actual = userService.login(dto);

        assertNotNull(actual);
        assertThat(actual.getUsername()).isEqualTo(dto.getUsername());
    }

}
