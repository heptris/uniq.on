package com.ssafy.uniqon.controller.Member;

import com.ssafy.uniqon.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    @GetMapping
    public ResponseEntity test() {
        return new ResponseEntity<ResponseDto>(new ResponseDto(200, "success", null)
        , HttpStatus.OK);
    }
}
