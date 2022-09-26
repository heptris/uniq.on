package com.ssafy.uniqon.dto.alarm;

import com.ssafy.uniqon.domain.alarm.Alarm;
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

    private Integer tokenId;

    private Integer investCount;

}
