package com.ssafy.uniqon.service.mypage;

import com.ssafy.uniqon.domain.alarm.Alarm;
import com.ssafy.uniqon.dto.mypage.AlarmDto;
import com.ssafy.uniqon.exception.ex.CustomException;
import com.ssafy.uniqon.exception.ex.ErrorCode;
import com.ssafy.uniqon.repository.mypage.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MypageService {

    private final AlarmRepository alarmRepository;

    @Transactional
    public List<AlarmDto> alarmList(Long memberId){

        List<Alarm> allAlarmList = alarmRepository.findAllByMemberId(memberId);
        List<AlarmDto> alarmDtoList = new ArrayList<>();
        for (Alarm alarm : allAlarmList) {
            alarmDtoList.add(new AlarmDto(alarm.getId(), alarm.getContent(), alarm.getIsRead()));
        }

        return alarmDtoList;
    }

    @Transactional
    public List<AlarmDto> unReadAlarmList(Long memberId){

        List<Alarm> alarmList = alarmRepository.findAllByMemberIdAndIsRead(memberId, false);
        List<AlarmDto> alarmDtoList = new ArrayList<>();
        for (Alarm alarm : alarmList) {
            alarmDtoList.add(new AlarmDto(alarm.getId(), alarm.getContent(), alarm.getIsRead()));
        }

        return alarmDtoList;
    }

    @Transactional
    public void readAlarm(Long alarmId){
        Alarm alarm = alarmRepository.findById(alarmId).orElseThrow(() -> new CustomException(ErrorCode.ALARM_NOT_FOUND));
        alarm.changeIsRead();
    }

}
