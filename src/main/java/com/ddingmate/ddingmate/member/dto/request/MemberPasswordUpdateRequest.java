package com.ddingmate.ddingmate.member.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class MemberPasswordUpdateRequest {
    @NotEmpty
    private String oldPassword;
    @NotEmpty
    private String newPassword;
    @NotEmpty
    private String newPasswordCheck;
}
