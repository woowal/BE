package com.ddingmate.ddingmate.member.dto.response;

import com.ddingmate.ddingmate.member.domain.Member;

import java.time.LocalDate;

public class MemberResponse {

    private String name;
    private String major;
    private LocalDate birth;
    private String introduction;
    private String category;

    public MemberResponse(String name, String major, LocalDate birth, String introduction, String category) {
        this.name = name;
        this.major = major;
        this.birth = birth;
        this.introduction = introduction;
        this.category = category;
    }

    public static MemberResponse from(Member member) {
        return new MemberResponse(member.getName(), member.getMajor(), member.getBirth(), member.getIntroduction(), member.getCategory());
    }
}
