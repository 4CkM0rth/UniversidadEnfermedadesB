package com.tolbargy.dtos;

public record LoginRequest(
        String usuario,
        String password
) {
}
