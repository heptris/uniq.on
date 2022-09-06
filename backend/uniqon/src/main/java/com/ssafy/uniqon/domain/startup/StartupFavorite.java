package com.ssafy.uniqon.domain.startup;

import com.ssafy.uniqon.domain.BaseEntity;
import com.ssafy.uniqon.domain.Member.Member;

import javax.persistence.*;

@Entity
public class StartupFavorite extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "startup_favorite_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "startup_id")
    private Startup startup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

}
