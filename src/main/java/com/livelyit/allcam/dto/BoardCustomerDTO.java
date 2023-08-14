package com.livelyit.allcam.dto;

import lombok.Data;

/**
 * 고객센터 문의 DTO
 * @author ㅇㄱ
 *
 */
@Data
public class BoardCustomerDTO{
	
	int user_no;
	String question_type;
	String board_text;
	String sdate;
	
}
