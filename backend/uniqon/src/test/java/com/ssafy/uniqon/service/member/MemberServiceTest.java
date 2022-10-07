package com.ssafy.uniqon.service.member;

import com.ssafy.uniqon.dto.member.MemberOwnNftDto;
import com.ssafy.uniqon.repository.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    public void 파싱확인() throws Exception{
        List<MemberOwnNftDto> memberOwnNftList = memberService.findMemberOwnNftList(6L);
        for (MemberOwnNftDto memberOwnNftDto : memberOwnNftList) {
            System.out.println("memberOwnNftDto = " + memberOwnNftDto);
        }
    }

}