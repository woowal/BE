package com.ddingmate.ddingmate.post.state;

import lombok.Getter;

@Getter
public enum Type {
    study("스터디"), teamplay("팀플");

    private final String value;

    Type(String value) {
        this.value = value;
    }
}
