package com.livelyit.allcam.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.ChatListDTO;
import com.livelyit.allcam.dto.DefaultDTO;
import com.livelyit.allcam.mapper.ChatMapper;
import com.livelyit.allcam.service.ChatService;

@RestController
public class ChatController {
	@Autowired
	ChatService chatService;

	@Autowired
	ChatMapper chatMapper;
	
	//내 채팅 메인 목록
	@RequestMapping("/getMyChat")
	@ResponseBody
	public ChatListDTO getMyChat(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no) {		
		return chatService.getMyChat(user_no);
	}

	// 내 채팅 메인 목록 v2
	// 최근 메세지가 하루 이내인 채팅방만 가져옴
	@RequestMapping("/v2/getMyChat")
	@ResponseBody
	public ChatListDTO getMyChatV2(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no) {
		return chatService.getMyChatV2(user_no);
	}

	// 내 채팅 메인 목록 v3
	// 최근 메세지가 하루가 지난 채팅도 가져옴, 즐겨찾기, 좋아요, 이모티콘 전송 여부도 채팅 메세지로 나타냄
	@RequestMapping("/v3/getMyChat")
	@ResponseBody
	public ChatListDTO getMyChatV3(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no) {
		return chatService.getMyChatV3(user_no);
	}

	//유저 채팅 목록 (1:1)
	@RequestMapping("/getUserChat")
	@ResponseBody
	public ChatListDTO getUserChat(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
			@RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no) {		
		return chatService.getUserChat(user_no, to_user_no);
	}

	//유저 채팅 목록 (1:1)
	// 최근 메세지가 하루 이내인 채팅만 가져옴
	@RequestMapping("/v2/getUserChat")
	@ResponseBody
	public ChatListDTO getUserChatV2(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
								   @RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no) {
		return chatService.getUserChatV2(user_no, to_user_no);
	}

	//유저 채팅 목록 (1:1)
	// 최근 메세지가 하루 이내인 채팅만 가져오고
	// 즐겨찾기, 좋아요, 선물 전송 여부를 가져옴
	@RequestMapping("/v3/getUserChat")
	@ResponseBody
	public ChatListDTO getUserChatV3(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
									 @RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no) {
		return chatService.getUserChatV3(user_no, to_user_no);
	}

	//채팅 보내기
	@RequestMapping(value = "/sendChat", params= {"user_no","to_user_no", "chat_text"})
	@ResponseBody
	public ChatListDTO sendChat(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
								@RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no,
								@RequestParam(value = "chat_text", required = !Utils.Debug) String chat_text) {
		return chatService.sendChat(user_no, to_user_no, chat_text);
	}
	//채팅 보내기(중복로그인용 오버로드)
	@RequestMapping(value = "/sendChat", params= {"user_no","to_user_no", "chat_text",  "pushKey"})
	@ResponseBody
	public ChatListDTO sendChat(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
								@RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no,
								@RequestParam(value = "chat_text", required = !Utils.Debug) String chat_text,
								@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey) {
		return chatService.sendChat(user_no, to_user_no, chat_text, pushKey);
	}
	//채팅 보내기(채팅 push 기능뺀 오버로드)
	@RequestMapping(value = "/sendChat", params= {"user_no","to_user_no", "chat_text", "banPush"})
	@ResponseBody
	public ChatListDTO sendChat(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
								@RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no,
								@RequestParam(value = "chat_text", required = !Utils.Debug) String chat_text,
								@RequestParam(value = "banPush", required = !Utils.Debug) boolean banPush) {
		return chatService.sendChat(user_no, to_user_no, chat_text, banPush);
	}


	//채팅 보내기 v2(과금 90원)
	@RequestMapping(value = "/v2/sendChat", params= {"user_no","to_user_no", "chat_text",  "pushKey"})
	@ResponseBody
	public ChatListDTO sendChatV2(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
								@RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no,
								@RequestParam(value = "chat_text", required = !Utils.Debug) String chat_text,
								@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey) {
		return chatService.sendChatV2(user_no, to_user_no, chat_text, pushKey);
	}

	//채팅 보내기 v2(과금 90원)
	@RequestMapping(value = "/v2/sendChat", params= {"user_no","to_user_no", "chat_text",  "pushKey", "chat_text_trans"})
	@ResponseBody
	public ChatListDTO sendChatV2(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
								  @RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no,
								  @RequestParam(value = "chat_text", required = !Utils.Debug) String chat_text,
								  @RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey,
								  @RequestParam(value = "chat_text_trans", required = !Utils.Debug) String chat_text_trans) {
		return chatService.sendChatV2EN(user_no, to_user_no, chat_text, pushKey, chat_text_trans);
	}

	//채팅 보내기 v2(과금 90원)
	@RequestMapping(value = "/v3/sendChat", params= {"user_no","to_user_no", "chat_text",  "pushKey", "chat_type"})
	@ResponseBody
	public ChatListDTO sendChatV3(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
								  @RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no,
								  @RequestParam(value = "chat_text", required = !Utils.Debug) String chat_text,
								  @RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey,
								  @RequestParam(value = "chat_type", required = !Utils.Debug) int chat_type) {
		return chatService.sendChatV3(user_no, to_user_no, chat_text, pushKey, chat_type);
	}

	//채팅 보내기 v2(과금 90원)
	@RequestMapping(value = "/v3/sendChat", params= {"user_no","to_user_no", "chat_text",  "pushKey", "chat_text_trans", "chat_type"})
	@ResponseBody
	public ChatListDTO sendChatV3EN(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
								  @RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no,
								  @RequestParam(value = "chat_text", required = !Utils.Debug) String chat_text,
								  @RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey,
								  @RequestParam(value = "chat_text_trans", required = !Utils.Debug) String chat_text_trans,
								  @RequestParam(value = "chat_type", required = !Utils.Debug) int chat_type) {
		return chatService.sendChatV3EN(user_no, to_user_no, chat_text, pushKey, chat_text_trans, chat_type);
	}

