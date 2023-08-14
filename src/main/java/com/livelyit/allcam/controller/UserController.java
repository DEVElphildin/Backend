package com.livelyit.allcam.controller;

import com.livelyit.allcam.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.mapper.UserMapper;
import com.livelyit.allcam.service.UserService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@RestController
public class UserController {
	@Autowired
	UserService userService;

	@Autowired
	UserMapper userMapper;

//	@RequestMapping(value = "/login", params = {"user_id", "pushKey"})
//	@ResponseBody
//	public LoginDTO login(@RequestParam(value = "user_id", required = !Utils.Debug) String user_id,
//						  @RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey) {
//		LoginDTO dto = new LoginDTO();
//		dto.setUser_id(user_id);
//		dto.setUser_push_key(pushKey);
//		dto.setOs_type("android");
//		dto.setUser_service_type("allcam");
//		dto.setUser_phone_number("NONE");
//		dto.setUser_country_code("NONE");
//		dto.setUser_language_code("NONE");
//
//		return userService.login(dto);
//	}
//
//	@RequestMapping(value = "/login", params = {"user_id", "pushKey", "osType"})
//	@ResponseBody
//	public LoginDTO login(@RequestParam(value = "user_id", required = !Utils.Debug) String user_id,
//			@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey,
//			@RequestParam(value = "osType", required = false) String osType) {
//		if(null == osType) {
//			osType = "android";
//		}
//		LoginDTO dto = new LoginDTO();
//		dto.setUser_id(user_id);
//		dto.setUser_push_key(pushKey);
//		dto.setOs_type(osType);
//		dto.setUser_service_type("allcam");
//		dto.setUser_phone_number("NONE");
//		dto.setUser_country_code("NONE");
//		dto.setUser_language_code("NONE");
//
//		return userService.login(dto);
//	}
//
//	@RequestMapping(value = "/login", params = {"user_id", "pushKey", "osType", "serviceType", "identiCode"})
//	@ResponseBody
//	public LoginDTO login(@RequestParam(value = "user_id", required = !Utils.Debug) String user_id,
//						  @RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey,
//						  @RequestParam(value = "osType", required = false) String osType,
//						  @RequestParam(value = "serviceType", required = !Utils.Debug) String serviceType,
//						  @RequestParam(value = "identiCode", required = !Utils.Debug) String identiCode) {
//		if(null == osType) {
//			osType = "android";
//		}
//		LoginDTO dto = new LoginDTO();
//		dto.setUser_id(user_id);
//		dto.setUser_push_key(pushKey);
//		dto.setOs_type(osType);
//		dto.setUser_service_type(serviceType);
//		dto.setUser_identi_code(identiCode);
//		dto.setUser_phone_number("NONE");
//		dto.setUser_country_code("NONE");
//		dto.setUser_language_code("NONE");
//
//		return userService.login(dto);
//	}
//
//	@RequestMapping(value = "/login", params = {"user_id", "pushKey", "osType", "serviceType", "identiCode", "phoneNumber","countryCode", "languageCode"})
//	@ResponseBody
//	public LoginDTO login(@RequestParam(value = "user_id", required = !Utils.Debug) String user_id,
//						  @RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey,
//						  @RequestParam(value = "osType", required = false) String osType,
//						  @RequestParam(value = "serviceType", required = !Utils.Debug) String serviceType,
//						  @RequestParam(value = "identiCode", required = !Utils.Debug) String identiCode,
//						  @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
//						  @RequestParam(value = "countryCode", required = !Utils.Debug) String countryCode,
//						  @RequestParam(value = "languageCode", required = !Utils.Debug) String languageCode) {
//		if(null == osType) {
//			osType = "android";
//		}
//		if(null == phoneNumber) {
//			phoneNumber = "NONE";
//		}
//		LoginDTO dto = new LoginDTO();
//		dto.setUser_id(user_id);
//		dto.setUser_push_key(pushKey);
//		dto.setOs_type(osType);
//		dto.setUser_service_type(serviceType);
//		dto.setUser_identi_code(identiCode);
//		dto.setUser_phone_number(phoneNumber);
//		dto.setUser_country_code(countryCode);
//		dto.setUser_language_code(languageCode);
//
//		return userService.login(dto);
//	}
//
//
//	@RequestMapping(value = "/v2/login", params = {"user_id", "pushKey", "osType", "serviceType", "identiCode", "phoneNumber","countryCode", "languageCode"})
//	@ResponseBody
//	public LoginDTO loginV2(@RequestParam(value = "user_id", required = !Utils.Debug) String user_id,
//						  @RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey,
//						  @RequestParam(value = "osType", required = false) String osType,
//						  @RequestParam(value = "serviceType", required = !Utils.Debug) String serviceType,
//						  @RequestParam(value = "identiCode", required = !Utils.Debug) String identiCode,
//						  @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
//						  @RequestParam(value = "countryCode", required = !Utils.Debug) String countryCode,
//						  @RequestParam(value = "languageCode", required = !Utils.Debug) String languageCode) {
//		if(null == osType) {
//			osType = "android";
//		}
//		if(null == phoneNumber) {
//			phoneNumber = "NONE";
//		}
//
//		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//		String ip = req.getHeader("X-FORWARDED-FOR");
//		if (ip == null)
//			ip = req.getRemoteAddr();
//
//		LoginDTO dto = new LoginDTO();
//		dto.setIp(ip);
//		dto.setUser_id(user_id);
//		dto.setUser_push_key(pushKey);
//		dto.setOs_type(osType);
//		dto.setUser_service_type(serviceType);
//		dto.setUser_identi_code(identiCode);
//		dto.setUser_phone_number(phoneNumber);
//		dto.setUser_country_code(countryCode);
//		dto.setUser_language_code(languageCode);
//
//		return userService.loginV2(dto);
//	}
//
//	@RequestMapping(value = "/v3/login", params = {"user_id", "pushKey", "osType", "serviceType", "identiCode", "phoneNumber","countryCode", "languageCode"})
//	@ResponseBody
//	public LoginDTO loginV3(@RequestParam(value = "user_id", required = !Utils.Debug) String user_id,
//							@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey,
//							@RequestParam(value = "osType", required = false) String osType,
//							@RequestParam(value = "serviceType", required = !Utils.Debug) String serviceType,
//							@RequestParam(value = "identiCode", required = !Utils.Debug) String identiCode,
//							@RequestParam(value = "phoneNumber", required = false) String phoneNumber,
//							@RequestParam(value = "countryCode", required = !Utils.Debug) String countryCode,
//							@RequestParam(value = "languageCode", required = !Utils.Debug) String languageCode) {
//		if(null == osType) {
//			osType = "android";
//		}
//		if(null == phoneNumber) {
//			phoneNumber = "NONE";
//		}
//
//		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//		String ip = req.getHeader("X-FORWARDED-FOR");
//		if (ip == null)
//			ip = req.getRemoteAddr();
//
//		LoginDTO dto = new LoginDTO();
//		dto.setIp(ip);
//		dto.setUser_id(user_id);
//		dto.setUser_push_key(pushKey);
//		dto.setOs_type(osType);
//		dto.setUser_service_type(serviceType);
//		dto.setUser_identi_code(identiCode);
//		dto.setUser_phone_number(phoneNumber);
//		dto.setUser_country_code(countryCode);
//		dto.setUser_language_code(languageCode);
//
//		return userService.loginV2(dto);
//	}

	@RequestMapping(value = "/v4/login", params = {"user_id", "pushKey", "osType", "serviceType", "identiCode", "phoneNumber","countryCode", "languageCode", "loginType"})
	@ResponseBody
	public LoginDTO loginV3(@RequestParam(value = "user_id", required = !Utils.Debug) String user_id,
							@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey,
							@RequestParam(value = "osType", required = false) String osType,
							@RequestParam(value = "serviceType", required = !Utils.Debug) String serviceType,
							@RequestParam(value = "identiCode", required = !Utils.Debug) String identiCode,
							@RequestParam(value = "phoneNumber", required = false) String phoneNumber,
							@RequestParam(value = "countryCode", required = !Utils.Debug) String countryCode,
							@RequestParam(value = "languageCode", required = !Utils.Debug) String languageCode,
							@RequestParam(value = "loginType", required = !Utils.Debug) String loginType) {
		if(null == osType) {
			osType = "android";
		}
		if(null == phoneNumber) {
			phoneNumber = "NONE";
		}

		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String ip = req.getHeader("X-FORWARDED-FOR");
		if (ip == null)
			ip = req.getRemoteAddr();

		LoginDTO dto = new LoginDTO();
		dto.setIp(ip);
		dto.setUser_id(user_id);
		dto.setUser_push_key(pushKey);
		dto.setOs_type(osType);
		dto.setUser_service_type(serviceType);
		dto.setUser_identi_code(identiCode);
		dto.setUser_phone_number(phoneNumber);
		dto.setUser_country_code(countryCode);
		dto.setUser_language_code(languageCode);
		dto.setLogin_type(loginType);

		return userService.loginV4(dto);
	}

