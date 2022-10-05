package com.ssafy.uniqon.dto.startup.community;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StartupCommunityResponseListDto {
    private Long communityId;
    private String startupName;
    private String title;
    private String nickName;
    private Integer hit;
    private Integer commentsCount;
    private LocalDateTime createdDate;
}
