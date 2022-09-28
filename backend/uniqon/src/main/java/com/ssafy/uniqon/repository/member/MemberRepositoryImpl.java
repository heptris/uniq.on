package com.ssafy.uniqon.repository.member;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.uniqon.domain.member.QMember;
import com.ssafy.uniqon.domain.startup.QStartup;
import com.ssafy.uniqon.domain.startup.QStartupFavorite;
import com.ssafy.uniqon.dto.member.MemberFavStartupDto;

import javax.persistence.EntityManager;
import java.util.List;

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
}
