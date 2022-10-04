package com.ssafy.uniqon.controller.startup.community;

import com.ssafy.uniqon.config.SecurityConfig;
import com.ssafy.uniqon.controller.RestDocsTestSupport;
import com.ssafy.uniqon.dto.startup.community.CommunityCommentRequestDto;
import com.ssafy.uniqon.dto.startup.community.CommunityCommentRequestModifyDto;
import com.ssafy.uniqon.service.startup.community.CommunityCommentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.ssafy.uniqon.config.RestDocsConfig.field;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CommunityCommentController.class, excludeFilters = { //!Added!
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})
class CommunityCommentControllerTest extends RestDocsTestSupport {

    private String accessToken = "accessToken";

    @MockBean
    private CommunityCommentService communityCommentService;

    @DisplayName(value = "스타트업 커뮤니티 댓글 등록")
    @Test
    public void 스타트업_커뮨니티_댓글_등록() throws Exception {
        CommunityCommentRequestDto commentRequestDto = new CommunityCommentRequestDto(1L, "content");

        mockMvc.perform(
                        post("/app/invest/community/{communityId}/comment", 1L)
                                .header("Authorization", "Bearer " + accessToken)
                                .content(objectMapper.writeValueAsString(commentRequestDto))
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("communityId").description("communityID")),
                                requestFields(
                                        fieldWithPath("parentId").description("대댓글일 경우 이전 댓글 communityId" +
                                                "대댓글이 아닐 경우 null").attributes(
                                                field("constraints", "")
                                        ),
                                        fieldWithPath("content").description("커뮤니티 댓글 내용").attributes(
                                                field("constraints", "길이 100 이하")
                                        )
                                )
                        ));
    }

    @DisplayName(value = "스타트업 커뮤니티 댓글 수정")
    @Test
    public void 스타트업_커뮤니티_댓글_수정() throws Exception {
        CommunityCommentRequestModifyDto commentRequestModifyDto = new CommunityCommentRequestModifyDto("content");

        mockMvc.perform(
                        put("/app/invest/community/{communityId}/{commentId}", 1L, 1L)
                                .header("Authorization", "Bearer " + accessToken)
                                .content(objectMapper.writeValueAsString(commentRequestModifyDto))
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("communityId").description("communityID"),
                                        parameterWithName("commentId").description("commentId")
                                ),
                                requestFields(
                                        fieldWithPath("content").description("커뮤니티 댓글 내용").attributes(
                                                field("constraints", "길이 100 이하")
                                        ).optional()
                                )
                        )
                );
    }

    @DisplayName(value = "스타트업_커뮤니티_댓글_삭제")
    @Test
    public void 스타트업_커뮤니티_댓글_삭제() throws Exception {
        mockMvc.perform(
                        delete("/app/invest/community/{communityId}/{commentId}", 1L, 1L)
                                .header("Authorization", "Bearer " + accessToken)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("communityId").description("communityID"),
                                        parameterWithName("commentId").description("commentId")
                                )
                        )
                );
    }
}