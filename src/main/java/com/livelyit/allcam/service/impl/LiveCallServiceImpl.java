package com.livelyit.allcam.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.livelyit.allcam.dto.*;
import com.livelyit.allcam.mapper.ChatMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.mapper.LiveCallMapper;
import com.livelyit.allcam.mapper.RecommendUserMapper;
import com.livelyit.allcam.mapper.UserMapper;
import com.livelyit.allcam.service.ChatService;
import com.livelyit.allcam.service.LiveCallService;
import com.livelyit.allcam.service.PushService;
import com.livelyit.allcam.service.UserService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.livelyit.allcam.common.Utils.API_LOG_PATH;
import static com.livelyit.allcam.common.Utils.checkEventDay;

@Service
public class LiveCallServiceImpl implements LiveCallService {
	@Autowired
	LiveCallMapper liveCallMapper;

	@Autowired
	RecommendUserMapper recommendUserMapper;

	@Autowired
	UserMapper userMapper;

	@Autowired
	ChatMapper chatMapper;

	@Autowired
	PushService pushService;

	@Autowired
	ChatService chatService;

	@Autowired
	UserService userService;

//	@Override
//	public GetWatingDTO insertWaiting(MainLiveCallDTO dto) {
//		GetWatingDTO resultDto = new GetWatingDTO();
//
//		liveCallMapper.deleteWaiting(dto.getUser_no());
//		int result = liveCallMapper.insertWaiting(dto.getUser_no());
//
//		if(result == 1) {
//			resultDto.setResult(Utils.SUCCESS);
//
//			ArrayList<WatingUserDTO> watingList = liveCallMapper.getWatingUser(dto);
//			int i = 0;
//			for (WatingUserDTO watingUserDTO : watingList){
//
//				// 대기 회원이 남자인 경우 해당 국기 이미지 URL을 넣지 않는다.
//				if (watingUserDTO.getUser_gender().equals("남성")){
//					break;
//				}
//
//				// 나라 코드 값이 null 인 경우
//				if (watingUserDTO.getUser_country_code() ==null){
//					// 한국 나라 설정을 해준다.
//					watingUserDTO.setUser_country_code("KR");
//					watingUserDTO.setUser_language_code("ko");
//					watingUserDTO.setUser_country_code("https://allcam.co.kr/images/country-image/KR.png");
//
//				}else{
//
//					// 나라에 따라 국기 이미지를 넣어준다.
//					switch (watingUserDTO.getUser_country_code()){
//						case "KR":
//							watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/KR.png");
//							break;
//						case "CN":
//							watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/CN.png");
//							break;
//						case "HK":
//							watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/HK.png");
//							break;
//						case "JP":
//							watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/JP.png");
//							break;
//						case "US":
//							watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/US.png");
//							break;
//						case "VN":
//							watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/VN.png");
//							break;
//						default:
//							watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/KR.png");
//					}
//				}
//
//				// 대기자 리스트에 넣어준다.
//				watingList.set(i,watingUserDTO);
//				i ++;
//			}
//
//			ArrayList<RecommendUserDTO> recommendList = recommendUserMapper.selectRecommendList(dto.getUser_no());
//			System.out.println("rec 길이"+recommendList.size());
//			i = 0;
//			for (RecommendUserDTO recommendUserDTO : recommendList){
//
//				// 대기 회원이 남자인 경우 해당 국기 이미지 URL을 넣지 않는다.
//				if (recommendUserDTO.getUser_gender().equals("남성")){
//					break;
//				}
//
//				// 나라 코드 값이 null 인 경우
//				if (recommendUserDTO.getUser_country_code() ==null){
//					// 한국 나라 설정을 해준다.
//					recommendUserDTO.setUser_country_code("KR");
//					recommendUserDTO.setUser_language_code("ko");
//					recommendUserDTO.setUser_country_code("https://allcam.co.kr/images/country-image/KR.png");
//				}else{
//
//					// 나라에 따라 국기 이미지를 넣어준다.
//					switch (recommendUserDTO.getUser_country_code()){
//						case "KR":
//							recommendUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/KR.png");
//							break;
//						case "CN":
//							recommendUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/CN.png");
//							break;
//						case "HK":
//							recommendUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/HK.png");
//							break;
//						case "JP":
//							recommendUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/JP.png");
//							break;
//						case "US":
//							recommendUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/US.png");
//							break;
//						case "VN":
//							recommendUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/VN.png");
//							break;
//						default:
//							recommendUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/KR.png");
//					}
//				}
//
//				recommendList.set(i, recommendUserDTO);
//				i ++;
//
//			}
//			resultDto.setWatingList(watingList);
//			resultDto.setRecommendList(recommendList);
//		}
//
//		return resultDto;
//	}
//
//	@Override
//	public GetWatingDTO insertWaitingV2(MainLiveCallDTO dto) {
//		GetWatingDTO resultDto = new GetWatingDTO();
//
//		liveCallMapper.deleteWaiting(dto.getUser_no());
//		int result = liveCallMapper.insertWaiting(dto.getUser_no());
//
//		if(result == 1) {
//			resultDto.setResult(Utils.SUCCESS);
//
//			ArrayList<WatingUserDTO> watingList = liveCallMapper.getWatingUserV2(dto);
//			int i = 0;
//			for (WatingUserDTO watingUserDTO : watingList){
//
//				// 대기 회원이 남자인 경우 해당 국기 이미지 URL을 넣지 않는다.
//				if (watingUserDTO.getUser_gender().equals("남성")){
//					break;
//				}
//
//				// 나라 코드 값이 null 인 경우
//				if (watingUserDTO.getUser_country_code() ==null){
//					// 한국 나라 설정을 해준다.
//					watingUserDTO.setUser_country_code("KR");
//					watingUserDTO.setUser_language_code("ko");
//					watingUserDTO.setUser_country_code("https://allcam.co.kr/images/country-image/KR.png");
//
//				}else{
//
//					// 나라에 따라 국기 이미지를 넣어준다.
//					switch (watingUserDTO.getUser_country_code()){
//						case "KR":
//							watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/KR.png");
//							break;
//						case "PH":
//							watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/PH.png");
//							break;
//						case "CN":
//							watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/CN.png");
//							break;
//						case "HK":
//							watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/HK.png");
//							break;
//						case "JP":
//							watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/JP.png");
//							break;
//						case "US":
//							watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/US.png");
//							break;
//						case "VN":
//							watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/VN.png");
//							break;
//						default:
//							watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/KR.png");
//					}
//				}
//
//				// 대기자 리스트에 넣어준다.
//				watingList.set(i,watingUserDTO);
//				i ++;
//			}
//
//			ArrayList<RecommendUserDTO> recommendList = recommendUserMapper.selectRecommendList(dto.getUser_no());
//			System.out.println("rec 길이"+recommendList.size());
//			i = 0;
//			for (RecommendUserDTO recommendUserDTO : recommendList){
//
//				// 대기 회원이 남자인 경우 해당 국기 이미지 URL을 넣지 않는다.
//				if (recommendUserDTO.getUser_gender().equals("남성")){
//					break;
//				}
//
//				// 나라 코드 값이 null 인 경우
//				if (recommendUserDTO.getUser_country_code() ==null){
//					// 한국 나라 설정을 해준다.
//					recommendUserDTO.setUser_country_code("KR");
//					recommendUserDTO.setUser_language_code("ko");
//					recommendUserDTO.setUser_country_code("https://allcam.co.kr/images/country-image/KR.png");
//					continue;
//				}else{
//
//					// 나라에 따라 국기 이미지를 넣어준다.
//					switch (recommendUserDTO.getUser_country_code()){
//						case "KR":
//							recommendUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/KR.png");
//							break;
//						case "PH":
//							recommendUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/PH.png");
//							break;
//						case "CN":
//							recommendUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/CN.png");
//							break;
//						case "HK":
//							recommendUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/HK.png");
//							break;
//						case "JP":
//							recommendUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/JP.png");
//							break;
//						case "US":
//							recommendUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/US.png");
//							break;
//						case "VN":
//							recommendUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/VN.png");
//							break;
//						default:
//							recommendUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/KR.png");
//					}
//				}
//
//				recommendList.set(i, recommendUserDTO);
//				i ++;
//
//			}
//			resultDto.setWatingList(watingList);
//			resultDto.setRecommendList(recommendList);
//		}
//
//		return resultDto;
//	}
//
//
//	@Override
//	public GetWatingDTO insertWaitingV3(MainLiveCallDTO dto) {
//		GetWatingDTO resultDto = new GetWatingDTO();
//
//		liveCallMapper.deleteWaiting(dto.getUser_no());
//		int result = liveCallMapper.insertWaiting(dto.getUser_no());
//
//		if(result == 1) {
//			resultDto.setResult(Utils.SUCCESS);
//
//			ArrayList<WatingUserDTO> watingList = liveCallMapper.getWatingUserV2(dto);
//			int i = 0;
//			for (WatingUserDTO watingUserDTO : watingList){
//
//				// 대기 회원이 남자인 경우 해당 국기 이미지 URL을 넣지 않는다.
//				if (watingUserDTO.getUser_gender().equals("남성")){
//					break;
//				}
//
//				// 나라 코드 값이 null 인 경우
//				if (watingUserDTO.getUser_country_code() ==null){
//					// 한국 나라 설정을 해준다.
//					watingUserDTO.setUser_country_code("KR");
//					watingUserDTO.setUser_language_code("ko");
//					watingUserDTO.setUser_country_code("https://allcam.co.kr/images/country-image/KR.png");
//				}else{
//
//					// 나라에 따라 국기 이미지를 넣어준다.
//					switch (watingUserDTO.getUser_country_code()){
//						case "KR":
//							watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/KR.png");
//							break;
//						case "PH":
//							watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/PH.png");
//							break;
//						case "CN":
//							watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/CN.png");
//							break;
//						case "HK":
//							watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/HK.png");
//							break;
//						case "JP":
//							watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/JP.png");
//							break;
//						case "US":
//							watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/US.png");
//							break;
//						case "VN":
//							watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/VN.png");
//							break;
//						default:
//							watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/KR.png");
//					}
//				}
//
//				// 대기자 리스트에 넣어준다.
//				watingList.set(i,watingUserDTO);
//				i ++;
//			}
//
//			// 사용자 성별을 가져온다.
//			String user_gender = userMapper.selectUserGender(dto.getUser_no());
//			RandomImageDTO randomImageDTO = new RandomImageDTO();
//			randomImageDTO.setTarget_gender(user_gender);
//			// 랜덤 이미지 리스트를 가져온다.
//			ArrayList<RandomImageDTO> imageDTOArrayList = liveCallMapper.getRandomImage(randomImageDTO);
//
//			if (imageDTOArrayList.size()>0)
//				resultDto.setRandomImageList(imageDTOArrayList);
//			resultDto.setWatingList(watingList);
//		}
//
//		return resultDto;
//	}
//
//	@Override
//	public GetWatingDTO insertWaitingV4(MainLiveCallDTO dto) {
//		GetWatingDTO resultDto = new GetWatingDTO();
//
//		liveCallMapper.deleteWaiting(dto.getUser_no());
//		int result = liveCallMapper.insertWaiting(dto.getUser_no());
//		UserInfoDTO userInfoDTO = userMapper.getUserInfo(dto.getUser_no());
//
//		// 나라 정보가 없으면 기본값 넣어 주기
//		if (userInfoDTO.getUser_country_code() == null){
//			dto.setUser_country_code("KR");
//			dto.setUser_language_code("ko");
//		}else{
//			dto.setUser_country_code(userInfoDTO.getUser_country_code());
//			dto.setUser_language_code(userInfoDTO.getUser_language_code());
//		}
//
//		if(result == 1) {
//			resultDto.setResult(Utils.SUCCESS);
//			ArrayList<WatingUserDTO> watingList;
//
//			// 남성 대기자 리스트를 가져오는 경우
//			if (dto.getWaiting_gender().equals("남성")){
//				watingList = liveCallMapper.getWatingUserV3Man(dto);
//
//				// 여자 대기자 리스트를 가져오는 경우
//			}else{
//				watingList = liveCallMapper.getWatingUserV3Woman(dto);
//			}
//
//			int i = 0;
//			for (WatingUserDTO watingUserDTO : watingList){
//
//				// 나라 코드 값이 null 인 경우
//				if (watingUserDTO.getUser_country_code() ==null){
//					// 한국 나라 설정을 해준다.
//					watingUserDTO.setUser_country_code("KR");
//					watingUserDTO.setUser_language_code("ko");
//					watingUserDTO.setUser_country_url("NONE");
//				}else{
//
//					// 사용자와 상대방의 나라 코드가 같다면 국기 이미지 url에 'NONE'을 넣어준다.
//					if (userInfoDTO.getUser_country_code().equals(watingUserDTO.getUser_country_code())){
//						watingUserDTO.setUser_country_url("NONE");
//						// 다르면 지구본 url을 넣어준다.
//					}else{
//						watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/world_wide_web.png");
//					}
//				}
//
//				// 대기자 리스트에 넣어준다.
//				watingList.set(i,watingUserDTO);
//				i ++;
//			}
//			// 응답 값에 대기자 리스트 넣어줌
//			resultDto.setWatingList(watingList);
//
//			// 사용자 성별을 가져온다.
//			String user_gender = "남성";
//			if (userInfoDTO.getUser_gender() != null){
//				user_gender = userInfoDTO.getUser_gender();
//			}
//
//			// 남성인 경우만 추천 회원 리스트를 넣어줌
//			if (user_gender.equals("남성")){
//
//				ArrayList<RecommendUserDTO> recommendList = recommendUserMapper.selectRecommendListV2(dto);
//				i = 0;
//				for (RecommendUserDTO recommendUserDTO : recommendList){
//
//					// 나라 코드 값이 null 인 경우
//					if (recommendUserDTO.getUser_country_code() ==null){
//						// 한국 나라 설정을 해준다.
//						recommendUserDTO.setUser_country_code("KR");
//						recommendUserDTO.setUser_language_code("ko");
//						recommendUserDTO.setUser_country_url("NONE");
//					}else{
//
//						// 사용자와 상대방의 나라 코드가 같다면 국기 이미지 url에 'NONE'을 넣어준다.
//						if (userInfoDTO.getUser_country_code().equals(recommendUserDTO.getUser_country_code())){
//							recommendUserDTO.setUser_country_url("NONE");
//							// 다르면 지구본 url을 넣어준다.
//						}else{
//							recommendUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/world_wide_web.png");
//						}
//					}
//
//					// 대기자 리스트에 넣어준다.
//					recommendList.set(i,recommendUserDTO);
//					i ++;
//				}
//
//				resultDto.setRecommendList(recommendList);
//			}
//		}
//
//		return resultDto;
//	}

