package com.ddingmate.ddingmate.member.dto.request;

import com.ddingmate.ddingmate.member.domain.Member;
import com.ddingmate.ddingmate.member.state.Major;
import com.ddingmate.ddingmate.member.state.Role;
import com.ddingmate.ddingmate.member.state.Univ;
import com.ddingmate.ddingmate.post.state.Category;
import com.ddingmate.ddingmate.util.valid.ValidEnum;
import com.ddingmate.ddingmate.util.valid.YearMonth;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

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
    @ValidEnum(enumClass = Univ.class)
    @Enumerated(value = EnumType.STRING)
    private Univ univ;
    @ValidEnum(enumClass = Major.class)
    @Enumerated(value = EnumType.STRING)
    private Major major;
    @NotNull
    private Long studentId;
    @YearMonth(pattern = "yyyy-MM-dd")
    private LocalDate birth;
    private String introduction;
    @ValidEnum(enumClass = Category.class)
    @Enumerated(value = EnumType.STRING)
    private List<Category> categories;

    public Member toEntity(String encodePassword) {
        return Member.builder()
                .email(this.email)
                .password(encodePassword)
                .name(this.name)
                .major(this.major)
                .studentId(this.studentId)
                .birth(this.birth)
                .introduction(this.introduction)
                .categories(this.categories)
                .univ(this.univ)
                .role(Role.USER)
                .build();
    }
}
