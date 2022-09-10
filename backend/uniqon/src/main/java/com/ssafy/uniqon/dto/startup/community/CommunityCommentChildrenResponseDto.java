package com.ssafy.uniqon.dto.startup.community;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@Builder
@NoArgsConstructor
public class CommunityCommentChildrenResponseDto {
    private Long parentId;
    private Long commentId;
    private String content;
    private String nickName;
    private boolean myComment;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;
    @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updateDate;

    public CommunityCommentChildrenResponseDto(Long parentId, Long commentId, String content, String nickName, boolean myComment, LocalDateTime createdDate, LocalDateTime updateDate) {
        this.parentId = parentId;
        this.commentId = commentId;
        this.content = content;
        this.nickName = nickName;
        this.myComment = myComment;
        this.createdDate = createdDate;
        this.updateDate = updateDate;
    }
}
