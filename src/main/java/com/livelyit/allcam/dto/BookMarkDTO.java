package com.livelyit.allcam.dto;

import lombok.Data;

/**
 * 즐겨찾기 DTO
 * 
 */

@Data
public class BookMarkDTO {
	int no;
	int user_no;
	int bookmark_user_no;
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
	String user_push_key;
	int user_point;
	int user_cash;
	int user_heart;
	int user_part_time;
	String user_newbie_time;
	String sdate;
	String user_img_url;
	int connect_user_no;
	String conversation;
	String user_imgs;
	int like_cnt;
	int bookmark_cnt;
	float user_star_grade;
	int user_live_average;
	String user_language_code;
	String user_total_live_time;
	int user_type;
}