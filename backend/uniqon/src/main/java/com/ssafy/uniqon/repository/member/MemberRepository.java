package com.ssafy.uniqon.repository.member;

import com.ssafy.uniqon.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);
    Optional<Member> findByWalletAddress(String walletAddress);

    boolean existsByNickname(String nickName);

}
