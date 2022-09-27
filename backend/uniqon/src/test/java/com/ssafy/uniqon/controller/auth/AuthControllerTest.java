package com.ssafy.uniqon.controller.auth;

import com.ssafy.uniqon.config.SecurityConfig;
import com.ssafy.uniqon.controller.RestDocsTestSupport;
import com.ssafy.uniqon.controller.alarm.AlarmController;
import com.ssafy.uniqon.domain.member.MemberType;
import com.ssafy.uniqon.dto.member.MemberJoinDto;
import com.ssafy.uniqon.service.auth.AuthService;
import com.ssafy.uniqon.service.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;

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
                .memberType(MemberType.USER)
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
                                        fieldWithPath("walletAddress").description("walletAddress")
                                                .attributes(field("constraints", "길이 100 이하")),
                                        fieldWithPath("email").description("email")
                                                .attributes(field("constraints", "길이 100 이하")),
                                        fieldWithPath("nickName").description("nickName")
                                                .attributes(field("constraints", "길이 100 이하")),
                                        fieldWithPath("memberType").description("memberType")
                                                .attributes(field("constraints", "길이 100 이하"))
                                        )

                        )
                );

    }

}