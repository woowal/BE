package com.ddingmate.ddingmate.member.dto.request;

import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.member.state.Major;
import com.ddingmate.ddingmate.util.valid.ValidEnum;
import com.ddingmate.ddingmate.util.valid.YearMonth;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class MemberCreateRequest {

    @NotEmpty
    private String email;
    @NotEmpty
    private String password;
    @NotEmpty
    private String passwordCheck;
    @NotEmpty
    private String name;
    @ValidEnum(enumClass = Major.class)
    @Enumerated(value = EnumType.STRING)
    private Major major;
    @NotNull
    private Long studentId;
    @YearMonth(pattern = "yyyyMMdd")
    private LocalDate birth;
    private String introduction;

    public Member toEntity(String encodePassword) {
        return Member.builder()
                .email(this.email)
                .password(encodePassword)
                .name(this.name)
                .major(this.major)
                .studentId(this.studentId)
                .birth(this.birth)
                .introduction(this.introduction)
                .build();
    }
}
