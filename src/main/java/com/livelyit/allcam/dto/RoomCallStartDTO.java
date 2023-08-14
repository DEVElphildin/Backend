package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class RoomCallStartDTO {
    String room_id;
    int my_user_no;
    int partner_user_no;
    int result_code;
    String result_msg;
}