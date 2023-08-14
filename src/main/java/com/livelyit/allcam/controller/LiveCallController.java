package com.livelyit.allcam.controller;

import com.livelyit.allcam.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.service.LiveCallService;
import com.livelyit.allcam.service.PushService;

@CrossOrigin(origins = "*")
@RestController
public class LiveCallController {
	@Autowired
	LiveCallService liveCallService;

	@Autowired
	PushService pushService;

//	@RequestMapping("/getWaiting")
//	@ResponseBody
//	public GetWatingDTO getWaiting(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
//			@RequestParam(value = "order", required = !Utils.Debug) String order) {
//		MainLiveCallDTO dto = new MainLiveCallDTO();
//		dto.setUser_no(user_no);
//		dto.setOrder(order);
//
//		return liveCallService.insertWaiting(dto);
//	}

//	@RequestMapping("/v2/getWaiting")
//	@ResponseBody
//	public GetWatingDTO getWaitingV2(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
//								   @RequestParam(value = "order", required = !Utils.Debug) String order,
//								   @RequestParam(value = "countryKind", required = !Utils.Debug) String countryKind) {
//		MainLiveCallDTO dto = new MainLiveCallDTO();
//		dto.setUser_no(user_no);
//		dto.setOrder(order);
//        dto.setCountry_kind(countryKind);
//
//		return liveCallService.insertWaitingV2(dto);
//	}

//	@RequestMapping("/v3/getWaiting")
//	@ResponseBody
//	public GetWatingDTO getWaitingV3(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
//									 @RequestParam(value = "order", required = !Utils.Debug) String order,
//									 @RequestParam(value = "countryKind", required = !Utils.Debug) String countryKind) {
//		MainLiveCallDTO dto = new MainLiveCallDTO();
//		dto.setUser_no(user_no);
//		dto.setOrder(order);
//		dto.setCountry_kind(countryKind);
//
//		return liveCallService.insertWaitingV3(dto);
//	}

	// 대기 리스트를 많이 가져옴
//	@RequestMapping("/v4/getWaiting")
//	@ResponseBody
//	public GetWatingDTO getWaitingV4(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
//									 @RequestParam(value = "order", required = !Utils.Debug) String order,
//									 @RequestParam(value = "waiting_gender", required = !Utils.Debug) String waiting_gender,
//									 @RequestParam(value = "countryKind", required = !Utils.Debug) String countryKind) {
//		MainLiveCallDTO dto = new MainLiveCallDTO();
//		dto.setUser_no(user_no);
//		dto.setOrder(order);
//		if(!waiting_gender.equals("남성") && !waiting_gender.equals("여성"))
//			dto.setWaiting_gender("여성");
//		else
//			dto.setWaiting_gender(waiting_gender);
//		dto.setCountry_kind(countryKind);
//
//		return liveCallService.insertWaitingV4(dto);
//	}

	// 많이 가져옴
	// 실 접속자만 요구하는 경우
	// sql 문 수정해줘야함 (원라이브, 탑톡, 와우캠)
	@RequestMapping("/v5/getWaiting")
	@ResponseBody
	public GetWatingDTO getWaitingV5(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
									 @RequestParam(value = "order", required = !Utils.Debug) String order,
									 @RequestParam(value = "waiting_gender", required = !Utils.Debug) String waiting_gender,
									 @RequestParam(value = "countryKind", required = !Utils.Debug) String countryKind) {
		MainLiveCallDTO dto = new MainLiveCallDTO();
		dto.setUser_no(user_no);
		dto.setOrder(order);
		if(!waiting_gender.equals("남성") && !waiting_gender.equals("여성"))
			dto.setWaiting_gender("여성");
		else
			dto.setWaiting_gender(waiting_gender);
		dto.setCountry_kind(countryKind);

		return liveCallService.insertWaitingV5(dto);
	}


