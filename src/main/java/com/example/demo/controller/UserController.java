package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dto.UserRegistrationDto;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.facade.UserFacade;
import org.springframework.http.MediaType;
import com.example.demo.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;
    private final UserFacade userFacade;

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(userFacade.getUserById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponseDto> createNewUser(@RequestBody @Valid UserRegistrationDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userFacade.createNewUser(dto));
    }

}
