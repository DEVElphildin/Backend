package com.livelyit.allcam.service;

import com.livelyit.allcam.dto.RecommendListDTO;


/**
 * 추천목록 service
 * @author ㅇㄱ
 *
 */
public interface RecommendUserService {
	//추천 리스트
	public RecommendListDTO selectRecommendList(int user_no); // 추천 리스트
	public RecommendListDTO selectRecommendListV2(int user_no); // 추천 리스트
	public RecommendListDTO selectRecommendListV3(int user_no); // 추천 리스트
	public RecommendListDTO selectRecommendListMC(int user_no); // 추천 리스트
}