package com.ssafy.uniqon.service.startup;

import com.ssafy.uniqon.domain.member.Member;
import com.ssafy.uniqon.domain.startup.Startup;
import com.ssafy.uniqon.domain.startup.StartupFavorite;
import com.ssafy.uniqon.dto.startup.StartupDetailResponseDto;
import com.ssafy.uniqon.dto.startup.StartupRequestDto;
import com.ssafy.uniqon.repository.member.MemberRepository;
import com.ssafy.uniqon.repository.startup.StartupRepository;
import com.ssafy.uniqon.repository.startup.fav.StartupFavoriteRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class StartupServiceTest {

    @Mock
    private StartupRepository startupRepository;

    @Mock
    private MemberRepository memberRepository;
    @Mock
    private StartupFavoriteRepository startupFavoriteRepository;

    @InjectMocks
    private StartupService startupService;

    @Test
    public void 투자상세정보() {
        //given
        given(startupRepository.findById(anyLong())).willReturn(Optional.ofNullable(Startup.builder().id(1L)
                .member(Member.builder().profileImage("profileImage").build()).build()));
        given(startupFavoriteRepository.findByMemberIdAndStartupId(anyLong(), anyLong()))
                .willReturn(Optional.of(StartupFavorite.builder().build()));

        //when
        StartupDetailResponseDto startupDetailResponseDto = startupService.startupDetail(1L, 1L);

        //then
        assertThat(startupDetailResponseDto.getStartupId()).isEqualTo(1L);
    }


}