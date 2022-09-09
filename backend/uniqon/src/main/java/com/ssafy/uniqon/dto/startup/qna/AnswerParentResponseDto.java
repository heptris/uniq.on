package com.ssafy.uniqon.dto.startup.qna;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import com.ssafy.uniqon.domain.startup.qna.StartupAnswer;
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
public class AnswerParentResponseDto {

    private Long startupAnswerId;
    private Long memberId;
    private String nickname;
    private String answer;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createDate;
    private Long parentId;

    private Boolean myAnswer;

    @Builder.Default
    private List<AnswerChildrenResponseDto> answerChildren = new ArrayList<>();

    public AnswerParentResponseDto(Long startupAnswerId, Long memberId, String nickname,
                                   String answer, LocalDateTime createDate, Long parentId, Boolean myAnswer) {
        this.startupAnswerId = startupAnswerId;
        this.memberId = memberId;
        this.nickname = nickname;
        this.answer = answer;
        this.createDate = createDate;
        this.parentId = parentId;
        this.myAnswer = myAnswer;
    }

    public void changeChildren(List<AnswerChildrenResponseDto> answerChildrenResponseDtoList) {
        this.answerChildren = answerChildrenResponseDtoList;
    }
}
