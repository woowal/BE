package com.ddingmate.ddingmate.member.dto.request;

import lombok.Getter;

@Getter
public class MemberUpdateRequest {

    private Long id;
    private String introduction;
    private String major;
}
