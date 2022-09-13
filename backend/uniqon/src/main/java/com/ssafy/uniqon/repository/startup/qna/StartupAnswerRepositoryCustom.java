package com.ssafy.uniqon.repository.startup.qna;

import com.ssafy.uniqon.dto.startup.qna.AnswerParentResponseDto;

import java.util.List;

public interface StartupAnswerRepositoryCustom {
    List<AnswerParentResponseDto> findAnswerParentResponseDtoList(Long startupQuestionId);
}
