package com.ddingmate.ddingmate.member.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class LoginRequest {
    @NotEmpty
    String email;
    @NotEmpty
    String password;
}
