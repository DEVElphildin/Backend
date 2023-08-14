package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class UseHistoryDTO {
	int no;
	int use_type;
	String use_content;
	int use_cnt;
	int user_no;
	String use_etc;
	String sdate;
	int use_etc_user_no = -1;
	String user_nick_name;
}
