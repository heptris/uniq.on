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
    private Double goalRate; // 달성률
    private Double pricePerNft; // 발행가
    private LocalDateTime endDate;
    
    // 사업계획서
    
    // 로드맵


    public StartupDetailResponseDto(Startup startup) {
        this.startupId = startup.getId();
        this.startupName = startup.getStartupName();
        this.title = startup.getTitle();
        this.description = startup.getDescription();
        this.nftImageUrl = startup.getImageNft();
        this.nftCount = startup.getNftCount();
        this.goalRate = new Double(startup.getInvestCount()) / startup.getNftCount() * 100;
        this.pricePerNft = startup.getPricePerNft();
        this.endDate = startup.getEndDate();
    }
}
