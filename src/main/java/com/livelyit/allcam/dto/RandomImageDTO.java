package com.livelyit.allcam.dto;

import lombok.Data;

/**
 * 랜덤 이미지 DTO
 *
 */

@Data
public class RandomImageDTO {
	int no;
	int image_type;
	String image_url;
	String image_text;
	int hit_count;
	String target_gender;
}
