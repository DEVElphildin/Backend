package com.livelyit.allcam.dto;

import lombok.Data;

/**
 * 환전 신청 시 들어올 정보
 * @author ㅇㄱ
 *
 */
@Data
public class UserRefundsUpdateDTO{
	int no;	//유저 번호
	int user_use_cash;	// 유저  환전 신청 캐시
	int user_cash;	//유저 환전 후 캐시
	String user_name;
	String user_mobile;
	String user_jubun;
	String user_co_paper;
	String user_bank;
	String user_bank_num;
	String user_bank_paper_copy;
}
