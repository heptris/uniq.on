package com.ssafy.uniqon.controller.startup.qna;

import com.ssafy.uniqon.domain.startup.qna.StartupQuestion;
import com.ssafy.uniqon.dto.response.CursorResult;
import com.ssafy.uniqon.dto.response.ResponseDto;
import com.ssafy.uniqon.dto.startup.qna.StartupQuestionReqDto;
import com.ssafy.uniqon.dto.startup.qna.StartupQuestionResDto;
import com.ssafy.uniqon.dto.startup.qna.StartupQuestionUpdateReqDto;
import com.ssafy.uniqon.service.startup.qna.StartupQuestionService;
import com.ssafy.uniqon.util.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/startup/question")
@RestController
public class StartupQuestionController {

    private static final int DEFAULT_SIZE = 2;
    private final StartupQuestionService startupQuestionService;

    @ApiOperation(value = "스타트업 question 조회")
    @GetMapping("/{startupId}")
    public ResponseEntity getStartupQuestion(@PathVariable Long startupId) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        List<StartupQuestionResDto> startupQuestionResDtoList = startupQuestionService.질문조회(memberId, startupId);
        return new ResponseEntity(new ResponseDto(200, "질문 조회 성공", startupQuestionResDtoList),
               HttpStatus.OK);
    }

    @ApiOperation(value = "스타트업 question 페이징 조회")
    @GetMapping("/{startupId}/page")
    public ResponseEntity getStartupQuestionPage(@PathVariable Long startupId, Long cursorId, Integer size) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        if(size == null) size = DEFAULT_SIZE;
        CursorResult<StartupQuestionResDto> questionCursorResult = startupQuestionService.질문조회페이징(memberId, startupId, cursorId
                , PageRequest.of(0, size));
        return new ResponseEntity(new ResponseDto(200, "질문 조회 성공", questionCursorResult),
                HttpStatus.OK);
    }

    @ApiOperation(value = "스타트업 Question 동륵")
    @PostMapping("/{startupId}")
    public ResponseEntity startupQuestionRegist(@PathVariable Long startupId
    , @RequestBody StartupQuestionReqDto startupQuestionReqDto) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        startupQuestionService.질문등록(memberId, startupId, startupQuestionReqDto);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(
                new ResponseDto(200, "question 등록 성공", null)
        );
    }

    @ApiOperation(value = "스타트업 Question 수정")
    @PutMapping("/{startupQuestionId}")
    public ResponseEntity startupQuestionUpdate(@PathVariable Long startupQuestionId, @RequestBody StartupQuestionUpdateReqDto startupQuestionUpdateReqDto) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        startupQuestionService.질문수정(memberId, startupQuestionId, startupQuestionUpdateReqDto);
        return ResponseEntity.status(HttpStatus.OK.value()).body(
                new ResponseDto(200, "question 수정 성공", null)
        );
    }

    @ApiOperation(value = "스타트업 Question 삭제")
    @DeleteMapping("/{startupQuestionId}")
    public ResponseEntity startupQuestionDelete(@PathVariable Long startupQuestionId) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        startupQuestionService.질문삭제(memberId, startupQuestionId);
        return ResponseEntity.status(HttpStatus.OK.value()).body(
                new ResponseDto(200, "question 삭제 성공", null)
        );
    }
}
