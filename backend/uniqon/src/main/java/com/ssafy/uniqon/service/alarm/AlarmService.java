package com.ssafy.uniqon.service.alarm;

import com.ssafy.uniqon.domain.alarm.Alarm;
import com.ssafy.uniqon.domain.invest.InvestStatus;
import com.ssafy.uniqon.domain.invest.Invest_history;
import com.ssafy.uniqon.domain.member.Member;
import com.ssafy.uniqon.domain.startup.Startup;
import com.ssafy.uniqon.exception.ex.CustomException;
import com.ssafy.uniqon.exception.ex.ErrorCode;
import com.ssafy.uniqon.repository.invest.InvestHistoryRepository;
import com.ssafy.uniqon.repository.mypage.AlarmRepository;
import com.ssafy.uniqon.repository.startup.StartupRepository;
import com.ssafy.uniqon.util.SecurityUtil;
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

    /**
     * 스타트업이 NFT토큰 발행 후 투자자들에게 NFT토큰 됐다는 알람 생성하는 함수
     */
    @Transactional
    public void mintSuccess(Long alarmId) {
        List<Alarm> alarmList = new ArrayList<>();
        Alarm alarm = alarmRepository.findById(alarmId).orElseThrow(
                () -> new CustomException(ErrorCode.ALARM_NOT_FOUND)
        );
        alarm.changeIsRead();

        Startup startup = startupRepository.findById(alarm.getStartupId()).orElseThrow(
                () -> new CustomException(ErrorCode.STARTUP_NOT_FOUND)
        );

        List<Invest_history> investHistoryList = investHistoryRepository.findByInvestingInvestHistoryList(startup.getId());
        investHistoryList.forEach(invest_history -> {
            // 알람 생성(투자자에게 보내는 알람)
            Member member = new Member();
            member.changeId(invest_history.getMember().getId());

            Alarm alarmToInvestor = Alarm.builder()
                    .content(startup.getTitle() + " NFT가 발행됐습니다. 투자하시겠습니까?")
                    .member(member)
                    .isRead(Boolean.FALSE)
                    .startupId(startup.getId())
                    .build();
            alarmList.add(alarmToInvestor);
        });
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

        Invest_history investHistory = investHistoryRepository.findByIdAndStartupId(alarmId, alarm.getStartupId());
        investHistory.changeInvestStatus(InvestStatus.INVESTED);
    }
}
