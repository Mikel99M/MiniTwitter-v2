package com.example.demo.facade;

import com.example.demo.dto.LikeDto;
import com.example.demo.dto.PostResponseDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.mapper.LikeMapper;
import com.example.demo.mapper.PostMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeFacade {

    private final LikeService likeService;
    private final LikeMapper likeMapper;
    private final PostMapper postMapper;
    private final UserMapper userMapper;

    public void toggleLike(Long userId, Long postId) {
        likeService.toggleLike(userId, postId);
    }

    public List<LikeDto> getAllLikes() {
        return likeMapper.likeListToLikeDtoList(likeService.getAllLikes());
    }

    public LikeDto getLikeById(Long id) {
        return likeMapper.likeToLikeDto(likeService.getLikeById(id));
    }

    public List<PostResponseDto> getAllPostsLikedByUser(Long id) {
        return postMapper.postListToPostResponseDtoList(likeService.getAllPostsLikedByUser(id));
    }

    public List<UserResponseDto> getAllUserWhoLikedPost(Long id) {
        return userMapper.userListToUserResponseDtoList(likeService.getAllUserWhoLikedPost(id));
    }
}
