package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class PayResultDTO {
	int no;
	int user_no;
	String pay_result_product_name;
	int pay_result_pay;
	int pay_result_point;
	int pay_status;
	String pay_result_type;
	int one_store_flag;
	String sdate;
	int pay_point;
	int free_point;
}
