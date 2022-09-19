package com.ssafy.uniqon.repository.startup;

import com.ssafy.uniqon.dto.startup.StartupResponseListDto;
import com.ssafy.uniqon.dto.startup.StartupSearchCondition;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StartupRepositoryImplTest {

    @Autowired
    private StartupRepository startupRepository;

    @Test
    public void 스타트업투자조회(){
//        StartupSearchCondition condition = new StartupSearchCondition("", "");
//        Pageable pageable = Pageable.ofSize(2).withPage(0);
//        Page<StartupResponseListDto> search = startupRepository.search(condition, pageable);
//        Assertions.assertThat(search.getContent().size()).isEqualTo(2);
    }
}