package com.ssafy.uniqon.service.startup;

import com.ssafy.uniqon.dto.startup.StartupRequestDto;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@Rollback(value = false)
@Transactional
@SpringBootTest
class StartupServiceTest {

    @Autowired
    private StartupService startupService;

    @Test
    public void 투자등록() {
        StartupRequestDto startupRequestDto = StartupRequestDto.builder()
                .description("스타트업 투자 소개글")
                .discordUrl("스타트업 test discord url")
                .endDate(LocalDateTime.now().plusDays(2))
                .managerEmail("test@naver.com")
                .managerNumber("010-1234-5678")
                .managerName("test")
                .startupName("startupTest")
                .goalPrice(1000)
                .nftCount(20)
                .title("스타트업 test title")
                .build();

    }

    @Test
    public void test() {
        File file = new File("C:/Users/SSAFY/Desktop/220713_SSAFY개발환경뿌수기_서울당현아.pdf"); //어떤 경로의 어떤 파일을 읽을것인지 설정하고 해당 파일객체 생성
        try {
            PDDocument document = PDDocument.load(file);
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            // 0 페이지를 JPG파일로 저장
            BufferedImage imageObj = pdfRenderer.renderImageWithDPI(0, 100, ImageType.RGB);
            File outputfile = new File("C:/Users/SSAFY/Desktop/test.jpg");
            ImageIO.write(imageObj, "jpg", outputfile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}