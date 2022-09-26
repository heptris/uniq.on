package com.ssafy.uniqon.service.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Rollback(value = false)
@Transactional
@SpringBootTest
class MypageServiceTest {

    @Autowired
    private MypageService mypageService;

}