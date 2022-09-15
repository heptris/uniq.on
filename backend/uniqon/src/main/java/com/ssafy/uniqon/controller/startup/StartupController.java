package com.ssafy.uniqon.controller.startup;
import com.ssafy.uniqon.dto.response.ResponseDto;
import com.ssafy.uniqon.dto.startup.StartupDetailResponseDto;
import com.ssafy.uniqon.dto.startup.StartupRequestDto;
import com.ssafy.uniqon.dto.startup.StartupResponseListDto;
import com.ssafy.uniqon.dto.startup.StartupSearchCondition;
import com.ssafy.uniqon.service.startup.StartupService;
import com.ssafy.uniqon.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/api/startup")
@RestController
public class StartupController {

    private final StartupService startupService;

    @PostMapping("/regist")
    public ResponseEntity startupRegist(@RequestPart StartupRequestDto startupRequestDto,
                                        @RequestPart(value = "business_plan", required = false) MultipartFile business_plan,
                                        @RequestPart(value = "nft_image", required = false) MultipartFile nft_image,
                                        @RequestPart(value = "project_pdf", required = false) MultipartFile road_map) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        startupService.investRegist(memberId, startupRequestDto, business_plan, nft_image, road_map);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto(HttpStatus.CREATED.value(), "투자 등록 완료", null)
        );
    }

    @GetMapping
    public ResponseEntity startupList(@RequestParam(required = false) String title,
                                      @RequestParam(required = false) String startupName,
                                      @RequestParam Integer size,
                                      @RequestParam Integer page){
        Pageable pageable = Pageable.ofSize(size).withPage(page);
        StartupSearchCondition condition = new StartupSearchCondition(title, startupName);
        Page<StartupResponseListDto> startupList = startupService.startupList(condition, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "스타트업 목록 조회", startupList)
        );
    }

    @GetMapping("/{startupId}")
    public ResponseEntity startupDetail(@PathVariable Long startupId) {
        StartupDetailResponseDto startupDetailResponseDto = startupService.startupDetail(startupId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "스타트업 상세정보", startupDetailResponseDto)
        );
    }
}
