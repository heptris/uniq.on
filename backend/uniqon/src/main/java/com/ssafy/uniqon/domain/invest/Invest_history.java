package com.ssafy.uniqon.domain.invest;

import com.ssafy.uniqon.domain.BaseEntity;
import com.ssafy.uniqon.domain.member.Member;
import com.ssafy.uniqon.domain.startup.Startup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Entity
public class Invest_history extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invest_history_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "startup_id")
    private Startup startup;

    @Enumerated(EnumType.STRING)
    private InvestStatus investStatus;

    public void changeInvestStatus(InvestStatus investStatus) {
        this.investStatus = investStatus;
    }
}
