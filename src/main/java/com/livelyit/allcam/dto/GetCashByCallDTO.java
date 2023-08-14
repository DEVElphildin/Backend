package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class GetCashByCallDTO {
    int user_no;
    String room_id;
    int get_cash;
    int partner_user_no;
    int result_code;
    String result_msg;
}
