package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class BoardDTO {
	int no;
	String board_type;
	String board_subject;
	String board_text;
	String service_type;
	String target_gender;
	String sdate;
}