package com.ssafy.uniqon.repository.startup;

import com.ssafy.uniqon.domain.member.Member;
import com.ssafy.uniqon.domain.startup.Startup;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.*;

@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
class StartupRepositoryTest {

    @Autowired
    private StartupRepository startupRepository;

    @DisplayName(value = "투자 등록")
    @Test
    public void 투자_등록() throws Exception {

        Member member = new Member();

        Startup startup = Startup.builder()
                .member(member)
                .startupName("startupName")
                .planPaper("planPaper")
                .planPaperImg("planPaperImg")
                .roadMap("roadMap")
                .title("title")
                .description("description")
                .dueDate(LocalDateTime.now().plusDays(1))
                .nftImage("nftImage")
                .nftTargetCount(10)
                .nftReserveCount(5)
                .nftPrice(2)
                .isFinished(Boolean.FALSE)
                .isGoal(Boolean.FALSE)
                .discordUrl("discordUrl")
                .build();

        Startup savedStartup = startupRepository.save(startup);

        assertThat(startup.getDescription()).isEqualTo(savedStartup.getDescription());
        assertThat(startup.getStartupName()).isEqualTo(savedStartup.getStartupName());

    }

    @DisplayName(value = "투자중인 스타트업 리스트 조회")
    @Test
    public void 투자중인_스타트업_리스트_조회() throws Exception {
        List<Startup> byInvestingStartupList = startupRepository.findByInvestingStartupList();
        byInvestingStartupList.forEach(startup -> {
            System.out.println(startup.getId());
        });
    }
}