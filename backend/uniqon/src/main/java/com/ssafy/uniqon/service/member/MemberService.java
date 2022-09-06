package com.ssafy.uniqon.service.member;

import com.ssafy.uniqon.domain.Member.Member;
import com.ssafy.uniqon.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long 회원가입(Member member) {
        memberRepository.save(member);
        return member.getId();
    }
}
