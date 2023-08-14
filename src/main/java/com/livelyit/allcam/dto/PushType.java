package com.livelyit.allcam.dto;

import java.util.Arrays;

public enum PushType {

	LIVECALL("영상통화신청", "1"),
	LIVECANCEL("영상통화취소", "2"),
	LIVEOK("영상통화수락", "3"),
	LIVESTART("영상통화시작", "4"),
	LIVEFAIL("영상통화실패 (DB)", "5"),
	POINTGET("포인트선물받음", "6"),
	CHATGET("채팅 받음", "7"),
	NOTIGET("노티 받음", "8"),
	PROFILEOK("프로필 승인", "9"),
	MOOTONGJANGOK("무통장 승인", "10"),
	CALLUSERLOGIN("전화했던 유저 로그인", "12"),
	BOOKMARKADD("즐겨찾기에 추가함", "13"),
	LIKEADD("추천이 증가함", "14"),
	VOICECALL("음성통화신청", "21"),
	VOICECANCEL("음성통화취소", "22"),
	VOICEOK("음성통화수락", "23"),
	VOICESTART("음성통화시작", "24"),
	VOICEFAIL("음성통화실패 (DB)", "25");

	private String typeNm;
	private String typeCd;

	private PushType(String typeNm, String typeCd) {
		this.typeNm = typeNm;
		this.typeCd = typeCd;
	}

	public String getTypeNm() {
		return typeNm;
	}

	public String getTypeCd() {
		return typeCd;
	}
	
	public static PushType getEnum(String typeCd){
		return Arrays.stream(PushType.values()).filter(w -> w.getTypeCd().equals(typeCd)).findFirst().orElse(null);
	}
}