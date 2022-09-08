package com.ssafy.uniqon.dto.startup.qna;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerResponseDto {

    private Long startupAnswerId;
    private Long memberId;
    private String nickname;
    private String answer;
    private LocalDateTime createDate;
}
