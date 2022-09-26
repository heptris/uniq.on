package com.ssafy.uniqon.repository.startup;

import com.ssafy.uniqon.domain.startup.Startup;
import com.ssafy.uniqon.dto.startup.StartupResponseListDto;
import com.ssafy.uniqon.dto.startup.StartupSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StartupRepositoryCustom {
    Page<StartupResponseListDto> search(StartupSearchCondition condition, Pageable pageable);

    List<Startup> findByInvestingStartupList();

}
