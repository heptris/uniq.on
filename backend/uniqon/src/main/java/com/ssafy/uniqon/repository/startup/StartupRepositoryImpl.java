package com.ssafy.uniqon.repository.startup;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.uniqon.domain.startup.QStartup;
import com.ssafy.uniqon.dto.startup.StartupResponseListDto;
import com.ssafy.uniqon.dto.startup.StartupSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.ssafy.uniqon.domain.startup.QStartup.startup;

public class StartupRepositoryImpl implements StartupRepositoryCustom{
    private JPAQueryFactory queryFactory;

    public StartupRepositoryImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<StartupResponseListDto> search(StartupSearchCondition condition, Pageable pageable) {
        List<StartupResponseListDto> contents = queryFactory
                .select(Projections.constructor(StartupResponseListDto.class,
                        startup.startupName,
                        startup.title,
                        startup.endDate,
                        startup.imageNft
                        ))
                .from(startup)
                .where(titleEq(condition.getTitle()),
                        startupNameEq(condition.getStartupName()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = queryFactory
                .select(startup)
                .from(startup)
                .where(titleEq(condition.getTitle()),
                        startupNameEq(condition.getStartupName()))
                .fetchCount();

        return new PageImpl<>(contents, pageable, total);
    }


    private BooleanExpression titleEq(String title) {
        return title == null ? null : startup.title.contains(title);
    }
    private BooleanExpression startupNameEq(String startupName) {
        return startupName == null ? null : startup.startupName.contains(startupName);
    }
}
