package com.livelyit.allcam.service.impl;

import com.livelyit.allcam.dto.MainLiveCallDTO;
import com.livelyit.allcam.dto.UserInfoDTO;
import com.livelyit.allcam.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.RecommendListDTO;
import com.livelyit.allcam.mapper.RecommendUserMapper;
import com.livelyit.allcam.service.RecommendUserService;


/**
 * 추천목록 serviceimpl
 * @author ㅇㄱ
 *
 */
@Service
public class RecommendUserServiceImpl implements RecommendUserService {
	@Autowired
	RecommendUserMapper recommendUserMapper;

	@Autowired
	UserMapper userMapper;

	//추천 리스트
	@Override
	public RecommendListDTO selectRecommendList(int user_no) {
		RecommendListDTO dto = new RecommendListDTO();
		dto.setRecommendList(recommendUserMapper.selectRecommendList(user_no));
		dto.setResult(Utils.SUCCESS);
		
		return dto;
	}

	//추천 리스트
	@Override
	public RecommendListDTO selectRecommendListV2(int user_no) {
		UserInfoDTO userInfoDTO = userMapper.getUserInfo(user_no);

		MainLiveCallDTO mainLiveCallDTO = new MainLiveCallDTO();
		mainLiveCallDTO.setUser_no(user_no);
		mainLiveCallDTO.setUser_language_code(userInfoDTO.getUser_language_code()!=null?userInfoDTO.getUser_language_code():"ko");


		RecommendListDTO dto = new RecommendListDTO();
		dto.setRecommendList(recommendUserMapper.selectRecommendListV2(mainLiveCallDTO));
		dto.setResult(Utils.SUCCESS);

		return dto;
	}

	@Override
	public RecommendListDTO selectRecommendListV3(int user_no) {
		UserInfoDTO userInfoDTO = userMapper.getUserInfo(user_no);

		MainLiveCallDTO mainLiveCallDTO = new MainLiveCallDTO();
		mainLiveCallDTO.setUser_no(user_no);
		mainLiveCallDTO.setUser_language_code(userInfoDTO.getUser_language_code()!=null?userInfoDTO.getUser_language_code():"ko");


		RecommendListDTO dto = new RecommendListDTO();
		dto.setRecommendList(recommendUserMapper.selectRecommendListV3(mainLiveCallDTO));
		dto.setResult(Utils.SUCCESS);

		return dto;
	}

	@Override
	public RecommendListDTO selectRecommendListMC(int user_no) {
		UserInfoDTO userInfoDTO = userMapper.getUserInfo(user_no);

		MainLiveCallDTO mainLiveCallDTO = new MainLiveCallDTO();
		mainLiveCallDTO.setUser_no(user_no);

		if(userInfoDTO.getUser_gender().equals("여성"))
			mainLiveCallDTO.setWaiting_gender("남성");
		else
			mainLiveCallDTO.setWaiting_gender("여성");

		mainLiveCallDTO.setUser_language_code(userInfoDTO.getUser_language_code()!=null?userInfoDTO.getUser_language_code():"ko");

		RecommendListDTO dto = new RecommendListDTO();
		dto.setRecommendList(recommendUserMapper.selectRecommendListMC(mainLiveCallDTO));
		dto.setResult(Utils.SUCCESS);

		return dto;
	}
}