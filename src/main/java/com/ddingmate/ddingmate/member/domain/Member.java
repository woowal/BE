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
import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {

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

    public void update(String name, String introduction, Long studentId, LocalDate birth, Univ univ, Major major, List<Category> categories) {
        this.name = name;
        this.studentId = studentId;
        this.birth = birth;
        this.categories = categories;
        this.introduction = introduction;
        this.univ = univ;
        this.major = major;
    }

    public void updatePassword(String newPassword) {
        this.password = newPassword;
    }

}
