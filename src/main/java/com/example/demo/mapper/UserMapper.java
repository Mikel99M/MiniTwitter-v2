package com.example.demo.mapper;

import com.example.demo.domain.User;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.dto.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "posts", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "lastLoggedInAt", ignore = true)
    @Mapping(target = "likesGiven", ignore = true)
    @Mapping(target = "role", constant = "USER")
    User userRegistrationDtoToUser(UserRegistrationDto userRegistrationDto);

    UserResponseDto userToUserResponseDto(User user);

    List<UserResponseDto> userListToUserResponseDtoList(List<User> list);

}
