package com.ssafy.uniqon.dto.startup;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StartupResponseListDto {

    private Long startupId;
    private String startupName;
    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dueDate;

    private Integer nftTargetCount;

    private Integer nftReserveCount;

    private String profileImage;
    private String nftImage;

}
