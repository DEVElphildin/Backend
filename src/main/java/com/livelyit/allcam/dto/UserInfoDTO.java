package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class UserInfoDTO {
	String no;
	String user_id;
	String user_email;
	String user_nick_name;
	int user_age;
	String user_gender;
	int conversation_no;
	int user_alarm_popup;
	int user_alarm_push;
	int user_alarm_bookmark;
	int user_alarm_sound;
	int user_alarm_bive;
	int user_cash;
	int user_point;
	int user_heart;
	int user_free_point;
	int user_pay_point;
	String user_push_key;
	String user_voip_key;
	String user_star_grade;
	String user_live_average;
	String user_total_live_time;
	int call_type;
	int is_refund_visible;
	String user_type;
	String user_identi_code;
	String user_service_type;
	String user_country_code;
	String user_language_code;
	String sdate;
	String conversation;
	String agent_id;
	int like_cnt;
	int bookmark_cnt;
	int user_part_time;
	int cash_per_second;
	int point_per_second;
	int voice_cash_per_second;
	int voice_point_per_second;
	double present_to_cash_rate;
	String user_newbie_time;
	String user_imgs;
	int is_certified;
}
