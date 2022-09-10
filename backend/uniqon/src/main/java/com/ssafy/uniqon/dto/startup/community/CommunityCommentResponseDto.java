package com.ssafy.uniqon.dto.startup.community;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommunityCommentResponseDto {
    private Long parentId;
    private Long commentId;
    private String content;
    private String nickName;
    private boolean myComment;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updateDate;
    private List<CommunityCommentChildrenResponseDto> children = new ArrayList<>();

    public CommunityCommentResponseDto(Long parentId, Long commentId, String content, String nickName, boolean myComment, LocalDateTime createdDate, LocalDateTime updateDate){
        this.parentId = parentId;
        this.commentId = commentId;
        this.content = content;
        this.nickName = nickName;
        this.myComment = myComment;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
    }
}
