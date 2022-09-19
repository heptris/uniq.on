package com.ssafy.uniqon.dto.startup;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    private Integer nftCount; // 발행 개수
    private Double goalRate; // 달성률
    private Double pricePerNft; // 발행가
    @JsonFormat(pattern = "yyyy-MM-dd HH")
    private LocalDateTime endDate;

    // 사업계획서
    private String businessPlan;
    private String businessPlanImg;

    // 로드맵
    private String roadMap;

    // nft image
    private String imageNft;


    public StartupDetailResponseDto(Startup startup) {
        this.startupId = startup.getId();
        this.startupName = startup.getStartupName();
        this.title = startup.getTitle();
        this.description = startup.getDescription();
        this.nftCount = startup.getNftCount();
        this.goalRate = new Double(startup.getInvestCount()) / startup.getNftCount() * 100;
        this.pricePerNft = startup.getPricePerNft();
        this.endDate = startup.getEndDate();
        this.businessPlan = startup.getBusinessPlan();
        this.businessPlanImg = startup.getBusinessPlanImg();
        this.roadMap = startup.getRoadMap();
        this.imageNft = startup.getImageNft();
    }
}
