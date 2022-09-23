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
                        List<Invest_history> investHistoryList = investHistoryRepository.findByInvestingInvestHistoryList(startup.getId());
                        if (startup.getIsGoal()) { // 목표금액 달성 했을 경우
                            // 알람 생성 (투자자에게 보내는 알람)
                            investHistoryList.forEach(invest_history -> {
                                Member member = new Member();
                                member.changeId(invest_history.getMember().getId());
                                Alarm alarm = Alarm.builder()
                                        .content(startup.getStartupName() + "이 NFT 발행을 진행 중에 있습니다. 잠시만 기다려 주세요!!")
                                        .isRead(Boolean.FALSE)
                                        .startupId(startup.getId())
                                        .member(member)
                                        .build();
                                alarmList.add(alarm);
                            });

                            // 알람 생성 (스타트업에게만 보내는 알람)
                            Member memberStartup = new Member();
                            memberStartup.changeId(startup.getMember().getId());

                            Alarm alarmToStartup = Alarm.builder()
                                    .member(memberStartup)
                                    .isRead(Boolean.FALSE)
                                    .startupId(startup.getId())
                                    .content(startup.getStartupName() + " 투자 유치에 성공했습니다. NFT 토큰을 발급해주세요")
                                    .build();
                            alarmList.add(alarmToStartup);

                        } else { // 목표금액 달성하지 못했을 경우
                            investHistoryList.forEach(invest_history -> {
                                invest_history.changeInvestStatus(InvestStatus.CANCELED);

                                // 알람 생성 (투자자에게 보내는 알람)
                                Member member = new Member();
                                member.changeId(invest_history.getMember().getId());
                                Alarm alarm = Alarm.builder()
                                        .content(startup.getStartupName() + " 투자 예약에 실패했습니다.")
                                        .isRead(Boolean.FALSE)
                                        .startupId(startup.getId())
                                        .member(member)
                                        .build();
                                alarmList.add(alarm);

                            });
                            // 알람 생성(스타트업에게 보내는 알람)
                            Member memberStartup = new Member();
                            memberStartup.changeId(startup.getMember().getId());

                            Alarm alarmToStartup = Alarm.builder()
                                    .member(memberStartup)
                                    .isRead(Boolean.FALSE)
                                    .startupId(startup.getId())
                                    .content(startup.getStartupName() + " 투자 유치에 실패했습니다.")
                                    .build();
                            alarmList.add(alarmToStartup);

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
