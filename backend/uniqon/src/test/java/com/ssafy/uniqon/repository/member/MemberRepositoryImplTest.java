package com.ssafy.uniqon.repository.member;

import com.ssafy.uniqon.dto.member.MemberFavStartupDto;
import com.ssafy.uniqon.dto.member.MemberInvestedStartupDto;
import com.ssafy.uniqon.dto.member.StartupInvestedListDto;
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

    @Test
    public void 투자신청한스타트업목록() throws Exception{
        List<MemberInvestedStartupDto> investedStartup = memberRepository.findInvestedStartup(6L);

        for (MemberInvestedStartupDto memberInvestedStartupDto : investedStartup) {
            System.out.println("memberInvestedStartupDto = " + memberInvestedStartupDto);
        }
    }

    @Test
    public void 스타트업이투자신청한목록() throws Exception{
        List<StartupInvestedListDto> startupInvestedList = memberRepository.findStartupInvestedList(6L);

        for (StartupInvestedListDto startupInvestedListDto : startupInvestedList) {
            System.out.println("startupInvestedListDto = " + startupInvestedListDto);
        }
    }

}