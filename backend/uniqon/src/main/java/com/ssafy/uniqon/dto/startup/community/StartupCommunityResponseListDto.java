package com.ssafy.uniqon.dto.startup.community;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StartupCommunityResponseListDto {
    private String title;
    private String nickName;
    private Integer commentsCount;
    private LocalDateTime createdDate;
}
