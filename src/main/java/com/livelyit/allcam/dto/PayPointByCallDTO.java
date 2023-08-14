package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class PayPointByCallDTO {
    int user_no;
    String room_id;
    int pay_point;
    int pay_free_point;
    int partner_user_no;
    int result_code;
    String result_msg;
}

