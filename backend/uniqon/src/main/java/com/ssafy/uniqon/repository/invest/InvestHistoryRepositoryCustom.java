package com.ssafy.uniqon.repository.invest;

import com.ssafy.uniqon.domain.invest.Invest_history;

import java.util.List;

public interface InvestHistoryRepositoryCustom {

    List<Invest_history> findByInvestingInvestHistoryList(Long startupId);
}
