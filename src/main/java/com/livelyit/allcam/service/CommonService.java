package com.livelyit.allcam.service;

import com.livelyit.allcam.dto.DefaultDTO;

public interface CommonService {
	public String imgUpload(String upType, String originalFilename, byte[] bytes);
	public String imgUploadV2(String upType, String originalFilename, byte[] bytes, int user_no);


}