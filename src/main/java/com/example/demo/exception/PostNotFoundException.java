package com.example.demo.exception;

public class PostNotFoundException extends RuntimeException {

    public PostNotFoundException(Long id) {
        super("Post with id {" + id + "} not found.");
    }
}
