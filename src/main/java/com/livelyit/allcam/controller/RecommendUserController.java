package com.livelyit.allcam.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.RecommendListDTO;
import com.livelyit.allcam.service.RecommendUserService;

/**
 * 추천목록 controller
 * @author ㅇㄱ
 *
 */
@RestController
public class RecommendUserController {
	@Autowired
	RecommendUserService recommendUserService;
		
	// 추천 리스트
	@RequestMapping("/recommendUserList")
	@ResponseBody
	public RecommendListDTO selectRecommendList(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no){
		return recommendUserService.selectRecommendList(user_no);
	}

	// 추천 리스트
	// 사용자의 언어에 맞춰서 대화주제가 나타남 (직접 입력한 대화주제 제외)
	@RequestMapping("/v2/recommendUserList")
	@ResponseBody
	public RecommendListDTO selectRecommendListV2(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no){
		return recommendUserService.selectRecommendListV2(user_no);
	}

	// 추천 리스트
	@RequestMapping("/v3/recommendUserList")
	@ResponseBody
	public RecommendListDTO selectRecommendListV3(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no){
		return recommendUserService.selectRecommendListV3(user_no);
	}

	// 미인캠 추천 리스트
	@RequestMapping("/recommendUserListMC")
	@ResponseBody
	public RecommendListDTO selectRecommendListMC(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no){
		return recommendUserService.selectRecommendListMC(user_no);
	}

}