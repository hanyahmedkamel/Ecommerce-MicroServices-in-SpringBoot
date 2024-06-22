package com.Almaamouny.UserService;

public record ActivateRequest(
        String email,
        Integer code
) {
}
