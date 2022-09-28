package com.ssafy.uniqon.repository.member;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.uniqon.domain.invest.InvestStatus;
import com.ssafy.uniqon.domain.invest.QInvest_history;
import com.ssafy.uniqon.domain.member.QMember;
import com.ssafy.uniqon.domain.startup.QStartup;
import com.ssafy.uniqon.domain.startup.QStartupFavorite;
import com.ssafy.uniqon.dto.member.MemberFavStartupDto;
import com.ssafy.uniqon.dto.member.MemberInvestedStartupDto;
import com.ssafy.uniqon.dto.member.StartupInvestedListDto;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.uniqon.domain.invest.InvestStatus.INVESTING;
import static com.ssafy.uniqon.domain.invest.QInvest_history.invest_history;
import static com.ssafy.uniqon.domain.member.QMember.member;
import static com.ssafy.uniqon.domain.startup.QStartup.startup;
import static com.ssafy.uniqon.domain.startup.QStartupFavorite.startupFavorite;

public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public MemberRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }
    @Override
    public List<MemberFavStartupDto> findFavStartup(Long memberId) {

        List<MemberFavStartupDto> memberFavStartupDtoList = queryFactory
                .select(Projections.constructor(MemberFavStartupDto.class,
                        member.id,
                        startup.id,
                        startup.startupName,
                        startup.title,
                        startup.description,
                        startup.nftCount,
                        startup.investCount,
                        startup.pricePerNft,
                        startup.endDate,
                        startup.businessPlan,
                        startup.businessPlanImg,
                        startup.roadMap,
                        startup.imageNft,
                        startupFavorite.isFav
                ))
                .from(member)
                .innerJoin(member.startupFavoriteList, startupFavorite)
                .innerJoin(startupFavorite.startup, startup)
                .where(member.id.eq(memberId).and(startupFavorite.isFav.eq(true)))
                .fetch();

        return memberFavStartupDtoList;
    }

    @Override
    public List<MemberInvestedStartupDto> findInvestedStartup(Long memberId) {
        List<MemberInvestedStartupDto> memberInvestedStartupDtoList = queryFactory
                .select(Projections.constructor(MemberInvestedStartupDto.class,
                        member.id,
                        startup.id,
                        startup.startupName,
                        startup.title,
                        startup.description,
                        startup.nftCount,
                        startup.investCount,
                        startup.pricePerNft,
                        startup.endDate,
                        startup.businessPlan,
                        startup.businessPlanImg,
                        startup.roadMap,
                        startup.imageNft,
                        invest_history.investStatus
                ))
                .from(member)
                .innerJoin(member.investHistoryList, invest_history)
                .innerJoin(invest_history.startup, startup)
                .where(member.id.eq(memberId).and(startup.member.id.ne(memberId)))
                .fetch();

        return memberInvestedStartupDtoList;
    }

    @Override
    public List<StartupInvestedListDto> findStartupInvestedList(Long memberId) {
        List<StartupInvestedListDto> startupInvestedListDtoList = queryFactory
                .select(Projections.constructor(StartupInvestedListDto.class,
                        member.id,
                        startup.id,
                        startup.startupName,
                        startup.title,
                        startup.description,
                        startup.nftCount,
                        startup.investCount,
                        startup.pricePerNft,
                        startup.endDate,
                        startup.businessPlan,
                        startup.businessPlanImg,
                        startup.roadMap,
                        startup.imageNft
//                        invest_history.investStatus
                ))
                .from(member)
                .innerJoin(member.startupList, startup)
//                .innerJoin(invest_history.startup, startup)
                .where(member.id.eq(memberId))
                .fetch();

        return startupInvestedListDtoList;
    }
}
