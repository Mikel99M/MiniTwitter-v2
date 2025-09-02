package com.example.demo.service;


import com.example.demo.domain.Post;
import com.example.demo.dto.PostDto;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new PostNotFoundException(id));
    }
}
