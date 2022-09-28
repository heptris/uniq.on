package com.ssafy.uniqon.controller.startup.qna;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.uniqon.config.SecurityConfig;
import com.ssafy.uniqon.controller.RestDocsTestSupport;
import com.ssafy.uniqon.controller.WithMockCustomUser;
import com.ssafy.uniqon.dto.startup.qna.AnswerRequestDto;
import com.ssafy.uniqon.dto.startup.qna.AnswerUpdateRequestDto;
import com.ssafy.uniqon.dto.startup.qna.StartupQuestionUpdateReqDto;
import com.ssafy.uniqon.service.startup.qna.StartupAnswerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

import static com.ssafy.uniqon.config.RestDocsConfig.field;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StartupAnswerController.class, excludeFilters = { //!Added!
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})
public class StartupAnswerControllerTest extends RestDocsTestSupport {

    private String accessToken = "accessToken";

    @MockBean
    private StartupAnswerService answerService;

    @DisplayName(value = "스타트업 answer 등록")
    @WithMockCustomUser
    @Test
    public void 스타트업_댓글등록() throws Exception {

        AnswerRequestDto answerRequestDto = new AnswerRequestDto(1L, "answer");

        mockMvc.perform(
                        post("/api/invest/answer/{startupQuestionId}", 1L)
                                .header("Authorization", "Bearer " + accessToken)
                                .content(objectMapper.writeValueAsString(answerRequestDto))
                                .contentType(MediaType.APPLICATION_JSON)

                ).andExpect(status().isCreated())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("startupQuestionId").description("스타트업 Question ID")),
                                requestFields(
                                        fieldWithPath("parentId").description("parent ID").attributes(
                                                field("constraints", "길이 100 이하")
                                        ),
                                        fieldWithPath("answer").description("answer").attributes(
                                                field("constraints", "길이 100 이하")
                                        )
                                )
                        )
                );
    }

    @DisplayName(value = "스타트업 answer 수정")
    @WithMockCustomUser
    @Test
    public void 스타트업_댓글수정() throws Exception {
        AnswerUpdateRequestDto answerUpdateRequestDto = new AnswerUpdateRequestDto("Answer Update");

        mockMvc.perform(
                        put("/api/invest/answer/{startupAnswerId}", 1L).
                                header("Authorization", "Bearer " + accessToken)
                                .content(objectMapper.writeValueAsString(answerUpdateRequestDto))
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("startupAnswerId").description("스타트업 Answer ID")),
                                requestFields(
                                        fieldWithPath("answer").description("질문에 대한 답변").type(JsonFieldType.STRING).attributes(field(
                                                "constraints", "길이 100 이하"
                                        ))
                                )
                        )
                );

    }

    @DisplayName(value = "스타트업 answer 삭제")
    @WithMockCustomUser
    @Test
    public void 스타트업_댓글삭제() throws Exception {
        mockMvc.perform(
                        delete("/api/invest/answer/{startupAnswerId}", 1L)
                                .header("Authorization", "Bearer " + accessToken)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("startupAnswerId").description("스타트업 answer ID"))
                        )
                );
    }
}

