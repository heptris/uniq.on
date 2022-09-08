package com.ssafy.uniqon.repository.startup.qna;

import com.ssafy.uniqon.domain.startup.qna.StartupAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StartupAnswerRepository extends JpaRepository<StartupAnswer, Long> {
}
