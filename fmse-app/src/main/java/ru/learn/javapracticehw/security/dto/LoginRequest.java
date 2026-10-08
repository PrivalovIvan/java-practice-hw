package ru.learn.javapracticehw.security.dto;

public record LoginRequest(
    String email,
    String password
) {

}
