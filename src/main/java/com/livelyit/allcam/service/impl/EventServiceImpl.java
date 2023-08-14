package com.livelyit.allcam.service.impl;

import com.livelyit.allcam.dto.*;
import com.livelyit.allcam.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.mapper.EventMapper;
import com.livelyit.allcam.service.EventService;

import javax.rmi.CORBA.Util;
import java.util.ArrayList;

/**
 * 이벤트 serviceimpl
 * @author ㅇㄱ
 *
 */
@Service
public class EventServiceImpl implements EventService {
	@Autowired
	EventMapper eventMapper;

	@Autowired
	UserMapper userMapper;

	// 이벤트 리스트
	@Override
	public EventListDTO selectEventList() {
		EventListDTO dto = new EventListDTO();
		dto.setEventList(eventMapper.selectEventList());
		dto.setResult(Utils.SUCCESS);
		return dto;
	}

	@Override
	public EventInfoDTO selectEvent(int event_no) {
		// 이벤트 1개 가져오기
		EventInfoDTO dto = new EventInfoDTO();
		dto.setEventInfo(eventMapper.selectEvent(event_no));
		dto.setResult(Utils.SUCCESS);
		return dto;
	}

	@Override
	public EventListDTO selectEventListV2(int user_no) {
		EventListDTO dto = new EventListDTO();
		dto.setResult(Utils.FAIL);

		// 사용자 성별, 서비스 타입에 따른 이벤트 리스트
		UserInfoDTO userInfoDTO = userMapper.getUserInfo(user_no);

		if (userInfoDTO == null){
			return dto;
		}

		dto.setResult(Utils.SUCCESS);
		dto.setEventList(eventMapper.selectEventListV2(userInfoDTO));

		return dto;
	}

	@Override
	public EventListDTO selectEventListMC(int user_no) {
		EventListDTO dto = new EventListDTO();
		dto.setResult(Utils.FAIL);

		// 사용자 성별, 서비스 타입에 따른 이벤트 리스트
		UserInfoDTO userInfoDTO = userMapper.getUserInfo(user_no);

		if (userInfoDTO == null){
			return dto;
		}

		dto.setResult(Utils.SUCCESS);
		dto.setEventList(eventMapper.selectEventListMC(userInfoDTO));

		return dto;
	}

	// 비회원 이미지 가져오기
	@Override
	public MainBannerDTO selectNonMemberBanner() {
		return eventMapper.selectNonMemberBanner();
	}
}