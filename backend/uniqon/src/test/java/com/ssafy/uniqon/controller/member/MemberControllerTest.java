package com.ssafy.uniqon.controller.member;

import com.ssafy.uniqon.config.SecurityConfig;
import com.ssafy.uniqon.controller.RestDocsTestSupport;
import com.ssafy.uniqon.controller.WithMockCustomUser;
import com.ssafy.uniqon.controller.auth.AuthController;
import com.ssafy.uniqon.domain.member.MemberType;
import com.ssafy.uniqon.dto.member.*;
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
                                        fieldWithPath("nickName").description("nickname").attributes(field("constraints", "닉네임은 3~30자여야 합니다."))
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

        MemberFavStartupDto favStartupDto = MemberFavStartupDto.builder().memberId(2L).startupId(1L).
                startupName("스타트업A").title("title").
                description("description").nftTargetCount(10).nftReserveCount(5)
                .nftPrice(new Double(2)).dueDate(LocalDateTime.now().plusDays(2))
                .planPaper("planPaper").planPaperImg("planPaperImg").roadMap("roadMap")
                .nftImage("nftImage").isFav(Boolean.TRUE).build();

        MemberFavStartupDto favStartupDto2 = MemberFavStartupDto.builder().memberId(3L).startupId(2L).
                startupName("스타트업B").title("title2").
                description("description2").nftTargetCount(10).nftReserveCount(5)
                .nftPrice(new Double(2)).dueDate(LocalDateTime.now().plusDays(2))
                .planPaper("planPaper").planPaperImg("planPaperImg").roadMap("roadMap")
                .nftImage("nftImage").isFav(Boolean.TRUE).build();

        List<MemberFavStartupDto> favStartupDtoList = Arrays.asList(favStartupDto, favStartupDto2);

        given(memberService.findMemberFavStartup(1L)).willReturn(favStartupDtoList);

        mockMvc.perform(
                get("/api/member/mypage/favstartup")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @DisplayName(value = "마이페이지(투자자) 내가 투자한 스타트업 목록")
    @WithMockCustomUser
    @Test
    public void 마이페이지_투자자_투자한_스타트업_목록() throws Exception {
        MemberInvestedStartupDto memberInvestedStartupDto = MemberInvestedStartupDto.builder()
                .memberId(1L).startupId(1L).startupName("startupName").title("title").description("description")
                .nftTargetCount(10).nftReserveCount(5).nftPrice(new Double(2)).dueDate(LocalDateTime.now().plusDays(1))
                .planPaper("planPaper").planPaperImg("planPaperImg").roadMap("roadMap").nftImage("nftImage")
                .nftDescription("nftDescription").build();

        MemberInvestedStartupDto memberInvestedStartupDto2 = MemberInvestedStartupDto.builder()
                .memberId(2L).startupId(2L).startupName("startupName").title("title").description("description")
                .nftTargetCount(10).nftReserveCount(5).nftPrice(new Double(2)).dueDate(LocalDateTime.now().plusDays(1))
                .planPaper("planPaper").planPaperImg("planPaperImg").roadMap("roadMap").nftImage("nftImage")
                .nftDescription("nftDescription").build();

        List<MemberInvestedStartupDto> memberInvestedStartupDtoList = Arrays.asList(memberInvestedStartupDto, memberInvestedStartupDto2);

        given(memberService.findInvestedStartup(any(Long.class))).willReturn(memberInvestedStartupDtoList);

        mockMvc.perform(
                get("/api/member/mypage/invest")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

    }

    @DisplayName(value = "마이페이지(스타트업) 투자 신청 목록")
    @WithMockCustomUser
    @Test
    public void 마이페이지_스타트업_투자_신청_목록() throws Exception {
        StartupInvestedListDto startupInvestedListDto = StartupInvestedListDto.builder().memberId(1L).startupId(1L).startupName("startupName").title("title")
                .description("description").nftTargetCount(10).nftReserveCount(5).nftPrice(new Double(2))
                .dueDate(LocalDateTime.now().plusDays(1)).planPaper("planPaper").planPaperImg("planPaperImg")
                .roadMap("roadMap").nftImage("nftImage").nftDescription("nftDescription").build();

        StartupInvestedListDto startupInvestedListDto2 = StartupInvestedListDto.builder().memberId(1L).startupId(2L).startupName("startupName").title("title")
                .description("description").nftTargetCount(10).nftReserveCount(5).nftPrice(new Double(2))
                .dueDate(LocalDateTime.now().plusDays(1)).planPaper("planPaper").planPaperImg("planPaperImg")
                .roadMap("roadMap").nftImage("nftImage").nftDescription("nftDescription").build();

        List<StartupInvestedListDto> startupInvestedListDtoList = Arrays.asList(startupInvestedListDto, startupInvestedListDto2);

        given(memberService.findStartupInvestedList(1L)).willReturn(startupInvestedListDtoList);

        mockMvc.perform(
                get("/api/member/mypage/startup")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
}