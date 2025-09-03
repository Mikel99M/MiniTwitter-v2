package com.example.demo.facade;

import com.example.demo.dto.PostDto;
import com.example.demo.dto.UserPasswordChangeDto;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.mapper.PostMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;

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

    public List<PostDto> getPostsMadeByUser(Long id) {
        return postMapper.postListToPostDtoList(userService.getPostsMadeByUser(id));
    }

    public List<PostDto> getPostsLikedByUser(Long id) {
        return postMapper.postListToPostDtoList(userService.getPostsLikedByUser(id));
    }

    public void deleteUser(Long id) {
        userService.deleteUser(id);
    }
}
