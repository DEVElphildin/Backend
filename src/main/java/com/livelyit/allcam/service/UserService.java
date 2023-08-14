package com.livelyit.allcam.service;



import com.livelyit.allcam.dto.*;

public interface UserService {
//	LoginDTO join(LoginDTO dto);
//	LoginDTO joinV2(LoginDTO dto);
//	LoginDTO joinV4(LoginDTO dto);
//	LoginDTO joinV5(LoginDTO dto);
//	LoginDTO joinV6(LoginDTO dto);
	LoginDTO joinV7(LoginDTO dto);
	LoginDTO joinV8(LoginDTO dto);
	LoginDTO joinV9(LoginDTO dto);

//	LoginDTO login(LoginDTO dto);
//	LoginDTO loginV2(LoginDTO dto);
	LoginDTO loginV4(LoginDTO dto);
	LoginDTO loginV5(LoginDTO dto);
	LoginDTO loginV6(LoginDTO dto);
	UserInfoDTO updateVoipKey(LoginDTO dto);
	UserInfoDTO updateCertification(LoginDTO dto);

	DefaultDTO checkAgentId(String agentId);
	LoginDTO logOut(int user_no);
//	ConversationListDTO conversationList(); // 회원가입 시 대화주제 선택
	ConversationListDTO conversationListV2(String language_code); // 회원가입 시 대화주제 선택
	ConversationListDTO conversationListV3(String language_code); // 회원가입 시 대화주제 선택
	LikeResultDTO insertLike(LikeDTO likedto); //좋아요 추가
	LikeResultDTO insertLikeV2(LikeDTO likedto); //좋아요 추가
	int likeCheck(LikeDTO dto); //좋아요 날짜계산
	MainBlackListDTO myBlackList(int user_no); //차단목록
	BlackDTO insertBlackList(BlackListDTO dto); //차단하기
	UserPointDTO getMyPoint(int user_no); //내 포인트 확인
	SetAlarmDTO setMyAlaram(int user_no, int alarm_status); //내 알림 받기 수정
	SetAlarmDTO setUserSetting(int user_no, int set_type, int set_status);	//알림 전체 셋팅
	UserPointDTO attendanceChk(int user_no); // 출석 체크
	UserModifyDTO updateProfile(UserProfileUpdateDTO dto);  //프로필 업데이트 포인트X
	UserModifyDTO updateProfileV2(UserProfileUpdateDTO dto);  //프로필 업데이트 포인트X
	UserModifyDTO updateProfileV3(UserProfileUpdateDTO dto);  //닉변경 10P
	UserRenkingListDTO selectUserRankingList(int user_no, String order); // 랭킹 구버전
	UserRenkingListDTO selectUserRankingList(int user_no, String gender, String order); // 랭킹 신버전
	UserRenkingListDTO selectUserRankingListMC(int user_no, String gender, String order); // 미인캠 전용 랭킹

	DefaultDTO sendReport(int user_no, int to_user_no, int report_type, String report_content); // 신고하기
	DefaultDTO updateUserOut(int user_no); // 회원 탈퇴
	UserRefundsHistoryDTO selectUserRefundsHistory(int user_no);	//환급내역 리스트
	UserRefundsDTO updateUserRefundsInfo(UserRefundsUpdateDTO dto);	//환급신청정보 업데이트
	UserRefundsDTO updateUserRefundsInfoSave(UserRefundsUpdateDTO dto);	//환급신청정보 저장하기
	UserRefundsViewDTO selectUserRefunds(int no);		//환급정보 불러오기
	int chkUserBlackList(int user_no, int black_user_no);		//블랙 체크 여부 확인
	UserModifyDTO updateUserNewbieTime(UserProfileUpdateDTO dto);
	DefaultDTO insertNewbieLevelUp(NewbieLevelUpDTO dto); //새내기 일반화원 전환 신청
	DefaultDTO insertExchangeHeart(UserExchangeHeartDTO dto); //하드 환전 신청

    DefaultDTO checkPartnerInstall(String adid); //partner.allcam 기록 확인
	DefaultDTO verifyPartnerInstall(String adid, String partner_id); //partner id : adid 매칭 검증

	DefaultDTO checkNicknameUsed(String user_nick_name); //닉네임 사용여부 확인
}