package com.livelyit.allcam.service;


import com.livelyit.allcam.dto.EventInfoDTO;
import com.livelyit.allcam.dto.EventListDTO;
import com.livelyit.allcam.dto.MainBannerDTO;


/**
 * 이벤트 service
 *
 *
 */
public interface EventService {

	public EventListDTO selectEventList();	// 이벤트 리스트
	public EventInfoDTO selectEvent(int event_no); // 이벤트 1개 가져오기
	public EventListDTO selectEventListV2(int user_no);	// 사용자 성별, 서비스 타입에 따른 이벤트 리스트MainBannerDTO
	public EventListDTO selectEventListMC(int user_no);	// 사용자 성별, 서비스 타입에 따른 이벤트 리스트MainBannerDTO
	public MainBannerDTO selectNonMemberBanner(); // 이벤트 1개 가져오기

}