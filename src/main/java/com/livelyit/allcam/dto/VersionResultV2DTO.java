package com.livelyit.allcam.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper=false)
public class VersionResultV2DTO extends DefaultDTO {
	VersionDTO versionInfo;
	ArrayList<MainBannerDTO> banner_url;
	String pay_url_mobile;
	String pay_url_card;
}