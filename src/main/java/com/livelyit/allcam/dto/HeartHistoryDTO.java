package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class HeartHistoryDTO {

    String room_id;
    int user_no;
    String user_nick_name;
    String start_time;
    String call_history_second;
    int use_type;
    int heart_cnt;
    String use_content;

}
