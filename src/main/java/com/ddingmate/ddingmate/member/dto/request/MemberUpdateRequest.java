package com.ddingmate.ddingmate.member.dto.request;

import com.ddingmate.ddingmate.member.state.Major;
import com.ddingmate.ddingmate.member.state.Univ;
import com.ddingmate.ddingmate.post.state.Category;
import com.ddingmate.ddingmate.util.valid.ValidEnum;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class MemberUpdateRequest {

    @NotEmpty
    private String name;
    @NotEmpty
    private String introduction;
    @NotEmpty
    private Long studentId;
    @NotEmpty
    private LocalDate birth;
    @ValidEnum(enumClass = Univ.class)
    private Univ univ;
    @ValidEnum(enumClass = Major.class)
    private Major major;
    @ValidEnum(enumClass = Category.class)
    private List<Category> categories;
}
