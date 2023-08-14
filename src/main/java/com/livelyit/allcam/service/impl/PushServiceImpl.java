package com.livelyit.allcam.service.impl;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.ApsAlert;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.DefaultDTO;
import com.livelyit.allcam.service.PushService;

import com.eatthepath.pushy.apns.*;
import com.eatthepath.pushy.apns.auth.ApnsSigningKey;
import com.eatthepath.pushy.apns.proxy.HttpProxyHandlerFactory;
import com.eatthepath.pushy.apns.util.ApnsPayloadBuilder;
import com.eatthepath.pushy.apns.util.SimpleApnsPayloadBuilder;
import com.eatthepath.pushy.apns.util.SimpleApnsPushNotification;
import com.eatthepath.pushy.apns.util.TokenUtil;
import com.eatthepath.pushy.apns.util.concurrent.PushNotificationFuture;

import static com.livelyit.allcam.common.Utils.API_LOG_PATH;

@Service
public class PushServiceImpl implements PushService {
	@Override
	public DefaultDTO sendDataPush(String pushKey, String pushType, String data, String title, String body) {
		DefaultDTO dto = new DefaultDTO();

		if(!"".equals(pushKey) && !"NONE".equals(pushKey)) {
			ApsAlert apsAlert = ApsAlert.builder()
			.setTitle(title)
			.setBody(body).build();
	
			Aps aps = Aps.builder()
					.setBadge(1)
					.setSound("default")
					.setAlert(apsAlert)
					.build();
			
			Message message = Message.builder()
					.putData("pushType", pushType)
					.putData("data", data)
					.putData("priority", "high")
					.putData("content_available", "true")
					.setToken(pushKey)
					.setApnsConfig(ApnsConfig.builder().setAps(aps).build())
					.build();

			String response;
			
			Utils.getFBA();

			try {
				response = FirebaseMessaging.getInstance().sendAsync(message).get();


			} catch (InterruptedException | ExecutionException e) {

				PrintStream ps = null;
				FileOutputStream fos=null;
				Date today = new Date();

				SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
				String dateStr = date.format(today);
				String timeStr = time.format(today);

				try {
					fos = new FileOutputStream(API_LOG_PATH +"push_send_error_"+dateStr+".log",true); // error.log파일에 출력 준비
					ps=new PrintStream(fos); // err의 출력을 화면이 아닌, error.log파일로 변경
					System.setErr(ps);

					System.err.println("-----------------------------------");
					System.err.println("[" + dateStr+ " "+timeStr+"]");  // 현재시간출력
					System.err.println("보내려 했던 push type : " + pushType);
					System.err.println("보내려 했던 push key : " + pushKey);
					System.err.println("보내려 했던 push data: " + data);
					System.err.println("보내려 했던 push title: " + title);
					System.err.println("보내려 했던 push body: " + body);
					System.err.println("예외메시지 : " + e.getMessage());
					e.printStackTrace(System.err);
					dto.setReason("PUSH 발송 에러");
				} catch (FileNotFoundException fileNotFoundException) {
					fileNotFoundException.printStackTrace();
				}finally {
					try {
						if(fos != null)
							fos.close();
					} catch (IOException ioException) {
						ioException.printStackTrace();
					}
				}


				return dto;
			}
			dto.setResult(Utils.SUCCESS);
		}
		
		return dto;
	}

