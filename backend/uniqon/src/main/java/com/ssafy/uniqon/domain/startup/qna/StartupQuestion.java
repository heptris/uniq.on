package com.ssafy.uniqon.domain.startup.qna;

import com.ssafy.uniqon.domain.BaseEntity;
import com.ssafy.uniqon.domain.member.Member;
import com.ssafy.uniqon.domain.startup.Startup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Entity
public class StartupQuestion extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "startup_question_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "startup_id")
    private Startup startup;

    @Column(nullable = false)
    private String question;

    @OneToMany(mappedBy = "startupQuestion")
    private List<StartupAnswer> startupAnswerList = new ArrayList<>();
}
