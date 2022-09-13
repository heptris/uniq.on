package com.ssafy.uniqon.repository.startup.qna;

import com.ssafy.uniqon.domain.startup.Startup;
import com.ssafy.uniqon.domain.startup.qna.StartupQuestion;
import com.ssafy.uniqon.dto.startup.qna.StartupQuestionResDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StartupQuestionRepository extends JpaRepository<StartupQuestion, Long>, StartupQuestionRepositoryCustom {

    List<StartupQuestion> findAllByStartupId(Long startupId);

    @Query("select count(sq.id) > 0 from StartupQuestion sq where sq.startup.id = :startupId and sq.id < :cursorId")
    Boolean existsByIdLessThan(@Param("startupId") Long startupId, @Param("cursorId") Long cursorId);
}
