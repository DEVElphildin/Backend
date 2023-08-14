package com.livelyit.allcam.service;

import com.livelyit.allcam.dto.BoardCustomerDTO;
import com.livelyit.allcam.dto.BoardCustomerResultDTO;
import com.livelyit.allcam.dto.BoardListDTO;

public interface BoardService {
	public BoardListDTO selectBoardList(String board_type);	//공지 및 이용약관 리스트
	public BoardListDTO selectBoardListV2(String board_type, String service_type, String target_gender);	//공지 및 이용약관 리스트 v2
	public BoardCustomerResultDTO insertCustomer(BoardCustomerDTO dto); // 고객센터 문의사항

    //public String iosTokenTest(String token);
}