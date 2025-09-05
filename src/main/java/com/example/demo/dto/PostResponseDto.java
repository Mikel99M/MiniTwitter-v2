package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PostResponseDto {

    @NotNull
    private Long id;

    @NotNull
    private Long authorId;

    @NotBlank
    @Size(min = 1, max = 500)
    private String text;

    @NotNull
    private LocalDateTime postedAt;

    @NotNull
    private int likeCount;
}
