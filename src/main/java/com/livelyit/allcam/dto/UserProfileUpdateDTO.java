package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class UserProfileUpdateDTO {	
	int user_no;
	String user_nick_name;
	int user_age;
	int conversation_no;
	String conversation_txt;
	int user_point;
	int user_free_point;
	int user_pay_point;
	int call_type;
	int user_result_point;
	String user_type;
	String sdate;
	int no;
    String user_thumnail;
	int admin_appro;
	String user_newbie_time;
	int user_deduction_point;
	int user_deduction_free_point;
}