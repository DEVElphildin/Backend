package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class UserBalanceDTO extends DefaultDTO{
    int user_no;
    int user_point;
    int user_cash;
    int user_heart;
    String user_gender;
}