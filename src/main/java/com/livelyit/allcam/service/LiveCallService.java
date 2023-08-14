package com.livelyit.allcam.service;

import com.livelyit.allcam.dto.*;

public interface LiveCallService {
//	public GetWatingDTO insertWaiting(MainLiveCallDTO mainLiveCallDTO);
//	public GetWatingDTO insertWaitingV2(MainLiveCallDTO mainLiveCallDTO);
//	public GetWatingDTO insertWaitingV3(MainLiveCallDTO mainLiveCallDTO);
//	public GetWatingDTO insertWaitingV4(MainLiveCallDTO mainLiveCallDTO);
	public GetWatingDTO insertWaitingV5(MainLiveCallDTO mainLiveCallDTO);
	public GetWatingDTO insertWaitingV6(MainLiveCallDTO mainLiveCallDTO);
	public GetWatingDTO insertWaitingMC(MainLiveCallDTO mainLiveCallDTO);
	public GetWatingDTO getWatingUserNonMember();
	public GetNewbieUserListDTO getNewbieUserList(MainLiveCallDTO dto);
	public GetNewbieUserListDTO getNewbieUserListV2(MainLiveCallDTO dto);
	public GetNewbieUserListDTO getNewUserList(MainLiveCallDTO dto);

	public CallChkInfoDTO callInfoChk(int user_no, int partner_user_no);
	public CallChkInfoDTO callInfoChk(int user_no, int partner_user_no, String pushKey);
	public CallChkInfoDTO callInfoChkV2(int user_no, int partner_user_no, String pushKey);
	public CallChkInfoDTO callInfoChkRandomPartner(int user_no, String pushKey, int image_no);
	public CallChkInfoDTO randomCallChk(int user_no, String pushKey, int image_no);
	public CallChkInfoDTO voiceCallInfoChk(int user_no, int partner_user_no, String pushKey);
	public CallChkInfoDTO voiceCallInfoChkV2(int user_no, int partner_user_no, String pushKey);
	public DefaultDTO callCancel(String room_id, int call_type);
	public DefaultDTO voiceCallCancel(String room_id, int call_type);
	public DefaultDTO callOk(String room_id, int req_user_no);
	public DefaultDTO voiceCallOk(String room_id, int req_user_no);
	public DefaultDTO callStart(String room_id);
	public DefaultDTO callStartV2(String room_id);
	public DefaultDTO voiceCallStart(String room_id);
	public DefaultDTO randomCallStart(String room_id, int user_no, String pushKey);
	public DefaultDTO randomCallCancel(String room_id, int finish_type, int user_no);
	public UserBalanceDTO randomCallFinish(int user_no, String room_id, int use_seconds, int finish_type);
	public DefaultDTO insertStar(LiveStarGradeDTO dto); // 별점 주기
	public UserPointDTO liveUsePoint(int user_no, String room_id, boolean endFlag); // 영상통화 시 포인트 차감 (도중/종료)
	public GetCashDTO liveGetCash(int user_no, String room_id); // 영상통화후 캐쉬 추가
//	public PresentPointDTO pointPresent(int user_no, int to_user_no, int point, String etc); // 영상통화 시 포인트 선물
//	public PresentPointDTO pointPresentV3(int user_no, int to_user_no, int point, String etc); // 영상통화 시 포인트 선물
	public PresentPointDTO pointPresentV4(int user_no, int to_user_no, int point, String etc); // 영상통화 시 포인트 선물
	public PresentPointDTO pointPresentMC(int user_no, int to_user_no, int point, String etc); // 미인캠 영상통화 시 포인트 선물
	public PresentPointDTO pointPresentV5(int user_no, int to_user_no, int point, String etc); // all 영상통화 시 포인트 선물
	public UseHistoryListDTO getUseList(int user_no, int use_type); // 사용 내역
	public CallHistoryListDTO getCallHistoryList(int user_no); // 사용 내역 > 통화 내역
	public HeartHistoryListDTO getHeartHistoryList(int user_no); // 사용 내역 > 통화 내역
	public LiveInfoDTO liveInfo(int user_no, int to_user_no); // 상대방 정보
	public RoomStatusResultDTO getRoomStatus(String room_id); // 대화 방 상태
	public UserBalanceDTO callFinish(int user_no, String room_id, int use_seconds, int finish_type); // 영상 통화 종료
	public UserBalanceDTO callFinishV2(int user_no, String room_id, int use_seconds, int finish_type); // 영상 통화 종료
	public UserBalanceDTO callFinishMC(int user_no, String room_id, int use_seconds, int finish_type); // 미인캠 영상 통화 통료

	public UserBalanceDTO voiceCallFinish(int user_no, String room_id, int use_seconds, int finish_type); // 음성 통화 종료
	public UserBalanceDTO voiceCallFinishV2(int user_no, String room_id, int use_seconds, int finish_type); // 음성 통화 종료
	public DefaultDTO getCashByCall(int user_no, String room_id, int get_cash, int partner_user_no); //통화 종료 캐시 지급
	public DefaultDTO payPointByCall(int user_no, String room_id, int pay_point, int pay_free_point, int partner_user_no); //통화 종료 포인트 차감



}