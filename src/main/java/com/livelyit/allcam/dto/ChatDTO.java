package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class ChatDTO {
	int no;
	int from_user_no;
	int to_user_no;
	String chat_text;
	String chat_text_trans;
	String absent_room_id;
	int customer_no;
	int from_visible;
	int to_visible;
	int to_agree;
	String sdate;
	String partner_user_thumnail;	
	int connect_user_no;
    String user_id;
    String user_email;
    String user_nick_name;
    int user_age;
    String user_gender;
    int conversation_no;
    String user_imgs;
    int user_cash;
    int user_point;
    String cov_content;
    String conversation;
    String user_country_code;
    String user_language_code;
    int like_cnt;
    int bookmark_cnt;
    float user_star_grade;
    int user_live_average;
    String user_total_live_time;
    String user_sdate;
    String message_sdate;
    int chat_type;
    double present_to_cash_rate;
}