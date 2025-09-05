package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.facade.UserFacade;
import org.springframework.http.MediaType;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserFacade userFacade;

    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok().body(userFacade.getAllUsers());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userFacade.getUserById(id));
    }

    @GetMapping(value = "/{id}/posts")
    public ResponseEntity<List<PostResponseDto>> getPostsMadeByUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(userFacade.getPostsMadeByUser(id));
    }

    @GetMapping(value = "/{id}/likedPosts")
    public ResponseEntity<List<PostResponseDto>> getPostsLikedByUser(@PathVariable Long id) {
        return ResponseEntity.ok().body(userFacade.getPostsLikedByUser(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDto> createNewUser(@RequestBody @Valid UserRegistrationDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userFacade.createNewUser(dto));
    }

    @PutMapping(value = "/user/{id}/password")
    public ResponseEntity<Void> updateUserPassword(@PathVariable Long id, @RequestBody @Valid UserPasswordChangeDto dto) {
        userFacade.updateUserPassword(id, dto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userFacade.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
