package com.ssafy.uniqon.domain.member;

import com.ssafy.uniqon.domain.BaseEntity;
import com.ssafy.uniqon.domain.invest.Invest_history;
import com.ssafy.uniqon.domain.startup.Startup;
import com.ssafy.uniqon.domain.startup.StartupFavorite;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Entity
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Startup> startupList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Invest_history> investHistoryList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<StartupFavorite> startupFavoriteList = new ArrayList<>();

//    @Column(nullable = false)
//    private String name;

    @Column(nullable = false, unique = true)
    private String walletAddress;

    @Column(nullable = false)
    private String password;

    @Column(unique = true)
    private String nickname;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    @Column(nullable = false)
    private String profileImage;

    public void changeId(Long memberId) {
        this.id = memberId;
    }

    public void updateMember(String nickname){
        this.nickname = nickname;
    }

    public void changeProfileImage(String profileImage){
        this.profileImage = profileImage;
    }

}
