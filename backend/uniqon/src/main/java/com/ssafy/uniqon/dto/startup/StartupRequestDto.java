package com.ssafy.uniqon.dto.startup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class StartupRequestDto {

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH")
    private LocalDateTime dueDate; // 투자 마감일
    private String discordUrl;
    private String description; // 회사 소개글
    private String title; // 제목

    private Integer nftTargetCount; // 토큰 목표 발행 개수
    private Double nftPrice;    // NFT 1개당 가격
    private String nftDescription;
    private String tokenURI; // ipfs uri : metadata
}
