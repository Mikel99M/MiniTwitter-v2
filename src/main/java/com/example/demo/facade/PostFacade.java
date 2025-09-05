package com.example.demo.facade;

import com.example.demo.dto.PostCreateDto;
import com.example.demo.dto.PostResponseDto;
import com.example.demo.dto.PostUpdateDto;
import com.example.demo.mapper.PostMapper;
import com.example.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostFacade {

    private final PostService postService;
    private final PostMapper postMapper;

    public PostResponseDto getPostById(Long id) {
        return postMapper.postToPostResponseDto(postService.getPostById(id));
    }

    public PostResponseDto createNewPost(PostCreateDto dto) {
        return postMapper.postToPostResponseDto(postService.createNewPost(dto));
    }

    public void updatePost(Long id, PostUpdateDto dto) {
        postService.updatePost(id, dto);
    }

    public void deletePost(Long id) {
        postService.deletePost(id);
    }

    public List<PostResponseDto> getAllPosts() {
        return postMapper.postListToPostResponseDtoList(postService.getAllPosts());
    }
}
