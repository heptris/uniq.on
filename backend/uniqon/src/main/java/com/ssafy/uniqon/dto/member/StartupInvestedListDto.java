package com.ssafy.uniqon.dto.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.uniqon.domain.invest.InvestStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StartupInvestedListDto {

    private Long memberId;
    private Long startupId;
    private String startupName;
    private String title;
    private String description;
    private Integer nftCount; // 발행 개수
    private Integer investCount;
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

    private InvestStatus investStatus;
}