	// 마지막 로그인이 5시간 이내인 사용자만 가져옴
	// 1. 올캠이 아닌 사용자들은 통화 타입이 1인(영상만) 설정하신 분들만 가져오기
	// 추후에 다른 앱 업데이트 되면 이 해당 사항은 삭제함
	// 2. 대화 주제 번호가 0인 경우 user 테이블의 conversation_txt 가져오기
	// 3. 0번이 아니면 대화 주제 테이블에서 가져오기
	// 올캠만..
	@RequestMapping("/v6/getWaiting")
	@ResponseBody
	public GetWatingDTO getWaitingV6(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
									 @RequestParam(value = "order", required = !Utils.Debug) String order,
									 @RequestParam(value = "waiting_gender", required = !Utils.Debug) String waiting_gender,
									 @RequestParam(value = "countryKind", required = !Utils.Debug) String countryKind) {
		MainLiveCallDTO dto = new MainLiveCallDTO();
		dto.setUser_no(user_no);
		dto.setOrder(order);
		if(!waiting_gender.equals("남성") && !waiting_gender.equals("여성"))
			dto.setWaiting_gender("여성");
		else
			dto.setWaiting_gender(waiting_gender);
		dto.setCountry_kind(countryKind);

		return liveCallService.insertWaitingV6(dto);
	}

	// 미인캠용
	@RequestMapping("/getWaitingMC")
	@ResponseBody
	public GetWatingDTO getWaitingMC(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
									 @RequestParam(value = "order", required = !Utils.Debug) String order,
									 @RequestParam(value = "waiting_gender", required = !Utils.Debug) String waiting_gender,
									 @RequestParam(value = "countryKind", required = !Utils.Debug) String countryKind) {
		MainLiveCallDTO dto = new MainLiveCallDTO();
		dto.setUser_no(user_no);
		dto.setOrder(order);
		if(!waiting_gender.equals("남성") && !waiting_gender.equals("여성"))
			dto.setWaiting_gender("여성");
		else
			dto.setWaiting_gender(waiting_gender);
		dto.setCountry_kind(countryKind);

		return liveCallService.insertWaitingMC(dto);
	}

	// 비회원이 요청하는 대기회원 리스트
	@RequestMapping("/getWatingUserNonMember")
	@ResponseBody
	public GetWatingDTO getWatingUserNonMember() {
		return liveCallService.getWatingUserNonMember();
	}



	@RequestMapping("/getNewbieUserList")
	@ResponseBody
	public GetNewbieUserListDTO getNewbieUserList(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no) {
		MainLiveCallDTO dto = new MainLiveCallDTO();
		dto.setUser_no(user_no);

		return liveCallService.getNewbieUserList(dto);
	}

	@RequestMapping("/v2/getNewbieUserList")
	@ResponseBody
	public GetNewbieUserListDTO getNewbieUserListV2(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no) {
		MainLiveCallDTO dto = new MainLiveCallDTO();
		dto.setUser_no(user_no);

		return liveCallService.getNewbieUserListV2(dto);
	}

	@RequestMapping("/getNewUserList")
	@ResponseBody
	public GetNewbieUserListDTO getNewUserList(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
											   @RequestParam(value = "waiting_gender", required = !Utils.Debug) String waiting_gender) {
		MainLiveCallDTO dto = new MainLiveCallDTO();
		dto.setUser_no(user_no);
		dto.setWaiting_gender(waiting_gender);

		return liveCallService.getNewUserList(dto);
	}


	@RequestMapping(value = "/callInfoChk", params = {"user_no", "partner_user_no"})
	@ResponseBody
	public CallChkInfoDTO callInfoChk(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
			@RequestParam(value = "partner_user_no", required = !Utils.Debug) int partner_user_no) {
		return liveCallService.callInfoChk(user_no, partner_user_no);
	}

	@RequestMapping(value = "/callInfoChk", params = {"user_no", "partner_user_no","pushKey"})
	@ResponseBody
	public CallChkInfoDTO callInfoChk(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
									  @RequestParam(value = "partner_user_no", required = !Utils.Debug) int partner_user_no,
									  @RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey) {
		return liveCallService.callInfoChk(user_no, partner_user_no, pushKey);
	}

	@RequestMapping(value = "/v2/callInfoChk", params = {"user_no", "partner_user_no","pushKey"})
	@ResponseBody
	public CallChkInfoDTO callInfoChkV2(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
									  @RequestParam(value = "partner_user_no", required = !Utils.Debug) int partner_user_no,
									  @RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey) {
		return liveCallService.callInfoChkV2(user_no, partner_user_no, pushKey);
	}

