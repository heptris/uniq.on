package com.ssafy.uniqon.repository.alarm;

import com.ssafy.uniqon.domain.alarm.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    List<Alarm> findAllByMemberIdOrderByIdDesc(Long memberId);

    List<Alarm> findAllByMemberIdAndIsReadOrderByIdDesc(Long memberId, boolean isRead);

}
