package com.example.demo.facade;

import com.example.demo.dto.LikeDto;
import com.example.demo.mapper.LikeMapper;
import com.example.demo.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeFacade {

    private final LikeService likeService;
    private final LikeMapper likeMapper;

    public void toggleLike(Long userId, Long postId) {
        likeService.toggleLike(userId, postId);
    }

    public List<LikeDto> getAllLikes() {
        return likeMapper.likeListToLikeDtoList(likeService.getAllLikes());
    }

    public LikeDto getLikeById(Long id) {
        return likeMapper.likeToLikeDto(likeService.getLikeById(id));
    }
}