	@RequestMapping(value = "/callInfoChkRandomPartner", params = {"user_no","pushKey","image_no"})
	@ResponseBody
	public CallChkInfoDTO callInfoChkRandomPartner(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
												   @RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey,
												   @RequestParam(value = "image_no", required = !Utils.Debug) int image_no) {
		return liveCallService.callInfoChkRandomPartner(user_no, pushKey, image_no);
	}

	@RequestMapping(value = "/voiceCallInfoChk", params = {"user_no", "partner_user_no","pushKey"})
	@ResponseBody
	public CallChkInfoDTO voiceCallInfoChk(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
									  @RequestParam(value = "partner_user_no", required = !Utils.Debug) int partner_user_no,
									  @RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey) {
		return liveCallService.voiceCallInfoChk(user_no, partner_user_no, pushKey);
	}

	// 새내기 상관없이 누구나 음성 통화 할 수 있도록 함
	@RequestMapping(value = "/v2/voiceCallInfoChk", params = {"user_no", "partner_user_no","pushKey"})
	@ResponseBody
	public CallChkInfoDTO voiceCallInfoChkV2(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
										   @RequestParam(value = "partner_user_no", required = !Utils.Debug) int partner_user_no,
										   @RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey) {
		return liveCallService.voiceCallInfoChkV2(user_no, partner_user_no, pushKey);
	}

	@RequestMapping(value = "/randomCallChk", params = {"user_no", "pushKey", "image_no"})
	@ResponseBody
	public CallChkInfoDTO randomCallChk(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
									  @RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey,
										@RequestParam(value = "image_no", required = !Utils.Debug) int image_no) {
		return liveCallService.randomCallChk(user_no, pushKey, image_no);
	}

	@RequestMapping("/randomCallStart")
	@ResponseBody
	public DefaultDTO randomCallStart(@RequestParam(value = "room_id", required = !Utils.Debug) String room_id,
									  @RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
									  @RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey) {
		return liveCallService.randomCallStart(room_id, user_no, pushKey);
	}

	@RequestMapping("/randomCallCancel")
	@ResponseBody
	public DefaultDTO randomCallCancel(@RequestParam(value = "room_id", required = !Utils.Debug) String room_id,
								 @RequestParam(value = "finish_type", required = !Utils.Debug) int finish_type,
								 @RequestParam(value = "user_no", required = !Utils.Debug) int user_no) {
		return liveCallService.randomCallCancel(room_id, finish_type, user_no);
	}

	@RequestMapping("/randomCallFinish")
	@ResponseBody
	public UserBalanceDTO randomCallFinish(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
									 @RequestParam(value = "room_id", required = !Utils.Debug) String room_id,
									 @RequestParam(value = "use_seconds", required = !Utils.Debug) int use_seconds,
									 @RequestParam(value = "finish_type", required = !Utils.Debug) int finish_type) {
		return liveCallService.randomCallFinish(user_no, room_id, use_seconds, finish_type);
	}


	@RequestMapping("/callCancel")
	@ResponseBody
	public DefaultDTO callCancel(@RequestParam(value = "room_id", required = !Utils.Debug) String room_id,
			@RequestParam(value = "call_type", required = !Utils.Debug) int call_type) {	//룸 상태 / 0 대기 , -1취소 , 1라이브중, 2라이브 종료
		return liveCallService.callCancel(room_id, call_type);
	}

	@RequestMapping("/voiceCallCancel")
	@ResponseBody
	public DefaultDTO voiceCallCancel(@RequestParam(value = "room_id", required = !Utils.Debug) String room_id,
								 @RequestParam(value = "call_type", required = !Utils.Debug) int call_type) {	//룸 상태 / 0 대기 , -1취소 , 1라이브중, 2라이브 종료
		return liveCallService.voiceCallCancel(room_id, call_type);
	}

	@RequestMapping("/callOk")
	@ResponseBody
	public DefaultDTO callOk(@RequestParam(value = "room_id", required = !Utils.Debug) String room_id,
			@RequestParam(value = "req_user_no", required = !Utils.Debug) int req_user_no) {
		return liveCallService.callOk(room_id, req_user_no);
	}