	@Override
	public GetWatingDTO insertWaitingV5(MainLiveCallDTO dto) {
		GetWatingDTO resultDto = new GetWatingDTO();

		liveCallMapper.deleteWaiting(dto.getUser_no());
		int result = liveCallMapper.insertWaiting(dto.getUser_no());
		UserInfoDTO userInfoDTO = userMapper.getUserInfo(dto.getUser_no());

		if(!userInfoDTO.getUser_service_type().equals("allcam")) dto.setOrder("new");
		// 나라 정보가 없으면 기본값 넣어 주기
		if (userInfoDTO.getUser_country_code() == null){
			dto.setUser_country_code("KR");
			dto.setUser_language_code("ko");
		}else{
			dto.setUser_country_code(userInfoDTO.getUser_country_code());
			dto.setUser_language_code(userInfoDTO.getUser_language_code());
		}

		if(result == 1) {
			resultDto.setResult(Utils.SUCCESS);
			ArrayList<WatingUserDTO> watingList;

			// 대기자 많이 가져온는 것
			// 남성 대기자 리스트를 가져오는 경우
			if (dto.getWaiting_gender().equals("남성")){
				watingList = liveCallMapper.getWatingUserV3Man(dto);

				// 여자 대기자 리스트를 가져오는 경우
			}else{
				watingList = liveCallMapper.getWatingUserV3Woman(dto);
			}

			// 대기자 적게 가져온는 것
			// 남성 대기자 리스트를 가져오는 경우
//			if (dto.getWaiting_gender().equals("남성")){
//				// 5시간 이내 사용자만 가져옴
//				watingList = liveCallMapper.getWatingUserV4Man(dto);
//
//				// 여자 대기자 리스트를 가져오는 경우
//			}else{
//				watingList = liveCallMapper.getWatingUserV4Woman(dto);
//			}

			int i = 0;
			for (WatingUserDTO watingUserDTO : watingList){

				// 나라 코드 값이 null 인 경우
				if (watingUserDTO.getUser_country_code() ==null){
					// 한국 나라 설정을 해준다.
					watingUserDTO.setUser_country_code("KR");
					watingUserDTO.setUser_language_code("ko");
					watingUserDTO.setUser_country_url("NONE");
				}else{

					// 사용자와 상대방의 나라 코드가 같다면 국기 이미지 url에 'NONE'을 넣어준다.
					if (userInfoDTO.getUser_country_code().equals(watingUserDTO.getUser_country_code())){
						watingUserDTO.setUser_country_url("NONE");
						// 다르면 지구본 url을 넣어준다.
					}else{
						watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/world_wide_web.png");
					}
				}

				// 대기자 리스트에 넣어준다.
				watingList.set(i,watingUserDTO);
				i ++;
			}
			// 응답 값에 대기자 리스트 넣어줌
			resultDto.setWatingList(watingList);

			// 사용자 성별을 가져온다.
			String user_gender = "남성";
			if (userInfoDTO.getUser_gender() != null){
				user_gender = userInfoDTO.getUser_gender();
			}

			// 남성인 경우만 추천 회원 리스트를 넣어줌
			if (user_gender.equals("남성")){

				ArrayList<RecommendUserDTO> recommendList = recommendUserMapper.selectRecommendListV2(dto);
				i = 0;
				for (RecommendUserDTO recommendUserDTO : recommendList){

					// 나라 코드 값이 null 인 경우
					if (recommendUserDTO.getUser_country_code() ==null){
						// 한국 나라 설정을 해준다.
						recommendUserDTO.setUser_country_code("KR");
						recommendUserDTO.setUser_language_code("ko");
						recommendUserDTO.setUser_country_url("NONE");
					}else{

						// 사용자와 상대방의 나라 코드가 같다면 국기 이미지 url에 'NONE'을 넣어준다.
						if (userInfoDTO.getUser_country_code().equals(recommendUserDTO.getUser_country_code())){
							recommendUserDTO.setUser_country_url("NONE");
							// 다르면 지구본 url을 넣어준다.
						}else{
							recommendUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/world_wide_web.png");
						}
					}

					// 대기자 리스트에 넣어준다.
					recommendList.set(i,recommendUserDTO);
					i ++;
				}

				resultDto.setRecommendList(recommendList);
			}
			// 사용자 성별을 가져온다.
			RandomImageDTO randomImageDTO = new RandomImageDTO();
			randomImageDTO.setTarget_gender(user_gender);
			// 랜덤 이미지 리스트를 가져온다.
			ArrayList<RandomImageDTO> imageDTOArrayList = liveCallMapper.getRandomImage(randomImageDTO);

			if (imageDTOArrayList.size()>0)
				resultDto.setRandomImageList(imageDTOArrayList);
			resultDto.setWatingList(watingList);
		}

		return resultDto;
	}

	@Override
	public GetWatingDTO insertWaitingV6(MainLiveCallDTO dto) {
		GetWatingDTO resultDto = new GetWatingDTO();

		liveCallMapper.deleteWaiting(dto.getUser_no());
		int result = liveCallMapper.insertWaiting(dto.getUser_no());
		UserInfoDTO userInfoDTO = userMapper.getUserInfo(dto.getUser_no());

		// 나라 정보가 없으면 기본값 넣어 주기
		if (userInfoDTO.getUser_country_code() == null){
			dto.setUser_country_code("KR");
			dto.setUser_language_code("ko");
		}else{
			dto.setUser_country_code(userInfoDTO.getUser_country_code());
			dto.setUser_language_code(userInfoDTO.getUser_language_code());
		}

		if(result == 1) {
			resultDto.setResult(Utils.SUCCESS);
			ArrayList<WatingUserDTO> watingList;

			// 내가 올캠인 경우 (나 - 여성 AND 통화 타입이 1이 아님, 대상 - 남성인 경우만 문제 생김)
			if (userInfoDTO.getUser_service_type().equals("allcam")){
				// 문제가 생기는 경우
				if (dto.getWaiting_gender().equals("남성")
						&& userInfoDTO.getUser_gender().equals("여성")
						&& userInfoDTO.getCall_type() != 1){

					// 올캠 남자 회원만 불러오도록 수정해야 함
					watingList = liveCallMapper.getWatingUserV4ManVoice(dto);

						// 여성 대기자 회원을 불러오는 경우
					}else{
						// 남성 대기자 리스트를 가져오는 경우
						if (dto.getWaiting_gender().equals("남성")){
							watingList = liveCallMapper.getWatingUserV4Man(dto);
							// 여자 대기자 리스트를 가져오는 경우
						}else{
							watingList = liveCallMapper.getWatingUserV4Woman(dto);
						}
					}


				// 내가 올캠이 아닌 경우
			}else{

				// 여성 대기자 리스트를 가져오는 경우 - 문제가 생기는 경우
				if (dto.getWaiting_gender().equals("여성")
						&& userInfoDTO.getUser_gender().equals("남성")){

					// 상대방의 전화 타입이 1인 사람만 불러와야 함
					watingList = liveCallMapper.getWatingUserV4WomanVoice(dto);

				}else{
					//
					if (dto.getWaiting_gender().equals("남성")){
						watingList = liveCallMapper.getWatingUserV4Man(dto);


						// 여자 대기자 리스트를 가져오는 경우
					}else{
						watingList = liveCallMapper.getWatingUserV4Woman(dto);
					}
				}

			}



			int i = 0;
			for (WatingUserDTO watingUserDTO : watingList){

				// 나라 코드 값이 null 인 경우
				if (watingUserDTO.getUser_country_code() ==null){
					// 한국 나라 설정을 해준다.
					watingUserDTO.setUser_country_code("KR");
					watingUserDTO.setUser_language_code("ko");
					watingUserDTO.setUser_country_url("NONE");
				}else{

					// 사용자와 상대방의 나라 코드가 같다면 국기 이미지 url에 'NONE'을 넣어준다.
					if (userInfoDTO.getUser_country_code().equals(watingUserDTO.getUser_country_code())){
						watingUserDTO.setUser_country_url("NONE");
						// 다르면 지구본 url을 넣어준다.
					}else{
						watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/world_wide_web.png");
					}
				}

				// 대기자 리스트에 넣어준다.
				watingList.set(i,watingUserDTO);
				i ++;
			}
			// 응답 값에 대기자 리스트 넣어줌
			resultDto.setWatingList(watingList);

			// 사용자 성별을 가져온다.
			String user_gender = "남성";
			if (userInfoDTO.getUser_gender() != null){
				user_gender = userInfoDTO.getUser_gender();
			}

			// 남성인 경우만 추천 회원 리스트를 넣어줌
			if (user_gender.equals("남성")){

				ArrayList<RecommendUserDTO> recommendList = recommendUserMapper.selectRecommendListV2(dto);
				i = 0;
				for (RecommendUserDTO recommendUserDTO : recommendList){

					// 나라 코드 값이 null 인 경우
					if (recommendUserDTO.getUser_country_code() ==null){
						// 한국 나라 설정을 해준다.
						recommendUserDTO.setUser_country_code("KR");
						recommendUserDTO.setUser_language_code("ko");
						recommendUserDTO.setUser_country_url("NONE");
					}else{

						// 사용자와 상대방의 나라 코드가 같다면 국기 이미지 url에 'NONE'을 넣어준다.
						if (userInfoDTO.getUser_country_code().equals(recommendUserDTO.getUser_country_code())){
							recommendUserDTO.setUser_country_url("NONE");
							// 다르면 지구본 url을 넣어준다.
						}else{
							recommendUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/world_wide_web.png");
						}
					}

					// 대기자 리스트에 넣어준다.
					recommendList.set(i,recommendUserDTO);
					i ++;
				}

				resultDto.setRecommendList(recommendList);
			}

			// 사용자 성별을 가져온다.
			RandomImageDTO randomImageDTO = new RandomImageDTO();
			randomImageDTO.setTarget_gender(user_gender);
			// 랜덤 이미지 리스트를 가져온다.
			ArrayList<RandomImageDTO> imageDTOArrayList = liveCallMapper.getRandomImage(randomImageDTO);

			if (imageDTOArrayList.size()>0)
				resultDto.setRandomImageList(imageDTOArrayList);
			resultDto.setWatingList(watingList);
		}

		return resultDto;
	}

	@Override
	public GetWatingDTO insertWaitingMC(MainLiveCallDTO dto) {
		GetWatingDTO resultDto = new GetWatingDTO();

		liveCallMapper.deleteWaiting(dto.getUser_no());
		int result = liveCallMapper.insertWaiting(dto.getUser_no());
		UserInfoDTO userInfoDTO = userMapper.getUserInfo(dto.getUser_no());

		// 나라 정보가 없으면 기본값 넣어 주기
		if (userInfoDTO.getUser_country_code() == null){
			dto.setUser_country_code("KR");
			dto.setUser_language_code("ko");
		}else{
			dto.setUser_country_code(userInfoDTO.getUser_country_code());
			dto.setUser_language_code(userInfoDTO.getUser_language_code());
		}

		if(result == 1) {
			resultDto.setResult(Utils.SUCCESS);
			ArrayList<WatingUserDTO> watingList;

			watingList = liveCallMapper.getWatingUserMC(dto);

			int i = 0;
			for (WatingUserDTO watingUserDTO : watingList){

				// 나라 코드 값이 null 인 경우
				if (watingUserDTO.getUser_country_code() ==null){
					// 한국 나라 설정을 해준다.
					watingUserDTO.setUser_country_code("KR");
					watingUserDTO.setUser_language_code("ko");
					watingUserDTO.setUser_country_url("NONE");
				}else{

					// 사용자와 상대방의 나라 코드가 같다면 국기 이미지 url에 'NONE'을 넣어준다.
					if (userInfoDTO.getUser_country_code().equals(watingUserDTO.getUser_country_code())){
						watingUserDTO.setUser_country_url("NONE");
						// 다르면 지구본 url을 넣어준다.
					}else{
						watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/world_wide_web.png");
					}
				}

				// 대기자 리스트에 넣어준다.
				watingList.set(i,watingUserDTO);
				i ++;
			}
			// 응답 값에 대기자 리스트 넣어줌
			resultDto.setWatingList(watingList);

			// 사용자 성별을 가져온다.
			String user_gender = "남성";
			if (userInfoDTO.getUser_gender() != null){
				user_gender = userInfoDTO.getUser_gender();
			}

			// 남성인 경우만 추천 회원 리스트를 넣어줌
			//if (user_gender.equals("남성")){

				ArrayList<RecommendUserDTO> recommendList = recommendUserMapper.selectRecommendListMC(dto);
				i = 0;
				for (RecommendUserDTO recommendUserDTO : recommendList){

					// 나라 코드 값이 null 인 경우
					if (recommendUserDTO.getUser_country_code() ==null){
						// 한국 나라 설정을 해준다.
						recommendUserDTO.setUser_country_code("KR");
						recommendUserDTO.setUser_language_code("ko");
						recommendUserDTO.setUser_country_url("NONE");
					}else{

						// 사용자와 상대방의 나라 코드가 같다면 국기 이미지 url에 'NONE'을 넣어준다.
						if (userInfoDTO.getUser_country_code().equals(recommendUserDTO.getUser_country_code())){
							recommendUserDTO.setUser_country_url("NONE");
							// 다르면 지구본 url을 넣어준다.
						}else{
							recommendUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/world_wide_web.png");
						}
					}

					// 대기자 리스트에 넣어준다.
					recommendList.set(i,recommendUserDTO);
					i ++;
				}

				resultDto.setRecommendList(recommendList);
			//}
		}

		return resultDto;
	}

