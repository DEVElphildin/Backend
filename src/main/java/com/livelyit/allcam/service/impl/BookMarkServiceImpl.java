package com.livelyit.allcam.service.impl;

import java.net.URLEncoder;
import java.util.ArrayList;

import com.livelyit.allcam.dto.*;
import com.livelyit.allcam.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.mapper.BookMarkMapper;
import com.livelyit.allcam.mapper.ChatMapper;
import com.livelyit.allcam.mapper.LiveCallMapper;
import com.livelyit.allcam.mapper.UserMapper;
import com.livelyit.allcam.mapper.VersionMapper;
import com.livelyit.allcam.service.BookMarkService;
import com.livelyit.allcam.service.PushService;

@Service
public class BookMarkServiceImpl implements BookMarkService {
	@Autowired
	BookMarkMapper bookMapper;
	
	@Autowired
	ChatMapper chatMapper;
	
	@Autowired
	VersionMapper versionMapper;
	
	@Autowired
	UserMapper userMapper;
	
	@Autowired
	LiveCallMapper liveCallMapper;

	@Autowired
	PushService pushService;

	@Autowired
	UserService userService;
	// 즐겨찾기 추가
	@Override
	public DefaultDTO insertBookMark(BookMarkDTO dto) {
		int cnt = bookMapper.cntBookmark(dto);
		DefaultDTO resultDto = new DefaultDTO();
		
		int result = -1;
		if (cnt == 0) {
			result = bookMapper.insertBookMark(dto);
			resultDto.setSuccess_code("1");			// 즐겨찾기 없으면 추가
		}else {
			result = bookMapper.deleteBookMark(dto);						
			resultDto.setSuccess_code("2");			// 즐겨찾기 있으면 삭제
		}
		
		if(result == 1) {
			resultDto.setResult(Utils.SUCCESS);			
		}		
		
		return resultDto;
	}

	// 즐겨찾기 추가
	@Override
	public DefaultDTO insertBookMarkV2(BookMarkDTO dto) {
		int cnt = bookMapper.cntBookmark(dto);
		DefaultDTO resultDto = new DefaultDTO();

		UserInfoDTO userInfo = userMapper.getUserInfo(dto.getUser_no());
		UserInfoDTO partnerInfo = userMapper.getUserInfo(dto.getBookmark_user_no());
		int result = -1;
		if (cnt == 0) {
			result = bookMapper.insertBookMark(dto);

			if (result==1){

				JSONObject userInfoJson = new JSONObject();
				userInfoJson.put("from_user_no", userInfo.getNo());
				userInfoJson.put("user_nick_name", userInfo.getUser_nick_name());
				userInfoJson.put("user_imgs", userInfo.getUser_imgs());
				String userInfoStr = userInfoJson.toString();

				// 상대방이 나를 차단했는지 정보 가져오기
				int blackCnt = userService.chkUserBlackList(dto.getBookmark_user_no(), dto.getUser_no());

				if (blackCnt > 0) {    //블랙 당한경우 채팅 상대 방이 안보임


				}else {

					new Thread(() -> {
						// 즐겨찾기한 상대방에게 푸쉬 발송
						pushService.sendDataPush(partnerInfo.getUser_push_key(), PushType.BOOKMARKADD.getTypeCd(), userInfoStr, "", "");
					}).start();
				}

			}

			// if (result == 0)
			resultDto.setSuccess_code("1");			// 즐겨찾기 없으면 추가
		}else {
			result = bookMapper.deleteBookMark(dto);
			resultDto.setSuccess_code("2");			// 즐겨찾기 있으면 삭제
		}

		if(result == 1) {
			resultDto.setResult(Utils.SUCCESS);
		}

		return resultDto;
	}
	
	// 내/팬 즐겨찾기 리스트
	@Override
	public GetBookMarkDTO bookMarkList(int user_no) {
		GetBookMarkDTO resultDto = new GetBookMarkDTO();
		ArrayList<BookMarkDTO> bookmarkList = bookMapper.bookMarkList(user_no);
		ArrayList<BookMarkDTO> bookMarkUserList = bookMapper.bookMarkUserList(user_no);
		resultDto.setBookmarkList(bookmarkList);
		resultDto.setBookMarkFanList(bookMarkUserList);
		resultDto.setResult(Utils.SUCCESS);		
		
		return resultDto;
	}

