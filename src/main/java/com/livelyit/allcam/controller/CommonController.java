package com.livelyit.allcam.controller;

import java.io.IOException;

import com.livelyit.allcam.dto.DefaultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.service.CommonService;

@RestController
public class CommonController {
	@Autowired
	CommonService commonService;
		
	@RequestMapping("/imgUpload")
    @ResponseBody
    public String imgUpload(@RequestParam("imgFile") MultipartFile file,
    		@RequestParam(value = "upType", required = !Utils.Debug) String upType) {
		try {
			return commonService.imgUpload(upType, file.getOriginalFilename(), file.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "{'result':'FAIL'}";
		}
    }

    // 올캠에서 프로필 수정시 이미지 업로드할 때만 사용됨
	@RequestMapping("/profileModifyImgUpload")
	@ResponseBody
	public String imgUploadV2(@RequestParam("imgFile") MultipartFile file,
							@RequestParam(value = "upType", required = !Utils.Debug) String upType,
							@RequestParam(value = "user_no", required = !Utils.Debug) int user_no) {
		try {
			return commonService.imgUploadV2(upType, file.getOriginalFilename(), file.getBytes(), user_no);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "{'result':'FAIL'}";
		}
	}

}