	@Override
	public GetWatingDTO getWatingUserNonMember() {
		GetWatingDTO resultDto = new GetWatingDTO();

		resultDto.setResult(Utils.SUCCESS);
		MainLiveCallDTO dto = new MainLiveCallDTO();
		dto.setCountry_kind("all");
		dto.setOrder("new");
		dto.setUser_country_code("KR");
		dto.setUser_language_code("ko");
		ArrayList<WatingUserDTO> watingList = liveCallMapper.getWatingUserNonMember(dto);
		int i = 0;

		for (WatingUserDTO watingUserDTO : watingList){

			// 나라 코드 값이 null 인 경우
			if (watingUserDTO.getUser_country_code() ==null){
				// 한국 나라 설정을 해준다.
				watingUserDTO.setUser_country_code("KR");
				watingUserDTO.setUser_language_code("ko");
				watingUserDTO.setUser_country_url("NONE");
			}else{

				// 대기인원이 한국이 아닌 경우
				if (!watingUserDTO.getUser_country_code().equals("KR")){
					watingUserDTO.setUser_country_url("https://allcam.co.kr/images/country-image/world_wide_web.png");
					// 한국인 경우
				}else{
					watingUserDTO.setUser_country_url("NONE");
				}

			}

			// 대기자 리스트에 넣어준다.
			watingList.set(i,watingUserDTO);
			i ++;
		}

		// 응답 값에 대기자 리스트 넣어줌
		resultDto.setWatingList(watingList);

		ArrayList<RecommendUserDTO> recommendList = recommendUserMapper.recommendListNonMember();
		i = 0;
		for (RecommendUserDTO recommendUserDTO : recommendList){

			// 나라 코드 값이 null 인 경우
			if (recommendUserDTO.getUser_country_code() ==null){
				// 한국 나라 설정을 해준다.
				recommendUserDTO.setUser_country_code("KR");
				recommendUserDTO.setUser_language_code("ko");
				recommendUserDTO.setUser_country_url("NONE");
			}else{

				// 다르면 지구본 url을 넣어준다.
				recommendUserDTO.setUser_country_url("NONE");
			}

			// 대기자 리스트에 넣어준다.
			recommendList.set(i,recommendUserDTO);
			i ++;
		}
		resultDto.setRecommendList(recommendList);


		return resultDto;
	}

	@Override
	public GetNewbieUserListDTO getNewbieUserList(MainLiveCallDTO dto) {
		GetNewbieUserListDTO resultDto = new GetNewbieUserListDTO();

		liveCallMapper.deleteWaiting(dto.getUser_no());
		int result = liveCallMapper.insertWaiting(dto.getUser_no());

		if(result == 1) {
			resultDto.setResult(Utils.SUCCESS);
			ArrayList<NewbieUserDTO> _newbieUserList = liveCallMapper.getNewbieUserList(dto);

			ArrayList<NewbieUserDTO> newbieUserList = new ArrayList<>();
			Date nowDate = new Date();

			// 0~23 시간을 포맷으로 함
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
			String strNowDate = simpleDateFormat.format(nowDate);
			String lastTime = String.valueOf(Integer.parseInt(strNowDate)+2);
			// 새내기 시간 데이터가 없는 것은 현재 시간 기준으로 +2를 한다.
			for (NewbieUserDTO newbieUserDTO : _newbieUserList) {
				if (newbieUserDTO.getUser_newbie_time() == null){
					newbieUserDTO.setUser_newbie_time(strNowDate+lastTime);
				}
				newbieUserList.add(newbieUserDTO);
			}

			resultDto.setNewbie_user_list(newbieUserList);
		}

		return resultDto;
	}

	@Override
	public GetNewbieUserListDTO getNewbieUserListV2(MainLiveCallDTO dto){
		UserInfoDTO userInfoDTO = userMapper.getUserInfo(dto.getUser_no());
		dto.setUser_language_code(userInfoDTO.getUser_language_code());

		GetNewbieUserListDTO resultDto = new GetNewbieUserListDTO();

		liveCallMapper.deleteWaiting(dto.getUser_no());
		int result = liveCallMapper.insertWaiting(dto.getUser_no());

		if(result == 1) {
			resultDto.setResult(Utils.SUCCESS);
			ArrayList<NewbieUserDTO> _newbieUserList = liveCallMapper.getNewbieUserListV2(dto);

			ArrayList<NewbieUserDTO> newbieUserList = new ArrayList<>();
			Date nowDate = new Date();

			// 0~23 시간을 포맷으로 함
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH");
			String strNowDate = simpleDateFormat.format(nowDate);
			String lastTime = String.valueOf(Integer.parseInt(strNowDate)+2);
			// 새내기 시간 데이터가 없는 것은 현재 시간 기준으로 +2를 한다.
			for (NewbieUserDTO newbieUserDTO : _newbieUserList) {
				if (newbieUserDTO.getUser_newbie_time() == null){
					newbieUserDTO.setUser_newbie_time(strNowDate+lastTime);
				}
				newbieUserList.add(newbieUserDTO);
			}

			resultDto.setNewbie_user_list(newbieUserList);
		}

		return resultDto;
	}

	@Override
	public GetNewbieUserListDTO getNewUserList(MainLiveCallDTO dto){
		UserInfoDTO userInfoDTO = userMapper.getUserInfo(dto.getUser_no());
		dto.setUser_language_code(userInfoDTO.getUser_language_code());

		GetNewbieUserListDTO resultDto = new GetNewbieUserListDTO();

		liveCallMapper.deleteWaiting(dto.getUser_no());
		int result = liveCallMapper.insertWaiting(dto.getUser_no());

		if(result == 1) {
			resultDto.setResult(Utils.SUCCESS);
			ArrayList<NewbieUserDTO> _newbieUserList = liveCallMapper.getNewUserList(dto);
			resultDto.setNewbie_user_list(_newbieUserList);
		}

		return resultDto;
	}

	@Override
	public CallChkInfoDTO callInfoChk(int user_no, int partner_user_no) {
		CallChkUserDTO dto = new CallChkUserDTO();
		dto.setUser_no(user_no);
		dto.setPartner_user_no(partner_user_no);

		UserInfoDTO myInfo = userMapper.getUserInfo(user_no);
		UserInfoDTO partnerInfo = userMapper.getUserInfo(partner_user_no);

		boolean malePointChk = false;	//남자 유저만 포인트 체크
		if("남성".equals(myInfo.getUser_gender())) {
			malePointChk = (myInfo.getUser_point() >= 1800);
		}else {
			malePointChk = true;
		}

		CallChkInfoDTO resultDto = new CallChkInfoDTO();
		resultDto.setMyInfo(myInfo);
		resultDto.setPartnerInfo(partnerInfo);
		resultDto.setResult(Utils.SUCCESS);
		resultDto.setSuccess_code(malePointChk ? "1":"2");	//통화 가능 1 , 포인트 부족 2, 상대방이 통화 중이거나 통화 연결 중 3


		// 예외사항 처리
		// - 현재 사용자가 만든 방 중에
		// 통화 진행(방 상태 코드 1) 또는 대기(방 상태 코드 0)가 있는 경우
		// 방 상태 코드를 -4로 수정한다.
//		liveCallMapper.fixErrorCall(myInfo);

		if(malePointChk && partnerInfo.getUser_alarm_push() == 0) {	//푸쉬 받기 설정 해야 룸 생성 | 0 : 받기 / 1 안받기
			String roomId = myInfo.getNo() + "_" + new Date().getTime();
			resultDto.setRoomId(roomId);

			// 상대방이 통화 중이거나 통화 연결 중인지 확인
			// 1. 상대방이 속한 room인 경우
			// 2. room_status 값이 0(통화 연결 중) 또는 1(통화 중)인 경우
			// 3. 방 만든지 15초 안의 방인 경우
			int partnerRoomCount = liveCallMapper.getPartnerChk(partner_user_no);

			// 상대방이 통화 중이거나 통화 연결 중인 방이 1개 이상인 경우
			if (partnerRoomCount >0){
				resultDto.setSuccess_code("3");
				// 방이 1개도 없는 경우
			}else{

				int blackChk = userService.chkUserBlackList(partner_user_no, user_no);

				// 상대방이 나를 차단 안 한 경우
				if(blackChk == 0) {
					RoomStatusDTO roomDto = new RoomStatusDTO();
					roomDto.setRoom_id(roomId);
					roomDto.setRoom_type(1); // 1:영상(기본), 2:음성
					roomDto.setRoom_status(0);
					roomDto.setMyPushKey(myInfo.getUser_push_key());
					roomDto.setPartnerPushKey(partnerInfo.getUser_push_key());
					roomDto.setMy_user_no(user_no);
					roomDto.setPartner_user_no(partner_user_no);

					liveCallMapper.insertRoomStatus(roomDto);	// 룸 상태 생성

					Gson gson = new Gson();
					pushService.sendDataVoipPush(partnerInfo.getUser_push_key(), partnerInfo.getUser_voip_key(), PushType.LIVECALL.getTypeCd(), gson.toJson(resultDto), myInfo.getUser_nick_name(), "영상 통화 신청");	//상대 방한테 푸쉬 콜
					// 채팅 데이터를 저장한다.
					chatService.sendChat(user_no, partner_user_no, "영상 통화 신청", false); //채팅 등록- banPush=false
				}
			}

		}

		return resultDto;
	}

	//중복로그인 오류 관련 push 토큰 업데이트용 오버로드
	public CallChkInfoDTO callInfoChk(int user_no, int partner_user_no, String pushKey) {
		UserPushTokenDTO userPushTokenDto = new UserPushTokenDTO();
		userPushTokenDto.setNo(user_no);
		// pushKey는 상대방이 아닌 본인 pushkey이다.
		userPushTokenDto.setUser_push_key(pushKey);
		userMapper.setPushKey(userPushTokenDto);

		CallChkInfoDTO resultDto = new CallChkInfoDTO();
		resultDto = callInfoChk(user_no, partner_user_no);

		return resultDto;
	}

	//중복로그인 오류 관련 push 토큰 업데이트용 오버로드
	public CallChkInfoDTO callInfoChkV2(int user_no, int partner_user_no, String pushKey) {
		UserPushTokenDTO userPushTokenDto = new UserPushTokenDTO();
		userPushTokenDto.setNo(user_no);
		// pushKey는 상대방이 아닌 본인 pushkey이다.
		userPushTokenDto.setUser_push_key(pushKey);
		userMapper.setPushKey(userPushTokenDto);

		CallChkUserDTO dto = new CallChkUserDTO();
		dto.setUser_no(user_no);
		dto.setPartner_user_no(partner_user_no);

		UserInfoDTO myInfo = userMapper.getUserInfo(user_no);
		UserInfoDTO partnerInfo = userMapper.getUserInfo(partner_user_no);

		boolean malePointChk = false;	//남자 유저만 포인트v 체크
		if("남성".equals(myInfo.getUser_gender())) {
			malePointChk = (myInfo.getUser_point() >= Utils.MC_POINT_PER_MIN);
		}else {
			malePointChk = true;
		}

		CallChkInfoDTO resultDto = new CallChkInfoDTO();
		resultDto.setMyInfo(myInfo);
		resultDto.setPartnerInfo(partnerInfo);
		resultDto.setResult(Utils.SUCCESS);
		resultDto.setSuccess_code(malePointChk ? "1":"2");	//통화 가능 1 , 포인트 부족 2, 상대방이 통화 중이거나 통화 연결 중 3


		// 예외사항 처리
		// - 현재 사용자가 만든 방 중에
		// 통화 진행(방 상태 코드 1) 또는 대기(방 상태 코드 0)가 있는 경우
		// 방 상태 코드를 -4로 수정한다.
//		liveCallMapper.fixErrorCall(myInfo);

		if(malePointChk && partnerInfo.getUser_alarm_push() == 0) {	//푸쉬 받기 설정 해야 룸 생성 | 0 : 받기 / 1 안받기
			String roomId = myInfo.getNo() + "_" + new Date().getTime();
			resultDto.setRoomId(roomId);

			// 상대방이 통화 중이거나 통화 연결 중인지 확인
			// 1. 상대방이 속한 room인 경우
			// 2. room_status 값이 0(통화 연결 중) 또는 1(통화 중)인 경우
			// 3. 방 만든지 15초 안의 방인 경우
			int partnerRoomCount = liveCallMapper.getPartnerChk(partner_user_no);

			// 상대방이 통화 중이거나 통화 연결 중인 방이 1개 이상인 경우
			if (partnerRoomCount >0){
				resultDto.setSuccess_code("3");
				// 방이 1개도 없는 경우
			}else{

				int blackChk = userService.chkUserBlackList(partner_user_no, user_no);
				// 상대방이 나를 차단 안 한 경우
				if(blackChk == 0) {
					RoomStatusDTO roomDto = new RoomStatusDTO();
					roomDto.setRoom_id(roomId);
					roomDto.setRoom_type(1); // 1:영상(기본), 2:음성
					roomDto.setRoom_status(0);
					roomDto.setMyPushKey(myInfo.getUser_push_key());
					roomDto.setPartnerPushKey(partnerInfo.getUser_push_key());
					roomDto.setMy_user_no(user_no);
					roomDto.setPartner_user_no(partner_user_no);

					liveCallMapper.insertRoomStatus(roomDto);	// 룸 상태 생성

					Gson gson = new Gson();
					pushService.sendDataVoipPush(partnerInfo.getUser_push_key(), partnerInfo.getUser_voip_key(), PushType.LIVECALL.getTypeCd(), gson.toJson(resultDto), myInfo.getUser_nick_name(), "영상 통화 신청");	//상대 방한테 푸쉬 콜
					// 채팅 데이터를 저장한다.
					chatService.sendChat(user_no, partner_user_no, "영상 통화 신청", false); //채팅 등록- banPush=false
				}
			}

		}

		return resultDto;
	}

