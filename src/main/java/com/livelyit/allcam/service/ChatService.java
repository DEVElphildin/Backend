package com.livelyit.allcam.service;

import com.livelyit.allcam.dto.ChatListDTO;

public interface ChatService {
	ChatListDTO getMyChat(int user_no);	//내 채팅 전체 목록
	ChatListDTO getMyChatV2(int user_no);	//내 채팅 전체 목록 v2
	ChatListDTO getMyChatV3(int user_no);	//내 채팅 전체 목록 v2
	ChatListDTO getUserChat(int user_no, int to_user_no);	//1:1 채팅 내용
	ChatListDTO getUserChatV2(int user_no, int to_user_no);	//1:1 채팅 내용 v2
	ChatListDTO getUserChatV3(int user_no, int to_user_no);	//1:1 채팅 내용 v3
	ChatListDTO sendChat(int user_no, int to_user_no, String chat_text); //채팅 보내기
	ChatListDTO sendChat(int user_no, int to_user_no, String chat_text, String pushKey); //채팅 보내기
	ChatListDTO sendChat(int user_no, int to_user_no, String chat_text, boolean banPush); //채팅 보내기-db처리만,push 없이
	ChatListDTO sendChatV2(int user_no, int to_user_no, String chat_text, String pushKey); //채팅 보내기 v2
	ChatListDTO sendChatV2(int user_no, int to_user_no, String chat_text, boolean banPush); //채팅 보내기-db처리만,push 없이
	ChatListDTO sendChatV2EN(int user_no, int to_user_no, String chat_text, String pushKey, String chat_text_trans); //채팅 보내기 v2 +번역
	ChatListDTO sendChatV3(int user_no, int to_user_no, String chat_text, String pushKey, int chat_type); //채팅 보내기 v3
	ChatListDTO sendChatV3EN(int user_no, int to_user_no, String chat_text, String pushKey, String chat_text_trans, int chat_type); //채팅 보내기 v3 +번역
	ChatListDTO sendChatV4(int user_no, int to_user_no, String chat_text, String pushKey, int chat_type); //채팅 보내기 v4
	ChatListDTO sendChatV4EN(int user_no, int to_user_no, String chat_text, String pushKey, String chat_text_trans, int chat_type); //채팅 보내기 v4 +번역
	ChatListDTO sendChatMC(int user_no, int to_user_no, String chat_text, String pushKey, int chat_type); //미인캠 채팅 보내기
	ChatListDTO sendChatMCEN(int user_no, int to_user_no, String chat_text, String pushKey, String chat_text_trans, int chat_type); //미인캠 채팅 보내기 +번역
	ChatListDTO sendChatV5(int user_no, int to_user_no, String chat_text, String pushKey, int chat_type); //미인캠 채팅 보내기
	ChatListDTO sendChatV5EN(int user_no, int to_user_no, String chat_text, String pushKey, String chat_text_trans, int chat_type); //미인캠 채팅 보내기 +번역
	ChatListDTO deleteChat(int user_no, String arr_to_user_no); //채팅 삭제
}