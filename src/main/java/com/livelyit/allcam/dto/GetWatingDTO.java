package com.livelyit.allcam.dto;

import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class GetWatingDTO extends DefaultDTO {
	ArrayList<RandomImageDTO> randomImageList;
	ArrayList<WatingUserDTO> watingList;
	ArrayList<RecommendUserDTO> recommendList;
}