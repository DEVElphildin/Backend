package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class DefaultDTO {
	int no;
	String result = "FAIL";
	String reason;
	String error_code;
	String success_code;
}
