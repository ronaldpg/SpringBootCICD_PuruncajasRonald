package com.example.springlab.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class StudentCreateRequest {

    @NotBlank @Size(min = 3, max = 120)
    private String fullName;

    @NotBlank @Email @Size(min =10, max = 120)
    private String email;

    @NotNull
    @Past(message = "La fecha debe de ser una antigua")
    private LocalDate birthDate;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
