package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class RoomStatusDTO {
	int no;
	String room_id;
	int room_type; //1영상, 2음성
	int room_status;
	int finish_type;
	String myPushKey;
	String partnerPushKey;
	int my_user_no;
	int partner_user_no;
	int finish_user_no;

}