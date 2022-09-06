package com.ssafy.uniqon.service.startup;

import com.ssafy.uniqon.domain.startup.Startup;
import com.ssafy.uniqon.repository.startup.StartupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class StartupService {

    private final StartupRepository startupRepository;

    @Transactional
    public Long 투자등록(Startup startup) {
        startupRepository.save(startup);
        return startup.getId();
    }

}
