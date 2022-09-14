package com.ssafy.uniqon.service.startup;

import com.ssafy.uniqon.dto.startup.StartupRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@Rollback(value = false)
@Transactional
@SpringBootTest
class StartupServiceTest {

    @Autowired
    private StartupService startupService;

    @Test
    public void 투자등록() {
        StartupRequestDto startupRequestDto = StartupRequestDto.builder()
                .description("스타트업 투자 소개글")
                .discordUrl("스타트업 test discord url")
                .endDate(LocalDateTime.now().plusDays(2))
                .managerEmail("test@naver.com")
                .managerNumber("010-1234-5678")
                .managerName("test")
                .startupName("startupTest")
                .goalPrice(1000)
                .nftCount(20)
                .title("스타트업 test title")
                .build();

    }


}