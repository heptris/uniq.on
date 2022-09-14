package com.ssafy.uniqon.dto.startup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StartupSearchCondition {
    // 제목, 기업 이름
    private String title;
    private String startupName;
}
