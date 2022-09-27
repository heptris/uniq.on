package com.ssafy.uniqon.controller.auth;

import com.ssafy.uniqon.config.jwt.TokenProvider;
import com.ssafy.uniqon.domain.member.Member;
import com.ssafy.uniqon.dto.auth.AuthLoginDto;
import com.ssafy.uniqon.dto.member.MemberJoinDto;
import com.ssafy.uniqon.dto.member.MemberLoginDto;
import com.ssafy.uniqon.dto.response.ResponseDto;
import com.ssafy.uniqon.dto.token.TokenDto;
import com.ssafy.uniqon.dto.token.TokenRequestDto;
import com.ssafy.uniqon.exception.ex.CustomException;
import com.ssafy.uniqon.exception.ex.CustomValidationException;
import com.ssafy.uniqon.repository.member.MemberRepository;
import com.ssafy.uniqon.service.auth.AuthService;
import com.ssafy.uniqon.service.member.MemberService;
import com.ssafy.uniqon.util.SecurityUtil;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.ssafy.uniqon.exception.ex.ErrorCode.*;

@RestController
@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {


    private final AuthService authService;
    private final MemberService memberService;

    @ApiOperation(value = "회원가입", notes = "회원가입 성공 시 이메일을 반환해줍니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원가입 성공"),
            @ApiResponse(code = 400, message = "잘못된 접근"),
            @ApiResponse(code = 409, message = "이미 존재하는 지갑주소"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid MemberJoinDto memberJoinDto, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            throw new CustomValidationException("유효성 검사 실패", errorMap);
        } else {
            authService.signup(memberJoinDto);
            return new ResponseEntity<ResponseDto>(new ResponseDto(200, "success", "회원가입 완료"), HttpStatus.OK);
        }
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody MemberLoginDto memberLoginDto) {
//        TokenDto token = authService.login(memberLoginDto);
//        return new ResponseEntity<ResponseDto>(new ResponseDto<>(200, "로그인 성공",
//                token), HttpStatus.OK);
//    }

    @ApiOperation(value = "로그인", notes = "로그인 성공 시 accessToken과 refreshToken을 반환해줍니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그인 성공"),
            @ApiResponse(code = 404, message = "존재하지 않은 지갑주소입니다."),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @PostMapping("/login")
    public ResponseEntity<?> metaMaskLogin(@RequestBody AuthLoginDto authLoginDto){
        TokenDto token = authService.metaMasklogin(authLoginDto.getUserAccount());
        return new ResponseEntity<ResponseDto>(new ResponseDto(200, "로그인 성공", token), HttpStatus.OK);
    }

    @ApiOperation(value = "토큰 재발행", notes = "accessToken 만료 시 재발행 용도입니다.")
    @PostMapping("/reissue")
    public ResponseEntity<?> reissue(@RequestBody TokenRequestDto tokenRequestDto){
        return new ResponseEntity<ResponseDto>(new ResponseDto<>(200, "토큰 재발행",
                authService.reissue(tokenRequestDto)), HttpStatus.OK);
    }

    @ApiOperation(value = "로그아웃", notes = "로그아웃 시 accessToken과 refreshToken은 더이상 사용할 수 없습니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그아웃 성공"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    @GetMapping("/logout")
    public ResponseEntity logout(HttpServletRequest request){
        String accessToken = request.getHeader("Authorization").substring(7);
        authService.logout(accessToken);
        return new ResponseEntity<>(new ResponseDto<>(200, "로그아웃 완료", null), HttpStatus.OK);
    }

    @ApiOperation(value = "닉네임 중복 검사", notes = "닉네임 중복 시 에러 발생")
    @ApiResponses({
            @ApiResponse(code = 200, message = "닉네임 중복 X"),
            @ApiResponse(code = 409, message = "닉네임 중복")
    })
    @GetMapping("/{nickname}/check")
    public ResponseEntity checkNickName(@PathVariable("nickname") String nickName){
        if(memberService.existsByNickname(nickName)){
            throw new CustomException(ALREADY_USED_NICKNAME);
        }else{
            return new ResponseEntity<>(new ResponseDto<String>(200,"success","사용가능한 닉네임입니다"), HttpStatus.OK);
        }
    }

}
