package com.ssafy.uniqon.repository.startup.qna;

import com.ssafy.uniqon.domain.startup.Startup;
import com.ssafy.uniqon.domain.startup.qna.StartupQuestion;
import com.ssafy.uniqon.dto.startup.qna.StartupQuestionResDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StartupQuestionRepository extends JpaRepository<StartupQuestion, Long> {

    List<StartupQuestion> findAllByStartupId(Long startupId);

}
