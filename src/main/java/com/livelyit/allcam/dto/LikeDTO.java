package com.livelyit.allcam.dto;

import lombok.Data;

/**
 * 즐겨찾기 DTO
 * 
 */

@Data
public class LikeDTO{
	
	int no;	
	int user_no; 
	int to_user_no; 
	String sdate; 
	
}