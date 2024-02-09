package com.ddingmate.ddingmate.member.dto.request;

import com.ddingmate.ddingmate.member.state.Major;
import com.ddingmate.ddingmate.util.valid.ValidEnum;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class MemberUpdateRequest {

    @NotEmpty
    private String introduction;
    @ValidEnum(enumClass = Major.class)
    private Major major;
}
