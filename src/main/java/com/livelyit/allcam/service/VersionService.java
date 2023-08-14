package com.livelyit.allcam.service;

import com.livelyit.allcam.dto.VersionDTO;
import com.livelyit.allcam.dto.VersionResultDTO;
import com.livelyit.allcam.dto.VersionResultV2DTO;

public interface VersionService {
	public VersionResultV2DTO versionCheckV2(VersionDTO dto);	// 버전체크 ver.2
}