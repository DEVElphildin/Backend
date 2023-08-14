package com.livelyit.allcam.dto;

import lombok.Data;

/**
 * 로그인 이력 DTO
 * @author ㅇㄱ
 *
 */

@Data
public class LoginLogDTO {

	int user_no;
	String user_id;
	String os_type;
	String sdate;
	String version;
}
