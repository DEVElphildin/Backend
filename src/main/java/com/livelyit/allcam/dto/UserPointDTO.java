package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class UserPointDTO extends DefaultDTO{	
	int user_point;
	int use_point;
	int use_free_point;
	int user_free_point;
	int user_pay_point;
	String user_gender;
}
