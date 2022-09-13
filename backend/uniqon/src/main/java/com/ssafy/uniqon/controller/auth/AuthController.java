package com.ssafy.uniqon.controller.auth;

import com.ssafy.uniqon.config.jwt.TokenProvider;
import com.ssafy.uniqon.domain.member.Member;
import com.ssafy.uniqon.dto.member.MemberJoinDto;
import com.ssafy.uniqon.dto.member.MemberLoginDto;
import com.ssafy.uniqon.dto.response.ResponseDto;
import com.ssafy.uniqon.dto.token.TokenDto;
import com.ssafy.uniqon.exception.ex.CustomException;
import com.ssafy.uniqon.exception.ex.CustomValidationException;
import com.ssafy.uniqon.repository.member.MemberRepository;
import com.ssafy.uniqon.service.auth.AuthService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.ssafy.uniqon.exception.ex.ErrorCode.MEMBER_EMAIL_NOT_FOUND;
import static com.ssafy.uniqon.exception.ex.ErrorCode.NOT_EQUAL_PASSWORD;

@RestController
@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AuthService authService;
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid MemberJoinDto memberJoinDto, BindingResult bindingResult){

        if (bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }

            throw new CustomValidationException("유효성 검사 실패", errorMap);
        } else {
            if (!memberJoinDto.getPassword().equals(memberJoinDto.getPasswordConfirm())) {
                throw new CustomException(NOT_EQUAL_PASSWORD);
            }
            authService.signup(memberJoinDto);
            return new ResponseEntity<ResponseDto>(new ResponseDto(200, "success", "회원가입 완료"), HttpStatus.OK);
        }
    }

    @Transactional
    public TokenDto login(MemberLoginDto memberLoginDto) throws RuntimeException{

        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = memberLoginDto.toAuthentication();

        Member member = memberRepository.findByEmail(memberLoginDto.getEmail()).orElseThrow(() ->
                new CustomException(MEMBER_EMAIL_NOT_FOUND));

        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        //    authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);
        // 5. 토큰 발급
        return tokenDto;
    }


}
