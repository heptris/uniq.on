package com.ssafy.uniqon.domain.startup;

import com.ssafy.uniqon.domain.BaseEntity;
import com.ssafy.uniqon.domain.Member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Builder
@AllArgsConstructor
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

    private String businessPlan;

    private String projectPdf;

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
}
