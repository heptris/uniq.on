package com.ssafy.uniqon.service.auth;

import com.ssafy.uniqon.domain.member.Member;
import com.ssafy.uniqon.dto.member.MemberJoinDto;
import com.ssafy.uniqon.exception.ex.CustomException;
import com.ssafy.uniqon.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ssafy.uniqon.exception.ex.ErrorCode.ALREADY_SAVED_MEMBER;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void signup(MemberJoinDto memberJoinDto){
        if(memberRepository.existsByEmail(memberJoinDto.getEmail())){
            throw new CustomException(ALREADY_SAVED_MEMBER);
        }

        Member member = memberJoinDto.toMember(passwordEncoder);
        memberRepository.save(member);
    }

}