	@Override
	public CallChkInfoDTO callInfoChkRandomPartner(int user_no, String pushKey, int image_no) {
		// 랜덤 이미지의 클릭 수를 증가시킴
		liveCallMapper.updateRandomImageHit(image_no);

		UserPushTokenDTO userPushTokenDto = new UserPushTokenDTO();
		userPushTokenDto.setNo(user_no);
		// pushKey는 상대방이 아닌 본인 pushkey이다.
		userPushTokenDto.setUser_push_key(pushKey);
		userMapper.setPushKey(userPushTokenDto);

		//여성, user_type=0, user_aram_type=0, user_part_time < 2, black 등록 여부 확인
		int partner_user_no = userMapper.selectRandomPartner(user_no);

		CallChkUserDTO dto = new CallChkUserDTO();
		dto.setUser_no(user_no);
		dto.setPartner_user_no(partner_user_no);

		UserInfoDTO myInfo = userMapper.getUserInfo(user_no);
		UserInfoDTO partnerInfo = userMapper.getUserInfo(partner_user_no);

		boolean malePointChk = false;	//남자 유저만 포인트v 체크
		if("남성".equals(myInfo.getUser_gender())) {
			malePointChk = (myInfo.getUser_point() >= Utils.MC_POINT_PER_MIN);
		}else {
			malePointChk = true;
		}

		CallChkInfoDTO resultDto = new CallChkInfoDTO();
		resultDto.setMyInfo(myInfo);
		resultDto.setPartnerInfo(partnerInfo);
		resultDto.setResult(Utils.SUCCESS);
		resultDto.setSuccess_code(malePointChk ? "1":"2");	//통화 가능 1 , 포인트 부족 2, 상대방이 통화 중이거나 통화 연결 중 3


		// 예외사항 처리
		// - 현재 사용자가 만든 방 중에
		// 통화 진행(방 상태 코드 1) 또는 대기(방 상태 코드 0)가 있는 경우
		// 방 상태 코드를 -4로 수정한다.
//		liveCallMapper.fixErrorCall(myInfo);

		if(malePointChk) {	//푸쉬 받기 설정 해야 룸 생성 | 0 : 받기 / 1 안받기
			String roomId = myInfo.getNo() + "_" + new Date().getTime();
			resultDto.setRoomId(roomId);

			RoomStatusDTO roomDto = new RoomStatusDTO();
			roomDto.setRoom_id(roomId);
			roomDto.setRoom_type(1); // 1:영상(기본), 2:음성
			roomDto.setRoom_status(0);
			roomDto.setMyPushKey(myInfo.getUser_push_key());
			roomDto.setPartnerPushKey(partnerInfo.getUser_push_key());
			roomDto.setMy_user_no(user_no);
			roomDto.setPartner_user_no(partner_user_no);

			liveCallMapper.insertRoomStatus(roomDto);	// 룸 상태 생성

			Gson gson = new Gson();
			pushService.sendDataVoipPush(partnerInfo.getUser_push_key(),partnerInfo.getUser_voip_key(), PushType.LIVECALL.getTypeCd(), gson.toJson(resultDto), myInfo.getUser_nick_name(), "영상 통화 신청");	//상대 방한테 푸쉬 콜
			// 채팅 데이터를 저장한다.
			chatService.sendChat(user_no, partner_user_no, "영상 통화 신청", false); //채팅 등록- banPush=false

		}

		return resultDto;





	}

	// 랜덤 영상통화 방 확인
	public CallChkInfoDTO randomCallChk(int user_no, String pushKey, int image_no) {
		UserPushTokenDTO userPushTokenDto = new UserPushTokenDTO();
		userPushTokenDto.setNo(user_no);
		// pushKey는 상대방이 아닌 본인 pushkey이다.
		userPushTokenDto.setUser_push_key(pushKey);
		// 내 pushkey 수정함
		userMapper.setPushKey(userPushTokenDto);

		CallChkInfoDTO resultDto = new CallChkInfoDTO();
		resultDto.setResult(Utils.SUCCESS);

		// 랜덤 이미지의 클릭 수를 증가시킴
		liveCallMapper.updateRandomImageHit(image_no);

		// 1. 남자라면 포인트 확인
		// 1-1 포인트가 없는 경우 success code에 2를 반환함
		// 2. 이성이 만들고, 대기 중인 방이 있는지 확인 (user table join, room_status가 0인 방, my_user_no이랑 part_user_no이 다른 방)
		// 2-1 있는 경우 success code에 0, roomId는 랜덤으로 반환함
		// 2-2 없는 경우 success code에 1를 반환함
		UserInfoDTO myInfo = userMapper.getUserInfo(user_no);

		// 1. 남자라면 포인트 확인
		String successCode = "-1";
		if("남성".equals(myInfo.getUser_gender())) {
			 if (myInfo.getUser_point() < Utils.MC_FIRST_CHAT_POINT)
				 successCode = "2";
		}

		// 예외사항 처리
		// - 현재 사용자가 만든 방 중에
		// 랜덤 채팅 진행(방 상태 코드 1) 또는 대기(방 상태 코드 0)가 있는 경우
		// 방 상태 코드를 -4로 수정한다.
//		liveCallMapper.fixErrorCall(myInfo);

		// 2. 이성이 만들고, 대기 중인 랜덤 영상통화 방이 있는지 확인
		ArrayList<CallChkUserDTO> roomList = liveCallMapper.getRandomCall(myInfo.getUser_gender().equals("남성")? "여성": "남성");

		// 2-2 랜덤 영상통화 대기방이 있는 경우
		if (roomList.size() > 0){
			String room_id = roomList.get(0).getRoom_id();
			int my_user_no = roomList.get(0).getMy_user_no();
			resultDto.setRoomId(room_id);
			successCode = "0";

			CallChkUserDTO callChkUserDTO = new CallChkUserDTO();
			callChkUserDTO.setPartner_user_no(user_no);
			callChkUserDTO.setRoom_id(room_id);

			// 해당 방의 partner 정보를 수정함
			liveCallMapper.updateRoomPartner(callChkUserDTO);

			// 내 방을 만든 사람 정보를 가져옴
			// 채팅방에 통화 내역 저장함
			ChatDTO dto = new ChatDTO();
			dto.setChat_text("랜덤 영상 통화");
			dto.setTo_user_no(user_no);
			dto.setFrom_user_no(my_user_no);
			dto.setTo_visible(0);

			chatMapper.sendChat(dto);

			// 2-1 대기 방이 없는 경우
		}else{

			// 1. 방 만들기
			String room_id = user_no + "_" + new Date().getTime();

			// TODO: INSERT 실패했을 경우 처리해야 함
			RoomStatusDTO roomDto = new RoomStatusDTO();
			roomDto.setRoom_id(room_id);
			roomDto.setRoom_type(3); // 1:영상(기본), 2:음성, 3:랜덤영상
			roomDto.setRoom_status(0);
			roomDto.setMyPushKey(pushKey);
			roomDto.setPartnerPushKey(pushKey);
			roomDto.setMy_user_no(user_no);
			roomDto.setPartner_user_no(user_no);
			liveCallMapper.insertRoomStatus(roomDto);

			// 2. 영상통화 대기 리스트에 내가 통화중이라고 나올 수 있게 하기
			IsLiveDTO isDto = new IsLiveDTO();
			isDto.setUser_no(user_no);
			isDto.setConnect_user_no(user_no);
			// 영상통화 대기자 목록에서 내가 연결된 사람이 있게 만듬
			liveCallMapper.isLive(isDto); //웨이팅 룸 라이브로 전환

			successCode = "1";
			resultDto.setRoomId(room_id);
		}

		resultDto.setSuccess_code(successCode);

		return resultDto;
	}

	@Override
	public DefaultDTO randomCallStart(String room_id, int user_no, String partner_push_key) {
		DefaultDTO resultDto = new DefaultDTO();

		// 랜덤 영상통화 상대방이 입장했으므로
		// 랜덤 영상통화 상대방의 정보를 넣는다.
		// user_no -> 랜덤 영상통화 방에 입장할 유저 번호이다.
		LiveTimeDTO dto = new LiveTimeDTO();
		dto.setRoom_id(room_id);
		dto.setPartner_user_no(user_no);
		dto.setPartner_push_key(partner_push_key);
		dto.setStart_time(Utils.getNowTime());
		dto.setRoom_status(1);

		// 랜덤 영상통화 상대방이 방 정보를 수정한다.
		// (상대 id, 상대 push key, 통화 시작 시간, 방 상태)
		int result = liveCallMapper.updateRandomCallRoom(dto);
		// 상대방, 나의 push key 가져옴
		RoomPushKeyDTO roomPushDto = liveCallMapper.getRoomPushKey(room_id);

		if(result == 1) {

			resultDto.setResult(Utils.SUCCESS);

			IsLiveDTO isDto = new IsLiveDTO();
			isDto.setUser_no(roomPushDto.getMy_user_no());
			isDto.setConnect_user_no(roomPushDto.getPartner_user_no());
			// 영상통화 대기자에서 상대방이 연결된 사람이 있다고 수정함
			liveCallMapper.isLive(isDto); //웨이팅 룸 라이브로 전환

			isDto = new IsLiveDTO();
			isDto.setUser_no(roomPushDto.getPartner_user_no());
			isDto.setConnect_user_no(roomPushDto.getMy_user_no());
			// 영상통화 대기자 목록에서 내가 연결된 사람이 있다고 수정함
			liveCallMapper.isLive(isDto); //웨이팅 룸 라이브로 전환

		}else{
		}

		return resultDto;
	}

	@Override
	public DefaultDTO randomCallCancel(String room_id, int finish_type, int user_no) {
		DefaultDTO resultDto = new DefaultDTO();

		if(!"null".equals(room_id)) {

			RoomStatusDTO roomDto = new RoomStatusDTO();
			roomDto.setRoom_id(room_id);
			roomDto.setRoom_status(-1);
			roomDto.setFinish_user_no(user_no);
			roomDto.setFinish_type(finish_type);

			int result = liveCallMapper.updateRandomRoomStatus(roomDto);	// 룸 상태 변경

			if(result == 1) {

				if(finish_type ==5){
					// 부재중 통화 리워드 이벤트 기간인 경우
					// 하루 최대 100 포인트 지급
					giveAbsentCallPoint(room_id);
				}

				resultDto.setResult(Utils.SUCCESS);

			}
		}

		return resultDto;
	}

	@Override
	public UserBalanceDTO randomCallFinish(int user_no, String room_id, int use_seconds, int finish_type) { // 대화 종료
		//live room  업데이트
		CallFinishDTO callFinishDto = new CallFinishDTO();
		callFinishDto.setRoom_id(room_id);
		callFinishDto.setFinish_user_no(user_no);
		callFinishDto.setFinish_type(finish_type);
		callFinishDto.setFinish_use_seconds(use_seconds);
		int resultUpdate = liveCallMapper.updateCallFinish(callFinishDto);

		//response
		UserBalanceDTO resultDto = new UserBalanceDTO();
		UserInfoDTO userInfoDto = userMapper.getUserInfo(user_no);
		resultDto.setUser_no(user_no);
		resultDto.setUser_point(userInfoDto.getUser_point());
		resultDto.setUser_cash(userInfoDto.getUser_cash());
		resultDto.setUser_gender(userInfoDto.getUser_gender());
		resultDto.setResult(Utils.SUCCESS);

		return resultDto;
	}


	//음성 통화용(남성만 걸수 있고 새내기(여성)는 받기만 가
	public CallChkInfoDTO voiceCallInfoChk(int user_no, int partner_user_no, String pushKey) {
		UserPushTokenDTO userPushTokenDto = new UserPushTokenDTO();
		userPushTokenDto.setNo(user_no);
		userPushTokenDto.setUser_push_key(pushKey);
		userMapper.setPushKey(userPushTokenDto);

		CallChkUserDTO dto = new CallChkUserDTO();
		dto.setUser_no(user_no);
		dto.setPartner_user_no(partner_user_no);

		UserInfoDTO myInfo = userMapper.getUserInfo(user_no);
		UserInfoDTO partnerInfo = userMapper.getUserInfo(partner_user_no);

		boolean malePointChk = false;	//남자 유저만 포인트 체크
		boolean femaleNewbieChk = false; //새내기 체크
		String success_code = "0";
		if("남성".equals(myInfo.getUser_gender())) {
			malePointChk = (myInfo.getUser_point() >= 360);
			femaleNewbieChk = (partnerInfo.getUser_part_time() == 3);
		}
		if(malePointChk){
			if(femaleNewbieChk){
				success_code = "1";
			}else {
				success_code = "3";
			}
		}else{
			success_code = "2";
		}

		CallChkInfoDTO resultDto = new CallChkInfoDTO();
		resultDto.setMyInfo(myInfo);
		resultDto.setPartnerInfo(partnerInfo);
		resultDto.setResult(Utils.SUCCESS);
		resultDto.setSuccess_code(success_code);	//통화 가능 1 , 포인트 부족 2, 새내기 아님 3

		if("1".equals(success_code) && partnerInfo.getUser_alarm_push() == 0) {	//푸쉬 받기 설정 해야 룸 생성 | 0 : 받기 / 1 안받기
			String roomId = myInfo.getNo() + "_" + new Date().getTime();
			resultDto.setRoomId(roomId);

			int blackChk = userService.chkUserBlackList(partner_user_no, user_no);

			if(blackChk == 0) {
				RoomStatusDTO roomDto = new RoomStatusDTO();
				roomDto.setRoom_id(roomId);
				roomDto.setRoom_type(2); // 1:영상(기본), 2:음성
				roomDto.setRoom_status(0);
				roomDto.setMyPushKey(myInfo.getUser_push_key());
				roomDto.setPartnerPushKey(partnerInfo.getUser_push_key());
				roomDto.setMy_user_no(user_no);
				roomDto.setPartner_user_no(partner_user_no);

				liveCallMapper.insertRoomStatus(roomDto);	// 룸 상태 생성

				Gson gson = new Gson();
				pushService.sendDataPush(partnerInfo.getUser_push_key(), PushType.VOICECALL.getTypeCd(), gson.toJson(resultDto), partnerInfo.getUser_nick_name(), "보이스팅 신청");	//상대 방한테 푸쉬 콜
				chatService.sendChatV2(user_no, partner_user_no, "보이스팅 신청", false); //채팅 등록- banPush=false
			}
		}

		return resultDto;
	}

