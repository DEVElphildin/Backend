package com.livelyit.allcam.service.impl;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.google.gson.Gson;
import com.livelyit.allcam.dto.*;
import com.livelyit.allcam.mapper.*;
import com.livelyit.allcam.service.PushService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.mapper.SMSMapper;
import com.livelyit.allcam.service.UserService;

import static com.livelyit.allcam.common.Utils.API_LOG_PATH;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;

	@Autowired
	SMSMapper smsMapper;

	@Autowired
	ConversationMapper conversationMapper;

	@Autowired
	LiveCallMapper liveCallMapper;

	@Autowired
	VersionMapper versionMapper;

	@Autowired
	PushService pushService;

	@Autowired
	ChatMapper chatMapper;

	@Autowired
	UserService userService;

	// private GoogleIdToken getTokenInfo(String token) throws
	// GeneralSecurityException, IOException {
	// HttpTransport transport = new NetHttpTransport();
	// JacksonFactory jsonFactory = new JacksonFactory();
	//
	// GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport,
	// jsonFactory)
	// // Specify the CLIENT_ID of the app that accesses the backend:
	//// .setAudience(Collections.singletonList("292067405474-a4eqsvpkfcqfhmeiqumovp805ckoruqi.apps.googleusercontent.com"))
	// .setAudience(Collections.singletonList("292067405474-r6p98a3fgrfd4b92lknjn21o6gp79f55.apps.googleusercontent.com"))
	// // Or, if multiple clients access the backend:
	// //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
	// .build();
	//
	// return verifier.verify(token);
	// }

	// @Override
	// public LoginDTO login(LoginDTO dto) {
	// LoginDTO resultDto = new LoginDTO();
	//
	//// try {
	//// GoogleIdToken idToken = getTokenInfo(token);
	//// if (idToken != null) {
	//// Payload payload = idToken.getPayload();
	//// String userId = payload.getSubject();
	//// // Get profile information from payload
	//// String email = payload.getEmail();
	//// boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
	//// String name = (String) payload.get("name");
	//// String pictureUrl = (String) payload.get("picture");
	//// String locale = (String) payload.get("locale");
	//// String familyName = (String) payload.get("family_name");
	//// String givenName = (String) payload.get("given_name");
	//
	// resultDto = userMapper.login(dto);
	//
	// if(resultDto != null) {
	// resultDto.setOs_type(dto.getOs_type());
	// if(resultDto.getUser_type() == -1) { //탈퇴 한 회원
	// resultDto.setError_code("-1");
	// resultDto.setReason("탈퇴 된 회원입니다");
	// }else if(resultDto.getUser_type() == 1) { //정지 된 회원
	// resultDto.setError_code("-1");
	// resultDto.setReason("정지 된 회원입니다\n정지일자:
	// "+resultDto.getUser_start_stop_date()+"~"+resultDto.getUser_end_stop_date()+"\n정지사유:
	// "+resultDto.getStop_text());
	// }else {
	//
	// // 폰넘버 확인 후 수정
	// if(!"NONE".equals(dto.getUser_phone_number())) {
	// resultDto.setUser_phone_number(dto.getUser_phone_number());
	// //사용자 phone number 입력
	// userMapper.updateUserPhoneNumber(resultDto);
	// }
	// // FCM KEY 확인 후 수정
	// if(!"NONE".equals( dto.getUser_push_key())) {
	// resultDto.setUser_push_key(dto.getUser_push_key());
	//
	// // 기존에 동일한 user_push_key가 있는 경우 'NONE'로 초기화
	// userMapper.deduplicationPushKey(resultDto);
	//
	// // 로그인 사용자의 pushKey와 country, language code 업데이트
	// userMapper.setPushKey(resultDto);
	// }
	//
	// if(!"NONE".equals(dto.getUser_country_code())) {
	// // country, language code 수정
	// resultDto.setUser_country_code(dto.getUser_country_code());
	// resultDto.setUser_language_code(dto.getUser_language_code());
	//
	// // country, language code 업데이트
	// userMapper.setCountryLanguage(resultDto);
	// }
	//
	// //if(null == resultDto.getOs_type()) {
	// // resultDto.setOs_type("android");
	// //}
	// LoginLogDTO logDto = new LoginLogDTO();
	// logDto.setUser_no(resultDto.getNo());
	// logDto.setOs_type(resultDto.getOs_type());
	// int logResult = userMapper.loginLog(logDto);
	//
	// if(logResult == 1) {
	// int alarmResult = userMapper.updateUserAlarm(resultDto.getNo());
	//
	// if(alarmResult == 1) {
	// resultDto.setResult(Utils.SUCCESS);
	// }
	//
	// }else {
	// resultDto.setReason("LOGIN_LOG_DB_FAIL");
	// }
	// }
	// }else {
	// resultDto = new LoginDTO();
	// resultDto.setReason("LOGIN_DB_FAIL");
	// }
	//// } else {
	//// resultDto.setReason("LOGIN_AUTH_FAIL");
	//// }
	//// } catch (GeneralSecurityException e) {
	//// // TODO Auto-generated catch bloc
	//// e.printStackTrace();
	//// } catch (IOException e) {
	//// // TODO Auto-generated catch block
	//// e.printStackTrace();
	//// }
	//
	// return resultDto;
	// }
	//
	// @Override
	// public LoginDTO loginV2(LoginDTO dto) {
	// LoginDTO resultDto = new LoginDTO();
	//
	// /* 로그인 에러 로그를 파일로 저장하는 코드 */
	// /*PrintStream ps = null;
	// FileOutputStream fos=null;
	// Date today = new Date();
	//
	// SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
	// SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
	// String dateStr = date.format(today);
	// String timeStr = time.format(today);
	//
	// try {
	// fos = new
	// FileOutputStream(PUSH_SEND_ERROR_LOG_PATH+"login_error_"+dateStr+".log",true);
	// // error.log파일에 출력 준비
	// ps=new PrintStream(fos); // err의 출력을 화면이 아닌, error.log파일로 변경
	// System.setErr(ps);
	//
	// System.err.println("-----------------------------------");
	// System.err.println("[" + dateStr+ " "+timeStr+"] "+dto.getIp()); // 현재시간출력
	// System.err.println("getUser_id : " + dto.getUser_id());
	// System.err.println("getUser_push_key: " + dto.getUser_push_key());
	// System.err.println("Os_type: " + dto.getOs_type());
	// System.err.println("User_service_type: " + dto.getUser_service_type());
	// System.err.println("User_identi_code: " + dto.getUser_identi_code());
	// System.err.println("User_phone_number " + dto.getUser_phone_number());
	// System.err.println("User_country_code " + dto.getUser_country_code());
	// System.err.println("User_language_code: " + dto.getUser_language_code());
	// dto.setReason("PUSH 발송 에러");
	// } catch (FileNotFoundException fileNotFoundException) {
	// fileNotFoundException.printStackTrace();
	// }finally {
	// try {
	// if(fos != null)
	// fos.close();
	// } catch (IOException ioException) {
	// ioException.printStackTrace();
	// }
	// }*/
	// LoginDTO _dto = dto;
	// resultDto = userMapper.login(dto);
	//
	// if(resultDto != null) {
	// resultDto.setOs_type(dto.getOs_type());
	// if(resultDto.getUser_type() == -1) { //탈퇴 한 회원
	// resultDto.setError_code("-1");
	// resultDto.setReason("탈퇴 된 회원입니다");
	// }else if(resultDto.getUser_type() == 1) { //정지 된 회원
	// resultDto.setError_code("-1");
	// resultDto.setReason("정지 된 회원입니다\n정지일자:
	// "+resultDto.getUser_start_stop_date()+"~"+resultDto.getUser_end_stop_date()+"\n정지사유:
	// "+resultDto.getStop_text());
	// }else {
	//
	// // 기존에 동일한 user_push_key가 있는 경우 'NONE'로 초기화
	// userMapper.deduplicationPushKey(_dto);
	// // 폰넘버, FCM KEY, country, language code, identicode 수정
	// _dto.setNo(dto.getNo());
	// userMapper.updateUserInfo(_dto);
	//
	// LoginLogDTO logDto = new LoginLogDTO();
	// logDto.setUser_no(resultDto.getNo());
	// logDto.setOs_type(resultDto.getOs_type());
	// int logResult = userMapper.loginLog(logDto);
	//
	// if(logResult == 1) {
	// int alarmResult = userMapper.updateUserAlarm(resultDto.getNo());
	//
	// if(alarmResult == 1) {
	// resultDto.setResult(Utils.SUCCESS);
	// }
	//
	// }else {
	// resultDto.setReason("LOGIN_LOG_DB_FAIL");
	// }
	//
	// // 초당 캐시 정보를 넣어줌
	// int cash_per_second = 10;
	// if (resultDto.getUser_gender().equals("여성") && resultDto.getUser_part_time()
	// ==1
	// && !resultDto.getAgent_id().equals("") &&
	// !resultDto.getAgent_id().equals("NONE")){
	// AgentDTO agentDTO = userMapper.getAgentCashInfo(resultDto.getAgent_id());
	// if (agentDTO!= null){
	// if (agentDTO.getCash_per_second() > 0){
	// cash_per_second = agentDTO.getCash_per_second();
	// resultDto.setIs_refund_visible(agentDTO.getIs_refund_visible());
	// }
	//
	// }
	// }
	// resultDto.setCash_per_second(cash_per_second);
	//
	// // 배너 정보를 넣는다.
	// resultDto.setBanner_info(versionMapper.getMainBannerV3(resultDto));
	//
	// LoginDTO finalResultDto = resultDto;
	// new Thread(() -> {
	// Gson gson = new Gson();
	//
	// // 영상 통화한 사람 목록을 가져온다.
	// ArrayList<MyVideoCallUserDTO> myVideoCallUserList =
	// userMapper.selectMyVideoCallUserList(finalResultDto);
	//
	// for (MyVideoCallUserDTO myVideoCallUserDTO : myVideoCallUserList){
	// System.out.print("이름 : " +myVideoCallUserDTO.getUser_nick_name());
	//
	// // 유효한 FCM KEY가 있는 경우
	// if (!myVideoCallUserDTO.getUser_push_key().equals("NONE")){
	// // 알림 메세지를 보낸다.
	//
	// JSONObject userInfoJson = new JSONObject();
	// userInfoJson.put("user_nick_name", finalResultDto.getUser_nick_name());
	// userInfoJson.put("user_imgs", finalResultDto.getUser_imgs());
	// String userInfoStr = userInfoJson.toString();
	// pushService.sendDataPush(myVideoCallUserDTO.getUser_push_key(),
	// PushType.CALLUSERLOGIN.getTypeCd(), userInfoStr,
	// myVideoCallUserDTO.getUser_nick_name(), "전화했던 유저 로그인"); //상대 방한테 푸쉬 콜
	//
	// }
	// // 유저의 마지막 push 받은 시간을 수정한다.
	// userMapper.updateUserLastPush(myVideoCallUserDTO);
	//
	// }
	// }).start();
	// }
	// }else {
	// /* 로그인 에러 로그를 파일로 저장하는 코드 */
	// /*try {
	// fos = new
	// FileOutputStream(PUSH_SEND_ERROR_LOG_PATH+"login_error_"+dateStr+".log",true);
	// // error.log파일에 출력 준비
	// ps=new PrintStream(fos); // err의 출력을 화면이 아닌, error.log파일로 변경
	// System.setErr(ps);
	//
	// System.err.println("login 쿼리 실행 실패");
	// } catch (FileNotFoundException fileNotFoundException) {
	// fileNotFoundException.printStackTrace();
	// }finally {
	// bookMarkList try {
	// if(fos != null)
	// fos.close();
	// } catch (IOException ioException) {
	// ioException.printStackTrace();
	// }
	// }*/
	// resultDto = new LoginDTO();
	// resultDto.setReason("LOGIN_DB_FAIL");
	// }
	//
	// return resultDto;
	// }
	//
	//
	@Override
	public LoginDTO loginV4(LoginDTO dto) {
		LoginDTO resultDto = new LoginDTO();

		/* 로그인 에러 로그를 파일로 저장하는 코드 */
		PrintStream ps = null;
		FileOutputStream fos = null;
		Date today = new Date();

		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
		String dateStr = date.format(today);
		String timeStr = time.format(today);

		try {
			fos = new FileOutputStream(API_LOG_PATH + "login_" + dateStr + ".log", true); // error.log파일에 출력 준비
			ps = new PrintStream(fos); // err의 출력을 화면이 아닌, error.log파일로 변경
			System.setErr(ps);

			System.err.println("-----------------------------------");
			System.err.println("[" + dateStr + " " + timeStr + "] " + dto.getIp()); // 현재시간출력
			System.err.println("getUser_id  : " + dto.getUser_id());
			System.err.println("getUser_push_key: " + dto.getUser_push_key());
			System.err.println("Os_type: " + dto.getOs_type());
			System.err.println("User_service_type: " + dto.getUser_service_type());
			System.err.println("User_identi_code: " + dto.getUser_identi_code());
			System.err.println("User_phone_number " + dto.getUser_phone_number());
			System.err.println("User_country_code " + dto.getUser_country_code());
			System.err.println("User_language_code: " + dto.getUser_language_code());
			System.err.println("Login_type: " + dto.getLogin_type());

		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}

		LoginDTO _dto = dto;
		resultDto = userMapper.loginV2(dto);

		if (resultDto != null) {
			resultDto.setOs_type(dto.getOs_type());
			if (resultDto.getUser_type() == -1) { // 탈퇴 한 회원
				resultDto.setError_code("-1");
				resultDto.setReason("탈퇴 된 회원입니다");
			} else if (resultDto.getUser_type() == 1) { // 정지 된 회원
				resultDto.setError_code("-1");
				String message = "";
				if (!dto.getUser_language_code().equals("ko")) {
					message = "Your account has been suspended\nSuspended period: "
							+ resultDto.getUser_start_stop_date() + "~" + resultDto.getUser_end_stop_date()
							+ "\nReason: " + resultDto.getStop_text();
				} else {
					message = "정지 된 회원입니다\n정지일자: " + resultDto.getUser_start_stop_date() + "~"
							+ resultDto.getUser_end_stop_date() + "\n정지사유: " + resultDto.getStop_text();
				}
				resultDto.setReason(message);
			} else {

				// 기존에 동일한 user_push_key가 있는 경우 'NONE'로 초기화
				userMapper.deduplicationPushKey(_dto);
				if (_dto.getUser_voip_key() != null && _dto.getUser_voip_key() != "NONE"
						&& _dto.getUser_voip_key() != "")
					userMapper.deduplicationVoipPushKey(_dto);
				// 폰넘버, FCM KEY, country, language code, identicode 수정
				_dto.setNo(resultDto.getNo());
				userMapper.updateUserInfo(_dto);
				resultDto.setUser_voip_key(_dto.getUser_voip_key());
				LoginLogDTO logDto = new LoginLogDTO();
				logDto.setUser_no(resultDto.getNo());
				logDto.setOs_type(resultDto.getOs_type());
				logDto.setVersion(resultDto.getVersion());
				int logResult = userMapper.loginLog(logDto);

				if (logResult == 1) {
					int alarmResult = userMapper.updateUserAlarm(resultDto.getNo());

					if (alarmResult == 1) {
						resultDto.setResult(Utils.SUCCESS);
					}

				} else {
					resultDto.setReason("LOGIN_LOG_DB_FAIL");
				}

				// 초당 캐시, 포인트 정보를 넣어줌
				int cash_per_second = Utils.MC_CASH_PER_SECONDS;
				int point_per_second = Utils.MC_POINT_PER_SECONDS;
				if (dto.getUser_service_type().equals("meincam")) {
					cash_per_second = Utils.MC_CASH_PER_SECONDS;
					point_per_second = Utils.MC_POINT_PER_SECONDS;
				}
				// 여성 + 알바 + 에이전트 아이디가 빈 값, 기본 값이 아닌 경우
				if (resultDto.getUser_gender().equals("여성") && resultDto.getUser_part_time() == 1
						&& !resultDto.getAgent_id().equals("") && !resultDto.getAgent_id().equals("NONE")) {
					AgentDTO agentDTO = userMapper.getAgentCashInfo(resultDto.getAgent_id());
					if (agentDTO != null) {
						if (agentDTO.getCash_per_second() > 0) {
							cash_per_second = agentDTO.getCash_per_second();
							resultDto.setIs_refund_visible(agentDTO.getIs_refund_visible());
						}
					}
				}
				resultDto.setCash_per_second(cash_per_second);
				resultDto.setPoint_per_second(point_per_second);
				resultDto.setVoice_cash_per_second(Utils.VOICE_CASH_PER_SECONDS);
				resultDto.setVoice_point_per_second(Utils.VOICE_POINT_PER_SECONDS);
				resultDto.setMinimum_payback(Utils.MINIMUM_PAYBACK);

				// 배너 정보를 넣는다.
				resultDto.setBanner_info(versionMapper.getMainBannerV3(resultDto));

				LoginDTO finalResultDto = resultDto;
				new Thread(() -> {
					Gson gson = new Gson();

					// 영상 통화한 사람 목록을 가져온다.
					ArrayList<MyVideoCallUserDTO> myVideoCallUserList = userMapper
							.selectMyVideoCallUserList(finalResultDto);

					for (MyVideoCallUserDTO myVideoCallUserDTO : myVideoCallUserList) {
						System.out.print("이름 : " + myVideoCallUserDTO.getUser_nick_name());

						// 유효한 FCM KEY가 있는 경우
						if (!myVideoCallUserDTO.getUser_push_key().equals("NONE")) {
							// 알림 메세지를 보낸다.

							// jin 2022.8.16
							// JSONObject userInfoJson = new JSONObject();
							// userInfoJson.put("user_nick_name", finalResultDto.getUser_nick_name());
							// userInfoJson.put("user_imgs", finalResultDto.getUser_imgs());
							// String userInfoStr = userInfoJson.toString();
							// pushService.sendDataPush(myVideoCallUserDTO.getUser_push_key(),
							// PushType.CALLUSERLOGIN.getTypeCd(), userInfoStr,
							// myVideoCallUserDTO.getUser_nick_name(), "전화했던 유저 로그인"); //상대 방한테 푸쉬 콜

						}
						// 유저의 마지막 push 받은 시간을 수정한다.
						userMapper.updateUserLastPush(myVideoCallUserDTO);

					}
				}).start();
			}
		} else {
			/* 로그인 에러 로그를 파일로 저장하는 코드 */
			try {
				fos = new FileOutputStream(API_LOG_PATH + "login_" + dateStr + ".log", true); // error.log파일에 출력 준비
				ps = new PrintStream(fos); // err의 출력을 화면이 아닌, error.log파일로 변경
				System.setErr(ps);

				System.err.println("login 쿼리 실행 실패");
			} catch (FileNotFoundException fileNotFoundException) {
				fileNotFoundException.printStackTrace();
			} finally {
				try {
					if (fos != null)
						fos.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
			resultDto = new LoginDTO();
			resultDto.setReason("LOGIN_DB_FAIL");
		}

		return resultDto;
	}

	@Override
	public LoginDTO loginV5(LoginDTO dto) {
		LoginDTO resultDto = new LoginDTO();

		/* 로그인 에러 로그를 파일로 저장하는 코드 */
		PrintStream ps = null;
		FileOutputStream fos = null;
		Date today = new Date();

		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
		String dateStr = date.format(today);
		String timeStr = time.format(today);

		try {
			fos = new FileOutputStream(API_LOG_PATH + "login_" + dateStr + ".log", true); // error.log파일에 출력 준비
			ps = new PrintStream(fos); // err의 출력을 화면이 아닌, error.log파일로 변경
			System.setErr(ps);

			System.err.println("------loginV5--------------------");
			System.err.println("[" + dateStr + " " + timeStr + "] " + dto.getIp()); // 현재시간출력
			System.err.println("getUser_id  : " + dto.getUser_id());
			System.err.println("getUser_push_key: " + dto.getUser_push_key());
			System.err.println("Os_type: " + dto.getOs_type());
			System.err.println("User_service_type: " + dto.getUser_service_type());
			System.err.println("User_identi_code: " + dto.getUser_identi_code());
			System.err.println("User_phone_number " + dto.getUser_phone_number());
			System.err.println("User_country_code " + dto.getUser_country_code());
			System.err.println("User_language_code: " + dto.getUser_language_code());
			System.err.println("Login_type: " + dto.getLogin_type());
			System.err.println("version: " + dto.getVersion());

		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}

		LoginDTO _dto = dto;
		resultDto = userMapper.loginV2(dto);

		if (resultDto != null) {
			resultDto.setOs_type(dto.getOs_type());
			String service = dto.getUser_service_type();
			if (resultDto.getUser_type() == -1) { // 탈퇴 한 회원
				resultDto.setError_code("-1");
				resultDto.setReason("탈퇴 된 회원입니다");
			} else if (resultDto.getUser_type() == 1) { // 정지 된 회원
				resultDto.setError_code("-1");
				String message = "";
				if (!dto.getUser_language_code().equals("ko")) {
					message = "Your account has been suspended\nSuspended period: "
							+ resultDto.getUser_start_stop_date() + "~" + resultDto.getUser_end_stop_date()
							+ "\nReason: " + resultDto.getStop_text();
				} else {
					message = "정지 된 회원입니다\n정지일자: " + resultDto.getUser_start_stop_date() + "~"
							+ resultDto.getUser_end_stop_date() + "\n정지사유: " + resultDto.getStop_text();
				}
				resultDto.setReason(message);
			} else {

				// 기존에 동일한 user_push_key가 있는 경우 'NONE'로 초기화
				userMapper.deduplicationPushKey(_dto);
				if (_dto.getUser_voip_key() != null && _dto.getUser_voip_key() != "NONE"
						&& _dto.getUser_voip_key() != "")
					userMapper.deduplicationVoipPushKey(_dto);
				// 폰넘버, FCM KEY, country, language code, identicode 수정
				_dto.setNo(resultDto.getNo());
				userMapper.updateUserInfo(_dto);
				resultDto.setUser_voip_key(_dto.getUser_voip_key());
				LoginLogDTO logDto = new LoginLogDTO();
				logDto.setUser_no(resultDto.getNo());
				logDto.setOs_type(resultDto.getOs_type());
				logDto.setVersion(dto.getVersion());
				int logResult = userMapper.loginLog(logDto);

				if (logResult == 1) {
					int alarmResult = userMapper.updateUserAlarm(resultDto.getNo());

					if (alarmResult == 1) {
						resultDto.setResult(Utils.SUCCESS);
					}

				} else {
					resultDto.setReason("LOGIN_LOG_DB_FAIL");
				}

				// 초당 캐시, 포인트 정보를 넣어줌
				int cash_per_second = Utils.MC_CASH_PER_SECONDS;
				int point_per_second = Utils.MC_POINT_PER_SECONDS;

				// 여성 + 알바 + 에이전트 아이디가 빈 값, 기본 값이 아닌 경우
				if (resultDto.getUser_gender().equals("여성") && resultDto.getUser_part_time() == 1
						&& !resultDto.getAgent_id().equals("") && !resultDto.getAgent_id().equals("NONE")) {
					AgentDTO agentDTO = userMapper.getAgentCashInfo(resultDto.getAgent_id());
					if (agentDTO != null) {
						if (agentDTO.getCash_per_second() > 0) {
							cash_per_second = agentDTO.getCash_per_second();
							resultDto.setIs_refund_visible(agentDTO.getIs_refund_visible());
						}
					}
				}
				resultDto.setCash_per_second(cash_per_second);
				resultDto.setPoint_per_second(point_per_second);
				resultDto.setVoice_cash_per_second(Utils.VOICE_CASH_PER_SECONDS);
				resultDto.setVoice_point_per_second(Utils.VOICE_POINT_PER_SECONDS);
				resultDto.setMinimum_payback(Utils.MINIMUM_PAYBACK);

				// 배너 정보를 넣는다.
				resultDto.setBanner_info(versionMapper.getMainBannerV3(resultDto));

				LoginDTO finalResultDto = resultDto;
				new Thread(() -> {
					Gson gson = new Gson();

					// 영상 통화한 사람 목록을 가져온다.
					ArrayList<MyVideoCallUserDTO> myVideoCallUserList = userMapper
							.selectMyVideoCallUserList(finalResultDto);

					for (MyVideoCallUserDTO myVideoCallUserDTO : myVideoCallUserList) {
						System.out.print("이름 : " + myVideoCallUserDTO.getUser_nick_name());

						// 유효한 FCM KEY가 있는 경우
						if (!myVideoCallUserDTO.getUser_push_key().equals("NONE")) {
							// 알림 메세지를 보낸다.
							// jin 2022.8.16
							// JSONObject userInfoJson = new JSONObject();
							// userInfoJson.put("user_nick_name", finalResultDto.getUser_nick_name());
							// userInfoJson.put("user_imgs", finalResultDto.getUser_imgs());
							// String userInfoStr = userInfoJson.toString();
							// pushService.sendDataPush(myVideoCallUserDTO.getUser_push_key(),
							// PushType.CALLUSERLOGIN.getTypeCd(), userInfoStr,
							// myVideoCallUserDTO.getUser_nick_name(), "전화했던 유저 로그인"); //상대 방한테 푸쉬 콜

						}
						// 유저의 마지막 push 받은 시간을 수정한다.
						userMapper.updateUserLastPush(myVideoCallUserDTO);

					}
				}).start();
			}
		} else {
			/* 로그인 에러 로그를 파일로 저장하는 코드 */
			try {
				fos = new FileOutputStream(API_LOG_PATH + "login_" + dateStr + ".log", true); // error.log파일에 출력 준비
				ps = new PrintStream(fos); // err의 출력을 화면이 아닌, error.log파일로 변경
				System.setErr(ps);

				System.err.println("login 쿼리 실행 실패");
			} catch (FileNotFoundException fileNotFoundException) {
				fileNotFoundException.printStackTrace();
			} finally {
				try {
					if (fos != null)
						fos.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
			resultDto = new LoginDTO();
			resultDto.setReason("LOGIN_DB_FAIL");
		}

		return resultDto;
	}

	@Override
	public LoginDTO loginV6(LoginDTO dto) {
		LoginDTO resultDto = new LoginDTO();

		/* 로그인 에러 로그를 파일로 저장하는 코드 */
		PrintStream ps = null;
		FileOutputStream fos = null;
		Date today = new Date();

		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
		String dateStr = date.format(today);
		String timeStr = time.format(today);

		try {
			fos = new FileOutputStream(API_LOG_PATH + "login_" + dateStr + ".log", true); // error.log파일에 출력 준비
			ps = new PrintStream(fos); // err의 출력을 화면이 아닌, error.log파일로 변경
			System.setErr(ps);

			System.err.println("------loginV6--------------------");
			System.err.println("[" + dateStr + " " + timeStr + "] " + dto.getIp()); // 현재시간출력
			System.err.println("getUser_id  : " + dto.getUser_id());
			System.err.println("getUser_push_key: " + dto.getUser_push_key());
			System.err.println("Os_type: " + dto.getOs_type());
			System.err.println("User_service_type: " + dto.getUser_service_type());
			System.err.println("User_identi_code: " + dto.getUser_identi_code());
			System.err.println("User_phone_number " + dto.getUser_phone_number());
			System.err.println("User_country_code " + dto.getUser_country_code());
			System.err.println("User_language_code: " + dto.getUser_language_code());
			System.err.println("Login_type: " + dto.getLogin_type());
			System.err.println("version: " + dto.getVersion());

		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}

		LoginDTO _dto = dto;
		resultDto = userMapper.loginV2(dto);

		if (resultDto != null) {
			resultDto.setOs_type(dto.getOs_type());
			if (resultDto.getUser_type() == -1) { // 탈퇴 한 회원
				resultDto.setError_code("-1");
				resultDto.setReason("탈퇴 된 회원입니다");
			} else if (resultDto.getUser_type() == 1) { // 정지 된 회원
				resultDto.setError_code("-1");
				String message = "";
				if (!dto.getUser_language_code().equals("ko")) {
					message = "Your account has been suspended\nSuspended period: "
							+ resultDto.getUser_start_stop_date() + "~" + resultDto.getUser_end_stop_date()
							+ "\nReason: " + resultDto.getStop_text();
				} else {
					message = "정지 된 회원입니다\n정지일자: " + resultDto.getUser_start_stop_date() + "~"
							+ resultDto.getUser_end_stop_date() + "\n정지사유: " + resultDto.getStop_text();
				}
				resultDto.setReason(message);
			} else {

				// 기존에 동일한 user_push_key가 있는 경우 'NONE'로 초기화
				userMapper.deduplicationPushKey(_dto);
				if (_dto.getUser_voip_key() != null && _dto.getUser_voip_key() != "NONE"
						&& _dto.getUser_voip_key() != "")
					userMapper.deduplicationVoipPushKey(_dto);
				// 폰넘버, FCM KEY, country, language code, identicode 수정
				_dto.setNo(resultDto.getNo());
				userMapper.updateUserInfo(_dto);
				resultDto.setUser_voip_key(_dto.getUser_voip_key());
				LoginLogDTO logDto = new LoginLogDTO();
				logDto.setUser_no(resultDto.getNo());
				logDto.setOs_type(resultDto.getOs_type());
				logDto.setVersion(dto.getVersion());
				int logResult = userMapper.loginLog(logDto);

				if (logResult == 1) {
					int alarmResult = userMapper.updateUserAlarm(resultDto.getNo());

					if (alarmResult == 1) {
						resultDto.setResult(Utils.SUCCESS);
					}

				} else {
					resultDto.setReason("LOGIN_LOG_DB_FAIL");
				}

				// 초당 캐시, 포인트 정보를 넣어줌
				int cash_per_second = Utils.NMC_CASH_PER_SECONDS;
				int point_per_second = Utils.NMC_POINT_PER_SECONDS;

				// 여성 + 알바 + 에이전트 아이디가 빈 값, 기본 값이 아닌 경우
				if (resultDto.getUser_gender().equals("여성") && resultDto.getUser_part_time() == 1
						&& !resultDto.getAgent_id().equals("") && !resultDto.getAgent_id().equals("NONE")) {
					AgentDTO agentDTO = userMapper.getAgentCashInfo(resultDto.getAgent_id());
					if (agentDTO != null) {
						if (agentDTO.getCash_per_second() > 0) {
							cash_per_second = agentDTO.getCash_per_second();
							resultDto.setIs_refund_visible(agentDTO.getIs_refund_visible());
						}
					}
				}
				resultDto.setCash_per_second(cash_per_second);
				resultDto.setPoint_per_second(point_per_second);
				resultDto.setVoice_cash_per_second(Utils.VOICE_CASH_PER_SECONDS);
				resultDto.setVoice_point_per_second(Utils.VOICE_POINT_PER_SECONDS);
				resultDto.setMinimum_payback(Utils.MINIMUM_PAYBACK);

				// 배너 정보를 넣는다.
				resultDto.setBanner_info(versionMapper.getMainBannerV3(resultDto));

				LoginDTO finalResultDto = resultDto;
				new Thread(() -> {
					Gson gson = new Gson();

					// 영상 통화한 사람 목록을 가져온다.
					ArrayList<MyVideoCallUserDTO> myVideoCallUserList = userMapper
							.selectMyVideoCallUserList(finalResultDto);

					for (MyVideoCallUserDTO myVideoCallUserDTO : myVideoCallUserList) {
						System.out.print("이름 : " + myVideoCallUserDTO.getUser_nick_name());

						// 유효한 FCM KEY가 있는 경우
						if (!myVideoCallUserDTO.getUser_push_key().equals("NONE")) {
							// 알림 메세지를 보낸다.
							// jin 2022.8.16
							// JSONObject userInfoJson = new JSONObject();
							// userInfoJson.put("user_nick_name", finalResultDto.getUser_nick_name());
							// userInfoJson.put("user_imgs", finalResultDto.getUser_imgs());
							// String userInfoStr = userInfoJson.toString();
							// pushService.sendDataPush(myVideoCallUserDTO.getUser_push_key(),
							// PushType.CALLUSERLOGIN.getTypeCd(), userInfoStr,
							// myVideoCallUserDTO.getUser_nick_name(), "전화했던 유저 로그인"); //상대 방한테 푸쉬 콜

						}
						// 유저의 마지막 push 받은 시간을 수정한다.
						userMapper.updateUserLastPush(myVideoCallUserDTO);

					}
				}).start();
			}
		} else {
			/* 로그인 에러 로그를 파일로 저장하는 코드 */
			try {
				fos = new FileOutputStream(API_LOG_PATH + "login_" + dateStr + ".log", true); // error.log파일에 출력 준비
				ps = new PrintStream(fos); // err의 출력을 화면이 아닌, error.log파일로 변경
				System.setErr(ps);

				System.err.println("login 쿼리 실행 실패");
			} catch (FileNotFoundException fileNotFoundException) {
				fileNotFoundException.printStackTrace();
			} finally {
				try {
					if (fos != null)
						fos.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
			resultDto = new LoginDTO();
			resultDto.setReason("LOGIN_DB_FAIL");
		}

		return resultDto;
	}

	@Override
	public UserInfoDTO updateVoipKey(LoginDTO dto) {
		dto.setResult(Utils.SUCCESS);
		userMapper.setVoipPushKey(dto);
		UserInfoDTO userInfoDTO = userMapper.getUserInfo(dto.getNo());
		return userInfoDTO;

	}

	@Override
	public UserInfoDTO updateCertification(LoginDTO dto) {
		dto.setResult(Utils.SUCCESS); // 11~58 -> 48개 7 ~ 46 -> 40개
		userMapper.updateCertUser(dto);
		userMapper.updateUserIsCertified(dto);
		UserInfoDTO userInfoDTO = userMapper.getUserInfo(dto.getNo());
		return userInfoDTO;
	}

	@Override
	public DefaultDTO checkAgentId(String agentId) {
		DefaultDTO dto = new DefaultDTO();

		AgentDTO agentDTO = userMapper.getAgentCashInfo(agentId);
		// 사용자가 보낸 에이전트 정보가 존재하는 경우
		if (agentDTO != null) {
			dto.setResult(Utils.SUCCESS);
		}

		return dto;
	}

	@Override
	public LoginDTO logOut(int user_no) {
		LoginDTO resultDto = new LoginDTO();

		resultDto.setNo(user_no);

		// user_push_key 초기화
		resultDto.setUser_push_key("NONE");
		resultDto.setUser_voip_key("NONE");
		userMapper.setVoipPushKey(resultDto);
		int result = userMapper.setPushKey(resultDto);

		if (result == 1) {
			resultDto.setResult(Utils.SUCCESS);
		}

		return resultDto;
	}

	// @Override
	// public LoginDTO join(LoginDTO dto) {
	// LoginDTO resultDto = new LoginDTO();
	//
	// if(dto.getUser_nick_name().length() > 8) {
	// resultDto.setReason("닉네임 8자 이하입니다");
	// resultDto.setError_code("4");
	//
	// return resultDto;
	// }
	//
	// int nickChk = userMapper.nickNameChk(dto.getUser_nick_name());
	//
	// if(nickChk > 0) {
	// resultDto.setReason("이미 사용중인 닉네임 입니다");
	// resultDto.setError_code("1");
	//
	// return resultDto;
	// }
	//
	// boolean banChkFlag = Utils.filterBanText(dto.getUser_nick_name(),
	// versionMapper.getBanText("닉네임"));
	//
	// if(banChkFlag) {
	// resultDto.setReason("닉네임에 금지어가 포함 되어 있습니다");
	// resultDto.setError_code("2");
	//
	// return resultDto;
	// }
	//
	// int joinOk = userMapper.join(dto);
	// /*
	// * TB_USER.user_point, default : 2000
	// * 20200709
	// * - TB_USER.user_point, default : 0
	// * - TB_USER.user_free_point, default : 0
	// */
	//
	// if(joinOk > -1) {
	// if(!"NONE".equals(dto.getUser_thumnail())) {
	// userMapper.userThumnailInsert(dto);
	// }
	//
	// /*
	// * 1. 영구정지 회원이지 조회
	// * 2. 영구정지 회원이면 포인트 미지급
	// * 3. 아니면 포인트 지급
	// */
	// int chkStationaryUser = userMapper.chkStationaryUser(dto);
	// if(chkStationaryUser < 2) {
	// int joinAddPoint = 2000;
	//
	// UserIDServiceTypeDTO userIDServiceTypeDTO = new UserIDServiceTypeDTO();
	// userIDServiceTypeDTO.setUser_id(dto.getUser_id());
	// userIDServiceTypeDTO.setUser_service_type(dto.getUser_service_type());
	//
	// int user_no = userMapper.selectUserNo(userIDServiceTypeDTO);
	//
	//
	// UserPointDTO pointdto = userMapper.getMyPoint(user_no);
	//
	// UserPointDTO pDto = new UserPointDTO();
	// pDto.setNo(user_no);
	// pDto.setUser_free_point(pointdto.getUser_free_point() + joinAddPoint);
	// //pDto.setUser_pay_point(pointdto.getUser_pay_point());
	//
	// int pointResult = userMapper.setPoint(pDto);
	//
	// if(pointResult == 1) {
	// UseHistoryDTO hisdto = new UseHistoryDTO();
	// hisdto.setUser_no(user_no);
	// hisdto.setUse_content("회원가입");
	// hisdto.setUse_cnt(joinAddPoint);
	// hisdto.setUse_type(2); // 0:유료포인트, 1:캐시, 2:무료포인트, 3:환급
	//
	// userMapper.insertUserHistory(hisdto);
	// }
	// }
	// resultDto = login(dto);
	// resultDto.setOs_type(dto.getOs_type());
	//
	// }else {
	// resultDto.setResult(Utils.FAIL);
	// resultDto.setReason(Utils.REASON_DB);
	// resultDto.setError_code("3");
	// }
	// resultDto.setResult(Utils.SUCCESS);
	//
	//// } catch (GeneralSecurityException e) {
	//// // TODO Auto-generated catch block
	//// e.printStackTrace();
	//// } catch (IOException e) {
	//// // TODO Auto-generated catch block
	//// e.printStackTrace();
	//// }
	//
	// return resultDto;
	// }
	//
	// @Override
	// public LoginDTO joinV2(LoginDTO dto) {
	// LoginDTO resultDto = new LoginDTO();
	//
	// if(dto.getUser_nick_name().length() > 8) {
	// resultDto.setReason("닉네임 8자 이하입니다");
	// resultDto.setError_code("4");
	//
	// return resultDto;
	// }
	//
	// int nickChk = userMapper.nickNameChk(dto.getUser_nick_name());
	//
	// if(nickChk > 0) {
	// resultDto.setReason("이미 사용중인 닉네임 입니다");
	// resultDto.setError_code("1");
	//
	// return resultDto;
	// }
	//
	// boolean banChkFlag = Utils.filterBanText(dto.getUser_nick_name(),
	// versionMapper.getBanText("닉네임"));
	//
	// if(banChkFlag) {
	// resultDto.setReason("닉네임에 금지어가 포함 되어 있습니다");
	// resultDto.setError_code("2");
	//
	// return resultDto;
	// }
	//
	// int joinOk = userMapper.joinV2(dto);
	// /*
	// * TB_USER.user_point, default : 1000
	// * 20200709
	// * - TB_USER.user_point, default : 0
	// * - TB_USER.user_free_point, default : 0
	// */
	//
	// if(joinOk > -1) {
	// if(!"NONE".equals(dto.getUser_thumnail()) && dto.getUser_thumnail().length()
	// > 0) {
	// userMapper.userThumnailInsert(dto);
	// }
	//
	// /*
	// * 1. 영구정지 회원이지 조회
	// * 2. 영구정지 회원이면 포인트 미지급
	// * 3. 아니면 포인트(1000) 지급
	// */
	// int chkStationaryUser = userMapper.chkStationaryUser(dto);
	// if(chkStationaryUser < 2) {
	// int joinAddPoint = 1000;
	//
	// UserIDServiceTypeDTO userIDServiceTypeDTO = new UserIDServiceTypeDTO();
	// userIDServiceTypeDTO.setUser_id(dto.getUser_id());
	// userIDServiceTypeDTO.setUser_service_type(dto.getUser_service_type());
	//
	// int user_no = userMapper.selectUserNo(userIDServiceTypeDTO);
	//
	//
	// UserPointDTO pointdto = userMapper.getMyPoint(user_no);
	//
	// UserPointDTO pDto = new UserPointDTO();
	// pDto.setNo(user_no);
	// pDto.setUser_free_point(pointdto.getUser_free_point() + joinAddPoint);
	// //pDto.setUser_pay_point(pointdto.getUser_pay_point());
	//
	// int pointResult = userMapper.setPoint(pDto);
	//
	// if(pointResult == 1) {
	// UseHistoryDTO hisdto = new UseHistoryDTO();
	// hisdto.setUser_no(user_no);
	// hisdto.setUse_content("회원가입");
	// hisdto.setUse_cnt(joinAddPoint);
	// hisdto.setUse_type(2); // 0:유료포인트, 1:캐시, 2:무료포인트, 3:환급
	//
	// userMapper.insertUserHistory(hisdto);
	// }
	// }
	// resultDto = login(dto);
	// resultDto.setOs_type(dto.getOs_type());
	//
	// }else {
	// resultDto.setResult(Utils.FAIL);
	// resultDto.setReason(Utils.REASON_DB);
	// resultDto.setError_code("3");
	// }
	// resultDto.setResult(Utils.SUCCESS);
	//
	//// } catch (GeneralSecurityException e) {
	//// // TODO Auto-generated catch block
	//// e.printStackTrace();
	//// } catch (IOException e) {
	//// // TODO Auto-generated catch block
	//// e.printStackTrace();
	//// }
	//
	// return resultDto;
	// }
	//
	// @Override
	// public LoginDTO joinV4(LoginDTO dto) {
	// LoginDTO resultDto = new LoginDTO();
	//
	// // 닉네임이 8글자 이상임
	// if(dto.getUser_nick_name().length() > 8) {
	// resultDto.setReason("닉네임 8자 이하입니다");
	// resultDto.setError_code("4");
	//
	// return resultDto;
	// }
	//
	// int nickChk = userMapper.nickNameChk(dto.getUser_nick_name());
	//
	// // 중복된 닉네임
	// if(nickChk > 0) {
	// resultDto.setReason("이미 사용중인 닉네임 입니다");
	// resultDto.setError_code("1");
	//
	// return resultDto;
	// }
	//
	// boolean banChkFlag = Utils.filterBanText(dto.getUser_nick_name(),
	// versionMapper.getBanText("닉네임"));
	//
	// // 닉네임에 금지어 있음
	// if(banChkFlag) {
	// resultDto.setReason("닉네임에 금지어가 포함 되어 있습니다");
	// resultDto.setError_code("2");
	//
	// return resultDto;
	// }
	//
	// int joinOk = userMapper.joinV4(dto);
	// /*
	// * TB_USER.user_point, default : 1000
	// * 20200709
	// * - TB_USER.user_point, default : 0
	// * - TB_USER.user_free_point, default : 0
	// */
	//
	// // 회원가입에 성공한 경우
	// if(joinOk > -1) {
	// if(!"NONE".equals(dto.getUser_thumnail()) && dto.getUser_thumnail().length()
	// > 0) {
	// userMapper.userThumnailInsert(dto);
	// }
	//
	// /*
	// * 1. 영구정지 회원이지 조회
	// * 2. 영구정지 회원이면 포인트 미지급
	// * 3. 아니면 포인트(1000) 지급
	// */
	// int chkStationaryUser = userMapper.chkStationaryUser(dto);
	// if(chkStationaryUser < 2) {
	// int joinAddPoint = 1200;
	//
	// UserIDServiceTypeDTO userIDServiceTypeDTO = new UserIDServiceTypeDTO();
	// userIDServiceTypeDTO.setUser_id(dto.getUser_id());
	// userIDServiceTypeDTO.setUser_service_type(dto.getUser_service_type());
	//
	// int user_no = userMapper.selectUserNo(userIDServiceTypeDTO);
	//
	// UserPointDTO pointdto = userMapper.getMyPoint(user_no);
	//
	// UserPointDTO pDto = new UserPointDTO();
	// pDto.setNo(user_no);
	// pDto.setUser_free_point(pointdto.getUser_free_point() + joinAddPoint);
	// //pDto.setUser_pay_point(pointdto.getUser_pay_point());
	//
	// int pointResult = userMapper.setPoint(pDto);
	//
	// if(pointResult == 1) {
	// UseHistoryDTO hisdto = new UseHistoryDTO();
	// hisdto.setUser_no(user_no);
	// hisdto.setUse_content("회원가입");
	// hisdto.setUse_cnt(joinAddPoint);
	// hisdto.setUse_type(2); // 0:유료포인트, 1:캐시, 2:무료포인트, 3:환급
	//
	// userMapper.insertUserHistory(hisdto);
	// }
	// }
	// // 방금 가입한 계정으로 다시 로그인 함
	// resultDto = loginV2(dto);
	// resultDto.setOs_type(dto.getOs_type());
	//
	// // 회원가입에 실패한 경우
	// }else {
	// resultDto.setResult(Utils.FAIL);
	// resultDto.setReason(Utils.REASON_DB);
	// resultDto.setError_code("3");
	// }
	// resultDto.setResult(Utils.SUCCESS);
	//
	// return resultDto;
	// }
	//
	//
	// @Override
	// public LoginDTO joinV5(LoginDTO dto) {
	// LoginDTO resultDto = new LoginDTO();
	//
	// // 닉네임이 8글자 이상임
	// if(dto.getUser_nick_name().length() > 8) {
	// resultDto.setReason("닉네임 8자 이하입니다");
	// resultDto.setError_code("4");
	//
	// return resultDto;
	// }
	//
	// int nickChk = userMapper.nickNameChk(dto.getUser_nick_name());
	//
	// // 중복된 닉네임
	// if(nickChk > 0) {
	// resultDto.setReason("이미 사용중인 닉네임 입니다");
	// resultDto.setError_code("1");
	//
	// return resultDto;
	// }
	//
	// boolean banChkFlag = Utils.filterBanText(dto.getUser_nick_name(),
	// versionMapper.getBanText("닉네임"));
	//
	// // 닉네임에 금지어 있음
	// if(banChkFlag) {
	// resultDto.setReason("닉네임에 금지어가 포함 되어 있습니다");
	// resultDto.setError_code("2");
	//
	// return resultDto;
	// }
	//
	// int joinOk = userMapper.joinV5(dto);
	// /*
	// * TB_USER.user_point, default : 1000
	// * 20200709
	// * - TB_USER.user_point, default : 0
	// * - TB_USER.user_free_point, default : 0
	// */
	//
	// // 회원가입에 성공한 경우
	// if(joinOk > -1) {
	// if(!"NONE".equals(dto.getUser_thumnail()) && dto.getUser_thumnail().length()
	// > 0) {
	// userMapper.userThumnailInsert(dto);
	// }
	//
	// /*
	// * 1. 영구정지 회원이지 조회
	// * 2. 영구정지 회원이면 포인트 미지급
	// * 3. 아니면 포인트(1000) 지급
	// */
	// int chkStationaryUser = userMapper.chkStationaryUser(dto);
	// if(chkStationaryUser < 2) {
	// int joinAddPoint = 1200;
	//
	// UserIDServiceTypeDTO userIDServiceTypeDTO = new UserIDServiceTypeDTO();
	// userIDServiceTypeDTO.setUser_id(dto.getUser_id());
	// userIDServiceTypeDTO.setUser_service_type(dto.getUser_service_type());
	//
	// int user_no = userMapper.selectUserNo(userIDServiceTypeDTO);
	//
	// UserPointDTO pointdto = userMapper.getMyPoint(user_no);
	//
	// UserPointDTO pDto = new UserPointDTO();
	// pDto.setNo(user_no);
	// pDto.setUser_free_point(pointdto.getUser_free_point() + joinAddPoint);
	// //pDto.setUser_pay_point(pointdto.getUser_pay_point());
	//
	// int pointResult = userMapper.setPoint(pDto);
	//
	// if(pointResult == 1) {
	// UseHistoryDTO hisdto = new UseHistoryDTO();
	// hisdto.setUser_no(user_no);
	// hisdto.setUse_content("회원가입");
	// hisdto.setUse_cnt(joinAddPoint);
	// hisdto.setUse_type(2); // 0:유료포인트, 1:캐시, 2:무료포인트, 3:환급
	//
	// userMapper.insertUserHistory(hisdto);
	// }
	// }
	// // 방금 가입한 계정으로 다시 로그인 함
	// resultDto = loginV2(dto);
	// resultDto.setOs_type(dto.getOs_type());
	//
	// // 회원가입에 실패한 경우
	// }else {
	// resultDto.setResult(Utils.FAIL);
	// resultDto.setReason(Utils.REASON_DB);
	// resultDto.setError_code("3");
	// }
	// resultDto.setResult(Utils.SUCCESS);
	//
	// return resultDto;
	// }
	//
	//
	// @Override
	// public LoginDTO joinV6(LoginDTO dto) {
	// LoginDTO resultDto = new LoginDTO();
	//
	// // 닉네임이 8글자 이상임
	// if(dto.getUser_nick_name().length() > 8) {
	// resultDto.setReason("닉네임 8자 이하입니다");
	// resultDto.setError_code("4");
	//
	// return resultDto;
	// }
	//
	// int nickChk = userMapper.nickNameChk(dto.getUser_nick_name());
	//
	// // 중복된 닉네임
	// if(nickChk > 0) {
	// resultDto.setReason("이미 사용중인 닉네임 입니다");
	// resultDto.setError_code("1");
	//
	// return resultDto;
	// }
	//
	// boolean banChkFlag = Utils.filterBanText(dto.getUser_nick_name(),
	// versionMapper.getBanText("닉네임"));
	//
	// // 닉네임에 금지어 있음
	// if(banChkFlag) {
	// resultDto.setReason("닉네임에 금지어가 포함 되어 있습니다");
	// resultDto.setError_code("2");
	//
	// return resultDto;
	// }
	//
	// // 여성 회원의 경우 추천인 코드 확인
	// // 있는 계정이면 user_part_time =1 - 알바
	// // 없는 계정이면 call_type = 0 - 음성통화
	// int cnt = 0;
	// if (dto.getUser_gender().equals("여성")){
	// // 추천인을 입력하지 않은 경우
	// if (dto.getAgent_id().equals("NONE")){
	//
	// // call_type = 0 - 음성통화
	// dto.setCall_type(0);
	// dto.setUser_part_time(0);
	// // 추천인을 입력한 경우
	// }else{
	// cnt = userMapper.checkAdmin(dto.getAgent_id());
	//
	// // 추천인 코드(=어드민 아이디)가 실제 있는 데이터인 경우
	// if (cnt>0){
	// // 알바로 설정
	// dto.setCall_type(1);
	// dto.setUser_part_time(1);
	// }else{
	// // 실제하지 않는 경우
	// dto.setCall_type(0);
	// dto.setUser_part_time(0);
	// }
	// }
	// }
	//
	// int joinOk = userMapper.joinV6(dto);
	// /*
	// * TB_USER.user_point, default : 1000
	// * 20200709
	// * - TB_USER.user_point, default : 0
	// * - TB_USER.user_free_point, default : 0
	// */
	//
	// // 회원가입에 성공한 경우
	// if(joinOk > -1) {
	// if(!"NONE".equals(dto.getUser_thumnail()) && dto.getUser_thumnail().length()
	// > 0) {
	// userMapper.userThumnailInsert(dto);
	// }
	//
	// /*
	// * 1. 영구정지 회원이지 조회
	// * 2. 영구정지 회원이면 포인트 미지급
	// * 3. 아니면 포인트(1000) 지급
	// */
	// int chkStationaryUser = userMapper.chkStationaryUser(dto);
	// if(chkStationaryUser < 2) {
	// int joinAddPoint = 1200;
	//
	// UserIDServiceTypeDTO userIDServiceTypeDTO = new UserIDServiceTypeDTO();
	// userIDServiceTypeDTO.setUser_id(dto.getUser_id());
	// userIDServiceTypeDTO.setUser_service_type(dto.getUser_service_type());
	//
	// int user_no = userMapper.selectUserNo(userIDServiceTypeDTO);
	//
	// UserPointDTO pointdto = userMapper.getMyPoint(user_no);
	//
	// UserPointDTO pDto = new UserPointDTO();
	// pDto.setNo(user_no);
	// pDto.setUser_free_point(pointdto.getUser_free_point() + joinAddPoint);
	// //pDto.setUser_pay_point(pointdto.getUser_pay_point());
	//
	// int pointResult = userMapper.setPoint(pDto);
	//
	// if(pointResult == 1) {
	// UseHistoryDTO hisdto = new UseHistoryDTO();
	// hisdto.setUser_no(user_no);
	// hisdto.setUse_content("회원가입");
	// hisdto.setUse_cnt(joinAddPoint);
	// hisdto.setUse_type(2); // 0:유료포인트, 1:캐시, 2:무료포인트, 3:환급
	//
	// userMapper.insertUserHistory(hisdto);
	// }
	// }
	// // 방금 가입한 계정으로 다시 로그인 함
	// resultDto = loginV4(dto);
	// resultDto.setOs_type(dto.getOs_type());
	//
	// // 회원가입에 실패한 경우
	// }else {
	// resultDto.setResult(Utils.FAIL);
	// resultDto.setReason(Utils.REASON_DB);
	// resultDto.setError_code("3");
	// }
	// resultDto.setResult(Utils.SUCCESS);
	//
	// return resultDto;
	// }
	//
	//
	@Override
	public LoginDTO joinV7(LoginDTO dto) {
		LoginDTO resultDto = new LoginDTO();

		// 닉네임이 8글자 이상임
		if (dto.getUser_nick_name().length() > 16) {
			resultDto.setReason("닉네임 16자 이하입니다");
			resultDto.setError_code("4");

			return resultDto;
		}

		int nickChk = userMapper.nickNameChk(dto.getUser_nick_name());

		// 중복된 닉네임
		if (nickChk > 0) {
			resultDto.setReason("이미 사용중인 닉네임 입니다");
			resultDto.setError_code("1");

			return resultDto;
		}

		boolean banChkFlag = Utils.filterBanText(dto.getUser_nick_name(), versionMapper.getBanText("닉네임"));

		// 닉네임에 금지어 있음
		if (banChkFlag) {
			resultDto.setReason("닉네임에 금지어가 포함 되어 있습니다");
			resultDto.setError_code("2");

			return resultDto;
		}

		// 여성 회원의 경우 추천인 코드 확인
		// 있는 계정이면 user_part_time =1 - 알바
		// 없는 계정이면 call_type = 0 - 음성통화
		int cnt = 0;

		if (dto.getUser_gender().equals("여성")) {
			// 추천인을 입력하지 않은 경우
			if (dto.getAgent_id().equals("NONE")) {

				// call_type = 0 - 음성통화
				dto.setCall_type(0);
				dto.setUser_part_time(0);
				// 추천인을 입력한 경우
			} else {
				cnt = userMapper.checkAdmin(dto.getAgent_id());

				// 추천인 코드(=어드민 아이디)가 실제 있는 데이터인 경우
				if (cnt > 0) {
					// 알바로 설정
					dto.setCall_type(1);
					dto.setUser_part_time(1);
				} else {
					// 실제하지 않는 경우
					dto.setCall_type(0);
					dto.setUser_part_time(0);
				}
			}
		}

		int joinOk = userMapper.joinV6(dto);
		/*
		 * TB_USER.user_point, default : 1000
		 * 20200709
		 * - TB_USER.user_point, default : 0
		 * - TB_USER.user_free_point, default : 0
		 */

		// 회원가입에 성공한 경우
		if (joinOk > -1) {
			if (!"NONE".equals(dto.getUser_thumnail()) && dto.getUser_thumnail().length() > 0) {
				userMapper.userThumnailInsert(dto);
			}

			/*
			 * 1. 영구정지 회원이지 조회
			 * 2. 영구정지 회원이면 포인트 미지급
			 * 3. 아니면 포인트(1200) 지급
			 */
			int chkStationaryUser = userMapper.chkStationaryUser(dto);
			if (chkStationaryUser < 2) {
				int joinAddPoint = 2000;

				UserIDServiceTypeDTO userIDServiceTypeDTO = new UserIDServiceTypeDTO();
				userIDServiceTypeDTO.setUser_id(dto.getUser_id());
				userIDServiceTypeDTO.setUser_service_type(dto.getUser_service_type());

				int user_no = userMapper.selectUserNo(userIDServiceTypeDTO);

				UserPointDTO pointdto = userMapper.getMyPoint(user_no);

				UserPointDTO pDto = new UserPointDTO();
				pDto.setNo(user_no);
				pDto.setUser_free_point(pointdto.getUser_free_point() + joinAddPoint);
				// pDto.setUser_pay_point(pointdto.getUser_pay_point());

				int pointResult = userMapper.setPoint(pDto);

				if (pointResult == 1) {
					UseHistoryDTO hisdto = new UseHistoryDTO();
					hisdto.setUser_no(user_no);
					hisdto.setUse_content("회원가입");
					hisdto.setUse_cnt(joinAddPoint);
					hisdto.setUse_type(2); // 0:유료포인트, 1:캐시, 2:무료포인트, 3:환급

					userMapper.insertUserHistory(hisdto);
				}
			}

			// 방금 가입한 계정으로 다시 로그인 함
			resultDto = loginV4(dto);
			resultDto.setOs_type(dto.getOs_type());

			// 회원가입에 실패한 경우
		} else {
			resultDto.setResult(Utils.FAIL);
			resultDto.setReason(Utils.REASON_DB);
			resultDto.setError_code("3");
		}
		resultDto.setResult(Utils.SUCCESS);

		return resultDto;
	}

	@Override
	public LoginDTO joinV8(LoginDTO dto) {
		LoginDTO resultDto = new LoginDTO();

		// 닉네임이 8글자 이상임
		if (dto.getUser_nick_name().length() > 8) {
			resultDto.setReason("닉네임 8자 이하입니다");
			resultDto.setError_code("4");

			return resultDto;
		}

		int nickChk = userMapper.nickNameChk(dto.getUser_nick_name());

		// 중복된 닉네임
		if (nickChk > 0) {
			resultDto.setReason("이미 사용중인 닉네임 입니다");
			resultDto.setError_code("1");

			return resultDto;
		}

		boolean banChkFlag = Utils.filterBanText(dto.getUser_nick_name(), versionMapper.getBanText("닉네임"));

		// 닉네임에 금지어 있음
		if (banChkFlag) {
			resultDto.setReason("닉네임에 금지어가 포함 되어 있습니다");
			resultDto.setError_code("2");

			return resultDto;
		}

		// 여성 회원의 경우 추천인 코드 확인
		// 있는 계정이면 user_part_time =1 - 알바
		// 없는 계정이면 call_type = 0 - 음성통화
		int cnt = 0;

		if (dto.getUser_gender().equals("여성")) {
			// 추천인을 입력하지 않은 경우
			if (dto.getAgent_id().equals("NONE")) {

				// call_type = 0 - 음성통화
				dto.setCall_type(0);
				dto.setUser_part_time(0);
				// 추천인을 입력한 경우
			} else {
				cnt = userMapper.checkAdmin(dto.getAgent_id());

				// 추천인 코드(=어드민 아이디)가 실제 있는 데이터인 경우
				if (cnt > 0) {
					// 알바로 설정
					dto.setCall_type(1);
					dto.setUser_part_time(1);
				} else {
					// 실제하지 않는 경우
					dto.setCall_type(0);
					dto.setUser_part_time(0);
				}
			}
		}

		// 인증 번호가 있다면 인증 되었다고 저장하기
		int certCnt = userMapper.checkCertNo(dto.getCert_no());

		if (certCnt > 0 || dto.getUser_adid().length() > 10) // 인증정보가 있거나 파트너 통한ㅇ 가
			dto.setIs_certified(1);
		else
			dto.setIs_certified(0);
		int joinOk = userMapper.joinV7(dto);
		/*
		 * TB_USER.user_point, default : 1000
		 * 20200709
		 * - TB_USER.user_point, default : 0
		 * - TB_USER.user_free_point, default : 0
		 */

		// 회원가입에 성공한 경우
		if (joinOk > -1) {
			if (!"NONE".equals(dto.getUser_thumnail()) && dto.getUser_thumnail().length() > 0) {
				userMapper.userThumnailInsert(dto);
			}

			/*
			 * 1. 영구정지 회원이지 조회
			 * 2. 영구정지 회원이면 포인트 미지급
			 * 3. 아니면 포인트(1200) 지급
			 */
			int chkStationaryUser = userMapper.chkStationaryUser(dto);
			if (chkStationaryUser < 2) {
				int joinAddPoint = 2000;

				UserIDServiceTypeDTO userIDServiceTypeDTO = new UserIDServiceTypeDTO();
				userIDServiceTypeDTO.setUser_id(dto.getUser_id());
				userIDServiceTypeDTO.setUser_service_type(dto.getUser_service_type());

				int user_no = userMapper.selectUserNo(userIDServiceTypeDTO);

				dto.setNo(user_no);

				UserPointDTO pointdto = userMapper.getMyPoint(user_no);

				UserPointDTO pDto = new UserPointDTO();
				pDto.setNo(user_no);
				pDto.setUser_free_point(pointdto.getUser_free_point() + joinAddPoint);
				// pDto.setUser_pay_point(pointdto.getUser_pay_point());

				int pointResult = userMapper.setPoint(pDto);

				if (pointResult == 1) {
					UseHistoryDTO hisdto = new UseHistoryDTO();
					hisdto.setUser_no(user_no);
					hisdto.setUse_content("회원가입");
					hisdto.setUse_cnt(joinAddPoint);
					hisdto.setUse_type(2); // 0:유료포인트, 1:캐시, 2:무료포인트, 3:환급

					userMapper.insertUserHistory(hisdto);
				}
			}

			if (certCnt > 0) {// 인증 테이블에 사용자 번호 넣어줌
				userMapper.updateCertUser(dto);
			}
			if (dto.getUser_adid() != null && dto.getUser_adid().length() > 10) {// 파트너 통한 가입시 adid정보를 인증정보 대신 입력
				userMapper.insertCertPartner(dto);
				DefaultDTO rsVerifyPartnerDto = new DefaultDTO();
				rsVerifyPartnerDto = verifyPartnerInstall(dto.getUser_adid(), "couponjo"); // 쿠폰조 가입 확인
				if (rsVerifyPartnerDto.getResult().equals(Utils.SUCCESS)) {
					// 쿠폰조 가입 확인시 포인트 추가 지급
					CouponjoPointDTO cDto = new CouponjoPointDTO();
					cDto.setUser_no(dto.getNo());
					cDto.setUser_adid(dto.getUser_adid());
					userMapper.insertCouponjoPoint(cDto);
				}
			}
			// 방금 가입한 계정으로 다시 로그인 함
			resultDto = loginV4(dto);

			resultDto.setOs_type(dto.getOs_type());

			// 회원가입에 실패한 경우
		} else {
			resultDto.setResult(Utils.FAIL);
			resultDto.setReason(Utils.REASON_DB);
			resultDto.setError_code("3");
		}
		resultDto.setResult(Utils.SUCCESS);

		return resultDto;
	}

	@Override
	public LoginDTO joinV9(LoginDTO dto) {
		LoginDTO resultDto = new LoginDTO();

		// 닉네임이 8글자 이상임
		if (dto.getUser_nick_name().length() > 8) {
			resultDto.setReason("닉네임 8자 이하입니다");
			resultDto.setError_code("4");

			return resultDto;
		}

		int nickChk = userMapper.nickNameChk(dto.getUser_nick_name());

		// 중복된 닉네임
		if (nickChk > 0) {
			resultDto.setReason("이미 사용중인 닉네임 입니다");
			resultDto.setError_code("1");

			return resultDto;
		}

		boolean banChkFlag = Utils.filterBanText(dto.getUser_nick_name(), versionMapper.getBanText("닉네임"));

		// 닉네임에 금지어 있음
		if (banChkFlag) {
			resultDto.setReason("닉네임에 금지어가 포함 되어 있습니다");
			resultDto.setError_code("2");

			return resultDto;
		}

		// 여성 회원의 경우 추천인 코드 확인
		// 있는 계정이면 user_part_time =1 - 알바
		// 없는 계정이면 call_type = 0 - 음성통화
		int cnt = 0;

		if (dto.getUser_gender().equals("여성")) {
			// 추천인을 입력하지 않은 경우
			if (dto.getAgent_id().equals("NONE")) {

				// call_type = 0 - 음성통화
				dto.setCall_type(0);
				dto.setUser_part_time(0);
				// 추천인을 입력한 경우
			} else {
				cnt = userMapper.checkAdmin(dto.getAgent_id());

				// 추천인 코드(=어드민 아이디)가 실제 있는 데이터인 경우
				if (cnt > 0) {
					// 알바로 설정
					dto.setCall_type(1);
					dto.setUser_part_time(1);
				} else {
					// 실제하지 않는 경우
					dto.setCall_type(0);
					dto.setUser_part_time(0);
				}
			}
		}

		// 인증 번호가 있다면 인증 되었다고 저장하기
		int certCnt = userMapper.checkCertNo(dto.getCert_no());

		if (certCnt > 0 || dto.getUser_adid().length() > 10) // 인증정보가 있거나 파트너 통한ㅇ 가
			dto.setIs_certified(1);
		else
			dto.setIs_certified(0);
		int joinOk = userMapper.joinV7(dto);
		/*
		 * TB_USER.user_point, default : 1000
		 * 20200709
		 * - TB_USER.user_point, default : 0
		 * - TB_USER.user_free_point, default : 0
		 */

		// 회원가입에 성공한 경우
		if (joinOk > -1) {
			if (!"NONE".equals(dto.getUser_thumnail()) && dto.getUser_thumnail().length() > 0) {
				userMapper.userThumnailInsert(dto);
			}

			/*
			 * 1. 영구정지 회원이지 조회
			 * 2. 영구정지 회원이면 포인트 미지급
			 * 3. 아니면 포인트(2000) 지급
			 */
			int chkStationaryUser = userMapper.chkStationaryUser(dto);
			if (chkStationaryUser < 2) {
				int joinAddPoint = 2000;

				UserIDServiceTypeDTO userIDServiceTypeDTO = new UserIDServiceTypeDTO();
				userIDServiceTypeDTO.setUser_id(dto.getUser_id());
				userIDServiceTypeDTO.setUser_service_type(dto.getUser_service_type());

				int user_no = userMapper.selectUserNo(userIDServiceTypeDTO);

				dto.setNo(user_no);

				UserPointDTO pointdto = userMapper.getMyPoint(user_no);

				UserPointDTO pDto = new UserPointDTO();
				pDto.setNo(user_no);
				pDto.setUser_free_point(pointdto.getUser_free_point() + joinAddPoint);
				// pDto.setUser_pay_point(pointdto.getUser_pay_point());

				int pointResult = userMapper.setPoint(pDto);

				if (pointResult == 1) {
					UseHistoryDTO hisdto = new UseHistoryDTO();
					hisdto.setUser_no(user_no);
					hisdto.setUse_content("회원가입");
					hisdto.setUse_cnt(joinAddPoint);
					hisdto.setUse_type(2); // 0:유료포인트, 1:캐시, 2:무료포인트, 3:환급

					userMapper.insertUserHistory(hisdto);
				}
			}

			if (certCnt > 0) {// 인증 테이블에 사용자 번호 넣어줌
				userMapper.updateCertUser(dto);
			}
			if (dto.getUser_adid() != null && dto.getUser_adid().length() > 10) {// 파트너 통한 가입시 adid정보를 인증정보 대신 입력
				userMapper.insertCertPartner(dto);
				DefaultDTO rsVerifyPartnerDto = new DefaultDTO();
				rsVerifyPartnerDto = verifyPartnerInstall(dto.getUser_adid(), "couponjo"); // 쿠폰조 가입 확인
				if (rsVerifyPartnerDto.getResult().equals(Utils.SUCCESS)) {
					// 쿠폰조 가입 확인시 포인트 추가 지급
					CouponjoPointDTO cDto = new CouponjoPointDTO();
					cDto.setUser_no(dto.getNo());
					cDto.setUser_adid(dto.getUser_adid());
					userMapper.insertCouponjoPoint(cDto);
				}
			}
			// 방금 가입한 계정으로 다시 로그인 함
			resultDto = loginV4(dto);

			resultDto.setOs_type(dto.getOs_type());

			// 회원가입에 실패한 경우
		} else {
			resultDto.setResult(Utils.FAIL);
			resultDto.setReason(Utils.REASON_DB);
			resultDto.setError_code("3");
		}
		resultDto.setResult(Utils.SUCCESS);

		return resultDto;
	}

	// /**
	// * 회원가입 시 대화주제 선택 리스트
	// */
	// @Override
	// public ConversationListDTO conversationList(){
	// ConversationListDTO resultDto = new ConversationListDTO();
	// resultDto.setCoList(conversationMapper.conversationList());
	// resultDto.setResult(Utils.SUCCESS);
	// return resultDto;
	// }

	/**
	 * 회원가입 시 대화주제 선택 리스트
	 */
	@Override
	public ConversationListDTO conversationListV2(String language_code) {

		ConversationListDTO resultDto = new ConversationListDTO();
		resultDto.setCoList(conversationMapper.conversationListV2(language_code));
		resultDto.setResult(Utils.SUCCESS);
		return resultDto;
	}

	@Override
	public ConversationListDTO conversationListV3(String language_code) {

		ConversationListDTO resultDto = new ConversationListDTO();
		resultDto.setCoList(conversationMapper.conversationListV3(language_code));
		resultDto.setResult(Utils.SUCCESS);
		return resultDto;
	}

	// 좋아요 추가
	public LikeResultDTO insertLike(LikeDTO likedto) {
		LikeResultDTO resultDto = new LikeResultDTO();
		resultDto.setLikeResult(likedto);

		int likeCheck = likeCheck(likedto);
		int result = -1;

		if (likeCheck == 1) {
			result = userMapper.insertLike(likedto);
			resultDto.setSuccess_code("1"); // 성공

			if (result == 1) {
				resultDto.setResult(Utils.SUCCESS);
			}
		} else {
			result = -1;
			resultDto.setSuccess_code("2"); // 오늘 추천 했음
			resultDto.setResult(Utils.SUCCESS);
		}

		return resultDto;
	}

	// 좋아요 추가
	public LikeResultDTO insertLikeV2(LikeDTO likedto) {
		LikeResultDTO resultDto = new LikeResultDTO();
		resultDto.setLikeResult(likedto);

		UserInfoDTO partnerInfo = userMapper.getUserInfo(likedto.getTo_user_no());
		UserInfoDTO userInfo = userMapper.getUserInfo(likedto.getUser_no());

		int likeCheck = likeCheck(likedto);
		int result = -1;

		if (likeCheck == 1) {
			result = userMapper.insertLike(likedto);
			resultDto.setSuccess_code("1"); // 성공

			if (result == 1) {

				JSONObject userInfoJson = new JSONObject();
				userInfoJson.put("from_user_no", userInfo.getNo());
				userInfoJson.put("user_nick_name", userInfo.getUser_nick_name());
				userInfoJson.put("user_imgs", userInfo.getUser_imgs());
				String userInfoStr = userInfoJson.toString();

				// 상대방이 나를 차단했는지 정보 가져오기
				int blackCnt = userService.chkUserBlackList(likedto.getTo_user_no(), likedto.getUser_no());

				if (blackCnt > 0) { // 블랙 당한경우 채팅 상대 방이 안보임

				} else {

					new Thread(() -> {
						// 좋아요한 상대방에게 푸쉬 발송
						pushService.sendDataPush(partnerInfo.getUser_push_key(), PushType.LIKEADD.getTypeCd(),
								userInfoStr, "", "");
					}).start();

				}

				resultDto.setResult(Utils.SUCCESS);
			}
		} else {
			result = -1;
			resultDto.setSuccess_code("2"); // 오늘 추천 했음
			resultDto.setResult(Utils.SUCCESS);
		}

		return resultDto;
	}

	/**
	 * 좋아요 날짜 계산
	 */
	@Override
	public int likeCheck(LikeDTO dto) {
		LikeCheckDTO lcdto = new LikeCheckDTO();
		lcdto = userMapper.likeCheck(dto);

		int likeCheck = -1;
		SimpleDateFormat format = new SimpleDateFormat("dd");
		Date date = new Date();
		String time = format.format(date);
		String dd = "00";

		if (lcdto != null) {
			dd = lcdto.getSdate().substring(8, 10);
		}

		int intdd = Integer.parseInt(dd);
		int inttime = Integer.parseInt(time);

		if (intdd < inttime) { // 성공
			likeCheck = 1;

		} else { // 실패
			likeCheck = 2;
		}
		return likeCheck;
	}

	// 차단목록
	@Override
	public MainBlackListDTO myBlackList(int user_no) {
		MainBlackListDTO dto = new MainBlackListDTO();
		dto.setMainBlackList(userMapper.myBlackList(user_no));
		dto.setResult(Utils.SUCCESS);
		return dto;
	}

	// 차단하기
	@Override
	public BlackDTO insertBlackList(BlackListDTO dto) {

		BlackDTO resultDto = new BlackDTO();
		resultDto.setBlackList(dto);

		int cnt = userMapper.cntBlackList(dto);
		int result = -1;

		if (cnt == 0) {
			result = userMapper.insertBlackList(dto);
			resultDto.setSuccess_code("1"); // 추가
		} else {
			resultDto.setSuccess_code("2"); // 실패 이미있음
			result = userMapper.deleteBlackList(dto);
		}

		if (result == 1) {
			resultDto.setResult(Utils.SUCCESS);
		}

		return resultDto;
	}

	@Override
	public UserPointDTO getMyPoint(int user_no) {
		UserPointDTO resultDto = userMapper.getMyPoint(user_no);
		resultDto.setResult(Utils.SUCCESS);

		return resultDto;
	}

	@Override
	public SetAlarmDTO setMyAlaram(int user_no, int alarm_status) {
		SetAlarmDTO dto = new SetAlarmDTO();
		dto.setUser_alarm_push(alarm_status);
		dto.setUser_no(user_no);

		int result = userMapper.setMyAleram(dto);
		if (result == 1) {
			dto.setResult(Utils.SUCCESS);
		} else {
			dto.setReason("변경 실패");
		}

		return dto;
	}

	@Override
	public SetAlarmDTO setUserSetting(int user_no, int set_type, int set_status) {
		SetAlarmDTO dto = new SetAlarmDTO();
		dto.setUser_no(user_no);
		dto.setSet_type(set_type);
		dto.setSet_status(set_status);

		int result = userMapper.setUserSetting(dto);

		if (result == 1) {
			dto.setResult(Utils.SUCCESS);

			UserInfoDTO userInfoDto = userMapper.getUserInfo(user_no);

			dto.setUser_alarm_push(userInfoDto.getUser_alarm_push());
			dto.setUser_alarm_popup(userInfoDto.getUser_alarm_popup());
			dto.setUser_alarm_bookmark(userInfoDto.getUser_alarm_bookmark());
			dto.setUser_alarm_sound(userInfoDto.getUser_alarm_sound());
			dto.setUser_alarm_bive(userInfoDto.getUser_alarm_bive());
		}
		return dto;
	}

	@Override
	public UserPointDTO attendanceChk(int user_no) {
		String today = Utils.getNowTime().substring(0, 10);
		String endDate = today + " 23:59:59";

		AttendanceDTO dto = new AttendanceDTO();
		dto.setUser_no(user_no);
		dto.setStart_date(today);
		dto.setEnd_date(endDate);

		int aChkCnt = userMapper.getTodayAttendance(dto);

		UserPointDTO resultDto = new UserPointDTO();

		if (aChkCnt == 0) { // 오늘 출책 없으면 출책 시작
			int result = userMapper.setTodayAttendance(user_no);
			if (result == 1) {
				UserPointDTO pointdto = userMapper.getMyPoint(user_no);
				int attendanceChkPoint = 50;

				UserPointDTO pDto = new UserPointDTO();
				pDto.setNo(user_no);
				pDto.setUser_free_point(pointdto.getUser_free_point() + attendanceChkPoint);
				pDto.setUser_pay_point(pointdto.getUser_pay_point());

				int pointResult = userMapper.setPoint(pDto);

				if (pointResult == 1) { // 포인트 등록 성공 사용 내역 추가
					UseHistoryDTO uDto = new UseHistoryDTO();
					uDto.setUse_type(2); // 0 : 유료포인트 / 1 : 캐시 / 2 : 무료포인트
					uDto.setUse_content("출석체크");
					uDto.setUse_cnt(attendanceChkPoint);
					uDto.setUser_no(user_no);
					uDto.setUse_etc("");
					liveCallMapper.insertUseHistory(uDto);

					resultDto.setUser_point(pointdto.getUser_point() + attendanceChkPoint);
					resultDto.setUse_point(attendanceChkPoint);
					resultDto.setResult(Utils.SUCCESS);
				}

				UserInfoDTO toUserDto = userMapper.getUserInfo(user_no);
				if (toUserDto.getUser_gender().equals("여성")) {

					AttendanceEventDTO eventDto = new AttendanceEventDTO();
					eventDto.setUser_no(user_no);

					userMapper.attendanceEvent(eventDto);
					if (eventDto.getResult_code() == 1) {
						String msg = eventDto.getResult_msg();
						// send chat
						ChatDTO chatDto = new ChatDTO();
						chatDto.setChat_text(msg);
						chatDto.setTo_user_no(user_no);
						chatDto.setFrom_user_no(1);
						chatDto.setTo_visible(0);
						chatMapper.sendChat(chatDto);

						JSONObject chatData = new JSONObject();
						chatData.put("from_user_no", 1);
						chatData.put("chat_text", msg);
						chatData.put("from_user_nick", "관리자");
						chatData.put("from_user_thumnail", "NONE");
						pushService.sendDataPush(toUserDto.getUser_push_key(), PushType.CHATGET.getTypeCd(),
								chatData.toString(), "관리자", msg); // 상대 방에게 푸쉬 발송
					}
				}

			}
		} else { // 오늘 출책 있음
			resultDto.setError_code("1");
			resultDto.setReason("이미 출석 체크 했음");
		}

		return resultDto;
	}

	// 프로필 업데이트
	@Override
	public UserModifyDTO updateProfile(UserProfileUpdateDTO dto) {
		UserModifyDTO resultDto = new UserModifyDTO();

		if (dto.getUser_nick_name().length() > 8) {
			resultDto.setReason("닉네임 8자 이하입니다");
			resultDto.setError_code("4");

			return resultDto;
		}

		int result = 0;
		int result2 = 0;
		resultDto.setUserInfo(dto);

		// 프로필 승인 대기중인 이미지가 있는지 확인
		int cnt = userMapper.selectUserProfileImg(dto.getUser_no());

		// 소유 포인트 조회
		UserPointDTO pointdto = getMyPoint(dto.getUser_no());
		dto.setUser_point(pointdto.getUser_point());
		dto.setUser_free_point(pointdto.getUser_free_point());// 무료포인트
		dto.setUser_pay_point(pointdto.getUser_pay_point()); // 유료포인트

		// 닉네임 중복검사
		String nick = userMapper.selectCheckNickname(dto.getUser_no());

		// 프로필 사진 변경 시
		if (!"".equals(dto.getUser_thumnail()) && !"NONE".equals(dto.getUser_thumnail())) {
			if (cnt > 0) {
				userMapper.deleteProfileImg(dto.getUser_no());
			}

			LoginDTO ldto = new LoginDTO();
			ldto.setUser_thumnail(dto.getUser_thumnail());
			ldto.setNo(dto.getUser_no());
			userMapper.userThumnailInsert(ldto);
		}

		// 닉네임을 변경하지 않는다면
		if (nick.equals(dto.getUser_nick_name())) {
			// 받아온 값을 전부 업데이트 해준다.
			result = userMapper.updateProfile(dto);

			if (result == 1) {
				resultDto.setResult(Utils.SUCCESS);
			}

			// 닉네임을 변경하면
		} else {

			int deductionPoint = 50;// 차감될 포인트

			// 포인트 체크
			if (pointdto.getUser_point() < deductionPoint) {
				resultDto.setReason("포인트가 부족합니다.");
				resultDto.setError_code("3");

				return resultDto;

			} else {

				// 닉네임 중복 체크
				int nickChk = userMapper.nickNameChk2(dto);

				if (nickChk > 0) {
					resultDto.setReason("이미 사용중인 닉네임 입니다");
					resultDto.setError_code("1");

					return resultDto;
				}

				// 금지어 체크
				boolean banChkFlag = Utils.filterBanText(dto.getUser_nick_name(), versionMapper.getBanText("닉네임"));

				if (banChkFlag) {
					resultDto.setReason("닉네임에 금지어가 포함 되어 있습니다");
					resultDto.setError_code("2");

					return resultDto;
				}

				// dto.setUser_result_point(-50);//차감될 포인트
				int deductionFreePoint = -(pointdto.getUser_free_point() >= deductionPoint ? deductionPoint
						: pointdto.getUser_free_point());
				int deductionPayPoint = -(pointdto.getUser_free_point() >= deductionPoint ? 0
						: deductionPoint + deductionFreePoint);

				dto.setUser_deduction_free_point(deductionFreePoint);
				dto.setUser_deduction_point(deductionPayPoint);

				// 닉네임변경 -50P
				result = userMapper.updateProfilePoint(dto);

				// 닉네임 변경으로 인한 포인트 차감 히스토리에 저장
				UseHistoryDTO hdto = new UseHistoryDTO();

				hdto.setUser_no(dto.getUser_no());
				hdto.setUse_content("닉네임변경 차감");

				if (deductionFreePoint != 0) {
					hdto.setUse_type(2); // 무료포인트
					hdto.setUse_cnt(deductionFreePoint);
					result2 = userMapper.insertUserHistory(hdto);
				}
				if (deductionPayPoint != 0) {
					hdto.setUse_type(0); // 유료포인트
					hdto.setUse_cnt(deductionPayPoint);
					result2 = userMapper.insertUserHistory(hdto);
				}

				if (result == 1 && result2 == 1) {
					// 차감된 포인트 보여주기
					dto.setUser_point(pointdto.getUser_point() - deductionPoint);
					dto.setUser_free_point(pointdto.getUser_free_point() + deductionFreePoint);// 무료포인트
					dto.setUser_pay_point(pointdto.getUser_pay_point() + deductionPayPoint); // 유료포인트

					resultDto.setUserInfo(dto);
					resultDto.setResult(Utils.SUCCESS);
				}
			}
		}
		return resultDto;
	}

	@Override
	public UserModifyDTO updateProfileV2(UserProfileUpdateDTO dto) {
		UserModifyDTO resultDto = new UserModifyDTO();

		// 닉네임 체크
		if (dto.getUser_nick_name().length() > 8) {
			resultDto.setReason("닉네임 8자 이하입니다");
			resultDto.setError_code("4");
			return resultDto;
		}

		// 직접 입력한 대화 주제 길이 체크
		if (dto.getConversation_no() == 0) {
			// 직접 입력한 대화 주제가 30자가 넘는 경우
			if (dto.getConversation_txt().length() > 30) {
				resultDto.setError_code("5");
				return resultDto;
			}
		}

		int result = 0;
		int result2 = 0;
		resultDto.setUserInfo(dto);

		// 소유 포인트 조회
		UserPointDTO pointdto = getMyPoint(dto.getUser_no());
		dto.setUser_point(pointdto.getUser_point());
		dto.setUser_free_point(pointdto.getUser_free_point());// 무료포인트
		dto.setUser_pay_point(pointdto.getUser_pay_point()); // 유료포인트

		// 닉네임 중복검사
		String nick = userMapper.selectCheckNickname(dto.getUser_no());

		// 닉네임을 변경하지 않는다면
		if (nick.equals(dto.getUser_nick_name())) {
			if (dto.getConversation_no() == 0) {
				if (dto.getConversation_txt() == null) {
					dto.setConversation_txt("I wanna see you");
				}
				result = userMapper.updateProfileV2CovTxt(dto);
			} else {
				// 받아온 값을 전부 업데이트 해준다.
				result = userMapper.updateProfileV2(dto);
			}

			if (result == 1) {
				resultDto.setResult(Utils.SUCCESS);
			}

			// 닉네임을 변경하면
		} else {

			int deductionPoint = 50;// 차감될 포인트

			// 포인트 체크
			if (pointdto.getUser_point() < deductionPoint) {
				resultDto.setReason("포인트가 부족합니다.");
				resultDto.setError_code("3");

				return resultDto;

			} else {

				// 닉네임 중복 체크
				int nickChk = userMapper.nickNameChk2(dto);

				if (nickChk > 0) {
					resultDto.setReason("이미 사용중인 닉네임 입니다");
					resultDto.setError_code("1");

					return resultDto;
				}

				// 금지어 체크
				boolean banChkFlag = Utils.filterBanText(dto.getUser_nick_name(), versionMapper.getBanText("닉네임"));

				if (banChkFlag) {
					resultDto.setReason("닉네임에 금지어가 포함 되어 있습니다");
					resultDto.setError_code("2");

					return resultDto;
				}

				// dto.setUser_result_point(-50);//차감될 포인트
				int deductionFreePoint = -(pointdto.getUser_free_point() >= deductionPoint ? deductionPoint
						: pointdto.getUser_free_point());
				int deductionPayPoint = -(pointdto.getUser_free_point() >= deductionPoint ? 0
						: deductionPoint + deductionFreePoint);

				dto.setUser_deduction_free_point(deductionFreePoint);
				dto.setUser_deduction_point(deductionPayPoint);

				// 닉네임변경 -50P
				result = userMapper.updateProfilePointV2(dto);

				// 닉네임 변경으로 인한 포인트 차감 히스토리에 저장
				UseHistoryDTO hdto = new UseHistoryDTO();

				hdto.setUser_no(dto.getUser_no());
				hdto.setUse_content("닉네임변경 차감");

				if (deductionFreePoint != 0) {
					hdto.setUse_type(2); // 무료포인트
					hdto.setUse_cnt(deductionFreePoint);
					result2 = userMapper.insertUserHistory(hdto);
				}
				if (deductionPayPoint != 0) {
					hdto.setUse_type(0); // 유료포인트
					hdto.setUse_cnt(deductionPayPoint);
					result2 = userMapper.insertUserHistory(hdto);
				}

				if (result == 1 && result2 == 1) {
					// 차감된 포인트 보여주기
					dto.setUser_point(pointdto.getUser_point() - deductionPoint);
					dto.setUser_free_point(pointdto.getUser_free_point() + deductionFreePoint);// 무료포인트
					dto.setUser_pay_point(pointdto.getUser_pay_point() + deductionPayPoint); // 유료포인트

					resultDto.setUserInfo(dto);
					resultDto.setResult(Utils.SUCCESS);
				}
			}
		}
		return resultDto;
	}

	@Override
	public UserModifyDTO updateProfileV3(UserProfileUpdateDTO dto) {
		UserModifyDTO resultDto = new UserModifyDTO();

		// 닉네임 체크
		if (dto.getUser_nick_name().length() > 8) {
			resultDto.setReason("닉네임 8자 이하입니다");
			resultDto.setError_code("4");
			return resultDto;
		}

		// 직접 입력한 대화 주제 길이 체크
		if (dto.getConversation_no() == 0) {
			// 직접 입력한 대화 주제가 30자가 넘는 경우
			if (dto.getConversation_txt().length() > 30) {
				resultDto.setError_code("5");
				return resultDto;
			}
		}

		int result = 0;
		int result2 = 0;
		resultDto.setUserInfo(dto);

		// 소유 포인트 조회
		UserPointDTO pointdto = getMyPoint(dto.getUser_no());
		dto.setUser_point(pointdto.getUser_point());
		dto.setUser_free_point(pointdto.getUser_free_point());// 무료포인트
		dto.setUser_pay_point(pointdto.getUser_pay_point()); // 유료포인트

		// // 프로필 승인 대기중인 이미지가 있는지 확인
		// int cnt = userMapper.selectUserProfileImg(dto.getUser_no());
		// // 프로필 사진 변경 시
		// if (!"".equals(dto.getUser_thumnail()) && !"NONE".equals(dto.getUser_thumnail())) {
		// 	if (cnt > 0) {
		// 		userMapper.deleteProfileImg(dto.getUser_no());
		// 	}

		// 	LoginDTO ldto = new LoginDTO();
		// 	ldto.setUser_thumnail(dto.getUser_thumnail());
		// 	ldto.setNo(dto.getUser_no());
		// 	userMapper.userThumnailInsert(ldto);
		// }

		// 닉네임 중복검사
		String nick = userMapper.selectCheckNickname(dto.getUser_no());

		// 닉네임을 변경하지 않는다면
		if (nick.equals(dto.getUser_nick_name())) {
			if (dto.getConversation_no() == 0) {
				if (dto.getConversation_txt() == null) {
					dto.setConversation_txt("I wanna see you");
				}
				result = userMapper.updateProfileV2CovTxt(dto);
			} else {
				// 받아온 값을 전부 업데이트 해준다.
				result = userMapper.updateProfileV2(dto);
			}

			if (result == 1) {
				resultDto.setResult(Utils.SUCCESS);
			}

			// 닉네임을 변경하면
		} else {

			int deductionPoint = Utils.NMC_NICK_EDIT_POINT;// 차감될 포인트

			// 포인트 체크
			if (pointdto.getUser_point() < deductionPoint) {
				resultDto.setReason("포인트가 부족합니다.");
				resultDto.setError_code("3");

				return resultDto;

			} else {

				// 닉네임 중복 체크
				int nickChk = userMapper.nickNameChk2(dto);

				if (nickChk > 0) {
					resultDto.setReason("이미 사용중인 닉네임 입니다");
					resultDto.setError_code("1");

					return resultDto;
				}

				// 금지어 체크
				boolean banChkFlag = Utils.filterBanText(dto.getUser_nick_name(), versionMapper.getBanText("닉네임"));

				if (banChkFlag) {
					resultDto.setReason("닉네임에 금지어가 포함 되어 있습니다");
					resultDto.setError_code("2");

					return resultDto;
				}

				int deductionFreePoint = -(pointdto.getUser_free_point() >= deductionPoint ? deductionPoint
						: pointdto.getUser_free_point());
				int deductionPayPoint = -(pointdto.getUser_free_point() >= deductionPoint ? 0
						: deductionPoint + deductionFreePoint);

				dto.setUser_deduction_free_point(deductionFreePoint);
				dto.setUser_deduction_point(deductionPayPoint);

				// 닉네임변경
				result = userMapper.updateProfilePointV2(dto);

				// 닉네임 변경으로 인한 포인트 차감 히스토리에 저장
				UseHistoryDTO hdto = new UseHistoryDTO();

				hdto.setUser_no(dto.getUser_no());
				hdto.setUse_content("닉네임변경 차감");

				if (deductionFreePoint != 0) {
					hdto.setUse_type(2); // 무료포인트
					hdto.setUse_cnt(deductionFreePoint);
					result2 = userMapper.insertUserHistory(hdto);
				}
				if (deductionPayPoint != 0) {
					hdto.setUse_type(0); // 유료포인트
					hdto.setUse_cnt(deductionPayPoint);
					result2 = userMapper.insertUserHistory(hdto);
				}

				if (result == 1 && result2 == 1) {
					// 차감된 포인트 보여주기
					dto.setUser_point(pointdto.getUser_point() - deductionPoint);
					dto.setUser_free_point(pointdto.getUser_free_point() + deductionFreePoint);// 무료포인트
					dto.setUser_pay_point(pointdto.getUser_pay_point() + deductionPayPoint); // 유료포인트

					resultDto.setUserInfo(dto);
					resultDto.setResult(Utils.SUCCESS);
				}
			}
		}
		return resultDto;
	}

	// 랭킹 구버전
	@Override
	public UserRenkingListDTO selectUserRankingList(int user_no, String order) {
		UserRenkingListDTO dto = new UserRenkingListDTO();

		MainLiveCallDTO orderDto = new MainLiveCallDTO();
		orderDto.setUser_no(user_no);
		orderDto.setOrder(order);

		dto.setUserRankingList(userMapper.selectUserRankingList(orderDto));
		dto.setResult(Utils.SUCCESS);

		return dto;
	}

	// 랭킹 신버전
	@Override
	public UserRenkingListDTO selectUserRankingList(int user_no, String gender, String order) {
		UserRenkingListDTO dto = new UserRenkingListDTO();

		MainLiveCallDTO orderDto = new MainLiveCallDTO();
		orderDto.setUser_no(user_no);
		orderDto.setOrder(order);

		if ("male".equals(gender)) {
			dto.setUserRankingList(userMapper.selectMaleRankingList(orderDto));
		} else if ("newbie".equals(gender)) {
			dto.setUserRankingList(userMapper.selectNewbieRankingList(orderDto));
		} else {
			dto.setUserRankingList(userMapper.selectFamaleRankingList(orderDto));
		}

		dto.setResult(Utils.SUCCESS);

		return dto;
	}

	// 미인캠 랭킹
	@Override
	public UserRenkingListDTO selectUserRankingListMC(int user_no, String gender, String order) {
		UserRenkingListDTO dto = new UserRenkingListDTO();

		MainLiveCallDTO orderDto = new MainLiveCallDTO();
		orderDto.setUser_no(user_no);
		orderDto.setOrder(order);

		if ("male".equals(gender)) {
			// orderDto.setWaiting_gender("남성");
			dto.setUserRankingList(userMapper.selectMaleRankingListMC(orderDto));
		} else {
			// orderDto.setWaiting_gender("여성");
			dto.setUserRankingList(userMapper.selectFemaleRankingListMC(orderDto));
		}

		dto.setResult(Utils.SUCCESS);

		return dto;
	}

	@Override
	public DefaultDTO sendReport(int user_no, int to_user_no, int report_type, String report_content) {
		DefaultDTO resultDto = new DefaultDTO();

		ReportDTO dto = new ReportDTO();
		dto.setReport_content(report_content);
		dto.setReport_type(report_type);
		dto.setTo_user_no(to_user_no);
		dto.setUser_no(user_no);

		int result = userMapper.sendReport(dto);

		if (result == 1) {
			resultDto.setResult(Utils.SUCCESS);
		}

		return resultDto;
	}

	@Override
	public DefaultDTO updateUserOut(int user_no) {
		return Utils.defaultResult(new DefaultDTO(), userMapper.updateUserOut(user_no));
	}

	// 환급내역 리스트
	@Override
	public UserRefundsHistoryDTO selectUserRefundsHistory(int user_no) {

		UserRefundsHistoryDTO dto = new UserRefundsHistoryDTO();
		dto.setUserRefundsHistoryList(userMapper.selectUserRefundsHistory(user_no));
		dto.setResult(Utils.SUCCESS);

		return dto;
	}

	// 환급신청정보 업데이트 -> 히스토리 등록
	@Override
	public UserRefundsDTO updateUserRefundsInfo(UserRefundsUpdateDTO dto) {

		UserRefundsDTO resultDto = new UserRefundsDTO();

		GetCashDTO cashDto = userMapper.getMyCash(dto.getNo()); // 캐시 확인

		// 보유 캐시가 더 신청환급금보다 크다면 성공
		if (cashDto.getUser_cash() >= dto.getUser_use_cash()) {

			if (!"NONE".equals(dto.getUser_bank_paper_copy())) {
				String bank_path = saveImg(dto.getUser_bank_paper_copy(), dto.getNo(), "payback", "bank"); // 이미지 복사
				dto.setUser_bank_paper_copy(bank_path);
			}

			if (!"NONE".equals(dto.getUser_co_paper())) {
				String jubun_path = saveImg(dto.getUser_co_paper(), dto.getNo(), "payback", "jubun"); // 이미지 복사
				dto.setUser_co_paper(jubun_path);

			}
			resultDto.setDto(dto);
			int result = userMapper.updateUserRefundsInfo(dto); // 프로필 업데이트 (환급정보만 업데이트)

			if (result == 1) {

				UserRefundsHistoryListDTO hisDto = new UserRefundsHistoryListDTO();
				hisDto.setUse_cnt(dto.getUser_use_cash()); // 신청 캐시
				hisDto.setUse_content("환급신청");
				hisDto.setUser_no(dto.getNo());
				hisDto.setUse_type(3);
				resultDto.setHisDto(hisDto);
				userMapper.insertUserRefundsHistory(resultDto.getHisDto()); // 히스토리 등록 (앱 상 환급리스트 뿌리기용)

				RefundsInfoDTO reDto = new RefundsInfoDTO();
				reDto.setRefund_user_no(dto.getNo());
				reDto.setRefund_cash(dto.getUser_use_cash());
				reDto.setRefund_pay(dto.getUser_use_cash());
				userMapper.insertUserRefundsInfo(reDto); // 환급신청 테이블 등록

				resultDto.setResult(Utils.SUCCESS);
			}
		}

		return resultDto;
	}

	public String saveImg(String img_name, int no, String type, String subject) {

		String img_path = "";

		String imgTempPath = Utils.IMAGE_PATH + "/temp/" + type;
		String savePath = Utils.IMAGE_PATH + "/" + type + "/" + no;
		String saveUrl = Utils.IMAGE_URL + "/" + type + "/" + no;

		Utils.createDir(savePath);

		String[] imgPathSplit = img_name.split("\\.");
		String imgType = imgPathSplit[imgPathSplit.length - 1];

		File tempFile = new File(imgTempPath + img_name);

		Boolean img = Utils.resizeImage(imgTempPath + "/" + img_name, savePath + "/" + img_name, Utils.IMAGE_ORIGINAL);

		if (img == true) {
			img_path = saveUrl + "/" + img_name;
			tempFile.delete();
		}

		return img_path;
	}

	// 저장하기
	@Override
	public UserRefundsDTO updateUserRefundsInfoSave(UserRefundsUpdateDTO dto) {
		UserRefundsDTO resultDto = new UserRefundsDTO();

		if (!"NONE".equals(dto.getUser_bank_paper_copy())) {
			String bank_path = saveImg(dto.getUser_bank_paper_copy(), dto.getNo(), "payback", "bank"); // 이미지 복사
			dto.setUser_bank_paper_copy(bank_path);
		}

		if (!"NONE".equals(dto.getUser_co_paper())) {
			String jubun_path = saveImg(dto.getUser_co_paper(), dto.getNo(), "payback", "jubun"); // 이미지 복사
			dto.setUser_co_paper(jubun_path);
		}

		resultDto.setDto(dto);
		int result = userMapper.updateUserRefundsInfo(dto); // 프로필 업데이트

		if (result == 1) {
			resultDto.setResult(Utils.SUCCESS);
		}
		return resultDto;
	}

	// 환급정보 불러오기
	@Override
	public UserRefundsViewDTO selectUserRefunds(int no) {

		UserRefundsViewDTO dto = userMapper.selectUserRefunds(no);

		String img_name = dto.getUser_co_paper();
		String[] imgPathSplit = img_name.split("\\/");
		String co_paper = imgPathSplit[imgPathSplit.length - 1];

		String img_name2 = dto.getUser_bank_paper_copy();
		String[] imgPathSplit2 = img_name2.split("\\/");
		String bank_paper = imgPathSplit2[imgPathSplit2.length - 1];

		dto.setUser_bank_paper_copy(bank_paper);
		dto.setUser_co_paper(co_paper);
		dto.setResult(Utils.SUCCESS);

		return dto;
	}

	@Override
	public int chkUserBlackList(int user_no, int black_user_no) {
		CallChkUserDTO blackDto = new CallChkUserDTO();
		blackDto.setUser_no(user_no);
		blackDto.setPartner_user_no(black_user_no);
		return userMapper.chkUserBlackList(blackDto);
	}

	// 저장하기
	@Override
	public UserModifyDTO updateUserNewbieTime(UserProfileUpdateDTO dto) {
		UserModifyDTO resultDto = new UserModifyDTO();

		resultDto.setUserInfo(dto);

		int result = userMapper.updateUserNewbieTime(dto); // 프로필 업데이트

		if (result == 1) {
			resultDto.setResult(Utils.SUCCESS);
		}
		return resultDto;
	}

	@Override
	public DefaultDTO insertNewbieLevelUp(NewbieLevelUpDTO dto) {
		userMapper.insertNewbieLevelUp(dto);

		DefaultDTO returnDto = new DefaultDTO();

		returnDto.setReason(dto.getResult_msg());
		if (dto.getResult_code() == 1) {
			returnDto.setResult(Utils.SUCCESS);
		}
		return returnDto;
	}

	@Override
	public DefaultDTO insertExchangeHeart(UserExchangeHeartDTO dto) {
		userMapper.insertExchangeHeart(dto);

		DefaultDTO returnDto = new DefaultDTO();

		returnDto.setReason(dto.getResult_msg());
		if (dto.getResult_code() == 1) {
			returnDto.setResult(Utils.SUCCESS);
		}
		return returnDto;

	}

	@Override
	public DefaultDTO checkPartnerInstall(String adid) {
		DefaultDTO returnDto = new DefaultDTO();
		returnDto.setResult(Utils.FAIL);
		returnDto.setReason("설치 미확인");
		if (!"".equals(adid)) {
			String pResult = PayServiceImpl
					.directConnection("https://partner.allcam.co.kr/APP_acc/app_data.php?TYPE=E&ADID=" + adid);
			if (pResult.equals("0000")) {
				returnDto.setResult(Utils.SUCCESS);
				returnDto.setReason("설치 확인");
			}
		}
		return returnDto;
	}

	@Override
	public DefaultDTO verifyPartnerInstall(String adid, String partner_id) {
		DefaultDTO returnDto = new DefaultDTO();
		returnDto.setResult(Utils.FAIL);
		returnDto.setReason("검증 실패");
		if (!"".equals(adid)) {
			String pResult = PayServiceImpl.directConnection(
					"https://partner.allcam.co.kr/APP_acc/app_data.php?TYPE=E&ADID=" + adid + "&PID=" + partner_id);
			if (pResult.equals("0000")) {
				returnDto.setResult(Utils.SUCCESS);
				returnDto.setReason("검증 완료");
			}
		}
		return returnDto;
	}

	@Override
	public DefaultDTO checkNicknameUsed(String user_nick_name) {
		DefaultDTO returnDto = new DefaultDTO();
		returnDto.setResult(Utils.FAIL);
		returnDto.setReason("미사용");
		int nickChk = userMapper.nickNameChk(user_nick_name); // 해당 닉네임 카운트
		if (nickChk > 0) {
			returnDto.setResult(Utils.SUCCESS);
			returnDto.setReason("사용중인 닉네임");
		}
		return returnDto;
	}

}