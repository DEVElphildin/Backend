package com.livelyit.allcam.service.impl;


import com.sun.corba.se.pept.transport.ReaderThread;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.AddPayListDTO;
import com.livelyit.allcam.dto.AutoPassBookDTO;
import com.livelyit.allcam.dto.AutoPassNoDTO;
import com.livelyit.allcam.dto.ChatDTO;
import com.livelyit.allcam.dto.DefaultDTO;
import com.livelyit.allcam.dto.PassBookDTO;
import com.livelyit.allcam.dto.PassBookResultDTO;
import com.livelyit.allcam.dto.PushType;
import com.livelyit.allcam.dto.UseHistoryDTO;
import com.livelyit.allcam.dto.UserInfoDTO;
import com.livelyit.allcam.dto.UserPointDTO;
import com.livelyit.allcam.mapper.ChatMapper;
import com.livelyit.allcam.mapper.LiveCallMapper;
import com.livelyit.allcam.mapper.PassBookMapper;
import com.livelyit.allcam.mapper.PayMapper;
import com.livelyit.allcam.mapper.UserMapper;
import com.livelyit.allcam.service.PassbookService;
import com.livelyit.allcam.service.PushService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.livelyit.allcam.common.Utils.API_LOG_PATH;
import static com.livelyit.allcam.common.Utils.checkEventDay;


@Service
public class PassbookServiceImpl implements PassbookService {
	@Autowired
	LiveCallMapper liveCallMapper;
	
	@Autowired
	PassBookMapper passbookMapper;

	@Autowired
	PushService pushService;

	@Autowired
	UserMapper userMapper;

	@Autowired
	ChatMapper chatMapper;

	@Autowired
	PayMapper payMapper;
	
	@Override
	public PassBookResultDTO selectPassbookList(){
		
		PassBookResultDTO dto = new PassBookResultDTO();
		dto.setPassbookList(passbookMapper.selectPassbookList());
		dto.setResult(Utils.SUCCESS);
		
		return dto;
	}
	
	@Override
	public PassBookResultDTO insertPassBook(PassBookDTO dto) {	
//		int passbook_point = Utils.pointCheck(dto.getPassbook_payment());
//		dto.setPassbook_point(passbook_point);		
		PassBookResultDTO resultDto = new PassBookResultDTO();
		resultDto.setAddPassbook(dto);
		int result = -1;

		if(dto.getCash_receipts() == 1){
			result = passbookMapper.insertPassBookCashReceipts(dto);
		}else {
			result = passbookMapper.insertPassBook(dto);
		}

		UserInfoDTO udto = userMapper.getUserInfo(dto.getUser_no());
		String userServiceType = udto.getUser_service_type();
		String textHello = "안녕하세요! Let’s All night!\n올캠입니다.\n";
		String textAccount = "기업은행\n003-608-2888 (주)엘필딘\n";
		if("onelive".equals(userServiceType)) {
			textHello = "안녕하세요! 원라이브입니다.\n";
		}else if("wowcam".equals(userServiceType)) {
			textHello = "안녕하세요! 와우캠입니다.\n";
		}else if("toptalk".equals(userServiceType)) {
			textHello = "안녕하세요! 탑톡입니다.\n";
		}
		ChatDTO cdto = new ChatDTO();
		cdto.setChat_text(
				textHello + udto.getUser_nick_name() + " 회원님, 무통장 결제를 신청해 주셔서 대단히 감사합니다.\n" +
				"\n" + 
				"무통장 입금 계좌는\n" +
				textAccount + "입니다.\n" +
				"\n" + 
				"신청하신 후 1시간 이내에 입금해 주셔야 하오니 이점 참고하시기 바랍니다.\n" + 
				"\n" + 
				"오늘도 행복한 하루 보내시기 바랍니다."
				);
		cdto.setFrom_user_no(1);	// 1 : 관리자
		cdto.setTo_user_no(dto.getUser_no());									
								
		int chatResult = chatMapper.sendChat(cdto);
		
		if(result == 1 && chatResult == 1){
			resultDto.setResult(Utils.SUCCESS);

			JSONObject chatData = new JSONObject();
			chatData.put("from_user_no", 1);
			chatData.put("chat_text", "무통장 입금 신청 안내");
			chatData.put("from_user_nick", "관리자");
			chatData.put("from_user_thumnail", "NONE");
			pushService.sendDataPush(udto.getUser_push_key(), PushType.CHATGET.getTypeCd(), chatData.toString(), "관리자", "무통장 입금 신청 안내"); //푸쉬 발송
		}
		
		return resultDto;
	}

