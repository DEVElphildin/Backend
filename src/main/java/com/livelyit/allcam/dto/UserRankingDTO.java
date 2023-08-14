package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class UserRankingDTO {
	long total_score;
	int no;
	String sdate;
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
	int user_part_time;
	int user_cash;
	int user_point;
	int user_heart;
	String user_push_key;
	float user_star_grade;
	int user_live_average;
	String user_total_live_time;
	String conversation;
	int like_cnt;
	int bookmark_cnt;
	int connect_user_no;
	String user_img_url;
	String user_type;
	String user_imgs;
	String user_newbie_time;
}