	@RequestMapping(value = "/v5/login", params = {"user_id", "pushKey", "osType", "serviceType", "identiCode", "phoneNumber","countryCode", "languageCode", "loginType", "version"})
	@ResponseBody
	public LoginDTO loginV5(@RequestParam(value = "user_id", required = !Utils.Debug) String user_id,
							@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey,
							@RequestParam(value = "osType", required = false) String osType,
							@RequestParam(value = "serviceType", required = !Utils.Debug) String serviceType,
							@RequestParam(value = "identiCode", required = !Utils.Debug) String identiCode,
							@RequestParam(value = "phoneNumber", required = false) String phoneNumber,
							@RequestParam(value = "countryCode", required = !Utils.Debug) String countryCode,
							@RequestParam(value = "languageCode", required = !Utils.Debug) String languageCode,
							@RequestParam(value = "loginType", required = !Utils.Debug) String loginType,
							@RequestParam(value = "version", required = false) String version) {
		if(null == osType) {
			osType = "android";
		}
		if(null == phoneNumber) {
			phoneNumber = "NONE";
		}
		if(null == version) {
			version = "미확인";
		}

		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String ip = req.getHeader("X-FORWARDED-FOR");
		if (ip == null)
			ip = req.getRemoteAddr();

		LoginDTO dto = new LoginDTO();
		dto.setIp(ip);
		dto.setUser_id(user_id);
		dto.setUser_push_key(pushKey);
		dto.setOs_type(osType);
		dto.setUser_service_type(serviceType);
		dto.setUser_identi_code(identiCode);
		dto.setUser_phone_number(phoneNumber);
		dto.setUser_country_code(countryCode);
		dto.setUser_language_code(languageCode);
		dto.setLogin_type(loginType);
		dto.setVersion(version);

		return userService.loginV5(dto);
	}


	@RequestMapping(value = "/v6/login", params = {"user_id", "pushKey", "osType", "serviceType", "identiCode", "phoneNumber","countryCode", "languageCode", "loginType", "version"})
	@ResponseBody
	public LoginDTO loginV6(@RequestParam(value = "user_id", required = !Utils.Debug) String user_id,
							@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey,
							@RequestParam(value = "osType", required = false) String osType,
							@RequestParam(value = "serviceType", required = !Utils.Debug) String serviceType,
							@RequestParam(value = "identiCode", required = !Utils.Debug) String identiCode,
							@RequestParam(value = "phoneNumber", required = false) String phoneNumber,
							@RequestParam(value = "countryCode", required = !Utils.Debug) String countryCode,
							@RequestParam(value = "languageCode", required = !Utils.Debug) String languageCode,
							@RequestParam(value = "loginType", required = !Utils.Debug) String loginType,
							@RequestParam(value = "version", required = false) String version) {
		if(null == osType) {
			osType = "android";
		}
		if(null == phoneNumber) {
			phoneNumber = "NONE";
		}
		if(null == version) {
			version = "미확인";
		}

		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String ip = req.getHeader("X-FORWARDED-FOR");
		if (ip == null)
			ip = req.getRemoteAddr();

		LoginDTO dto = new LoginDTO();
		dto.setIp(ip);
		dto.setUser_id(user_id);
		dto.setUser_push_key(pushKey);
		dto.setOs_type(osType);
		dto.setUser_service_type(serviceType);
		dto.setUser_identi_code(identiCode);
		dto.setUser_phone_number(phoneNumber);
		dto.setUser_country_code(countryCode);
		dto.setUser_language_code(languageCode);
		dto.setLogin_type(loginType);
		dto.setVersion(version);

		return userService.loginV6(dto);
	}

