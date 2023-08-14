package com.livelyit.allcam.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 이벤트 DTO
 * 
 */

@Data
@EqualsAndHashCode(callSuper=false)
public class EventInfoDTO {
	EventDTO eventInfo;
	String result;
}
