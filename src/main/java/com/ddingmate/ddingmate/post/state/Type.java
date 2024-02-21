package com.ddingmate.ddingmate.post.state;

import lombok.Getter;

@Getter
public enum Type {
    스터디("스터디"), 팀플("팀플"), 공모전("공모전");

    private final String value;

    Type(String value) {
        this.value = value;
    }
}
