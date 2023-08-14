package com.livelyit.allcam.mapper;

import java.util.ArrayList;

import com.livelyit.allcam.dto.*;
import org.springframework.stereotype.Repository;

import com.livelyit.allcam.common.AbstractDAO;
import com.livelyit.allcam.common.Utils;

@Repository("userMapper")
public class UserMapper extends AbstractDAO{	
	public int join(LoginDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "userMapper.join", dto);
	}

	public int joinV2(LoginDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "userMapper.joinV2", dto);
	}

	public int joinV3(LoginDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "userMapper.joinV3", dto);
	}
	
	public int joinV4(LoginDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "userMapper.joinV4", dto);
	}

	public int joinV5(LoginDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "userMapper.joinV5", dto);
	}

	public int joinV6(LoginDTO dto) {return (int) insert(Utils.SQL_ALLCAM, "userMapper.joinV6", dto);}
	// 핸드폰 인증 여부 저장
	public int joinV7(LoginDTO dto) {return (int) insert(Utils.SQL_ALLCAM, "userMapper.joinV7", dto);}

	public int joinV6PhoneNum(LoginDTO dto) {return (int) insert(Utils.SQL_ALLCAM, "userMapper.joinV6", dto);}

	public int checkAdmin(String adminId){
		return (int) selectOne(Utils.SQL_ALLCAM, "userMapper.checkAdmin", adminId);
	}

	public int updatePartTime(LoginDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "userMapper.updatePartTime", dto);
	}

	public int updateCallType(LoginDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "userMapper.updateCallType", dto);
	}

	public LoginDTO login(LoginDTO dto) {
		return (LoginDTO) selectOne(Utils.SQL_ALLCAM, "userMapper.login", dto);
	}
	public LoginDTO loginV2(LoginDTO dto) {
		return (LoginDTO) selectOne(Utils.SQL_ALLCAM, "userMapper.loginV2", dto);
	}
	public int nickNameChk(String nickName) {
		return (int) selectOne(Utils.SQL_ALLCAM, "userMapper.nickNameChk", nickName);
	}
	
	public int nickNameChk2(UserProfileUpdateDTO dto) {
		return (int) selectOne(Utils.SQL_ALLCAM, "userMapper.nickNameChk2", dto);
	}

	public int insertCertPartner(LoginDTO dto){
		return (int) insert(Utils.SQL_ALLCAM, "userMapper.insertCertPartner", dto);
	}
	public int updateCertUser(LoginDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "userMapper.updateCertUser", dto);
	}

	public int updateUserIsCertified(LoginDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "userMapper.updateUserIsCertified", dto);
	}

	public int checkCertNo(int certNo){
		return (int) selectOne(Utils.SQL_ALLCAM, "userMapper.checkCertNo", certNo);
	}


	public int deduplicationPushKey(LoginDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "userMapper.deduplicationPushKey", dto);
	}

	public int deduplicationVoipPushKey(LoginDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "userMapper.deduplicationVoipPushKey", dto);
	}
	
	public int setPushKey(LoginDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "userMapper.setPushKey", dto);
	}

	public int setVoipPushKey(LoginDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "userMapper.setVoipPushKey", dto);
	}
	
	public int setCountryLanguage(LoginDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "userMapper.setCountryLanguage", dto);
	}

	public int setPushKey(UserPushTokenDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "userMapper.setPushKey", dto);
	}

	public int setVoipPushKey(UserPushTokenDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "userMapper.setVoipPushKey", dto);
	}

	public UserInfoDTO getUserInfo(int user_no) {
		return (UserInfoDTO) selectOne(Utils.SQL_ALLCAM, "userMapper.userInfo", user_no);
	}
		
	public int userThumnailInsert(LoginDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "userMapper.userThumnailInsert", dto);
	}
		
	// 연결 유무 체크
	public int waitingCheck (int user_no) {
		return(int) selectOne(Utils.SQL_ALLCAM,"userMapper.waitingCheck", user_no) ;
	}
	
	// 좋아요 추가
	public int insertLike(LikeDTO likedto) {
		return  (int) insert(Utils.SQL_ALLCAM, "userMapper.insertLike", likedto);
	}
	
	// 좋아요 리스트
	public LikeCheckDTO likeCheck(LikeDTO dto) {
		return (LikeCheckDTO) selectOne(Utils.SQL_ALLCAM, "userMapper.likeCheck", dto);
	}
	
	// 차단 리스트
	public ArrayList<BlackListDTO> myBlackList(int user_no) {
		return (ArrayList<BlackListDTO>) selectList(Utils.SQL_ALLCAM, "userMapper.selectMyBlackList", user_no);
	}
	
	// 로그인 이력
	public int loginLog(LoginLogDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "userMapper.loginLog", dto);
	}

	// 에이전트 아이디 정보
	public AgentDTO getAgentCashInfo(String admin_id) {
		return (AgentDTO) selectOne(Utils.SQL_ALLCAM, "userMapper.getAgentCashInfo", admin_id);
	}


	//차단하기
	public int insertBlackList (BlackListDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "userMapper.insertBlackList", dto);		
	}
	
	//차단 취소
	public  int deleteBlackList(BlackListDTO dto) {
		return  (int) delete(Utils.SQL_ALLCAM, "userMapper.deleteBlackList", dto);	
	}
	
	public int cntBlackList(BlackListDTO dto) {
		return (int) selectOne(Utils.SQL_ALLCAM, "userMapper.cntBlackList", dto);
	}
	
	public UserPointDTO getMyPoint(int user_no) {
		return (UserPointDTO) selectOne(Utils.SQL_ALLCAM, "userMapper.getMyPoint", user_no);
	}
	
	public GetCashDTO getMyCash(int user_no) {
		return (GetCashDTO) selectOne(Utils.SQL_ALLCAM, "userMapper.getMyCash", user_no);
	}
	
	public int setPoint(UserPointDTO dto) {	//사용자 포인트 변경
		return (int) update(Utils.SQL_ALLCAM, "userMapper.setPoint", dto);	
	}

	public int setCash(GetCashDTO dto) {	//사용자 캐쉬 변경
		return (int) update(Utils.SQL_ALLCAM, "userMapper.setCash", dto);	
	}
	
	public int setMyAleram(SetAlarmDTO dto) {	//사용자 알람 받기 변경
		return (int) update(Utils.SQL_ALLCAM, "userMapper.setAlarm", dto);	
	}
	
	public int setUserSetting(SetAlarmDTO dto) {	//알림 전체 셋팅
		return (int) update(Utils.SQL_ALLCAM, "userMapper.setUserSetting", dto);	
	}
	
	public int getTodayAttendance(AttendanceDTO dto) {	//출책 확인용
		return (int) selectOne(Utils.SQL_ALLCAM, "userMapper.getTodayAttendance", dto);
	}
	
	public int setTodayAttendance(int user_no) {
		return (int) insert(Utils.SQL_ALLCAM, "userMapper.setTodayAttendance", user_no);
		
	}

	//닉네임 체크
	public String selectCheckNickname(int user_no) {
		return (String) selectOne(Utils.SQL_ALLCAM,"userMapper.selectCheckNickname", user_no);
	}
	 //프로필 업데이트 포인트X 
	public int updateProfile (UserProfileUpdateDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "userMapper.updateProfile" ,dto);
	}

	//프로필 업데이트 포인트X
	public int updateProfileV2(UserProfileUpdateDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "userMapper.updateProfileV2" ,dto);
	}

	//프로필 업데이트 - 직접 입력한 대화 주제
	public int updateProfileV2CovTxt(UserProfileUpdateDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "userMapper.updateProfileV2CovTxt" ,dto);
	}
	  	
	//프로필 업데이트 포인트 -50 
	public int updateProfilePoint(UserProfileUpdateDTO dto){
		 return (int) update(Utils.SQL_ALLCAM, "userMapper.updateProfilePoint" ,dto);
	 }

	//프로필 업데이트 포인트 -50
	public int updateProfilePointV2(UserProfileUpdateDTO dto){
		return (int) update(Utils.SQL_ALLCAM, "userMapper.updateProfilePointV2" ,dto);
	}
	  	
	  //프로필이미지 등록
	public int insertProfileImg(UserThumnailApproDTO dto){
		  return (int) insert(Utils.SQL_ALLCAM, "userMapper.insertProfileImg" ,dto);
	  }
	  
	 //포인트 로그
	public int insertUserHistory(UseHistoryDTO dto){
		 return (int) insert(Utils.SQL_ALLCAM, "userMapper.insertUserHistory" ,dto);
	 }

	  //프로필 등록 포인트를 받았었는지 확인 
	public int selectFirstlogCnt(int user_no){
		 return (int) selectOne(Utils.SQL_ALLCAM, "userMapper.updateProfile" ,user_no);
	 }

	//랭킹
	public ArrayList<UserRankingDTO> selectUserRankingList(MainLiveCallDTO dto){
		return ( ArrayList<UserRankingDTO> ) selectList(Utils.SQL_ALLCAM, "userMapper.selectUserRankingList", dto);
	}

	//여자 랭킹
	public ArrayList<UserRankingDTO> selectFamaleRankingList(MainLiveCallDTO dto){
		return ( ArrayList<UserRankingDTO> ) selectList(Utils.SQL_ALLCAM, "userMapper.selectFamaleRankingList", dto);
	}

	//남자 랭킹
	public ArrayList<UserRankingDTO> selectMaleRankingList(MainLiveCallDTO dto){
		return ( ArrayList<UserRankingDTO> ) selectList(Utils.SQL_ALLCAM, "userMapper.selectMaleRankingList", dto);
	}

	//미인캠  랭킹
	public ArrayList<UserRankingDTO> selectMaleRankingListMC(MainLiveCallDTO dto){
		return ( ArrayList<UserRankingDTO> ) selectList(Utils.SQL_ALLCAM, "userMapper.selectMaleRankingListMC", dto);
	}

	//미인캠  랭킹
	public ArrayList<UserRankingDTO> selectFemaleRankingListMC(MainLiveCallDTO dto){
		return ( ArrayList<UserRankingDTO> ) selectList(Utils.SQL_ALLCAM, "userMapper.selectFemaleRankingListMC", dto);
	}

	// 나와 통화한 사람들 리스트 가져오기
	public ArrayList<MyVideoCallUserDTO> selectMyVideoCallUserList(LoginDTO dto){
		return ( ArrayList<MyVideoCallUserDTO> ) selectList(Utils.SQL_ALLCAM, "userMapper.selectMyVideoCallUserList", dto);
	}
	
	//신고하기 
	public int sendReport(ReportDTO dto){
		 return (int) insert(Utils.SQL_ALLCAM, "userMapper.sendReport" ,dto);
	}
	
	public int selectUserNo(UserIDServiceTypeDTO dto) {
		return (int) selectOne(Utils.SQL_ALLCAM, "userMapper.selectUserNo", dto);
	}

	public int updateUserFreePoint(int user_no) {
		return (int) update(Utils.SQL_ALLCAM, "userMapper.updateUserFreePoint", user_no);
	}
	
	public int deleteProfileImg(int user_no) {
		return (int) delete(Utils.SQL_ALLCAM, "userMapper.deleteProfileImg", user_no);
	}
	
	public int selectUserProfileImg(int user_no) {
		return (int) selectOne(Utils.SQL_ALLCAM, "userMapper.selectUserProfileImg", user_no);		
	}
	
	public int updateUserOut(int user_no) {
		return (int) update(Utils.SQL_ALLCAM, "userMapper.updateUserOut", user_no);		
	}
	
	public int updateUserAlarm(int user_no) {
		return (int) update(Utils.SQL_ALLCAM, "userMapper.updateUserAlarm", user_no);
	}
	
	public String selectUserGender(int user_no) {
		return (String) selectOne(Utils.SQL_ALLCAM, "userMapper.selectUserGender", user_no);		
	}
	
	//환급내역 리스트
	public ArrayList<UserRefundsHistoryListDTO> selectUserRefundsHistory(int user_no){
		return ( ArrayList<UserRefundsHistoryListDTO> ) selectList(Utils.SQL_ALLCAM, "userMapper.selectUserRefundsHistory", user_no);
	}
	
	//환급신청정보 업데이트
	public int updateUserRefundsInfo(UserRefundsUpdateDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "userMapper.updateUserRefundsInfo", dto);
	}

	// 유저 로그인 푸시 받은 마지막 시간 업데이트
	public int updateUserLastPush(MyVideoCallUserDTO myVideoCallUserDTO) {
		return (int) update(Utils.SQL_ALLCAM, "userMapper.updateUserLastPush", myVideoCallUserDTO);
	}
	
	//환급신청 히스토리 등록
	public int insertUserRefundsHistory(UserRefundsHistoryListDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "userMapper.insertUserRefundsHistory", dto);
	}
	
	//환급정보 불러오기
	public UserRefundsViewDTO selectUserRefunds(int no) {
		return ( UserRefundsViewDTO) selectOne(Utils.SQL_ALLCAM, "userMapper.selectUserRefunds", no);
	}
	
	//환급정보 등록
	public int insertUserRefundsInfo(RefundsInfoDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "userMapper.insertUserRefundsInfo", dto);
	}
		
	//차단 체크
	public int chkUserBlackList(CallChkUserDTO dto) {
		return (int) selectOne(Utils.SQL_ALLCAM, "userMapper.chkUserBlackList", dto);
	}

	public int chkStationaryUser(LoginDTO dto) {
		return (int) selectOne(Utils.SQL_ALLCAM, "userMapper.chkStationaryUser", dto);
	}

	public int updateUserPhoneNumber(LoginDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "userMapper.updateUserPhoneNumber", dto);
	}

	public int updateUserInfo(LoginDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "userMapper.updateUserInfo", dto);
	}

	public int updateUserNewbieTime(UserProfileUpdateDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "userMapper.updateUserNewbieTime", dto);
	}

	//새내 랭킹
	public ArrayList<UserRankingDTO> selectNewbieRankingList(MainLiveCallDTO dto){
		return ( ArrayList<UserRankingDTO> ) selectList(Utils.SQL_ALLCAM, "userMapper.selectNewbieRankingList", dto);
	}

	//새내기 일반회원 전환 신청
	public DefaultDTO insertNewbieLevelUp(NewbieLevelUpDTO dto) {
		return (DefaultDTO) selectOne(Utils.SQL_ALLCAM, "userMapper.insertNewbieLevelUp", dto);
	}

	//하트 환급 힌청
	public  UserExchangeHeartDTO insertExchangeHeart(UserExchangeHeartDTO dto){
		return (UserExchangeHeartDTO) selectOne(Utils.SQL_ALLCAM, "userMapper.insertExchangeHeart", dto);
	}

	public int updateUserCountryCode(LoginDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "userMapper.updateUserCountryCode", dto);
	}

	public int updateUserLanguageCode(LoginDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "userMapper.updateUserLanguageCode", dto);
	}

	public int selectRandomPartner(int user_no) {
		return (int) selectOne(Utils.SQL_ALLCAM, "userMapper.selectRandomPartner", user_no);
	}

	public  AttendanceEventDTO attendanceEvent(AttendanceEventDTO dto){
		return (AttendanceEventDTO) selectOne(Utils.SQL_ALLCAM, "userMapper.attendanceEvent", dto);
	}

	public  CouponjoPointDTO insertCouponjoPoint(CouponjoPointDTO dto){
		return (CouponjoPointDTO) selectOne(Utils.SQL_ALLCAM, "userMapper.insertCouponjoPoint", dto);
	}


}