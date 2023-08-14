package com.livelyit.allcam.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SetAlarmDTO extends DefaultDTO {
	int user_no;
	int set_type;
	int set_status;
	int user_alarm_push;
	int user_alarm_popup;
	int user_alarm_bookmark;
	int user_alarm_sound;
	int user_alarm_bive;
}
