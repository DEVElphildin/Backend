package com.livelyit.allcam.mapper;

import java.util.ArrayList;

import com.livelyit.allcam.dto.ChatListDTO;
import com.livelyit.allcam.dto.UserInfoDTO;
import org.springframework.stereotype.Repository;

import com.livelyit.allcam.common.AbstractDAO;
import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.ChatDTO;
import com.livelyit.allcam.dto.ChatUserDTO;

@SuppressWarnings("unchecked")
@Repository("chatMapper")
public class ChatMapper extends AbstractDAO{	
	
	//내 채팅 전체 목록
	public ArrayList<ChatDTO> getMyChat(int user_no) {
		return (ArrayList<ChatDTO>) selectList(Utils.SQL_ALLCAM, "chatMapper.getMyChat", user_no);
	}

	//내 채팅 전체 목록 v2
	public ArrayList<ChatDTO> getMyChatV2(int user_no) {
		return (ArrayList<ChatDTO>) selectList(Utils.SQL_ALLCAM, "chatMapper.getMyChatV2", user_no);
	}

	//내 채팅 전체 목록 v3
	public ArrayList<ChatDTO> getMyChatV3(UserInfoDTO userInfoDTO) {
		return (ArrayList<ChatDTO>) selectList(Utils.SQL_ALLCAM, "chatMapper.getMyChatV3", userInfoDTO);
	}

	//1:1 채팅 내역
	public ArrayList<ChatDTO> getUserChat(ChatUserDTO dto) {
		return (ArrayList<ChatDTO>) selectList(Utils.SQL_ALLCAM, "chatMapper.getUserChat", dto);
	}

	//1:1 채팅 내역
	public ArrayList<ChatDTO> getUserChatV2(ChatUserDTO dto) {
		return (ArrayList<ChatDTO>) selectList(Utils.SQL_ALLCAM, "chatMapper.getUserChatV2", dto);
	}

	//1:1 채팅 내역
	public ArrayList<ChatDTO> getUserChatV3(ChatUserDTO dto) {
		return (ArrayList<ChatDTO>) selectList(Utils.SQL_ALLCAM, "chatMapper.getUserChatV3", dto);
	}

	//채팅 보내기
	public int sendChat(ChatDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "chatMapper.sendChat", dto);
	}

	//채팅 보내기
	public int sendAbsentCallChat(ChatDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "chatMapper.sendAbsentCallChat", dto);
	}

	//
	public int getCallChat(String absent_room_id) {
		return (int) selectOne(Utils.SQL_ALLCAM, "chatMapper.getCallChat", absent_room_id);
	}


	//채팅 +번역 보내기
	public int sendChatEN(ChatDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "chatMapper.sendChatEN", dto);
	}

	// 채팅 메세지 타입 저장하기
	public int sendChatV3(ChatDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "chatMapper.sendChatV3", dto);
	}

	// 채팅 메세지 타입 저장하기
	public int sendChatCallFinish(ChatDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "chatMapper.sendChatCallFinish", dto);
	}


	//채팅 여러개 동시 발송
	public int sendGroupChat(String value) {
		return (int) insert(Utils.SQL_ALLCAM, "chatMapper.sendGroupChat", value);
	}
	
	//내가 보낸 채팅 삭제
	public int deleteFromChat(ChatUserDTO dto) {
		return (int) delete(Utils.SQL_ALLCAM, "chatMapper.deleteFromChat", dto);
	}
		
	//내가 받은 채팅 삭제
	public int deleteToChat(ChatUserDTO dto) {
		return (int) delete(Utils.SQL_ALLCAM, "chatMapper.deleteToChat", dto);
	}
	
	//1:1 채팅 회수
	public int getUserChatCnt(ChatDTO dto) {
		return (int) selectOne(Utils.SQL_ALLCAM, "chatMapper.getUserChatCnt", dto);
	}

	//1:1 하루 채팅 횟수
	public int getUserOneDayChatCnt(ChatDTO dto) {
		return (int) selectOne(Utils.SQL_ALLCAM, "chatMapper.getUserOneDayChatCnt", dto);
	}
	
	//금지어 등록
	public int insertBanText(String value) {
		return (int) insert(Utils.SQL_ALLCAM, "chatMapper.banChatInsert", value);
	}	
	
}