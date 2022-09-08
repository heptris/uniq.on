package com.ssafy.uniqon.exception.ex;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    // 404 NOT FOUND 잘못된 리소스 접근

    MEMBER_NOT_FOUND(404, "존재하지 않은 회원 ID 입니다."),
    QUESTION_NOT_FOUND(404, "존재하지 않은 Question ID 입니다.");

    private final int status;
    private final String message;

    }
