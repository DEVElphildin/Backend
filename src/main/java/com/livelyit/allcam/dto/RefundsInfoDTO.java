package com.livelyit.allcam.dto;

import lombok.Data;

/**
 * 환급신청 
 * 
 */

@Data
public class RefundsInfoDTO {
	int refund_user_no;	//신청 유저
	int refund_cash;	// 신청한 캐쉬
	int refund_pay;	// 환급금
	//int refund_status;	// -1 : 거절, 0 : 대기, 1 : 승인
}