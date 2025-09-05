package com.example.demo.exception;

import com.example.demo.dto.UserRegistrationDto;

public class UserToYoungToRegisterException extends RuntimeException {

    public UserToYoungToRegisterException(UserRegistrationDto dto) {
        super("User " + dto.getFullName() + " is too young to register.");
    }
}
