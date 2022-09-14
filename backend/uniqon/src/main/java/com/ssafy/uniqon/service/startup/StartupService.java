package com.ssafy.uniqon.service.startup;

import com.ssafy.uniqon.domain.member.Member;
import com.ssafy.uniqon.domain.s3.AwsS3;
import com.ssafy.uniqon.domain.startup.EnrollStatus;
import com.ssafy.uniqon.domain.startup.Startup;
import com.ssafy.uniqon.dto.startup.StartupRequestDto;
import com.ssafy.uniqon.exception.ex.CustomException;
import com.ssafy.uniqon.dto.startup.StartupResponseListDto;
import com.ssafy.uniqon.dto.startup.StartupSearchCondition;

import com.ssafy.uniqon.repository.startup.StartupRepository;
import com.ssafy.uniqon.service.s3.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.ssafy.uniqon.domain.startup.EnrollStatus.*;
import static com.ssafy.uniqon.exception.ex.ErrorCode.FILE_UPLOAD_ERROR;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class StartupService {

    private final StartupRepository startupRepository;
    private final AwsS3Service awsS3Service;

    @Transactional
    public Long 투자등록(Startup startup) {
        startupRepository.save(startup);
        return startup.getId();
    }

    @Transactional
    public Long investRegist(Long memberId, StartupRequestDto startupRequestDto, MultipartFile business_plan
    , MultipartFile nft_image, MultipartFile road_map) {
        Member member = new Member();
        member.changeId(memberId);

        Startup startup = Startup.builder()
           //     .description(startupRequestDto.getDescription())
                .startupName(startupRequestDto.getStartupName())
//                .managerEmail(startupRequestDto.getManagerEmail())
//                .managerName(startupRequestDto.getManagerName())
//                .managerNumber(startupRequestDto.getManagerNumber())
//                .goalPrice(startupRequestDto.getGoalPrice())
//                .endDate(startupRequestDto.getEndDate())
//                .discordUrl(startupRequestDto.getDiscordUrl())
//                .title(startupRequestDto.getTitle())
//                .nftCount(startupRequestDto.getNftCount())
                .member(member)
                .investCount(0)
                .isFinished(false)
                .enrollStatus(PENDING)
                .isGoal(false)
                .curTotalPrice(0)
//                .pricePerNft(startupRequestDto.getGoalPrice() / startupRequestDto.getNftCount())
                .build();


        Startup savedStartup = startupRepository.save(startup);
        AwsS3 awsS3 = new AwsS3();
        if (business_plan != null) {
            try {
                awsS3 = awsS3Service.upload(business_plan, "startup");
            }catch (IOException e){
                throw new CustomException(FILE_UPLOAD_ERROR);
            }

            String business_plan_url = awsS3.getPath();
            savedStartup.changeBusinessPlan(business_plan_url);
        }

        if (nft_image != null) {
            try {
                awsS3 = awsS3Service.upload(nft_image, "startup");
            }catch (IOException e){
                throw new CustomException(FILE_UPLOAD_ERROR);
            }

            String nft_image_url = awsS3.getPath();
            savedStartup.changeImageNft(nft_image_url);
        }
        return savedStartup.getId();
    }

    public Page<StartupResponseListDto> startupList(StartupSearchCondition condition, Pageable pageable){
        return startupRepository.search(condition, pageable);
    }
}
