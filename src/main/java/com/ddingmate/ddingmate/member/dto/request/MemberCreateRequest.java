package com.ddingmate.ddingmate.member.dto.request;

import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.member.state.Major;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberCreateRequest {

    private String email;
    private String password;
    private String passwordCheck;
    private String name;
    private Major major;
    private Long studentId;
    private LocalDate birth;
    private String introduction;

    public Member toEntity() {
        return Member.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .major(this.major)
                .studentId(this.studentId)
                .birth(this.birth)
                .introduction(this.introduction)
                .build();
    }
}
