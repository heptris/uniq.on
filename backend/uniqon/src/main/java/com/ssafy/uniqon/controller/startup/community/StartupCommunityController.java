package com.ssafy.uniqon.controller.startup.community;

import com.ssafy.uniqon.dto.response.ResponseDto;
import com.ssafy.uniqon.dto.startup.community.StartupCommunityRequestDto;
import com.ssafy.uniqon.dto.startup.community.StartupCommunityRequestModifyDto;
import com.ssafy.uniqon.dto.startup.community.StartupCommunityResponseListDto;
import com.ssafy.uniqon.service.startup.community.StartupCommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invest")
@RequiredArgsConstructor
public class StartupCommunityController {

    private final StartupCommunityService startupCommunityService;

    @GetMapping("/community/{startupId}")
    public ResponseEntity communityList(@PathVariable Long startupId){
        List<StartupCommunityResponseListDto> startupCommunityResponseListDto = startupCommunityService.communityList(startupId);
        return new ResponseEntity(new ResponseDto(200, "success", startupCommunityResponseListDto), HttpStatus.OK);
    }

    @PostMapping("/community/{startupId}")
    public ResponseEntity communityWrite(@PathVariable Long startupId, @RequestBody StartupCommunityRequestDto requestDto){
        startupCommunityService.communityWrite(startupId, requestDto);
        return new ResponseEntity(new ResponseDto(200, "success", "글쓰기 완료"), HttpStatus.OK);
    }

    @PutMapping("/community/{startupId}/{communityId}")
    public ResponseEntity communityModify(@PathVariable Long startupId,
                                          @PathVariable Long communityId,
                                          @RequestBody StartupCommunityRequestModifyDto requestDto){
        startupCommunityService.communityModify(startupId, communityId, requestDto);
        return new ResponseEntity(new ResponseDto(200, "success", "글 수정 완료"), HttpStatus.OK);
    }

    @DeleteMapping("/community/{startupId}/{communityId}s")
    public ResponseEntity communityDelete(@PathVariable Long startupId, @PathVariable Long communityId){
        startupCommunityService.communityDelete(startupId, communityId);
        return new ResponseEntity(new ResponseDto(200, "success", "글 삭제 완료"), HttpStatus.OK);
    }

    @GetMapping("/community/detail/{communityId}")
    public ResponseEntity communityDetail(@PathVariable Long communityId){
        return new ResponseEntity(new ResponseDto(200, "success", null), HttpStatus.OK);
    }


}
