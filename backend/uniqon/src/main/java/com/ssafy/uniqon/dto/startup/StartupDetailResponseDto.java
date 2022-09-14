package com.ssafy.uniqon.dto.startup;

import com.ssafy.uniqon.domain.startup.Startup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StartupDetailResponseDto {

    private Long startupId;
    private String startupName;
    private String title;
    private String description;
    private String nftImageUrl;
    private Integer nftCount; // 발행 개수
    private Integer goalRate; // 달성률
    private String pricePerNft; // 발행가
    private LocalDateTime endDate;
    
    // 사업계획서
    
    // 로드맵


    public StartupDetailResponseDto(Startup startup) {
        this.startupId = startupId;
        this.startupName = startupName;
        this.title = title;
        this.description = description;
        this.nftImageUrl = nftImageUrl;
        this.nftCount = nftCount;
        this.goalRate = goalRate;
        this.pricePerNft = pricePerNft;
        this.endDate = endDate;
    }
}
