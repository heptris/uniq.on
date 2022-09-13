package com.ssafy.uniqon.service.startup.community;

import com.ssafy.uniqon.dto.startup.community.CommunityCommentRequestDto;
import com.ssafy.uniqon.dto.startup.community.CommunityCommentRequestModifyDto;
import com.ssafy.uniqon.repository.startup.community.CommunityCommentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class CommunityCommentServiceTest {

    @Autowired
    private CommunityCommentService communityCommentService;
    @Autowired
    private CommunityCommentRepository communityCommentRepository;

    @Test
    public void 댓글등록(){
        CommunityCommentRequestDto requestDto = CommunityCommentRequestDto.builder()
                .content("댓글 등록 Test")
                .build();
        communityCommentService.commentWrite(1L, requestDto);

        assertThat(communityCommentRepository.findById(2L).get().getContent()).isEqualTo("댓글 등록 Test");
    }

    @Test
    public void 댓글수정(){
        CommunityCommentRequestModifyDto requestModifyDto = CommunityCommentRequestModifyDto.builder()
                .content("댓글 수정 Test")
                .build();
        communityCommentService.commentModify(1L, 1L, requestModifyDto);

        assertThat(communityCommentRepository.findById(1L).get().getContent()).isEqualTo("댓글 수정 Test");
    }

    @Test
    public void 댓글삭제(){
        communityCommentService.commentDelete(1L, 1L);

        assertThat(communityCommentRepository.findAll().size()).isEqualTo(0);
    }
}