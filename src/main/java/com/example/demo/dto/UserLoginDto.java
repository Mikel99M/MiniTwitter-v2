package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLoginDto {

    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

}
