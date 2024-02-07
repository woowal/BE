package com.ddingmate.ddingmate.member.state;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Major {
    응용소프트웨어("응용소프트웨어"), 데이터테크놀로지("데이터테크놀로지");

    private final String value;

    Major(String value) {
        this.value = value;
    }

    public static Major fromCode(String db) {
        return Arrays.stream(Major.values())
                .filter(v -> v.getValue().equals(db))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("전공 카테고리에 %s가 존재하지 않습니다.", db)));
    }
}
