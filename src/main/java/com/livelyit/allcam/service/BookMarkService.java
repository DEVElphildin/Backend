package com.livelyit.allcam.service;
import com.livelyit.allcam.dto.BookMarkDTO;
import com.livelyit.allcam.dto.DefaultDTO;
import com.livelyit.allcam.dto.GetBookMarkDTO;
import com.livelyit.allcam.dto.UserPointDTO;

public interface BookMarkService {
	public DefaultDTO insertBookMark(BookMarkDTO dto); // 즐겨찾기 추가
	public DefaultDTO insertBookMarkV2(BookMarkDTO dto); // 즐겨찾기 추가
	public GetBookMarkDTO bookMarkList(int user_no); //즐겨찾기 리스트
	public GetBookMarkDTO bookMarkListV2(int user_no); //즐겨찾기 리스트
	public UserPointDTO sendBookMarkGroupMsg(int user_no, String msg); //즐겨찾기 20명 메시지 보내기	
}