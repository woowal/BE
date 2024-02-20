package com.ddingmate.ddingmate.post.state;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Category {

    언어("언어"), 역사("역사"), 철학("철학"), 문화("문화"), 법("법"),
    교육("교육"), 경제("경제"), 프로그래밍("프로그래밍"), 과학("과학"), 기계("기계");

    private final String value;

    Category(String value) {
        this.value = value;
    }

    public static Category fromCode(String db) {
        return Arrays.stream(Category.values())
                .filter(v -> v.getValue().equals(db))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("카테고리에 %s가 존재하지 않습니다.", db)));
    }
}
