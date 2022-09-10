package com.ssafy.uniqon.repository.startup.community;

import com.ssafy.uniqon.dto.startup.community.StartupCommunityResponseDetailDto;

import java.util.Optional;

public interface StartupCommunityRepositoryCustom {
    Optional<StartupCommunityResponseDetailDto> findDetail(Long startupCommunityId, Long memberId);
}
