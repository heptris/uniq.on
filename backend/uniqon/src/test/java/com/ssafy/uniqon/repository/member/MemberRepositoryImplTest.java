package com.ssafy.uniqon.repository.member;

import com.ssafy.uniqon.dto.member.MemberFavStartupDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberRepositoryImplTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 관심목록() throws Exception{
        List<MemberFavStartupDto> favStartup = memberRepository.findFavStartup(6L);
        for (MemberFavStartupDto memberFavStartupDto : favStartup) {
            System.out.println("memberFavStartupDto = " + memberFavStartupDto);
        }
    }

}