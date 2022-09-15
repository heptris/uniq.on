package com.ssafy.uniqon.domain.startup;

import com.ssafy.uniqon.domain.BaseEntity;
import com.ssafy.uniqon.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private String startupName;

    private String managerName; // 담당자 이름
    private String managerEmail; // 담당자 이메일
    private String managerNumber; // 담당자 연락처

    @Lob
    private String businessPlan;
    @Lob
    private String businessPlanImg;

    private String roadMap;

    private String title;

    private String description;

    private LocalDateTime endDate;

    private String imageNft;

    private int goalPrice;

    private double curTotalPrice;

    private int nftCount;

    private int investCount;

    private double pricePerNft;

    private boolean isFinished;

    private boolean isGoal;

    private String discordUrl;

    private String rejectReason;

    @Enumerated(EnumType.STRING)
    private EnrollStatus enrollStatus;

    public void changeId(Long startupId) {
        this.id = startupId;
    }

    public void changeBusinessPlan(String businessPlan) {
        this.businessPlan = businessPlan;
    }

    public void changeBusinessPlanImg(String businessPlanImg){
        this.businessPlanImg = businessPlanImg;
    }

    public void changeImageNft(String imageNft) {
        this.imageNft = imageNft;
    }
    public void changeRoadMap(String roadMap){
        this.roadMap = roadMap;
    }
}
