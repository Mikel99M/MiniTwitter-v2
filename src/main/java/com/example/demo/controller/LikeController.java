package com.example.demo.controller;

import com.example.demo.dto.LikeDto;
import com.example.demo.dto.PostResponseDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.facade.LikeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("v1/likes")
public class LikeController {

    private final LikeFacade likeFacade;

    @GetMapping()
    public ResponseEntity<List<LikeDto>> getAllLikes() {
        return ResponseEntity.ok().body(likeFacade.getAllLikes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LikeDto> getLikeById(@PathVariable Long id) {
        return ResponseEntity.ok().body(likeFacade.getLikeById(id));
    }

    @GetMapping("/{id}/likedPosts")
    public ResponseEntity<List<PostResponseDto>> getAllPostsLikedByUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(likeFacade.getAllPostsLikedByUser(id));
    }

    @GetMapping("/{id}/likeGivers")
    public ResponseEntity<List<UserResponseDto>> getAllUserWhoLikedPost(@PathVariable Long id) {
        return ResponseEntity.ok().body(likeFacade.getAllUserWhoLikedPost(id));
    }

    @PostMapping("/{userId}/{postId}")
    public ResponseEntity<Void> toggleLike(@PathVariable Long userId, @PathVariable Long postId) {
        likeFacade.toggleLike(userId, postId);
        return ResponseEntity.noContent().build();
    }
}
