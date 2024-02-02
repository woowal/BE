package com.ddingmate.ddingmate.member.domain;

import jakarta.persistence.*;

@Entity

public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

}
