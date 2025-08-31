package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationDto {

    @NotBlank
    @Size(min = 2, max = 100)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 100)
    private String lastName;

    @NotBlank
    @Size(min = 3, max = 50)
    private String userName;

    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 6)
    private String password;

}
