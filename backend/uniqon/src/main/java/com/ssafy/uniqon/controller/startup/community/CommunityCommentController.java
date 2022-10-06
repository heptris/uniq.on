package com.ssafy.uniqon.controller.startup.community;

import com.ssafy.uniqon.domain.startup.community.CommunityComment;
import com.ssafy.uniqon.dto.response.ResponseDto;
import com.ssafy.uniqon.dto.startup.community.CommunityCommentRequestDto;
import com.ssafy.uniqon.dto.startup.community.CommunityCommentRequestModifyDto;
import com.ssafy.uniqon.service.startup.community.CommunityCommentService;
import com.ssafy.uniqon.service.startup.community.StartupCommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app/invest/community-comments")
@RequiredArgsConstructor
public class CommunityCommentController {

    private final CommunityCommentService communityCommentService;

    @PostMapping("/{communityId}/comment")
    public ResponseEntity commentWrite(@PathVariable Long communityId, @RequestBody CommunityCommentRequestDto requestDto){
        communityCommentService.commentWrite(communityId, requestDto);
        return new ResponseEntity(new ResponseDto(200, "success", "댓글 작성 완료"), HttpStatus.OK);
    }
    
    @PutMapping("/{communityId}/{commentId}")
    public ResponseEntity communityModify(@PathVariable Long communityId,
                                          @PathVariable Long commentId,
                                          @RequestBody CommunityCommentRequestModifyDto requestModifyDto){
        communityCommentService.commentModify(communityId, commentId, requestModifyDto);
        return new ResponseEntity(new ResponseDto(200, "success", "댓글 수정 완료"), HttpStatus.OK);
    }

    @DeleteMapping("/{communityId}/{commentId}")
    public ResponseEntity communityDelete(@PathVariable Long communityId, @PathVariable Long commentId){
        communityCommentService.commentDelete(communityId, commentId);
        return new ResponseEntity(new ResponseDto(200, "success", "댓글 삭제 완료"), HttpStatus.OK);
    }

}
