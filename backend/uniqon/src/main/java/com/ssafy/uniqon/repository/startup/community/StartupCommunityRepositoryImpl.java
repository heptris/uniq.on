package com.ssafy.uniqon.repository.startup.community;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.uniqon.domain.member.QMember;
import com.ssafy.uniqon.domain.startup.community.QCommunityComment;
import com.ssafy.uniqon.domain.startup.community.QStartupCommunity;
import com.ssafy.uniqon.domain.startup.community.StartupCommunity;
import com.ssafy.uniqon.dto.startup.community.*;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ssafy.uniqon.domain.member.QMember.member;
import static com.ssafy.uniqon.domain.startup.community.QCommunityComment.communityComment;
import static com.ssafy.uniqon.domain.startup.community.QStartupCommunity.startupCommunity;

public class StartupCommunityRepositoryImpl implements StartupCommunityRepositoryCustom {

    private JPAQueryFactory queryFactory;

    public StartupCommunityRepositoryImpl(EntityManager entityManager){
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Optional<StartupCommunityResponseDetailDto> findDetail(Long startupCommunityId, Long memberId) {
        Optional<StartupCommunityResponseDetailDto> detailDto = Optional.ofNullable(queryFactory.select(Projections.constructor(StartupCommunityResponseDetailDto.class,
                        startupCommunity.title,
                        startupCommunity.content,
                        startupCommunity.member.nickname,
                        startupCommunity.hit,
                        startupCommunity.createdDate
                ))
                .from(startupCommunity)
                .innerJoin(startupCommunity.member, member)
                .where(startupCommunity.id.eq(startupCommunityId))
                .fetchOne());

        if(!detailDto.isPresent()){
            return Optional.empty();
        }

        List<CommunityCommentResponseDto> comments =
                queryFactory
                        .select(Projections.constructor(CommunityCommentResponseDto.class,
                        communityComment.parent.id,
                        communityComment.id,
                        communityComment.content,
                        member.nickname,
                        JPAExpressions
                                .selectFrom(communityComment)
                                .where(member.id.eq(memberId))
                                .exists(),
                        communityComment.createdDate,
                        communityComment.lastModifiedDate
                        ))
                        .from(communityComment)
                        .innerJoin(communityComment.startupCommunity, startupCommunity)
                        .innerJoin(communityComment.member, member)
                        .where(startupCommunity.id.eq(startupCommunityId).and(communityComment.parent.id.isNull()))
                        .fetch();

        List<CommunityCommentChildrenResponseDto> childComments =
                queryFactory
                        .select(Projections.constructor(CommunityCommentChildrenResponseDto.class,
                        communityComment.parent.id,
                        communityComment.id,
                        communityComment.content,
                        member.nickname,
                        JPAExpressions
                                .selectFrom(communityComment)
                                .where(member.id.eq(memberId))
                                .exists(),
                        communityComment.createdDate,
                        communityComment.lastModifiedDate
                        ))
                        .from(communityComment)
                        .innerJoin(communityComment.startupCommunity, startupCommunity)
                        .innerJoin(communityComment.member, member)
                        .where(startupCommunity.id.eq(startupCommunityId).and(communityComment.parent.id.isNotNull()))
                        .fetch();

        comments.stream()
                .forEach(parent -> {
                    parent.setChildren(childComments.stream()
                            .filter(child -> child.getParentId().equals(parent.getCommentId()))
                            .collect(Collectors.toList()));
                });
        detailDto.get().setCommentList(comments);
        return detailDto;
    }
}
