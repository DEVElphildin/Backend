package com.livelyit.allcam.mapper;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.livelyit.allcam.common.AbstractDAO;
import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.BoardCustomerDTO;
import com.livelyit.allcam.dto.BoardDTO;

@SuppressWarnings("unchecked")
@Repository("boardMapper")
public class BoardMapper extends AbstractDAO{	
	
	//공지 및 이용약관
	public ArrayList<BoardDTO> selectBoardList(String board_type) {
		return  (ArrayList<BoardDTO>) selectList(Utils.SQL_ALLCAM, "boardMapper.selectBoardList", board_type);
	}

	//공지 및 이용약관
	public ArrayList<BoardDTO> selectBoardListV2(BoardDTO dto) {
		return  (ArrayList<BoardDTO>) selectList(Utils.SQL_ALLCAM, "boardMapper.selectBoardListV2", dto);
	}
	
	//고객센터 문의내용
	public int insertCustomer(BoardCustomerDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "boardMapper.insertCustomer", dto);
	}
}