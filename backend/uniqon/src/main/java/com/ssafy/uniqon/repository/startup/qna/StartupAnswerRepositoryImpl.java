package com.ssafy.uniqon.repository.startup.qna;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.uniqon.domain.startup.qna.QStartupAnswer;
import com.ssafy.uniqon.domain.startup.qna.StartupAnswer;
import com.ssafy.uniqon.dto.startup.qna.AnswerChildrenResponseDto;
import com.ssafy.uniqon.dto.startup.qna.AnswerParentResponseDto;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

import static com.ssafy.uniqon.domain.member.QMember.member;
import static com.ssafy.uniqon.domain.startup.qna.QStartupAnswer.*;
import static com.ssafy.uniqon.domain.startup.qna.QStartupQuestion.startupQuestion;

public class StartupAnswerRepositoryImpl implements StartupAnswerRepositoryCustom{
    private JPAQueryFactory queryFactory;

    public StartupAnswerRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<AnswerParentResponseDto> findAnswerParentResponseDtoList(Long startupQuestionId) {
        List<AnswerParentResponseDto> answerParentResponseDtoList = queryFactory.select(Projections.constructor(AnswerParentResponseDto.class
                        , startupAnswer.id,
                        member.id,
                        member.nickname,
                        startupAnswer.answer,
                        startupAnswer.createdDate,
                        startupAnswer.parent.id))
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
                        startupAnswer.parent.id))
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
