package com.ddingmate.ddingmate.member.domain;

import com.ddingmate.ddingmate.member.state.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String password;
    private String name;
    private String studentId;
    private Date birth;
    private String introduction;
    private Role role;


    @Builder
    public Member(String email, String password, String name, String studentId, Date birth, String introduction, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.studentId = studentId;
        this.birth = birth;
        this.introduction = introduction;
        this.role = role;
    }


}
