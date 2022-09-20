package com.ssafy.uniqon.repository.invest;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.uniqon.domain.invest.InvestStatus;
import com.ssafy.uniqon.domain.invest.Invest_history;
import com.ssafy.uniqon.domain.invest.QInvest_history;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.uniqon.domain.invest.QInvest_history.*;

public class InvestHistoryRepositoryImpl implements InvestHistoryRepositoryCustom{

    private JPAQueryFactory queryFactory;

    public InvestHistoryRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Invest_history> findByInvestingInvestHistoryList(Long startupId) {
        List<Invest_history> investHistoryList = queryFactory.selectFrom(invest_history)
                .where(invest_history.investStatus.eq(InvestStatus.INVESTING).and(invest_history.startup.id.eq(startupId)))
                .fetch();
        return investHistoryList;
    }
}