	@RequestMapping(value = "/v7/login", params = {"user_id", "pushKey", "voipKey", "osType", "serviceType", "identiCode", "phoneNumber","countryCode", "languageCode", "loginType", "version"})
	@ResponseBody
	public LoginDTO loginV7(@RequestParam(value = "user_id", required = !Utils.Debug) String user_id,
							@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey,
							@RequestParam(value = "voipKey", required = !Utils.Debug) String voipKey,
							@RequestParam(value = "osType", required = false) String osType,
							@RequestParam(value = "serviceType", required = !Utils.Debug) String serviceType,
							@RequestParam(value = "identiCode", required = !Utils.Debug) String identiCode,
							@RequestParam(value = "phoneNumber", required = false) String phoneNumber,
							@RequestParam(value = "countryCode", required = !Utils.Debug) String countryCode,
							@RequestParam(value = "languageCode", required = !Utils.Debug) String languageCode,
							@RequestParam(value = "loginType", required = !Utils.Debug) String loginType,
							@RequestParam(value = "version", required = false) String version) {
		if(null == osType) {
			osType = "android";
		}
		if(null == phoneNumber) {
			phoneNumber = "NONE";
		}
		if(null == version) {
			version = "미확인";
		}

		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String ip = req.getHeader("X-FORWARDED-FOR");
		if (ip == null)
			ip = req.getRemoteAddr();

		LoginDTO dto = new LoginDTO();
		dto.setIp(ip);
		dto.setUser_id(user_id);
		dto.setUser_voip_key(voipKey);
		dto.setUser_push_key(pushKey);
		dto.setOs_type(osType);
		dto.setUser_service_type(serviceType);
		dto.setUser_identi_code(identiCode);
		dto.setUser_phone_number(phoneNumber);
		dto.setUser_country_code(countryCode);
		dto.setUser_language_code(languageCode);
		dto.setLogin_type(loginType);
		dto.setVersion(version);

		return userService.loginV6(dto);
	}



//
//	@RequestMapping(value = "/join", params = {"authToken", "nickName", "gender", "age", "conversation", "pushKey", "thumnail", "authEmail"})
//	@ResponseBody
//	public LoginDTO join(@RequestParam(value = "authToken", required = !Utils.Debug) String authToken
//			,@RequestParam(value = "nickName", required = !Utils.Debug) String nickName
//			,@RequestParam(value = "gender", required = !Utils.Debug) String gender
//			,@RequestParam(value = "age", required = !Utils.Debug) int age
//			,@RequestParam(value = "conversation", required = !Utils.Debug) int conversation
//			,@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey
//			,@RequestParam(value = "thumnail", required = !Utils.Debug) String thumnail
//			,@RequestParam(value = "authEmail", required = !Utils.Debug) String authEmail
//	) {
//		LoginDTO dto = new LoginDTO();
//		dto.setUser_id(authToken);
//		dto.setUser_nick_name(nickName);
//		dto.setUser_gender(gender);
//		dto.setUser_age(age);
//		dto.setConversation_no(conversation);
//		dto.setUser_push_key(pushKey);
//		dto.setUser_thumnail(thumnail);
//		dto.setUser_email(authEmail);
//		dto.setOs_type("android");
//		dto.setUser_identi_code("NONE");
//		dto.setUser_service_type("allcam");
//
//		return userService.join(dto);
//	}
//
//	@RequestMapping(value = "/join", params = {"authToken", "nickName", "gender", "age", "conversation", "pushKey", "thumnail", "authEmail", "osType"})
//	@ResponseBody
//	public LoginDTO join(@RequestParam(value = "authToken", required = !Utils.Debug) String authToken
//			,@RequestParam(value = "nickName", required = !Utils.Debug) String nickName
//			,@RequestParam(value = "gender", required = !Utils.Debug) String gender
//			,@RequestParam(value = "age", required = !Utils.Debug) int age
//			,@RequestParam(value = "conversation", required = !Utils.Debug) int conversation
//			,@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey
//			,@RequestParam(value = "thumnail", required = !Utils.Debug) String thumnail
//			,@RequestParam(value = "authEmail", required = !Utils.Debug) String authEmail
//			,@RequestParam(value = "osType", required = false) String osType
//			) {
//		LoginDTO dto = new LoginDTO();
//		dto.setUser_id(authToken);
//		dto.setUser_nick_name(nickName);
//		dto.setUser_gender(gender);
//	    dto.setUser_age(age);
//	    dto.setConversation_no(conversation);
//	    dto.setUser_push_key(pushKey);
//	    dto.setUser_thumnail(thumnail);
//	    dto.setUser_email(authEmail);
//	    dto.setOs_type(osType);
//		dto.setUser_identi_code("NONE");
//		dto.setUser_service_type("allcam");
//
//		return userService.join(dto);
//	}
//
//	@RequestMapping(value = "/join", params = {"authToken", "nickName", "gender", "age", "conversation", "pushKey", "thumnail", "authEmail", "osType", "identiCode", "serviceType"})
//	@ResponseBody
//	public LoginDTO join(@RequestParam(value = "authToken", required = !Utils.Debug) String authToken
//			,@RequestParam(value = "nickName", required = !Utils.Debug) String nickName
//			,@RequestParam(value = "gender", required = !Utils.Debug) String gender
//			,@RequestParam(value = "age", required = !Utils.Debug) int age
//			,@RequestParam(value = "conversation", required = !Utils.Debug) int conversation
//			,@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey
//			,@RequestParam(value = "thumnail", required = !Utils.Debug) String thumnail
//			,@RequestParam(value = "authEmail", required = !Utils.Debug) String authEmail
//			,@RequestParam(value = "osType", required = false) String osType
//			,@RequestParam(value = "identiCode", required = !Utils.Debug) String identiCode
//			,@RequestParam(value = "serviceType", required = !Utils.Debug) String serviceType) {
//		LoginDTO dto = new LoginDTO();
//		dto.setUser_id(authToken);
//		dto.setUser_nick_name(nickName);
//		dto.setUser_gender(gender);
//		dto.setUser_age(age);
//		dto.setConversation_no(conversation);
//		dto.setUser_push_key(pushKey);
//		dto.setUser_thumnail(thumnail);
//		dto.setUser_email(authEmail);
//		dto.setOs_type(osType);
//		dto.setUser_identi_code(identiCode);
//		dto.setUser_service_type(serviceType);
//
//		return userService.join(dto);
//	}
//
//	@RequestMapping(value = "/v2/join", params = {"authToken", "nickName", "gender", "age", "conversation", "pushKey", "thumnail", "authEmail", "osType", "identiCode", "serviceType"})
//	@ResponseBody
//	public LoginDTO joinV2(@RequestParam(value = "authToken", required = !Utils.Debug) String authToken
//			,@RequestParam(value = "nickName", required = !Utils.Debug) String nickName
//			,@RequestParam(value = "gender", required = !Utils.Debug) String gender
//			,@RequestParam(value = "age", required = !Utils.Debug) int age
//			,@RequestParam(value = "conversation", required = !Utils.Debug) int conversation
//			,@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey
//			,@RequestParam(value = "thumnail", required = !Utils.Debug) String thumnail
//			,@RequestParam(value = "authEmail", required = !Utils.Debug) String authEmail
//			,@RequestParam(value = "osType", required = false) String osType
//			,@RequestParam(value = "identiCode", required = !Utils.Debug) String identiCode
//			,@RequestParam(value = "serviceType", required = !Utils.Debug) String serviceType) {
//		LoginDTO dto = new LoginDTO();
//		dto.setUser_id(authToken);
//		dto.setUser_nick_name(nickName);
//		dto.setUser_gender(gender);
//		dto.setUser_age(age);
//		dto.setConversation_no(conversation);
//		dto.setUser_push_key(pushKey);
//		dto.setUser_thumnail(thumnail);
//		dto.setUser_email(authEmail);
//		dto.setOs_type(osType);
//		dto.setUser_identi_code(identiCode);
//		dto.setUser_service_type(serviceType);
//		if( "여성".equals(gender) && !"allcam".equals(serviceType)){ //올캠 제외 여성회원 새내기
//			dto.setUser_part_time(3);
//		}else{
//			dto.setUser_part_time(0);
//		}
//		return userService.joinV2(dto);
//	}
//
//	@RequestMapping(value = "/v3/join", params = {"authToken", "nickName", "gender", "age", "conversation", "pushKey", "thumnail", "authEmail", "osType", "identiCode", "serviceType"})
//	@ResponseBody
//	public LoginDTO joinV3(@RequestParam(value = "authToken", required = !Utils.Debug) String authToken
//			,@RequestParam(value = "nickName", required = !Utils.Debug) String nickName
//			,@RequestParam(value = "gender", required = !Utils.Debug) String gender
//			,@RequestParam(value = "age", required = !Utils.Debug) int age
//			,@RequestParam(value = "conversation", required = !Utils.Debug) int conversation
//			,@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey
//			,@RequestParam(value = "thumnail", required = !Utils.Debug) String thumnail
//			,@RequestParam(value = "authEmail", required = !Utils.Debug) String authEmail
//			,@RequestParam(value = "osType", required = false) String osType
//			,@RequestParam(value = "identiCode", required = !Utils.Debug) String identiCode
//			,@RequestParam(value = "serviceType", required = !Utils.Debug) String serviceType) {
//		LoginDTO dto = new LoginDTO();
//		dto.setUser_id(authToken);
//		dto.setUser_nick_name(nickName);
//		dto.setUser_gender(gender);
//		dto.setUser_age(age);
//		dto.setConversation_no(conversation);
//		dto.setUser_push_key(pushKey);
//		dto.setUser_thumnail(thumnail);
//		dto.setUser_email(authEmail);
//		dto.setOs_type(osType);
//		dto.setUser_identi_code(identiCode);
//		dto.setUser_service_type(serviceType);
//		if( "여성".equals(gender)){
//			dto.setUser_part_time(3);
//		}else{
//			dto.setUser_part_time(0);
//		}
//		return userService.joinV2(dto);
//	}
//
//	@RequestMapping(value = "/v4/join", params = {"authToken", "nickName", "gender", "age", "conversation", "pushKey", "thumnail", "authEmail", "osType", "identiCode", "serviceType","countryCode", "languageCode"})
//	@ResponseBody
//	public LoginDTO joinV4(@RequestParam(value = "authToken", required = !Utils.Debug) String authToken
//			,@RequestParam(value = "nickName", required = !Utils.Debug) String nickName
//			,@RequestParam(value = "gender", required = !Utils.Debug) String gender
//			,@RequestParam(value = "age", required = !Utils.Debug) int age
//			,@RequestParam(value = "conversation", required = !Utils.Debug) int conversation
//			,@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey
//			,@RequestParam(value = "thumnail", required = !Utils.Debug) String thumnail
//			,@RequestParam(value = "authEmail", required = !Utils.Debug) String authEmail
//			,@RequestParam(value = "osType", required = false) String osType
//			,@RequestParam(value = "identiCode", required = !Utils.Debug) String identiCode
//			,@RequestParam(value = "serviceType", required = !Utils.Debug) String serviceType
//			,@RequestParam(value = "countryCode", required = !Utils.Debug) String countryCode
//			,@RequestParam(value = "languageCode", required = !Utils.Debug) String languageCode) {
//		LoginDTO dto = new LoginDTO();
//		dto.setUser_id(authToken);
//		dto.setUser_nick_name(nickName);
//		dto.setUser_gender(gender);
//		dto.setUser_age(age);
//		dto.setConversation_no(conversation);
//		dto.setUser_push_key(pushKey);
//		dto.setUser_thumnail(thumnail);
//		dto.setUser_email(authEmail);
//		dto.setOs_type(osType);
//		dto.setAgent_id("NONE");
//		dto.setUser_identi_code(identiCode);
//		dto.setUser_service_type(serviceType);
//		dto.setUser_country_code(countryCode);
//		dto.setUser_language_code(languageCode);
//		if( "여성".equals(gender)){
//			dto.setUser_part_time(3);
//		}else{
//			dto.setUser_part_time(0);
//		}
//		return userService.joinV4(dto);
//	}
//
//	@RequestMapping(value = "/v5/join", params = {"authToken", "nickName", "gender", "age", "conversation", "pushKey", "thumnail", "authEmail", "osType", "identiCode", "serviceType","countryCode", "languageCode", "agentId", "emailReceiveAgree"})
//	@ResponseBody
//	public LoginDTO joinV5(@RequestParam(value = "authToken", required = !Utils.Debug) String authToken
//			,@RequestParam(value = "nickName", required = !Utils.Debug) String nickName
//			,@RequestParam(value = "gender", required = !Utils.Debug) String gender
//			,@RequestParam(value = "age", required = !Utils.Debug) int age
//			,@RequestParam(value = "conversation", required = !Utils.Debug) int conversation
//			,@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey
//			,@RequestParam(value = "thumnail", required = !Utils.Debug) String thumnail
//			,@RequestParam(value = "authEmail", required = !Utils.Debug) String authEmail
//			,@RequestParam(value = "osType", required = false) String osType
//			,@RequestParam(value = "identiCode", required = !Utils.Debug) String identiCode
//			,@RequestParam(value = "serviceType", required = !Utils.Debug) String serviceType
//			,@RequestParam(value = "countryCode", required = !Utils.Debug) String countryCode
//			,@RequestParam(value = "languageCode", required = !Utils.Debug) String languageCode
//			,@RequestParam(value = "agentId", required = !Utils.Debug) String agentId
//			,@RequestParam(value = "emailReceiveAgree", required = !Utils.Debug) int emailReceiveAgree) {
//		LoginDTO dto = new LoginDTO();
//		dto.setUser_id(authToken);
//		dto.setUser_nick_name(nickName);
//		dto.setUser_gender(gender);
//		dto.setUser_age(age);
//		dto.setConversation_no(conversation);
//		dto.setUser_push_key(pushKey);
//		dto.setUser_thumnail(thumnail);
//		dto.setUser_email(authEmail);
//		dto.setOs_type(osType);
//		dto.setUser_identi_code(identiCode);
//		dto.setUser_service_type(serviceType);
//		dto.setUser_country_code(countryCode);
//		dto.setUser_language_code(languageCode);
//		dto.setAgent_id(agentId);
//		dto.setEmail_receive_agree(emailReceiveAgree);
//		if( "여성".equals(gender)){
//			dto.setUser_part_time(3);
//		}else{
//			dto.setUser_part_time(0);
//		}
//		return userService.joinV5(dto);
//	}
//
//
//	@RequestMapping(value = "/v6/join", params = {"authToken", "nickName", "gender", "age", "conversation", "pushKey", "thumnail", "authEmail", "osType", "identiCode", "serviceType","countryCode", "languageCode", "agentId", "emailReceiveAgree", "loginType"})
//	@ResponseBody
//	public LoginDTO joinV5(@RequestParam(value = "authToken", required = !Utils.Debug) String authToken
//			,@RequestParam(value = "nickName", required = !Utils.Debug) String nickName
//			,@RequestParam(value = "gender", required = !Utils.Debug) String gender
//			,@RequestParam(value = "age", required = !Utils.Debug) int age
//			,@RequestParam(value = "conversation", required = !Utils.Debug) int conversation
//			,@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey
//			,@RequestParam(value = "thumnail", required = !Utils.Debug) String thumnail
//			,@RequestParam(value = "authEmail", required = !Utils.Debug) String authEmail
//			,@RequestParam(value = "osType", required = false) String osType
//			,@RequestParam(value = "identiCode", required = !Utils.Debug) String identiCode
//			,@RequestParam(value = "serviceType", required = !Utils.Debug) String serviceType
//			,@RequestParam(value = "countryCode", required = !Utils.Debug) String countryCode
//			,@RequestParam(value = "languageCode", required = !Utils.Debug) String languageCode
//			,@RequestParam(value = "agentId", required = !Utils.Debug) String agentId
//			,@RequestParam(value = "emailReceiveAgree", required = !Utils.Debug) int emailReceiveAgree
//			,@RequestParam(value = "loginType", required = !Utils.Debug) String loginType) {
//		LoginDTO dto = new LoginDTO();
//		dto.setUser_id(authToken);
//		dto.setUser_nick_name(nickName);
//		dto.setUser_gender(gender);
//		dto.setUser_age(age);
//		dto.setConversation_no(conversation);
//		dto.setUser_push_key(pushKey);
//		dto.setUser_thumnail(thumnail);
//		dto.setUser_email(authEmail);
//		dto.setOs_type(osType);
//		dto.setUser_identi_code(identiCode);
//		dto.setUser_service_type(serviceType);
//		dto.setUser_country_code(countryCode);
//		dto.setUser_language_code(languageCode);
//		dto.setCall_type(1);
//		dto.setAgent_id(agentId);
//		dto.setEmail_receive_agree(emailReceiveAgree);
//		dto.setLogin_type(loginType);
//		if( "여성".equals(gender)){
//			dto.setUser_part_time(3);
//		}else{
//			dto.setUser_part_time(0);
//		}
//		return userService.joinV6(dto);
//	}

//	// 대화 주제를 직접 입력한 경우
//	@RequestMapping(value = "/v7/join", params = {"authToken", "nickName", "gender", "age", "conversation", "pushKey", "thumnail", "authEmail", "osType", "identiCode", "serviceType","countryCode", "languageCode", "agentId", "emailReceiveAgree", "loginType"})
//	@ResponseBody
//	public LoginDTO joinV7Direct(@RequestParam(value = "authToken", required = !Utils.Debug) String authToken
//			,@RequestParam(value = "nickName", required = !Utils.Debug) String nickName
//			,@RequestParam(value = "gender", required = !Utils.Debug) String gender
//			,@RequestParam(value = "age", required = !Utils.Debug) int age
//			,@RequestParam(value = "conversation", required = !Utils.Debug) int conversation
//			,@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey
//			,@RequestParam(value = "thumnail", required = !Utils.Debug) String thumnail
//			,@RequestParam(value = "authEmail", required = !Utils.Debug) String authEmail
//			,@RequestParam(value = "osType", required = false) String osType
//			,@RequestParam(value = "identiCode", required = !Utils.Debug) String identiCode
//			,@RequestParam(value = "serviceType", required = !Utils.Debug) String serviceType
//			,@RequestParam(value = "countryCode", required = !Utils.Debug) String countryCode
//			,@RequestParam(value = "languageCode", required = !Utils.Debug) String languageCode
//			,@RequestParam(value = "agentId", required = !Utils.Debug) String agentId
//			,@RequestParam(value = "emailReceiveAgree", required = !Utils.Debug) int emailReceiveAgree
//			,@RequestParam(value = "loginType", required = !Utils.Debug) String loginType) {
//		LoginDTO dto = new LoginDTO();
//		dto.setUser_id(authToken);
//		dto.setUser_nick_name(nickName);
//		dto.setUser_gender(gender);
//		dto.setUser_age(age);
//		dto.setConversation_no(conversation);
//		dto.setUser_push_key(pushKey);
//		dto.setUser_thumnail(thumnail);
//		dto.setUser_email(authEmail);
//		dto.setOs_type(osType);
//		dto.setUser_identi_code(identiCode);
//		dto.setUser_service_type(serviceType);
//		dto.setUser_country_code(countryCode);
//		dto.setUser_language_code(languageCode);
//		dto.setAgent_id(agentId);
//		dto.setEmail_receive_agree(emailReceiveAgree);
//		dto.setLogin_type(loginType);
//		dto.setUser_part_time(0);
//
//		return userService.joinV7(dto);
//	}

