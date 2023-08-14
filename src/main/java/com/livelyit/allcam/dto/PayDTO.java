package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class PayDTO {
	int no;
	int user_no;
	int pay_result_pay;
	int pay_status;
	int pay_result_point;
	String pay_result_product_name;
	String pay_result_type;
	String pay_daoutrx;
	int android_inapp_code;
	int pay_point;
	int free_point;
}