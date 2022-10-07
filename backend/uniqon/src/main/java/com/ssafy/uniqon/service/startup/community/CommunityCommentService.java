package com.ssafy.uniqon.service.startup.community;

import com.ssafy.uniqon.domain.member.Member;
import com.ssafy.uniqon.domain.startup.community.CommunityComment;
import com.ssafy.uniqon.domain.startup.community.StartupCommunity;
import com.ssafy.uniqon.dto.startup.community.CommunityCommentRequestDto;
import com.ssafy.uniqon.dto.startup.community.CommunityCommentRequestModifyDto;
import com.ssafy.uniqon.exception.ex.CustomException;
import com.ssafy.uniqon.exception.ex.ErrorCode;
import com.ssafy.uniqon.repository.member.MemberRepository;
import com.ssafy.uniqon.repository.startup.community.CommunityCommentRepository;
import com.ssafy.uniqon.repository.startup.community.StartupCommunityRepository;
import com.ssafy.uniqon.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommunityCommentService {

    private final CommunityCommentRepository communityCommentRepository;
    private final StartupCommunityRepository startupCommunityRepository;
    private final MemberRepository memberRepository;

    public void commentWrite(Long communityId, CommunityCommentRequestDto requestDto){
        Long memberId = SecurityUtil.getCurrentMemberId();
        StartupCommunity startupCommunity = startupCommunityRepository.findById(communityId).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        CommunityComment parent = null;

        if(requestDto.getParentId() != null){
            CommunityComment answer = communityCommentRepository.findById(requestDto.getParentId()).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

            Long id = answer.getParent() == null ? answer.getId() : answer.getParent().getId();
            parent = new CommunityComment();
            parent.changeId(id);
        }

        CommunityComment comment = CommunityComment.createCommunityComment(requestDto.getContent(), parent, member, startupCommunity);

        communityCommentRepository.save(comment);
    }

    public void commentModify(Long communityId, Long commentId, CommunityCommentRequestModifyDto requestModifyDto){
        CommunityComment comment = communityCommentRepository.findById(commentId).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        comment.changeContent(requestModifyDto.getContent());
    }

    public void commentDelete(Long communityId, Long commentId){
        communityCommentRepository.deleteById(commentId);
    }

}
