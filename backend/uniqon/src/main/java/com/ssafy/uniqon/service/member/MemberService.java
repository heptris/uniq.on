package com.ssafy.uniqon.service.member;

import com.ssafy.uniqon.domain.member.Member;
import com.ssafy.uniqon.domain.s3.AwsS3;
import com.ssafy.uniqon.dto.member.*;
import com.ssafy.uniqon.exception.ex.CustomException;
import com.ssafy.uniqon.exception.ex.ErrorCode;
import com.ssafy.uniqon.repository.member.MemberRepository;
import com.ssafy.uniqon.service.s3.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.ssafy.uniqon.exception.ex.ErrorCode.FILE_UPLOAD_ERROR;
import static com.ssafy.uniqon.exception.ex.ErrorCode.MEMBER_NOT_FOUND;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final AwsS3Service awsS3Service;


    @Transactional
    public boolean existsByNickname(String nickName){
        return memberRepository.existsByNickname(nickName);
    }

    @Transactional
    public void memberUpdate(Long memberId, MemberUpdateDto memberUpdateDto, MultipartFile multipartFile){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

        if (multipartFile != null) {
            AwsS3 awsS3 = new AwsS3();
            try {
                awsS3 = awsS3Service.upload(multipartFile, "member");
            } catch (IOException e) {
                throw new CustomException(FILE_UPLOAD_ERROR);
            }

            String url = awsS3.getPath();
            member.changeProfileImage(url);
        }
        member.updateMember(memberUpdateDto.getNickName());
    }

    @Transactional
    public void memberDelete(Long memberId){
        memberRepository.deleteById(memberId);
    }

    public MemberProfileDto memberDetail(Long memberId){
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));

        return MemberProfileDto.builder()
                .walletAddress(member.getWalletAddress())
                .nickName(member.getNickname())
                .email(member.getEmail())
                .profileImage(member.getProfileImage())
                .memberType(member.getMemberType())
                .build();
    }

    public List<MemberFavStartupDto> findMemberFavStartup(Long memberId){
        return memberRepository.findFavStartup(memberId);
    }

    public List<MemberInvestedStartupDto> findInvestedStartup(Long memberId){
        return memberRepository.findInvestedStartup(memberId);
    }

    public List<StartupInvestedListDto> findStartupInvestedList(Long memberId){
        return memberRepository.findStartupInvestedList(memberId);
    }


}
