package com.ssafy.uniqon.repository.startup.qna;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.uniqon.domain.startup.qna.QStartupQuestion;
import com.ssafy.uniqon.dto.startup.qna.AnswerChildrenResponseDto;
import com.ssafy.uniqon.dto.startup.qna.AnswerParentResponseDto;
import com.ssafy.uniqon.dto.startup.qna.StartupQuestionResDto;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static com.ssafy.uniqon.domain.member.QMember.member;
import static com.ssafy.uniqon.domain.startup.qna.QStartupAnswer.startupAnswer;
import static com.ssafy.uniqon.domain.startup.qna.QStartupQuestion.startupQuestion;

public class StartupQuestionRepositoryImpl implements StartupQuestionRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public StartupQuestionRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<StartupQuestionResDto> findStartupQuestionResDtoList(Long memberId, Long startupId) {

        List<StartupQuestionResDto> startupQuestionResDtoList = queryFactory.select(Projections.constructor(StartupQuestionResDto.class,
                        member.nickname,
                        member.id,
                        startupQuestion.createdDate,
                        startupQuestion.id,
                        startupQuestion.question,
                        JPAExpressions.selectFrom(startupQuestion)
                                .where(member.id.eq(memberId))
                                .exists()
                        ))
                .from(startupQuestion)
                .innerJoin(startupQuestion.member, member)
                .where(startupQuestion.startup.id.eq(startupId))
                .fetch();

        startupQuestionResDtoList.forEach(startupQuestionResDto -> {
            startupQuestionResDto.changeParentResponseDto(findAnswerParentResponseDtoList(memberId, startupQuestionResDto.getStartupQuestionId()));
        });

        return startupQuestionResDtoList;
    }

    @Override
    public List<StartupQuestionResDto> findQuestionListDtoPage(Long memberId, Long startupId, Pageable page) {

        List<StartupQuestionResDto> startupQuestionResDtoList = queryFactory.select(Projections.constructor(StartupQuestionResDto.class,
                        member.nickname,
                        member.id,
                        startupQuestion.createdDate,
                        startupQuestion.id,
                        startupQuestion.question,
                        JPAExpressions.selectFrom(startupQuestion)
                                .where(member.id.eq(memberId))
                                .exists()
                        ))
                .from(startupQuestion)
                .innerJoin(startupQuestion.member, member)
                .where(startupQuestion.startup.id.eq(startupId))
                .orderBy(startupQuestion.id.desc())
                .offset(page.getOffset())
                .limit(page.getPageSize())
                .fetch();

        startupQuestionResDtoList.forEach(startupQuestionResDto -> {
            startupQuestionResDto.changeParentResponseDto(findAnswerParentResponseDtoList(memberId, startupQuestionResDto.getStartupQuestionId()));
        });

        return startupQuestionResDtoList;
    }

    @Override
    public List<StartupQuestionResDto> findQuestionListDtoLessPage(Long memberId, Long startupId, Long cursorId, Pageable page) {
        List<StartupQuestionResDto> startupQuestionResDtoList = queryFactory.select(Projections.constructor(StartupQuestionResDto.class,
                        member.nickname,
                        member.id,
                        startupQuestion.createdDate,
                        startupQuestion.id,
                        startupQuestion.question,
                        JPAExpressions.selectFrom(startupQuestion)
                                .where(member.id.eq(memberId))
                                .exists()
                ))
                .from(startupQuestion)
                .innerJoin(startupQuestion.member, member)
                .where(startupQuestion.startup.id.eq(startupId).and(startupQuestion.id.lt(cursorId)))
                .orderBy(startupQuestion.id.desc())
                .offset(page.getOffset())
                .limit(page.getPageSize())
                .fetch();

        startupQuestionResDtoList.forEach(startupQuestionResDto -> {
            startupQuestionResDto.changeParentResponseDto(findAnswerParentResponseDtoList(memberId, startupQuestionResDto.getStartupQuestionId()));
        });

        return startupQuestionResDtoList;
    }

    @Override
    public List<AnswerParentResponseDto> findAnswerParentResponseDtoList(Long memberId, Long startupQuestionId) {
        List<AnswerParentResponseDto> answerParentResponseDtoList = queryFactory.select(Projections.constructor(AnswerParentResponseDto.class
                        , startupAnswer.id,
                        member.id,
                        member.nickname,
                        startupAnswer.answer,
                        startupAnswer.createdDate,
                        startupAnswer.parent.id,
                        JPAExpressions.selectFrom(startupAnswer)
                                .where(member.id.eq(memberId))
                                .exists()
                ))
                .from(startupAnswer)
                .innerJoin(startupAnswer.member, member)
                .innerJoin(startupAnswer.startupQuestion, startupQuestion)
                .where(startupQuestion.id.eq(startupQuestionId).and(startupAnswer.parent.id.isNull()))
                .orderBy(startupAnswer.id.asc())
                .fetch();

        List<AnswerChildrenResponseDto> answerChildrenResponseDtoList = queryFactory.select(Projections.constructor(AnswerChildrenResponseDto.class
                        , startupAnswer.id,
                        member.id,
                        member.nickname,
                        startupAnswer.answer,
                        startupAnswer.createdDate,
                        startupAnswer.parent.id,
                        JPAExpressions.selectFrom(startupAnswer)
                                .where(member.id.eq(memberId))
                                .exists()
                ))
                .from(startupAnswer)
                .innerJoin(startupAnswer.member, member)
                .innerJoin(startupAnswer.startupQuestion, startupQuestion)
                .where(startupQuestion.id.eq(startupQuestionId).and(startupAnswer.parent.id.isNotNull()))
                .fetch();

        answerParentResponseDtoList.stream()
                .forEach(answerParentResponseDto -> {
                    answerParentResponseDto.changeChildren(answerChildrenResponseDtoList.stream()
                            .filter(answerChildrenResponseDto -> answerChildrenResponseDto.getParentId().equals(
                                    answerParentResponseDto.getStartupAnswerId()
                            )).collect(Collectors.toList()));
                });
        return answerParentResponseDtoList;
    }
}
