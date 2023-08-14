package com.livelyit.allcam.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper=false)
public class LoginDTO extends DefaultDTO {
    String ip;
    int no;
	String user_email;
    String user_nick_name;
    int user_age;
    String user_gender;
    int conversation_no;
    String conversation_txt;
    String user_thumnail;
    String user_auth_token;
    String user_id;
    String user_sdate;
    String user_alarm_popup;
    String user_alarm_push;
    String user_alarm_bookmark;
    String user_alarm_sound;
    String user_alarm_bive;
    String user_push_key;
    String user_voip_key;
    int user_point;
    int user_free_point;
    int user_pay_point;
   	int user_cash;
   	int user_heart;
   	String user_imgs;
   	int user_type;
   	int user_part_time;  // 0일반, 1알바, 2테스트, 3새내기
    String agent_id;
    int is_refund_visible;
    int call_type;
    int email_receive_agree;
   	String os_type;
   	String user_start_stop_date;	//정지 시작일
   	String user_end_stop_date;	//정지 종료일
   	String stop_text;	// 정지사유
   	String user_identi_code;	//단말기 고유 키
    String user_service_type;	// 앱 구분
    String user_phone_number;
    String user_newbie_time; //새내기 설정 시간
    String user_country_code;
    String user_language_code;
    int cash_per_second;
    int point_per_second;
    int voice_cash_per_second;
    int voice_point_per_second;
    int minimum_payback;
    ArrayList<MainBannerDTO> banner_info;
    String login_type;
    int cert_no;
    int is_certified;
    String user_adid;
    String version;
}