package com.ssafy.uniqon.repository.startup;

import com.ssafy.uniqon.domain.startup.Startup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StartupRepository extends JpaRepository<Startup, Long>, StartupRepositoryCustom {

}
