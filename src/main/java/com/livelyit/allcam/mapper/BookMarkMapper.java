package com.livelyit.allcam.mapper;


import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.livelyit.allcam.common.AbstractDAO;
import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.BookMarkDTO;
import com.livelyit.allcam.dto.GroupMsgDTO;

@Repository("bookMarkMapper")
public class BookMarkMapper extends AbstractDAO {
	
	//내가 한 즐겨찾기 리스트
	public ArrayList<BookMarkDTO> bookMarkList(int user_no) {
		return (ArrayList<BookMarkDTO>) selectList(Utils.SQL_ALLCAM, "bookMarkMapper.bookMarkList", user_no);
	}

	//나를 즐겨찾기 한 리스트
	public ArrayList<BookMarkDTO> bookMarkUserList(int bookmark_user_no) {
		return (ArrayList<BookMarkDTO>) selectList(Utils.SQL_ALLCAM, "bookMarkMapper.bookMarkUserList", bookmark_user_no);
	}

	//내가 한 즐겨찾기 리스트
	public ArrayList<BookMarkDTO> bookMarkListAllcamMan(int user_no) {
		return (ArrayList<BookMarkDTO>) selectList(Utils.SQL_ALLCAM, "bookMarkMapper.bookMarkListAllcamMan", user_no);
	}

	//나를 즐겨찾기 한 리스트
	public ArrayList<BookMarkDTO> bookMarkUserListAllcamMan(int bookmark_user_no) {
		return (ArrayList<BookMarkDTO>) selectList(Utils.SQL_ALLCAM, "bookMarkMapper.bookMarkUserListAllcamMan", bookmark_user_no);
	}


	//내가 한 즐겨찾기 리스트
	public ArrayList<BookMarkDTO> bookMarkListVideo(int user_no) {
		return (ArrayList<BookMarkDTO>) selectList(Utils.SQL_ALLCAM, "bookMarkMapper.bookMarkListVideo", user_no);
	}

	//나를 즐겨찾기 한 리스트
	public ArrayList<BookMarkDTO> bookMarkUserListVideo(int bookmark_user_no) {
		return (ArrayList<BookMarkDTO>) selectList(Utils.SQL_ALLCAM, "bookMarkMapper.bookMarkUserListVideo", bookmark_user_no);
	}


	//즐겨찾기 추가
	public int insertBookMark(BookMarkDTO dto) {
		return  (int) insert(Utils.SQL_ALLCAM, "bookMarkMapper.insertBookMark", dto);	
	}
	
	//즐겨찾기 삭제
	public  int deleteBookMark(BookMarkDTO dto) {
		return  (int) delete(Utils.SQL_ALLCAM, "bookMarkMapper.deleteBookMark", dto);	
	}
		
	public int cntBookmark (BookMarkDTO dto) {
		return (int) selectOne(Utils.SQL_ALLCAM, "bookMarkMapper.cntBookmark", dto);
	}
	
	//북마크 20명 발송용
	public ArrayList<GroupMsgDTO> getBookmarkGroup(int user_no) {
		return (ArrayList<GroupMsgDTO>) selectList(Utils.SQL_ALLCAM, "bookMarkMapper.getBookmarkGroup", user_no);
	}
}