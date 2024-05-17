package com.nashtech.rookie.yasa.service.user;
import com.nashtech.rookie.yasa.dto.request.LoginDto;
import com.nashtech.rookie.yasa.dto.request.RegisterDto;
import com.nashtech.rookie.yasa.dto.response.UserDto;
import com.nashtech.rookie.yasa.entity.Cart;
import com.nashtech.rookie.yasa.entity.User;
import com.nashtech.rookie.yasa.exceptions.CantLoginException;
import com.nashtech.rookie.yasa.mapper.UserMapper;
import com.nashtech.rookie.yasa.repository.UserRepository;
import com.nashtech.rookie.yasa.service.cart.CartService;
import com.nashtech.rookie.yasa.util.SHA521Hasher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartService cartService;

    @Override
    public UserDto register(RegisterDto dto) {
        //Generate salt
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        String hashedPassword = SHA521Hasher.hash(dto.getPassword(), salt);

        // Map dto to User and set additional detail
        User user = UserMapper.INSTANCE.toEntity(dto);//new User();
        user.setRole("user");
        user.setSalt(Base64.getEncoder().encodeToString(salt));
        user.setSecret(hashedPassword);
        user.setCart(cartService.createCart(user));

        user =userRepository.save(user);

        return UserMapper.INSTANCE.toDto(user);
    }

    @Override
    public UserDto login(LoginDto dto) {
        User user = userRepository.findByUsername(dto.getUsername());
        byte[] salt = Base64.getDecoder().decode(user.getSalt());
        if (SHA521Hasher.checkPassword(user.getSecret(),dto.getPassword(), salt))
            return UserMapper.INSTANCE.toDto(user);
        else
            throw  new CantLoginException();
    }
}
