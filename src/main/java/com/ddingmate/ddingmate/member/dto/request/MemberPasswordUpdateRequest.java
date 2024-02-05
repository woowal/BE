package com.ddingmate.ddingmate.member.dto.request;

import lombok.Getter;

@Getter
public class MemberPasswordUpdateRequest {
    private Long id;
    private String oldPassword;
    private String newPassword;
    private String newPasswordCheck;
}
