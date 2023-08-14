package com.livelyit.allcam.dto;

import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;
 
@Data
@EqualsAndHashCode(callSuper=false)
public class VersionResultDTO extends DefaultDTO {
	VersionDTO versionInfo;
	String banner_url;
	String pay_url_mobile;
	String pay_url_card;
}