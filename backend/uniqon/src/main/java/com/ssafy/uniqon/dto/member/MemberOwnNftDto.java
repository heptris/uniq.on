package com.ssafy.uniqon.dto.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberOwnNftDto {

    private Long startupId;
    private String startupName;
    private Double price;
    private String nftDescription;
    private String metaData;
    private String nftImage;

    public MemberOwnNftDto(Long startupId, String startupName, Double price, String nftDescription, String metaData) {
        this.startupId = startupId;
        this.startupName = startupName;
        this.price = price;
        this.nftDescription = nftDescription;
        this.metaData = metaData;
    }
}
