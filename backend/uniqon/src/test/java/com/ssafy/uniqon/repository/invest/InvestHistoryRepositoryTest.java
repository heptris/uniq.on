package com.ssafy.uniqon.repository.invest;

import com.ssafy.uniqon.domain.member.Member;
import com.ssafy.uniqon.domain.startup.Startup;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InvestHistoryRepositoryTest {

    @Autowired
    private InvestHistoryRepository investHistoryRepository;

    @Test
    public void test() {
        Member member = new Member();
        member.changeId(1L);

        Startup startup = new Startup();
        startup.changeId(1L);
        System.out.println(investHistoryRepository.existsByMemberAndStartup(member, startup));
    }

}