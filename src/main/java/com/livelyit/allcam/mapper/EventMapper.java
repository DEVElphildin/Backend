package com.livelyit.allcam.mapper;

import java.util.ArrayList;

import com.livelyit.allcam.dto.MainBannerDTO;
import com.livelyit.allcam.dto.UserInfoDTO;
import org.springframework.stereotype.Repository;

import com.livelyit.allcam.common.AbstractDAO;
import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.EventDTO;

/**
 *  이벤트  mapper
 * 
 * @author ㅇㄱ
 *
 */
@Repository("eventMapper")
public class EventMapper extends AbstractDAO{
	
	// 이벤트 리스트 불러오기
	public ArrayList<EventDTO> selectEventList() {
		return (ArrayList<EventDTO>) selectList(Utils.SQL_ALLCAM, "eventMapper.selectEventList");
	}

	// 이벤트 리스트 v2 불러오기
	public ArrayList<EventDTO> selectEventListV2(UserInfoDTO dto) {
		return (ArrayList<EventDTO>) selectList(Utils.SQL_ALLCAM, "eventMapper.selectEventListV2", dto);	}

	// 미인캠 이벤트 리스트 불러오기
	public ArrayList<EventDTO> selectEventListMC(UserInfoDTO dto) {
		return (ArrayList<EventDTO>) selectList(Utils.SQL_ALLCAM, "eventMapper.selectEventListMC", dto);	}

	// 이벤트 1개 가져오기
	public EventDTO selectEvent(int event_no) {
		return (EventDTO) selectOne(Utils.SQL_ALLCAM, "eventMapper.selectEvent", event_no);
	}
	
	// 이벤트 이미지 불러오기
	public EventDTO selectEventImg(int no) {
		return (EventDTO) selectOne(Utils.SQL_ALLCAM, "eventMapper.selectEventImg");
	}

	// 이벤트 이미지 불러오기
	public MainBannerDTO selectNonMemberBanner() {
		return (MainBannerDTO) selectOne(Utils.SQL_ALLCAM, "eventMapper.selectNonMemberBanner");
	}

}