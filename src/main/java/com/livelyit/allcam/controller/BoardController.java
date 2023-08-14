package com.livelyit.allcam.controller;

import com.livelyit.allcam.common.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.BoardCustomerDTO;
import com.livelyit.allcam.dto.BoardListDTO;
import com.livelyit.allcam.dto.DefaultDTO;
import com.livelyit.allcam.service.BoardService;

@RestController
public class BoardController {
	@Autowired
	BoardService boardService;
	
	//공지 및 이용약관 리스트
	@RequestMapping("/boardList")
	@ResponseBody
	public BoardListDTO boardList(@RequestParam(value = "board_type", required = !Utils.Debug) String board_type) {		
		return boardService.selectBoardList(board_type);
	}

	//공지 및 이용약관 리스트
	@RequestMapping("/v2/boardList")
	@ResponseBody
	public BoardListDTO boardListV2(@RequestParam(value = "board_type", required = !Utils.Debug) String board_type,
									@RequestParam(value = "service_type", required = !Utils.Debug) String service_type,
									@RequestParam(value = "target_gender", required = !Utils.Debug) String target_gender) {
		return boardService.selectBoardListV2(board_type, service_type, target_gender);
	}


	
	//문의사항 등록
	@RequestMapping("/customerQuestions")
	@ResponseBody
	public DefaultDTO insertCustomer(@RequestParam(value = "question_type", required = !Utils.Debug) String question_type,
			@RequestParam(value = "board_text", required = !Utils.Debug) String board_text,
			@RequestParam(value = "user_no", required = !Utils.Debug) int user_no) {
		BoardCustomerDTO dto = new BoardCustomerDTO();
		dto.setBoard_text(board_text);
		dto.setQuestion_type(question_type);
		dto.setUser_no(user_no);
		
		return boardService.insertCustomer(dto);
	}
	
//	@RequestMapping("/jwt")
//	@ResponseBody
//	public String testScript() {
//		String token = JwtTokenUtil.createToken("ios_consumption_token");
//		return "token : "+token;
//	}


}