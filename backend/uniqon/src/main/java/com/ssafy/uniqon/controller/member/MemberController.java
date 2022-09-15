package com.ssafy.uniqon.controller.member;

import com.ssafy.uniqon.dto.member.MemberUpdateDto;
import com.ssafy.uniqon.dto.response.ResponseDto;
import com.ssafy.uniqon.exception.ex.CustomException;
import com.ssafy.uniqon.exception.ex.CustomValidationException;
import com.ssafy.uniqon.service.member.MemberService;
import com.ssafy.uniqon.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.ssafy.uniqon.exception.ex.ErrorCode.NOT_EQUAL_PASSWORD;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    @PutMapping
    public ResponseEntity memberUpdate(@RequestBody @Valid MemberUpdateDto memberUpdateDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationException("유효성 검사 실패", errorMap);
        } else {
            Long memberId = SecurityUtil.getCurrentMemberId();
            memberService.memberUpdate(memberId, memberUpdateDto);
        }
        return new ResponseEntity(new ResponseDto<>(200,"success","수정 완료 !!"), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity memberDelete(){
        Long memberId = SecurityUtil.getCurrentMemberId();
        memberService.memberDelete(memberId);

        return new ResponseEntity(new ResponseDto<>(200,"success","삭제 완료 !!"), HttpStatus.OK);
    }
}
