package com.ssafy.uniqon.exception.ex;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    FILE_UPLOAD_ERROR(400, "파일 업로드 에러"),

    // 401
    INVALID_REFRESH_TOKEN(401, "유효하지 않은 RefreshToken 입니다."),
    INVALID_ACCESS_TOKEN(401, "유효하지 않은 accessToken 입니다."),
    EXPIRED_ACCESS_TOKEN(401, "만료된 accessToken 입니다."),
    INVALID_ACCESS_MEMBER(401, "유효하지 않은 Member 입니다."),
    NOT_EQUAL_PASSWORD(400, "비밀번호가 서로 일치하지 않습니다"),

    // 404 NOT FOUND 잘못된 리소스 접근
    MEMBER_NOT_FOUND(404, "존재하지 않은 회원 ID 입니다."),
    MEMBER_WALLET_ADDRESS_NOT_FOUND(404, "존재하지 않는 지갑주소입니다."),
    MEMBER_NICKNAME_NOT_FOUND(404, "존재하지 않은 닉네임입니다."),
    QUESTION_NOT_FOUND(404, "존재하지 않은 Question ID 입니다."),
    ANSWER_NOT_FOUND(404, "존재하지 않은 Answer ID 입니다."),
    STARTUP_NOT_FOUND(404, "존재하지 않은 Startup ID 입니다."),
    ALARM_NOT_FOUND(404, "존재하지 않은 Alarm ID 입니다."),
    COMMUNITY_NOT_FOUND(404, "존재하지 않은 Community ID 입니다."),

    //409 CONFLICT 중복된 리소스
    ALREADY_SAVED_MEMBER(409, "이미 가입되어 있는 회원입니다."),
    ALREADY_USED_NICKNAME(409, "이미 사용중인 닉네임입니다.");

    private final int status;
    private final String message;

    }
