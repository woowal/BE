package com.ddingmate.ddingmate.member.domain;

import com.ddingmate.ddingmate.member.dto.request.MemberUpdateRequest;
import com.ddingmate.ddingmate.member.state.Major;
import com.ddingmate.ddingmate.member.state.Role;
import com.ddingmate.ddingmate.member.state.Univ;
import com.ddingmate.ddingmate.member.util.MajorConverter;
import com.ddingmate.ddingmate.member.util.RoleConverter;
import com.ddingmate.ddingmate.member.util.UnivConverter;
import com.ddingmate.ddingmate.post.state.Category;
import com.ddingmate.ddingmate.post.util.CategoryConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;
    private String name;
    @Convert(converter = MajorConverter.class)
    private Major major;
    private Long studentId;
    private LocalDate birth;
    private String introduction;
    @Convert(converter = UnivConverter.class)
    private Univ univ;
    @Convert(converter = CategoryConverter.class)
    private List<Category> categories;
    @Convert(converter = RoleConverter.class)
    private Role role;

    @Builder
    public Member(String email, String password, String name, Major major, Long studentId,
                  LocalDate birth, String introduction, Univ univ, List<Category> categories, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.major = major;
        this.studentId = studentId;
        this.birth = birth;
        this.introduction = introduction;
        this.univ = univ;
        this.categories = categories;
        this.role = role;
    }

    public void update(MemberUpdateRequest memberUpdateRequest) {
        this.introduction = memberUpdateRequest.getIntroduction();
        this.univ = memberUpdateRequest.getUniv();
        this.major = memberUpdateRequest.getMajor();
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void removeCategory(Category category) {
        this.categories.remove(category);
    }

}
