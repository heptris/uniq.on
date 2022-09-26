package com.ssafy.uniqon.repository.mypage;

import com.ssafy.uniqon.domain.alarm.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    List<Alarm> findAllByMemberId(Long memberId);

    List<Alarm> findAllByMemberIdAndIsRead(Long memberId, boolean isRead);

}
