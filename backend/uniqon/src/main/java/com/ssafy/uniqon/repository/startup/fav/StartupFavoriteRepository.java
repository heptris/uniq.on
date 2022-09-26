package com.ssafy.uniqon.repository.startup.fav;

import com.ssafy.uniqon.domain.startup.StartupFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StartupFavoriteRepository extends JpaRepository<StartupFavorite, Long> {

    @Query(value = "select sf from StartupFavorite sf where sf.member.id = :memberId and sf.startup.id = :startupId")
    Optional<StartupFavorite> findByMemberIdAndStartupId(@Param("memberId") Long memberId, @Param("startupId") Long startupId);
}
