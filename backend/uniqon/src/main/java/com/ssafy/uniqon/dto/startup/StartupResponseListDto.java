package com.ssafy.uniqon.dto.startup;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "yyyy-MM-dd HH")
    private LocalDateTime endDate;
    private String nftImageUrl;
}
