package com.example.demo.facade;

import com.example.demo.dto.PostDto;
import com.example.demo.mapper.PostMapper;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostFacade {

    private final PostService postService;
    private final PostMapper postMapper;

    public PostDto getPostById(Long id) {
        return postMapper.postPostToPostDto(postService.getPostById(id));
    }
}