	@Override
	public DefaultDTO sendDataVoipPush(String pushKey, String voipKey, String pushType, String data, String title, String body) {
		DefaultDTO dto = new DefaultDTO();

		if(!"".equals(pushKey) && !"NONE".equals(pushKey)) {
			if ( pushType == "1" && voipKey != null && !"".equals(voipKey) && !"NONE".equals(voipKey)) { //pushkit일때는 apns
				PrintStream ps = null;
				FileOutputStream fos=null;
				Date today = new Date();

				SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
				SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
				String dateStr = date.format(today);
				String timeStr = time.format(today);

				try {

					fos = new FileOutputStream(API_LOG_PATH +"push_try_to_send_"+dateStr+".log",true); // error.log파일에 출력 준비
					ps=new PrintStream(fos); // err의 출력을 화면이 아닌, error.log파일로 변경
					System.setErr(ps);

					System.err.println("-----------------------------------");
					System.err.println("[" + dateStr+ " "+timeStr+"]");  // 현재시간출력
					System.err.println("보내려 했던 push type : " + pushType);
					System.err.println("보내려 했던 push key : " + pushKey);
					System.err.println("보내려 했던 voip key : " + voipKey);
					System.err.println("보내려 했던 push data: " + data);
					System.err.println("보내려 했던 push title: " + title);
					System.err.println("보내려 했던 push body: " + body);

					final InputStream certificate = Thread.currentThread().getContextClassLoader()
					    .getResourceAsStream("voipcert.p12");

					final ApnsClientBuilder apnsClientBuilder = new ApnsClientBuilder();

					apnsClientBuilder.setApnsServer(ApnsClientBuilder.PRODUCTION_APNS_HOST);
					// apnsClientBuilder.setApnsServer(ApnsClientBuilder.DEVELOPMENT_APNS_HOST);
					apnsClientBuilder.setConcurrentConnections(3);
					apnsClientBuilder.setClientCredentials(certificate, "12345678");
					// apnsClientBuilder.setConnectionTimeout(5,TimeUnit.SECONDS);
					final ApnsClient apnsClient = apnsClientBuilder.build();
					final SimpleApnsPushNotification pushNotification;

					{
						final ApnsPayloadBuilder payloadBuilder = new SimpleApnsPayloadBuilder();
						payloadBuilder.setAlertBody(body);
						payloadBuilder.setAlertTitle(title);
						payloadBuilder.setBadgeNumber(1);
						payloadBuilder.setSound("default");
						payloadBuilder.setContentAvailable(true);
						payloadBuilder.addCustomProperty("pushType", pushType);
						payloadBuilder.addCustomProperty("data", data);
						// payloadBuilder.addCustomProperty("priority", "true");
						payloadBuilder.addCustomProperty("priority", "high");

						payloadBuilder.addCustomProperty("direct_boot_ok", "true");

						final String payload = payloadBuilder.build();
						pushNotification = new SimpleApnsPushNotification(voipKey, "com.meangsun.allcam.voip", payload, null, DeliveryPriority.IMMEDIATE, PushType.VOIP);
					}
					final PushNotificationFuture<SimpleApnsPushNotification, PushNotificationResponse<SimpleApnsPushNotification>>
    				sendNotificationFuture = apnsClient.sendNotification(pushNotification);
					
					final PushNotificationResponse<SimpleApnsPushNotification> pushNotificationResponse =
							sendNotificationFuture.get();
				
					if (pushNotificationResponse.isAccepted()) {
						System.err.println("Push notification accepted by APNs gateway.");
						System.err.println("---------------전송성공--------------------");	
					} else {
						System.err.println("Notification rejected by the APNs gateway: " +
								pushNotificationResponse.getRejectionReason());
				
						pushNotificationResponse.getTokenInvalidationTimestamp().ifPresent(timestamp -> {
							System.err.println("\t…and the token is invalid as of " + timestamp);
						});
						System.err.println("---------------전송실패--------------------");	
					}
					try {
						if(fos != null)
							fos.close();
					} catch (IOException ioException) {
						ioException.printStackTrace();
					}					
				} catch (Exception e) {
					System.err.println("Failed to send push notification.");
					e.printStackTrace();
					try {
						if(fos != null)
							fos.close();
					} catch (IOException ioException) {
						ioException.printStackTrace();
					}					
					try {
						fos = new FileOutputStream(API_LOG_PATH +"push_send_error_"+dateStr+".log",true); // error.log파일에 출력 준비
						ps=new PrintStream(fos); // err의 출력을 화면이 아닌, error.log파일로 변경
						System.setErr(ps);
	
						System.err.println("-----------------------------------");
						System.err.println("[" + dateStr+ " "+timeStr+"]");  // 현재시간출력
						System.err.println("보내려 했던 push type : " + pushType);
						System.err.println("보내려 했던 push key : " + pushKey);
						System.err.println("보내려 했던 push data: " + data);
						System.err.println("보내려 했던 push title: " + title);
						System.err.println("보내려 했던 push body: " + body);
						System.err.println("예외메시지 : " + e.getMessage());
						e.printStackTrace(System.err);
						dto.setReason("PUSH 발송 에러");
					} catch (FileNotFoundException fileNotFoundException) {
						fileNotFoundException.printStackTrace();
					}finally {
						try {
							if(fos != null)
								fos.close();
						} catch (IOException ioException) {
							ioException.printStackTrace();
						}
					}	
					return dto;
				}
			} else {
				ApsAlert apsAlert = ApsAlert.builder()
				.setTitle(title)
				.setBody(body).build();
		
				Aps aps = Aps.builder()
						.setBadge(1)
						.setSound("default")
						.setAlert(apsAlert)
						.build();
				
				Message message = Message.builder()
						.putData("pushType", pushType)
						.putData("data", data)
						.putData("priority", "high")
						.putData("content_available", "true")
						.setToken(pushKey)
						.setApnsConfig(ApnsConfig.builder().setAps(aps).build())
						.build();
	
				String response;
				
				Utils.getFBA();
	
				try {
					response = FirebaseMessaging.getInstance().sendAsync(message).get();
	
	
				} catch (InterruptedException | ExecutionException e) {
	
					PrintStream ps = null;
					FileOutputStream fos=null;
					Date today = new Date();
	
					SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
					SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
					String dateStr = date.format(today);
					String timeStr = time.format(today);
	
					try {
						fos = new FileOutputStream(API_LOG_PATH +"push_send_error_"+dateStr+".log",true); // error.log파일에 출력 준비
						ps=new PrintStream(fos); // err의 출력을 화면이 아닌, error.log파일로 변경
						System.setErr(ps);
	
						System.err.println("-----------------------------------");
						System.err.println("[" + dateStr+ " "+timeStr+"]");  // 현재시간출력
						System.err.println("보내려 했던 push type : " + pushType);
						System.err.println("보내려 했던 push key : " + pushKey);
						System.err.println("보내려 했던 push data: " + data);
						System.err.println("보내려 했던 push title: " + title);
						System.err.println("보내려 했던 push body: " + body);
						System.err.println("예외메시지 : " + e.getMessage());
						e.printStackTrace(System.err);
						dto.setReason("PUSH 발송 에러");
					} catch (FileNotFoundException fileNotFoundException) {
						fileNotFoundException.printStackTrace();
					}finally {
						try {
							if(fos != null)
								fos.close();
						} catch (IOException ioException) {
							ioException.printStackTrace();
						}
					}
	
					return dto;
				}
				dto.setResult(Utils.SUCCESS);	
			}
		}
		
		return dto;
	}
	
