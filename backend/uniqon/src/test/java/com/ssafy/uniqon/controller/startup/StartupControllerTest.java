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
import com.ssafy.uniqon.dto.startup.StartupResponseListDto;
import com.ssafy.uniqon.dto.startup.StartupSearchCondition;
import com.ssafy.uniqon.dto.startup.community.StartupCommunityResponseListDto;
import com.ssafy.uniqon.exception.ex.CustomException;
import com.ssafy.uniqon.exception.ex.ErrorCode;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.ssafy.uniqon.config.RestDocsConfig.field;
import static org.mockito.ArgumentMatchers.anyLong;
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

    @DisplayName(value = "???????????? ????????????")
    @WithMockCustomUser
    @Test
    public void ????????????_??????_??????() throws Exception {

        StartupDetailResponseDto startupDetailResponseDto = StartupDetailResponseDto.builder()
                .description("????????????1 ?????? ?????? ????????????.")
                .startupId(1L)
                .startupName("????????????1")
                .profileImage("profileImage")
                .title("????????????1 ??????")
                .nftReserveCount(5)
                .nftTargetCount(10)
                .nftPrice(new Double(2))
                .nftDescription("nft description")
                .isFav(Boolean.FALSE)
                .dueDate(LocalDateTime.now().plusDays(3))
                .isReserved(Boolean.TRUE)
                .planPaper("https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/4cfd6171-b839-404a-94e9-615e3cc93402uniqon%EB%B8%94%EB%A1%9D%EC%B2%B4%EC%9D%B8_%EC%A1%B0%EC%98%81%EB%8F%84%EB%A9%98%ED%86%A0%EB%8B%98.pdf")
                .planPaperImg("https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/e95ca4c9-b7c3-4f8a-87da-e718a97c2926uniqonuniqon%EB%B8%94%EB%A1%9D%EC%B2%B4%EC%9D%B8_%EC%A1%B0%EC%98%81%EB%8F%84%EB%A9%98%ED%86%A0%EB%8B%98.jpg")
                .roadMap("https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/2ceda00d-e53f-4b8d-ba17-89fa74e759a1uniqon5dc267300fefd2738de6.jpg")
                .nftImage("https://uniqon-bucket.s3.ap-northeast-2.amazonaws.com/startup/671c1f1b-e6f2-4c0b-af82-ada8ffc3ff3buniqon5dc267300fefd2738de6.jpg")
                .build();

        given(startupService.startupDetail(1L, 1L)).willReturn(startupDetailResponseDto);

        mockMvc.perform(
                        get("/app/invest/{startupId}", 1L)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                pathParameters(
                                        parameterWithName("startupId").description("Startup ID")
                                )
//                                responseFields( // response ?????? ?????? ??????
//                                        fieldWithPath("status").description("status"),
//                                        fieldWithPath("message").description("message"),
//                                        fieldWithPath("data").description("data"),
//                                        fieldWithPath("data.startupId").description("startup ID"),
//                                        fieldWithPath("data.startupName").description("startup name"),
//                                        fieldWithPath("data.title").description("startup title"),
//                                        fieldWithPath("data.description").description("startup description"),
//                                        fieldWithPath("data.nftCount").description("startup nftCount"),
//                                        fieldWithPath("data.pricePerNft").description("startup pricePerNft"),
//                                        fieldWithPath("data.goalRate").description("startup goalRate"),
//                                        fieldWithPath("data.isFav").description("isFav"),
//                                        fieldWithPath("data.pricePerNft").description("startup pricePerNft"),
//                                        fieldWithPath("data.endDate").description("startup endDate"),
//                                        fieldWithPath("data.businessPlan").description("startup businessPlan"),
//                                        fieldWithPath("data.businessPlanImg").description("startup businessPlanImg"),
//                                        fieldWithPath("data.roadMap").description("startup roadMap"),
//                                        fieldWithPath("data.imageNft").description("startup imageNft")
//                                )
                        )
                );
    }

    @DisplayName(value = "???????????? ??????")
    @WithMockCustomUser
    @Test
    public void ????????????_??????() throws Exception {
        StartupRequestDto startupRequestDto = StartupRequestDto.builder()
                .dueDate(LocalDateTime.now().plusDays(3))
                .discordUrl("discordUrl")
                .description("description")
                .title("title")
                .nftDescription("nft description")
                .nftTargetCount(10)
                .tokenURI("tokenURI")
                .nftPrice(new Double(2))
                .build();
        String requestDtoJson = objectMapper.writeValueAsString(startupRequestDto);
        MockMultipartFile request = new MockMultipartFile("startupRequestDto", "jsondata",
                "application/json", requestDtoJson.getBytes(StandardCharsets.UTF_8));

        MockMultipartFile planPaper = new MockMultipartFile("plan_paper", "business_plan.pdf",
                "application/pdf", "<<pdf file>>".getBytes(StandardCharsets.UTF_8));

        MockMultipartFile nftImage = new MockMultipartFile("nft_image", "nft_image.jpeg",
                "image/jpeg", "<<jpeg data>>".getBytes(StandardCharsets.UTF_8));

        MockMultipartFile roadMap = new MockMultipartFile("road_map", "road_map.jpeg",
                "image/jpeg", "<<jpeg data>>".getBytes(StandardCharsets.UTF_8));

        given(startupService.investRegist(1L, startupRequestDto, planPaper, nftImage, roadMap))
                .willReturn(1L);
        mockMvc.perform(
                        multipart("/app/invest/regist")
                                .file(request)
                                .file(planPaper)
                                .file(nftImage)
                                .file(roadMap)
                                .header("Authorization", "Bearer " + accessToken)
                )
                .andExpect(status().isCreated())
                .andDo(
                        restDocs.document(
                                requestParts(
                                        partWithName("plan_paper").description("??????????????? pdf"),
                                        partWithName("nft_image").description("NFT ?????????"),
                                        partWithName("road_map").description("???????????? ?????????"),
                                        partWithName("startupRequestDto").description("???????????? ?????? ?????? ???")
                                ),
                                requestPartFields("startupRequestDto",
                                        fieldWithPath("dueDate").description("?????? ?????? ??????").attributes(field("constraints", "yyyy-MM-dd HH:MM")),
                                        fieldWithPath("discordUrl").description("???????????? ???????????? ??????").attributes(field("constraints", "")),
                                        fieldWithPath("description").description("????????? ?????????").attributes(field("constraints", "?????? 50??? ??????")),
                                        fieldWithPath("title").description("??????").attributes(field("constraints", "?????? 30??? ??????")),
                                        fieldWithPath("nftTargetCount").description("?????? ?????? ??????").attributes(field("constraints", "")),
                                        fieldWithPath("nftPrice").description("NFT 1?????? ??????").attributes(field("constraints", "")),
                                        fieldWithPath("nftDescription").description("NFT ????????? ??????").attributes(field("constraints", "?????? 50??? ??????")),
                                        fieldWithPath("tokenURI").description("tokenURI").attributes(field("constraints", ""))
                                )
//                                responseFields(
//                                        fieldWithPath("status").description("status"),
//                                        fieldWithPath("message").description("message"),
//                                        fieldWithPath("data").description("data")
//                                )
                        )
                );
    }

    @DisplayName(value = "???????????? ?????? ??????(?????? ????????????)")
    @WithMockCustomUser
    @Test
    public void ????????????_??????_??????_??????????????????() throws Exception {
        StartupRequestDto startupRequestDto = StartupRequestDto.builder()
                .dueDate(LocalDateTime.now().plusDays(3))
                .discordUrl("discordUrl")
                .description("description")
                .title("title")
                .nftDescription("nft description")
                .nftTargetCount(10)
                .tokenURI("tokenURI")
                .nftPrice(new Double(2))
                .build();
        String requestDtoJson = objectMapper.writeValueAsString(startupRequestDto);
        MockMultipartFile request = new MockMultipartFile("startupRequestDto", "jsondata",
                "application/json", requestDtoJson.getBytes(StandardCharsets.UTF_8));

        MockMultipartFile planPaper = new MockMultipartFile("plan_paper", "business_plan.pdf",
                "application/pdf", "<<pdf file>>".getBytes(StandardCharsets.UTF_8));

        MockMultipartFile nftImage = new MockMultipartFile("nft_image", "nft_image.jpeg",
                "image/jpeg", "<<jpeg data>>".getBytes(StandardCharsets.UTF_8));

        MockMultipartFile roadMap = new MockMultipartFile("road_map", "road_map.jpeg",
                "image/jpeg", "<<jpeg data>>".getBytes(StandardCharsets.UTF_8));

        given(startupService.investRegist(anyLong(), any(StartupRequestDto.class), any(MultipartFile.class)
                , any(MultipartFile.class), any(MultipartFile.class))).willThrow(
                new CustomException(ErrorCode.FILE_UPLOAD_ERROR)
        );

        mockMvc.perform(
                        multipart("/app/invest/regist")
                                .file(request)
                                .file(planPaper)
                                .file(nftImage)
                                .file(roadMap)
                                .header("Authorization", "Bearer " + accessToken)
                )
                .andExpect(status().is4xxClientError())
                .andDo(
                        restDocs.document(
                                requestParts(
                                        partWithName("plan_paper").description("??????????????? pdf"),
                                        partWithName("nft_image").description("NFT ?????????"),
                                        partWithName("road_map").description("???????????? ?????????"),
                                        partWithName("startupRequestDto").description("???????????? ?????? ?????? ???")
                                ),
                                requestPartFields("startupRequestDto",
                                        fieldWithPath("dueDate").description("?????? ?????? ??????").attributes(field("constraints", "yyyy-MM-dd HH:MM")),
                                        fieldWithPath("discordUrl").description("???????????? ???????????? ??????").attributes(field("constraints", "")),
                                        fieldWithPath("description").description("????????? ?????????").attributes(field("constraints", "?????? 50??? ??????")),
                                        fieldWithPath("title").description("??????").attributes(field("constraints", "?????? 30??? ??????")),
                                        fieldWithPath("nftTargetCount").description("?????? ?????? ??????").attributes(field("constraints", "")),
                                        fieldWithPath("nftPrice").description("NFT 1?????? ??????").attributes(field("constraints", "")),
                                        fieldWithPath("nftDescription").description("NFT ????????? ??????").attributes(field("constraints", "?????? 50??? ??????")),
                                        fieldWithPath("tokenURI").description("tokenURI").attributes(field("constraints", ""))
                                )
//                                responseFields(
//                                        fieldWithPath("status").description("status"),
//                                        fieldWithPath("message").description("message"),
//                                        fieldWithPath("data").description("data")
//                                )
                        )
                );

    }

    @DisplayName(value = "???????????? ???????????? ?????? / ??????")
    @WithMockCustomUser
    @Test
    public void ????????????_????????????_??????_??????() throws Exception {

        mockMvc.perform(
                        get("/app/invest/{startupId}/favorite", 1L)
                                .header("Authorization", "Bearer " + accessToken)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(
                        restDocs.document(
                                pathParameters(
                                        parameterWithName("startupId").description("Startup ID")
                                )
                        ));
    }

    @DisplayName(value = "???????????? ?????????")
    @Test
    public void ????????????_?????????() throws Exception {

        Pageable pageable = Pageable.ofSize(10).withPage(0);
        StartupSearchCondition condition = new StartupSearchCondition("title", "startupName");
        StartupResponseListDto startupResponseListDto = new StartupResponseListDto(1L, "startupName1", "title1",
                LocalDateTime.now().plusDays(2), 10, 5, "profileImage", "nftImage");
        StartupResponseListDto startupResponseListDto2 = new StartupResponseListDto(2L, "startupName2", "title2", LocalDateTime.now().plusDays(2),
                10, 5, "profileImage", "nftImage");
        List<StartupResponseListDto> startupResponseListDtos = Arrays.asList(startupResponseListDto, startupResponseListDto2);
        PageImpl page = new PageImpl(startupResponseListDtos, pageable, 2);
        given(startupService.startupList(condition, pageable)).willReturn(page);

        mockMvc.perform(
                        get("/app/invest?size=10&page=0&startupName=startupName&title=title")
                                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk())
                .andDo(restDocs.document(
                        requestParameters(
                                parameterWithName("title").description("??????"),
                                parameterWithName("startupName").description("???????????? ??????"),
                                parameterWithName("size").description("1 ???????????? ???????????? ????????? ??????"),
                                parameterWithName("page").description("Page ??????(0?????? ??????)")
                        )
                ));
    }
}