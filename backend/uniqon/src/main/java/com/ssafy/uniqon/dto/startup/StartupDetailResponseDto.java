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
    private Integer nftTargetCount;
    private Integer nftReserveCount; // 투자 예약 수
    private Double nftPrice;
    @JsonFormat(pattern = "yyyy-MM-dd HH")
    private LocalDateTime dueDate;

    // 사업계획서
    private String planPaper;

    private String planPaperImg;

    // 로드맵
    private String roadMap;

    // nft image
    private String nftImage;

    private String nftDescription;

    private Boolean isFav;

    public StartupDetailResponseDto(Startup startup) {
        this.startupId = startup.getId();
        this.startupName = startup.getStartupName();
        this.title = startup.getTitle();
        this.description = startup.getDescription();
        this.roadMap = startup.getRoadMap();
        this.planPaper = startup.getPlanPaper();
        this.planPaperImg = startup.getPlanPaperImg();
        this.nftTargetCount = startup.getNftTargetCount();
        this.nftReserveCount = startup.getNftReserveCount();
        this.nftDescription = startup.getNftDescription();
        this.nftImage = startup.getNftImage();
        this.nftPrice = startup.getNftPrice();
        this.dueDate = startup.getDueDate();
    }
}
