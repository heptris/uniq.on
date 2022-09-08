package com.ssafy.uniqon.dto.startup.community;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StartupCommunityRequestModifyDto {
    private String title;
    private String content;
    private Long startupCommunityId;
}
