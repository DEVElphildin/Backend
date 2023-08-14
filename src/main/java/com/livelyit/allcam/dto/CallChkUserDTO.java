package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class CallChkUserDTO {
	String room_id;
	int user_no;
	int partner_user_no;
	int my_user_no;
}