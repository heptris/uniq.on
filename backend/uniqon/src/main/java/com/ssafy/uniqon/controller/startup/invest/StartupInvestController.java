package com.ssafy.uniqon.controller.startup.invest;

import com.ssafy.uniqon.dto.response.ResponseDto;
import com.ssafy.uniqon.service.startup.invest.StartupInvestService;
import com.ssafy.uniqon.util.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/app/invest/reserve")
@RestController
public class StartupInvestController {

    private final StartupInvestService startupInvestService;

    @ApiOperation(value = "투자자 스타트업 투자 예약(by 투자자)")
    @GetMapping("/{startupId}")
    public ResponseEntity startupInvestReserve(@PathVariable("startupId") Long startupId) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        startupInvestService.investReserve(memberId, startupId);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto<>(HttpStatus.CREATED.value(), "유저 투자 예약 완료", null)
        );
    }
}