	// 응답 값에 call_type 추가함, 여성 회원이 회원가입 하면 새내기가 아니라 일반 회원이 됨
	@RequestMapping(value = "/v7/join", params = {"authToken", "nickName", "gender", "age", "conversation", "pushKey", "thumnail", "authEmail", "osType", "identiCode", "serviceType","countryCode", "languageCode", "agentId", "emailReceiveAgree", "loginType"})
	@ResponseBody
	public LoginDTO joinV7(@RequestParam(value = "authToken", required = !Utils.Debug) String authToken
			,@RequestParam(value = "nickName", required = !Utils.Debug) String nickName
			,@RequestParam(value = "gender", required = !Utils.Debug) String gender
			,@RequestParam(value = "age", required = !Utils.Debug) int age
			,@RequestParam(value = "conversation", required = !Utils.Debug) int conversation
			,@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey
			,@RequestParam(value = "thumnail", required = !Utils.Debug) String thumnail
			,@RequestParam(value = "authEmail", required = !Utils.Debug) String authEmail
			,@RequestParam(value = "osType", required = false) String osType
			,@RequestParam(value = "identiCode", required = !Utils.Debug) String identiCode
			,@RequestParam(value = "serviceType", required = !Utils.Debug) String serviceType
			,@RequestParam(value = "countryCode", required = !Utils.Debug) String countryCode
			,@RequestParam(value = "languageCode", required = !Utils.Debug) String languageCode
			,@RequestParam(value = "agentId", required = !Utils.Debug) String agentId
			,@RequestParam(value = "emailReceiveAgree", required = !Utils.Debug) int emailReceiveAgree
			,@RequestParam(value = "loginType", required = !Utils.Debug) String loginType) {
		LoginDTO dto = new LoginDTO();
		dto.setUser_id(authToken);
		dto.setUser_nick_name(nickName);
		dto.setUser_gender(gender);
		dto.setUser_age(age);
		dto.setConversation_no(conversation);
		dto.setUser_push_key(pushKey);
		dto.setUser_thumnail(thumnail);
		dto.setUser_email(authEmail);
		dto.setOs_type(osType);
		dto.setUser_identi_code(identiCode);
		dto.setUser_service_type(serviceType);
		dto.setUser_country_code(countryCode);
		dto.setUser_language_code(languageCode);
		dto.setAgent_id(agentId);
		dto.setEmail_receive_agree(emailReceiveAgree);
		dto.setLogin_type(loginType);
		dto.setUser_part_time(0);

		return userService.joinV7(dto);
	}

