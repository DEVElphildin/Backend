package com.livelyit.allcam.dto;

import lombok.Data;

/**
 * 별점DTO
 * 
 */

@Data
public class LiveStarGradeDTO extends DefaultDTO {
	
	int user_no;
	int to_user_no;
	int star_cnt;
	String sdate;
	
}