package com.ssafy.uniqon.service.startup.qna;

import com.ssafy.uniqon.dto.startup.qna.AnswerRequestDto;
import com.ssafy.uniqon.dto.startup.qna.AnswerUpdateRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

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
                .build();
        startupAnswerService.답변저장(5L, 4L, answerRequestDto);
    }

    @Test
    public void 자식답변등록() {
        AnswerRequestDto answerRequestDto = AnswerRequestDto.builder()
                .answer("test")
                .parentId(4L)
                .build();
        startupAnswerService.답변저장(2L, 2L, answerRequestDto);
       }

    @Test
    public void 답변수정() {
        AnswerUpdateRequestDto answerUpdateRequestDto = AnswerUpdateRequestDto.builder()
                .answer("답변4 수정")
                .build();

        startupAnswerService.답변수정(4L, 4L, answerUpdateRequestDto);

    }

    @Test
    public void 답변삭제() {
        startupAnswerService.답변삭제(4L, 1L);
    }

}