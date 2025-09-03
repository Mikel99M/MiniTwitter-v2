package com.example.demo.service;


import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.dto.PostDto;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.mapper.PostMapper;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;

    @Transactional
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new PostNotFoundException(id));
    }

    @Transactional
    public Post createNewPost(PostDto dto) {

        User user = userRepository.findById(dto.getAuthorId()).orElseThrow(
                (() -> new UserNotFoundException(dto.getAuthorId())));
        log.info("Creating post from user: " + user.getUserName());

        Post post = postMapper.postDtoToPost(dto);

        return postRepository.save(post);
    }

    @Transactional
    public void updatePost(Long id, PostDto dto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new PostNotFoundException(id)
        );
        post.setText(dto.getText());
        postRepository.save(post);
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new PostNotFoundException(id)
        );
        log.info("Deleting post with id: " + post.getId());

        postRepository.delete(post);

    }
}
