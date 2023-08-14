package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class NewbieUserDTO {
    int no;
    int user_no;
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
    int user_heart;
    String sdate;
    int like_cnt;
    int bookmark_cnt;
    int my_like_chk;
    int my_bookmark_chk;
    String conversation;
    float user_star_grade;
    int user_live_average;
    String user_total_live_time;
    String user_newbie_time;
    String user_country_code;
    String user_language_code;
    int user_part_time;
}
