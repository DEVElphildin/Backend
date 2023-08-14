package com.livelyit.allcam.dto;

import lombok.Data;

/**
 * 환전 내역 리스트
 * @author ㅇㄱ
 *
 */
@Data
public class UserRefundsHistoryListDTO{
	int use_type;
	int user_no;	//유저 번호
	int use_cnt;	//환급비용
	String sdate;	//환급일시
	String use_content; //환급구분
}