	@Override
	public PassBookResultDTO insertPassBookV2(PassBookDTO dto) {
//		int passbook_point = Utils.pointCheck(dto.getPassbook_payment());
//		dto.setPassbook_point(passbook_point);
		PassBookResultDTO resultDto = new PassBookResultDTO();
		resultDto.setAddPassbook(dto);
		int result = -1;

		if(dto.getCash_receipts() == 1){
			result = passbookMapper.insertPassBookCashReceipts(dto);
		}else {
			result = passbookMapper.insertPassBook(dto);
		}

		UserInfoDTO udto = userMapper.getUserInfo(dto.getUser_no());
		String userServiceType = udto.getUser_service_type();
		String textHello = "안녕하세요! Let’s All night!\n올캠입니다.\n";
		String textAccount = "기업은행\n003-608-2888 (주)엘필딘\n";
		if("onelive".equals(userServiceType)) {
			textHello = "안녕하세요! 원라이브입니다.\n";
		}else if("wowcam".equals(userServiceType)) {
			textHello = "안녕하세요! 와우캠입니다.\n";
		}else if("toptalk".equals(userServiceType)) {
			textHello = "안녕하세요! 탑톡입니다.\n";
		}
		ChatDTO cdto = new ChatDTO();
		cdto.setChat_text(
				textHello + udto.getUser_nick_name() + " 회원님, 무통장 결제를 신청해 주셔서 대단히 감사합니다.\n" +
						"\n" +
						"무통장 입금 계좌는\n" +
						textAccount + "입니다.\n" +
						"\n" +
						"신청하신 후 1시간 이내에 입금해 주셔야 하오니 이점 참고하시기 바랍니다.\n" +
						"\n" +
						"오늘도 행복한 하루 보내시기 바랍니다."
		);
		cdto.setFrom_user_no(1);	// 1 : 관리자
		cdto.setTo_user_no(dto.getUser_no());

		int chatResult = chatMapper.sendChat(cdto);

		if(result == 1 && chatResult == 1){
			resultDto.setResult(Utils.SUCCESS);

			JSONObject chatData = new JSONObject();
			chatData.put("from_user_no", 1);
			chatData.put("chat_text", "무통장 입금 신청 안내");
			chatData.put("from_user_nick", "관리자");
			chatData.put("from_user_thumnail", "NONE");
			pushService.sendDataPush(udto.getUser_push_key(), PushType.CHATGET.getTypeCd(), chatData.toString(), "관리자", "무통장 입금 신청 안내"); //푸쉬 발송
		}

		return resultDto;
	}

