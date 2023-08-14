package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class LiveTimeDTO {
	int room_status;
	String start_time;
	String end_time;
	String room_id;
	int partner_user_no;
	String partner_push_key;
}
