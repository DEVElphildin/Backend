package com.livelyit.allcam.dto;

import lombok.Data;

/**
 * 회원가입 시 지정하는 대화주제 리스트 DTO
 * 
 */

@Data
public class ConversationDTO extends DefaultDTO {
	
	int no;	// 구분 값
	String cov_content; // 대화주제
}