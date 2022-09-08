package com.ssafy.uniqon.repository.startup.community;

import com.ssafy.uniqon.domain.startup.community.CommunityComment;
import com.ssafy.uniqon.domain.startup.community.StartupCommunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommunityCommentRepository extends JpaRepository<CommunityComment, Long> {

//    @Query("select count(cc.startupCommunity) from CommunityComment cc group by cc.startupCommunity")
//    List<Integer> commentsCount();

}
