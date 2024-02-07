package com.ddingmate.ddingmate.member.dto.request;

import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.member.state.Major;
import com.ddingmate.ddingmate.member.state.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class RegisterRequest {

    private String email;
    private String password;
    private String passwordCheck;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Major major;
    private Long studentId;
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
                .role(Role.USER)
                .build();
    }
}
