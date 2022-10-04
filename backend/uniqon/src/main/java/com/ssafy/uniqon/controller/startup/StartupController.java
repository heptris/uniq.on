package com.ssafy.uniqon.controller.startup;

import com.ssafy.uniqon.dto.response.ResponseDto;
import com.ssafy.uniqon.dto.startup.StartupDetailResponseDto;
import com.ssafy.uniqon.dto.startup.StartupRequestDto;
import com.ssafy.uniqon.dto.startup.StartupResponseListDto;
import com.ssafy.uniqon.dto.startup.StartupSearchCondition;
import com.ssafy.uniqon.service.startup.StartupService;
import com.ssafy.uniqon.util.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/app/invest")
@RestController
public class StartupController {

    private final StartupService startupService;

    @ApiOperation(value = "스타트업 투자 등록(by 스타트업)")
    @PostMapping("/regist")
    public ResponseEntity startupRegist(@RequestPart StartupRequestDto startupRequestDto,
                                        @RequestPart(value = "plan_paper", required = false) MultipartFile plan_paper,
                                        @RequestPart(value = "nft_image", required = false) MultipartFile nft_image,
                                        @RequestPart(value = "road_map", required = false) MultipartFile road_map) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        startupService.investRegist(memberId, startupRequestDto, plan_paper, nft_image, road_map);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto(HttpStatus.CREATED.value(), "투자 등록 완료", null)
        );
    }

    @ApiOperation(value = "스타트업 투자 리스트")
    @GetMapping
    public ResponseEntity startupList(@RequestParam(required = false) String title,
                                      @RequestParam(required = false) String startupName,
                                      @RequestParam Integer size,
                                      @RequestParam Integer page) {
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        StartupSearchCondition condition = new StartupSearchCondition(title, startupName);
        Page<StartupResponseListDto> startupList = startupService.startupList(condition, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "스타트업 목록 조회", startupList)
        );
    }

    @ApiOperation(value = "스타트업 상세정보")
    @GetMapping("/{startupId}")
    public ResponseEntity startupDetail(@PathVariable Long startupId) {
        try {
            Long memberId = SecurityUtil.getCurrentMemberId();
            StartupDetailResponseDto startupDetailResponseDto = startupService.startupDetail(memberId, startupId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto(HttpStatus.OK.value(), "스타트업 상세정보", startupDetailResponseDto)
            );
        } catch (NumberFormatException e) {
            StartupDetailResponseDto startupDetailResponseDto = startupService.startupDetail(startupId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto(HttpStatus.OK.value(), "스타트업 상세정보", startupDetailResponseDto)
            );
        }
    }

    /**
     * 관심 목록 등록 / 해제
     */
    @ApiOperation(value = "스타트업 관심목록 등록/해제")
    @GetMapping("/{startupId}/favorite")
    public ResponseEntity startupFavorite(@PathVariable Long startupId) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        startupService.startupFavoriteToggle(memberId, startupId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "스타트업 관심 목록 등록 / 해제", null)
        );
    }
}
