package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class CallFinishDTO {
    String room_id;
    int finish_type;
    int finish_user_no;
    int finish_use_seconds;
    int other_use_seconds;
    int cash_per_seconds;
    int point_per_seconds;
}
