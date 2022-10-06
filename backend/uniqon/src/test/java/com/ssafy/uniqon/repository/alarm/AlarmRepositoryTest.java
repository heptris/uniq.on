package com.ssafy.uniqon.repository.alarm;

import com.ssafy.uniqon.domain.alarm.Alarm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AlarmRepositoryTest {

    @Autowired
    private AlarmRepository alarmRepository;

    @Test
    public void test() {
        List<Alarm> allByMemberIdOrderByIdDesc = alarmRepository.findAllByMemberIdOrderByIdDesc(1L);
        allByMemberIdOrderByIdDesc.forEach(
                alarm -> System.out.println(alarm.getId())
        );

    }

}