package com.livelyit.allcam.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;

@Data
@EqualsAndHashCode(callSuper=false)
public class MyVideoCallUserDTO extends DefaultDTO {
	int user_no;
	String user_nick_name;
    String user_push_key;
   	String user_imgs;
}