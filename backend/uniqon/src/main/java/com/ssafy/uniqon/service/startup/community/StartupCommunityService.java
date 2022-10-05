package com.ssafy.uniqon.service.startup.community;

import com.ssafy.uniqon.domain.member.Member;
import com.ssafy.uniqon.domain.startup.Startup;
import com.ssafy.uniqon.domain.startup.community.StartupCommunity;
import com.ssafy.uniqon.dto.startup.community.StartupCommunityRequestDto;
import com.ssafy.uniqon.dto.startup.community.StartupCommunityRequestModifyDto;
import com.ssafy.uniqon.dto.startup.community.StartupCommunityResponseDetailDto;
import com.ssafy.uniqon.dto.startup.community.StartupCommunityResponseListDto;
import com.ssafy.uniqon.exception.ex.CustomException;
import com.ssafy.uniqon.exception.ex.ErrorCode;
import com.ssafy.uniqon.repository.member.MemberRepository;
import com.ssafy.uniqon.repository.startup.StartupRepository;
import com.ssafy.uniqon.repository.startup.community.StartupCommunityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.ssafy.uniqon.exception.ex.ErrorCode.*;

@Service
@Transactional
@RequiredArgsConstructor
public class StartupCommunityService {

    private final StartupCommunityRepository startupCommunityRepository;
    private final MemberRepository memberRepository;
    private final StartupRepository startupRepository;

    public List<StartupCommunityResponseListDto> communityList(Long startupId){
        List<StartupCommunity> communityList = startupCommunityRepository.findAllByStartupId(startupId);
        List<StartupCommunityResponseListDto> responseDtoList = new ArrayList<>();

        for(StartupCommunity sc : communityList){
            responseDtoList.add(new StartupCommunityResponseListDto(sc.getId(), sc.getStartup().getStartupName(), sc.getTitle(), sc.getMember().getNickname(), sc.getHit(), sc.getCommunityCommentList().size(), sc.getCreatedDate()));
        }

        return responseDtoList;
    }

    public void communityWrite(Long startupId, StartupCommunityRequestDto requestDto){

        Member member = new Member();
        memberRepository.save(member);
        Startup startup = startupRepository.findById(startupId).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
        StartupCommunity startupCommunity =
                StartupCommunity.createStartupCommunity(requestDto.getTitle(), requestDto.getContent(), member, startup);
        startupCommunityRepository.save(startupCommunity);
    }

    public void communityModify(Long startupId, Long communityId, StartupCommunityRequestModifyDto requestDto){
        StartupCommunity startupCommunity = startupCommunityRepository.findById(communityId).orElseThrow(() -> new CustomException(COMMUNITY_NOT_FOUND));
        startupCommunity.changePost(requestDto.getTitle(), requestDto.getContent());
    }

    public void communityDelete(Long startupId, Long communityId){
        startupCommunityRepository.deleteById(communityId);
    }

    public StartupCommunityResponseDetailDto communityDetail(Long communityId){
        Long memberId = 1L;
        StartupCommunity startupCommunity = startupCommunityRepository.findById(communityId).orElseThrow(() -> new CustomException(COMMUNITY_NOT_FOUND));
        startupCommunity.updateHit();

        StartupCommunityResponseDetailDto detailDto = startupCommunityRepository.findDetail(communityId, memberId).orElseThrow(() -> new CustomException(COMMUNITY_NOT_FOUND));
        return detailDto;
    }
}
