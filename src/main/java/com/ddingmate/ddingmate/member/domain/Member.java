package com.ddingmate.ddingmate.member.domain;

import jakarta.persistence.*;
import lombok.Builder;

import java.util.Date;

@Entity
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


    @Builder
    public Member(String email, String password, String name, String studentId, Date birth, String introduction) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.studentId = studentId;
        this.birth = birth;
        this.introduction = introduction;
    }


}
