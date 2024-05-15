package com.nashtech.rookie.yasa.mapper;
import com.nashtech.rookie.yasa.dto.request.CreateUserDto;
import com.nashtech.rookie.yasa.dto.request.UpdateUserDto;
import com.nashtech.rookie.yasa.dto.response.UserDto;
import org.apache.catalina.User;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User entity);
    User toEntity(CreateUserDto dto);
    User updateEntity(@MappingTarget User entity, UpdateUserDto dto);
}
