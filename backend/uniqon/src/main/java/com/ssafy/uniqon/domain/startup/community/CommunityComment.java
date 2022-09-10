package com.ssafy.uniqon.domain.startup.community;

import com.ssafy.uniqon.domain.BaseEntity;
import com.ssafy.uniqon.domain.member.Member;
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
public class CommunityComment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_comment_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private CommunityComment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<CommunityComment> children = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "startup_community_id")
    private StartupCommunity startupCommunity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    public static CommunityComment createCommunityComment(String content, StartupCommunity startupCommunity){
        CommunityComment communityComment = CommunityComment.builder()
                .content(content)
                .build();

        communityComment.addStartupCommunity(startupCommunity);
        return communityComment;
    }

    public void addStartupCommunity(StartupCommunity startupCommunity){
        this.startupCommunity = startupCommunity;
    }

    public void changeContent(String content){
        this.content = content;
    }
}
