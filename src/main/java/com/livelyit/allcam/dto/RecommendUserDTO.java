package com.livelyit.allcam.dto;

import lombok.Data;

/**
 * 추천목록 DTO
 * 
 */

@Data
public class RecommendUserDTO {
	int no;
	int user_no;
	int to_user_no;
	int re_type;
	String user_nick_name;
	String conversation;
	int like_cnt;
	int bookmark_cnt;
	String user_imgs;
	String sdate;
	String user_id;
	String user_email;
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
	String user_push_key;
	float user_star_grade;
	int user_live_average;
	String user_total_live_time;
	int user_type;
	int connect_user_no;
	int my_like_chk;
	int my_bookmark_chk;
	String user_country_code;
	String user_country_url;
	String user_language_code;
}