package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LikeDto {

    @NotNull
    private Long userId;

    @NotNull
    private Long postId;

}