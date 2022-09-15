package com.ssafy.uniqon.controller.startup.qna;

import com.ssafy.uniqon.dto.response.ResponseDto;
import com.ssafy.uniqon.dto.startup.qna.AnswerRequestDto;
import com.ssafy.uniqon.dto.startup.qna.AnswerUpdateRequestDto;
import com.ssafy.uniqon.dto.startup.qna.StartupQuestionUpdateReqDto;
import com.ssafy.uniqon.service.startup.qna.StartupAnswerService;
import com.ssafy.uniqon.util.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/invest/answer")
@RestController
public class StartupAnswerController {

    private final StartupAnswerService startupAnswerService;

    @ApiOperation(value = "스타트업 answer 등록")
    @PostMapping("/{startupQuestionId}")
    public ResponseEntity startupAnswerRegist(@PathVariable Long startupQuestionId,
                                              @RequestBody AnswerRequestDto answerRequestDto) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        startupAnswerService.답변저장(memberId, startupQuestionId, answerRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(
                new ResponseDto(200, "answer 성공", null)
        );
    }

    @ApiOperation(value = "스타트업 answer 수정")
    @PutMapping("/{startupAnswerId}")
    public ResponseEntity startupAnswerUpdate(@PathVariable Long startupAnswerId, @RequestBody AnswerUpdateRequestDto answerUpdateRequestDto) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        startupAnswerService.답변수정(memberId, startupAnswerId, answerUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK.value()).body(
                new ResponseDto(200, "answer 수정 성공", null)
        );
    }

    @ApiOperation(value = "스타트업 answer 삭제")
    @DeleteMapping("/{startupAnswerId}")
    public ResponseEntity startupAnswerDelete(@PathVariable Long startupAnswerId) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        startupAnswerService.답변삭제(memberId, startupAnswerId);
        return ResponseEntity.status(HttpStatus.OK.value()).body(
                new ResponseDto(200, "answer 삭제 성공", null)
        );
    }
}
