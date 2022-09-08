package com.ssafy.uniqon.dto.startup.qna;

import com.ssafy.uniqon.domain.startup.qna.StartupQuestion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StartupQuestionResDto {

    private String nickname;
    private Long memberId;
    private LocalDateTime createDate;
    private Long startupQuestionId;
    private String question;

    private List<AnswerResponseDto> answerResponseDtoList = new ArrayList<>();

    public StartupQuestionResDto(StartupQuestion startupQuestion) {
        this.nickname = startupQuestion.getMember().getNickname();
        this.memberId = startupQuestion.getMember().getId();
        this.createDate = startupQuestion.getCreatedDate();
        this.startupQuestionId = startupQuestion.getId();
        this.question = startupQuestion.getQuestion();
    }

}
