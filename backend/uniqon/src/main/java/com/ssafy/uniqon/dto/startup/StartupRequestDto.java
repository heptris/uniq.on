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

    private String startupName;
    private String managerName;
    private String managerEmail;
    private String managerNumber;

    private Double goalPrice; // 희망 모집 금액
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH")
    private LocalDateTime endDate; // 투자 마감일
    private Integer nftCount; // 토큰 발행 개수
    private String discordUrl;
    private String description; // 회사 소개글
    private String title; // 제목

}
