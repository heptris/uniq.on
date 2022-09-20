package com.ssafy.uniqon.dto.mypage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AlarmDto {

    private Long alarmId;

    private String content;

    private boolean isRead;

}
