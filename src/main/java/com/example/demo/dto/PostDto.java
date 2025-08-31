package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostDto {

    @NotNull
    private Long authorId;

    @NotBlank
    @Size(min = 1, max = 500)
    private String text;

}
