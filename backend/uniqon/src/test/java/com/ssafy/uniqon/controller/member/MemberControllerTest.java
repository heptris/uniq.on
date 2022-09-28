package com.ssafy.uniqon.controller.member;

import com.ssafy.uniqon.config.SecurityConfig;
import com.ssafy.uniqon.controller.RestDocsTestSupport;
import com.ssafy.uniqon.controller.WithMockCustomUser;
import com.ssafy.uniqon.controller.auth.AuthController;
import com.ssafy.uniqon.domain.member.MemberType;
import com.ssafy.uniqon.dto.member.MemberFavStartupDto;
import com.ssafy.uniqon.dto.member.MemberProfileDto;
import com.ssafy.uniqon.dto.member.MemberUpdateDto;
import com.ssafy.uniqon.service.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.ssafy.uniqon.config.RestDocsConfig.field;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.partWithName;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MemberController.class, excludeFilters = { //!Added!
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})
class MemberControllerTest extends RestDocsTestSupport {

    private String accessToken = "accessToken";

    @MockBean
    private MemberService memberService;

    @DisplayName(value = "회원 프로필 수정")
    @WithMockCustomUser
    @Test
    public void 회원_프로필_수정() throws Exception {

        MemberUpdateDto memberUpdateDto = new MemberUpdateDto("nickname");

        String requestDtoJson = objectMapper.writeValueAsString(memberUpdateDto);

        MockMultipartFile request = new MockMultipartFile("memberUpdateDto", "jsondata",
                "application/json", requestDtoJson.getBytes(StandardCharsets.UTF_8));

        MockMultipartFile memberProfile = new MockMultipartFile("file", "member_profile.jpeg",
                "image/jpeg", "<<jpeg data>>".getBytes(StandardCharsets.UTF_8));


        MockMultipartHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.fileUpload("/api/member");
        builder.with(new RequestPostProcessor() {
            @Override
            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                request.setMethod("PUT");
                return request;
            }
        });

        mockMvc.perform(
                        builder.file(request).file(memberProfile)
                                .header("Authorization", "Bearer " + accessToken)

                ).andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                requestParts(
                                        partWithName("file").description("회원 프로필 이미지"),
                                        partWithName("memberUpdateDto").description("회원 프로필 수정 DTO")
                                ),
                                requestPartFields("memberUpdateDto",
                                        fieldWithPath("nickName").description("nickname").optional().attributes(field("constraints", "길이 10 이하"))
                                )
                        ));
    }

    @DisplayName(value = "회원 프로필 조회")
    @WithMockCustomUser
    @Test
    public void 회원_프로필_조회() throws Exception {
        MemberProfileDto memberProfileDto = MemberProfileDto.builder()
                .walletAddress("0X1234")
                .nickName("nickName")
                .email("email@naver.com")
                .profileImage("profileImage")
                .memberType(MemberType.USER)
                .build();

        given(memberService.memberDetail(1L)).willReturn(memberProfileDto);

        mockMvc.perform(
                get("/api/member")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @DisplayName(value = "회원 탈퇴")
    @WithMockCustomUser
    @Test
    public void 회원_탈퇴() throws Exception {
        mockMvc.perform(
                delete("/api/member")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @DisplayName(value = "마이페이지 관심 목록")
    @WithMockCustomUser
    @Test
    public void 마이페이지_관심_목록() throws Exception {

        MemberFavStartupDto favStartupDto = MemberFavStartupDto.builder().memberId(1L).startupId(1L).startupName("스타트업A").title("title").
                description("description").nftCount(10).investCount(5)
                .pricePerNft(new Double(2)).endDate(LocalDateTime.now().plusDays(2))
                .businessPlan("businessPlan").businessPlanImg("businessPlanImg").roadMap("roadMap")
                .imageNft("imageNft").isFav(Boolean.TRUE).build();

        MemberFavStartupDto favStartupDto2 = MemberFavStartupDto.builder().memberId(1L).startupId(2L).startupName("스타트업B").title("title").
                description("description").nftCount(10).investCount(5)
                .pricePerNft(new Double(2)).endDate(LocalDateTime.now().plusDays(2))
                .businessPlan("businessPlan").businessPlanImg("businessPlanImg").roadMap("roadMap")
                .imageNft("imageNft").isFav(Boolean.TRUE).build();

        List<MemberFavStartupDto> favStartupDtoList = Arrays.asList(favStartupDto, favStartupDto2);

        given(memberService.findMemberFavStartup(1L)).willReturn(favStartupDtoList);

        mockMvc.perform(
                get("/api/member/mypage/favstartup")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
}