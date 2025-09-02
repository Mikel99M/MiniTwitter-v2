package com.example.demo.controller;

import com.example.demo.dto.PostDto;
import com.example.demo.facade.PostFacade;
import lombok.RequiredArgsConstructor;
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



}
