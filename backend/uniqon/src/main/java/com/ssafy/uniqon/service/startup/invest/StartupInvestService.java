package com.ssafy.uniqon.service.startup.invest;

import com.ssafy.uniqon.domain.invest.InvestStatus;
import com.ssafy.uniqon.domain.invest.Invest_history;
import com.ssafy.uniqon.domain.member.Member;
import com.ssafy.uniqon.domain.startup.Startup;
import com.ssafy.uniqon.exception.ex.CustomException;
import com.ssafy.uniqon.exception.ex.ErrorCode;
import com.ssafy.uniqon.repository.invest.InvestHistoryRepository;
import com.ssafy.uniqon.repository.startup.StartupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class StartupInvestService {

    private final StartupRepository startupRepository;
    private final InvestHistoryRepository investHistoryRepository;

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

    @Scheduled(cron = "*/20 * * * * *")
    public void test() {
        List<Startup> startupList = startupRepository.findAll();
        startupList.forEach(
                startup -> {
                    if (startup.getEndDate().isBefore(LocalDateTime.now())) { // 마감일 지났을 때
                        if (startup.getIsGoal()) { // 목표금액 달성 했을 경우
                            // 스타트업 NFT토큰 발행

                            // 투자자 NFT토큰 구매
                        } else { // 목표금액 달성하지 못했을 경우
                            List<Invest_history> investHistoryList = investHistoryRepository.findByInvestingInvestHistoryList(startup.getId());
                            investHistoryList.stream().map(invest_history -> {
                                invest_history.changeInvestStatus(InvestStatus.CANCELED);
                                return null;
                            });
                        } // end of else
                    }
                }
        );
    }

    @Scheduled(cron = "0 1 * * * * ")   // 매시각 1분에 실행
    public void startupInvestCheck() {
        log.info("test");
    }
}
