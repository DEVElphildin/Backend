package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class AutoPassBookDTO {

	int no;
	String user_name;
	int user_pay;

	int result_code;
	String result_msg;

	String user_adid;
	int user_no;
}