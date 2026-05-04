package com.example.springlab.web.advice;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
    super(message);
    }
}
