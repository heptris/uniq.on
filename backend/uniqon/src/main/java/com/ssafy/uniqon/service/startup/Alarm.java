package com.ssafy.uniqon.service.startup;

import com.ssafy.uniqon.domain.Member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
public class Alarm {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "alarm_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;

    private boolean isRead;
}
