package com.ssafy.uniqon.dto.startup.qna;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerUpdateRequestDto {

    private Long startupAnswerId;
    private String answer;
}
