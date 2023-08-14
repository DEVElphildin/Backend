package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class LikeCheckDTO extends DefaultDTO{
	int user_no;
	int to_user_no;
	String sdate;
}
