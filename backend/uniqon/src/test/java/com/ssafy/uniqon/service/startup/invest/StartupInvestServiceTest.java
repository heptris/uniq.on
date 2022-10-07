package com.ssafy.uniqon.service.startup.invest;

import com.ssafy.uniqon.domain.startup.Startup;
import com.ssafy.uniqon.repository.startup.StartupRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Rollback(value = false)
@Transactional
@SpringBootTest
class StartupInvestServiceTest {

    @Autowired
    private StartupInvestService startupInvestService;

    @Autowired
    private StartupRepository startupRepository;

    @Test
    public void 스타트업투자() {
        startupInvestService.investReserve(1L, 1L);
    }

}