package com.livelyit.allcam.dto;

import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class ChatListDTO extends DefaultDTO {
	int user_point;
	int user_type;
	String user_country_code;
	String user_language_code;
	ArrayList<ChatDTO> chatList;
}