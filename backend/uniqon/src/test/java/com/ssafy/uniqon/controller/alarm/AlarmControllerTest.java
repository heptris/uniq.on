package com.ssafy.uniqon.controller.alarm;

import static org.junit.jupiter.api.Assertions.*;

import com.ssafy.uniqon.config.SecurityConfig;
import com.ssafy.uniqon.controller.RestDocsTestSupport;
import com.ssafy.uniqon.controller.WithMockCustomUser;
import com.ssafy.uniqon.controller.startup.community.CommunityCommentController;
import com.ssafy.uniqon.dto.alarm.AlarmDto;
import com.ssafy.uniqon.dto.alarm.AlarmRequestDto;
import com.ssafy.uniqon.dto.startup.community.CommunityCommentRequestDto;
import com.ssafy.uniqon.dto.startup.community.CommunityCommentRequestModifyDto;
import com.ssafy.uniqon.exception.ex.CustomException;
import com.ssafy.uniqon.exception.ex.ErrorCode;
import com.ssafy.uniqon.service.alarm.AlarmService;
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
import static org.mockito.Mockito.doThrow;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = AlarmController.class, excludeFilters = { //!Added!
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})
class AlarmControllerTest extends RestDocsTestSupport {

    private String accessToken = "accessToken";

    @MockBean
    private AlarmService alarmService;

    @DisplayName(value = "전체 알람 리스트 조회")
    @WithMockCustomUser
    @Test
    public void 전체_알람_조회() throws Exception {

        AlarmDto alarmDto = AlarmDto.builder().alarmId(1L).content("알람1").isRead(Boolean.FALSE).investCount(10).build();
        AlarmDto alarmDto2 = AlarmDto.builder().alarmId(2L).content("알람2").isRead(Boolean.FALSE).tokenId(1).build();
        AlarmDto alarmDto3 = AlarmDto.builder().alarmId(3L).content("알람3").isRead(Boolean.FALSE).build();
        AlarmDto alarmDto4 = AlarmDto.builder().alarmId(4L).content("알람4").isRead(Boolean.TRUE).build();

        List<AlarmDto> alarmDtoList = Arrays.asList(alarmDto, alarmDto2, alarmDto3, alarmDto4);
        given(alarmService.alarmList(1L)).willReturn(alarmDtoList);

        mockMvc.perform(
                get("/app/alarm/alarmList")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @DisplayName(value = "안읽은 알람 리스트 조회")
    @WithMockCustomUser
    @Test
    public void 안읽은_알림_조회() throws Exception {
        AlarmDto alarmDto = AlarmDto.builder().alarmId(1L).content("알람1").isRead(Boolean.FALSE).investCount(10).build();
        AlarmDto alarmDto2 = AlarmDto.builder().alarmId(2L).content("알람2").isRead(Boolean.FALSE).tokenId(1).build();
        AlarmDto alarmDto3 = AlarmDto.builder().alarmId(3L).content("알람3").isRead(Boolean.FALSE).build();

        List<AlarmDto> alarmDtoList = Arrays.asList(alarmDto, alarmDto2, alarmDto3);
        given(alarmService.unReadAlarmList(any(Long.class))).willReturn(alarmDtoList);

        mockMvc.perform(
                get("/app/alarm/unReadAlarmList")
                        .header("Authorization", "Bearer " + accessToken)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @DisplayName(value = "알람 읽기")
    @Test
    public void 알람_읽기() throws Exception {
        mockMvc.perform(
                        post("/app/alarm/readAlarm/{alarmId}", 1L)
                                .header("Authorization", "Bearer " + accessToken)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("alarmId").description("alarmID"))
                        ));
    }

    @DisplayName(value = "알람 읽기 실패")
    @Test
    public void 알람_읽기_실패() throws Exception {
        doThrow(new CustomException(ErrorCode.ALARM_NOT_FOUND)).when(alarmService).readAlarm(1L);

        mockMvc.perform(
                        post("/app/alarm/readAlarm/{alarmId}", 1L)
                                .header("Authorization", "Bearer " + accessToken)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().is4xxClientError())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("alarmId").description("alarmID"))
                        ));
    }

    @DisplayName(value = "스타트업이 NFT 민팅 성공후 알람 읽기")
    @Test
    public void 스타트업_NFT_민팅_성공_알람_읽기() throws Exception {
        AlarmRequestDto alarmRequestDto = new AlarmRequestDto(10);

        mockMvc.perform(
                        post("/app/alarm/mintSuccess/{alarmId}", 1L)
                                .header("Authorization", "Bearer " + accessToken)
                                .content(objectMapper.writeValueAsString(alarmRequestDto))
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("alarmId").description("alarmID")),
                                requestFields(
                                        fieldWithPath("lastTokenId").description("마지막으로 발행한 NFT 토큰 ID").attributes(
                                                field("constraints", "")
                                        )
                                )
                        )
                );
    }

    @DisplayName(value = "스타트업이 NFT 민팅 성공후 알람 읽기 실패1")
    @Test
    public void 스타트업_NFT_민팅_성공_알람_읽기_실패1() throws Exception {
        AlarmRequestDto alarmRequestDto = new AlarmRequestDto(10);

        doThrow(new CustomException(ErrorCode.ALARM_NOT_FOUND)).when(alarmService).mintSuccess(10, 1L);

        mockMvc.perform(
                        post("/app/alarm/mintSuccess/{alarmId}", 1L)
                                .header("Authorization", "Bearer " + accessToken)
                                .content(objectMapper.writeValueAsString(alarmRequestDto))
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().is4xxClientError())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("alarmId").description("alarmID")),
                                requestFields(
                                        fieldWithPath("lastTokenId").description("마지막으로 발행한 NFT 토큰 ID").attributes(
                                                field("constraints", "")
                                        )
                                )
                        )
                );
    }

    @DisplayName(value = "투자자가 NFT 구매 후 알람 읽기")
    @Test
    public void 투자자_NFT_구매_성공_알람_읽기() throws Exception {
        mockMvc.perform(
                        post("/app/alarm/investSuccess/{alarmId}", 1L)
                                .header("Authorization", "Bearer " + accessToken)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("alarmId").description("alarmID"))
                        )
                );
    }

    @DisplayName(value = "투자자가 NFT 구매 후 알람 읽기_실패")
    @Test
    public void 투자자_NFT_구매_성공_알람_읽기_실패() throws Exception {

        doThrow(new CustomException(ErrorCode.ALARM_NOT_FOUND)).when(alarmService).nftPurchase(1L);

        mockMvc.perform(
                        post("/app/alarm/investSuccess/{alarmId}", 1L)
                                .header("Authorization", "Bearer " + accessToken)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().is4xxClientError())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("alarmId").description("alarmID"))
                        )
                );
    }

    @DisplayName(value = "투자자가 NFT 구매 실패 후 알람 읽기")
    @Test
    public void 투자자_NFT_구매_실패_알람_읽기() throws Exception {
        mockMvc.perform(
                        post("/app/alarm/investFail/{alarmId}", 1L)
                                .header("Authorization", "Bearer " + accessToken)
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                pathParameters(parameterWithName("alarmId").description("alarmID"))
                        )
                );
    }
}