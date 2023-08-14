package com.livelyit.allcam.dto;
import lombok.Data;

@Data
public class RoomCallDTO {
    // RoomCall 정보
    int no;
    int user_no;
    int room_status;
    String room_name;
    String sdate;
    String edate;
    // 회원정보  추가
    String user_nick_name;
    String user_img;
}
