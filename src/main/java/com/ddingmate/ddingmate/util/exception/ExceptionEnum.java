package com.ddingmate.ddingmate.util.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ExceptionEnum {
    NO_SUCH_MEMBER("해당 멤버가 존재하지 않습니다"),
    NO_SUCH_POST("해당 게시글이 존재하지 않습니다"),
    NO_SUCH_COMMENT("해당 댓글이 존재하지 않습니다"),
    NOT_MATCHED_PASSWORD("비밀번호와 비밀번호 확인이 일치하지 않습니다"),
    FAILED_TO_AUTH_PASSWORD("비밀번호가 잘못되었습니다"),
    ALREADY_EXIST_MARK("이미 찜한 게시글입니다"),
    WRONG_LOGIN_INFO("해당 계정은 유효하지 않습니다."),
    WRONG_EMAIL_FORM("명지대학교 이메일 형식과 일치하지 않습니다."),
    ALREADY_EXIST_EMAIL("이미 회원가입된 이메일입니다"),
    ;

    public final String errorMessage;
}
