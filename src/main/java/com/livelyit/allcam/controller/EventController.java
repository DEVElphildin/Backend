package com.livelyit.allcam.controller;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.EventInfoDTO;
import com.livelyit.allcam.dto.MainBannerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.livelyit.allcam.dto.EventListDTO;
import com.livelyit.allcam.service.EventService;

/**
 * 이벤트 controller
 * @author ㅇㄱ
 *
 */

@RestController
public class EventController {

	@Autowired
	EventService eventService;
	
	// 이벤트 리스트
	@RequestMapping(value = "/eventList", params = {"user_no"})
	@ResponseBody
	public EventListDTO selectEventList(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no){
		return eventService.selectEventListV2(user_no);
	}

	// 이벤트 리스트
	// 사용자 성별, 앱에 따라 다르게 이벤트 보옂무
	@RequestMapping(value = "/v2/eventList", params = {"user_no"})
	@ResponseBody
	public EventListDTO selectEventListV2(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no){
		return eventService.selectEventListV2(user_no);
	}

	// 미인캠 이벤트 리스트
	// 사용자 성별, 앱에 따라 다르게 이벤트 보옂무
	@RequestMapping(value = "/eventListMC", params = {"user_no"})
	@ResponseBody
	public EventListDTO selectEventListMC(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no){
		return eventService.selectEventListMC(user_no);
	}

	// 한 이벤트에 대한 정보를 가져옴
	@RequestMapping(value = "/event", params = {"event_no"})
	@ResponseBody
	public EventInfoDTO selectEvent(@RequestParam(value = "event_no", required = !Utils.Debug) int event_no){
		return eventService.selectEvent(event_no);
	}

	// 비회원 들에게 보여줄 회원가입 유도 이미지 가져오기
	@RequestMapping(value = "/nonMemberBanner")
	@ResponseBody
	public MainBannerDTO nonMemberBanner(){
		return eventService.selectNonMemberBanner();
	}
}
