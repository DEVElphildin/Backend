package com.livelyit.allcam.dto;

import lombok.Data;

/**
 *  이미지 DTO
 * 
 */

@Data
public class UserThumnailDTO extends DefaultDTO {
	
	int no;
	int user_no;
	String img_type;
	String img_url;
	int img_width;
	int img_height;
	String sdate;

	
}