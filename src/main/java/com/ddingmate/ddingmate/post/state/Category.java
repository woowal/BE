package com.ddingmate.ddingmate.post.state;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Category {

    SPRINGBOOT("springboot"), REACT("react");

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
