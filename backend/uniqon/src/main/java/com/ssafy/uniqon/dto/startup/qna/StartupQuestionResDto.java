package com.ssafy.uniqon.dto.startup.qna;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.uniqon.domain.startup.qna.StartupQuestion;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StartupQuestionResDto {

    private String nickname;
    private Long memberId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createDate;
    private Long startupQuestionId;
    private String question;

    private Boolean myQuestion;

    private List<AnswerParentResponseDto> answerParentResponseDtoList = new ArrayList<>();
        public StartupQuestionResDto(String nickname, Long memberId, LocalDateTime createDate,
                                     Long startupQuestionId, String question, Boolean myQuestion) {
        this.nickname = nickname;
        this.memberId = memberId;
        this.createDate = createDate;
        this.startupQuestionId = startupQuestionId;
        this.question = question;
        this.myQuestion = myQuestion;
    }

    public StartupQuestionResDto(StartupQuestion startupQuestion) {
        this.nickname = startupQuestion.getMember().getNickname();
        this.memberId = startupQuestion.getMember().getId();
        this.createDate = startupQuestion.getCreatedDate();
        this.startupQuestionId = startupQuestion.getId();
        this.question = startupQuestion.getQuestion();
    }

    public void changeParentResponseDto(List<AnswerParentResponseDto> answerParentResponseDtoList) {
        this.answerParentResponseDtoList = answerParentResponseDtoList;
    }

}
