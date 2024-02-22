package com.ddingmate.ddingmate.member.dto.response;

import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.post.state.Category;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MemberResponse(String name, String univ, String major, LocalDate birth, Long studentId, String introduction, List<Category> categories) {

    public static MemberResponse from(Member member) {
        return new MemberResponse(member.getName(), member.getUniv().getValue(), member.getMajor().getValue(), member.getBirth(), member.getStudentId(), member.getIntroduction(), member.getCategories());
    }
}