	@RequestMapping("/voiceCallOk")
	@ResponseBody
	public DefaultDTO voiceCallOk(@RequestParam(value = "room_id", required = !Utils.Debug) String room_id,
							 @RequestParam(value = "req_user_no", required = !Utils.Debug) int req_user_no) {
		return liveCallService.voiceCallOk(room_id, req_user_no);
	}


	@RequestMapping("/setLiveScore")
	@ResponseBody
	public DefaultDTO insertStar(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
			@RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no,
			@RequestParam(value = "star_cnt", required = !Utils.Debug) int star_cnt) {

		LiveStarGradeDTO dto = new LiveStarGradeDTO();
		dto.setStar_cnt(star_cnt);
		dto.setUser_no(user_no);
		dto.setTo_user_no(to_user_no);

		return liveCallService.insertStar(dto);
	}

	@RequestMapping("/callStart")
	@ResponseBody
	public DefaultDTO callStart(@RequestParam(value = "room_id", required = !Utils.Debug) String room_id) {
		return liveCallService.callStart(room_id);
	}

	@RequestMapping("/v2/callStart")
	@ResponseBody
	public DefaultDTO callStartV2(@RequestParam(value = "room_id", required = !Utils.Debug) String room_id) {
		return liveCallService.callStartV2(room_id);
	}


	@RequestMapping("/voiceCallStart")
	@ResponseBody
	public DefaultDTO voiceCallStart(@RequestParam(value = "room_id", required = !Utils.Debug) String room_id) {
		return liveCallService.voiceCallStart(room_id);
	}

	@RequestMapping("/liveUsePoint")
	@ResponseBody
	public UserPointDTO liveUsePoint(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
			@RequestParam(value = "room_id", required = !Utils.Debug) String room_id,
			@RequestParam(value = "endFlag", required = !Utils.Debug) boolean endFlag) {
		return liveCallService.liveUsePoint(user_no, room_id, endFlag);
	}

	@RequestMapping("/liveGetCash")
	@ResponseBody
	public GetCashDTO liveGetCash(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
			@RequestParam(value = "room_id", required = !Utils.Debug) String room_id) {
		return liveCallService.liveGetCash(user_no, room_id);
	}

//	@RequestMapping("/pointPresent")
//	@ResponseBody
//	public PresentPointDTO pointPresent(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
//			@RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no,
//			@RequestParam(value = "etc", required = !Utils.Debug) String etc,
//			@RequestParam(value = "point", required = !Utils.Debug) int point) {
//		return liveCallService.pointPresent(user_no, to_user_no, point, etc);
//	}
//
//	@RequestMapping("/v2/pointPresent")
//	@ResponseBody
//	public PresentPointDTO pointPresentV2(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
//										@RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no,
//										@RequestParam(value = "etc", required = !Utils.Debug) String etc,
//										@RequestParam(value = "point", required = !Utils.Debug) int point) {
//		return liveCallService.pointPresent(user_no, to_user_no, point, etc);
//	}
//
//	@RequestMapping("/v3/pointPresent")
//	@ResponseBody
//	public PresentPointDTO pointPresentV3(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
//										  @RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no,
//										  @RequestParam(value = "etc", required = !Utils.Debug) String etc,
//										  @RequestParam(value = "point", required = !Utils.Debug) int point) {
//		return liveCallService.pointPresentV3(user_no, to_user_no, point, etc);
//	}

	@RequestMapping("/v4/pointPresent")
	@ResponseBody
	public PresentPointDTO pointPresentV4(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
										  @RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no,
										  @RequestParam(value = "etc", required = !Utils.Debug) String etc,
										  @RequestParam(value = "point", required = !Utils.Debug) int point) {
		return liveCallService.pointPresentV4(user_no, to_user_no, point, etc);
	}

	@RequestMapping("/pointPresentMC")
	@ResponseBody
	public PresentPointDTO pointPresentMC(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
										  @RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no,
										  @RequestParam(value = "etc", required = !Utils.Debug) String etc,
										  @RequestParam(value = "point", required = !Utils.Debug) int point) {
		return liveCallService.pointPresentMC(user_no, to_user_no, point, etc);
	}

