package com.livelyit.allcam.controller;

import com.livelyit.allcam.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.VersionDTO;
import com.livelyit.allcam.dto.VersionResultDTO;
import com.livelyit.allcam.dto.VersionResultV2DTO;
import com.livelyit.allcam.service.VersionService;

@RestController
public class VersionController {
	@Autowired
	VersionService versionService;

	// 저장된 최신 업데이트 버전을 가져온다.
	@RequestMapping(value = "/versionCheckV2", params = {"os_type","service_type", "store_type"})
	@ResponseBody
	public VersionResultV2DTO versionCheckV2(@RequestParam(value = "os_type", required=!Utils.Debug) String os_type,
											 @RequestParam(value = "service_type", required=!Utils.Debug) String service_type,
											 @RequestParam(value = "store_type", required=!Utils.Debug) String store_type) {

		//jin 2022.8.15
		// if(os_type.equals("android")  && !service_type.equals("meincam")){
		// 	return null;
		// }
		VersionDTO dto = new VersionDTO();
		dto.setOs_type(os_type);
		dto.setService_type(service_type);
		dto.setStore_type(store_type);

		return versionService.versionCheckV2(dto);
	}

}