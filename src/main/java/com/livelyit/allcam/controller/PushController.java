package com.livelyit.allcam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.livelyit.allcam.dto.DefaultDTO;
import com.livelyit.allcam.dto.PushType;
import com.livelyit.allcam.service.PushService;

@RestController
public class PushController {
	@Autowired
	PushService pushService;
	
//	@RequestMapping("/testTopic")
//	@ResponseBody
//	public DefaultDTO testTopic() {
//		//전체 : all
//		//여성 : female
//		//남성 : male
//		
//		pushService.sendListPushTopic("all", PushType.NOTIGET.getTypeCd(), "전체용 입니다");
//		pushService.sendListPushTopic("male", PushType.NOTIGET.getTypeCd(), "남자용 입니다");
//		return pushService.sendListPushTopic("female", PushType.NOTIGET.getTypeCd(), "여자용 입니다");
//	}
//	
//	@RequestMapping("/testPush")
//	@ResponseBody
//	public DefaultDTO testPush() {
//		String pushKey = "cAijttw5S2CEZ8kY94bCf8:APA91bFVp46nP33S0n1ETTM5eqGQmQhgaktv9y-ZV0zcrNi6zR7m0m4hkJAwZlIVMjeu2besL-GNLmJUQn_o-CmKQw2fQRCCxQO3ZQNZMTwK1t7t1t9XDx0ZRao3ebYC9UyVMYMc-nuQ";
////		return pushService.sendDataPush(pushKey, "11", "");
//		return pushService.sendDataPush(pushKey, PushType.MOOTONGJANGOK.getTypeCd(), "입금이 확인되었다");
//	}
}
