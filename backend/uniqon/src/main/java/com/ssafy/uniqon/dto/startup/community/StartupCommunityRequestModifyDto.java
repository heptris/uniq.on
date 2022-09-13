package com.ssafy.uniqon.dto.startup.community;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StartupCommunityRequestModifyDto {
    private String title;
    private String content;
}
