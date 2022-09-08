package com.ssafy.uniqon.service.startup.community;

import com.ssafy.uniqon.domain.startup.community.CommunityComment;
import com.ssafy.uniqon.domain.startup.community.StartupCommunity;
import com.ssafy.uniqon.dto.startup.community.CommunityCommentRequestDto;
import com.ssafy.uniqon.exception.ex.CustomException;
import com.ssafy.uniqon.exception.ex.ErrorCode;
import com.ssafy.uniqon.repository.startup.community.CommunityCommentRepository;
import com.ssafy.uniqon.repository.startup.community.StartupCommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommunityCommentService {

    private final CommunityCommentRepository communityCommentRepository;
    private final StartupCommunityRepository startupCommunityRepository;

    public void commentWrite(Long communityId, CommunityCommentRequestDto requestDto){
        StartupCommunity startupCommunity = startupCommunityRepository.findById(communityId).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));

        CommunityComment comment = CommunityComment.createCommunityComment(requestDto.getContent(), startupCommunity);
        communityCommentRepository.save(comment);
    }

    public void commentModify(Long communityId, Long commentId){
        CommunityComment comment = communityCommentRepository.findById(commentId).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        comment.changeContent();
    }

    public void commentDelete(Long communityId, Long commentId){
    }
}
