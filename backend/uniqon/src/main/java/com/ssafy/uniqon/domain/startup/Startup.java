package com.ssafy.uniqon.domain.startup;

import com.ssafy.uniqon.domain.BaseEntity;
import com.ssafy.uniqon.domain.invest.Invest_history;
import com.ssafy.uniqon.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Entity
public class Startup extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "startup_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder.Default
    @OneToMany(mappedBy = "startup", cascade = CascadeType.ALL)
    private List<Invest_history> investHistoryList = new ArrayList<>();

    private String startupName;
    @Lob
    private String planPaper;
    @Lob
    private String planPaperImg;
    private String roadMap;

    private String title;

    private String description;

    private LocalDateTime dueDate;
    private String nftImage;

    private Integer nftTargetCount;
    private Integer nftReserveCount;

    private double nftPrice;

    private String nftDescription;
    private Boolean isFinished;
    private Boolean isGoal;

    private String discordUrl;

    private String rejectReason;

    @Enumerated(EnumType.STRING)
    private EnrollStatus enrollStatus;

    public void changeId(Long startupId) {
        this.id = startupId;
    }
    public void changeNftImage(String nftImage) {
        this.nftImage = nftImage;
    }
    public void changeRoadMap(String roadMap){
        this.roadMap = roadMap;
    }
    public void nftReserveCountIncrement() {
        this.nftReserveCount += 1;
    }
    public void changeIsGoal() {
        this.isGoal = Boolean.TRUE;
    }
    public void changeIsFinish() {
        this.isFinished = Boolean.TRUE;
    }

    public void changePlanPaper(String plan_paper_url) {
        this.planPaper = plan_paper_url;
    }

    public void changePlanPaperImg(String imgUrl) {
        this.planPaperImg = imgUrl;
    }
}
