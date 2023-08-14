package com.livelyit.allcam.dto;

import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 추천목록 DTO
 * 
 */

@Data
@EqualsAndHashCode(callSuper=false)
public class RecommendListDTO extends DefaultDTO {
	ArrayList<RecommendUserDTO> recommendList;
}