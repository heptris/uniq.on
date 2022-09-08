package com.ssafy.uniqon.service.startup.qna;

import com.ssafy.uniqon.domain.startup.qna.StartupAnswer;
import com.ssafy.uniqon.dto.startup.qna.AnswerRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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

}