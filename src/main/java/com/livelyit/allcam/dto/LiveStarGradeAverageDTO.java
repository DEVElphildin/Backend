package com.livelyit.allcam.dto;

import lombok.Data;

/**
 * 평균 별점DTO
 * 
 */

@Data
public class LiveStarGradeAverageDTO extends DefaultDTO {
	
	int user_no;
	int average_star;
	
}