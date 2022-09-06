package com.ssafy.uniqon.domain.startup.qna;

import com.ssafy.uniqon.domain.BaseEntity;
import com.ssafy.uniqon.domain.Member.Member;
import com.ssafy.uniqon.domain.startup.community.CommunityComment;
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
public class StartupAnswer extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "startup_answer_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "startup_question_id")
    private StartupQuestion startupQuestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private StartupAnswer parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<StartupAnswer> children = new ArrayList<>();
}
