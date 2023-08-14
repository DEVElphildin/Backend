package com.livelyit.allcam.dto;

import lombok.Data;

/**
 * 이벤트 DTO
 * 
 */

@Data
public class EventDTO {
	int no;
	String event_subject;
	String event_banner;
	String event_content_img;
	String event_banner_data;
	String event_term_start;
	String event_content_img_data;
	String event_term_end;
	String sdate;
	int click_flag;
	String link_url;
}
