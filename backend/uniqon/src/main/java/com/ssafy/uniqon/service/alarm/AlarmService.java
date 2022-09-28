package com.ssafy.uniqon.service.alarm;

import com.ssafy.uniqon.domain.alarm.Alarm;
import com.ssafy.uniqon.domain.invest.InvestStatus;
import com.ssafy.uniqon.domain.invest.Invest_history;
import com.ssafy.uniqon.domain.member.Member;
import com.ssafy.uniqon.domain.startup.Startup;
import com.ssafy.uniqon.dto.alarm.AlarmDto;
import com.ssafy.uniqon.exception.ex.CustomException;
import com.ssafy.uniqon.exception.ex.ErrorCode;
import com.ssafy.uniqon.repository.invest.InvestHistoryRepository;
import com.ssafy.uniqon.repository.alarm.AlarmRepository;
import com.ssafy.uniqon.repository.startup.StartupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AlarmService {

    private final AlarmRepository alarmRepository;
    private final InvestHistoryRepository investHistoryRepository;
    private final StartupRepository startupRepository;

    @Transactional
    public List<AlarmDto> alarmList(Long memberId){

        List<Alarm> allAlarmList = alarmRepository.findAllByMemberId(memberId);
        List<AlarmDto> alarmDtoList = new ArrayList<>();
        for (Alarm alarm : allAlarmList) {
            alarmDtoList.add(new AlarmDto(alarm.getId(), alarm.getContent(), alarm.getIsRead(),alarm.getTokenId(), alarm.getInvestCount()));
        }

        return alarmDtoList;
    }
    @Transactional
    public List<AlarmDto> unReadAlarmList(Long memberId){

        List<Alarm> alarmList = alarmRepository.findAllByMemberIdAndIsRead(memberId, false);
        List<AlarmDto> alarmDtoList = new ArrayList<>();
        for (Alarm alarm : alarmList) {
            alarmDtoList.add(new AlarmDto(alarm.getId(), alarm.getContent(), alarm.getIsRead(),alarm.getTokenId(), alarm.getInvestCount()));
        }

        return alarmDtoList;
    }

    @Transactional
    public void readAlarm(Long alarmId){
        Alarm alarm = alarmRepository.findById(alarmId).orElseThrow(() -> new CustomException(ErrorCode.ALARM_NOT_FOUND));
        alarm.changeIsRead();
    }

    /**
     * 스타트업이 NFT토큰 발행 후 투자자들에게 NFT토큰 됐다는 알람 생성하는 함수
     */
    @Transactional
    public void mintSuccess(Integer lastTokenId, Long alarmId) {
        List<Alarm> alarmList = new ArrayList<>();
        Alarm alarm = alarmRepository.findById(alarmId).orElseThrow(
                () -> new CustomException(ErrorCode.ALARM_NOT_FOUND)
        );
        alarm.changeIsRead();

        Startup startup = startupRepository.findById(alarm.getStartupId()).orElseThrow(
                () -> new CustomException(ErrorCode.STARTUP_NOT_FOUND)
        );
        int firstTokenId = lastTokenId - startup.getNftReserveCount() + 1;
        List<Invest_history> investHistoryList = investHistoryRepository.findByInvestingInvestHistoryList(startup.getId());
        for (Invest_history investHistory : investHistoryList) {
            // 알람 생성(투자자에게 보내는 알람)
            Member member = new Member();
            member.changeId(investHistory.getMember().getId());

            Alarm alarmToInvestor = Alarm.builder()
                    .content(startup.getTitle() + " NFT가 발행됐습니다. 투자하시겠습니까?")
                    .member(member)
                    .isRead(Boolean.FALSE)
                    .startupId(startup.getId())
                    .tokenId(firstTokenId++)
                    .build();
            alarmList.add(alarmToInvestor);
        }
        alarmRepository.saveAll(alarmList);
    }

    /**
     * 투자자가 NFT 구매 성공 한 후
     */
    @Transactional
    public void nftPurchase(Long alarmId) {
        Alarm alarm = alarmRepository.findById(alarmId).orElseThrow(
                () -> new CustomException(ErrorCode.ALARM_NOT_FOUND)
        );
        alarm.changeIsRead();

        Invest_history investHistory = investHistoryRepository.findByMemberIdAndStartupId(alarm.getMember().getId(), alarm.getStartupId());
        investHistory.changeInvestStatus(InvestStatus.INVESTED);
    }
}
