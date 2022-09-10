package com.ssafy.uniqon.repository.startup.community;

import com.ssafy.uniqon.domain.startup.Startup;
import com.ssafy.uniqon.domain.startup.community.CommunityComment;
import com.ssafy.uniqon.domain.startup.community.StartupCommunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StartupCommunityRepository extends JpaRepository<StartupCommunity, Long>, StartupCommunityRepositoryCustom {

    List<StartupCommunity> findAllByStartupId(Long startupId);
}
