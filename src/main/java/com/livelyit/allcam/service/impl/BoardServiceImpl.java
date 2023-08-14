package com.livelyit.allcam.service.impl;

import com.livelyit.allcam.common.JwtTokenUtil;
import com.livelyit.allcam.dto.BoardDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.BoardCustomerDTO;
import com.livelyit.allcam.dto.BoardCustomerResultDTO;
import com.livelyit.allcam.dto.BoardListDTO;
import com.livelyit.allcam.mapper.BoardMapper;
import com.livelyit.allcam.service.BoardService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static com.livelyit.allcam.common.Utils.API_LOG_PATH;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardMapper boardMapper;

	
	// 공지 및 이용약관 리스트 
	@Override
	public BoardListDTO selectBoardList(String board_type) {
		BoardListDTO dto = new BoardListDTO();
		dto.setBoardList(boardMapper.selectBoardList(board_type));
		dto.setResult(Utils.SUCCESS);
		return dto;
	}

	// 공지 및 이용약관 리스트
	@Override
	public BoardListDTO selectBoardListV2(String board_type, String service_type, String target_gender ) {
		BoardListDTO dto = new BoardListDTO();
		BoardDTO boardDTO = new BoardDTO();
		boardDTO.setBoard_type(board_type);
		boardDTO.setService_type(service_type);
		boardDTO.setTarget_gender(target_gender);

		dto.setBoardList(boardMapper.selectBoardListV2(boardDTO));
		dto.setResult(Utils.SUCCESS);
		return dto;
	}
	
	//고객센터 문의사항
	@Override
	public BoardCustomerResultDTO insertCustomer(BoardCustomerDTO dto) {
		BoardCustomerResultDTO resultDto = new BoardCustomerResultDTO();
		int result = boardMapper.insertCustomer(dto);
		resultDto.setBoardCustomerList(dto);
		if(result == 1) {
			//sms 발송
			//Utils.sendSMS("[올캠 관리자]\n고객센터에 문의사항이 등록되었습니다.","01031696119");
			resultDto.setResult(Utils.SUCCESS);
		}
		
		return resultDto; 
	}

//	@Override
//	public String iosTokenTest(String token){
//		String strResult = sendConsumptionIOS(token,"480000924390844","DENY");
//		return strResult;
//	}
//
//	public String sendConsumptionIOS(String jwt, String transactionId, String confirmRefund) {
//		String result = "";
//
//		URL url = null;
//		try {
//			UUID uuid = UUID.randomUUID();
//			//String jwt = JwtTokenUtil.createToken("ios_consumption_token");
//			url = new URL("https://api.storekit.itunes.apple.com/inApps/v1/transactions/consumption/"+transactionId);	//상용
//			HttpURLConnection http = (HttpURLConnection) url.openConnection();
//			http.setRequestMethod("PUT");
//			http.setDoOutput(true);
//			http.setRequestProperty("Content-Type", "application/json");
//			http.setRequestProperty("Accept", "application/json");
//			http.setRequestProperty("Authorization", "Bearer " + jwt);
//
//
//			String consumReq = "{\"accountTenure\":0, \"consumptionStatus\":0,\"customerConsented\":false, \"lifetimeDollarsPurchased\":0,"
//					+" \"lifetimeDollarsRefunded\":0, \"userStatus\":1,\"platform\":1, \"playTime\":0,"
//					+" \"deliveryStatus\":0, \"sampleContentProvided\":true,\"appAccountToken\":\""+uuid+"\"}";
//
//			if(confirmRefund.equals("ALLOW")){
//				consumReq = "{\"accountTenure\":0, \"consumptionStatus\":1,\"customerConsented\":true, \"lifetimeDollarsPurchased\":0,"
//						+" \"lifetimeDollarsRefunded\":0, \"userStatus\":1,\"platform\":1, \"playTime\":0,"
//						+" \"deliveryStatus\":0, \"sampleContentProvided\":true,\"appAccountToken\":\""+uuid+"\"}";
//			}
//
//			byte[] out = consumReq.getBytes(StandardCharsets.UTF_8);
//
//			OutputStream stream = http.getOutputStream();
//			stream.write(out);
//
//
//			int responseCode = http.getResponseCode();
//
//			if(responseCode == 202){
//				result = Utils.SUCCESS;//처리 완
//			}else{
//				//실패
//				result = Utils.FAIL;
//			}
//
//			http.disconnect();
//
//			HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//			String ip = req.getHeader("X-FORWARDED-FOR");
//			if (ip == null)
//				ip = req.getRemoteAddr();
//
//			// 사용자 요청 데이터를 로그를 파일로 저장하는 코드
//			PrintStream ps = null;
//			FileOutputStream fos=null;
//			Date today = new Date();
//
//			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
//			SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
//			String dateStr = date.format(today);
//			String timeStr = time.format(today);
//
//			try {
//
//				fos = new FileOutputStream(API_LOG_PATH+"sendConsumptionIOS_"+dateStr+".log",true); // error.log파일에 출력 준비
//				ps=new PrintStream(fos); // err의 출력을 화면이 아닌, error.log파일로 변경
//				System.setErr(ps);
//				System.err.println("API : sendConsumptionIOS -----------------------------------");
//				System.err.println("[" + dateStr+ " "+timeStr+"] "+ip);  // 사용자 ip
//				System.err.println("transactionId: " + transactionId);
//				System.err.println("confirmRefund: " + confirmRefund);
//				System.err.println("responseCode: " + responseCode);
//			} catch (FileNotFoundException fileNotFoundException) {
//				fileNotFoundException.printStackTrace();
//			} finally {
//				if (fos!=null){
//					try {
//						fos.close();
//					} catch (IOException e) {
//						e.printStackTrace();
//					}
//				}
//
//			}
//
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return result;
//	}

}