package com.ddingmate.ddingmate.member.state;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Major {
    응용소프트웨어("응용소프트웨어"), 데이터테크놀로지("데이터테크놀로지"),
    디지털콘텐츠디자인( "디지털콘텐츠디자인"), 정보통신공학("정보통신공학"),
    국어국문학과("국어국문학과"), 중어중문학과("중어중문학과"), 일어일문학과("일어일문학과"),
    영어영문학과("영어영문학과"), 사학과("사학과"), 문헌정보학과("문헌정보학과"),
    아랍지역학과("아랍지역학과"), 미술사학과("미술사학과"), 철학과("철학과"), 문예창작학과("문예창작학과"),
    글로벌한국어학과("글로벌한국어학과"), 글로벌아시아문화학과("글로벌아시아문화학과"),
    행정학과("행정학과"), 경제학과("경제학과"), 정치외교학과("정치외교학과"),
    디지털미디어학과("디지털미디어학과"), 아동학과("아동학과"), 청소년지도학과("청소년지도학과"),
    경영학과("경영학과"), 국제통상학과("국제통상학과"), 경영정보학과("경영정보학과"),
    부동산학과by경영("부동산학과-경영"),
    법학과("법학과"), 법무정책학과("법무정책학과"),
    창의융합인재학부("창의융합인재학부"), 사회복지학과("사회복지학과"), 부동산학과by미래융합("부동산학과"),
    법무행정학과("법무행정학과"), 심리치료학과("심리치료학과"), 미래융합경영학과("미래융합경영학과"),
    멀티디자인학과("멀티디자인학과"), 계약학과("계약학과"),
    수학과("수학과"), 물리학과("물리학과"), 화학과("화학과"),
    식품영양학과("식품영양학과"), 생명과학정보학과("생명과학정보학과"),
    전기공학("전기공학"), 전자공학("전자공학"), 반도체공학("반도체공학"),
    화학공학("화학공학"), 신소재공학("신소재공학"), 환경에너지공학("환경에너지공학"),
    컴퓨터공학("컴퓨터공학"), 토목환경공학("토목환경공학"), 교통공학("교통공학"),
    기계공학("기계공학"), 산업경영공학("산업경영공학"),
    디자인학부("디자인학부"), 스포츠학부("스포츠학부"), 바둑학부("바둑학부"),
    예술학부("예술학부"),
    건축학부("건축학부"),
    전공자유("전공자유"), 융합전공("융합전공"),
    글로벌비즈니스("글로벌비즈니스");

    private String value;

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
