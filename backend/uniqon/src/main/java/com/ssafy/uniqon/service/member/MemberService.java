package com.ssafy.uniqon.service.member;

import com.ssafy.uniqon.domain.member.Member;
import com.ssafy.uniqon.dto.member.MemberUpdateDto;
import com.ssafy.uniqon.exception.ex.CustomException;
import com.ssafy.uniqon.exception.ex.ErrorCode;
import com.ssafy.uniqon.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ssafy.uniqon.exception.ex.ErrorCode.MEMBER_NOT_FOUND;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public boolean existsByNickname(String nickName){
        return memberRepository.existsByNickname(nickName);
    }

    @Transactional
    public void memberUpdate(Long memberId, MemberUpdateDto memberUpdateDto){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

        member.updateMember(passwordEncoder.encode(memberUpdateDto.getPassword()), memberUpdateDto.getNickName());
    }

    @Transactional
    public void memberDelete(Long memberId){
        memberRepository.deleteById(memberId);
    }

}
