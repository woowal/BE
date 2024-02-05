package com.ddingmate.ddingmate.member.dto.response;

import com.ddingmate.ddingmate.member.state.Role;
import lombok.Builder;

@Builder
public record LoginResponse(
        String name, Role role, String token
) {
}
