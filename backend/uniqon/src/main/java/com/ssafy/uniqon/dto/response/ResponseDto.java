package com.ssafy.uniqon.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseDto<T> {

    private int status;
    private String message;
    private T data;

}
