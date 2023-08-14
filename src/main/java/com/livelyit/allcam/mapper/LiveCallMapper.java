package com.livelyit.allcam.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import com.livelyit.allcam.dto.*;
import org.springframework.stereotype.Repository;

import com.livelyit.allcam.common.AbstractDAO;
import com.livelyit.allcam.common.Utils;

@SuppressWarnings("unchecked")
@Repository("liveCallMapper")
public class LiveCallMapper extends AbstractDAO{	
	public int insertWaiting(int user_no) {
		return (int) delete(Utils.SQL_ALLCAM, "liveCallMapper.insertWaiting", user_no);
	}

	public int deleteWaiting(int user_no) {
		return (int) delete(Utils.SQL_ALLCAM, "liveCallMapper.deleteWaiting", user_no);
	}

	public ArrayList<WatingUserDTO> getWatingUser(MainLiveCallDTO dto) {
		return (ArrayList<WatingUserDTO>) selectList(Utils.SQL_ALLCAM, "liveCallMapper.getWatingUser", dto);
	}

	public ArrayList<WatingUserDTO> getWatingUserV2(MainLiveCallDTO dto) {
		return (ArrayList<WatingUserDTO>) selectList(Utils.SQL_ALLCAM, "liveCallMapper.getWatingUserV2", dto);
	}

	public ArrayList<WatingUserDTO> getWatingUserMC(MainLiveCallDTO dto) {
		return (ArrayList<WatingUserDTO>) selectList(Utils.SQL_ALLCAM, "liveCallMapper.getWatingUserMC", dto);
	}

	public ArrayList<WatingUserDTO> getWatingUserV3Man(MainLiveCallDTO dto) {
		return (ArrayList<WatingUserDTO>) selectList(Utils.SQL_ALLCAM, "liveCallMapper.getWatingUserV3Man", dto);
	}

	public ArrayList<WatingUserDTO> getWatingUserV3Woman(MainLiveCallDTO dto) {
		return (ArrayList<WatingUserDTO>) selectList(Utils.SQL_ALLCAM, "liveCallMapper.getWatingUserV3Woman", dto);
	}

	public ArrayList<WatingUserDTO> getWatingUserV4Man(MainLiveCallDTO dto) {
		return (ArrayList<WatingUserDTO>) selectList(Utils.SQL_ALLCAM, "liveCallMapper.getWatingUserV4Man", dto);
	}

	public ArrayList<WatingUserDTO> getWatingUserV4Woman(MainLiveCallDTO dto) {
		return (ArrayList<WatingUserDTO>) selectList(Utils.SQL_ALLCAM, "liveCallMapper.getWatingUserV4Woman", dto);
	}

	public ArrayList<WatingUserDTO> getWatingUserV4ManVoice(MainLiveCallDTO dto) {
		return (ArrayList<WatingUserDTO>) selectList(Utils.SQL_ALLCAM, "liveCallMapper.getWatingUserV4ManVoice", dto);
	}

	public ArrayList<WatingUserDTO> getWatingUserV4WomanVoice(MainLiveCallDTO dto) {
		return (ArrayList<WatingUserDTO>) selectList(Utils.SQL_ALLCAM, "liveCallMapper.getWatingUserV4WomanVoice", dto);
	}

	public ArrayList<WatingUserDTO> getWatingUserNonMember(MainLiveCallDTO dto) {
		return (ArrayList<WatingUserDTO>) selectList(Utils.SQL_ALLCAM, "liveCallMapper.getWatingUserNonMember", dto);
	}

	public ArrayList<RandomImageDTO> getRandomImage(RandomImageDTO dto) {
		return (ArrayList<RandomImageDTO>) selectList(Utils.SQL_ALLCAM, "liveCallMapper.getRandomImage", dto);
	}
	public ArrayList<NewbieUserDTO> getNewbieUserList(MainLiveCallDTO dto) {
		return (ArrayList<NewbieUserDTO>) selectList(Utils.SQL_ALLCAM, "liveCallMapper.getNewbieUserList", dto);
	}

	public ArrayList<NewbieUserDTO> getNewbieUserListV2(MainLiveCallDTO dto) {
		return (ArrayList<NewbieUserDTO>) selectList(Utils.SQL_ALLCAM, "liveCallMapper.getNewbieUserListV2", dto);
	}

	public ArrayList<NewbieUserDTO> getNewUserList(MainLiveCallDTO dto) {
		return (ArrayList<NewbieUserDTO>) selectList(Utils.SQL_ALLCAM, "liveCallMapper.getNewUserList", dto);
	}

	public int absentCallCount(int user_no) {
		return (int) selectOne(Utils.SQL_ALLCAM, "liveCallMapper.absentCallCount", user_no);
	}

	public UserInfoDTO getMyUserNo(String room_id){
		return (UserInfoDTO) selectOne(Utils.SQL_ALLCAM, "liveCallMapper.getMyUserNo", room_id);
	}
	
