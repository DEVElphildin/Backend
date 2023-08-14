package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class AutoPassNoDTO {
	int no;
	int user_no;
	String user_adid;

	String user_nick_name;
	int user_point;
	int user_free_point;
	int user_pay_point;
	int passbook_payment;
	String user_push_key;
	String passbook_product;
	int passbook_point;
	
	int pay_point;
	int free_point;
	int passbook_free_point;
}