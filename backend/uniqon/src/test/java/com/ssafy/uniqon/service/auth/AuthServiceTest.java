package com.ssafy.uniqon.service.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Rollback(value = false)
@Transactional
class AuthServiceTest {

    @Autowired
    AuthService authService;

    @Test
    public void defaultImageTest() throws Exception{
        //given
        String randomImage = authService.getRandomImage();

        //when

        //then
        System.out.println("randomImage = " + randomImage);
     }
}