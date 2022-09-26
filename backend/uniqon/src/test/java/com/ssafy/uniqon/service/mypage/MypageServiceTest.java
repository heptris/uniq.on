package com.ssafy.uniqon.service.mypage;

import com.ssafy.uniqon.dto.mypage.AlarmDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Rollback(value = false)
@Transactional
@SpringBootTest
class MypageServiceTest {

    @Autowired
    private MypageService mypageService;

    @Test
    public void 알림목록조회() throws Exception{
        //given
        List<AlarmDto> alarmDtoList = mypageService.alarmList(6L);

        //when

        //then
        for (AlarmDto alarmDto : alarmDtoList) {
            System.out.println("alarmDto.getContent() = " + alarmDto.getContent());
            System.out.println("alarmDto.isRead() = " + alarmDto.isRead());
        }
    }

//    @Test
//    public void 안읽은알림목록조회() throws Exception{
//        //given
//        List<AlarmDto> alarmDtoList = mypageService.unReadAlarmList(6L);
//
//        //when
//
//        //then
//        for (AlarmDto alarmDto : alarmDtoList) {
//            System.out.println("alarmDto.getContent() = " + alarmDto.getContent());
//            System.out.println("alarmDto.isRead() = " + alarmDto.isRead());
//        }
//        assertThat(alarmDtoList.get(0).getContent()).isEqualTo("예약한 펀딩이 성공했습니다 !!");
//    }

    @Test
    public void 알림확인() throws Exception{
        //given
        List<AlarmDto> alarmDtoList = mypageService.alarmList(6L);
        boolean check = alarmDtoList.get(2).isRead();
        System.out.println("check = " + check);

        //when
        mypageService.readAlarm(12L);

        List<AlarmDto> alarmDtoList2 = mypageService.alarmList(6L);
        check = alarmDtoList2.get(2).isRead();
        System.out.println("check = " + check);

        //then
        assertThat(check).isTrue();
     }

}