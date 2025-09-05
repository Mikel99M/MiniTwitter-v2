package com.example.demo.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

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

    @NotNull
    @Past
    private LocalDate birthDate;

    @NotBlank
    @Size(min = 6)
    private String password;

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
