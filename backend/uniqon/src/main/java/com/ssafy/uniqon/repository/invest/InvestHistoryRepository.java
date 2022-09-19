package com.ssafy.uniqon.repository.invest;

import com.ssafy.uniqon.domain.invest.Invest_history;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestHistoryRepository extends JpaRepository<Invest_history, Long>, InvestHistoryRepositoryCustom {

}