	// 새내기 상관없이 누구나 음성 통화 할 수 있도록 함
	@Override
	public CallChkInfoDTO voiceCallInfoChkV2(int user_no, int partner_user_no, String pushKey) {
		UserPushTokenDTO userPushTokenDto = new UserPushTokenDTO();
		userPushTokenDto.setNo(user_no);
		userPushTokenDto.setUser_push_key(pushKey);
		userMapper.setPushKey(userPushTokenDto);

		CallChkUserDTO dto = new CallChkUserDTO();
		dto.setUser_no(user_no);
		dto.setPartner_user_no(partner_user_no);

		UserInfoDTO myInfo = userMapper.getUserInfo(user_no);
		UserInfoDTO partnerInfo = userMapper.getUserInfo(partner_user_no);

		boolean malePointChk = true;	//남자 유저만 포인트 체크

		String success_code = "0";
		if("남성".equals(myInfo.getUser_gender())) {
			malePointChk = (myInfo.getUser_point() >= Utils.VOICE_POINT_PER_MIN);
		}
		if(malePointChk){
			success_code = "1";
		}else{
			success_code = "2";
		}

		CallChkInfoDTO resultDto = new CallChkInfoDTO();
		resultDto.setMyInfo(myInfo);
		resultDto.setPartnerInfo(partnerInfo);
		resultDto.setResult(Utils.SUCCESS);
		resultDto.setSuccess_code(success_code);	//통화 가능 1 , 포인트 부족 2

		if("1".equals(success_code) && partnerInfo.getUser_alarm_push() == 0) {	//푸쉬 받기 설정 해야 룸 생성 | 0 : 받기 / 1 안받기
			String roomId = myInfo.getNo() + "_" + new Date().getTime();
			resultDto.setRoomId(roomId);

			int blackChk = userService.chkUserBlackList(partner_user_no, user_no);

			if(blackChk == 0) {
				RoomStatusDTO roomDto = new RoomStatusDTO();
				roomDto.setRoom_id(roomId);
				roomDto.setRoom_type(2); // 1:영상(기본), 2:음성
				roomDto.setRoom_status(0);
				roomDto.setMyPushKey(myInfo.getUser_push_key());
				roomDto.setPartnerPushKey(partnerInfo.getUser_push_key());
				roomDto.setMy_user_no(user_no);
				roomDto.setPartner_user_no(partner_user_no);

				liveCallMapper.insertRoomStatus(roomDto);	// 룸 상태 생성

				Gson gson = new Gson();
				pushService.sendDataPush(partnerInfo.getUser_push_key(), PushType.VOICECALL.getTypeCd(), gson.toJson(resultDto), partnerInfo.getUser_nick_name(), "보이스팅 신청");	//상대 방한테 푸쉬 콜
				chatService.sendChatV2(user_no, partner_user_no, "보이스팅 신청", false); //채팅 등록- banPush=false
			}
		}

		return resultDto;
	}

	private void giveAbsentCallPoint(String room_id) {
		// 부재중 통화인 경우 포인트 지급
		String startDayStr = "2021-02-01";
		String endDayStr = "2021-02-28";
		// 이벤트 기간인 경우
		if (checkEventDay(startDayStr, endDayStr)) {

			// 사용자 번호, push key 가져오기
			RoomPushKeyDTO roomPushKeyDTO = liveCallMapper.getRoomPushKeyFlag(room_id);
			int absent_push_flag = roomPushKeyDTO.getAbsent_push_flag();

			// 이미 push를 보낸 경우 다시 안보낸다.
			if (absent_push_flag ==1){
				return;
			}
			int user_no = roomPushKeyDTO.getMy_user_no();

			// 사용자 성별 가져오기
			String user_gender = userMapper.selectUserGender(user_no);

			// 여성인 경우는 제외
			if (user_gender.equals("여성")){
				return;
			}

			// 오늘 부재중 통화 횟수가 10 이하라면
			int absent_call_count = liveCallMapper.absentCallCount(user_no);

			// 리워드 포인트 알림 채팅 메세지 보내기
			if (absent_call_count <= 10) {

				int cnt = chatMapper.getCallChat(room_id);
				if (cnt >0){
					return;
				}
				// 리워드 포인트 알림 채팅 메세지 저장
				ChatDTO cdto = new ChatDTO();
				cdto.setChat_text("리워드 이벤트 10P가 적립되었습니다. 익일 10시에 일괄 지급됩니다.");
				cdto.setFrom_user_no(1);	// 1 : 관리자
				cdto.setTo_user_no(user_no);
				cdto.setAbsent_room_id(room_id);
				int chatResult = chatMapper.sendAbsentCallChat(cdto);
				liveCallMapper.updateRoomPushFlag(room_id);

				// 저장에 성공하면 채팅 메세지 보내기
				if(chatResult == 1){
					JSONObject chatData = new JSONObject();
					chatData.put("from_user_no", 1);
					chatData.put("chat_text", "리워드 이벤트 10P가 적립되었습니다. 익일 10시에 일괄 지급됩니다.");
					chatData.put("from_user_nick", "관리자");
					chatData.put("from_user_thumnail", "NONE");
					pushService.sendDataPush(roomPushKeyDTO.getMy_push_key(), PushType.CHATGET.getTypeCd(), chatData.toString(), "관리자", "리워드 포인트 지급 안내"); //푸쉬 발송
				}
			}
// -------------------------- 실시간 포인트 지급
			// 1. 사용자 번호, 성별 가져옴
//			UserInfoDTO userInfoDTO = liveCallMapper.getMyUserNo(room_id);
//			int user_no = Integer.parseInt(userInfoDTO.getNo());
//			String user_gender = userInfoDTO.getUser_gender();
//
//			// 남성인 경우만 무료 포인트를 제공한다.
//			if (user_gender.equals("남성")) {
//				// 2. 사용자가 오늘 부재중으로 인한 통화 종료 횟수 가져옴
//				int absent_call_count = liveCallMapper.absentCallCount(user_no);
////
//				// 오늘 부재중 통화 횟수가 10 이하라면 포인트 추가하고 포인트 히스토리에 추가한다.
//				if (absent_call_count <= 10) {
//					userMapper.updateUserFreePoint(user_no);
//
//					String content = "부재중 통화 리워드 포인트 적립";
//					UseHistoryDTO hDto = new UseHistoryDTO();
//					hDto.setUse_type(2);
//					hDto.setUse_content(content);
//					hDto.setUse_cnt(10);
//					hDto.setUser_no(user_no);
//					hDto.setUse_etc_user_no(-1);
//					hDto.setUse_etc(room_id);
//					liveCallMapper.insertUseHistory(hDto);
//
//				}
//			}
		}
	}


	@Override
	public DefaultDTO callCancel(String room_id, int call_type) {
		DefaultDTO resultDto = new DefaultDTO();

		if(!"null".equals(room_id)) {
			RoomStatusDTO roomDto = new RoomStatusDTO();
			roomDto.setRoom_id(room_id);
			roomDto.setRoom_status(-1);

			// 종료 타입 - 기본은 발신자가 취소
			int room_status;

			// 정해진 시간이 지나서 취소되는 것
			if(call_type ==3){
				room_status = 6;

				// 수신자가 취소
			}else if (call_type ==1){
				room_status = 62;
				// 발신자가 취소
			}else if (call_type == 0){
				room_status = 61;
			}else{
				room_status = call_type;
			}
			roomDto.setFinish_type(room_status);

			int result = liveCallMapper.updateRoomStatus(roomDto);	// 룸 상태 변경

			// 시간이 지나서 취소되는 경우
			if (room_status ==6) {
				// 부재중 통화 리워드 이벤트 기간인 경우
				// 하루 최대 100 포인트 지급
				giveAbsentCallPoint(room_id);
			}

			// 룸 상태 변경에 성공한 경우
			if(result == 1) {
				resultDto.setResult(Utils.SUCCESS);

				// 취소인 경우 상대방에게 알림 보냄
				if(call_type < 3) {	//3은 시간 지연 취소
					String sendPushKey = "";

					RoomPushKeyDTO roomPushDto = liveCallMapper.getRoomPushKey(room_id);

				
					if(call_type == 0) {	//발신자가 취소
						sendPushKey = roomPushDto.getPartner_push_key();
					}else if(call_type == 1) {	//수신자가 취소
						sendPushKey = roomPushDto.getMy_push_key();
					}
					
					pushService.sendDataPush(sendPushKey, PushType.LIVECANCEL.getTypeCd(), "", "", "");
					

				}
			}
		}

		return resultDto;
	}

	@Override
	public DefaultDTO voiceCallCancel(String room_id, int call_type) {
		DefaultDTO resultDto = new DefaultDTO();

		if(!"null".equals(room_id)) {
			RoomStatusDTO roomDto = new RoomStatusDTO();
			roomDto.setRoom_id(room_id);
			roomDto.setRoom_status(-1);

			int room_status;

			// 종료 타입 - 기본은 정해진 시간 지나 취소되는 것
			if(call_type ==3){
				room_status = 6;

				// 수신자가 취소
			}else if (call_type ==1){
				room_status = 62;
				// 발신자가 취소
			}else if (call_type ==0){
				room_status = 61;
			}else{
				room_status = call_type;
			}
			roomDto.setFinish_type(room_status);
			int result = liveCallMapper.updateRoomStatus(roomDto);	// 룸 상태 변경

			if(result == 1) {

				if (room_status ==6){
					// 부재중 통화 리워드 이벤트 기간인 경우
					// 하루 최대 100 포인트 지급
					giveAbsentCallPoint(room_id);
				}
				resultDto.setResult(Utils.SUCCESS);

				if(call_type < 3) {	//3은 시간 지연 취소
					String sendPushKey = "";

					RoomPushKeyDTO roomPushDto = liveCallMapper.getRoomPushKey(room_id);

					if(call_type == 0) {	//발신자가 취소
						sendPushKey = roomPushDto.getPartner_push_key();
					}else if(call_type == 1) {	//수신자가 취소
						sendPushKey = roomPushDto.getMy_push_key();
					}

					pushService.sendDataPush(sendPushKey, PushType.VOICECANCEL.getTypeCd(), "", "", "");
				}
			}
		}

		return resultDto;
	}

	@Override
	public DefaultDTO callOk(String room_id, int req_user_no) {
		DefaultDTO resultDto = new DefaultDTO();

		RoomPushKeyDTO roomPushDto = liveCallMapper.getRoomPushKey(room_id);
		// 방 만든 사람(상대방)에게 LIVEOK PUSH를 보냄
		pushService.sendDataPush(roomPushDto.getMy_push_key(), PushType.LIVEOK.getTypeCd(), req_user_no + "", "", "");

		resultDto.setResult(Utils.SUCCESS);

		return resultDto;
	}

	@Override
	public DefaultDTO voiceCallOk(String room_id, int req_user_no) {
		DefaultDTO resultDto = new DefaultDTO();

		RoomPushKeyDTO roomPushDto = liveCallMapper.getRoomPushKey(room_id);
		pushService.sendDataPush(roomPushDto.getMy_push_key(), PushType.VOICEOK.getTypeCd(), req_user_no + "", "", "");

		resultDto.setResult(Utils.SUCCESS);

		return resultDto;
	}

	@Override
	public DefaultDTO insertStar(LiveStarGradeDTO dto) {
		DefaultDTO resultDto = new DefaultDTO();

		int result = liveCallMapper.insertStar(dto);

		if(result == 1) {
			resultDto.setResult(Utils.SUCCESS);
		}

		return resultDto;
	}

	@Override
	public DefaultDTO callStart(String room_id) {
		DefaultDTO resultDto = new DefaultDTO();

		LiveTimeDTO dto = new LiveTimeDTO();
		dto.setRoom_id(room_id);
		dto.setStart_time(Utils.getNowTime());
		dto.setRoom_status(1);

		// 통화 수신자(callStart API 요청자)가 방의 시작 시간을 수정한다.
		int result = liveCallMapper.setLiveTime(dto);
		// 상대방, 나의 push key 가져옴
		RoomPushKeyDTO roomPushDto = liveCallMapper.getRoomPushKey(room_id);

		if(result == 1) {
			// LIVESTART PUSH를 상대방에게 보냄
			pushService.sendDataPush(roomPushDto.getPartner_push_key(), PushType.LIVESTART.getTypeCd(), "", "", "");
			resultDto.setResult(Utils.SUCCESS);

			IsLiveDTO isDto = new IsLiveDTO();
			isDto.setUser_no(roomPushDto.getMy_user_no());
			isDto.setConnect_user_no(roomPushDto.getPartner_user_no());
			// 영상통화 대기자에서 상대방이 연결된 사람이 있다고 수정함
			liveCallMapper.isLive(isDto); //웨이팅 룸 라이브로 전환

			isDto = new IsLiveDTO();
			isDto.setUser_no(roomPushDto.getPartner_user_no());
			isDto.setConnect_user_no(roomPushDto.getMy_user_no());
			// 영상통화 대기자 목록에서 내가 연결된 사람이 있다고 수정함
			liveCallMapper.isLive(isDto); //웨이팅 룸 라이브로 전환

		}else {
			pushService.sendDataPush(roomPushDto.getPartner_push_key(), PushType.LIVECANCEL.getTypeCd(), "", "", "");
		}

		return resultDto;
	}

	@Override
	public DefaultDTO callStartV2(String room_id) {
		DefaultDTO resultDto = new DefaultDTO();

		LiveTimeDTO dto = new LiveTimeDTO();
		dto.setRoom_id(room_id);
		dto.setStart_time(Utils.getNowTime());
		dto.setRoom_status(1);

		// 통화 수신자(callStart API 요청자)가 방의 시작 시간을 수정한다.
		int result = liveCallMapper.setLiveTime(dto);
		// 상대방, 나의 push key 가져옴
		RoomPushKeyDTO roomPushDto = liveCallMapper.getRoomPushKey(room_id);

		if(result == 1) {
			// LIVESTART PUSH를 상대방에게 보냄
			pushService.sendDataPush(roomPushDto.getPartner_push_key(), PushType.LIVESTART.getTypeCd(), "", "", "");

			resultDto.setResult(Utils.SUCCESS);

			IsLiveDTO isDto = new IsLiveDTO();
			isDto.setUser_no(roomPushDto.getMy_user_no());
			isDto.setConnect_user_no(roomPushDto.getPartner_user_no());
			// 영상통화 대기자에서 상대방이 연결된 사람이 있다고 수정함
			liveCallMapper.isLive(isDto); //웨이팅 룸 라이브로 전환

			isDto = new IsLiveDTO();
			isDto.setUser_no(roomPushDto.getPartner_user_no());
			isDto.setConnect_user_no(roomPushDto.getMy_user_no());
			// 영상통화 대기자 목록에서 내가 연결된 사람이 있다고 수정함
			liveCallMapper.isLive(isDto); //웨이팅 룸 라이브로 전환

		}else {
			pushService.sendDataPush(roomPushDto.getPartner_push_key(), PushType.LIVECANCEL.getTypeCd(), "", "", "");
		}
		return resultDto;
	}