	// 내/팬 즐겨찾기 리스트
	@Override
	public GetBookMarkDTO bookMarkListV2(int user_no) {
//		UserInfoDTO userInfoDTO = userMapper.getUserInfo(user_no);
//		GetBookMarkDTO resultDto = new GetBookMarkDTO();
//
//
//		if (userInfoDTO.getUser_service_type().equals("allcam")
//				&& userInfoDTO.getUser_gender().equals("여성")){
//
//			// 다른 앱 남자들은 제외하기
//			resultDto.setResult(Utils.SUCCESS);
//			ArrayList<BookMarkDTO> bookmarkList = bookMapper.bookMarkListAllcamMan(user_no);
//			ArrayList<BookMarkDTO> bookMarkUserList = bookMapper.bookMarkUserListAllcamMan(user_no);
//			resultDto.setBookmarkList(bookmarkList);
//			resultDto.setBookMarkFanList(bookMarkUserList);
//			return resultDto;
//		}
//
//		if (!userInfoDTO.getUser_service_type().equals("allcam")
//				&& userInfoDTO.getUser_gender().equals("남성")){
//
//			// call_type이 1인 여성 회원만 볼 수 있도록 하기
//			resultDto.setResult(Utils.SUCCESS);
//			ArrayList<BookMarkDTO> bookmarkList = bookMapper.bookMarkListVideo(user_no);
//			ArrayList<BookMarkDTO> bookMarkUserList = bookMapper.bookMarkUserListVideo(user_no);
//			resultDto.setBookmarkList(bookmarkList);
//			resultDto.setBookMarkFanList(bookMarkUserList);
//			return resultDto;
//		}
//		resultDto.setResult(Utils.SUCCESS);
//		ArrayList<BookMarkDTO> bookmarkList = bookMapper.bookMarkList(user_no);
//		ArrayList<BookMarkDTO> bookMarkUserList = bookMapper.bookMarkUserList(user_no);
//
		GetBookMarkDTO resultDto = new GetBookMarkDTO();
		ArrayList<BookMarkDTO> bookmarkList = bookMapper.bookMarkList(user_no);
		ArrayList<BookMarkDTO> bookMarkUserList = bookMapper.bookMarkUserList(user_no);
		resultDto.setResult(Utils.SUCCESS);
		resultDto.setBookmarkList(bookmarkList);
		resultDto.setBookMarkFanList(bookMarkUserList);

		return resultDto;
	}

	@Override
	public UserPointDTO sendBookMarkGroupMsg(int user_no, String msg) {
		UserPointDTO resultDto = new UserPointDTO();		
		
		boolean banChkFlag = Utils.filterBanText(msg, versionMapper.getBanText("채팅"));
		
		if(banChkFlag) {	//금지어 포함되어 있음
			resultDto.setError_code("2");	//금지어 에러는 늘 2번
		}else {
			UserInfoDTO myInfo = userMapper.getUserInfo(user_no);     //포인트 체크
			
			int myPoint = myInfo.getUser_point();
			
			if(myPoint >= 1000) {	//포인트가 1000이 넘으면 발송 가능 아니면 에러
				ArrayList<GroupMsgDTO> bookmarkUserList = bookMapper.getBookmarkGroup(user_no);	 //북마크 리스트
				
				if(bookmarkUserList.size() > 0) {
					String chatValue = "";	//채팅 입력 VALUE 생성
					for(GroupMsgDTO dto : bookmarkUserList) {
						chatValue = chatValue + ",(" +user_no+ ", " +dto.getBookmark_user_no()+ ", '" +msg+ "')";
					}
					chatValue = chatValue.substring(1);
					
					int chatLenght = chatMapper.sendGroupChat(chatValue);
					
					if(bookmarkUserList.size() == chatLenght) {
						int deductionPoint = 1000; // 차감포인트
						int deductionFreePoint = (myInfo.getUser_free_point() >= deductionPoint? deductionPoint : myInfo.getUser_free_point());
						int deductionPayPoint = (myInfo.getUser_free_point() >= deductionPoint? 0 : deductionPoint - deductionFreePoint);
						
						UserPointDTO pointDto = new UserPointDTO();
						pointDto.setNo(user_no);
						pointDto.setUser_free_point(myInfo.getUser_free_point() - deductionFreePoint);
						pointDto.setUser_pay_point(myInfo.getUser_pay_point() - deductionPayPoint);
						
						int pResult = userMapper.setPoint(pointDto);	//포인트 차감
						
						if(pResult == 1) {
							UseHistoryDTO hDto = new UseHistoryDTO();
							hDto.setUser_no(user_no);
							hDto.setUse_etc("BOOKMARK_GROUP_CHAT");
							hDto.setUse_content("즐겨찾기 단체 메시지 발송");
							
							int uResult = 0;
							if(deductionFreePoint > 0) {
								hDto.setUse_cnt(-deductionFreePoint);
								hDto.setUse_type(2);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트	
								
								uResult = liveCallMapper.insertUseHistory(hDto);	//포인트 사용 내역 등록
							}
							if(deductionPayPoint > 0) {
								hDto.setUse_cnt(-deductionPayPoint);
								hDto.setUse_type(0);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트	
								
								uResult = liveCallMapper.insertUseHistory(hDto);	//포인트 사용 내역 등록
							}
							
							if(uResult == 1) {	//결과
								resultDto.setUse_point(deductionPoint);
								resultDto.setUser_point(myInfo.getUser_point() - deductionPoint);
								
								resultDto.setResult(Utils.SUCCESS);
								
								for(GroupMsgDTO dto : bookmarkUserList) {
									String chatText = msg;
									String nickNameText = myInfo.getUser_nick_name();
									
									JSONObject chatData = new JSONObject();
									chatData.put("from_user_no", user_no);
									chatData.put("chat_text", chatText);
									chatData.put("from_user_nick", nickNameText);
									chatData.put("from_user_thumnail", "NONE".equals(myInfo.getUser_imgs()) ? "NONE":Utils.getImgType(new JSONArray(myInfo.getUser_imgs()), "small"));
									
									pushService.sendDataPush(dto.getUser_push_key(), PushType.CHATGET.getTypeCd(), chatData.toString(), nickNameText, chatText);	//상대 방한테 푸쉬 콜
								}
								
							}
						}					
					}
				}else {
					resultDto.setError_code("3");	//즐겨 찾기가 없음
				}
			}else {	//포인트 에러는 늘 1번
				resultDto.setError_code("1");
			}
		}
		
		return resultDto;
	}
}