	// 핸드폰 인증 여부 저장
	@RequestMapping(value = "/v8/join", params = {"authToken", "nickName", "gender", "age", "conversation", "pushKey", "thumnail", "authEmail", "osType", "identiCode", "serviceType","countryCode", "languageCode", "agentId", "emailReceiveAgree", "loginType", "certNo"})
	@ResponseBody
	public LoginDTO joinV8(@RequestParam(value = "authToken", required = !Utils.Debug) String authToken
			,@RequestParam(value = "nickName", required = !Utils.Debug) String nickName
			,@RequestParam(value = "gender", required = !Utils.Debug) String gender
			,@RequestParam(value = "age", required = !Utils.Debug) int age
			,@RequestParam(value = "conversation", required = !Utils.Debug) int conversation
			,@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey
			,@RequestParam(value = "thumnail", required = !Utils.Debug) String thumnail
			,@RequestParam(value = "authEmail", required = !Utils.Debug) String authEmail
			,@RequestParam(value = "osType", required = false) String osType
			,@RequestParam(value = "identiCode", required = !Utils.Debug) String identiCode
			,@RequestParam(value = "serviceType", required = !Utils.Debug) String serviceType
			,@RequestParam(value = "countryCode", required = !Utils.Debug) String countryCode
			,@RequestParam(value = "languageCode", required = !Utils.Debug) String languageCode
			,@RequestParam(value = "agentId", required = !Utils.Debug) String agentId
			,@RequestParam(value = "emailReceiveAgree", required = !Utils.Debug) int emailReceiveAgree
			,@RequestParam(value = "loginType", required = !Utils.Debug) String loginType
			,@RequestParam(value = "certNo", required = !Utils.Debug) int certNo) {
		LoginDTO dto = new LoginDTO();
		dto.setUser_id(authToken);
		dto.setUser_nick_name(nickName);
		dto.setUser_gender(gender);
		dto.setUser_age(age);
		dto.setConversation_no(conversation);
		dto.setUser_push_key(pushKey);
		dto.setUser_thumnail(thumnail);
		dto.setUser_email(authEmail);
		dto.setOs_type(osType);
		dto.setUser_identi_code(identiCode);
		dto.setUser_service_type(serviceType);
		dto.setUser_country_code(countryCode);
		dto.setUser_language_code(languageCode);
		dto.setAgent_id(agentId);
		dto.setEmail_receive_agree(emailReceiveAgree);
		dto.setLogin_type(loginType);
		dto.setUser_part_time(0);
		dto.setCert_no(certNo);
		return userService.joinV8(dto);
	}

	// 파트너 통한 가입시 인증 우회
	@RequestMapping(value = "/v8/join", params = {"authToken", "nickName", "gender", "age", "conversation", "pushKey", "thumnail", "authEmail", "osType", "identiCode", "serviceType","countryCode", "languageCode", "agentId", "emailReceiveAgree", "loginType", "adid"})
	@ResponseBody
	public LoginDTO joinV8(@RequestParam(value = "authToken", required = !Utils.Debug) String authToken
			,@RequestParam(value = "nickName", required = !Utils.Debug) String nickName
			,@RequestParam(value = "gender", required = !Utils.Debug) String gender
			,@RequestParam(value = "age", required = !Utils.Debug) int age
			,@RequestParam(value = "conversation", required = !Utils.Debug) int conversation
			,@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey
			,@RequestParam(value = "thumnail", required = !Utils.Debug) String thumnail
			,@RequestParam(value = "authEmail", required = !Utils.Debug) String authEmail
			,@RequestParam(value = "osType", required = false) String osType
			,@RequestParam(value = "identiCode", required = !Utils.Debug) String identiCode
			,@RequestParam(value = "serviceType", required = !Utils.Debug) String serviceType
			,@RequestParam(value = "countryCode", required = !Utils.Debug) String countryCode
			,@RequestParam(value = "languageCode", required = !Utils.Debug) String languageCode
			,@RequestParam(value = "agentId", required = !Utils.Debug) String agentId
			,@RequestParam(value = "emailReceiveAgree", required = !Utils.Debug) int emailReceiveAgree
			,@RequestParam(value = "loginType", required = !Utils.Debug) String loginType
			,@RequestParam(value = "adid", required = !Utils.Debug) String adid) {
		LoginDTO dto = new LoginDTO();
		dto.setUser_id(authToken);
		dto.setUser_nick_name(nickName);
		dto.setUser_gender(gender);
		dto.setUser_age(age);
		dto.setConversation_no(conversation);
		dto.setUser_push_key(pushKey);
		dto.setUser_thumnail(thumnail);
		dto.setUser_email(authEmail);
		dto.setOs_type(osType);
		dto.setUser_identi_code(identiCode);
		dto.setUser_service_type(serviceType);
		dto.setUser_country_code(countryCode);
		dto.setUser_language_code(languageCode);
		dto.setAgent_id(agentId);
		dto.setEmail_receive_agree(emailReceiveAgree);
		dto.setLogin_type(loginType);
		dto.setUser_part_time(0);
		dto.setUser_adid(adid);
		return userService.joinV8(dto);
	}

	// 핸드폰 인증 여부 저장
	@RequestMapping(value = "/v9/join", params = {"authToken", "nickName", "gender", "age", "conversation", "pushKey", "thumnail", "authEmail", "osType", "identiCode", "serviceType","countryCode", "languageCode", "agentId", "emailReceiveAgree", "loginType", "certNo"})
	@ResponseBody
	public LoginDTO joinV9(@RequestParam(value = "authToken", required = !Utils.Debug) String authToken
			,@RequestParam(value = "nickName", required = !Utils.Debug) String nickName
			,@RequestParam(value = "gender", required = !Utils.Debug) String gender
			,@RequestParam(value = "age", required = !Utils.Debug) int age
			,@RequestParam(value = "conversation", required = !Utils.Debug) int conversation
			,@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey
			,@RequestParam(value = "thumnail", required = !Utils.Debug) String thumnail
			,@RequestParam(value = "authEmail", required = !Utils.Debug) String authEmail
			,@RequestParam(value = "osType", required = false) String osType
			,@RequestParam(value = "identiCode", required = !Utils.Debug) String identiCode
			,@RequestParam(value = "serviceType", required = !Utils.Debug) String serviceType
			,@RequestParam(value = "countryCode", required = !Utils.Debug) String countryCode
			,@RequestParam(value = "languageCode", required = !Utils.Debug) String languageCode
			,@RequestParam(value = "agentId", required = !Utils.Debug) String agentId
			,@RequestParam(value = "emailReceiveAgree", required = !Utils.Debug) int emailReceiveAgree
			,@RequestParam(value = "loginType", required = !Utils.Debug) String loginType
			,@RequestParam(value = "certNo", required = !Utils.Debug) int certNo) {


		LoginDTO dto = new LoginDTO();
		dto.setUser_id(authToken);
		dto.setUser_nick_name(nickName);
		dto.setUser_gender(gender);
		dto.setUser_age(age);
		dto.setConversation_no(conversation);
		dto.setUser_push_key(pushKey);
		dto.setUser_thumnail(thumnail);
		dto.setUser_email(authEmail);
		dto.setOs_type(osType);
		dto.setUser_identi_code(identiCode);
		dto.setUser_service_type(serviceType);
		dto.setUser_country_code(countryCode);
		dto.setUser_language_code(languageCode);
		dto.setAgent_id(agentId);
		dto.setEmail_receive_agree(emailReceiveAgree);
		dto.setLogin_type(loginType);
		dto.setUser_part_time(0);
		dto.setCert_no(certNo);
		return userService.joinV9(dto);
	}

