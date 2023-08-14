package com.livelyit.allcam.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SMSDTO extends DefaultDTO {
	String msg;
	String sendNum = "07070081010";
	String receiveNum;
	String CMID = System.currentTimeMillis() + "";
	String sendDate = "NONE";
}
