package com.example.demo.exception;

public class LikeNotFoundException extends RuntimeException {
    public LikeNotFoundException(Long id) {
        super("Like with id {" + id + "} not found.");
    }
}
