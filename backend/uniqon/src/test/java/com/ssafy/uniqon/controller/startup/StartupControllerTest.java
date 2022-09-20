package com.ssafy.uniqon.controller.startup;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.uniqon.config.RestDocsConfig;
import com.ssafy.uniqon.config.SecurityConfig;
import com.ssafy.uniqon.controller.RestDocsTestSupport;
import com.ssafy.uniqon.controller.WithMockCustomUser;
import com.ssafy.uniqon.domain.startup.Startup;
import com.ssafy.uniqon.dto.startup.StartupDetailResponseDto;
import com.ssafy.uniqon.dto.startup.StartupRequestDto;
import com.ssafy.uniqon.service.startup.StartupService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import static com.ssafy.uniqon.config.RestDocsConfig.field;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StartupController.class, excludeFilters = { //!Added!
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)})
class StartupControllerTest extends RestDocsTestSupport {

    private String accessToken = "accessToken";
    @MockBean
    private StartupService startupService;

    @DisplayName(value = "스타트업 상세정보")
    @WithMockUser
    @Test
    public void 스타트업_상세_정보() throws Exception {

        StartupDetailResponseDto startupDetailResponseDto = StartupDetailResponseDto.builder()
                .description("스타트업1 상세 정보 글입니다.")
                .startupId(1L)
                .startupName("스타트업1")
                .title("스타트업1 제목")
                .nftCount(10)
                .goalRate(0.0)
                .pricePerNft(10.0)
                .endDate(LocalDateTime.now().plusDays(3))
                .businessPlan("https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/4cfd6171-b839-404a-94e9-615e3cc93402uniqon%EB%B8%94%EB%A1%9D%EC%B2%B4%EC%9D%B8_%EC%A1%B0%EC%98%81%EB%8F%84%EB%A9%98%ED%86%A0%EB%8B%98.pdf")
                .businessPlanImg("https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/e95ca4c9-b7c3-4f8a-87da-e718a97c2926uniqonuniqon%EB%B8%94%EB%A1%9D%EC%B2%B4%EC%9D%B8_%EC%A1%B0%EC%98%81%EB%8F%84%EB%A9%98%ED%86%A0%EB%8B%98.jpg")
                .roadMap("https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/2ceda00d-e53f-4b8d-ba17-89fa74e759a1uniqon5dc267300fefd2738de6.jpg")
                .imageNft("https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/671c1f1b-e6f2-4c0b-af82-ada8ffc3ff3buniqon5dc267300fefd2738de6.jpg")
                .build();


        given(startupService.startupDetail(1L)).willReturn(startupDetailResponseDto);

        mockMvc.perform(
                        get("/api/invest/{startupId}", 1L)
                                .header("Authorization", "Bearer " + accessToken)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                pathParameters(
                                        parameterWithName("startupId").description("Startup ID")
                                ),
                                responseFields( // response 필드 정보 입력
                                        fieldWithPath("status").description("status"),
                                        fieldWithPath("message").description("message"),
                                        fieldWithPath("data").description("data"),
                                        fieldWithPath("data.startupId").description("startup ID"),
                                        fieldWithPath("data.startupName").description("startup name"),
                                        fieldWithPath("data.title").description("startup title"),
                                        fieldWithPath("data.description").description("startup description"),
                                        fieldWithPath("data.nftCount").description("startup nftCount"),
                                        fieldWithPath("data.pricePerNft").description("startup pricePerNft"),
                                        fieldWithPath("data.goalRate").description("startup goalRate"),
                                        fieldWithPath("data.pricePerNft").description("startup pricePerNft"),
                                        fieldWithPath("data.endDate").description("startup endDate"),
                                        fieldWithPath("data.businessPlan").description("startup businessPlan"),
                                        fieldWithPath("data.businessPlanImg").description("startup businessPlanImg"),
                                        fieldWithPath("data.roadMap").description("startup roadMap"),
                                        fieldWithPath("data.imageNft").description("startup imageNft")
                                )
                        )
                );
    }

    @DisplayName(value = "스타트업 등록")
    @WithMockCustomUser
    @Test
    public void 스타트업_등록() throws Exception {
        StartupRequestDto startupRequestDto = StartupRequestDto.builder()
                .startupName("스타트업2")
                .managerName("test")
                .managerNumber("010-5138-9823")
                .managerEmail("test@naver.com")
                .goalPrice(new Double(1000))
                .endDate(LocalDateTime.now().plusDays(3))
                .nftCount(10)
                .discordUrl("discordUrl")
                .description("test")
                .title("test")
                .build();
        String requestDtoJson = objectMapper.writeValueAsString(startupRequestDto);
        MockMultipartFile request = new MockMultipartFile("startupRequestDto", "jsondata",
                "application/json", requestDtoJson.getBytes(StandardCharsets.UTF_8));

        MockMultipartFile businessPlan = new MockMultipartFile("business_plan", "business_plan.pdf",
                "application/pdf", "<<pdf file>>".getBytes(StandardCharsets.UTF_8));

        MockMultipartFile nftImage = new MockMultipartFile("nft_image", "nft_image.jpeg",
                "image/jpeg", "<<jpeg data>>".getBytes(StandardCharsets.UTF_8));

        MockMultipartFile roadMap = new MockMultipartFile("road_map", "road_map.jpeg",
                "image/jpeg", "<<jpeg data>>".getBytes(StandardCharsets.UTF_8));

        given(startupService.investRegist(1L, startupRequestDto, businessPlan, nftImage, roadMap))
                .willReturn(1L);
        mockMvc.perform(
                        multipart("/api/invest/regist")
                                .file(request)
                                .file(businessPlan)
                                .file(nftImage)
                                .file(roadMap)
                                .header("Authorization", "Bearer " + accessToken)
                                )
                .andExpect(status().isCreated())
                .andDo(
                        restDocs.document(
                                requestParts(
                                        partWithName("business_plan").description("사업계획서 pdf"),
                                        partWithName("nft_image").description("NFT 이미지"),
                                        partWithName("road_map").description("스타트업 로드맵"),
                                        partWithName("startupRequestDto").description("스타트업 등록 요청 폼")
                                ),
                                requestPartFields("startupRequestDto",
                                        fieldWithPath("startupName").description("startupName").optional().attributes(field("constraints", "길이 10 이하")),
                                        fieldWithPath("managerName").description("managerName").attributes(field("constraints", "길이 10 이하")),
                                        fieldWithPath("managerEmail").description("managerEmail").attributes(field("constraints", "길이 10 이하")),
                                        fieldWithPath("managerNumber").description("managerNumber").attributes(field("constraints", "길이 10 이하")),
                                        fieldWithPath("goalPrice").description("goalPrice").attributes(field("constraints", "길이 10 이하")),
                                        fieldWithPath("endDate").description("endDate").attributes(field("constraints", "길이 10 이하")),
                                        fieldWithPath("nftCount").description("nftCount").attributes(field("constraints", "길이 10 이하")),
                                        fieldWithPath("discordUrl").description("discordUrl").attributes(field("constraints", "길이 10 이하")),
                                        fieldWithPath("description").description("description").attributes(field("constraints", "길이 10 이하")),
                                        fieldWithPath("title").description("title").attributes(field("constraints", "길이 10 이하"))
                                ),
                                responseFields(
                                        fieldWithPath("status").description("status"),
                                        fieldWithPath("message").description("message"),
                                        fieldWithPath("data").description("data")
                                )
                        )
                );

    }
}