package com.ssafy.uniqon.domain.startup.community;

import com.ssafy.uniqon.domain.BaseEntity;
import com.ssafy.uniqon.domain.Member.Member;
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
public class StartupCommunity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "startup_community_id")
    private Long id;

    private String title;

    @Lob
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "startup_id")
    private Startup startup;

    @OneToMany(mappedBy = "startupCommunity")
    private List<CommunityComment> communityCommentList = new ArrayList<>();

    public static StartupCommunity createStartupCommunity(String title, String content, Member member, Startup startup){
        StartupCommunity startupCommunity = StartupCommunity.builder()
                .title(title)
                .content(content)
                .build();
        startupCommunity.addMember(member);
        startupCommunity.addStartup(startup);
        return startupCommunity;
    }

    public void addMember(Member member){
        this.member = member;
    }

    public void addStartup(Startup startup){
        this.startup = startup;
    }

    public void changePost(String title, String content){
        this.title = title;
        this.content = content;
    }

}