	// 파트너 통한 가입시 인증 우회
	@RequestMapping(value = "/v9/join", params = {"authToken", "nickName", "gender", "age", "conversation", "pushKey", "thumnail", "authEmail", "osType", "identiCode", "serviceType","countryCode", "languageCode", "agentId", "emailReceiveAgree", "loginType", "adid"})
	@ResponseBody
	public LoginDTO joinV9(@RequestParam(value = "authToken", required = !Utils.Debug) String authToken
			,@RequestParam(value = "nickName", required = !Utils.Debug) String nickName
			,@RequestParam(value = "gender", required = !Utils.Debug) String gender
			,@RequestParam(value = "age", required = !Utils.Debug) int age
			,@RequestParam(value = "conversation", required = !Utils.Debug) int conversation
			,@RequestParam(value = "pushKey", required = !Utils.Debug) String pushKey
			,@RequestParam(value = "thumnail", required = !Utils.Debug) String thumnail
			,@RequestParam(value = "authEmail", required = !Utils.Debug) String authEmail
			,@RequestParam(value = "osType", required = false) String osType
			,@RequestParam(value = "identiCode", required = !Utils.Debug) String identiCode
			,@RequestParam(value = "serviceType", required = !Utils.Debug) String serviceType
			,@RequestParam(value = "countryCode", required = !Utils.Debug) String countryCode
			,@RequestParam(value = "languageCode", required = !Utils.Debug) String languageCode
			,@RequestParam(value = "agentId", required = !Utils.Debug) String agentId
			,@RequestParam(value = "emailReceiveAgree", required = !Utils.Debug) int emailReceiveAgree
			,@RequestParam(value = "loginType", required = !Utils.Debug) String loginType
			,@RequestParam(value = "adid", required = !Utils.Debug) String adid) {
		LoginDTO dto = new LoginDTO();
		dto.setUser_id(authToken);
		dto.setUser_nick_name(nickName);
		dto.setUser_gender(gender);
		dto.setUser_age(age);
		dto.setConversation_no(conversation);
		dto.setUser_push_key(pushKey);
		dto.setUser_thumnail(thumnail);
		dto.setUser_email(authEmail);
		dto.setOs_type(osType);
		dto.setUser_identi_code(identiCode);
		dto.setUser_service_type(serviceType);
		dto.setUser_country_code(countryCode);
		dto.setUser_language_code(languageCode);
		dto.setAgent_id(agentId);
		dto.setEmail_receive_agree(emailReceiveAgree);
		dto.setLogin_type(loginType);
		dto.setUser_part_time(0);
		dto.setUser_adid(adid);
		return userService.joinV9(dto);
	}


	// 아이폰 사용자인 경우 VOIP토큰 올려보내기
	@RequestMapping(value = "/updateVoipKey", params = {"voipKey", "user_no"})
	@ResponseBody
	public UserInfoDTO updateVoipKey(@RequestParam(value = "voipKey", required = !Utils.Debug) String voipKey
			,@RequestParam(value = "user_no", required = !Utils.Debug)  int user_no) {
		LoginDTO dto = new LoginDTO();
		dto.setNo(user_no);
		dto.setUser_voip_key(voipKey);
		return userService.updateVoipKey(dto);
	}

	// 기존 회원 핸드폰 인증 여부 수정하기
	@RequestMapping(value = "/updateCertification", params = {"certNo", "userNo"})
	@ResponseBody
	public UserInfoDTO updateCertification(@RequestParam(value = "certNo", required = !Utils.Debug) int certNo
			,@RequestParam(value = "userNo", required = !Utils.Debug) int userNo) {
		LoginDTO dto = new LoginDTO();
		dto.setNo(userNo);
		dto.setCert_no(certNo);
		return userService.updateCertification(dto);
	}

	// 에이전트 아이디 유효한지 확인하기
	@RequestMapping(value = "/checkAgentId", params = {"agentId"})
	@ResponseBody
	public DefaultDTO checkAgentId(@RequestParam(value = "agentId", required = !Utils.Debug) String agentId) {
		return userService.checkAgentId(agentId);
	}


	//차단리스트
	@RequestMapping(value="/blacklist")
	@ResponseBody
	public MainBlackListDTO MyBlackList(@RequestParam(value = "user_no", required = !Utils.Debug)  int user_no) {
		return userService.myBlackList(user_no);
	}
	
	//차단하기
	@RequestMapping(value="/addBlackList")
	@ResponseBody
	public  BlackDTO insertBlackList (@RequestParam(value = "user_no", required = !Utils.Debug) int user_no, 
			@RequestParam(value = "black_user_no", required = !Utils.Debug) int black_user_no) {
		
		BlackListDTO dto = new BlackListDTO();
		dto.setBlack_user_no(black_user_no);
		dto.setUser_no(user_no);
		
		return userService.insertBlackList(dto);
	}
	
	//좋아요 추가
	@RequestMapping(value="/addLike")
	@ResponseBody
	public  LikeResultDTO insertLike(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no, 
			@RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no) {
		LikeDTO dto = new LikeDTO();
		dto.setUser_no(user_no);
		dto.setTo_user_no(to_user_no);
		
		return userService.insertLike(dto);
	}

	//좋아요 추가
	@RequestMapping(value="/v2/addLike")
	@ResponseBody
	public  LikeResultDTO insertLikeV2(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
									 @RequestParam(value = "to_user_no", required = !Utils.Debug) int to_user_no) {
		LikeDTO dto = new LikeDTO();
		dto.setUser_no(user_no);
		dto.setTo_user_no(to_user_no);

		return userService.insertLikeV2(dto);
	}

	@RequestMapping(value="/getMyPoint")
	@ResponseBody
	public UserPointDTO insertLike(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no) {		
		return userService.getMyPoint(user_no);
	}
	

	@RequestMapping(value="/setMyAlarm")
	@ResponseBody
	public SetAlarmDTO setMyAleram(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
			@RequestParam(value = "alarm_status", required = !Utils.Debug) int alarm_status) {		
		return userService.setMyAlaram(user_no, alarm_status);
	}
	
	
	@RequestMapping(value="/setUserSetting")
	@ResponseBody
	public SetAlarmDTO setUserSetting(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
			@RequestParam(value = "set_type", required = !Utils.Debug) int set_type,
			@RequestParam(value = "set_status", required = !Utils.Debug) int set_status) {		
		return userService.setUserSetting(user_no, set_type, set_status);
	}

//	// 대화주제 리스트
//	@RequestMapping("/conversationlist")
//	@ResponseBody
//	public ConversationListDTO conversationList() {
//		return userService.conversationList();
//	}

	// 대화주제 리스트
	@RequestMapping("/v2/conversationlist")
	@ResponseBody
	public ConversationListDTO conversationListV2(@RequestParam(value = "language_code", required = !Utils.Debug) String language_code) {
		return userService.conversationListV2(language_code);
	}

	// 대화주제 리스트
	@RequestMapping("/v3/conversationlist")
	@ResponseBody
	public ConversationListDTO conversationListV3(@RequestParam(value = "language_code", required = !Utils.Debug) String language_code) {
		return userService.conversationListV3(language_code);
	}


	// 출석 체크
	@RequestMapping("/attendanceChk")
	@ResponseBody
	public UserPointDTO attendanceChk(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no) {
		return userService.attendanceChk(user_no);
	}
	
	@RequestMapping("/updateProfile")
	@ResponseBody
	public UserModifyDTO updateProfile(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
			@RequestParam(value = "user_nick_name", required = !Utils.Debug) String user_nick_name,
			@RequestParam(value = "user_age", required = !Utils.Debug) int user_age,
			@RequestParam(value = "conversation_no", required = !Utils.Debug) int conversation_no,
			@RequestParam(value = "user_thumnail", required = !Utils.Debug) String user_thumnail) {

		UserProfileUpdateDTO dto = new UserProfileUpdateDTO();
		dto.setUser_no(user_no);
		dto.setConversation_no(conversation_no);
		dto.setUser_age(user_age);
		dto.setUser_nick_name(user_nick_name);
		dto.setUser_thumnail(user_thumnail);
		dto.setAdmin_appro(0);

		return userService.updateProfile(dto);
	}

