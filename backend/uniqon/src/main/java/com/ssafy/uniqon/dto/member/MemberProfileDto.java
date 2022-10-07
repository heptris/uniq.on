package com.ssafy.uniqon.dto.member;

import com.ssafy.uniqon.domain.member.MemberType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberProfileDto {

    private Long memberId;
    private String walletAddress;
    private String nickName;
    private String email;
    private String profileImage;
    private MemberType memberType;

}
