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
    private String profileImage;
    private String title;
    private String description;

    private Integer nftTargetCount; // 발행 개수

    private Integer nftReserveCount;

    private Double nftPrice; // 발행가
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

//    private InvestStatus investStatus;
}
