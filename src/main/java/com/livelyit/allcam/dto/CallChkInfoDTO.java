package com.livelyit.allcam.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CallChkInfoDTO extends DefaultDTO {

	String roomId;
	UserInfoDTO myInfo;
	UserInfoDTO partnerInfo;
}