	@Override
	public DefaultDTO voiceCallStart(String room_id) {
		DefaultDTO resultDto = new DefaultDTO();

		LiveTimeDTO dto = new LiveTimeDTO();
		dto.setRoom_id(room_id);
		dto.setStart_time(Utils.getNowTime());
		dto.setRoom_status(1);

		int result = liveCallMapper.setLiveTime(dto);
		RoomPushKeyDTO roomPushDto = liveCallMapper.getRoomPushKey(room_id);

		if(result == 1) {
			pushService.sendDataPush(roomPushDto.getPartner_push_key(), PushType.VOICESTART.getTypeCd(), "", "", "");
			resultDto.setResult(Utils.SUCCESS);

			IsLiveDTO isDto = new IsLiveDTO();
			isDto.setUser_no(roomPushDto.getMy_user_no());
			isDto.setConnect_user_no(roomPushDto.getPartner_user_no());
			liveCallMapper.isLive(isDto); //웨이팅 룸 라이브로 전환

			isDto = new IsLiveDTO();
			isDto.setUser_no(roomPushDto.getPartner_user_no());
			isDto.setConnect_user_no(roomPushDto.getMy_user_no());
			liveCallMapper.isLive(isDto); //웨이팅 룸 라이브로 전환

		}else {
			pushService.sendDataPush(roomPushDto.getPartner_push_key(), PushType.VOICECANCEL.getTypeCd(), "", "", "");
		}

		return resultDto;
	}

	@Override
	public UserPointDTO liveUsePoint(int user_no, String room_id, boolean endFlag) {	//라이브 진행 시간과 룸번호로 포인트 실시간 차감
		UserPointDTO resultDto = new UserPointDTO();

		RoomTimeDTO roomTimeDto = liveCallMapper.getRoomTime(room_id);
		if(endFlag) { //종료 플러그 true시 영통 종료 등록
			LiveTimeDTO dto = new LiveTimeDTO();
			dto.setRoom_id(room_id);
			dto.setRoom_status(2); // -1 : 취소 / 0 : 대기 / 1 : 통화중 / 2 : 통화종료

			// end_time 등록여부 확인 후 업데이트
			String endTime = roomTimeDto.getEnd_time().substring(0, 10);
			if("2012-10-06".equals(endTime)) {
				dto.setEnd_time(Utils.getNowTime());
			}

			// start_time
			String startTime = roomTimeDto.getStart_time().substring(0, 10);
			if("2012-10-06".equals(startTime)) {
				dto.setRoom_status(-1); // -1 : 취소 / 0 : 대기 / 1 : 통화중 / 2 : 통화종료
				dto.setStart_time(dto.getEnd_time());
			}
			liveCallMapper.setLiveTime(dto);
			roomTimeDto = liveCallMapper.getRoomTime(room_id);

//			IsLiveDTO isLiveDto = new IsLiveDTO();
//			isLiveDto.setConnect_user_no(-1);
//			isLiveDto.setUser_no(user_no);
//			
//			liveCallMapper.isLive(isLiveDto);
		}

		if(roomTimeDto.getRoom_status() != -1) {
			UserPointDTO pointdto = userMapper.getMyPoint(user_no);
			int myPoint = pointdto.getUser_point();			//현재 포인트
			String startLiveTime = roomTimeDto.getStart_time();		//영통 시작 시간
			int liveSecond = Utils.dateDiffSec(startLiveTime);						//영통 진행 시간 (초)

			LiveHistoryDTO dto = new LiveHistoryDTO();
			dto.setRoom_id(room_id);
			dto.setUser_no(user_no);
			int liveUsePoint = liveCallMapper.getLiveUsePoint(dto);	//현재까지 사용한 포인트

			int usePoint = 0;	//사용할 포인트
			if(liveSecond < 11) {	//10초 부터 1800 포인트 차감 (네트워크 1초 고려하여 10초)
				usePoint = liveSecond * 30;
			}else {
				int min = (int) Math.ceil((double) liveSecond/(double) 60);
				usePoint =  min * 1800;
			}

			usePoint += liveUsePoint;

			if(myPoint < usePoint) {
				resultDto.setError_code("1");//포인트 부족
				resultDto.setReason("포인트가 부족하여 영상통화를 종료 합니다");
				resultDto.setUser_point(myPoint);
			}else{
				if(usePoint > 0) {
					int minusPoint = myPoint - usePoint;
					RoomPushKeyDTO roomInfoDto = liveCallMapper.getRoomPushKey(room_id);

					int deductionFreePoint = (pointdto.getUser_free_point() >= usePoint ? usePoint : pointdto.getUser_free_point());
					int deductionPayPoint = (pointdto.getUser_free_point() >= usePoint ? 0 : usePoint - deductionFreePoint);

					int partner_user_no = roomInfoDto.getMy_user_no() == user_no ? roomInfoDto.getPartner_user_no():roomInfoDto.getMy_user_no();

					UseHistoryDTO hDto = new UseHistoryDTO();
					hDto.setUser_no(user_no);
					hDto.setUse_etc(room_id);
					hDto.setUse_content("영상 통화 차감");
					hDto.setUse_etc_user_no(partner_user_no);

					PayPointByCallDTO payPointByCallDto = new PayPointByCallDTO();

					payPointByCallDto.setUser_no(user_no);
					payPointByCallDto.setRoom_id(room_id);
					payPointByCallDto.setPay_point(deductionPayPoint);
					payPointByCallDto.setPay_free_point(deductionFreePoint);
					payPointByCallDto.setPartner_user_no(partner_user_no);
					liveCallMapper.uspPayPointByCall(payPointByCallDto);

					if (payPointByCallDto.getResult_code() == 1){
						resultDto.setResult(Utils.SUCCESS);
						resultDto.setReason(payPointByCallDto.getResult_msg());
					}else{
						resultDto.setReason("사용 내역, 유저 포인트 등록 에러 (DB)");
						resultDto.setError_code("3");
					}

					/*
					int result = 0;
					if(deductionFreePoint > 0) {
						hDto.setUse_cnt(-deductionFreePoint);
						hDto.setUse_type(2);	//0 : 유료포인트 / 1 : 캐쉬 / 2: 무료포인트
						result = liveCallMapper.insertUseHistory(hDto);
					}
					if(deductionPayPoint > 0) {
						hDto.setUse_cnt(-deductionPayPoint);
						hDto.setUse_type(0);	//0 : 유료포인트 / 1 : 캐쉬 / 2: 무료포인트
						result = liveCallMapper.insertUseHistory(hDto);
					}
								
					if(result == 1) {	//포인트 사용 내역 등록
						UserPointDTO updateDto = new UserPointDTO();
						updateDto.setNo(user_no);
	//					updateDto.setUser_point(minusPoint);
						updateDto.setUser_free_point(pointdto.getUser_free_point() - deductionFreePoint);
						updateDto.setUser_pay_point(pointdto.getUser_pay_point() - deductionPayPoint);
						
						int uResult = userMapper.setPoint(updateDto);
						
						if(uResult == 1) {	//사용자 포인트 차감
							resultDto.setResult(Utils.SUCCESS);
							resultDto.setUser_point(minusPoint);
							resultDto.setUse_point(usePoint);
						}else {
							resultDto.setReason("사용 내역 등록 에러 (DB)");
							resultDto.setError_code("2");
						}				
					}else {
						resultDto.setReason("유저 포인트 등록 에러 (DB)");
						resultDto.setError_code("3");
					}
					 */
				}else {
					resultDto.setReason("차감 될 포인트가 없음");
					resultDto.setError_code("4");
				}
			}
		} else {
			resultDto.setReason("통화실패");
			resultDto.setError_code("5");
		}

		return resultDto;
	}

	@Override
	public GetCashDTO liveGetCash(int user_no, String room_id) {
		GetCashDTO resultDto = new GetCashDTO();

		RoomTimeDTO roomTimeDto = liveCallMapper.getRoomTime(room_id);	//영통 시간

		String startTime = roomTimeDto.getStart_time().substring(0, 10);
		String endTime = roomTimeDto.getEnd_time().substring(0, 10);

		if("2012-10-06".equals(startTime) || "2012-10-06".equals(endTime)) {
			LiveTimeDTO dto = new LiveTimeDTO();
			dto.setRoom_id(room_id);
			// end_time 등록여부 확인
			if("2012-10-06".equals(endTime)) {
				dto.setEnd_time(Utils.getNowTime());
				dto.setRoom_status(2); // -1 : 취소 / 0 : 대기 / 1 : 통화중 / 2 : 통화종료
			}
			// start_time 등록여부 확인
			if("2012-10-06".equals(startTime)) {
				if("2012-10-06".equals(endTime)) {
					dto.setStart_time(dto.getEnd_time());
				} else {
					dto.setStart_time(liveCallMapper.getEndTime(room_id));
				}
				dto.setRoom_status(-1); // -1 : 취소 / 0 : 대기 / 1 : 통화중 / 2 : 통화종료
			}

			liveCallMapper.setLiveTime(dto);	//영통 종료 (남여 둘다 한번씩 함)
			roomTimeDto = liveCallMapper.getRoomTime(room_id);	//영통 시간
		}

		if(roomTimeDto.getRoom_status() != -1) {
			int liveSecond = Utils.timeDiffSec(roomTimeDto.getStart_time(), roomTimeDto.getEnd_time());				//영통 시간 (초)

			int user_cash = userMapper.getMyCash(user_no).getUser_cash();

			if(liveSecond < 11) {	//10초 까지는 캐쉬 적립 안됌 (9초까지 지만 네트워크 시간 1초 고려)
				resultDto.setGet_cash(0);
				resultDto.setUser_cash(user_cash);
				resultDto.setResult(Utils.SUCCESS);
			}else{
				int plusCash = liveSecond * 10;

				resultDto.setGet_cash(plusCash);
				resultDto.setUser_cash(user_cash + plusCash);

				RoomPushKeyDTO roomInfoDto = liveCallMapper.getRoomPushKey(room_id);
				int partner_user_no = roomInfoDto.getMy_user_no() == user_no ? roomInfoDto.getPartner_user_no():roomInfoDto.getMy_user_no();

				//s:아래 캐시 지급 대체 20200903
				GetCashByCallDTO getCashByCallDto = new GetCashByCallDTO();

				getCashByCallDto.setUser_no(user_no);
				getCashByCallDto.setRoom_id(room_id);
				getCashByCallDto.setGet_cash(plusCash);
				getCashByCallDto.setPartner_user_no(partner_user_no);
				liveCallMapper.uspGetCashByCall(getCashByCallDto);

				resultDto.setResult(Utils.SUCCESS); //캐시 지급 여부랑 관계없이 API result:SUCCESS 리턴
				resultDto.setReason(getCashByCallDto.getResult_msg());
				//e:아래 캐시 지급 대체 20200903



			}
		} else {
			resultDto.setResult(Utils.SUCCESS);
		}

		return resultDto;
	}


	@Override
	public PresentPointDTO pointPresentMC(int user_no, int to_user_no, int point, String etc) {
		PresentPointDTO resultDto = new PresentPointDTO();

		if (to_user_no == -1){
			// 방에 대한 정보 가져온다.
			RoomPushKeyDTO roomPushKeyDTO = liveCallMapper.getRoomPushKey(etc);

			if (roomPushKeyDTO !=null){
				if (user_no == roomPushKeyDTO.getMy_user_no()){
					to_user_no = roomPushKeyDTO.getPartner_user_no();
				}else if (user_no ==roomPushKeyDTO.getPartner_user_no()){
					to_user_no = roomPushKeyDTO.getMy_user_no();
				}
			}

		}

		UserInfoDTO myInfo = userMapper.getUserInfo(user_no);
		if(myInfo.getUser_pay_point() >= point || point < 0) {
			UserInfoDTO partnerInfo = userMapper.getUserInfo(to_user_no);

			String gender = userMapper.selectUserGender(to_user_no);	//선물받는사람 성별 가져오기
			GetCashDTO cashDto = new GetCashDTO();
			int user_cash = 0;

			UseHistoryDTO preDto = new UseHistoryDTO();
			preDto.setUse_cnt(-point);
			preDto.setUse_content("포인트 선물");
			preDto.setUse_type(0);
			preDto.setUser_no(user_no);
			preDto.setUse_etc("PRESENT_" + etc);
			preDto.setUse_etc_user_no(to_user_no);

			UseHistoryDTO toDto = new UseHistoryDTO();
			toDto.setUse_cnt(point);
			toDto.setUse_content("포인트 선물 받음");
			toDto.setUse_type(0);
			toDto.setUser_no(to_user_no);
			toDto.setUse_etc("PRESENT_" + etc);
			toDto.setUse_etc_user_no(user_no);

			if("여성".equals(gender)){	// 여성이면 toDto 의 use_type 바꿔줌 0 -> 1
				cashDto = userMapper.getMyCash(to_user_no);
				user_cash = (int)(point*0.5) + cashDto.getUser_cash();	//포인트 * 0.4 + 보유캐시

				toDto.setUse_content("캐시 선물 받음");	// 히스토리 값 변경
				toDto.setUse_type(1);
				toDto.setUse_cnt((int)(point*0.5));

				cashDto.setNo(to_user_no);	//포인트 업데이트
				cashDto.setUser_cash(user_cash);
			}

			int preResult = liveCallMapper.insertUseHistory(preDto);	//포인트 쓴 사람 내역
			int toResult = liveCallMapper.insertUseHistory(toDto);	//포인트 받은 사람 내역

			if(preResult == 1 && toResult == 1) {

				UserPointDTO pointDto = new UserPointDTO();
				pointDto.setNo(user_no);
				pointDto.setUser_pay_point(myInfo.getUser_pay_point() - point);
				pointDto.setUser_free_point(myInfo.getUser_free_point());

				userMapper.setPoint(pointDto);	//선물자 포인트 차감

				if("여성".equals(gender)){
					userMapper.setCash(cashDto);	//선물 받은 자 캐시 적립
				}else {
					pointDto = new UserPointDTO();
					pointDto.setNo(to_user_no);
					pointDto.setUser_pay_point(partnerInfo.getUser_pay_point() + point);
					pointDto.setUser_free_point(partnerInfo.getUser_free_point());

					userMapper.setPoint(pointDto);	//선물 받은 자 포인트 적립
				}

				resultDto.setGet_point(point);
				resultDto.setUser_point(myInfo.getUser_point() - point);
				resultDto.setResult(Utils.SUCCESS);

				String user_nick_name = myInfo.getUser_nick_name();

				JSONObject dataJson = new JSONObject();
				dataJson.put("user_no", user_no);
				dataJson.put("user_nick_name", user_nick_name);
				dataJson.put("get_point", point);
				dataJson.put("my_point", partnerInfo.getUser_point() + point);

				pushService.sendDataPush(partnerInfo.getUser_push_key(), PushType.POINTGET.getTypeCd(), dataJson.toString(), "", ""); //선물 받은 상대방에게 푸쉬 발송

				// 채팅 메세지에 저장함
				// chat_type 3는 선물 했다는 것을 저장한다는 의미임
				ChatDTO chatDto = new ChatDTO();
				chatDto.setChat_text(Utils.setCntComma(point));
				chatDto.setTo_user_no(to_user_no);
				chatDto.setFrom_user_no(user_no);
				chatDto.setChat_type(3);
				chatDto.setTo_visible(0);
				int chatResult = chatMapper.sendChatV3(chatDto);

			}
		} else {
			resultDto.setReason("유료 포인트 부족으로 인하여 포인트 선물 실패");
			resultDto.setError_code("1");
		}

		return resultDto;
	}


