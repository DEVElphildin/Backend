package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class UserExchangeHeartDTO {
    int user_no;
    String user_name;
    String user_mobile;
    int exchange_item_no;
    int result_code;
    String result_msg;
}
