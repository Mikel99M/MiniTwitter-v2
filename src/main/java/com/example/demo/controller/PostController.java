package com.example.demo.controller;

import com.example.demo.dto.PostDto;
import com.example.demo.facade.PostFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/v1/posts")
public class PostController {

    private final PostFacade postFacade;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(postFacade.getPostById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostDto> createNewPost(@RequestBody @Valid PostDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postFacade.createNewPost(dto));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> updatePost(@RequestBody @Valid PostDto dto, @PathVariable Long id) {
        postFacade.updatePost(id, dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postFacade.deletePost(id);
        return ResponseEntity.noContent().build();
    }


}
