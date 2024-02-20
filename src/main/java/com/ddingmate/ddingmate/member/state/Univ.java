package com.ddingmate.ddingmate.member.state;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Univ {

    ICT융합대학("ICT 융합대학", new Major[]{
            Major.응용소프트웨어, Major.데이터테크놀로지, Major.디지털콘텐츠디자인, Major.정보통신공학
    }),
    인문대학("인문대학", new Major[] {
            Major.국어국문학과, Major.중어중문학과, Major.일어일문학과, Major.영어영문학과,
            Major.사학과, Major.문헌정보학과, Major.아랍지역학과, Major.미술사학과, Major.철학과,
            Major.문예창작학과, Major.글로벌한국어학과, Major.글로벌아시아문화학과
    }),
    사회과학대학("사회과학대학", new Major[] {
            Major.행정학과, Major.경제학과, Major.정치외교학과, Major.디지털미디어학과,
            Major.아동학과, Major.청소년지도학과
    }),
    경영대학("경영대학", new Major[] {
            Major.경영학과, Major.국제통상학과, Major.경영정보학과,
            Major.부동산학과by경영
    }),
    법과대학("법과대학", new Major[] {
            Major.법학과, Major.법무정책학과
    }),
    미래융합대학("미래융합대학", new Major[] {
            Major.창의융합인재학부, Major.사회복지학과, Major.부동산학과by미래융합,
            Major.법무행정학과, Major.심리치료학과, Major.미래융합경영학과,
            Major.멀티디자인학과, Major.계약학과
    }),
    자연과학대학("자연과학대학", new Major[] {
            Major.수학과, Major.물리학과, Major.화학과, Major.식품영양학과,
            Major.생명과학정보학과
    }),
    공과대학("공과대학", new Major[] {
        Major.전기공학, Major.전자공학, Major.반도체공학, Major.화학공학,
        Major.신소재공학, Major.환경에너지공학, Major.컴퓨터공학, Major.토목환경공학,
        Major.교통공학, Major.기계공학, Major.산업경영공학
    }),
    예술체육대학("예술체육대학", new Major[] {
        Major.디자인학부, Major.스포츠학부, Major.바둑학부, Major.예술학부
    }),
    건축대학("건축대학", new Major[]{
        Major.건축학부
    }),
    방목기초교육대학("방목기초교육대학", new Major[] {
        Major.전공자유, Major.융합전공
    }),
    국제학부("국제학부", new Major[] {
        Major.글로벌비즈니스
    })
    ;

    private String univ;
    private Major[] containMajors;

    Univ(String univ, Major[] containMajors) {
        this.univ = univ;
        this.containMajors = containMajors;
    }

    public static Univ fromCode(String db) {
        return Arrays.stream(Univ.values())
                .filter(v -> v.getUniv().equals(db))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("대학 카테고리에 %s가 존재하지 않습니다.", db)));
    }
}
