package com.ssafy.uniqon.service.startup.community;

import com.ssafy.uniqon.domain.Member.Member;
import com.ssafy.uniqon.domain.startup.Startup;
import com.ssafy.uniqon.domain.startup.community.StartupCommunity;
import com.ssafy.uniqon.dto.startup.community.StartupCommunityRequestDto;
import com.ssafy.uniqon.dto.startup.community.StartupCommunityRequestModifyDto;
import com.ssafy.uniqon.dto.startup.community.StartupCommunityResponseDetailDto;
import com.ssafy.uniqon.dto.startup.community.StartupCommunityResponseListDto;
import com.ssafy.uniqon.exception.ex.CustomException;
import com.ssafy.uniqon.exception.ex.ErrorCode;
import com.ssafy.uniqon.repository.startup.community.CommunityCommentRepository;
import com.ssafy.uniqon.repository.startup.community.StartupCommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StartupCommunityService {

    private final StartupCommunityRepository startupCommunityRepository;

    public List<StartupCommunityResponseListDto> communityList(Long startupId){
        List<StartupCommunity> communityList = startupCommunityRepository.findAll();
        List<StartupCommunityResponseListDto> responseDtoList = new ArrayList<>();

        for(StartupCommunity sc : communityList){
            responseDtoList.add(new StartupCommunityResponseListDto(sc.getTitle(), sc.getMember().getNickname(), sc.getCommunityCommentList().size(), null));
        }

        return responseDtoList;
    }

    public void communityWrite(Long startupId, StartupCommunityRequestDto requestDto){
        Member member = new Member();
        Startup startup = new Startup();
        StartupCommunity startupCommunity =
                StartupCommunity.createStartupCommunity(requestDto.getTitle(), requestDto.getContent(), member, startup);
        startupCommunityRepository.save(startupCommunity);
    }

    public void communityModify(Long startupId, Long communityId, StartupCommunityRequestModifyDto requestDto){
        StartupCommunity startupCommunity = startupCommunityRepository.findById(requestDto.getStartupCommunityId()).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        startupCommunity.changePost(requestDto.getTitle(), requestDto.getContent());
    }

    public void communityDelete(Long startupId, Long communityId){
        startupCommunityRepository.deleteById(communityId);
    }

    public StartupCommunityResponseDetailDto communityDetail(Long communityId){
        StartupCommunity startupCommunity = startupCommunityRepository.findById(communityId).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND));
        StartupCommunityResponseDetailDto detailDto = new StartupCommunityResponseDetailDto(
                startupCommunity.getTitle(),
                startupCommunity.getContent(),
                startupCommunity.getMember().getNickname(),
                startupCommunity.getCommunityCommentList(),
                null
        );
        return detailDto;
    }
}
