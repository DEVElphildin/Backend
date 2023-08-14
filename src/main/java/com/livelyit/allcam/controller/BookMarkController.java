package com.livelyit.allcam.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.BookMarkDTO;
import com.livelyit.allcam.dto.DefaultDTO;
import com.livelyit.allcam.dto.GetBookMarkDTO;
import com.livelyit.allcam.dto.UserPointDTO;
import com.livelyit.allcam.service.BookMarkService;

@RestController
public class BookMarkController {
	@Autowired
	BookMarkService bookService;
	
	// 즐겨찾기 추가
	@RequestMapping("/addBookMark")
	@ResponseBody
	public DefaultDTO insertBookMark(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
			@RequestParam(value = "bookmark_user_no", required = !Utils.Debug) int bookmark_user_no){
		
		BookMarkDTO dto = new BookMarkDTO();
		dto.setUser_no(user_no);
		dto.setBookmark_user_no(bookmark_user_no);
		
		return bookService.insertBookMark(dto);
		
	}

	// 즐겨찾기 추가한 상대에게 push 메세지 날리도록 함
	@RequestMapping("/v2/addBookMark")
	@ResponseBody
	public DefaultDTO insertBookMarkV2(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
									 @RequestParam(value = "bookmark_user_no", required = !Utils.Debug) int bookmark_user_no){

		BookMarkDTO dto = new BookMarkDTO();
		dto.setUser_no(user_no);
		dto.setBookmark_user_no(bookmark_user_no);

		return bookService.insertBookMarkV2(dto);

	}
	
	// 내/팬 즐겨찾기 리스트
	@RequestMapping("/bookMarkList")
	@ResponseBody
	public GetBookMarkDTO bookMarkList(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no) {
		return bookService.bookMarkList(user_no);
	}


	// 내/팬 즐겨찾기 리스트 - 보이스팅 과금 변동에 따른 임시 API
	@RequestMapping("/v2/bookMarkList")
	@ResponseBody
	public GetBookMarkDTO bookMarkListV2(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no) {
		return bookService.bookMarkListV2(user_no);
	}
	
	@RequestMapping("/sendBookMarkGroupMsg")
	@ResponseBody
	public UserPointDTO sendBookMarkGroupMsg(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
			@RequestParam(value = "msg", required = !Utils.Debug) String msg) {
		return bookService.sendBookMarkGroupMsg(user_no, msg);
	}
}
	