	// 대화 주제 기존에서 선택한 경우
	@RequestMapping(value ="v2/updateProfile", params = {"user_no", "user_nick_name", "user_age", "call_type", "conversation_no"})
	@ResponseBody
	public UserModifyDTO updateProfileV2(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
									   @RequestParam(value = "user_nick_name", required = !Utils.Debug) String user_nick_name,
									   @RequestParam(value = "user_age", required = !Utils.Debug) int user_age,
									   @RequestParam(value = "call_type", required = !Utils.Debug) int call_type,
									   @RequestParam(value = "conversation_no", required = !Utils.Debug) int conversation_no) {

		UserProfileUpdateDTO dto = new UserProfileUpdateDTO();
		dto.setUser_no(user_no);
		dto.setConversation_no(conversation_no);
		dto.setUser_age(user_age);
		dto.setUser_nick_name(user_nick_name);
		dto.setCall_type(call_type);
		dto.setAdmin_appro(0);

		return userService.updateProfileV2(dto);
	}

	// 대화 주제를 사용자가 직접 입력한 경우
	@RequestMapping(value ="v2/updateProfile", params = {"user_no", "user_nick_name", "user_age", "call_type", "conversation_no", "conversation_txt"})
	@ResponseBody
	public UserModifyDTO updateProfileV2(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
										 @RequestParam(value = "user_nick_name", required = !Utils.Debug) String user_nick_name,
										 @RequestParam(value = "user_age", required = !Utils.Debug) int user_age,
										 @RequestParam(value = "call_type", required = !Utils.Debug) int call_type,
										 @RequestParam(value = "conversation_no", required = !Utils.Debug) int conversation_no,
										 @RequestParam(value = "conversation_txt", required = !Utils.Debug) String conversation_txt) {

		UserProfileUpdateDTO dto = new UserProfileUpdateDTO();
		dto.setUser_no(user_no);
		dto.setConversation_no(conversation_no);
		dto.setUser_age(user_age);
		dto.setUser_nick_name(user_nick_name);
		dto.setCall_type(call_type);
		dto.setAdmin_appro(0);
		dto.setConversation_txt(conversation_txt);
		return userService.updateProfileV2(dto);
	}

	// 대화 주제 기존에서 선택한 경우
	@RequestMapping(value ="v3/updateProfile", params = {"user_no", "user_nick_name", "user_age", "call_type", "conversation_no"})
	@ResponseBody
	public UserModifyDTO updateProfileV3(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
									   @RequestParam(value = "user_nick_name", required = !Utils.Debug) String user_nick_name,
									   @RequestParam(value = "user_age", required = !Utils.Debug) int user_age,
									   @RequestParam(value = "call_type", required = !Utils.Debug) int call_type,
									   @RequestParam(value = "conversation_no", required = !Utils.Debug) int conversation_no) {

		UserProfileUpdateDTO dto = new UserProfileUpdateDTO();
		dto.setUser_no(user_no);
		dto.setConversation_no(conversation_no);
		dto.setUser_age(user_age);
		dto.setUser_nick_name(user_nick_name);
		dto.setCall_type(call_type);
		dto.setAdmin_appro(0);

		return userService.updateProfileV3(dto);
	}

	// 대화 주제를 사용자가 직접 입력한 경우
	@RequestMapping(value ="v3/updateProfile", params = {"user_no", "user_nick_name", "user_age", "call_type", "conversation_no", "conversation_txt"})
	@ResponseBody
	public UserModifyDTO updateProfileV3(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
										 @RequestParam(value = "user_nick_name", required = !Utils.Debug) String user_nick_name,
										 @RequestParam(value = "user_age", required = !Utils.Debug) int user_age,
										 @RequestParam(value = "call_type", required = !Utils.Debug) int call_type,
										 @RequestParam(value = "conversation_no", required = !Utils.Debug) int conversation_no,
										 @RequestParam(value = "conversation_txt", required = !Utils.Debug) String conversation_txt) {

		UserProfileUpdateDTO dto = new UserProfileUpdateDTO();
		dto.setUser_no(user_no);
		dto.setConversation_no(conversation_no);
		dto.setUser_age(user_age);
		dto.setUser_nick_name(user_nick_name);
		dto.setCall_type(call_type);
		dto.setAdmin_appro(0);
		dto.setConversation_txt(conversation_txt);
		return userService.updateProfileV3(dto);
	}

	@RequestMapping("/userRankList")
	@ResponseBody
	public UserRenkingListDTO selectUserRankingList(@RequestParam(value = "user_no", required = !Utils.Debug)  int user_no,
			@RequestParam(value = "order", required = !Utils.Debug) String order){
		return userService.selectUserRankingList(user_no, order);
	}
	
	@RequestMapping("/userRankListN")
	@ResponseBody
	public UserRenkingListDTO selectUserRankingList(@RequestParam(value = "user_no", required = !Utils.Debug)  int user_no,
			@RequestParam(value = "gender", required = !Utils.Debug) String gender,
			@RequestParam(value = "order", required = !Utils.Debug) String order){
		return userService.selectUserRankingList(user_no, gender, order);
	}

	@RequestMapping("/userRankListMC")
	@ResponseBody
	public UserRenkingListDTO selectUserRankingListMC(@RequestParam(value = "user_no", required = !Utils.Debug)  int user_no,
													@RequestParam(value = "gender", required = !Utils.Debug) String gender,
													@RequestParam(value = "order", required = !Utils.Debug) String order){
		return userService.selectUserRankingListMC(user_no, gender, order);
	}
		
	@RequestMapping("/sendReport")
	@ResponseBody
	public DefaultDTO sendReport(@RequestParam(value = "user_no", required = !Utils.Debug)  int user_no,
			@RequestParam(value = "to_user_no", required = !Utils.Debug)  int to_user_no,
			@RequestParam(value = "report_type", required = !Utils.Debug)  int report_type,
			@RequestParam(value = "report_content", required = !Utils.Debug) String report_content){
		return userService.sendReport(user_no, to_user_no, report_type, report_content);
	}
	
	@RequestMapping("/getUserInfo")
	@ResponseBody
	public UserInfoDTO getUserInfo(@RequestParam(value = "user_no", required = !Utils.Debug)  int user_no){
		UserInfoDTO userInfoDTO = userMapper.getUserInfo(user_no);
		userInfoDTO.setPoint_per_second(Utils.MC_POINT_PER_SECONDS);
		userInfoDTO.setVoice_point_per_second(Utils.VOICE_POINT_PER_SECONDS);
		userInfoDTO.setVoice_cash_per_second(Utils.VOICE_CASH_PER_SECONDS);
		int cash_per_second = Utils.MC_CASH_PER_SECONDS;
		if (userInfoDTO.getUser_gender().equals("여성") && userInfoDTO.getUser_part_time() ==1
				&& !userInfoDTO.getAgent_id().equals("") &&  !userInfoDTO.getAgent_id().equals("NONE")){
			AgentDTO agentDTO = userMapper.getAgentCashInfo(userInfoDTO.getAgent_id());
			if (agentDTO != null){
				if (agentDTO.getCash_per_second() >0){
					userInfoDTO.setIs_refund_visible(agentDTO.getIs_refund_visible());
					cash_per_second = agentDTO.getCash_per_second();
				}

			}
		}
		userInfoDTO.setCash_per_second(cash_per_second);
		return userInfoDTO;
	}

	@RequestMapping("/getUserInfoMC")
	@ResponseBody
	public UserInfoDTO getUserInfoMC(@RequestParam(value = "user_no", required = !Utils.Debug)  int user_no){
		UserInfoDTO userInfoDTO = userMapper.getUserInfo(user_no);
		userInfoDTO.setPoint_per_second(Utils.MC_POINT_PER_SECONDS);
		userInfoDTO.setVoice_point_per_second(Utils.VOICE_POINT_PER_SECONDS);
		userInfoDTO.setVoice_cash_per_second(Utils.VOICE_CASH_PER_SECONDS);
		int cash_per_second = Utils.MC_CASH_PER_SECONDS;
		if (userInfoDTO.getUser_gender().equals("여성") && userInfoDTO.getUser_part_time() ==1
				&& !userInfoDTO.getAgent_id().equals("") &&  !userInfoDTO.getAgent_id().equals("NONE")){
			AgentDTO agentDTO = userMapper.getAgentCashInfo(userInfoDTO.getAgent_id());
			if (agentDTO != null){
				if (agentDTO.getCash_per_second() >0){
					userInfoDTO.setIs_refund_visible(agentDTO.getIs_refund_visible());
					cash_per_second = agentDTO.getCash_per_second();
				}

			}
		}
		userInfoDTO.setCash_per_second(cash_per_second);
		return userInfoDTO;
	}