	public int insertRoomStatus(RoomStatusDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "liveCallMapper.insertRoomStatus", dto);
	}
	
	public int updateRoomStatus(RoomStatusDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "liveCallMapper.updateRoomStatus", dto);
	}

	public int updateRandomRoomStatus(RoomStatusDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "liveCallMapper.updateRandomRoomStatus", dto);
	}

	public int getPartnerChk(int partner_user_no){
		return (int) selectOne(Utils.SQL_ALLCAM, "liveCallMapper.partnerChk", partner_user_no);
	}

	public RoomPushKeyDTO getRoomPushKey(String room_id) {
		return (RoomPushKeyDTO) selectOne(Utils.SQL_ALLCAM, "liveCallMapper.getRoomPushKey", room_id);
	}

	public RoomPushKeyDTO getRoomPushKeyFlag(String room_id) {
		return (RoomPushKeyDTO) selectOne(Utils.SQL_ALLCAM, "liveCallMapper.getRoomPushKeyFlag", room_id);
	}

	public int updateRoomPushFlag(String room_id) {
		return (int) update(Utils.SQL_ALLCAM, "liveCallMapper.updateRoomPushFlag" , room_id);
	}


	public int insertStar(LiveStarGradeDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "liveCallMapper.insertStar", dto);
	}
	
	public int setLiveTime(LiveTimeDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "liveCallMapper.setLiveTime", dto);
	}
	
	public String getEndTime(String room_id) {
		return (String) selectOne(Utils.SQL_ALLCAM, "liveCallMapper.getEndTime", room_id);
	}

	public int getLiveUsePoint(LiveHistoryDTO dto) {
		return (int) selectOne(Utils.SQL_ALLCAM, "liveCallMapper.getLiveUsePoint", dto);
	}

	public RoomTimeDTO getRoomTime(String room_id) {
		return (RoomTimeDTO) selectOne(Utils.SQL_ALLCAM, "liveCallMapper.getRoomTime", room_id);
	}
	
	public int insertUseHistory(UseHistoryDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "liveCallMapper.insertUseHistory", dto);
	}

	public int isLive(IsLiveDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "liveCallMapper.isLive", dto);
	}
	
	public ArrayList<UseHistoryDTO> getUseList(UseHistoryDTO dto) {
		return (ArrayList<UseHistoryDTO>) selectList(Utils.SQL_ALLCAM, "liveCallMapper.getUseList", dto);
	}

	public ArrayList<CallChkUserDTO> getRandomCall(String user_gender) {
		return (ArrayList<CallChkUserDTO>) selectList(Utils.SQL_ALLCAM, "liveCallMapper.getRandomCall", user_gender);
	}

	public int fixErrorCall(UserInfoDTO myInfo) {
		return (int) update(Utils.SQL_ALLCAM, "liveCallMapper.fixErrorCall", myInfo);
	}

	public int updateRoomPartner(CallChkUserDTO callChkUserDTO) {
		return (int) update(Utils.SQL_ALLCAM, "liveCallMapper.updateRoomPartner" ,callChkUserDTO);
	}

	public CallChkUserDTO getRoomInfo(CallChkUserDTO callChkUserDTO) {
		return (CallChkUserDTO) selectOne(Utils.SQL_ALLCAM, "liveCallMapper.getRoomInfo", callChkUserDTO);
	}

	public int updateRandomImageHit(int image_no) {
		return (int) update(Utils.SQL_ALLCAM, "liveCallMapper.updateRandomImageHit" , image_no);
	}

	public int updateRandomCallRoom(LiveTimeDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "liveCallMapper.updateRandomCallRoom", dto);
	}

	public ArrayList<CallHistoryDTO> getCallHistoryList(CallHistoryDTO dto) {
		return (ArrayList<CallHistoryDTO>) selectList(Utils.SQL_ALLCAM, "liveCallMapper.getCallHistoryList", dto);
	}

	public ArrayList<HeartHistoryDTO> getHeartHistoryList(HeartHistoryDTO dto) {
		return (ArrayList<HeartHistoryDTO>) selectList(Utils.SQL_ALLCAM, "liveCallMapper.getHeartHistoryList", dto);
	}
	
	public LiveInfoDTO liveInfo(LikeDTO dto) {
		return (LiveInfoDTO) selectOne(Utils.SQL_ALLCAM, "liveCallMapper.liveInfo", dto);
	}

	public RoomStatusDTO getRoomStatus(String room_id) {
		return (RoomStatusDTO) selectOne(Utils.SQL_ALLCAM, "liveCallMapper.getRoomStatus", room_id);
	}

	public int updateCallFinish(CallFinishDTO dto){
		return (int) update(Utils.SQL_ALLCAM, "liveCallMapper.updateCallFinish", dto);
	}

	public int updateCallFinishV2(CallFinishDTO dto){
		return (int) update(Utils.SQL_ALLCAM, "liveCallMapper.updateCallFinishV2", dto);
	}

	public int updateVoiceCallFinish(CallFinishDTO dto){
		return (int) update(Utils.SQL_ALLCAM, "liveCallMapper.updateVoiceCallFinish", dto);
	}

	public int updateVoiceCallFinishV2(CallFinishDTO dto){
		return (int) update(Utils.SQL_ALLCAM, "liveCallMapper.updateVoiceCallFinishV2", dto);
	}

	public GetCashByCallDTO uspGetCashByCall(GetCashByCallDTO dto){
		return (GetCashByCallDTO) selectOne(Utils.SQL_ALLCAM, "liveCallMapper.uspGetCashByCall", dto);
	}

	public PayPointByCallDTO uspPayPointByCall(PayPointByCallDTO dto){
		return (PayPointByCallDTO) selectOne(Utils.SQL_ALLCAM, "liveCallMapper.uspPayPointByCall", dto);
	}
}