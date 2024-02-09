package com.ddingmate.ddingmate.post.state;

import lombok.Getter;

@Getter
public enum Type {
    STUDY("스터디"), TEAMPLAY("팀플");

    private final String value;

    Type(String value) {
        this.value = value;
    }
}
