package com.ssafy.uniqon.repository.member;

import com.ssafy.uniqon.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
