package com.ssafy.uniqon.controller.mypage;

import com.ssafy.uniqon.dto.response.ResponseDto;
import com.ssafy.uniqon.service.mypage.MypageService;
import com.ssafy.uniqon.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/mypage")
public class MypageController {

    private final MypageService mypageService;
    @GetMapping("/alarmList")
    public ResponseEntity alarmList(){
        Long memberId = SecurityUtil.getCurrentMemberId();
        return new ResponseEntity<>(new ResponseDto(200, "success",
                mypageService.alarmList(memberId)), HttpStatus.OK);
    }

    @GetMapping("/unReadAlarmList")
    public ResponseEntity unReadAlarmList(){
        Long memberId = SecurityUtil.getCurrentMemberId();
        return new ResponseEntity<>(new ResponseDto(200, "success",
                mypageService.unReadAlarmList(memberId)), HttpStatus.OK);
    }

    @PostMapping("/readAlarm/{alarmId}")
    public ResponseEntity readAlarm(@PathVariable("alarmId") Long alarmId){
        mypageService.readAlarm(alarmId);
        return new ResponseEntity(new ResponseDto(200, "success", "알림 확인 완료"), HttpStatus.OK);
    }

}
