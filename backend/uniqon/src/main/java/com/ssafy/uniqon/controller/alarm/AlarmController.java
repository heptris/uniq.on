package com.ssafy.uniqon.controller.alarm;


import com.ssafy.uniqon.dto.alarm.AlarmRequestDto;
import com.ssafy.uniqon.dto.response.ResponseDto;
import com.ssafy.uniqon.service.alarm.AlarmService;
import com.ssafy.uniqon.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api/alarm")
@RequiredArgsConstructor
public class AlarmController {
    private final AlarmService alarmService;

    @GetMapping("/alarmList")
    public ResponseEntity alarmList() {
        Long memberId = SecurityUtil.getCurrentMemberId();
        return new ResponseEntity<>(new ResponseDto(200, "success",
                alarmService.alarmList(memberId)), HttpStatus.OK);
    }

    @GetMapping("/unReadAlarmList")
    public ResponseEntity unReadAlarmList() {
        Long memberId = SecurityUtil.getCurrentMemberId();
        return new ResponseEntity<>(new ResponseDto(200, "success",
                alarmService.unReadAlarmList(memberId)), HttpStatus.OK);
    }

    /**
     * 투자 실패했을 때의 알람을 읽는 요청
     */
    @PostMapping("/readAlarm/{alarmId}")
    public ResponseEntity readAlarm(@PathVariable("alarmId") Long alarmId) {
        alarmService.readAlarm(alarmId);
        return new ResponseEntity(new ResponseDto(200, "success", "알림 확인 완료"), HttpStatus.OK);
    }

    @PostMapping("/mintSuccess/{alarmId}")
    public ResponseEntity readStartupAlarm(@PathVariable("alarmId") Long alarmId, @RequestBody AlarmRequestDto alarmRequestDto) {
        alarmService.mintSuccess(alarmRequestDto.getLastTokenId(), alarmId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "alarm read", null)
        );
    }

    @GetMapping("/investSuccses/{alarmId}")
    public ResponseEntity readInvestorAlarm(@PathVariable("alarmId") Long alarmId) {
        alarmService.nftPurchase(alarmId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "alarm read", null)
        );
    }

}
