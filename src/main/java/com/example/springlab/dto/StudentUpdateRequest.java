package com.example.springlab.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class StudentUpdateRequest {

    @Size(min = 3, max = 120)
    private String fullName;

    @Past
    private LocalDate birthDate;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}