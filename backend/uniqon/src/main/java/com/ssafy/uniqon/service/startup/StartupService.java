package com.ssafy.uniqon.service.startup;
import com.ssafy.uniqon.domain.member.Member;
import com.ssafy.uniqon.domain.s3.AwsS3;
import com.ssafy.uniqon.domain.startup.EnrollStatus;
import com.ssafy.uniqon.domain.startup.Startup;
import com.ssafy.uniqon.domain.startup.StartupFavorite;
import com.ssafy.uniqon.dto.startup.StartupDetailResponseDto;
import com.ssafy.uniqon.dto.startup.StartupRequestDto;
import com.ssafy.uniqon.exception.ex.CustomException;
import com.ssafy.uniqon.dto.startup.StartupResponseListDto;
import com.ssafy.uniqon.dto.startup.StartupSearchCondition;
import com.ssafy.uniqon.repository.invest.InvestHistoryRepository;
import com.ssafy.uniqon.repository.member.MemberRepository;
import com.ssafy.uniqon.repository.startup.StartupRepository;
import com.ssafy.uniqon.repository.startup.fav.StartupFavoriteRepository;
// import com.ssafy.uniqon.service.ipfs.IpfsService;
import com.ssafy.uniqon.service.s3.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.ssafy.uniqon.domain.startup.EnrollStatus.*;
import static com.ssafy.uniqon.exception.ex.ErrorCode.*;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class StartupService {

    private final StartupRepository startupRepository;
    private final StartupFavoriteRepository startupFavoriteRepository;
    private final InvestHistoryRepository investHistoryRepository;
    private final MemberRepository memberRepository;
    private final AwsS3Service awsS3Service;

    @Transactional
    public Long 투자등록(Startup startup) {
        startupRepository.save(startup);
        return startup.getId();
    }

    @Transactional
    public Long investRegist(Long memberId, StartupRequestDto startupRequestDto, MultipartFile plan_paper
    , MultipartFile nft_image, MultipartFile road_map) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new CustomException(MEMBER_NOT_FOUND)
        );

        Startup startup = Startup.builder()
                .startupName(member.getNickname())
                .title(startupRequestDto.getTitle())
                .description(startupRequestDto.getDescription())
                .dueDate(startupRequestDto.getDueDate())
                .discordUrl(startupRequestDto.getDiscordUrl())
                .member(member)
                .nftPrice(startupRequestDto.getNftPrice())
                .nftReserveCount(0)
                .nftTargetCount(startupRequestDto.getNftTargetCount())
                .nftDescription(startupRequestDto.getNftDescription())
                .isFinished(false)
                .tokenUri(startupRequestDto.getTokenURI())
                .enrollStatus(PENDING)
                .isGoal(false)
                .build();

        BufferedReader in = null;
        StringBuilder sb = new StringBuilder();

        try {
            URL obj = new URL("https://ipfs.io/ipfs/bafyreienvkbfakzzpot56ae7mcipysyuuooygtwha4kqsduek2mxrczki4/metadata.json"); // 호출할 url
            HttpURLConnection con = (HttpURLConnection)obj.openConnection();
            con.setRequestMethod("GET");
            in = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String line;
            while((line = in.readLine()) != null) { // response를 차례대로 출력
                sb.append(line);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(in != null) try { in.close(); } catch(Exception e) { e.printStackTrace(); }
        }

        startup.addMetadata(sb.toString());

        Startup savedStartup = startupRepository.save(startup);
        AwsS3 awsS3 = new AwsS3();
        if (plan_paper != null) {
            try {
                awsS3 = awsS3Service.upload(plan_paper, "startup");
            }catch (IOException e){
                throw new CustomException(FILE_UPLOAD_ERROR);
            }

            String plan_paper_url = awsS3.getPath();
            savedStartup.changePlanPaper(plan_paper_url);

            if("application/pdf".equals(plan_paper.getContentType())) {
                String imgUrl = awsS3Service.pdfToImg(plan_paper);
                savedStartup.changePlanPaperImg(imgUrl);
            }
        }

        if (nft_image != null) {
            try {
                awsS3 = awsS3Service.upload(nft_image, "startup");
            }catch (IOException e){
                throw new CustomException(FILE_UPLOAD_ERROR);
            }

            String nft_image_url = awsS3.getPath();
            savedStartup.changeNftImage(nft_image_url);
        }

        if (road_map != null) {
            try {
                awsS3 = awsS3Service.upload(road_map, "startup");
            }catch (IOException e){
                throw new CustomException(FILE_UPLOAD_ERROR);
            }

            String roadMapUrl = awsS3.getPath();
            savedStartup.changeRoadMap(roadMapUrl);
        }
        return savedStartup.getId();
    }

    public Page<StartupResponseListDto> startupList(StartupSearchCondition condition, Pageable pageable){
        return startupRepository.search(condition, pageable);
    }

    /**
     * 스타트업 상세정보 조회(로그인 상태)
     */
    @Transactional
    public StartupDetailResponseDto startupDetail(Long memberId, Long startupId) {
        System.out.println(memberId + " " +  startupId);
        Startup startup = startupRepository.findById(startupId).orElseThrow(
                () -> new CustomException(STARTUP_NOT_FOUND)
        );
        StartupDetailResponseDto startupDetailResponseDto = new StartupDetailResponseDto(startup);
        Optional<StartupFavorite> startupFavorite = startupFavoriteRepository.findByMemberIdAndStartupId(memberId, startupId);
        if (startupFavorite.isPresent()) {
            startupDetailResponseDto.setIsFav(startupFavorite.get().getIsFav());
        } else {
            startupDetailResponseDto.setIsFav(Boolean.FALSE);
        }
        Member member = new Member();
        member.changeId(memberId);
        startupDetailResponseDto.setIsReserved(investHistoryRepository.existsByMemberAndStartup(member, startup));
        return startupDetailResponseDto;
    }

    /**
     * 스타트업 상세조회(비로그인 상태)
     */
    @Transactional
    public StartupDetailResponseDto startupDetail(Long startupId) {
        Startup startup = startupRepository.findById(startupId).orElseThrow(
                () -> new CustomException(STARTUP_NOT_FOUND)
        );
        StartupDetailResponseDto startupDetailResponseDto = new StartupDetailResponseDto(startup);
        startupDetailResponseDto.setIsFav(Boolean.FALSE);
        return startupDetailResponseDto;
    }
    @Transactional
    public void startupFavoriteToggle(Long memberId, Long startupId) {
        Optional<StartupFavorite> startupFavoriteOptional = startupFavoriteRepository.findByMemberIdAndStartupId(memberId, startupId);
        if (startupFavoriteOptional.isPresent()) {
            StartupFavorite startupFavorite = startupFavoriteOptional.get();
            startupFavorite.toggle();
        } else {
            Member member = new Member();
            member.changeId(memberId);
            Startup startup = new Startup();
            startup.changeId(startupId);
            StartupFavorite startupFavorite = StartupFavorite.builder()
                    .member(member)
                    .startup(startup)
                    .isFav(Boolean.TRUE)
                    .build();
            startupFavoriteRepository.save(startupFavorite);
        }
    }

}
