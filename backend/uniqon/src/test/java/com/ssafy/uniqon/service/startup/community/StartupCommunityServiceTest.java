package com.ssafy.uniqon.service.startup.community;

import com.ssafy.uniqon.dto.startup.community.StartupCommunityRequestDto;
import com.ssafy.uniqon.dto.startup.community.StartupCommunityRequestModifyDto;
import com.ssafy.uniqon.dto.startup.community.StartupCommunityResponseDetailDto;
import com.ssafy.uniqon.dto.startup.community.StartupCommunityResponseListDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
//@Rollback(value = false)
class StartupCommunityServiceTest {

    @Autowired
    private StartupCommunityService startupCommunityService;

    @Test
    public void 글목록조회(){
        List<StartupCommunityResponseListDto> communityList = startupCommunityService.communityList(1L);
        assertThat(communityList.get(0).getTitle()).isEqualTo("Title Test");
        assertThat(communityList.get(0).getCommentsCount()).isEqualTo(3);
    }

//    @Test
//    public void 글등록(){
//        StartupCommunityRequestDto requestDto = StartupCommunityRequestDto.builder()
//                .title("글 등록 Title Test")
//                .content("글 등록 Content Test")
//                .build();
//        startupCommunityService.communityWrite(1L, requestDto);
//
//        assertThat(startupCommunityService.communityList(1L).get(1).getTitle()).isEqualTo("글 등록 Title Test");
//    }

//    @Test
//    public void 글수정(){
//        StartupCommunityRequestModifyDto requestDto = StartupCommunityRequestModifyDto.builder()
//                .title("글 수정 Title Test")
//                .content("글 수정 Content Test")
//                .build();
//        startupCommunityService.communityModify(1L, 1L, requestDto);
//
//        assertThat(startupCommunityService.communityList(1L).get(0).getTitle()).isEqualTo("글 수정 Title Test");
//    }

    @Test
    public void 글삭제(){
        startupCommunityService.communityDelete(1L, 1L);

        assertThat(startupCommunityService.communityList(1L).size()).isEqualTo(0);
    }

    @Test
    public void 글상세보기(){
        StartupCommunityResponseDetailDto detailDto = startupCommunityService.communityDetail(1L);

        assertThat(detailDto.getTitle()).isEqualTo("Title Test");
        assertThat(detailDto.getContent()).isEqualTo("Content Test");
        assertThat(detailDto.getCommentList().get(0).getNickName()).isEqualTo("aaa");
        assertThat(detailDto.getCommentList().get(0).getChildren().get(0).getNickName()).isEqualTo("bbb");
        assertThat(detailDto.getCommentList().get(0).getChildren().get(1).getNickName()).isEqualTo("ccc");
    }
}