	@Override
	public PassBookResultDTO insertPassBookV3(PassBookDTO dto) {
		PassBookResultDTO resultDto = new PassBookResultDTO();
		resultDto.setAddPassbook(dto);
		int result = -1;

		if(dto.getCash_receipts() == 1){
			// 현금 영수증 정보를 저장하는 경우
			result = passbookMapper.insertPassBookCashReceiptsV3(dto);
		}else {
			// 현금 영수증 정보를 저장 안하는 경우
			result = passbookMapper.insertPassBookV3(dto);
		}

		UserInfoDTO udto = userMapper.getUserInfo(dto.getUser_no());
		String userServiceType = udto.getUser_service_type();
		String textHello = "안녕하세요! Let’s All night!\n올캠입니다.\n";
		String textAccount = "기업은행\n003-608-2888 (주)엘필딘\n";
		if("onelive".equals(userServiceType)) {
			textHello = "안녕하세요! 원라이브입니다.\n";
		}else if("wowcam".equals(userServiceType)) {
			textHello = "안녕하세요! 와우캠입니다.\n";
		}else if("toptalk".equals(userServiceType)) {
			textHello = "안녕하세요! 탑톡입니다.\n";
		}
		ChatDTO cdto = new ChatDTO();
		cdto.setChat_text(
				textHello + udto.getUser_nick_name() + " 회원님, 무통장 결제를 신청해 주셔서 대단히 감사합니다.\n" +
						"\n" +
						"무통장 입금 계좌는\n" +
						textAccount + "입니다.\n" +
						"\n" +
						"신청하신 후 1시간 이내에 입금해 주셔야 하오니 이점 참고하시기 바랍니다.\n" +
						"\n" +
						"오늘도 행복한 하루 보내시기 바랍니다."
		);
		cdto.setFrom_user_no(1);	// 1 : 관리자
		cdto.setTo_user_no(dto.getUser_no());

		int chatResult = chatMapper.sendChat(cdto);

		if(result == 1 && chatResult == 1){
			resultDto.setResult(Utils.SUCCESS);

			JSONObject chatData = new JSONObject();
			chatData.put("from_user_no", 1);
			chatData.put("chat_text", "무통장 입금 신청 안내");
			chatData.put("from_user_nick", "관리자");
			chatData.put("from_user_thumnail", "NONE");
			pushService.sendDataPush(udto.getUser_push_key(), PushType.CHATGET.getTypeCd(), chatData.toString(), "관리자", "무통장 입금 신청 안내"); //푸쉬 발송
		}

		return resultDto;
	}


