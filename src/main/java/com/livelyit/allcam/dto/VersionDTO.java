package com.livelyit.allcam.dto;

import lombok.Data;

/**
 * 어플/앱의 버전을 체크한다.
 * 
 */

@Data
public class VersionDTO {
	
	 int no;  // 구분 번호
	 String version; // 현 버전
	 String version_description; // 바뀔 버전
	 String version_url; // 업데이트 url
	 String user_no; // 유저 구분번호
	 String sdate; // 입력날짜
	 String os_type; // 휴대폰 종류
	 String service_type; //앱 서비스 구분
	 String store_type; //앱스토어 구분
	 boolean release_flag; //출시

}