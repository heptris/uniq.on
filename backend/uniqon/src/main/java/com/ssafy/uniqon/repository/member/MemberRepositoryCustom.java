package com.ssafy.uniqon.repository.member;

import com.ssafy.uniqon.dto.member.MemberFavStartupDto;
import com.ssafy.uniqon.dto.member.MemberInvestedStartupDto;
import com.ssafy.uniqon.dto.member.MemberOwnNftDto;
import com.ssafy.uniqon.dto.member.StartupInvestedListDto;

import java.util.List;

public interface MemberRepositoryCustom {
    List<MemberFavStartupDto> findFavStartup(Long memberId);

    List<MemberInvestedStartupDto> findInvestedStartup(Long memberId);

    List<StartupInvestedListDto> findStartupInvestedList(Long memberId);

    List<MemberOwnNftDto> findOwnNftList(Long memberId);
}
