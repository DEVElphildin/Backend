package com.livelyit.allcam.service;

import com.livelyit.allcam.dto.DefaultDTO;

public interface PushService {
	public DefaultDTO sendDataPush(String pushKey, String pushType, String data, String title, String body);
	public DefaultDTO sendDataVoipPush(String pushKey, String voipKey, String pushType, String data, String title, String body);
	public DefaultDTO sendListPushTopic(String topic, String pushType, String data, String title, String body);
}