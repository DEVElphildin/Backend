package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class UserThumnailApproDTO {

	 int user_no;
	 String img_file_name;
	 int admin_appro;	// 0 : 대기         -1 : 불가
	 String sdate;
}
