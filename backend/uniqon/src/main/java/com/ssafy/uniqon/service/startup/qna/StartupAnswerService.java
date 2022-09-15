package com.ssafy.uniqon.service.startup.qna;

import com.ssafy.uniqon.domain.member.Member;
import com.ssafy.uniqon.domain.startup.qna.StartupAnswer;
import com.ssafy.uniqon.domain.startup.qna.StartupQuestion;
import com.ssafy.uniqon.dto.startup.qna.AnswerRequestDto;
import com.ssafy.uniqon.dto.startup.qna.AnswerUpdateRequestDto;
import com.ssafy.uniqon.exception.ex.CustomException;
import com.ssafy.uniqon.repository.startup.qna.StartupAnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.ssafy.uniqon.exception.ex.ErrorCode.*;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class StartupAnswerService {

    private final StartupAnswerRepository startupAnswerRepository;

    @Transactional
    public Long 답변저장(Long memberId, Long startupQuestionId, AnswerRequestDto answerRequestDto) {
        Member member = new Member();
        member.changeId(memberId);

        StartupQuestion startupQuestion = new StartupQuestion();
        startupQuestion.changeId(startupQuestionId);

        StartupAnswer parent = null;
        // 자식 댓글일 경우
        if (answerRequestDto.getParentId() != null) {
            StartupAnswer startupAnswer = startupAnswerRepository.findById(answerRequestDto.getParentId())
                    .orElseThrow(() -> new CustomException(ANSWER_NOT_FOUND));
            Long id = startupAnswer.getParent() == null ? startupAnswer.getId() : startupAnswer.getParent().getId();
            parent = new StartupAnswer();
            parent.changeId(id);
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
    public void 답변수정(Long memberId, Long startupAnswerId, AnswerUpdateRequestDto answerUpdateRequestDto) {
        StartupAnswer startupAnswer = startupAnswerRepository.findById(startupAnswerId)
                .orElseThrow(() -> new CustomException(ANSWER_NOT_FOUND));
        if (startupAnswer.getMember().getId().equals(memberId)) {
            startupAnswer.changeAnswer(answerUpdateRequestDto.getAnswer());
        } else {
            throw new CustomException(INVALID_ACCESS_MEMBER);
        }
    }

    @Transactional
    public void 답변삭제(Long memberId, Long startupAnswerId) {
        StartupAnswer startupAnswer = startupAnswerRepository.findById(startupAnswerId)
                .orElseThrow(() -> new CustomException(ANSWER_NOT_FOUND));
        if (startupAnswer.getMember().getId().equals(memberId)) {
            startupAnswerRepository.deleteById(startupAnswerId);
        } else {
            throw new CustomException(INVALID_ACCESS_MEMBER);
        }
    }
}