	@Override
	public DefaultDTO sendListPushTopic(String topic, String pushType, String data, String title, String body) {
		DefaultDTO dto = new DefaultDTO();
		
		ApsAlert apsAlert = ApsAlert.builder()
				.setTitle(title)
				.setBody(body).build();
		
		Aps aps = Aps.builder()
		          .setBadge(1)
		          .setSound("default")
		          .setAlert(apsAlert)
		          .build();
		
		Message message = Message.builder()
			    .putData("pushType", pushType)
			    .putData("data", data)
			    .putData("priority", "high")
			    .putData("content_available", "true")
			    .setApnsConfig(ApnsConfig.builder().setAps(aps).build())
			    .setTopic(topic)
			    .build();

		String response;
		
		Utils.getFBA();
				
		try {
			response = FirebaseMessaging.getInstance().sendAsync(message).get();
			
			FirebaseMessaging.getInstance().sendAsync(message);
			System.out.println("Successfully sendListPush message: " + response);
		} catch (InterruptedException e) {
			e.printStackTrace();
			dto.setReason("PUSH TOPIC 발송 에러");
			return dto;
		} catch (ExecutionException e) {
			e.printStackTrace();
			dto.setReason("PUSH TOPIC 발송 에러");
			return dto;
		}
		dto.setResult(Utils.SUCCESS);
		
		return dto;
	}
}