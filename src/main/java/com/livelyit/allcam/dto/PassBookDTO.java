package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class PassBookDTO{
	int no;
	int user_no;
	String passbook_name;	//입금자명
	String passbook_phone_number;	//입금자 연락처
	int passbook_point;	//총 포인트 
	int passbook_payment;	//결제금액
	String passbook_product; // 구매상품
	int cash_receipts;	//현금영수증 여부  -- 0 : 현금영수증 X   -- 1 : 현금영수증  O
	String cash_receipts_purpose;	//현금영수증 목적 
	String cash_receipts_name;	//이름
	String cash_receipts_number;	// 사업자등록번호, 주민등록번호
	String cash_receipts_phone_nember;	//연락처
	int passbook_confirm;	//상태	-1 : 거부		0 : 대기 		1 : 승인  		2 : 재승인
	String sdate;
	String user_adid;
	int pay_point;
	int free_point;
	int passbook_free_point;
}
