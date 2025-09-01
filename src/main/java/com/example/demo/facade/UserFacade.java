package com.example.demo.facade;

import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.mapper.UserMapper;
import com.example.demo.user.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserResponseDto getUserById(Long id) {
        return userMapper.userToUserResponseDto(userService.getUserById(id));
    }

    public UserResponseDto createNewUser(UserRegistrationDto dto) {
        return userMapper.userToUserResponseDto(userService.registerNewUser(dto));
    }



}
