package com.ssafy.uniqon.service.startup.qna;

import com.google.gson.Gson;
import com.ssafy.uniqon.domain.member.Member;
import com.ssafy.uniqon.domain.member.MemberType;
import com.ssafy.uniqon.domain.startup.Startup;
import com.ssafy.uniqon.domain.startup.qna.StartupQuestion;
import com.ssafy.uniqon.dto.startup.qna.StartupQuestionReqDto;
import com.ssafy.uniqon.dto.startup.qna.StartupQuestionResDto;
import com.ssafy.uniqon.dto.startup.qna.StartupQuestionUpdateReqDto;
import com.ssafy.uniqon.service.member.MemberService;
import com.ssafy.uniqon.service.startup.StartupService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Rollback(value = false)
@Transactional
@SpringBootTest
class StartupQuestionServiceTest {

    @Autowired
    private StartupQuestionService startupQuestionService;

    @Test
    public void 질문등록() {
        StartupQuestionReqDto startupQuestionReqDto = StartupQuestionReqDto.builder()
                .startupId(1L)
                .question("a가 무슨뜻인가요?")
                .build();

        StartupQuestionReqDto startupQuestionReqDto2 = StartupQuestionReqDto.builder()
                .startupId(1L)
                .question("a가 무슨뜻인가요? - 2")
                .build();

        Long startupQuestionId = startupQuestionService.질문등록(1L, startupQuestionReqDto);
        Long startupQuestionId2 = startupQuestionService.질문등록(2L, startupQuestionReqDto2);

        StartupQuestionResDto startupQuestionResDto1 = startupQuestionService.질문하나조회(startupQuestionId);
        StartupQuestionResDto startupQuestionResDto2 = startupQuestionService.질문하나조회(startupQuestionId2);

        assertThat(startupQuestionResDto1.getQuestion()).isEqualTo(startupQuestionReqDto.getQuestion());
        assertThat(startupQuestionResDto2.getQuestion()).isEqualTo(startupQuestionReqDto2.getQuestion());

    }

    @Test
    public void 질문조회() {
        List<StartupQuestionResDto> list = startupQuestionService.질문조회(1L);
        list.forEach(lst -> {
            System.out.println(lst);
        });


        assertThat(list.get(2).getQuestion()).isEqualTo("질문3");
    }

    @Test
    public void 질문수정() {
        StartupQuestionUpdateReqDto startupQuestionUpdateReqDto = StartupQuestionUpdateReqDto.builder()
                .startupQuestionId(1L)
                .question("update question")
                .build();

        startupQuestionService.질문수정(startupQuestionUpdateReqDto);
        StartupQuestionResDto startupQuestionResDto = startupQuestionService.질문하나조회(1L);
        assertThat(startupQuestionResDto.getQuestion()).isEqualTo(startupQuestionUpdateReqDto.getQuestion());
    }

    @Test
    public void 질문삭제() {
        startupQuestionService.질문삭제(6L);
    }

}