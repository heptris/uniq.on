package com.ssafy.uniqon.dto.startup.community;

import com.ssafy.uniqon.domain.startup.community.CommunityComment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StartupCommunityResponseDetailDto {
    private String title;
    private String content;
    private String nickName;
    private List<CommunityComment> commentList;
    private LocalDateTime createdDate;
}