	@Override
	public PresentPointDTO pointPresentV4(int user_no, int to_user_no, int point, String etc) {
		PresentPointDTO resultDto = new PresentPointDTO();

		if (to_user_no == -1){
			// 방에 대한 정보 가져온다.
			RoomPushKeyDTO roomPushKeyDTO = liveCallMapper.getRoomPushKey(etc);

			if (roomPushKeyDTO !=null){
				if (user_no == roomPushKeyDTO.getMy_user_no()){
					to_user_no = roomPushKeyDTO.getPartner_user_no();
				}else if (user_no ==roomPushKeyDTO.getPartner_user_no()){
					to_user_no = roomPushKeyDTO.getMy_user_no();
				}
			}
		}

		UserInfoDTO myInfo = userMapper.getUserInfo(user_no);
		if(myInfo.getUser_pay_point() >= point || point < 0) {
			UserInfoDTO partnerInfo = userMapper.getUserInfo(to_user_no);

			String gender = userMapper.selectUserGender(to_user_no);	//선물받는사람 성별 가져오기
			GetCashDTO cashDto = new GetCashDTO();
			int user_cash = 0;

			UseHistoryDTO preDto = new UseHistoryDTO();
			preDto.setUse_cnt(-point);
			preDto.setUse_content("포인트 선물");
			preDto.setUse_type(0);
			preDto.setUser_no(user_no);
			preDto.setUse_etc("PRESENT_" + etc);
			preDto.setUse_etc_user_no(to_user_no);

			UseHistoryDTO toDto = new UseHistoryDTO();
			toDto.setUse_cnt(point);
			toDto.setUse_content("포인트 선물 받음");
			toDto.setUse_type(0);
			toDto.setUser_no(to_user_no);
			toDto.setUse_etc("PRESENT_" + etc);
			toDto.setUse_etc_user_no(user_no);

			if("여성".equals(gender)){	// 여성이면 toDto 의 use_type 바꿔줌 0 -> 1
				cashDto = userMapper.getMyCash(to_user_no);
				user_cash = (int)(point*0.7) + cashDto.getUser_cash();	//포인트 * 0.4 + 보유캐시

				toDto.setUse_content("캐시 선물 받음");	// 히스토리 값 변경
				toDto.setUse_type(1);
				toDto.setUse_cnt((int)(point*0.7));

				cashDto.setNo(to_user_no);	//포인트 업데이트
				cashDto.setUser_cash(user_cash);
			}

			int preResult = liveCallMapper.insertUseHistory(preDto);	//포인트 쓴 사람 내역
			int toResult = liveCallMapper.insertUseHistory(toDto);	//포인트 받은 사람 내역

			if(preResult == 1 && toResult == 1) {

				UserPointDTO pointDto = new UserPointDTO();
				pointDto.setNo(user_no);
				pointDto.setUser_pay_point(myInfo.getUser_pay_point() - point);
				pointDto.setUser_free_point(myInfo.getUser_free_point());

				userMapper.setPoint(pointDto);	//선물자 포인트 차감

				if("여성".equals(gender)){
					userMapper.setCash(cashDto);	//선물 받은 자 캐시 적립
				}else {
					pointDto = new UserPointDTO();
					pointDto.setNo(to_user_no);
					pointDto.setUser_pay_point(partnerInfo.getUser_pay_point() + point);
					pointDto.setUser_free_point(partnerInfo.getUser_free_point());

					userMapper.setPoint(pointDto);	//선물 받은 자 포인트 적립
				}

				resultDto.setGet_point(point);
				resultDto.setUser_point(myInfo.getUser_point() - point);
				resultDto.setResult(Utils.SUCCESS);

				String user_nick_name = myInfo.getUser_nick_name();

				JSONObject dataJson = new JSONObject();
				dataJson.put("user_no", user_no);
				dataJson.put("user_nick_name", user_nick_name);
				dataJson.put("get_point", point);
				dataJson.put("my_point", partnerInfo.getUser_point() + point);

				pushService.sendDataPush(partnerInfo.getUser_push_key(), PushType.POINTGET.getTypeCd(), dataJson.toString(), "", ""); //선물 받은 상대방에게 푸쉬 발송

				// 채팅 메세지에 저장함
				// chat_type 3는 선물 했다는 것을 저장한다는 의미임
				ChatDTO chatDto = new ChatDTO();
				chatDto.setChat_text(Utils.setCntComma(point));
				chatDto.setTo_user_no(to_user_no);
				chatDto.setFrom_user_no(user_no);
				chatDto.setChat_type(3);
				chatDto.setTo_visible(0);
				int chatResult = chatMapper.sendChatV3(chatDto);

			}
		} else {
			resultDto.setReason("유료 포인트 부족으로 인하여 포인트 선물 실패");
			resultDto.setError_code("1");
		}

		return resultDto;
	}

	@Override
	public PresentPointDTO pointPresentV5(int user_no, int to_user_no, int point, String etc) {
		PresentPointDTO resultDto = new PresentPointDTO();

		if (to_user_no == -1){
			// 방에 대한 정보 가져온다.
			RoomPushKeyDTO roomPushKeyDTO = liveCallMapper.getRoomPushKey(etc);

			if (roomPushKeyDTO !=null){
				if (user_no == roomPushKeyDTO.getMy_user_no()){
					to_user_no = roomPushKeyDTO.getPartner_user_no();
				}else if (user_no ==roomPushKeyDTO.getPartner_user_no()){
					to_user_no = roomPushKeyDTO.getMy_user_no();
				}
			}

		}

		UserInfoDTO myInfo = userMapper.getUserInfo(user_no);
		if(myInfo.getUser_pay_point() >= point || point < 0) {
			UserInfoDTO partnerInfo = userMapper.getUserInfo(to_user_no);

			String gender = userMapper.selectUserGender(to_user_no);	//선물받는사람 성별 가져오기
			GetCashDTO cashDto = new GetCashDTO();
			int user_cash = 0;

			UseHistoryDTO preDto = new UseHistoryDTO();
			preDto.setUse_cnt(-point);
			preDto.setUse_content("포인트 선물");
			preDto.setUse_type(0);
			preDto.setUser_no(user_no);
			preDto.setUse_etc("PRESENT_" + etc);
			preDto.setUse_etc_user_no(to_user_no);

			UseHistoryDTO toDto = new UseHistoryDTO();
			toDto.setUse_cnt(point);
			toDto.setUse_content("포인트 선물 받음");
			toDto.setUse_type(0);
			toDto.setUser_no(to_user_no);
			toDto.setUse_etc("PRESENT_" + etc);
			toDto.setUse_etc_user_no(user_no);

			if("여성".equals(gender)){	// 여성이면 toDto 의 use_type 바꿔줌 0 -> 1
				cashDto = userMapper.getMyCash(to_user_no);
				user_cash = (int)(point * Utils.NMC_PRESENT_TO_CASH_RATE) + cashDto.getUser_cash();	//포인트 * 전환율 + 보유캐시

				toDto.setUse_content("캐시 선물 받음");	// 히스토리 값 변경
				toDto.setUse_type(1);
				toDto.setUse_cnt((int)(point * Utils.NMC_PRESENT_TO_CASH_RATE));

				cashDto.setNo(to_user_no);	//포인트 업데이트
				cashDto.setUser_cash(user_cash);
			}

			int preResult = liveCallMapper.insertUseHistory(preDto);	//포인트 쓴 사람 내역
			int toResult = liveCallMapper.insertUseHistory(toDto);	//포인트 받은 사람 내역

			if(preResult == 1 && toResult == 1) {

				UserPointDTO pointDto = new UserPointDTO();
				pointDto.setNo(user_no);
				pointDto.setUser_pay_point(myInfo.getUser_pay_point() - point);
				pointDto.setUser_free_point(myInfo.getUser_free_point());

				userMapper.setPoint(pointDto);	//선물자 포인트 차감

				if("여성".equals(gender)){
					userMapper.setCash(cashDto);	//선물 받은 자 캐시 적립
				}else {
					pointDto = new UserPointDTO();
					pointDto.setNo(to_user_no);
					pointDto.setUser_pay_point(partnerInfo.getUser_pay_point() + point);
					pointDto.setUser_free_point(partnerInfo.getUser_free_point());

					userMapper.setPoint(pointDto);	//선물 받은 자 포인트 적립
				}

				resultDto.setGet_point(point);
				resultDto.setUser_point(myInfo.getUser_point() - point);
				resultDto.setResult(Utils.SUCCESS);

				String user_nick_name = myInfo.getUser_nick_name();

				JSONObject dataJson = new JSONObject();
				dataJson.put("user_no", user_no);
				dataJson.put("user_nick_name", user_nick_name);
				dataJson.put("get_point", point);
				dataJson.put("my_point", partnerInfo.getUser_point() + point);

				pushService.sendDataPush(partnerInfo.getUser_push_key(), PushType.POINTGET.getTypeCd(), dataJson.toString(), "", ""); //선물 받은 상대방에게 푸쉬 발송

				// 채팅 메세지에 저장함
				// chat_type 3는 선물 했다는 것을 저장한다는 의미임
				ChatDTO chatDto = new ChatDTO();
				chatDto.setChat_text(Utils.setCntComma(point));
				chatDto.setTo_user_no(to_user_no);
				chatDto.setFrom_user_no(user_no);
				chatDto.setChat_type(3);
				chatDto.setTo_visible(0);
				int chatResult = chatMapper.sendChatV3(chatDto);

			}
		} else {
			resultDto.setReason("유료 포인트 부족으로 인하여 포인트 선물 실패");
			resultDto.setError_code("1");
		}

		return resultDto;
	}

	@Override
	public UseHistoryListDTO getUseList(int user_no, int use_type) {
		UseHistoryListDTO resultDto = new UseHistoryListDTO();

		UseHistoryDTO dto = new UseHistoryDTO();
		dto.setUser_no(user_no);
		dto.setUse_type(use_type);

		resultDto.setUseList(liveCallMapper.getUseList(dto));
		resultDto.setResult(Utils.SUCCESS);

		return resultDto;
	}

	@Override
	public CallHistoryListDTO getCallHistoryList(int user_no) {
		CallHistoryListDTO resultDto = new CallHistoryListDTO();

		CallHistoryDTO dto = new CallHistoryDTO();
		dto.setUser_no(user_no);

		resultDto.setCallList(liveCallMapper.getCallHistoryList(dto));
		resultDto.setResult(Utils.SUCCESS);
		return resultDto;
	}

	@Override
	public HeartHistoryListDTO getHeartHistoryList(int user_no) {
		HeartHistoryListDTO resultDto = new HeartHistoryListDTO();

		HeartHistoryDTO dto = new HeartHistoryDTO();
		dto.setUser_no(user_no);

		resultDto.setHeartList(liveCallMapper.getHeartHistoryList(dto));
		resultDto.setResult(Utils.SUCCESS);
		return resultDto;
	}

	@Override
	public LiveInfoDTO liveInfo(int user_no, int to_user_no) {
		LikeDTO dto = new LikeDTO();
		dto.setUser_no(user_no);
		dto.setTo_user_no(to_user_no);

		LiveInfoDTO resultDto = liveCallMapper.liveInfo(dto);
		if(null != resultDto) {
			resultDto.setResult(Utils.SUCCESS);
		}

		return resultDto;
	}

	@Override
	public RoomStatusResultDTO getRoomStatus(String room_id) {
		RoomStatusResultDTO resultdto = new RoomStatusResultDTO();

		RoomStatusDTO dto = new RoomStatusDTO();
		dto = liveCallMapper.getRoomStatus(room_id);
		if(null != dto) {
			resultdto.setResult(Utils.SUCCESS);
			resultdto.setRoomInfo(dto);
		}
		return resultdto;
	}

	@Override
	public UserBalanceDTO callFinish(int user_no, String room_id, int use_seconds, int finish_type) { // 대화 종료
		//live room  업데이트
		CallFinishDTO callFinishDto = new CallFinishDTO();
		callFinishDto.setRoom_id(room_id);
		callFinishDto.setFinish_user_no(user_no);
		callFinishDto.setFinish_type(finish_type);
		callFinishDto.setFinish_use_seconds(use_seconds);
		int resultUpdate = liveCallMapper.updateCallFinish(callFinishDto);

		//response
		UserBalanceDTO resultDto = new UserBalanceDTO();
		UserInfoDTO userInfoDto = userMapper.getUserInfo(user_no);
		resultDto.setUser_no(user_no);
		resultDto.setUser_point(userInfoDto.getUser_point());
		resultDto.setUser_cash(userInfoDto.getUser_cash());
		resultDto.setUser_gender(userInfoDto.getUser_gender());
		//if (resultUpdate == 1){
			resultDto.setResult(Utils.SUCCESS);
		//}else{
		//	resultDto.setReason("업데이트 실패"); //종료를 통보 받은 회원의 use_seconds 참고용.
		//}
		return resultDto;
	}

