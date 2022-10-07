package com.ssafy.uniqon.controller.startup.qna;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ssafy.uniqon.config.SecurityConfig;
import com.ssafy.uniqon.controller.RestDocsTestSupport;
import com.ssafy.uniqon.controller.WithMockCustomUser;
import com.ssafy.uniqon.dto.startup.qna.AnswerRequestDto;
import com.ssafy.uniqon.dto.startup.qna.AnswerUpdateRequestDto;
import com.ssafy.uniqon.dto.startup.qna.StartupQuestionUpdateReqDto;
import com.ssafy.uniqon.exception.ex.CustomException;
import com.ssafy.uniqon.exception.ex.ErrorCode;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
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
                        post("/app/invest/answer/{startupQuestionId}", 1L)
                                .header("Authorization", "Bearer " + accessToken)
                                .content(objectMapper.writeValueAsString(answerRequestDto))
                                .contentType(MediaType.APPLICATION_JSON)

                ).andExpect(status().isCreated())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("startupQuestionId").description("스타트업 Question ID")),
                                requestFields(
                                        fieldWithPath("parentId").description("대댓글일 경우 startupAnswerID" +
                                                "대댓글이 아닐경우 null").optional().attributes(
                                                field("constraints", "")
                                        ),
                                        fieldWithPath("answer").description("질문에 대한 댓글").attributes(
                                                field("constraints", "길이 50 이하")
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
                        put("/app/invest/answer/{startupAnswerId}", 1L).
                                header("Authorization", "Bearer " + accessToken)
                                .content(objectMapper.writeValueAsString(answerUpdateRequestDto))
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("startupAnswerId").description("스타트업 Answer ID")),
                                requestFields(
                                        fieldWithPath("answer").description("질문에 대한 댓글(수정)").type(JsonFieldType.STRING).attributes(field(
                                                "constraints", "길이 100 이하"
                                        ))
                                )
                        )
                );
    }

    @DisplayName(value = "스타트업 answer 수정 권한 없을 경우")
    @WithMockCustomUser
    @Test
    public void 스타트업_댓글수정_권한_없을_경우() throws Exception {
        AnswerUpdateRequestDto answerUpdateRequestDto = new AnswerUpdateRequestDto("Answer Update");

        doThrow(new CustomException(ErrorCode.INVALID_ACCESS_MEMBER)).when(answerService)
                        .답변수정(anyLong(), anyLong(), any(AnswerUpdateRequestDto.class));

        mockMvc.perform(
                        put("/app/invest/answer/{startupAnswerId}", 1L).
                                header("Authorization", "Bearer " + accessToken)
                                .content(objectMapper.writeValueAsString(answerUpdateRequestDto))
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().is4xxClientError())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("startupAnswerId").description("스타트업 Answer ID")),
                                requestFields(
                                        fieldWithPath("answer").description("질문에 대한 댓글(수정)").type(JsonFieldType.STRING).attributes(field(
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
                        delete("/app/invest/answer/{startupAnswerId}", 1L)
                                .header("Authorization", "Bearer " + accessToken)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("startupAnswerId").description("스타트업 answer ID"))
                        )
                );
    }

    @DisplayName(value = "스타트업 answer 삭제 권한 없을 경우")
    @WithMockCustomUser
    @Test
    public void 스타트업_댓글삭제_권한X() throws Exception {

        doThrow(new CustomException(ErrorCode.INVALID_ACCESS_MEMBER)).when(answerService)
                        .답변삭제(anyLong(), anyLong());

        mockMvc.perform(
                        delete("/app/invest/answer/{startupAnswerId}", 1L)
                                .header("Authorization", "Bearer " + accessToken)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().is4xxClientError())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("startupAnswerId").description("스타트업 answer ID"))
                        )
                );
    }
}

