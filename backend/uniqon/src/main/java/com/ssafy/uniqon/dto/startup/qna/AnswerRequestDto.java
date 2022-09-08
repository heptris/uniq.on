package com.ssafy.uniqon.dto.startup.qna;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerRequestDto {
 //   private Long memberId;  // 작성자 기본키
    private Long startupQuestionId; // Question 기본키
    private Long parentId; // 대댓글일경우 부모 기본키
    private String answer;
}
