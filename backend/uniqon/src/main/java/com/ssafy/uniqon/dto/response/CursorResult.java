package com.ssafy.uniqon.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CursorResult<T> {

    private T values;
    private Boolean hasNext;
    private Long cursorId;

}


