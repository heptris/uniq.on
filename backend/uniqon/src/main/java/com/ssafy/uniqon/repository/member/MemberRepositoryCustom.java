package com.ssafy.uniqon.repository.member;

import com.ssafy.uniqon.dto.member.MemberFavStartupDto;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberFavStartupDto> findFavStartup(Long memberId);
}