	@RequestMapping("/v5/pointPresent")
	@ResponseBody
	public PresentPointDTO pointPresentV5(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
										  @RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no,
										  @RequestParam(value = "etc", required = !Utils.Debug) String etc,
										  @RequestParam(value = "point", required = !Utils.Debug) int point) {
		return liveCallService.pointPresentV5(user_no, to_user_no, point, etc);
	}

	@RequestMapping("/getUseList")
	@ResponseBody
	public UseHistoryListDTO getUseList(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
			@RequestParam(value = "use_type", required = !Utils.Debug) int use_type) {
		return liveCallService.getUseList(user_no, use_type);
	}

	// 사용내역 > 통화내역
	@RequestMapping("/getCallHistoryList")
	@ResponseBody
	public CallHistoryListDTO getCallHistoryList(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no) {
		return liveCallService.getCallHistoryList(user_no);
	}

	// 사용내역 > 통화내역
	@RequestMapping("/getHeartHistoryList")
	@ResponseBody
	public HeartHistoryListDTO getHeartHistoryList(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no) {
		return liveCallService.getHeartHistoryList(user_no);
	}

	@RequestMapping("/liveInfo")
	@ResponseBody
	public LiveInfoDTO liveInfo(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
			@RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no) {
		return liveCallService.liveInfo(user_no, to_user_no);
	}

	@RequestMapping("/getRoomStatus")
	@ResponseBody
	public RoomStatusResultDTO getRoomStatus(@RequestParam(value = "room_id", required = !Utils.Debug) String room_id) {
		return liveCallService.getRoomStatus(room_id);
	}

	@RequestMapping("/callFinish")
	@ResponseBody
	public UserBalanceDTO callFinish(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
									   @RequestParam(value = "room_id", required = !Utils.Debug) String room_id,
									   @RequestParam(value = "use_seconds", required = !Utils.Debug) int use_seconds,
									   @RequestParam(value = "finish_type", required = !Utils.Debug) int finish_type) {
		return liveCallService.callFinish(user_no, room_id, use_seconds, finish_type);
	}

	@RequestMapping("/v2/callFinish")
	@ResponseBody
	public UserBalanceDTO callFinishV2(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
									 @RequestParam(value = "room_id", required = !Utils.Debug) String room_id,
									 @RequestParam(value = "use_seconds", required = !Utils.Debug) int use_seconds,
									 @RequestParam(value = "finish_type", required = !Utils.Debug) int finish_type) {
		return liveCallService.callFinishV2(user_no, room_id, use_seconds, finish_type);
	}

	@RequestMapping("/callFinishMC")
	@ResponseBody
	public UserBalanceDTO callFinishMC(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
									   @RequestParam(value = "room_id", required = !Utils.Debug) String room_id,
									   @RequestParam(value = "use_seconds", required = !Utils.Debug) int use_seconds,
									   @RequestParam(value = "finish_type", required = !Utils.Debug) int finish_type) {
		return liveCallService.callFinishMC(user_no, room_id, use_seconds, finish_type);
	}



	@RequestMapping("/voiceCallFinish")
	@ResponseBody
	public UserBalanceDTO voiceCallFinish(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
									   @RequestParam(value = "room_id", required = !Utils.Debug) String room_id,
									   @RequestParam(value = "use_seconds", required = !Utils.Debug) int use_seconds,
									   @RequestParam(value = "finish_type", required = !Utils.Debug) int finish_type) {
		return liveCallService.voiceCallFinish(user_no, room_id, use_seconds, finish_type);
	}

	@RequestMapping("/v2/voiceCallFinish")
	@ResponseBody
	public UserBalanceDTO voiceCallFinishV2(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
										  @RequestParam(value = "room_id", required = !Utils.Debug) String room_id,
										  @RequestParam(value = "use_seconds", required = !Utils.Debug) int use_seconds,
										  @RequestParam(value = "finish_type", required = !Utils.Debug) int finish_type) {
		return liveCallService.voiceCallFinishV2(user_no, room_id, use_seconds, finish_type);
	}
}