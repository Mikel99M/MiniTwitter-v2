package com.example.demo.facade;

import com.example.demo.dto.*;
import com.example.demo.mapper.PostMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final UserMapper userMapper;
    private final PostMapper postMapper;

    public UserResponseDto getUserById(Long id) {
        return userMapper.userToUserResponseDto(userService.getUserById(id));
    }

    public UserResponseDto createNewUser(UserRegistrationDto dto) {
        return userMapper.userToUserResponseDto(userService.registerNewUser(dto));
    }


    public void updateUserPassword(Long id, UserPasswordChangeDto dto) {
        userService.updateUserPassword(id, dto);
    }

    public List<PostResponseDto> getPostsMadeByUser(Long id) {
        return postMapper.postListToPostResponseDtoList(userService.getPostsMadeByUser(id));
    }

    public List<PostResponseDto> getPostsLikedByUser(Long id) {
        return postMapper.postListToPostResponseDtoList(userService.getPostsLikedByUser(id));
    }

    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }

    public List<UserResponseDto> getAllUsers() {
        return userMapper.userListToUserResponseDtoList(userService.getAllUser());
    }
}
