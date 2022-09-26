package com.ssafy.uniqon.service.alarm;

import com.ssafy.uniqon.domain.alarm.Alarm;
import com.ssafy.uniqon.repository.mypage.AlarmRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@Rollback(value = false)
@SpringBootTest
class AlarmServiceTest {

    @Autowired
    private AlarmService alarmService;

    @Test
    public void mintTest() {
        alarmService.mintSuccess(0, 16L);
    }

    @Test
    public void purchaseTest() {
        alarmService.nftPurchase(20L);
    }
}