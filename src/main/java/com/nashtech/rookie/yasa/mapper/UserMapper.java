package com.nashtech.rookie.yasa.mapper;
import com.nashtech.rookie.yasa.dto.request.RegisterDto;
import com.nashtech.rookie.yasa.dto.request.UpdateUserDto;
import com.nashtech.rookie.yasa.dto.response.CartDto;
import com.nashtech.rookie.yasa.dto.response.UserDto;
import com.nashtech.rookie.yasa.entity.Cart;
import com.nashtech.rookie.yasa.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    @Mapping(source = "cart", target = "cart", qualifiedByName = "cartToDto")
    UserDto toDto(User entity);
    User toEntity(RegisterDto dto);
    User updateEntity(@MappingTarget User entity, UpdateUserDto dto);

    @Named("cartToDto")
    public static CartDto cartToDto(Cart cart) {
        return CartMapper.INSTANCE.toDto(cart);
    }

}
