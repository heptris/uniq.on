package com.ssafy.uniqon.dto.startup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StartupResponseListDto {
    private String startupName;
    private String title;
    private LocalDateTime endDate;
    private String nftImageUrl;
}
