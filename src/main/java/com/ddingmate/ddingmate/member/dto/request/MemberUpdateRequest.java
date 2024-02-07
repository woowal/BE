package com.ddingmate.ddingmate.member.dto.request;

import com.ddingmate.ddingmate.member.state.Major;
import lombok.Getter;

@Getter
public class MemberUpdateRequest {

    private Long id;
    private String introduction;
    private Major major;
}
