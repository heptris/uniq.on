package com.ssafy.uniqon.service.startup.qna;

import com.ssafy.uniqon.domain.startup.qna.StartupAnswer;
import com.ssafy.uniqon.dto.startup.qna.AnswerDeleteRequestDto;
import com.ssafy.uniqon.dto.startup.qna.AnswerRequestDto;
import com.ssafy.uniqon.dto.startup.qna.AnswerUpdateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Rollback(value = false)
@Transactional
@SpringBootTest
class StartupAnswerServiceTest {

    @Autowired
    private StartupAnswerService startupAnswerService;

    @Test
    public void 부모답변등록() {
        AnswerRequestDto answerRequestDto = AnswerRequestDto.builder()
                .answer("답변8")
                .startupQuestionId(4L)
                .build();
        startupAnswerService.답변저장(5L, answerRequestDto);
    }

    @Test
    public void 자식답변등록() {
        AnswerRequestDto answerRequestDto = AnswerRequestDto.builder()
                .answer("test")
                .startupQuestionId(2L)
                .parentId(4L)
                .build();
        startupAnswerService.답변저장(2L, answerRequestDto);
       }

    @Test
    public void 답변수정() {
        AnswerUpdateRequestDto answerUpdateRequestDto = AnswerUpdateRequestDto.builder()
                .startupAnswerId(4L)
                .answer("답변4 수정")
                .build();

        startupAnswerService.답변수정(answerUpdateRequestDto);

    }

    @Test
    public void 답변삭제() {
        AnswerDeleteRequestDto answerDeleteRequestDto = AnswerDeleteRequestDto.builder()
                .startupAnswerId(1L)
                .build();

        startupAnswerService.답변삭제(answerDeleteRequestDto);
    }

}