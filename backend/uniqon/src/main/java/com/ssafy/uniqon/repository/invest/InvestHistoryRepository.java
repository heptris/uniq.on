package com.ssafy.uniqon.repository.invest;

import com.ssafy.uniqon.domain.invest.Invest_history;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface InvestHistoryRepository extends JpaRepository<Invest_history, Long>, InvestHistoryRepositoryCustom {

    @Query("select ih from Invest_history ih where ih.member.id = :memberId and ih.startup.id = :startupId")
    Invest_history findByMemberIdAndStartupId(@Param("memberId") Long memberId, @Param("startupId") Long startupId);
}
