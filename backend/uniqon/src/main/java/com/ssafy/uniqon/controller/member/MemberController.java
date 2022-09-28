package com.ssafy.uniqon.controller.member;

import com.ssafy.uniqon.dto.member.MemberUpdateDto;
import com.ssafy.uniqon.dto.response.ResponseDto;
import com.ssafy.uniqon.exception.ex.CustomException;
import com.ssafy.uniqon.exception.ex.CustomValidationException;
import com.ssafy.uniqon.service.member.MemberService;
import com.ssafy.uniqon.util.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @ApiOperation(value = "회원 프로필 수정")
    @PutMapping
    public ResponseEntity profileEdit(@RequestPart(value = "file", required = false) MultipartFile multipartFile
            , @Valid @RequestPart MemberUpdateDto memberUpdateDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            throw new CustomValidationException("유효성 검사 실패", errorMap);
        } else {
            Long memberId = SecurityUtil.getCurrentMemberId();
            memberService.memberUpdate(memberId, memberUpdateDto, multipartFile);
        }
        return new ResponseEntity(new ResponseDto<>(200,"success","수정 완료 !!"), HttpStatus.OK);
    }

    @ApiOperation(value = "회원 프로필 조회", notes = "회원 프로필을 반환해줍니다.")
    @GetMapping
    public ResponseEntity memberProfile(){
        Long memberId = SecurityUtil.getCurrentMemberId();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "멤버 프로필 조회", memberService.memberDetail(memberId))
        );
    }

    @ApiOperation(value = "회원 삭제")
    @DeleteMapping
    public ResponseEntity memberDelete(){
        Long memberId = SecurityUtil.getCurrentMemberId();
        memberService.memberDelete(memberId);

        return new ResponseEntity(new ResponseDto<>(200,"success","삭제 완료 !!"), HttpStatus.OK);
    }

    @ApiOperation(value = "마이페이지 관심 목록")
    @GetMapping("/mypage/favstartup")
    public ResponseEntity findMemberFavStartup(){
        Long memberId = SecurityUtil.getCurrentMemberId();

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "관심 목록", memberService.findMemberFavStartup(memberId))
        );
    }

    @ApiOperation(value = "마이페이지(투자자) 내가 투자한 스타트업 목록")
    @GetMapping("/mypage/invest")
    public ResponseEntity findInvestedStartup(){
        Long memberId = SecurityUtil.getCurrentMemberId();

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "투자한 스타트업 목록", memberService.findInvestedStartup(memberId))
        );
    }

    @ApiOperation(value = "마이페이지(스타트업) 내가 투자 신청한 내역")
    @GetMapping("/mypage/startup")
    public ResponseEntity findStartupInvestedList(){
        Long memberId = SecurityUtil.getCurrentMemberId();

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto(HttpStatus.OK.value(), "스타트업이 투자 신청한 내역", memberService.findStartupInvestedList(memberId))
        );
    }

}
