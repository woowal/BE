package com.ddingmate.ddingmate.member.dto.response;

import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.member.state.Major;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MemberResponse(String name, String major, LocalDate birth, String introduction, String category) {

    public static MemberResponse from(Member member) {
        return new MemberResponse(member.getName(), member.getMajor().getValue(), member.getBirth(), member.getIntroduction(), member.getCategory());
    }
}
