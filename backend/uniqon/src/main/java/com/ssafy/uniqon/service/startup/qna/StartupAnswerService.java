package com.ssafy.uniqon.service.startup.qna;

import com.ssafy.uniqon.domain.Member.Member;
import com.ssafy.uniqon.domain.startup.qna.StartupAnswer;
import com.ssafy.uniqon.domain.startup.qna.StartupQuestion;
import com.ssafy.uniqon.dto.startup.qna.AnswerRequestDto;
import com.ssafy.uniqon.dto.startup.qna.AnswerResponseDto;
import com.ssafy.uniqon.repository.startup.qna.StartupAnswerRepository;
import com.ssafy.uniqon.repository.startup.qna.StartupQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class StartupAnswerService {

    private final StartupAnswerRepository startupAnswerRepository;

    @Transactional
    public Long 답변저장(Long memberId, AnswerRequestDto answerRequestDto) {
        Member member = new Member();
        member.changeId(memberId);

        StartupQuestion startupQuestion = new StartupQuestion();
        startupQuestion.changeId(answerRequestDto.getStartupQuestionId());

        StartupAnswer parent = null;
        // 자식 댓글일 경우
        if (answerRequestDto.getParentId() != null) {
            parent = new StartupAnswer();
            parent.changeId(answerRequestDto.getParentId());
        }

        StartupAnswer startupAnswer = StartupAnswer.builder()
                .answer(answerRequestDto.getAnswer())
                .member(member)
                .startupQuestion(startupQuestion)
                .build();

        if (null != parent) {
            startupAnswer.updateParent(parent);
        }

        startupAnswerRepository.save(startupAnswer);
        return startupAnswer.getId();
    }

}
