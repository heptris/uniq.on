package com.ssafy.uniqon.controller.auth;

import com.ssafy.uniqon.config.SecurityConfig;
import com.ssafy.uniqon.controller.RestDocsTestSupport;
import com.ssafy.uniqon.controller.WithMockCustomUser;
import com.ssafy.uniqon.controller.alarm.AlarmController;
import com.ssafy.uniqon.domain.member.MemberType;
import com.ssafy.uniqon.dto.auth.AuthLoginDto;
import com.ssafy.uniqon.dto.member.MemberJoinDto;
import com.ssafy.uniqon.dto.token.TokenDto;
import com.ssafy.uniqon.dto.token.TokenRequestDto;
import com.ssafy.uniqon.exception.ex.CustomException;
import com.ssafy.uniqon.exception.ex.ErrorCode;
import com.ssafy.uniqon.service.auth.AuthService;
import com.ssafy.uniqon.service.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;

import javax.persistence.SecondaryTable;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.ssafy.uniqon.config.RestDocsConfig.field;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AuthController.class, excludeFilters = { //!Added!
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})
class AuthControllerTest extends RestDocsTestSupport {

    private String accessToken = "accessToken";

    @MockBean
    private AuthService authService;

    @MockBean
    private MemberService memberService;

    @DisplayName(value = "회원가입")
    @Test
    public void 회원가입() throws Exception {
        MemberJoinDto memberJoinDto = MemberJoinDto.builder()
                .walletAddress("0X1234")
                .email("test@naver.com")
                .nickName("nickName")
                .build();

        mockMvc.perform(
                        post("/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(memberJoinDto))
                ).andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                requestFields(
                                        fieldWithPath("walletAddress").description("지갑 주소")
                                                .attributes(field("constraints", "")),
                                        fieldWithPath("email").description("이메일")
                                                .attributes(field("constraints", "")),
                                        fieldWithPath("nickName").description("닉네임")
                                                .attributes(field("constraints", "닉네임은 3~30자리입니다."))
                                )

                        )
                );

    }

    @DisplayName(value = "회원 가입 실패")
    @Test
    public void 회원가입_실패() throws Exception {
        MemberJoinDto memberJoinDto = MemberJoinDto.builder()
                .walletAddress("0X1234")
                .email("test@naver.com")
                .nickName("nickName")
                .build();

        doThrow(new CustomException(ErrorCode.ALREADY_SAVED_MEMBER))
                .when(authService).signup(memberJoinDto);

        mockMvc.perform(
                        post("/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(memberJoinDto))
                ).andExpect(status().isConflict())
                .andDo(
                        restDocs.document(
                                requestFields(
                                        fieldWithPath("walletAddress").description("지갑 주소")
                                                .attributes(field("constraints", "")),
                                        fieldWithPath("email").description("이메일")
                                                .attributes(field("constraints", "")),
                                        fieldWithPath("nickName").description("닉네임")
                                                .attributes(field("constraints", "닉네임은 3~30자리입니다."))
                                )

                        )
                );
    }

    @DisplayName(value = "로그인")
    @Test
    public void 로그인() throws Exception {
        AuthLoginDto authLoginDto = new AuthLoginDto("0X1234");
        TokenDto tokenDto = TokenDto.builder().accessToken("accessToken").refreshToken("refreshToken").grantType("bearer").accessTokenExpiresIn(16124L)
                .build();

        given(authService.metaMasklogin(authLoginDto.getWalletAddress())).willReturn(tokenDto);

        mockMvc.perform(
                        post("/auth/login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(authLoginDto))
                ).andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                requestFields(
                                        fieldWithPath("walletAddress").description("지갑 주소").attributes(
                                                field("constraints", "")
                                        )
                                )
                        )
                );
    }

    @DisplayName(value = "토큰 재발행")
    @Test
    public void 토큰_재발행() throws Exception {
        TokenRequestDto tokenRequestDto = new TokenRequestDto("accessToken", "refreshToken");
        TokenDto tokenDto = TokenDto.builder()
                .accessToken("accessToken").refreshToken("refreshToken").grantType("bearer")
                .accessTokenExpiresIn(1124L).build();

        given(authService.reissue(any(TokenRequestDto.class))).willReturn(tokenDto);

        mockMvc.perform(
                        post("/auth/reissue")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(tokenRequestDto))
                ).andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                requestFields(
                                        fieldWithPath("accessToken").description("accessToken").attributes(
                                                field("constraints", "")
                                        ),
                                        fieldWithPath("refreshToken").description("refreshToken").attributes(
                                                field("constraints", "")
                                        )
                                )
                        )
                );
    }

    @DisplayName(value = "토큰 재발행 실패")
    @Test
    public void 토큰_재발행_실패() throws Exception {
        TokenRequestDto tokenRequestDto = new TokenRequestDto("accessToken", "refreshToken");
        TokenDto tokenDto = TokenDto.builder()
                .accessToken("accessToken").refreshToken("refreshToken").grantType("bearer")
                .accessTokenExpiresIn(1124L).build();

        given(authService.reissue(any(TokenRequestDto.class))).willThrow(
                new CustomException(ErrorCode.INVALID_REFRESH_TOKEN)
        );

        mockMvc.perform(
                        post("/auth/reissue")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(tokenRequestDto))
                ).andExpect(status().is4xxClientError())
                .andDo(
                        restDocs.document(
                                requestFields(
                                        fieldWithPath("accessToken").description("accessToken").attributes(
                                                field("constraints", "")
                                        ),
                                        fieldWithPath("refreshToken").description("refreshToken").attributes(
                                                field("constraints", "")
                                        )
                                )
                        )
                );
    }

    @DisplayName(value = "로그아웃")
    @Test
    public void 로그아웃() throws Exception {
        mockMvc.perform(
                get("/auth/logout")
                        .header("Authorization", "Bearer " + accessToken)

        ).andExpect(status().isOk());
    }

    @DisplayName(value = "닉네임 중복 검사")
    @Test
    public void 닉네임_중복_검사() throws Exception {
        mockMvc.perform(
                        get("/auth/{nickname}/check", "nickname")

                ).andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("nickname").description("닉네임"))
                        )
                );
    }

    @DisplayName(value = "닉네임 중복 검사 실패")
    @Test
    public void 닉네임_중복_검사_실패() throws Exception {

        given(memberService.existsByNickname("nickname")).willThrow(
                new CustomException(ErrorCode.ALREADY_USED_NICKNAME)
        );

        mockMvc.perform(
                        get("/auth/{nickname}/check", "nickname")

                ).andExpect(status().isConflict())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("nickname").description("닉네임"))
                        )
                );
    }
}