	@Override
	public DefaultDTO autoPassbookConfirm(String user_name, int user_pay) {
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

			fos = new FileOutputStream(API_LOG_PATH+"pay_"+dateStr+".log",true); // error.log파일에 출력 준비
			ps=new PrintStream(fos); // err의 출력을 화면이 아닌, error.log파일로 변경
			System.setErr(ps);
			System.err.println("API : autoPassbookConfirm -----------------------------------");
			System.err.println("[" + dateStr+ " "+timeStr+"] "+ip);  // 사용자 ip
			System.err.println("user_name: " + user_name);
			System.err.println("user_pay: " + user_pay);

		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		} finally {
			if (fos!=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

		DefaultDTO resultDto = new DefaultDTO();

		AutoPassBookDTO autoDto = new AutoPassBookDTO();
		autoDto.setUser_name(user_name);
		autoDto.setUser_pay(user_pay);

		// 무통장 입금 이름을 통해 사용자 번호를 가져온다.
		int user_no = 0;
		try {
			user_no = passbookMapper.getUserNoPassBook(autoDto);
		}catch (Exception e){
			return resultDto;
		}

		// 첫결제 이벤트 기간인 경우
		if (checkEventDay(Utils.EVENT_DATE_START, Utils.EVENT_DATE_END)) {

			// (모바일, 신용카드, iOS 결제) + 무통장 전체 포함한 결제 횟수 가져오기
			int payCount = payMapper.getPayListCount(user_no);

			// 첫 결제가 아닌 경우
			if (payCount > 0){

				// 유저 포인트 history, 실제 포인트 수정함
				// paylist에 데이터 추가함
				passbookMapper.autoPassbookConfirmV2(autoDto);

				// 첫 결제인 경우
			}else{

				// 유저 포인트 history, 실제 포인트 수정, 이벤트 포인트도 추가함
				// paylist에 데이터 추가함
				passbookMapper.autoEventPassbookConfirmV2(autoDto);
			}

			// 이벤트가 아닌 경우
		}else{

			// 유저 포인트 history, 실제 포인트 수정함
			// paylist에 데이터 추가함
			passbookMapper.autoPassbookConfirmV2(autoDto);
		}

		// 유저 포인트 history, 실제 포인트 수정함
		// paylist에 데이터 추가함
		// 이벤트시 passbookMapper.autoPassbookConfirmV2(autoDto);

		DefaultDTO returnDto = new DefaultDTO();

		returnDto.setReason(autoDto.getResult_msg());
		if (autoDto.getResult_code() == 1){

			returnDto.setResult(Utils.SUCCESS);
			// ad_id, 유저 정보 가져옴
			AutoPassNoDTO passNoDto = passbookMapper.autoPassbookChk(autoDto);
			if (passNoDto.getUser_adid()== null){
				return returnDto;
			}

			String user_adid = passNoDto.getUser_adid();
			int user_id = passNoDto.getUser_no();
			int no = passNoDto.getNo();

			UserInfoDTO userInfoDTO = userMapper.getUserInfo(user_id);


			if (userInfoDTO != null){
				// 채팅 메세지 보내기 + 남기기
				ChatDTO cdto = new ChatDTO();
				String payMessage= "";
				cdto.setChat_text(
						payMessage + userInfoDTO.getUser_nick_name() + " 회원님, "+Utils.setCntComma(passNoDto.getPay_point())+" 포인트 구매해 주셔서 감사합니다.\n" +
								"\n" +
								"현재 다퍼줘 이벤트 기간으로 "+Utils.setCntComma(passNoDto.getFree_point()) +" 포인트가 추가 지급되었습니다.\n" +
								"\n" +
								"오늘도 행복한 하루 보내시기 바랍니다."
				);

				cdto.setFrom_user_no(1);	// 1 : 관리자
				cdto.setTo_user_no(user_id);

				int chatResult = chatMapper.sendChat(cdto);

				if(chatResult == 1){
					resultDto.setResult(Utils.SUCCESS);

					JSONObject chatData = new JSONObject();
					chatData.put("from_user_no", 1);
					chatData.put("chat_text", "무통장 입금 완료 안내");
					chatData.put("from_user_nick", "관리자");
					chatData.put("from_user_thumnail", "NONE");
					pushService.sendDataPush(userInfoDTO.getUser_push_key(), PushType.CHATGET.getTypeCd(), chatData.toString(), "관리자", "무통장 입금 완료 안내"); //푸쉬 발송
				}
			}



			// null 이거나 기본값이 아닌 경우
			if (user_adid != null && user_adid.equals("")){
				// 파트너 서버에 데이터 넣어줌
				System.out.println("https://partner.allcam.co.kr/APP_acc/app_data.php?TYPE=P&UID=" +user_id+ "&MONEY=" + user_pay+ "&ADID=" + user_adid+"&PAYID="+no);
				PayServiceImpl.directConnection("https://partner.allcam.co.kr/APP_acc/app_data.php?TYPE=P&UID="+user_id
						+"&MONEY="+user_pay
						+"&ADID=" +user_adid
						+"&PAYID="+no
						+"&PAYTYPE=1");

			}
		}

		return returnDto;
	}


	@Override
	public PassBookResultDTO insertPassBookV4(PassBookDTO dto) {
		PassBookResultDTO resultDto = new PassBookResultDTO();
		resultDto.setAddPassbook(dto);
		int result = -1;

		if(dto.getCash_receipts() == 1){
			// 현금 영수증 정보를 저장하는 경우
			result = passbookMapper.insertPassBookCashReceiptsV3(dto);
		}else {
			// 현금 영수증 정보를 저장 안하는 경우
			result = passbookMapper.insertPassBookV3(dto);
		}

		UserInfoDTO udto = userMapper.getUserInfo(dto.getUser_no());
		String userServiceType = udto.getUser_service_type();
		String textHello = "안녕하세요! Let’s All night!\n올캠입니다.\n";
		String textAccount = "기업은행\n003-608-2888 (주)엘필딘\n";
		if("onelive".equals(userServiceType)) {
			textHello = "안녕하세요! 원라이브입니다.\n";
		}else if("wowcam".equals(userServiceType)) {
			textHello = "안녕하세요! 와우캠입니다.\n";
		}else if("toptalk".equals(userServiceType)) {
			textHello = "안녕하세요! 탑톡입니다.\n";
		}else if("meincam".equals(userServiceType)) {
			textHello = "안녕하세요! 미인캠입니다.\n";
		}
		ChatDTO cdto = new ChatDTO();
		cdto.setChat_text(
				textHello + udto.getUser_nick_name() + " 회원님, 무통장 결제를 신청해 주셔서 대단히 감사합니다.\n" +
						"\n" +
						"무통장 입금 계좌는\n" +
						textAccount + "입니다.\n" +
						"\n" +
						"신청하신 후 1시간 이내에 입금해 주셔야 하오니 이점 참고하시기 바랍니다.\n" +
						"\n" +
						"오늘도 행복한 하루 보내시기 바랍니다."
		);
		cdto.setFrom_user_no(1);	// 1 : 관리자
		cdto.setTo_user_no(dto.getUser_no());

		int chatResult = chatMapper.sendChat(cdto);

		if(result == 1 && chatResult == 1){
			resultDto.setResult(Utils.SUCCESS);

			JSONObject chatData = new JSONObject();
			chatData.put("from_user_no", 1);
			chatData.put("chat_text", "무통장 입금 신청 안내");
			chatData.put("from_user_nick", "관리자");
			chatData.put("from_user_thumnail", "NONE");
			pushService.sendDataPush(udto.getUser_push_key(), PushType.CHATGET.getTypeCd(), chatData.toString(), "관리자", "무통장 입금 신청 안내"); //푸쉬 발송
		}

		return resultDto;
	}

	@Override
	public PassBookResultDTO insertPassBookMC(PassBookDTO dto) {
		PassBookResultDTO resultDto = new PassBookResultDTO();
		resultDto.setAddPassbook(dto);
		int result = -1;

		if(dto.getCash_receipts() == 1){
			// 현금 영수증 정보를 저장하는 경우
			result = passbookMapper.insertPassBookCashReceiptsV3(dto);
		}else {
			// 현금 영수증 정보를 저장 안하는 경우
			result = passbookMapper.insertPassBookV3(dto);
		}

		UserInfoDTO udto = userMapper.getUserInfo(dto.getUser_no());
		String userServiceType = udto.getUser_service_type();
		String textHello = "안녕하세요! Let’s All night!\n올캠입니다.\n";
		String textAccount = "기업은행\n003-608-2888 (주)엘필딘\n";
		if("onelive".equals(userServiceType)) {
			textHello = "안녕하세요! 원라이브입니다.\n";
		}else if("wowcam".equals(userServiceType)) {
			textHello = "안녕하세요! 와우캠입니다.\n";
		}else if("toptalk".equals(userServiceType)) {
			textHello = "안녕하세요! 탑톡입니다.\n";
		}else if("meincam".equals(userServiceType)) {
			textHello = "안녕하세요! 미인캠입니다.\n";
		}
		ChatDTO cdto = new ChatDTO();
		cdto.setChat_text(
				textHello + udto.getUser_nick_name() + " 회원님, 무통장 결제를 신청해 주셔서 대단히 감사합니다.\n" +
						"\n" +
						"무통장 입금 계좌는\n" +
						textAccount + "입니다.\n" +
						"\n" +
						"신청하신 후 1시간 이내에 입금해 주셔야 하오니 이점 참고하시기 바랍니다.\n" +
						"\n" +
						"오늘도 행복한 하루 보내시기 바랍니다."
		);
		cdto.setFrom_user_no(1);	// 1 : 관리자
		cdto.setTo_user_no(dto.getUser_no());

		int chatResult = chatMapper.sendChat(cdto);

		if(result == 1 && chatResult == 1){
			resultDto.setResult(Utils.SUCCESS);

			JSONObject chatData = new JSONObject();
			chatData.put("from_user_no", 1);
			chatData.put("chat_text", "무통장 입금 신청 안내");
			chatData.put("from_user_nick", "관리자");
			chatData.put("from_user_thumnail", "NONE");
			pushService.sendDataPush(udto.getUser_push_key(), PushType.CHATGET.getTypeCd(), chatData.toString(), "관리자", "무통장 입금 신청 안내"); //푸쉬 발송
		}

		return resultDto;
	}

	@Override
	public PassBookResultDTO insertPassBookV5(PassBookDTO dto) {
		PassBookResultDTO resultDto = new PassBookResultDTO();
		resultDto.setAddPassbook(dto);
		int result = -1;

		if(dto.getCash_receipts() == 1){
			// 현금 영수증 정보를 저장하는 경우
			result = passbookMapper.insertPassBookCashReceiptsV3(dto);
		}else {
			// 현금 영수증 정보를 저장 안하는 경우
			result = passbookMapper.insertPassBookV3(dto);
		}

		UserInfoDTO udto = userMapper.getUserInfo(dto.getUser_no());
		String userServiceType = udto.getUser_service_type();
		String textHello = "안녕하세요! Let’s All night!\n올캠입니다.\n";
		String textAccount = "국민은행\n631001-04-252286 황성철(아이티플러스)\n";
		if("onelive".equals(userServiceType)) {
			textHello = "안녕하세요! 원라이브입니다.\n";
		}else if("wowcam".equals(userServiceType)) {
			textHello = "안녕하세요! 와우캠입니다.\n";
		}else if("toptalk".equals(userServiceType)) {
			textHello = "안녕하세요! 탑톡입니다.\n";
		}else if("meincam".equals(userServiceType)) {
			textHello = "안녕하세요! 미인캠입니다.\n";
		}
		ChatDTO cdto = new ChatDTO();
		cdto.setChat_text(
				textHello + udto.getUser_nick_name() + " 회원님, 무통장 결제를 신청해 주셔서 대단히 감사합니다.\n" +
						"\n" +
						"무통장 입금 계좌는\n" +
						textAccount + "입니다.\n" +
						"\n" +
						"신청하신 후 1시간 이내에 입금해 주셔야 하오니 이점 참고하시기 바랍니다.\n" +
						"\n" +
						"오늘도 행복한 하루 보내시기 바랍니다."
		);
		cdto.setFrom_user_no(1);	// 1 : 관리자
		cdto.setTo_user_no(dto.getUser_no());

		int chatResult = chatMapper.sendChat(cdto);

		if(result == 1 && chatResult == 1){
			resultDto.setResult(Utils.SUCCESS);

			JSONObject chatData = new JSONObject();
			chatData.put("from_user_no", 1);
			chatData.put("chat_text", "무통장 입금 신청 안내");
			chatData.put("from_user_nick", "관리자");
			chatData.put("from_user_thumnail", "NONE");
			pushService.sendDataPush(udto.getUser_push_key(), PushType.CHATGET.getTypeCd(), chatData.toString(), "관리자", "무통장 입금 신청 안내"); //푸쉬 발송
		}

		return resultDto;
	}


	private Boolean paymentCheck (int use_pay, int product) {
		//use_pay : 입금된 금액
		//product : 결제상품
		boolean result = false;
		
		switch(product){
		case 3000 :
			if(use_pay == 5000) {result = true;}
			break;
		case 6000 :
			if(use_pay == 10000) {result = true;}
			break;
		case 18000 :
			if(use_pay == 30000) {result = true;}
			break;
		case 30000 :
			if(use_pay == 50000) {result = true;}
			break;
		case 60000 :
			if(use_pay == 100000) {result = true;}
			break;
		case 120000 :
			if(use_pay == 200000) {result = true;}
			break;
		default : 
			break;
		}
		
		return result;
	}
	
	private int pointCheck (int payment) {		
		int result_point = 0;
		
		if (payment == 5000) {
			result_point = 3000;
		}else if( payment == 6000) {
			result_point = 6060;
		}else if( payment == 18000) {
			result_point = 18630;
		}else if( payment == 30000) {
			result_point = 32100;
		}else if( payment == 60000) {
			result_point = 67800;
		}else if( payment == 120000) {
			result_point = 140400;
		}
		return result_point;
	}
}