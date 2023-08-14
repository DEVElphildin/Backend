package com.livelyit.allcam.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper=false)
public class RoomStatusResultDTO extends DefaultDTO{
    RoomStatusDTO roomInfo;
}
