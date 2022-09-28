package com.ssafy.uniqon.controller.startup.invest;

import com.ssafy.uniqon.config.SecurityConfig;
import com.ssafy.uniqon.controller.RestDocsTestSupport;
import com.ssafy.uniqon.controller.WithMockCustomUser;
import com.ssafy.uniqon.controller.startup.qna.StartupAnswerController;
import com.ssafy.uniqon.service.startup.invest.StartupInvestService;
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

@WebMvcTest(controllers = StartupInvestController.class, excludeFilters = { //!Added!
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})
class StartupInvestControllerTest extends RestDocsTestSupport {

    private String accessToken = "accessToken";

    @MockBean
    private StartupInvestService startupInvestService;

    @DisplayName(value = "스타트업 투자 예약(by 투자자)")
    @WithMockCustomUser
    @Test
    public void 투자_예약() throws Exception {

        mockMvc.perform(get("/api/invest/reserve/{startupId}", 1L)
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isCreated())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("startupId").description("startupID"))
                        )
                );
    }

}