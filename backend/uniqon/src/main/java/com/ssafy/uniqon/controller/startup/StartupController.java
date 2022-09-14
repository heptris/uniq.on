package com.ssafy.uniqon.controller.startup;

import com.ssafy.uniqon.dto.response.ResponseDto;
import com.ssafy.uniqon.dto.startup.StartupRequestDto;
import com.ssafy.uniqon.service.startup.StartupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RequiredArgsConstructor
@RequestMapping("/startup")
@RestController
public class StartupController {

    private final StartupService startupService;

    @PostMapping("/regist")
    public ResponseEntity startupRegist(@RequestPart StartupRequestDto startupRequestDto, @RequestPart("business_plan") MultipartFile business_plan,
                                        @RequestPart("nft_image") MultipartFile nft_image,
                                        @RequestPart("project_pdf") MultipartFile road_map) {
        Long memberId = 5L;
        startupService.investRegist(memberId, startupRequestDto, business_plan, nft_image, project_pdf);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto(HttpStatus.CREATED.value(), "투자 등록 완료", null)
        );
    }
}
