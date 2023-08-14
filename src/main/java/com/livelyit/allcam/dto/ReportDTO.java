package com.livelyit.allcam.dto;

import lombok.Data;

@Data
public class ReportDTO {
	int user_no;
	int to_user_no; 
	int report_type; 
	String report_content;
}