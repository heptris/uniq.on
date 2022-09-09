package com.ssafy.uniqon.service.startup.qna;

import com.ssafy.uniqon.domain.member.Member;
import com.ssafy.uniqon.domain.startup.qna.StartupAnswer;
import com.ssafy.uniqon.domain.startup.qna.StartupQuestion;
import com.ssafy.uniqon.dto.startup.qna.AnswerDeleteRequestDto;
import com.ssafy.uniqon.dto.startup.qna.AnswerRequestDto;
import com.ssafy.uniqon.dto.startup.qna.AnswerParentResponseDto;
import com.ssafy.uniqon.dto.startup.qna.AnswerUpdateRequestDto;
import com.ssafy.uniqon.exception.ex.CustomException;
import com.ssafy.uniqon.exception.ex.ErrorCode;
import com.ssafy.uniqon.repository.startup.qna.StartupAnswerRepository;
import com.ssafy.uniqon.repository.startup.qna.StartupQuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.ssafy.uniqon.exception.ex.ErrorCode.*;

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

//    public List<AnswerParentResponseDto> 댓글조회(Long startupQuestionId) {
//        return startupAnswerRepository.findAnswerParentResponseDtoList(startupQuestionId);
//    }

    @Transactional
    public void 답변수정(AnswerUpdateRequestDto answerUpdateRequestDto) {
        StartupAnswer startupAnswer = startupAnswerRepository.findById(answerUpdateRequestDto.getStartupAnswerId())
                .orElseThrow(() -> new CustomException(ANSWER_NOT_FOUND));
        startupAnswer.changeAnswer(answerUpdateRequestDto.getAnswer());
    }

    @Transactional
    public void 답변삭제(AnswerDeleteRequestDto answerDeleteRequestDto) {
        startupAnswerRepository.deleteById(answerDeleteRequestDto.getStartupAnswerId());
    }
}
