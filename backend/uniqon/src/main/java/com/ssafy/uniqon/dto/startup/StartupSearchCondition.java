package com.ssafy.uniqon.dto.startup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StartupSearchCondition {
    // 제목, 기업 이름
    private String title;
    private String startupName;
}
