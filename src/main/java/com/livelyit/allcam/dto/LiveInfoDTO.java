package com.livelyit.allcam.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class LiveInfoDTO extends DefaultDTO {
	int user_no;
    int connect_user_no;
    String user_id;
    String user_email;
    String user_nick_name;
    int user_age;
    String user_gender;
    int conversation_no;
    String user_imgs;
    int call_type;
    int user_cash;
    int user_point;
    String sdate;
    String conversation;
    int like_cnt;
    int bookmark_cnt;
    float user_star_grade;
    int user_live_average;
    String user_total_live_time;
    int my_bookmark_chk;
    int my_like_chk;
    int user_type;
    int user_part_time;
    String user_newbie_time;
    String img_url;
    String user_country_code;
    String user_language_code;
}
