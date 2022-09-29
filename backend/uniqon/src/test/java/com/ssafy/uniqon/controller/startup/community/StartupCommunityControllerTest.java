package com.ssafy.uniqon.controller.startup.community;

import com.ssafy.uniqon.config.SecurityConfig;
import com.ssafy.uniqon.controller.RestDocsTestSupport;
import com.ssafy.uniqon.controller.startup.invest.StartupInvestController;
import com.ssafy.uniqon.dto.startup.community.*;
import com.ssafy.uniqon.service.startup.community.StartupCommunityService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

import static org.springframework.restdocs.request.RequestDocumentation.*;

import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;

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

@WebMvcTest(controllers = StartupCommunityController.class, excludeFilters = { //!Added!
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})
class StartupCommunityControllerTest extends RestDocsTestSupport {

    private String accessToken = "accessToken";

    @MockBean
    private StartupCommunityService startupCommunityService;

    @DisplayName(value = "스타트업 커뮤니티 조회")
    @Test
    public void 스타트업_커뮤니티_조회() throws Exception {


        StartupCommunityResponseListDto startupCommunityResponseListDto = new StartupCommunityResponseListDto(
                "title", "nickName", new Integer(10), LocalDateTime.now()
        );
        StartupCommunityResponseListDto startupCommunityResponseListDto2 = new StartupCommunityResponseListDto(
                "title2", "nickName2", new Integer(10), LocalDateTime.now()
        );
        List<StartupCommunityResponseListDto> startupCommunityResponseListDtos = Arrays.asList(startupCommunityResponseListDto, startupCommunityResponseListDto2);

        given(startupCommunityService.communityList(any())).willReturn(startupCommunityResponseListDtos);

        mockMvc.perform(get("/api/invest/community/{startupId}", 1L)
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("startupId").description("startupID"))
                        )
                );
    }

    @DisplayName(value = "스타트업 커뮤니티 등록")
    @Test
    public void 스타트업_커뮤니티_등록() throws Exception {
        StartupCommunityRequestDto startupCommunityRequestDto = new StartupCommunityRequestDto("title", "content");

        mockMvc.perform(
                        post("/api/invest/community/{startupId}", 1L)
                                .header("Authorization", "Bearer " + accessToken)
                                .content(objectMapper.writeValueAsString(startupCommunityRequestDto))
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("startupId").description("startupID")),
                                requestFields(
                                        fieldWithPath("title").description("커뮤니티 글 제목").type(JsonFieldType.STRING).attributes(field(
                                                "constraints", "길이 100 이하"
                                        )),
                                        fieldWithPath("content").description("커뮤니티 글 내용").type(JsonFieldType.STRING).attributes(field(
                                                "constraints", "길이 100 이하"
                                        ))
                                )
                        )
                );
    }

    @DisplayName(value = "스타트업 커뮤니티 수정")
    @Test
    public void 스타트업_커뮤니티_수정() throws Exception {
        StartupCommunityRequestModifyDto startupCommunityRequestModifyDto = new StartupCommunityRequestModifyDto("title", "content");

        mockMvc.perform(
                        put("/api/invest/community/{startupId}/{communityId}", 1L, 1L)
                                .header("Authorization", "Bearer " + accessToken)
                                .content(objectMapper.writeValueAsString(startupCommunityRequestModifyDto))
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("startupId").description("startupID"),
                                        parameterWithName("communityId").description("communityID")),
                                requestFields(
                                        fieldWithPath("title").description("커뮤니티 글 제목").type(JsonFieldType.STRING).attributes(field(
                                                "constraints", "길이 100 이하"
                                        )),
                                        fieldWithPath("content").description("커뮤니티 글 내용").type(JsonFieldType.STRING).attributes(field(
                                                "constraints", "길이 100 이하"
                                        ))
                                )
                        )
                );
    }

    @DisplayName(value = "스타트업 커뮤니티 삭제")
    @Test
    public void 스타트업_커뮤니티_삭제() throws Exception {
        mockMvc.perform(
                        delete("/api/invest/community/{startupId}/{communityId}", 1L, 1L)
                                .header("Authorization", "Bearer " + accessToken)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("startupId").description("startupID"),
                                        parameterWithName("communityId").description("communityID"))
                        )
                );
    }

    @DisplayName(value = "스타트업 커뮤니티 상세정보조회")
    @Test
    public void 스타트업_커뮤니티_상세정보조회() throws Exception {

        CommunityCommentChildrenResponseDto communityCommentChildrenResponseDto = CommunityCommentChildrenResponseDto.builder().parentId(1L).commentId(2L).content("댓글2").nickName("nickName2")
                .myComment(Boolean.FALSE).createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();

        CommunityCommentChildrenResponseDto communityCommentChildrenResponseDto2 = CommunityCommentChildrenResponseDto.builder().parentId(1L).commentId(3L).content("댓글3").nickName("nickName3")
                .myComment(Boolean.FALSE).createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();

        List<CommunityCommentChildrenResponseDto> communityCommentChildrenResponseDtos = Arrays.asList(communityCommentChildrenResponseDto, communityCommentChildrenResponseDto2);

        CommunityCommentResponseDto communityCommentResponseDto = CommunityCommentResponseDto.builder().commentId(1L).content("댓글1").nickName("nickName1").myComment(Boolean.TRUE)
                .createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).children(communityCommentChildrenResponseDtos).build();

        CommunityCommentResponseDto communityCommentResponseDto2 = CommunityCommentResponseDto.builder().commentId(4L).content("댓글4").nickName("nickName4").myComment(Boolean.TRUE)
                .createdDate(LocalDateTime.now()).updateDate(LocalDateTime.now()).build();

        List<CommunityCommentResponseDto> communityCommentResponseDtos = Arrays.asList(communityCommentResponseDto, communityCommentResponseDto2);

        StartupCommunityResponseDetailDto startupCommunityResponseDetailDto = StartupCommunityResponseDetailDto.builder().content("content").title("title").nickName("nickName")
                .createdDate(LocalDateTime.now()).commentList(communityCommentResponseDtos).build();

        given(startupCommunityService.communityDetail(1L)).willReturn(startupCommunityResponseDetailDto);

        mockMvc.perform(
                        get("/api/invest/community/detail/{communityId}", 1L)
                                .header("Authorization", "Bearer " + accessToken)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("communityId").description("community ID"))
                        )
                );
    }
}