	@Override
	public UserBalanceDTO callFinishV2(int user_no, String room_id, int use_seconds, int finish_type) { // 대화 종료

		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String ip = req.getHeader("X-FORWARDED-FOR");
		if (ip == null)
			ip = req.getRemoteAddr();

		// 사용자 요청 데이터를 로그를 파일로 저장하는 코드
		PrintStream ps = null;
		FileOutputStream fos=null;
		Date today = new Date();

		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
		String dateStr = date.format(today);
		String timeStr = time.format(today);

		try {
			fos = new FileOutputStream(API_LOG_PATH+"callfinish_"+dateStr+".log",true); // error.log파일에 출력 준비
			ps=new PrintStream(fos); // err의 출력을 화면이 아닌, error.log파일로 변경
			System.setErr(ps);
			System.err.println("API : callFinishV2 -----------------------------------");
			System.err.println("[" + dateStr+ " "+timeStr+"] "+ip);  // 사용자 ip
			System.err.println("user_no: " + user_no);
			System.err.println("room_id: " + room_id);
			System.err.println("use_seconds: " + use_seconds);
			System.err.println("finish_type: " + finish_type);
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		}

	    // 상대방 고유 번호 가져오기
		CallChkUserDTO callChkUserDTO = new CallChkUserDTO();
		callChkUserDTO.setRoom_id(room_id);
		callChkUserDTO = liveCallMapper.getRoomInfo(callChkUserDTO);
		if (callChkUserDTO ==null) System.err.println("getRoomInfo 에러 ");

		int to_user_no = 0;
		int cash_per_seconds = Utils.MC_CASH_PER_SECONDS;

		// 채팅 메세지에 저장함
		if (user_no == callChkUserDTO.getMy_user_no()) {
			to_user_no = callChkUserDTO.getPartner_user_no();
		} else {
			to_user_no = callChkUserDTO.getMy_user_no();
		}

		// 내가 남성 경우 에이전트 포인트를 가져온다.
		UserInfoDTO userInfoDto = userMapper.getUserInfo(user_no);
		if (userInfoDto ==null) System.err.println("getUserInfo 에러 user_no: "+user_no);

		if (userInfoDto.getUser_gender().equals("남성")){

			// 상대방의 에이전트 포인트를 가져온다. (알바인 경우만)
			UserInfoDTO partnerInfoDto = userMapper.getUserInfo(to_user_no);
			if (partnerInfoDto ==null) System.err.println("getUserInfo 에러 to_user_no: "+to_user_no);

			// 상대방이 알바 + 여성인 경우만
			if (partnerInfoDto.getUser_part_time() ==1 && partnerInfoDto.getUser_gender().equals("여성")){
				String agent_id = partnerInfoDto.getAgent_id();
				AgentDTO agentDTO =  userMapper.getAgentCashInfo(agent_id);
				if (agentDTO!= null){
					if (agentDTO.getCash_per_second() > 0)
						cash_per_seconds = agentDTO.getCash_per_second();

				}else{
					System.err.println("상대방 알바 getAgentCashInfo 에러 agent_id: "+agent_id);
				}

			}

			// 내가 여성인 경우
		}else{

			// 내가 알바인 경우
			if (userInfoDto.getUser_part_time() ==1 && userInfoDto.getUser_gender().equals("여성")){
				String agent_id = userInfoDto.getAgent_id();
				AgentDTO agentDTO =  userMapper.getAgentCashInfo(agent_id);
				if (agentDTO!= null){
					if (agentDTO.getCash_per_second() > 0)
						cash_per_seconds = agentDTO.getCash_per_second();

				}else{
					System.err.println("내가 알바 getAgentCashInfo 에러 agent_id: "+agent_id);
				}
			}
		}


		//live room  업데이트
		CallFinishDTO callFinishDto = new CallFinishDTO();
		callFinishDto.setRoom_id(room_id);
		callFinishDto.setFinish_user_no(user_no);
		callFinishDto.setFinish_type(finish_type);
		callFinishDto.setFinish_use_seconds(use_seconds);
		callFinishDto.setCash_per_seconds(cash_per_seconds);
		callFinishDto.setPoint_per_seconds(Utils.MC_POINT_PER_SECONDS);
		liveCallMapper.updateCallFinishV2(callFinishDto);
//		System.err.println("updateCallFinishV2 "+resultUpdate);

		//response
		UserBalanceDTO resultDto = new UserBalanceDTO();
		UserInfoDTO _userInfoDto = userMapper.getUserInfo(user_no);
		if (_userInfoDto==null) System.err.println("after Callfiniish getUserInfo 에러 user_no: "+user_no);
		if (_userInfoDto!=null){
			resultDto.setUser_no(user_no);
			resultDto.setUser_point(_userInfoDto.getUser_point());
			resultDto.setUser_cash(_userInfoDto.getUser_cash());
			resultDto.setUser_gender(_userInfoDto.getUser_gender());
			resultDto.setResult(Utils.SUCCESS);

		}
		//if (resultUpdate == 1){

		//}else{
		//	resultDto.setReason("업데이트 실패"); //종료를 통보 받은 회원의 use_seconds 참고용.
		//}


		try {
			if(fos != null)
				fos.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
        // 채팅 데이터가 있는 경우
//        int cnt = chatMapper.getCallChat(room_id);
//		if (cnt ==0)  System.err.println("getCallChat 채팅데이터 없음 "+room_id);
//        if (cnt > 0) {
//        	System.err.println("getCallChat 채팅데이터 있음 "+room_id);
//			try {
//				if(fos != null)
//					fos.close();
//			} catch (IOException ioException) {
//				ioException.printStackTrace();
//			}
//            return resultDto;
//
//        }₩
//
//        // 채팅 데이터를 저장한다.
//        ChatDTO chatDto = new ChatDTO();
//        chatDto.setChat_text("영상 통화 종료");
//        chatDto.setChat_text_trans(null);
//        chatDto.setTo_user_no(to_user_no);
//        chatDto.setFrom_user_no(user_no);
//        chatDto.setChat_type(0);
//        chatDto.setTo_visible(0);
//        chatDto.setAbsent_room_id(room_id);
//        int chat_result = chatMapper.sendChatCallFinish(chatDto); //채팅 등록- banPush=false
//		if (chat_result==0) System.err.println("sendChatCallFinish 에러 ");
//
//		try {
//			if(fos != null)
//				fos.close();
//		} catch (IOException ioException) {
//			ioException.printStackTrace();
//		}
		return resultDto;
	}

	@Override
	public UserBalanceDTO callFinishMC(int user_no, String room_id, int use_seconds, int finish_type) { // 대화 종료

		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String ip = req.getHeader("X-FORWARDED-FOR");
		if (ip == null)
			ip = req.getRemoteAddr();

		// 사용자 요청 데이터를 로그를 파일로 저장하는 코드
		PrintStream ps = null;
		FileOutputStream fos=null;
		Date today = new Date();

		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
		String dateStr = date.format(today);
		String timeStr = time.format(today);

		try {
			fos = new FileOutputStream(API_LOG_PATH+"callfinish_"+dateStr+".log",true); // error.log파일에 출력 준비
			ps=new PrintStream(fos); // err의 출력을 화면이 아닌, error.log파일로 변경
			System.setErr(ps);
			System.err.println("API : callFinishMC -----------------------------------");
			System.err.println("[" + dateStr+ " "+timeStr+"] "+ip);  // 사용자 ip
			System.err.println("user_no: " + user_no);
			System.err.println("room_id: " + room_id);
			System.err.println("use_seconds: " + use_seconds);
			System.err.println("finish_type: " + finish_type);
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		}

		// 상대방 고유 번호 가져오기
		CallChkUserDTO callChkUserDTO = new CallChkUserDTO();
		callChkUserDTO.setRoom_id(room_id);
		callChkUserDTO = liveCallMapper.getRoomInfo(callChkUserDTO);
		if (callChkUserDTO ==null) System.err.println("getRoomInfo 에러 ");

		int to_user_no = 0;
		// 채팅 메세지에 저장함
		if (user_no == callChkUserDTO.getMy_user_no()) {
			to_user_no = callChkUserDTO.getPartner_user_no();
		} else {
			to_user_no = callChkUserDTO.getMy_user_no();
		}

		//live room  업데이트
		CallFinishDTO callFinishDto = new CallFinishDTO();
		callFinishDto.setRoom_id(room_id);
		callFinishDto.setFinish_user_no(user_no);
		callFinishDto.setFinish_type(finish_type);
		callFinishDto.setFinish_use_seconds(use_seconds);
		callFinishDto.setCash_per_seconds(Utils.MC_CASH_PER_SECONDS);
		callFinishDto.setPoint_per_seconds(Utils.MC_POINT_PER_SECONDS);
		liveCallMapper.updateCallFinishV2(callFinishDto);
//		System.err.println("updateCallFinishV2 "+resultUpdate);

		//response
		UserBalanceDTO resultDto = new UserBalanceDTO();
		UserInfoDTO _userInfoDto = userMapper.getUserInfo(user_no);
		if (_userInfoDto==null) System.err.println("after Callfiniish getUserInfo 에러 user_no: "+user_no);
		if (_userInfoDto!=null){
			resultDto.setUser_no(user_no);
			resultDto.setUser_point(_userInfoDto.getUser_point());
			resultDto.setUser_cash(_userInfoDto.getUser_cash());
			resultDto.setUser_gender(_userInfoDto.getUser_gender());
			resultDto.setResult(Utils.SUCCESS);
		}
		try {
			if(fos != null)
				fos.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
		return resultDto;
	}



	@Override
	public UserBalanceDTO voiceCallFinish(int user_no, String room_id, int use_seconds, int finish_type) { // 대화 종료
		//live room  업데이트
		CallFinishDTO callFinishDto = new CallFinishDTO();
		callFinishDto.setRoom_id(room_id);
		callFinishDto.setFinish_user_no(user_no);
		callFinishDto.setFinish_type(finish_type);
		callFinishDto.setFinish_use_seconds(use_seconds);
		int resultUpdate = liveCallMapper.updateVoiceCallFinish(callFinishDto);

		//response
		UserBalanceDTO resultDto = new UserBalanceDTO();
		UserInfoDTO userInfoDto = userMapper.getUserInfo(user_no);
		resultDto.setUser_no(user_no);
		resultDto.setUser_point(userInfoDto.getUser_point());
		resultDto.setUser_cash(userInfoDto.getUser_cash());
		resultDto.setUser_heart(userInfoDto.getUser_heart());
		resultDto.setUser_gender(userInfoDto.getUser_gender());
		//if (resultUpdate == 1){
		resultDto.setResult(Utils.SUCCESS);
		//}else{
		//	resultDto.setReason("업데이트 실패"); //종료를 통보 받은 회원의 use_seconds 참고용.
		//}
		return resultDto;
	}

	@Override
	public UserBalanceDTO voiceCallFinishV2(int user_no, String room_id, int use_seconds, int finish_type) { // 대화 종료
		//live room  업데이트
		CallFinishDTO callFinishDto = new CallFinishDTO();
		callFinishDto.setRoom_id(room_id);
		callFinishDto.setFinish_user_no(user_no);
		callFinishDto.setFinish_type(finish_type);
		callFinishDto.setFinish_use_seconds(use_seconds);
		callFinishDto.setCash_per_seconds(Utils.VOICE_CASH_PER_SECONDS);
		callFinishDto.setPoint_per_seconds(Utils.VOICE_POINT_PER_SECONDS);
		int resultUpdate = liveCallMapper.updateVoiceCallFinishV2(callFinishDto);

		//response
		UserBalanceDTO resultDto = new UserBalanceDTO();
		UserInfoDTO userInfoDto = userMapper.getUserInfo(user_no);
		resultDto.setUser_no(user_no);
		resultDto.setUser_point(userInfoDto.getUser_point());
		resultDto.setUser_cash(userInfoDto.getUser_cash());
		resultDto.setUser_heart(userInfoDto.getUser_heart());
		resultDto.setUser_gender(userInfoDto.getUser_gender());
		resultDto.setResult(Utils.SUCCESS);

		// 상대방 고유 번호 가져오기
		CallChkUserDTO callChkUserDTO = new CallChkUserDTO();
		callChkUserDTO.setRoom_id(room_id);
		callChkUserDTO = liveCallMapper.getRoomInfo(callChkUserDTO);

		int to_user_no = 0;
		if (callChkUserDTO !=null) {

			// 채팅 메세지에 저장함
			if (user_no == callChkUserDTO.getMy_user_no()) {
				to_user_no = callChkUserDTO.getPartner_user_no();
			} else {
				to_user_no = callChkUserDTO.getMy_user_no();
			}

			// 채팅 데이터가 있는 경우
			int cnt = chatMapper.getCallChat(room_id);
			if (cnt > 0) {
				return resultDto;
			}

			// 채팅 데이터를 저장한다.
			ChatDTO chatDto = new ChatDTO();
			chatDto.setChat_text("보이스팅 종료");
			chatDto.setChat_text_trans(null);
			chatDto.setTo_user_no(to_user_no);
			chatDto.setFrom_user_no(user_no);
			chatDto.setChat_type(0);
			chatDto.setTo_visible(0);
			chatDto.setAbsent_room_id(room_id);
			chatMapper.sendChatCallFinish(chatDto); //채팅 등록- banPush=false

		}


		return resultDto;
	}

	@Override
	public DefaultDTO getCashByCall(int user_no, String room_id, int get_cash, int partner_user_no) {
		GetCashByCallDTO getCashByCallDto = new GetCashByCallDTO();

		getCashByCallDto.setUser_no(user_no);
		getCashByCallDto.setRoom_id(room_id);
		getCashByCallDto.setGet_cash(get_cash);
		getCashByCallDto.setPartner_user_no(partner_user_no);
		liveCallMapper.uspGetCashByCall(getCashByCallDto);

		DefaultDTO returnDto = new DefaultDTO();

		returnDto.setReason(getCashByCallDto.getResult_msg());
		if (getCashByCallDto.getResult_code() == 1){
			returnDto.setResult(Utils.SUCCESS);
		}
		return returnDto;
	}

	@Override
	public DefaultDTO payPointByCall(int user_no, String room_id, int pay_point, int pay_free_point, int partner_user_no) {
		PayPointByCallDTO payPointByCallDto = new PayPointByCallDTO();

		payPointByCallDto.setUser_no(user_no);
		payPointByCallDto.setRoom_id(room_id);
		payPointByCallDto.setPay_point(pay_point);
		payPointByCallDto.setPay_free_point(pay_free_point);
		payPointByCallDto.setPartner_user_no(partner_user_no);
		liveCallMapper.uspPayPointByCall(payPointByCallDto);

		DefaultDTO returnDto = new DefaultDTO();

		returnDto.setReason(payPointByCallDto.getResult_msg());
		if (payPointByCallDto.getResult_code() == 1){
			returnDto.setResult(Utils.SUCCESS);
		}
		return returnDto;
	}

}


