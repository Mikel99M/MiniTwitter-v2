package com.example.demo.service;

import com.example.demo.domain.Like;
import com.example.demo.domain.Post;
import com.example.demo.domain.User;
import com.example.demo.exception.LikeNotFoundException;
import com.example.demo.exception.PostNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.LikeRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public void toggleLike(Long userId, Long postId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(postId));

        if (likeRepository.existsByLikeGiverAndLikedPost(user, post)) {

            likeRepository.deleteByLikeGiverAndLikedPost(user, post);
            log.info("User " + user.getUserName() + " unliked post with id: " + post.getId());

        } else {

            Like like = new Like();
            like.setLikedAt(LocalDateTime.now());
            like.setLikeGiver(user);
            like.setLikedPost(post);

            likeRepository.save(like);
            log.info("User " + user.getUserName() + " liked post with id: " + post.getId());
        }


    }

    @Transactional
    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    @Transactional
    public Like getLikeById(Long id) {
        return likeRepository.findById(id).orElseThrow(() -> new LikeNotFoundException(id));
    }
}
