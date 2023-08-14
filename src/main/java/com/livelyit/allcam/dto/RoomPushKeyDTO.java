package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class RoomPushKeyDTO {
	String my_push_key;
	String partner_push_key;

	int my_user_no;
	int partner_user_no;
	int absent_push_flag;
}