	//채팅 보내기 v4
	// 과금 없엠,  번역 안한 채팅
	@RequestMapping(value = "/v4/sendChat", params= {"user_no","to_user_no", "chat_text",  "pushKey", "chat_type"})
	@ResponseBody
	public ChatListDTO sendChatV4(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
								  @RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no,
								  @RequestParam(value = "chat_text", required = !Utils.Debug) String chat_text,
								  @RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey,
								  @RequestParam(value = "chat_type", required = !Utils.Debug) int chat_type) {
		return chatService.sendChatV4(user_no, to_user_no, chat_text, pushKey, chat_type);
	}

	//채팅 보내기 v2(과금 90원)
	// 과금 없엠, 번역 한 채팅
	@RequestMapping(value = "/v4/sendChat", params= {"user_no","to_user_no", "chat_text",  "pushKey", "chat_text_trans", "chat_type"})
	@ResponseBody
	public ChatListDTO sendChatV4EN(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
									@RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no,
									@RequestParam(value = "chat_text", required = !Utils.Debug) String chat_text,
									@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey,
									@RequestParam(value = "chat_text_trans", required = !Utils.Debug) String chat_text_trans,
									@RequestParam(value = "chat_type", required = !Utils.Debug) int chat_type) {
		return chatService.sendChatV4EN(user_no, to_user_no, chat_text, pushKey, chat_text_trans, chat_type);
	}

	//미인캠 채팅 보내기 (과금 50원)
	// 번역 안한 채팅
	@RequestMapping(value = "/sendChatMC", params= {"user_no","to_user_no", "chat_text",  "pushKey", "chat_type"})
	@ResponseBody
	public ChatListDTO sendChatMC(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
								  @RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no,
								  @RequestParam(value = "chat_text", required = !Utils.Debug) String chat_text,
								  @RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey,
								  @RequestParam(value = "chat_type", required = !Utils.Debug) int chat_type) {
		return chatService.sendChatMC(user_no, to_user_no, chat_text, pushKey, chat_type);
	}

	//미인캠 채팅 보내기 (과금 50원)
	// 번역 한 채팅
	@RequestMapping(value = "/sendChatMC", params= {"user_no","to_user_no", "chat_text",  "pushKey", "chat_text_trans", "chat_type"})
	@ResponseBody
	public ChatListDTO sendChatMCEN(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
									@RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no,
									@RequestParam(value = "chat_text", required = !Utils.Debug) String chat_text,
									@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey,
									@RequestParam(value = "chat_text_trans", required = !Utils.Debug) String chat_text_trans,
									@RequestParam(value = "chat_type", required = !Utils.Debug) int chat_type) {
		return chatService.sendChatMCEN(user_no, to_user_no, chat_text, pushKey, chat_text_trans, chat_type);
	}

//미인캠 채팅 보내기 (과금 30원)
	// 번역 안한 채팅
	@RequestMapping(value = "/v5/sendChat", params= {"user_no","to_user_no", "chat_text",  "pushKey", "chat_type"})
	@ResponseBody
	public ChatListDTO sendChatV5(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
								  @RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no,
								  @RequestParam(value = "chat_text", required = !Utils.Debug) String chat_text,
								  @RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey,
								  @RequestParam(value = "chat_type", required = !Utils.Debug) int chat_type) {
		return chatService.sendChatV5(user_no, to_user_no, chat_text, pushKey, chat_type);
	}

	//미인캠 채팅 보내기 (과금 30원)
	// 번역 한 채팅
	@RequestMapping(value = "/v5/sendChat", params= {"user_no","to_user_no", "chat_text",  "pushKey", "chat_text_trans", "chat_type"})
	@ResponseBody
	public ChatListDTO sendChatV5EN(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
									@RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no,
									@RequestParam(value = "chat_text", required = !Utils.Debug) String chat_text,
									@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey,
									@RequestParam(value = "chat_text_trans", required = !Utils.Debug) String chat_text_trans,
									@RequestParam(value = "chat_type", required = !Utils.Debug) int chat_type) {
		return chatService.sendChatV5EN(user_no, to_user_no, chat_text, pushKey, chat_text_trans, chat_type);
	}


	//채팅 삭제
	@RequestMapping("/deleteChat")
	@ResponseBody
	public ChatListDTO deleteChat(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
			@RequestParam(value = "to_user_no", required = !Utils.Debug) String to_user_no) {		
		return chatService.deleteChat(user_no, to_user_no);
	}
	
//	@RequestMapping("/testExcel")
//	@ResponseBody
//	public DefaultDTO deleteChat() {
//		ArrayList<String> chatList = Utils.getExcelText("C:/source/chat_ban.xlsx");
//		ArrayList<String> nickList = Utils.getExcelText("C:/source/nick_ban.xlsx");
//
//		String chatBanValue = "";
//		String nickBanValue = "";
//
//		for(String s : chatList) {
//			chatBanValue += ",('채팅', '" +s+ "')";
//		}
//
//		for(String s : nickList) {
//			nickBanValue += ",('닉네임', '" +s+ "')";
//		}
//
//
//		chatMapper.insertBanText(chatBanValue.substring(1));
//		chatMapper.insertBanText(nickBanValue.substring(1));
//
//		return new DefaultDTO();
//	}
}