	@RequestMapping("/v2/getUserInfo")
	@ResponseBody
	public UserInfoDTO getUserInfoV2(@RequestParam(value = "user_no", required = !Utils.Debug)  int user_no){
		UserInfoDTO userInfoDTO = userMapper.getUserInfo(user_no);
		userInfoDTO.setPoint_per_second(Utils.NMC_POINT_PER_SECONDS);
		userInfoDTO.setVoice_point_per_second(Utils.VOICE_POINT_PER_SECONDS);
		userInfoDTO.setVoice_cash_per_second(Utils.VOICE_CASH_PER_SECONDS);
		userInfoDTO.setPresent_to_cash_rate(Utils.NMC_PRESENT_TO_CASH_RATE);
		int cash_per_second = Utils.NMC_CASH_PER_SECONDS;
		if (userInfoDTO.getUser_gender().equals("여성") && userInfoDTO.getUser_part_time() ==1
				&& !userInfoDTO.getAgent_id().equals("") &&  !userInfoDTO.getAgent_id().equals("NONE")){
			AgentDTO agentDTO = userMapper.getAgentCashInfo(userInfoDTO.getAgent_id());
			if (agentDTO != null){
				if (agentDTO.getCash_per_second() >0){
					userInfoDTO.setIs_refund_visible(agentDTO.getIs_refund_visible());
					cash_per_second = agentDTO.getCash_per_second();
				}

			}
		}
		userInfoDTO.setCash_per_second(cash_per_second);
		return userInfoDTO;
	}
	
	@RequestMapping("/userOut")
	@ResponseBody
	public DefaultDTO updateUserOut(@RequestParam(value = "user_no", required = !Utils.Debug)  int user_no){
		return userService.updateUserOut(user_no);
	}
	
	
	//환급신청
	@RequestMapping("applyRefund")
	@ResponseBody
	public DefaultDTO applyRefund(@RequestParam(value = "user_no", required = !Utils.Debug)  int user_no
			,@RequestParam(value = "user_name", required = !Utils.Debug)  String user_name
			,@RequestParam(value = "user_mobile", required = !Utils.Debug)  String user_mobile
			,@RequestParam(value = "user_jubun", required = !Utils.Debug)  String user_jubun
			,@RequestParam(value = "user_co_paper", required = !Utils.Debug)  String user_co_paper
			,@RequestParam(value = "user_bank", required = !Utils.Debug)  String user_bank
			,@RequestParam(value = "user_bank_paper_copy", required = !Utils.Debug)  String user_bank_paper_copy
			,@RequestParam(value = "user_bank_num", required = !Utils.Debug)  String user_bank_num
			,@RequestParam(value = "use_cash", required = !Utils.Debug)  int use_cash){
		UserRefundsUpdateDTO dto = new UserRefundsUpdateDTO();
		dto.setNo(user_no);
		dto.setUser_name(user_name);
		dto.setUser_mobile(user_mobile);
		dto.setUser_jubun(user_jubun);
		dto.setUser_co_paper(user_co_paper);
		dto.setUser_bank(user_bank);
		dto.setUser_bank_num(user_bank_num);
		dto.setUser_bank_paper_copy(user_bank_paper_copy);
		dto.setUser_use_cash(use_cash);

		return userService.updateUserRefundsInfo(dto);
	}
	
	//환급내역 리스트
	@RequestMapping("refundList")
	@ResponseBody
	public DefaultDTO refundList(@RequestParam(value = "user_no", required = !Utils.Debug)  int user_no){		
		return userService.selectUserRefundsHistory(user_no);
	}
	
	//환급신청내역 저장하기
	@RequestMapping("saveRefund")
	@ResponseBody
	public DefaultDTO saveRefund(@RequestParam(value = "user_no", required = !Utils.Debug)  int user_no
			,@RequestParam(value = "user_name", required = !Utils.Debug)  String user_name
			,@RequestParam(value = "user_mobile", required = !Utils.Debug)  String user_mobile
			,@RequestParam(value = "user_jubun", required = !Utils.Debug)  String user_jubun
			,@RequestParam(value = "user_co_paper", required = !Utils.Debug)  String user_co_paper
			,@RequestParam(value = "user_bank", required = !Utils.Debug)  String user_bank
			,@RequestParam(value = "user_bank_paper_copy", required = !Utils.Debug)  String user_bank_paper_copy
			,@RequestParam(value = "user_bank_num", required = !Utils.Debug)  String user_bank_num){
		
		UserRefundsUpdateDTO dto = new UserRefundsUpdateDTO();
		dto.setNo(user_no);
		dto.setUser_name(user_name);
		dto.setUser_mobile(user_mobile);
		dto.setUser_jubun(user_jubun);
		dto.setUser_co_paper(user_co_paper);
		dto.setUser_bank(user_bank);
		dto.setUser_bank_num(user_bank_num);
		dto.setUser_bank_paper_copy(user_bank_paper_copy);

		return userService.updateUserRefundsInfoSave(dto);
	}
	
	//환급정보 불러오기
	@RequestMapping("refundView")
	@ResponseBody
	public UserRefundsViewDTO refundView(@RequestParam(value = "user_no", required = !Utils.Debug)  int user_no){
		return userService.selectUserRefunds(user_no);
	}
	
	//로그 아웃 후 푸쉬 키 제거
	@RequestMapping("logOut")
	@ResponseBody
	public DefaultDTO logOut(@RequestParam(value = "user_no", required = !Utils.Debug)  int user_no){
		return userService.logOut(user_no);
	}

	//새내기 활동 시간
	@RequestMapping(value = "/setNewbieTime", params = {"user_no", "user_newbie_time"})
	@ResponseBody
	public UserModifyDTO setNewbieTime(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no
			,@RequestParam(value = "user_newbie_time", required = !Utils.Debug) String user_newbie_time) {
		UserProfileUpdateDTO dto = new UserProfileUpdateDTO();
		dto.setNo(user_no);
		dto.setUser_newbie_time(user_newbie_time);

		return userService.updateUserNewbieTime(dto);
	}

	@RequestMapping("/setNewbieLevelUp")
	@ResponseBody
	public DefaultDTO setNewbieLevelUp(@RequestParam(value = "user_no", required = !Utils.Debug)  int user_no){
		NewbieLevelUpDTO dto = new NewbieLevelUpDTO();
		dto.setUser_no(user_no);
		return userService.insertNewbieLevelUp(dto);
	}

	//환급신청
	@RequestMapping("applyExchangeHeart")
	@ResponseBody
	public DefaultDTO applyExchangeHeart(@RequestParam(value = "user_no", required = !Utils.Debug)  int user_no
			,@RequestParam(value = "user_name", required = !Utils.Debug)  String user_name
			,@RequestParam(value = "user_mobile", required = !Utils.Debug)  String user_mobile
			,@RequestParam(value = "exchange_item_no", required = !Utils.Debug)  int exchange_item_no){
		UserExchangeHeartDTO dto = new UserExchangeHeartDTO();
		dto.setUser_no(user_no);
		dto.setUser_name(user_name);
		dto.setUser_mobile(user_mobile);
		dto.setExchange_item_no(exchange_item_no);

		return userService.insertExchangeHeart(dto);
	}

	//파트너사이트 통한 가입여부 확인
	@RequestMapping("checkPartnerInstall")
	@ResponseBody
	public DefaultDTO checkPartnerInstall(@RequestParam(value = "adid", required = !Utils.Debug)  String adid){
		return userService.checkPartnerInstall(adid);
	}

	//닉네임 사용여부 확인
	@RequestMapping("checkNicknameUsed")
	@ResponseBody
	public DefaultDTO checkNicknameUsed(@RequestParam(value = "user_nick_name", required = !Utils.Debug)  String user_nick_name){
		return userService.checkNicknameUsed(user_nick_name);
	}



}