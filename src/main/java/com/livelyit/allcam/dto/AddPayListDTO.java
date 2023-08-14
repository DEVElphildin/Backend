package com.livelyit.allcam.dto;

import lombok.Data;

/**
 * PayList 등록 DTO
 * @author ㅇㄱ
 *
 */
@Data
public class AddPayListDTO{
	int no;
	int user_no;	//결제유저번호
	int result_no;	//payResult, passbook 번호
	String result_payment_type;	//결제타입
	String result_product_name;	// 구매상품
	int result_point;	//지급포인트
	int result_pay;	//입금액
    String user_service_type;
	int pay_point;
	int free_point;
} 