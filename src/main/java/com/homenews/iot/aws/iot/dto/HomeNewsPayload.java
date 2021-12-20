package com.homenews.iot.aws.iot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomeNewsPayload {
    private String doorNumber;
    private Boolean mainDoorLocked;
    private Boolean allLightsOff;
}
