package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class ChatUserDTO {	
	int user_no;
	int to_user_no;
	String arr_to_user_no;
	String user_country_code;
	double present_to_cash_rate;
}
