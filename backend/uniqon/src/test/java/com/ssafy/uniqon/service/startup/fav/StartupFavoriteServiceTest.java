package com.ssafy.uniqon.service.startup.fav;

import com.ssafy.uniqon.service.startup.StartupService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Rollback(value = false)
@Transactional
@SpringBootTest
class StartupFavoriteServiceTest {

    @Autowired
    private StartupService startupService;

    @Test
    public void 즐겨찾기() {
        startupService.startupFavoriteToggle(1L, 1L);
        startupService.startupFavoriteToggle(1L, 2L);
        startupService.startupFavoriteToggle(2L, 1L);
    }

}