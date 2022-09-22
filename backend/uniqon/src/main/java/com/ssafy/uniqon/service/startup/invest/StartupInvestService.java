package com.ssafy.uniqon.service.startup.invest;

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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class StartupInvestService {

    private final StartupRepository startupRepository;
    private final InvestHistoryRepository investHistoryRepository;

    private final AlarmRepository alarmRepository;

    @Transactional
    public void startup_invest(Long memberId, Long startupId) {
        Startup startup = startupRepository.findById(startupId).orElseThrow(
                () -> new CustomException(ErrorCode.STARTUP_NOT_FOUND)
        );
        startup.investCountIncrement();
        startup.changeCurTotalPrice();

        // 현재 모금액이 목표금액보다 클 경우 목표달성 True
        if (!startup.getIsGoal() && startup.getCurTotalPrice() >= startup.getGoalPrice()) {
            startup.changeIsGoal();
        }

        Member member = new Member();
        member.changeId(memberId);

        Invest_history investHistory = Invest_history.builder()
                .member(member)
                .startup(startup)
                .investStatus(InvestStatus.INVESTING)
                .build();
        investHistoryRepository.save(investHistory);
    }

    @Transactional
    @Scheduled(cron = "*/20 * * * * *")
    public void test() {
        List<Alarm> alarmList = new ArrayList<>();
        List<Startup> startupList = startupRepository.findByInvestingStartupList();
        log.info("startupList count {}", startupList.size());
        startupList.forEach(startup -> {
            log.info("startupId {}", startup.getId());
        });

        startupList.forEach(
                startup -> {
                    if (startup.getEndDate().isBefore(LocalDateTime.now())) { // 마감일 지났을 때
                        startup.changeIsFinish();   // 투자 마감 표시
                        if (startup.getIsGoal()) { // 목표금액 달성 했을 경우
                            // 스타트업 NFT토큰 발행
                            log.info("목표 금액 달성!! NFT 토큰 발행");
                            // 투자자 NFT토큰 구매

                            // 알람 생성
                            List<Invest_history> investHistoryList = investHistoryRepository.findByInvestingInvestHistoryList(startup.getId());
                            investHistoryList.forEach(invest_history -> {
                                // 알람 생성
                                Member member = new Member();
                                member.changeId(invest_history.getMember().getId());
                                Alarm alarm = Alarm.builder()
                                        .content("목표금액 달성했습니다. 투자 완료 확인해주십시오")
                                        .isRead(Boolean.FALSE)
                                        .member(member)
                                        .build();
                                alarmList.add(alarm);
                            });
                        } else { // 목표금액 달성하지 못했을 경우
                            List<Invest_history> investHistoryList = investHistoryRepository.findByInvestingInvestHistoryList(startup.getId());
                            investHistoryList.forEach(invest_history -> {
                                invest_history.changeInvestStatus(InvestStatus.CANCELED);
                                
                                // 알람 생성
                                Member member = new Member();
                                member.changeId(invest_history.getMember().getId());
                                Alarm alarm = Alarm.builder()
                                        .content("목표금액 달성에 실패하여 투자에 실패했습니다.")
                                        .isRead(Boolean.FALSE)
                                        .member(member)
                                        .build();
                                alarmList.add(alarm);
                            });
                        } // end of else
                    }
                }
        );
        alarmRepository.saveAll(alarmList);
    }

    @Scheduled(cron = "0 1 * * * * ")   // 매시각 1분에 실행
    public void startupInvestCheck() {
        log.info("test");
    }
}
