package com.ssafy.uniqon.service.mypage;

import com.ssafy.uniqon.repository.mypage.AlarmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MypageService {

    private final AlarmRepository alarmRepository;

//    @Transactional
//    public

}
