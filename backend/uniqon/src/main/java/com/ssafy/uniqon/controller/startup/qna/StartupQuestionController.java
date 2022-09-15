package com.ssafy.uniqon.controller.startup.qna;

import com.ssafy.uniqon.domain.startup.qna.StartupQuestion;
import com.ssafy.uniqon.dto.response.CursorResult;
import com.ssafy.uniqon.dto.response.ResponseDto;
import com.ssafy.uniqon.dto.startup.qna.StartupQuestionResDto;
import com.ssafy.uniqon.service.startup.qna.StartupQuestionService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/startup/qna")
@RestController
public class StartupQuestionController {

    private static final int DEFAULT_SIZE = 2;
    private final StartupQuestionService startupQuestionService;

    @ApiOperation(value = "스타트업 qna 조회")
    @GetMapping("/{startupId}")
    public ResponseEntity getStartupQuestion(@PathVariable Long startupId) {
        List<StartupQuestionResDto> startupQuestionResDtoList = startupQuestionService.질문조회(1L, startupId);
        return new ResponseEntity(new ResponseDto(200, "질문 조회 성공", startupQuestionResDtoList),
               HttpStatus.OK);
    }

    @ApiOperation(value = "스타트업 qna 페이징 조회")
    @GetMapping("/{startupId}/page")
    public ResponseEntity getStartupQuestionPage(@PathVariable Long startupId, Long cursorId, Integer size) {
        if(size == null) size = DEFAULT_SIZE;
        CursorResult<StartupQuestionResDto> questionCursorResult = startupQuestionService.질문조회페이징(1L, startupId, cursorId
                , PageRequest.of(0, size));
        return new ResponseEntity(new ResponseDto(200, "질문 조회 성공", questionCursorResult),
                HttpStatus.OK);
    }
}
