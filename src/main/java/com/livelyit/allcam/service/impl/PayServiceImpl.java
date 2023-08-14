package com.livelyit.allcam.service.impl;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import com.livelyit.allcam.common.JwtTokenUtil;
import com.livelyit.allcam.dto.*;
import org.apache.http.util.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.mapper.ChatMapper;
import com.livelyit.allcam.mapper.LiveCallMapper;
import com.livelyit.allcam.mapper.PayMapper;
import com.livelyit.allcam.mapper.UserMapper;
import com.livelyit.allcam.service.PayService;

import net.minidev.json.JSONArray;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

import static com.livelyit.allcam.common.Utils.API_LOG_PATH;
import static com.livelyit.allcam.common.Utils.checkEventDay;

@Service
public class PayServiceImpl implements PayService {
	@Autowired
	PayMapper payMapper;

	@Autowired
	LiveCallMapper liveCallMapper;
	
	@Autowired
	UserMapper userMapper;

	@Autowired
	ChatMapper chatMapper;

//	@Override
//	public DefaultDTO startPay(int user_no, int pay_result_pay, String pay_result_type, int pay_result_point, String pay_result_product_name) {
//		DefaultDTO resultDto = new DefaultDTO();
//
//		PayDTO dto = new PayDTO();
//
//		dto.setUser_no(user_no);
//		dto.setPay_result_pay(pay_result_pay);
//		dto.setPay_status(0);
//		dto.setPay_result_point(pay_result_point);
//		dto.setPay_result_product_name(pay_result_product_name);
//		dto.setPay_result_type(pay_result_type);
//
//		if(pay_result_product_name.equals("3,000 P")){
//			dto.setPay_point(3000);
//			dto.setFree_point(0);
//		}else if(pay_result_product_name.equals("6,000 P")){
//			dto.setPay_point(6000);
//			dto.setFree_point(60);
//		}else if(pay_result_product_name.equals("18,000 P")){
//			dto.setPay_point(18000);
//			dto.setFree_point(540);
//		}else if(pay_result_product_name.equals("30,000 P")){
//			dto.setPay_point(30000);
//			dto.setFree_point(1500);
//		}else if(pay_result_product_name.equals("60,000 P")){
//			dto.setPay_point(60000);
//			dto.setFree_point(6000);
//		}else if(pay_result_product_name.equals("120,000 P")){
//			dto.setPay_point(120000);
//			dto.setFree_point(14400);
//		}
//
//		int result = payMapper.insertPay(dto);
//
//		if(result == 1) {
//			resultDto.setResult(Utils.SUCCESS);
//			resultDto.setNo(dto.getNo());
//		}
//
//		return resultDto;
//	}
//
//	@Override
//	public DefaultDTO startPayV2(int user_no, int pay_result_pay, String pay_result_type, int pay_result_point, String pay_result_product_name) {
//		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//		String ip = req.getHeader("X-FORWARDED-FOR");
//		if (ip == null)
//			ip = req.getRemoteAddr();
//
//		// 사용자 요청 데이터를 로그를 파일로 저장하는 코드
//		PrintStream ps = null;
//		FileOutputStream fos=null;
//		Date today = new Date();
//
//		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
//		String dateStr = date.format(today);
//		String timeStr = time.format(today);
//
//		try {
//
//			fos = new FileOutputStream(API_LOG_PATH+"pay_"+dateStr+".log",true); // error.log파일에 출력 준비
//			ps=new PrintStream(fos); // err의 출력을 화면이 아닌, error.log파일로 변경
//			System.setErr(ps);
//			System.err.println("API : startPayV2 -----------------------------------");
//			System.err.println("[" + dateStr+ " "+timeStr+"] "+ip);  // 사용자 ip
//			System.err.println("user_no: " + user_no);
//			System.err.println("pay_result_pay: " + pay_result_pay);
//			System.err.println("pay_result_type: " + pay_result_type);
//			System.err.println("pay_result_point: " + pay_result_point);
//			System.err.println("pay_result_product_name: " + pay_result_product_name);
//		} catch (FileNotFoundException fileNotFoundException) {
//			fileNotFoundException.printStackTrace();
//		} finally {
//			if (fos!=null){
//				try {
//					fos.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//
//		}
//
//		DefaultDTO resultDto = new DefaultDTO();
//
//		PayDTO dto = new PayDTO();
//
//		dto.setUser_no(user_no);
//		dto.setPay_result_pay(pay_result_pay);
//		dto.setPay_status(0);
//		dto.setPay_result_point(pay_result_point);
//		dto.setPay_result_product_name(pay_result_product_name);
//		dto.setPay_result_type(pay_result_type);
//
//		if(pay_result_product_name.equals("3,000 P")){
//			dto.setPay_point(3000);
//			dto.setFree_point(0);
//		}else if(pay_result_product_name.equals("6,000 P")){
//			dto.setPay_point(6000);
//			dto.setFree_point(0);
//		}else if(pay_result_product_name.equals("18,000 P")){
//			dto.setPay_point(18000);
//			dto.setFree_point(180);
//		}else if(pay_result_product_name.equals("30,000 P")){
//			dto.setPay_point(30000);
//			dto.setFree_point(600);
//		}else if(pay_result_product_name.equals("60,000 P")){
//			dto.setPay_point(60000);
//			dto.setFree_point(1800);
//		}else if(pay_result_product_name.equals("120,000 P")){
//			dto.setPay_point(120000);
//			dto.setFree_point(6000);
//		}
//
//		int result = payMapper.insertPay(dto);
//
//		if(result == 1) {
//			resultDto.setResult(Utils.SUCCESS);
//			resultDto.setNo(dto.getNo());
//		}
//
//		return resultDto;
//	}


	@Override
	public DefaultDTO startPayV3(int user_no, int pay_result_pay, String pay_result_type, int pay_result_point, String pay_result_product_name) {
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
			System.err.println("API : startPayV3 -----------------------------------");
			System.err.println("[" + dateStr+ " "+timeStr+"] "+ip);  // 사용자 ip
			System.err.println("user_no: " + user_no);
			System.err.println("pay_result_pay: " + pay_result_pay);
			System.err.println("pay_result_type: " + pay_result_type);
			System.err.println("pay_result_point: " + pay_result_point);
			System.err.println("pay_result_product_name: " + pay_result_product_name);
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

		int payResultPay = 0;
		int payPoint = 0;
		int freePoint = 0;

		if (pay_result_product_name.equals(Utils.PD_0001_NAME)){
			payResultPay = Utils.PD_0001_PRICE;
			payPoint = Utils.PD_0001_POINT;
			freePoint = Utils.PD_0001_FREE_POINT;
		}else if (pay_result_product_name.equals(Utils.PD_0002_NAME)){
			payResultPay = Utils.PD_0002_PRICE;
			payPoint = Utils.PD_0002_POINT;
			freePoint = Utils.PD_0002_FREE_POINT;
		}else if (pay_result_product_name.equals(Utils.PD_0003_NAME)){
			payResultPay = Utils.PD_0003_PRICE;
			payPoint = Utils.PD_0003_POINT;
			freePoint = Utils.PD_0003_FREE_POINT;
		}else if (pay_result_product_name.equals(Utils.PD_0004_NAME)){
			payResultPay = Utils.PD_0004_PRICE;
			payPoint = Utils.PD_0004_POINT;
			freePoint = Utils.PD_0004_FREE_POINT;
		}else if (pay_result_product_name.equals(Utils.PD_0005_NAME)){
			payResultPay = Utils.PD_0005_PRICE;
			payPoint = Utils.PD_0005_POINT;
			freePoint = Utils.PD_0005_FREE_POINT;
		}else if (pay_result_product_name.equals(Utils.PD_0006_NAME)){
			payResultPay = Utils.PD_0006_PRICE;
			payPoint = Utils.PD_0006_POINT;
			freePoint = Utils.PD_0006_FREE_POINT;
		}

		// s:다퍼줘 이벤트 종료
		if (!checkEventDay("2020-12-21", "2021-01-31")) {
			freePoint = 0;
		}
		// e:다퍼줘 이벤트 종료

		DefaultDTO resultDto = new DefaultDTO();

		PayDTO dto = new PayDTO();
		dto.setUser_no(user_no);
		dto.setPay_result_pay(payResultPay);
		dto.setPay_status(0);
		dto.setPay_result_point(pay_result_point);
		dto.setPay_result_product_name(pay_result_product_name);
		dto.setPay_result_type(pay_result_type);
		dto.setPay_point(payPoint);
		dto.setFree_point(freePoint);

		int result = payMapper.insertPay(dto);

		if(result == 1) {
			resultDto.setResult(Utils.SUCCESS);
			resultDto.setNo(dto.getNo());
		}

		return resultDto;
	}

	@Override
	public DefaultDTO startPayMC(int user_no, String pay_result_type, String product_id) {
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
			System.err.println("API : startPayMC -----------------------------------");
			System.err.println("[" + dateStr+ " "+timeStr+"] "+ip);  // 사용자 ip
			System.err.println("user_no: " + user_no);
			System.err.println("pay_result_type: " + pay_result_type);
			System.err.println("product_id: " + product_id);
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

		int payResultPay = 0;
		int payPoint = 0;
		int freePoint = 0;
		String productName="";

		if (product_id.equals(Utils.MC_01)){
			payResultPay = Utils.MC_01_PRICE;
			productName = Utils.MC_01_NAME;
			payPoint = Utils.MC_01_POINT;
			freePoint = 0;
		}else if (product_id.equals(Utils.MC_02)){
			payResultPay = Utils.MC_02_PRICE;
			productName = Utils.MC_02_NAME;
			payPoint = Utils.MC_02_POINT;
			freePoint = 0;
		}else if (product_id.equals(Utils.MC_03)){
			payResultPay = Utils.MC_03_PRICE;
			productName = Utils.MC_03_NAME;
			payPoint = Utils.MC_03_POINT;
			freePoint = 0;
		}else if (product_id.equals(Utils.MC_04)){
			payResultPay = Utils.MC_04_PRICE;
			productName = Utils.MC_04_NAME;
			payPoint = Utils.MC_04_POINT;
			freePoint = 0;
		}else if (product_id.equals(Utils.MC_05)){
			payResultPay = Utils.MC_05_PRICE;
			productName = Utils.MC_05_NAME;
			payPoint = Utils.MC_05_POINT;
			freePoint = 0;
		}else if (product_id.equals(Utils.MC_06)){
			payResultPay = Utils.MC_06_PRICE;
			productName = Utils.MC_06_NAME;
			payPoint = Utils.MC_06_POINT;
			freePoint = 0;
		}

//		// s:다퍼줘 이벤트 종료
//		if (!checkEventDay("2020-12-21", "2021-01-31")) {
//			freePoint = 0;
//		}
//		// e:다퍼줘 이벤트 종료

		DefaultDTO resultDto = new DefaultDTO();

		PayDTO dto = new PayDTO();
		dto.setUser_no(user_no);
		dto.setPay_result_pay(payResultPay);
		dto.setPay_status(0);
		dto.setPay_result_point(payPoint);
		dto.setPay_result_product_name(productName);
		dto.setPay_result_type(pay_result_type);
		dto.setPay_point(payPoint);
		dto.setFree_point(freePoint);

		int result = payMapper.insertPay(dto);

		if(result == 1) {
			resultDto.setResult(Utils.SUCCESS);
			resultDto.setNo(dto.getNo());
		}

		return resultDto;
	}

	@Override
	public DefaultDTO startPayV4(int user_no, String pay_result_type, String product_id) {
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
			System.err.println("API : startPayV4 -----------------------------------");
			System.err.println("[" + dateStr+ " "+timeStr+"] "+ip);  // 사용자 ip
			System.err.println("user_no: " + user_no);
			System.err.println("pay_result_type: " + pay_result_type);
			System.err.println("product_id: " + product_id);
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

		int payResultPay = 0;
		int payPoint = 0;
		int freePoint = 0;
		String productName="";


		for(int i=0;i<Utils.NMC_ID.length;i++){
			if(product_id.equals(Utils.NMC_ID[i])){
				productName = Utils.NMC_NAME[i];
				payPoint = Utils.NMC_POINT[i];
				freePoint = Utils.NMC_FREE_POINT[i];
				payResultPay = Utils.NMC_PRICE[i];
			}
		}

		DefaultDTO resultDto = new DefaultDTO();

		PayDTO dto = new PayDTO();
		dto.setUser_no(user_no);
		dto.setPay_result_pay(payResultPay);
		dto.setPay_status(0);
		dto.setPay_result_point(payPoint+freePoint);
		dto.setPay_result_product_name(productName);
		dto.setPay_result_type(pay_result_type);
		dto.setPay_point(payPoint);
		dto.setFree_point(freePoint);

		int result = payMapper.insertPay(dto);

		if(result == 1) {
			resultDto.setResult(Utils.SUCCESS);
			resultDto.setNo(dto.getNo());
		}

		return resultDto;
	}


	@Override
	public DefaultDTO startPayIOSV3(int user_no, String pay_result_product_name) {
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
			System.err.println("API : startPayIOSV3 -----------------------------------");
			System.err.println("[" + dateStr+ " "+timeStr+"] "+ip);  // 사용자 ip
			System.err.println("user_no: " + user_no);
			System.err.println("pay_result_product_name: " + pay_result_product_name);
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

		PayDTO dto = new PayDTO();

		dto.setUser_no(user_no);

		dto.setPay_status(0);

		dto.setPay_result_product_name(pay_result_product_name);

		int pay_result_pay = 0;
		int pay_point = 0;
		int free_point = 0;
		String pay_result_type = "ios";
		if (pay_result_product_name.equals(Utils.PD_0001_NAME)){
			pay_result_pay = Utils.PD_0001_PRICE_IOS;
			pay_point = Utils.PD_0001_POINT;
			free_point = Utils.PD_0001_FREE_POINT;
		}else if (pay_result_product_name.equals(Utils.PD_0002_NAME)){
			pay_result_pay = Utils.PD_0002_PRICE_IOS;
			pay_point = Utils.PD_0002_POINT;
			free_point = Utils.PD_0002_FREE_POINT;
		}else if (pay_result_product_name.equals(Utils.PD_0003_NAME)){
			pay_result_pay = Utils.PD_0003_PRICE_IOS;
			pay_point = Utils.PD_0003_POINT;
			free_point = Utils.PD_0003_FREE_POINT;
		}else if (pay_result_product_name.equals(Utils.PD_0004_NAME)){
			pay_result_pay = Utils.PD_0004_PRICE_IOS;
			pay_point = Utils.PD_0004_POINT;
			free_point = Utils.PD_0004_FREE_POINT;
		}else if (pay_result_product_name.equals(Utils.PD_0005_NAME)){
			pay_result_pay = Utils.PD_0005_PRICE_IOS;
			pay_point = Utils.PD_0005_POINT;
			free_point = Utils.PD_0005_FREE_POINT;
		}else if (pay_result_product_name.equals(Utils.PD_0006_NAME)){
			pay_result_pay = Utils.PD_0006_PRICE_IOS;
			pay_point = Utils.PD_0006_POINT;
			free_point = Utils.PD_0006_FREE_POINT;
		}else{
			return resultDto;
		}

		// s:다퍼줘 이벤트 종료
		if (!checkEventDay("2020-12-21", "2021-01-31")) {
			free_point = 0;
		}
		// s:다퍼줘 이벤트 종료

		dto.setPay_point(pay_point);
		dto.setFree_point(free_point);
		dto.setPay_result_point(pay_point+free_point);
		dto.setPay_result_pay(pay_result_pay);
		dto.setPay_result_type(pay_result_type);

		int result = payMapper.insertPay(dto);

		if(result == 1) {
			resultDto.setResult(Utils.SUCCESS);
			resultDto.setNo(dto.getNo());
		}

		return resultDto;
	}

	@Override
	public DefaultDTO startPayMCIOS(int user_no, String product_id) {
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
			System.err.println("API : startPayMCIOS -----------------------------------");
			System.err.println("[" + dateStr+ " "+timeStr+"] "+ip);  // 사용자 ip
			System.err.println("user_no: " + user_no);
			System.err.println("product_id: " + product_id);
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
		PayDTO dto = new PayDTO();
		dto.setUser_no(user_no);
		dto.setPay_status(0);
		String pay_result_product_name ="";
		int pay_result_pay = 0;
		int pay_point = 0;
		int free_point = 0;
		String pay_result_type = "ios";
		if (product_id.equals(Utils.MC_01)){
			pay_result_product_name = Utils.MC_01_NAME;
			pay_result_pay = Utils.MC_01_PRICE_IOS;
			pay_point = Utils.MC_01_POINT;
			free_point = 0;
		}else if (product_id.equals(Utils.MC_02)){
			pay_result_product_name = Utils.MC_02_NAME;
			pay_result_pay = Utils.MC_02_PRICE_IOS;
			pay_point = Utils.MC_02_POINT;
			free_point = 0;
		}else if (product_id.equals(Utils.MC_03)){
			pay_result_product_name = Utils.MC_03_NAME;
			pay_result_pay = Utils.MC_03_PRICE_IOS;
			pay_point = Utils.MC_03_POINT;
			free_point = 0;
		}else if (product_id.equals(Utils.MC_04)){
			pay_result_product_name = Utils.MC_04_NAME;
			pay_result_pay = Utils.MC_04_PRICE_IOS;
			pay_point = Utils.MC_04_POINT;
			free_point = 0;
		}else if (product_id.equals(Utils.MC_05)){
			pay_result_product_name = Utils.MC_05_NAME;
			pay_result_pay = Utils.MC_05_PRICE_IOS;
			pay_point = Utils.MC_05_POINT;
			free_point = 0;
		}else if (product_id.equals(Utils.MC_06)){
			pay_result_product_name = Utils.MC_06_NAME;
			pay_result_pay = Utils.MC_06_PRICE_IOS;
			pay_point = Utils.MC_06_POINT;
			free_point = 0;
		}else{
			return resultDto;
		}

//		// s:다퍼줘 이벤트 종료
//		if (!checkEventDay("2020-12-21", "2021-01-31")) {
//			free_point = 0;
//		}
//		// s:다퍼줘 이벤트 종료
		dto.setPay_result_product_name(pay_result_product_name);
		dto.setPay_point(pay_point);
		dto.setFree_point(free_point);
		dto.setPay_result_point(pay_point+free_point);
		dto.setPay_result_pay(pay_result_pay);
		dto.setPay_result_type(pay_result_type);

		int result = payMapper.insertPay(dto);

		if(result == 1) {
			resultDto.setResult(Utils.SUCCESS);
			resultDto.setNo(dto.getNo());
		}

		return resultDto;
	}

	@Override
	public DefaultDTO startPayIOSV4(int user_no, String product_id) {
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
			System.err.println("API : startPayIOSV4 -----------------------------------");
			System.err.println("[" + dateStr+ " "+timeStr+"] "+ip);  // 사용자 ip
			System.err.println("user_no: " + user_no);
			System.err.println("product_id: " + product_id);
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



		String pay_result_product_name ="";
		int pay_result_pay = 0;
		int pay_point = 0;
		int free_point = 0;
		String pay_result_type = "ios";

		for(int i=0;i<Utils.NMC_ID.length;i++){
			if(product_id.equals(Utils.NMC_ID[i])){
				pay_result_product_name = Utils.NMC_NAME[i];
				// pay_result_pay =  Utils.NMC_PRICE[i];
				pay_result_pay =  Utils.NMC_PRICE_IOS[i];

				pay_point = Utils.NMC_POINT[i];
				free_point = 0;
			}
		}

		DefaultDTO resultDto = new DefaultDTO();

		if(pay_result_pay != 0) {
			PayDTO dto = new PayDTO();
			dto.setUser_no(user_no);
			dto.setPay_status(0);
			dto.setPay_result_product_name(pay_result_product_name);
			dto.setPay_point(pay_point);
			dto.setFree_point(free_point);
			dto.setPay_result_point(pay_point + free_point);
			dto.setPay_result_pay(pay_result_pay);
			dto.setPay_result_type(pay_result_type);

			int result = payMapper.insertPay(dto);

			if (result == 1) {
				resultDto.setResult(Utils.SUCCESS);
				resultDto.setNo(dto.getNo());
			}
		}

		return resultDto;
	}

	@Override
	public DefaultDTO startPayAndroidInApp(int user_no, int pay_result_pay, String pay_result_type, int pay_result_point, String pay_result_product_name) {
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
			System.err.println("API : startPayAndroidInApp -----------------------------------");
			System.err.println("[" + dateStr+ " "+timeStr+"] "+ip);  // 사용자 ip
			System.err.println("user_no: " + user_no);
			System.err.println("pay_result_pay: " + pay_result_pay);
			System.err.println("pay_result_type: " + pay_result_type);
			System.err.println("pay_result_point: " + pay_result_point);
			System.err.println("pay_result_product_name: " + pay_result_product_name);
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		}

		DefaultDTO resultDto = new DefaultDTO();

		PayDTO dto = new PayDTO();

		dto.setUser_no(user_no);
		dto.setPay_result_pay(pay_result_pay);
		dto.setPay_status(0);
		dto.setPay_result_point(pay_result_point);
		dto.setPay_result_product_name(pay_result_product_name);
		dto.setPay_result_type(pay_result_type);

		if(pay_result_product_name.equals("3,000 P")){
			dto.setPay_point(3000);
			dto.setFree_point(0);
		}else if(pay_result_product_name.equals("6,000 P")){
			dto.setPay_point(6000);
			dto.setFree_point(0);
		}else if(pay_result_product_name.equals("18,000 P")){
			dto.setPay_point(18000);
			dto.setFree_point(0);
		}else if(pay_result_product_name.equals("30,000 P")){
			dto.setPay_point(30000);
			dto.setFree_point(0);
		}else if(pay_result_product_name.equals("60,000 P")){
			dto.setPay_point(60000);
			dto.setFree_point(0);
		}else if(pay_result_product_name.equals("120,000 P")){
			dto.setPay_point(120000);
			dto.setFree_point(0);
		}

		int result = payMapper.insertPay(dto);

		if(result == 1) {
			resultDto.setResult(Utils.SUCCESS);
			resultDto.setNo(dto.getNo());
		}


		if (fos != null) {
			try {
				System.err.println("응답 PayResultNo: " + dto.getNo());
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		return resultDto;
	}

	@Override
	public ProductListDTO getInAppProductList() {
		ArrayList<ProductDTO> productDTOArrayList = payMapper.getInAppProductList();
		ProductListDTO productListDTO = new ProductListDTO();
		productListDTO.setProductList(productDTOArrayList);
		productListDTO.setResult(Utils.SUCCESS);
		return productListDTO;
	}

	@Override
	public ProductListDTO getInAppProductListMC() {
		ArrayList<ProductDTO> productDTOArrayList = payMapper.getInAppProductListMC();
		ProductListDTO productListDTO = new ProductListDTO();
		productListDTO.setProductList(productDTOArrayList);
		productListDTO.setResult(Utils.SUCCESS);
		return productListDTO;
	}


	@Override
	public ProductListDTO getInAppProductListNMC() {
		ArrayList<ProductDTO> productDTOArrayList = payMapper.getInAppProductListNMC();
		ProductListDTO productListDTO = new ProductListDTO();
		productListDTO.setProductList(productDTOArrayList);
		productListDTO.setResult(Utils.SUCCESS);
		return productListDTO;
	}


	@Override
	public DefaultDTO startPayAndroidInAppV3(int user_no, String product_id){
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
			System.err.println("API : startPayAndroidInAppV3 -----------------------------------");
			System.err.println("[" + dateStr+ " "+timeStr+"] "+ip);  // 사용자 ip
			System.err.println("user_no: " + user_no);
			System.err.println("product_id: " + product_id);
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		}

		int payResultPay = 0;
		int payPoint = 0;
		int freePoint = 0;
		String productName = "";
		if (product_id.equals(Utils.PD_0001)){
			payResultPay = Utils.PD_0001_PRICE;
			productName = Utils.PD_0001_NAME;
			payPoint = Utils.PD_0001_POINT;
			freePoint = Utils.PD_0001_FREE_POINT;
		}else if (product_id.equals(Utils.PD_0002)){
			payResultPay = Utils.PD_0002_PRICE;
			productName = Utils.PD_0002_NAME;
			payPoint = Utils.PD_0002_POINT;
			freePoint = Utils.PD_0002_FREE_POINT;
		}else if (product_id.equals(Utils.PD_0003)){
			payResultPay = Utils.PD_0003_PRICE;
			productName = Utils.PD_0003_NAME;
			payPoint = Utils.PD_0003_POINT;
			freePoint = Utils.PD_0003_FREE_POINT;
		}else if (product_id.equals(Utils.PD_0004)){
			payResultPay = Utils.PD_0004_PRICE;
			productName = Utils.PD_0004_NAME;
			payPoint = Utils.PD_0004_POINT;
			freePoint = Utils.PD_0004_FREE_POINT;
		}else if (product_id.equals(Utils.PD_0005)){
			payResultPay = Utils.PD_0005_PRICE;
			productName = Utils.PD_0005_NAME;
			payPoint = Utils.PD_0005_POINT;
			freePoint = Utils.PD_0005_FREE_POINT;
		}else if (product_id.equals(Utils.PD_0006)){
			payResultPay = Utils.PD_0006_PRICE;
			productName = Utils.PD_0006_NAME;
			payPoint = Utils.PD_0006_POINT;
			freePoint = Utils.PD_0006_FREE_POINT;
		}

		// s:다퍼줘 이벤트 종료
		if (!checkEventDay("2020-12-21", "2021-01-31")) {
			freePoint = 0;
		}
		// e:다퍼줘 이벤트 종료

		DefaultDTO resultDto = new DefaultDTO();

		PayDTO dto = new PayDTO();

		dto.setUser_no(user_no);
		dto.setPay_result_pay(payResultPay);
		dto.setPay_status(0);
		dto.setPay_result_point(payPoint+freePoint);
		dto.setPay_result_product_name(productName);
		dto.setPay_result_type(Utils.IN_APP_ANDROID);
		dto.setPay_point(payPoint);
		dto.setFree_point(freePoint);

		int result = payMapper.insertPay(dto);

		if(result == 1) {
			resultDto.setResult(Utils.SUCCESS);
			resultDto.setNo(dto.getNo());
		}


		if (fos != null) {
			try {
				System.err.println("응답 PayResultNo: " + dto.getNo());
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		return resultDto;
	}

	@Override
	public DefaultDTO startPayMCAndroidInApp(int user_no, String product_id){
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
			System.err.println("API : startPayMCAndroidInApp -----------------------------------");
			System.err.println("[" + dateStr+ " "+timeStr+"] "+ip);  // 사용자 ip
			System.err.println("user_no: " + user_no);
			System.err.println("product_id: " + product_id);
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		}

		int payResultPay = 0;
		int payPoint = 0;
		int freePoint = 0;
		String productName = "";
		if (product_id.equals(Utils.MC_01)){
			payResultPay = Utils.MC_01_PRICE;
			productName = Utils.MC_01_NAME;
			payPoint = Utils.MC_01_POINT;
			freePoint = 0;
		}else if (product_id.equals(Utils.MC_02)){
			payResultPay = Utils.MC_02_PRICE;
			productName = Utils.MC_02_NAME;
			payPoint = Utils.MC_02_POINT;
			freePoint = 0;
		}else if (product_id.equals(Utils.MC_03)){
			payResultPay = Utils.MC_03_PRICE;
			productName = Utils.MC_03_NAME;
			payPoint = Utils.MC_03_POINT;
			freePoint = 0;
		}else if (product_id.equals(Utils.MC_04)){
			payResultPay = Utils.MC_04_PRICE;
			productName = Utils.MC_04_NAME;
			payPoint = Utils.MC_04_POINT;
			freePoint = 0;
		}else if (product_id.equals(Utils.MC_05)){
			payResultPay = Utils.MC_05_PRICE;
			productName = Utils.MC_05_NAME;
			payPoint = Utils.MC_05_POINT;
			freePoint = 0;
		}else if (product_id.equals(Utils.MC_06)){
			payResultPay = Utils.MC_06_PRICE;
			productName = Utils.MC_06_NAME;
			payPoint = Utils.MC_06_POINT;
			freePoint = 0;
		}

//		// s:다퍼줘 이벤트 종료
//		if (!checkEventDay("2020-12-21", "2021-01-31")) {
//			freePoint = 0;
//		}
//		// e:다퍼줘 이벤트 종료

		DefaultDTO resultDto = new DefaultDTO();

		PayDTO dto = new PayDTO();

		dto.setUser_no(user_no);
		dto.setPay_result_pay(payResultPay);
		dto.setPay_status(0);
		dto.setPay_result_point(payPoint+freePoint);
		dto.setPay_result_product_name(productName);
		dto.setPay_result_type(Utils.IN_APP_ANDROID);
		dto.setPay_point(payPoint);
		dto.setFree_point(freePoint);

		int result = payMapper.insertPay(dto);

		if(result == 1) {
			resultDto.setResult(Utils.SUCCESS);
			resultDto.setNo(dto.getNo());
		}


		if (fos != null) {
			try {
				System.err.println("응답 PayResultNo: " + dto.getNo());
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


		return resultDto;
	}


	@Override
	public DefaultDTO startPayAndroidInAppV4(int user_no, String product_id){
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
			System.err.println("API : startPayAndroidInAppV4 -----------------------------------");
			System.err.println("[" + dateStr+ " "+timeStr+"] "+ip);  // 사용자 ip
			System.err.println("user_no: " + user_no);
			System.err.println("product_id: " + product_id);
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

		int payResultPay = 0;
		int payPoint = 0;
		int freePoint = 0;
		String productName = "";

		for(int i=0;i<Utils.NMC_ID.length;i++){
			if(product_id.equals(Utils.NMC_ID[i])){
				productName = Utils.NMC_NAME[i];
				payResultPay =  Utils.NMC_PRICE[i];
				payPoint = Utils.NMC_POINT[i];
				freePoint = 0;
			}
		}

		DefaultDTO resultDto = new DefaultDTO();

		if(payResultPay != 0) {
			PayDTO dto = new PayDTO();

			dto.setUser_no(user_no);
			dto.setPay_result_pay(payResultPay);
			dto.setPay_status(0);
			dto.setPay_result_point(payPoint + freePoint);
			dto.setPay_result_product_name(productName);
			dto.setPay_result_type(Utils.IN_APP_ANDROID);
			dto.setPay_point(payPoint);
			dto.setFree_point(freePoint);

			int result = payMapper.insertPay(dto);

			if (result == 1) {
				resultDto.setResult(Utils.SUCCESS);
				resultDto.setNo(dto.getNo());
			}
		}
		return resultDto;
	}


//	@Override
//	public String resultPay(int user_no, int pay_no, String pay_type, String product_name, int amount, int point, String DaoPayNum, String user_adid, String user_id) {
//
//		HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//		String ip = req.getHeader("X-FORWARDED-FOR");
//		if (ip == null)
//			ip = req.getRemoteAddr();
//
//		// 사용자 요청 데이터를 로그를 파일로 저장하는 코드
//		PrintStream ps = null;
//		FileOutputStream fos=null;
//		Date today = new Date();
//
//		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
//		SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
//		String dateStr = date.format(today);
//		String timeStr = time.format(today);
//
//		try {
//			fos = new FileOutputStream(API_LOG_PATH+"pay_"+dateStr+".log",true); // error.log파일에 출력 준비
//			ps=new PrintStream(fos); // err의 출력을 화면이 아닌, error.log파일로 변경
//			System.setErr(ps);
//			System.err.println("API : resultPay -----------------------------------");
//			System.err.println("[" + dateStr+ " "+timeStr+"] "+ip);  // 사용자 ip
//			System.err.println("user_no: " + user_no);
//			System.err.println("pay_no: " + pay_no);
//			System.err.println("pay_type: " + pay_type);
//			System.err.println("product_name: " + product_name);
//			System.err.println("amount: " + amount);
//			System.err.println("point: " + point);
//			System.err.println("DaoPayNum: " + DaoPayNum);
//			System.err.println("user_adid: " + user_adid);
//			System.err.println("user_id: " + user_id);
//		} catch (FileNotFoundException fileNotFoundException) {
//			fileNotFoundException.printStackTrace();
//		}
//
//		String result = Utils.FAIL;
//		PayDTO dto = new PayDTO();
//		dto.setUser_no(user_no);
//		dto.setPay_status(1);
//		dto.setPay_result_type(pay_type);
//		dto.setNo(pay_no);
//		dto.setPay_daoutrx(DaoPayNum);
//
//		// pay_result 업데이트 결과 성공(1) 또는 실패(0) 값
//		int resultUpdate = 0;
//		// 이벤트 기간 무료 포인트
//		int event_free_point = 0;
//		// 상품에 포함된 기본 무료포인트
//		int free_point = 0;
//
//		PayResultDTO payresultdto = payMapper.getPayResult(pay_no);
//		if (payresultdto ==null) System.err.println("getPayResult 쿼리 실행 에러");
//
//		int pay_point = payresultdto.getPay_point();
//		free_point = payresultdto.getFree_point();
//
//		int no = payresultdto.getNo();
//
//		// 첫결제 이벤트 기간인 경우
//		if (checkEventDay(Utils.EVENT_DATE_START, Utils.EVENT_DATE_END)) {
//
//			// (모바일, 신용카드, iOS 결제) + 무통장 전체 포함한 결제 횟수 가져오기
//			int payCount = payMapper.getPayListCount(user_no);
//
//			if (payCount > 0){
//				// 첫 결제가 아닌 경우
//				// PAY_RESULT(모바일, 신용카드, iOS 결제) 테이블 업데이트(결제 상태 등)
//				resultUpdate = payMapper.updatePayStatus(dto);
//				if (resultUpdate == 0) System.err.println("updatePayStatus 쿼리 실행 에러");
//
//			}else{
//				// 첫 결제인 경우
//				if (product_name.equals(Utils.PD_0001_NAME)){
//					event_free_point = Utils.PD_0001_EVENT_POINT;
//				}else if (product_name.equals(Utils.PD_0002_NAME)){
//					event_free_point = Utils.PD_0002_EVENT_POINT;
//				}else if (product_name.equals(Utils.PD_0003_NAME)){
//					event_free_point = Utils.PD_0003_EVENT_POINT;
//				}else if (product_name.equals(Utils.PD_0004_NAME)){
//					event_free_point = Utils.PD_0004_EVENT_POINT;
//				}else if (product_name.equals(Utils.PD_0005_NAME)){
//					event_free_point = Utils.PD_0005_EVENT_POINT;
//				}else if (product_name.equals(Utils.PD_0006_NAME)){
//					event_free_point = Utils.PD_0006_EVENT_POINT;
//				}
//
//				// 이벤트 포인트도 free point 컬럼에 추가하기
//				dto.setFree_point(event_free_point);
//
//				resultUpdate = payMapper.updatePayEventStatus(dto);
//				if (resultUpdate == 0) System.err.println("updatePayEventStatus 쿼리 실행 에러");
//			}
//		}else{ // 이벤트가 아닌 경우
//
//			// PAY_RESULT(모바일, 신용카드, iOS 결제) 테이블 업데이트(결제 상태 등)
//			resultUpdate = payMapper.updatePayStatus(dto);
//			if (resultUpdate == 0) System.err.println("updatePayStatus 쿼리 실행 에러");
//		}
//
//		// pay_result 업데이트에 성공한 경우
//		if(resultUpdate == 1) {
//			UserPointDTO pointdto = userMapper.getMyPoint(user_no);
//			if (pointdto == null) System.err.println("getMyPoint 쿼리 실행 에러");
//
//			UserPointDTO pDto = new UserPointDTO();
//			pDto.setNo(user_no);
////			pDto.setUser_point(getMyPoint + point);
//			pDto.setUser_pay_point(pointdto.getUser_pay_point() + pay_point);
//			// 사용자 현재 포인트 + 결제 상품이 가진 기본 무료포인트 + 이벤트 무료 포인트
//			pDto.setUser_free_point(pointdto.getUser_free_point() + free_point + event_free_point);
//
//			int resultPoint = userMapper.setPoint(pDto);
//			if (resultPoint == 0) System.err.println("setPoint 쿼리 실행 에러");
//
//			if(resultPoint == 1) {
//				UserInfoDTO udto = userMapper.getUserInfo(user_no);
//				if (udto == null) System.err.println("getUserInfo 쿼리 실행 에러");
//
//				String userServiceType = udto.getUser_service_type();
//				String textHello = "안녕하세요! Let’s All night!\n올캠입니다.\n";
//				if("onelive".equals(userServiceType)) {
//					textHello = "안녕하세요! 원라이브입니다.\n";
//				}else if("wowcam".equals(userServiceType)) {
//					textHello = "안녕하세요! 와우캠입니다.\n";
//				}else if("toptalk".equals(userServiceType)) {
//					textHello = "안녕하세요! 탑톡입니다.\n";
//				}
//
//				AddPayListDTO payListDto = new AddPayListDTO();
//				payListDto.setUser_no(user_no);
//				payListDto.setResult_no(pay_no);
//				payListDto.setResult_payment_type(pay_type);
//				payListDto.setResult_product_name(product_name);
//				payListDto.setResult_point(point);
//				payListDto.setResult_pay(amount);
//				payListDto.setPay_point(pay_point);
//				payListDto.setFree_point(free_point+event_free_point);
//				payListDto.setUser_service_type(userServiceType);
//
//				int resultInsert = payMapper.insertPayList(payListDto);	//pay_list 등록
//				if (resultInsert == 0) System.err.println("insertPayList 쿼리 실행 에러");
//
//				UseHistoryDTO hDto = new UseHistoryDTO();
//				hDto.setUser_no(user_no);
//				hDto.setUse_etc(payListDto.getNo() + "");
//				hDto.setUse_content(product_name + "구매 (" + pay_type + ")");
//				hDto.setUse_etc_user_no(-1);
//
//				// 포인트 적립을 히스토리에 남기기
//				int resultHistory = 0;
//				if(pay_point > 0) { //유료포인트
//					hDto.setUse_cnt(pay_point);
//					hDto.setUse_type(0);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트
//					resultHistory = liveCallMapper.insertUseHistory(hDto);
//				}
//				if(free_point > 0) { //무료포인트
//					hDto.setUse_cnt(free_point);
//					hDto.setUse_type(2);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트
//					resultHistory = liveCallMapper.insertUseHistory(hDto);
//				}
//
//				if (event_free_point >0){
//					hDto.setUse_cnt(event_free_point);
//					hDto.setUse_type(2);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트
//					hDto.setUse_content("첫 결제 이벤트 포인트 적립");
//					resultHistory = liveCallMapper.insertUseHistory(hDto);
//				}
//				if (resultHistory == 0) System.err.println("insertUseHistory 쿼리 실행 에러");
//
//				// 채팅 메세지 보내기 + 남기기
//				ChatDTO cdto = new ChatDTO();
//				cdto.setChat_text(
//						textHello + udto.getUser_nick_name() + " 회원님, 포인트 구매해 주셔서 감사합니다.\n" +
//						"\n" +
//						"오늘도 행복한 하루 보내시기 바랍니다."
//								);
//				cdto.setFrom_user_no(1);	// 1 : 관리자
//				cdto.setTo_user_no(dto.getUser_no());
//
//				int resultChat = chatMapper.sendChat(cdto);
//				if (resultChat == 0) System.err.println("sendChat 쿼리 실행 에러");
//
//				// user_adid가 빈 값이 아니거나 유저 앱이 올캠인 경우만 광고사에 데이터를 보냄
//				if(!"".equals(user_adid) && userServiceType.equals("allcam")) {
//					String pResult = directConnection("https://partner.allcam.co.kr/APP_acc/app_data.php?TYPE=P&UID=" +user_no
//							+ "&MONEY=" + amount
//							+ "&ADID=" + user_adid
//							+ "&PAYID="+no
//							+ "&PAYTYPE=0");
//
//					System.err.println("partner데이터 전달 "+product_name);
//				}
//
//				result = Utils.SUCCESS;
//			}
//		}
//
//		try {
//			if(fos != null)
//				fos.close();
//		} catch (IOException ioException) {
//			ioException.printStackTrace();
//		}
//
//		return result;
//	}


	@Override
	public String resultPayV2(int user_no, int pay_no, String pay_type, String product_name, int amount, int point, String DaoPayNum, String user_adid, String user_id) {

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
			System.err.println("API : resultPayV2 -----------------------------------");
			System.err.println("[" + dateStr+ " "+timeStr+"] "+ip);  // 사용자 ip
			System.err.println("user_no: " + user_no);
			System.err.println("pay_no: " + pay_no);
			System.err.println("pay_type: " + pay_type);
			System.err.println("product_name: " + product_name);
			System.err.println("amount: " + amount);
			System.err.println("point: " + point);
			System.err.println("DaoPayNum: " + DaoPayNum);
			System.err.println("user_adid: " + user_adid);
			System.err.println("user_id: " + user_id);
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		}

		String result = Utils.FAIL;
		PayDTO dto = new PayDTO();
		dto.setUser_no(user_no);
		dto.setPay_status(1);
		dto.setPay_result_type(pay_type);
		dto.setNo(pay_no);
		dto.setPay_daoutrx(DaoPayNum);

		// pay_result 업데이트 결과 성공(1) 또는 실패(0) 값
		int resultUpdate = 0;
		int free_point = 0;
		// 이벤트 기간 무료 포인트
		int event_free_point = 0;

		PayResultDTO payresultdto = payMapper.getPayResult(pay_no);
		if (payresultdto ==null) System.err.println("getPayResult 쿼리 실행 에러");

		int pay_point = payresultdto.getPay_point();
		free_point = payresultdto.getFree_point();

		int no = payresultdto.getNo();

		// 첫결제 이벤트 기간인 경우
		if (checkEventDay(Utils.EVENT_DATE_START, Utils.EVENT_DATE_END)) {

			// (모바일, 신용카드, iOS 결제) + 무통장 전체 포함한 결제 횟수 가져오기
			int payCount = payMapper.getPayListCount(user_no);

			if (payCount > 0){
				// 첫 결제가 아닌 경우
				// PAY_RESULT(모바일, 신용카드, iOS 결제) 테이블 업데이트(결제 상태 등)
				resultUpdate = payMapper.updatePayStatus(dto);
				if (resultUpdate == 0) System.err.println("updatePayStatus 쿼리 실행 에러");

			}else{
				// 첫 결제인 경우
				if (product_name.equals(Utils.PD_0001_NAME)){
					event_free_point = Utils.PD_0001_EVENT_POINT;
				}else if (product_name.equals(Utils.PD_0002_NAME)){
					event_free_point = Utils.PD_0002_EVENT_POINT;
				}else if (product_name.equals(Utils.PD_0003_NAME)){
					event_free_point = Utils.PD_0003_EVENT_POINT;
				}else if (product_name.equals(Utils.PD_0004_NAME)){
					event_free_point = Utils.PD_0004_EVENT_POINT;
				}else if (product_name.equals(Utils.PD_0005_NAME)){
					event_free_point = Utils.PD_0005_EVENT_POINT;
				}else if (product_name.equals(Utils.PD_0006_NAME)){
					event_free_point = Utils.PD_0006_EVENT_POINT;
				}

				// 이벤트 포인트도 free point 컬럼에 추가하기
				dto.setFree_point(event_free_point);

				resultUpdate = payMapper.updatePayEventStatus(dto);
				if (resultUpdate == 0) System.err.println("updatePayEventStatus 쿼리 실행 에러");
			}
		}else{ // 이벤트가 아닌 경우

			// PAY_RESULT(모바일, 신용카드, iOS 결제) 테이블 업데이트(결제 상태 등)
			resultUpdate = payMapper.updatePayStatus(dto);
			if (resultUpdate == 0) System.err.println("updatePayStatus 쿼리 실행 에러");
		}

		// pay_result 업데이트에 성공한 경우
		if(resultUpdate == 1) {
			UserPointDTO pointdto = userMapper.getMyPoint(user_no);
			if (pointdto == null) System.err.println("getMyPoint 쿼리 실행 에러");

			UserPointDTO pDto = new UserPointDTO();
			pDto.setNo(user_no);
//			pDto.setUser_point(getMyPoint + point);
			pDto.setUser_pay_point(pointdto.getUser_pay_point() + pay_point);
			// 사용자 현재 포인트
			pDto.setUser_free_point(pointdto.getUser_free_point()+ free_point + event_free_point);

			int resultPoint = userMapper.setPoint(pDto);
			if (resultPoint == 0) System.err.println("setPoint 쿼리 실행 에러");

			if(resultPoint == 1) {
				UserInfoDTO udto = userMapper.getUserInfo(user_no);
				if (udto == null) System.err.println("getUserInfo 쿼리 실행 에러");

				String userServiceType = udto.getUser_service_type();
				String textHello = "안녕하세요! Let’s All night!\n올캠입니다.\n";
				if("onelive".equals(userServiceType)) {
					textHello = "안녕하세요! 원라이브입니다.\n";
				}else if("wowcam".equals(userServiceType)) {
					textHello = "안녕하세요! 와우캠입니다.\n";
				}else if("toptalk".equals(userServiceType)) {
					textHello = "안녕하세요! 탑톡입니다.\n";
				}

				AddPayListDTO payListDto = new AddPayListDTO();
				payListDto.setUser_no(user_no);
				payListDto.setResult_no(pay_no);
				payListDto.setResult_payment_type(pay_type);
				payListDto.setResult_product_name(product_name);
				payListDto.setResult_point(point);
				payListDto.setResult_pay(amount);
				payListDto.setPay_point(pay_point);
				payListDto.setFree_point(free_point+event_free_point);
				payListDto.setUser_service_type(userServiceType);

				int resultInsert = payMapper.insertPayList(payListDto);	//pay_list 등록
				if (resultInsert == 0) System.err.println("insertPayList 쿼리 실행 에러");

				UseHistoryDTO hDto = new UseHistoryDTO();
				hDto.setUser_no(user_no);
				hDto.setUse_etc(payListDto.getNo() + "");
				hDto.setUse_content(product_name + "구매 (" + pay_type + ")");
				hDto.setUse_etc_user_no(-1);

				// 포인트 적립을 히스토리에 남기기
				int resultHistory = 0;
				if(pay_point > 0) { //유료포인트
					hDto.setUse_cnt(pay_point);
					hDto.setUse_type(0);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트
					resultHistory = liveCallMapper.insertUseHistory(hDto);
				}

				if(free_point > 0) { //무료포인트
					hDto.setUse_cnt(free_point);
					hDto.setUse_type(2);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트
					resultHistory = liveCallMapper.insertUseHistory(hDto);
				}
				if (event_free_point >0){
					hDto.setUse_cnt(event_free_point);
					hDto.setUse_type(2);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트
					hDto.setUse_content("첫 결제 이벤트 포인트 적립");
					resultHistory = liveCallMapper.insertUseHistory(hDto);
				}

				if (resultHistory == 0) System.err.println("insertUseHistory 쿼리 실행 에러");

				// 채팅 메세지 보내기 + 남기기
				ChatDTO cdto = new ChatDTO();
				cdto.setChat_text(
						textHello + udto.getUser_nick_name() + " 회원님, 포인트 구매해 주셔서 감사합니다.\n" +
								"\n" +
								"오늘도 행복한 하루 보내시기 바랍니다."
				);
				cdto.setFrom_user_no(1);	// 1 : 관리자
				cdto.setTo_user_no(dto.getUser_no());

				int resultChat = chatMapper.sendChat(cdto);
				if (resultChat == 0) System.err.println("sendChat 쿼리 실행 에러");

				if(!"".equals(user_adid)) {
					String pResult = directConnection("https://partner.allcam.co.kr/APP_acc/app_data.php?TYPE=P&UID=" +user_no
							+ "&MONEY=" + amount
							+ "&ADID=" + user_adid
							+ "&PAYID="+no
							+ "&PAYTYPE=0");

					System.err.println("partner데이터 전달 "+product_name);
				}

				result = Utils.SUCCESS;
			}
		}

		try {
			if(fos != null)
				fos.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

		return result;
	}



	@Override
	public String resultPayMC(int user_no, int pay_no, String pay_type, String product_name, int amount, int point, String DaoPayNum, String user_adid, String user_id) {

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
			System.err.println("API : resultPayMC -----------------------------------");
			System.err.println("[" + dateStr+ " "+timeStr+"] "+ip);  // 사용자 ip
			System.err.println("user_no: " + user_no);
			System.err.println("pay_no: " + pay_no);
			System.err.println("pay_type: " + pay_type);
			System.err.println("product_name: " + product_name);
			System.err.println("amount: " + amount);
			System.err.println("point: " + point);
			System.err.println("DaoPayNum: " + DaoPayNum);
			System.err.println("user_adid: " + user_adid);
			System.err.println("user_id: " + user_id);
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		}

		String result = Utils.FAIL;
		PayDTO dto = new PayDTO();
		dto.setUser_no(user_no);
		dto.setPay_status(1);
		dto.setPay_result_type(pay_type);
		dto.setNo(pay_no);
		dto.setPay_daoutrx(DaoPayNum);

		// pay_result 업데이트 결과 성공(1) 또는 실패(0) 값
		int resultUpdate = 0;
		int free_point = 0;
		// 이벤트 기간 무료 포인트
		int event_free_point = 0;

		PayResultDTO payresultdto = payMapper.getPayResult(pay_no);
		if (payresultdto ==null) System.err.println("getPayResult 쿼리 실행 에러");

		int pay_point = payresultdto.getPay_point();
		free_point = payresultdto.getFree_point();

		int no = payresultdto.getNo();

		// 첫결제 이벤트 기간인 경우
		resultUpdate = payMapper.updatePayStatus(dto);


		// pay_result 업데이트에 성공한 경우
		if(resultUpdate == 1) {
			UserPointDTO pointdto = userMapper.getMyPoint(user_no);
			if (pointdto == null) System.err.println("getMyPoint 쿼리 실행 에러");

			UserPointDTO pDto = new UserPointDTO();
			pDto.setNo(user_no);
//			pDto.setUser_point(getMyPoint + point);
			pDto.setUser_pay_point(pointdto.getUser_pay_point() + pay_point);
			// 사용자 현재 포인트
			pDto.setUser_free_point(pointdto.getUser_free_point()+ free_point + event_free_point);

			int resultPoint = userMapper.setPoint(pDto);
			if (resultPoint == 0) System.err.println("setPoint 쿼리 실행 에러");

			if(resultPoint == 1) {
				UserInfoDTO udto = userMapper.getUserInfo(user_no);
				if (udto == null) System.err.println("getUserInfo 쿼리 실행 에러");

				String userServiceType = udto.getUser_service_type();
				String textHello = "안녕하세요! Let’s All night!\n올캠입니다.\n";
				if("onelive".equals(userServiceType)) {
					textHello = "안녕하세요! 원라이브입니다.\n";
				}else if("wowcam".equals(userServiceType)) {
					textHello = "안녕하세요! 와우캠입니다.\n";
				}else if("toptalk".equals(userServiceType)) {
					textHello = "안녕하세요! 탑톡입니다.\n";
				}else if("meincam".equals(userServiceType)) {
					textHello = "안녕하세요! 미인캠입니다.\n";
				}

				AddPayListDTO payListDto = new AddPayListDTO();
				payListDto.setUser_no(user_no);
				payListDto.setResult_no(pay_no);
				payListDto.setResult_payment_type(pay_type);
				payListDto.setResult_product_name(product_name);
				payListDto.setResult_point(point);
				payListDto.setResult_pay(amount);
				payListDto.setPay_point(pay_point);
				payListDto.setFree_point(free_point+event_free_point);
				payListDto.setUser_service_type(userServiceType);

				int resultInsert = payMapper.insertPayList(payListDto);	//pay_list 등록
				if (resultInsert == 0) System.err.println("insertPayList 쿼리 실행 에러");

				UseHistoryDTO hDto = new UseHistoryDTO();
				hDto.setUser_no(user_no);
				hDto.setUse_etc(payListDto.getNo() + "");
				hDto.setUse_content(product_name + "구매 (" + pay_type + ")");
				hDto.setUse_etc_user_no(-1);

				// 포인트 적립을 히스토리에 남기기
				int resultHistory = 0;
				if(pay_point > 0) { //유료포인트
					hDto.setUse_cnt(pay_point);
					hDto.setUse_type(0);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트
					resultHistory = liveCallMapper.insertUseHistory(hDto);
				}

				if(free_point > 0) { //무료포인트
					hDto.setUse_cnt(free_point);
					hDto.setUse_type(2);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트
					resultHistory = liveCallMapper.insertUseHistory(hDto);
				}
				if (event_free_point >0){
					hDto.setUse_cnt(event_free_point);
					hDto.setUse_type(2);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트
					hDto.setUse_content("첫 결제 이벤트 포인트 적립");
					resultHistory = liveCallMapper.insertUseHistory(hDto);
				}

				if (resultHistory == 0) System.err.println("insertUseHistory 쿼리 실행 에러");

				// 채팅 메세지 보내기 + 남기기
				ChatDTO cdto = new ChatDTO();
				cdto.setChat_text(
						textHello + udto.getUser_nick_name() + " 회원님, 포인트 구매해 주셔서 감사합니다.\n" +
								"\n" +
								"오늘도 행복한 하루 보내시기 바랍니다."
				);
				cdto.setFrom_user_no(1);	// 1 : 관리자
				cdto.setTo_user_no(dto.getUser_no());

				int resultChat = chatMapper.sendChat(cdto);
				if (resultChat == 0) System.err.println("sendChat 쿼리 실행 에러");

				if(!"".equals(user_adid)) {
					String pResult = directConnection("https://partner.allcam.co.kr/APP_acc/app_data.php?TYPE=P&UID=" +user_no
							+ "&MONEY=" + amount
							+ "&ADID=" + user_adid
							+ "&PAYID="+no
							+ "&PAYTYPE=0");

					System.err.println("partner데이터 전달 "+product_name);
				}

				result = Utils.SUCCESS;
			}
		}else{
			System.err.println("updatePayStatus 쿼리 실행 에러");
		}

		try {
			if(fos != null)
				fos.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

		return result;
	}


	@Override
	public String resultPayV3(int user_no, int pay_no, String pay_type, String product_name, int amount, int point, String DaoPayNum, String user_adid, String user_id) {

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
			System.err.println("API : resultPayV3 -----------------------------------");
			System.err.println("[" + dateStr+ " "+timeStr+"] "+ip);  // 사용자 ip
			System.err.println("user_no: " + user_no);
			System.err.println("pay_no: " + pay_no);
			System.err.println("pay_type: " + pay_type);
			System.err.println("product_name: " + product_name);
			System.err.println("amount: " + amount);
			System.err.println("point: " + point);
			System.err.println("DaoPayNum: " + DaoPayNum);
			System.err.println("user_adid: " + user_adid);
			System.err.println("user_id: " + user_id);
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		} finally {
			if (fos !=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		String result = Utils.FAIL;
		PayDTO dto = new PayDTO();
		dto.setUser_no(user_no);
		dto.setPay_status(1);
		dto.setPay_result_type(pay_type);
		dto.setNo(pay_no);
		dto.setPay_daoutrx(DaoPayNum);

		// pay_result 업데이트 결과 성공(1) 또는 실패(0) 값
		int resultUpdate = 0;
		int free_point = 0;
		// 이벤트 기간 무료 포인트
		int event_free_point = 0;

		PayResultDTO payresultdto = payMapper.getPayResult(pay_no);
		if (payresultdto ==null) System.err.println("getPayResult 쿼리 실행 에러");

		int pay_point = payresultdto.getPay_point();
		free_point = payresultdto.getFree_point();

		int no = payresultdto.getNo();

		// 첫결제 이벤트 기간인 경우
		resultUpdate = payMapper.updatePayStatus(dto);


		// pay_result 업데이트에 성공한 경우
		if(resultUpdate == 1) {
			UserPointDTO pointdto = userMapper.getMyPoint(user_no);
			if (pointdto == null) System.err.println("getMyPoint 쿼리 실행 에러");

			UserPointDTO pDto = new UserPointDTO();
			pDto.setNo(user_no);
//			pDto.setUser_point(getMyPoint + point);
			pDto.setUser_pay_point(pointdto.getUser_pay_point() + pay_point);
			// 사용자 현재 포인트
			pDto.setUser_free_point(pointdto.getUser_free_point()+ free_point + event_free_point);

			int resultPoint = userMapper.setPoint(pDto);
			if (resultPoint == 0) System.err.println("setPoint 쿼리 실행 에러");

			if(resultPoint == 1) {
				UserInfoDTO udto = userMapper.getUserInfo(user_no);
				if (udto == null) System.err.println("getUserInfo 쿼리 실행 에러");

				String userServiceType = udto.getUser_service_type();
				String textHello = "안녕하세요! Let’s All night!\n올캠입니다.\n";
				if("onelive".equals(userServiceType)) {
					textHello = "안녕하세요! 원라이브입니다.\n";
				}else if("wowcam".equals(userServiceType)) {
					textHello = "안녕하세요! 와우캠입니다.\n";
				}else if("toptalk".equals(userServiceType)) {
					textHello = "안녕하세요! 탑톡입니다.\n";
				}else if("meincam".equals(userServiceType)) {
					textHello = "안녕하세요! 미인캠입니다.\n";
				}

				AddPayListDTO payListDto = new AddPayListDTO();
				payListDto.setUser_no(user_no);
				payListDto.setResult_no(pay_no);
				payListDto.setResult_payment_type(pay_type);
				payListDto.setResult_product_name(product_name);
				payListDto.setResult_point(point);
				payListDto.setResult_pay(amount);
				payListDto.setPay_point(pay_point);
				payListDto.setFree_point(free_point+event_free_point);
				payListDto.setUser_service_type(userServiceType);

				int resultInsert = payMapper.insertPayList(payListDto);	//pay_list 등록
				if (resultInsert == 0) System.err.println("insertPayList 쿼리 실행 에러");

				UseHistoryDTO hDto = new UseHistoryDTO();
				hDto.setUser_no(user_no);
				hDto.setUse_etc(payListDto.getNo() + "");
				hDto.setUse_content(product_name + "구매 (" + pay_type + ")");
				hDto.setUse_etc_user_no(-1);

				// 포인트 적립을 히스토리에 남기기
				int resultHistory = 0;
				if(pay_point > 0) { //유료포인트
					hDto.setUse_cnt(pay_point);
					hDto.setUse_type(0);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트
					resultHistory = liveCallMapper.insertUseHistory(hDto);
				}

				if(free_point > 0) { //무료포인트
					hDto.setUse_cnt(free_point);
					hDto.setUse_type(2);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트
					resultHistory = liveCallMapper.insertUseHistory(hDto);
				}
				if (event_free_point >0){
					hDto.setUse_cnt(event_free_point);
					hDto.setUse_type(2);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트
					hDto.setUse_content("첫 결제 이벤트 포인트 적립");
					resultHistory = liveCallMapper.insertUseHistory(hDto);
				}

				if (resultHistory == 0) System.err.println("insertUseHistory 쿼리 실행 에러");

				// 채팅 메세지 보내기 + 남기기
				ChatDTO cdto = new ChatDTO();
				cdto.setChat_text(
						textHello + udto.getUser_nick_name() + " 회원님, 포인트 구매해 주셔서 감사합니다.\n" +
								"\n" +
								"오늘도 행복한 하루 보내시기 바랍니다."
				);
				cdto.setFrom_user_no(1);	// 1 : 관리자
				cdto.setTo_user_no(dto.getUser_no());

				int resultChat = chatMapper.sendChat(cdto);
				if (resultChat == 0) System.err.println("sendChat 쿼리 실행 에러");

				if(!"".equals(user_adid)) {
					String pResult = directConnection("https://partner.allcam.co.kr/APP_acc/app_data.php?TYPE=P&UID=" +user_no
							+ "&MONEY=" + amount
							+ "&ADID=" + user_adid
							+ "&PAYID="+no
							+ "&PAYTYPE=0");

					System.err.println("partner데이터 전달 "+product_name);
				}

				result = Utils.SUCCESS;
			}
		}else{
			System.err.println("updatePayStatus 쿼리 실행 에러");
		}

		try {
			if(fos != null)
				fos.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

		return result;
	}


	@Override
	public String resultPayIOS(int user_no, int pay_no, String pay_type, String product_name, int amount, int point, String DaoPayNum, String user_adid, String user_id, String receipt_data) {
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
			System.err.println("API : resultPayIOS -----------------------------------");
			System.err.println("receipt_data: " + receipt_data);
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		} finally {
			if (fos !=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}


		if (DaoPayNum != null && pay_no > 0) {
			return resultPayV2(user_no, pay_no, pay_type, product_name, amount, point, DaoPayNum, user_adid, user_id);
		}else {
			if(null != receipt_data) {
				receipt_data = receipt_data.replaceAll(" ", "+"); // + 기호 공백 처리 원복
				JSONObject receiptJson = new JSONObject();
				System.out.println("receipt_data replace : " + receipt_data);

				receiptJson.put("receipt-data", receipt_data);

				String verifyReceiptURL = "https://buy.itunes.apple.com/verifyReceipt";    //상용

				String verifyReceiptResult = getReceiptJson(verifyReceiptURL, receiptJson);
				JSONObject verifyReceiptResultJson = new JSONObject(verifyReceiptResult);
				System.out.println("실 영수 : " + verifyReceiptResult);
				int receiptStatus = verifyReceiptResultJson.getInt("status");
				if (receiptStatus == 21007) { //테스트 영수증
			/*
			String verifyReceiptURLTest = "https://sandbox.itunes.apple.com/verifyReceipt"; //테스트
			String verifyReceiptResultTest = getReceiptJson(verifyReceiptURLTest, receiptJson);
			System.out.println("테스트 영수 : " + verifyReceiptResultTest);
			JSONObject verifyReceiptResultJsonTest = new JSONObject(verifyReceiptResultTest);

			int receiptStatusTest = verifyReceiptResultJsonTest.getInt("status");
			if(receiptStatusTest == 0){ //테스트 영수증 확인 완
				return resultPay(user_no, pay_no, pay_type, product_name, amount, point, "TEST"+DaoPayNum, user_adid, user_id);
			}else{
				System.out.println("receiptStatusTest : " + receiptStatusTest);
				return Utils.FAIL;
			}
			 */
					return resultPayV2(user_no, pay_no, pay_type, product_name, amount, point, "TEST" + DaoPayNum, user_adid, user_id);

				} else if (receiptStatus == 0) { //실결제 영수증 확인 완료
					return resultPayV2(user_no, pay_no, pay_type, product_name, amount, point, DaoPayNum, user_adid, user_id);
				} else { //기타 오류
					System.out.println("receiptStatus : " + receiptStatus);
					return Utils.FAIL;
				}
			}else{
				System.out.println("receiptData : null");
				return Utils.FAIL;
			}
		}
	}


	@Override
	public String resultPayIOSV2(int user_no, int pay_no, String pay_type, String product_name, int amount, int point, String DaoPayNum, String user_adid, String user_id, String receipt_data) {
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
			System.err.println("API : resultPayIOSV2 -----------------------------------");
			System.err.println("receipt_data: " + receipt_data);
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		} finally {
			if (fos !=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		int pay_result_pay =0;
		int pay_point =0;
		int free_point =0;

		if (product_name.equals(Utils.PD_0001_NAME)){
			pay_result_pay = Utils.PD_0001_PRICE_IOS;
			pay_point = Utils.PD_0001_POINT;
			free_point = Utils.PD_0001_FREE_POINT;
		}else if (product_name.equals(Utils.PD_0002_NAME)){
			pay_result_pay = Utils.PD_0002_PRICE_IOS;
			pay_point = Utils.PD_0002_POINT;
			free_point = Utils.PD_0002_FREE_POINT;
		}else if (product_name.equals(Utils.PD_0003_NAME)){
			pay_result_pay = Utils.PD_0003_PRICE_IOS;
			pay_point = Utils.PD_0003_POINT;
			free_point = Utils.PD_0003_FREE_POINT;
		}else if (product_name.equals(Utils.PD_0004_NAME)){
			pay_result_pay = Utils.PD_0004_PRICE_IOS;
			pay_point = Utils.PD_0004_POINT;
			free_point = Utils.PD_0004_FREE_POINT;
		}else if (product_name.equals(Utils.PD_0005_NAME)){
			pay_result_pay = Utils.PD_0005_PRICE_IOS;
			pay_point = Utils.PD_0005_POINT;
			free_point = Utils.PD_0005_FREE_POINT;
		}else if (product_name.equals(Utils.PD_0006_NAME)){
			pay_result_pay = Utils.PD_0006_PRICE_IOS;
			pay_point = Utils.PD_0006_POINT;
			free_point = Utils.PD_0006_FREE_POINT;
		}else{
			return Utils.FAIL;
		}

		// s:다퍼줘 이벤트 종료
		if (!checkEventDay("2020-12-21", "2021-01-31")) {
			free_point = 0;
		}
		// e:다퍼줘 이벤트 종료

		int total_point = pay_point + free_point;

		if (DaoPayNum != null && pay_no > 0) {
			return resultPayV2(user_no, pay_no, pay_type, product_name, pay_result_pay, total_point, DaoPayNum, user_adid, user_id);
		}else {
			if(null != receipt_data) {
				receipt_data = receipt_data.replaceAll(" ", "+"); // + 기호 공백 처리 원복
				JSONObject receiptJson = new JSONObject();
				System.out.println("receipt_data replace : " + receipt_data);

				receiptJson.put("receipt-data", receipt_data);

				String verifyReceiptURL = "https://buy.itunes.apple.com/verifyReceipt";    //상용

				String verifyReceiptResult = getReceiptJson(verifyReceiptURL, receiptJson);
				JSONObject verifyReceiptResultJson = new JSONObject(verifyReceiptResult);
				System.out.println("실 영수 : " + verifyReceiptResult);
				int receiptStatus = verifyReceiptResultJson.getInt("status");
				if (receiptStatus == 21007) { //테스트 영수증
			/*
			String verifyReceiptURLTest = "https://sandbox.itunes.apple.com/verifyReceipt"; //테스트
			String verifyReceiptResultTest = getReceiptJson(verifyReceiptURLTest, receiptJson);
			System.out.println("테스트 영수 : " + verifyReceiptResultTest);
			JSONObject verifyReceiptResultJsonTest = new JSONObject(verifyReceiptResultTest);

			int receiptStatusTest = verifyReceiptResultJsonTest.getInt("status");
			if(receiptStatusTest == 0){ //테스트 영수증 확인 완
				return resultPay(user_no, pay_no, pay_type, product_name, amount, point, "TEST"+DaoPayNum, user_adid, user_id);
			}else{
				System.out.println("receiptStatusTest : " + receiptStatusTest);
				return Utils.FAIL;
			}
			 */
					return resultPayV2(user_no, pay_no, pay_type, product_name, amount, point, "TEST" + DaoPayNum, user_adid, user_id);

				} else if (receiptStatus == 0) { //실결제 영수증 확인 완료
					return resultPayV2(user_no, pay_no, pay_type, product_name, amount, point, DaoPayNum, user_adid, user_id);
				} else { //기타 오류
					System.out.println("receiptStatus : " + receiptStatus);
					return Utils.FAIL;
				}
			}else{
				System.out.println("receiptData : null");
				return Utils.FAIL;
			}
		}
	}

	@Override
	public String resultPayMCIOS(int user_no, int pay_no, String pay_type, String product_name, int amount, int point, String DaoPayNum, String user_adid, String user_id, String receipt_data) {
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
			System.err.println("API : resultPayMCIOS -----------------------------------");
			System.err.println("receipt_data: " + receipt_data);
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		} finally {
			if (fos !=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		int pay_result_pay =0;
		int pay_point =0;
		int free_point =0;

		if (product_name.equals(Utils.MC_01_NAME)){
			pay_result_pay = Utils.MC_01_PRICE_IOS;
			pay_point = Utils.MC_01_POINT;
			free_point = 0;
		}else if (product_name.equals(Utils.MC_02_NAME)){
			pay_result_pay = Utils.MC_02_PRICE_IOS;
			pay_point = Utils.MC_02_POINT;
			free_point = 0;
		}else if (product_name.equals(Utils.MC_03_NAME)){
			pay_result_pay = Utils.MC_03_PRICE_IOS;
			pay_point = Utils.MC_03_POINT;
			free_point = 0;
		}else if (product_name.equals(Utils.MC_04_NAME)){
			pay_result_pay = Utils.MC_04_PRICE_IOS;
			pay_point = Utils.MC_04_POINT;
			free_point = 0;
		}else if (product_name.equals(Utils.MC_05_NAME)){
			pay_result_pay = Utils.MC_05_PRICE_IOS;
			pay_point = Utils.MC_05_POINT;
			free_point = 0;
		}else if (product_name.equals(Utils.MC_06_NAME)){
			pay_result_pay = Utils.MC_06_PRICE_IOS;
			pay_point = Utils.MC_06_POINT;
			free_point = 0;
		}else{
			return Utils.FAIL;
		}

//		// s:다퍼줘 이벤트 종료
//		if (!checkEventDay("2020-12-21", "2021-01-31")) {
//			free_point = 0;
//		}
//		// e:다퍼줘 이벤트 종료

		int total_point = pay_point + free_point;

		if (DaoPayNum != null && pay_no > 0) {
			return resultPayMC(user_no, pay_no, pay_type, product_name, pay_result_pay, total_point, DaoPayNum, user_adid, user_id);
		}else {
			if(null != receipt_data) {
				receipt_data = receipt_data.replaceAll(" ", "+"); // + 기호 공백 처리 원복
				JSONObject receiptJson = new JSONObject();
				System.out.println("receipt_data replace : " + receipt_data);

				receiptJson.put("receipt-data", receipt_data);

				String verifyReceiptURL = "https://buy.itunes.apple.com/verifyReceipt";    //상용

				String verifyReceiptResult = getReceiptJson(verifyReceiptURL, receiptJson);
				JSONObject verifyReceiptResultJson = new JSONObject(verifyReceiptResult);
				System.out.println("실 영수 : " + verifyReceiptResult);
				int receiptStatus = verifyReceiptResultJson.getInt("status");
				if (receiptStatus == 21007) { //테스트 영수증
			/*
			String verifyReceiptURLTest = "https://sandbox.itunes.apple.com/verifyReceipt"; //테스트
			String verifyReceiptResultTest = getReceiptJson(verifyReceiptURLTest, receiptJson);
			System.out.println("테스트 영수 : " + verifyReceiptResultTest);
			JSONObject verifyReceiptResultJsonTest = new JSONObject(verifyReceiptResultTest);

			int receiptStatusTest = verifyReceiptResultJsonTest.getInt("status");
			if(receiptStatusTest == 0){ //테스트 영수증 확인 완
				return resultPay(user_no, pay_no, pay_type, product_name, amount, point, "TEST"+DaoPayNum, user_adid, user_id);
			}else{
				System.out.println("receiptStatusTest : " + receiptStatusTest);
				return Utils.FAIL;
			}
			 */
					return resultPayMC(user_no, pay_no, pay_type, product_name, amount, point, "TEST" + DaoPayNum, user_adid, user_id);

				} else if (receiptStatus == 0) { //실결제 영수증 확인 완료
					return resultPayMC(user_no, pay_no, pay_type, product_name, amount, point, DaoPayNum, user_adid, user_id);
				} else { //기타 오류
					System.out.println("receiptStatus : " + receiptStatus);
					return Utils.FAIL;
				}
			}else{
				System.out.println("receiptData : null");
				return Utils.FAIL;
			}
		}
	}

	@Override
	public String resultPayIOSV3(int user_no, int pay_no, String pay_type, String product_name, int amount, int point, String DaoPayNum, String user_adid, String user_id, String receipt_data) {
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



		int pay_result_pay =0;
		int pay_point =0;
		int free_point =0;

		for(int i=0;i<Utils.NMC_NAME.length;i++){
			if(product_name.equals(Utils.NMC_NAME_IOS[i])){
				product_name = Utils.NMC_NAME[i];
				// pay_result_pay =  Utils.NMC_PRICE[i];
				pay_result_pay =  Utils.NMC_PRICE_IOS[i];

				pay_point = Utils.NMC_POINT[i];
				free_point = 0;
			}
		}

		try {
			fos = new FileOutputStream(API_LOG_PATH+"pay_"+dateStr+".log",true); // error.log파일에 출력 준비
			ps=new PrintStream(fos); // err의 출력을 화면이 아닌, error.log파일로 변경
			System.setErr(ps);
			System.err.println("API : resultPayIOSV3 -----------------------------------");
			System.err.println("receipt_data: " + receipt_data);
			System.err.println("product_name: " + product_name);
			System.err.println("pay_result_pay: " + pay_result_pay);
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		} finally {
			if (fos !=null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		if(pay_result_pay == 0){
			return Utils.FAIL;
		}else{
			int total_point = pay_point + free_point;

			if (DaoPayNum != null && pay_no > 0) {
				return resultPayV3(user_no, pay_no, pay_type, product_name, pay_result_pay, total_point, DaoPayNum, user_adid, user_id);
			}else {
				if(null != receipt_data) {
					receipt_data = receipt_data.replaceAll(" ", "+"); // + 기호 공백 처리 원복
					JSONObject receiptJson = new JSONObject();
					System.out.println("receipt_data replace : " + receipt_data);

					receiptJson.put("receipt-data", receipt_data);

					String verifyReceiptURL = "https://buy.itunes.apple.com/verifyReceipt";    //상용

					String verifyReceiptResult = getReceiptJson(verifyReceiptURL, receiptJson);
					JSONObject verifyReceiptResultJson = new JSONObject(verifyReceiptResult);
					System.out.println("실 영수 : " + verifyReceiptResult);
					int receiptStatus = verifyReceiptResultJson.getInt("status");
					if (receiptStatus == 21007) { //테스트 영수증
						return resultPayV3(user_no, pay_no, pay_type, product_name, amount, point, "TEST" + DaoPayNum, user_adid, user_id);
					} else if (receiptStatus == 0) { //실결제 영수증 확인 완료
						return resultPayV3(user_no, pay_no, pay_type, product_name, amount, point, DaoPayNum, user_adid, user_id);
					} else { //기타 오류
						System.out.println("receiptStatus : " + receiptStatus);
						return Utils.FAIL;
					}
				}else{
					System.out.println("receiptData : null");
					return Utils.FAIL;
				}
			}
		}


	}

	@Override
	public UserPointDTO payResultAndroid(int user_no, int pay_no, String pay_token, int pay_code, int amount, int point, String product_name, String user_adid) {


		// 선택 : 토큰에 해당하는 결제 내역이 있는지 구글 서버에 요청해서 결과 확인
		// 1. TB_PAY_RESULT에 pay_no 해당하는 결제 시작 내역이 있는지 확인
		// 있다면 업데이트 하기 (status =1, pay_daoutrx에 결제 토큰 값 넣기)
		// 없으면 insert 해주기 (status =1, pay_daoutrx에 결제 토큰 값 넣기)
		// 2. TB_PAY_LIST에 insert하기
		// 3. 사용자 포인트 넣어주기 (TB_USER, TB_USE_HISTORY)

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
			System.err.println("API : payResultAndroid -----------------------------------");
			System.err.println("[" + dateStr+ " "+timeStr+"] "+ip);  // 사용자 ip
			System.err.println("user_no: " + user_no);
			System.err.println("pay_no: " + pay_no);
			System.err.println("pay_token: " + pay_token);
			System.err.println("pay_code: " + pay_code);
			System.err.println("amount: " + amount);
			System.err.println("point: " + point);
			System.err.println("product_name: " + product_name);
			System.err.println("user_adid: " + user_adid);

		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		}

		UserPointDTO userPointDTO = new UserPointDTO();
		userPointDTO.setResult(Utils.FAIL);

		PayDTO paydto = new PayDTO();
		paydto.setPay_daoutrx(pay_token);
		paydto.setPay_result_type(Utils.IN_APP_ANDROID);

		// 결제가 성공한 경우
		if (pay_code==0){
			// 결제 디비에 사용자가 요청한 토큰이 저장되어 있는지 확인
			try {
				int cnt = payMapper.getPayIdInApp(paydto);
				// 데이터가 저장되어 있는 경우
				// 오류 메세지를 저장한다.
				if (cnt > 0){
					System.err.println("[오류] pay 토큰이 이미 저장되어 있음 ");
					try {
						if(fos != null)
							fos.close();
					} catch (IOException ioException) {
						ioException.printStackTrace();
					}
					// 사용자에게 에러 응답한다.
					return userPointDTO;
				}
			}catch (Exception e){
				// 오류 메세지를 저장한다.
				System.err.println("[오류] pay 토큰이 이미 저장되어 있음 ");
				try {
					if(fos != null)
						fos.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				// 사용자에게 에러 응답한다.
				return userPointDTO;
			}
		}

		// 사용자가 요청한 결제 번호에 대한 결제 데이터가 있는지 확인
		PayResultDTO payresultdto = payMapper.getPayResult(pay_no);
		// pay_no 해당하는 데이터가 TB_PAY_RESULT테이블에 없는 경우
		if (payresultdto ==null) {
			System.err.println("getPayResult select 에러, pay_no에 해당하는 PAY_RESULT 데이터가 없음");
			PayDTO _dto = new PayDTO();

			_dto.setAndroid_inapp_code(pay_code);
			_dto.setUser_no(user_no);
			_dto.setPay_result_pay(amount);
			_dto.setPay_status(0);
			_dto.setPay_result_point(point);
			_dto.setPay_result_product_name(product_name);
			_dto.setPay_daoutrx(pay_token);
			_dto.setPay_result_type(Utils.IN_APP_ANDROID);

			if(product_name.equals("3,000 P")){
				_dto.setPay_point(3000);
				_dto.setFree_point(0);
			}else if(product_name.equals("6,000 P")){
				_dto.setPay_point(6000);
				_dto.setFree_point(0);
			}else if(product_name.equals("18,000 P")){
				_dto.setPay_point(18000);
				_dto.setFree_point(0);
			}else if(product_name.equals("30,000 P")){
				_dto.setPay_point(30000);
				_dto.setFree_point(0);
			}else if(product_name.equals("60,000 P")){
				_dto.setPay_point(60000);
				_dto.setFree_point(0);
			}else if(product_name.equals("120,000 P")){
				_dto.setPay_point(120000);
				_dto.setFree_point(0);
			}

			int _result = payMapper.insertPayInApp(_dto);

			// 결제에 실패한 경우 실패 코드를 사용자에게 응답한다.
			if (pay_code !=Utils.ANDROID_INAPP_BILLING_OK){
				System.err.println("인앱 결제 코드가 성공이 아님 (1 -> 사용자가 취소함, 6 -> 결제 에러 발생 )");
				try {
					if(fos != null)
						fos.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				return userPointDTO;
			}
		}

		PayDTO dto = new PayDTO();
		dto.setUser_no(user_no);
		dto.setPay_status(1);
		dto.setPay_result_type(Utils.IN_APP_ANDROID);
		dto.setNo(pay_no);
		dto.setPay_daoutrx(pay_token);
		dto.setAndroid_inapp_code(pay_code);

		// pay_result 업데이트 결과 성공(1) 또는 실패(0) 값
		int resultUpdate = 0;
		// 이벤트 기간 무료 포인트
		int event_free_point = 0;
		// 상품에 포함된 기본 무료포인트
		int free_point = 0;

		int pay_point = payresultdto.getPay_point();
		free_point = payresultdto.getFree_point();

		int no = payresultdto.getNo();
		// 이벤트 기간인 경우
		String startDayStr = "2020-11-12";
		String endDayStr = "2020-12-11";
		if (checkEventDay(startDayStr, endDayStr)) {

			// (모바일, 신용카드, iOS 결제) + 무통장 전체 포함한 결제 횟수 가져오기
			int payCount = payMapper.getPayListCount(user_no);

			// 첫 결제가 아닌 경우
			if (payCount > 0){
				// PAY_RESULT(모바일, 신용카드, iOS 결제) 테이블 업데이트(결제 상태 등)
				resultUpdate = payMapper.updatePayStatusInApp(dto);
				if (resultUpdate == 0) System.err.println("updatePayStatus 쿼리 실행 에러");

				// 첫 결제인 경우
			}else{

				if (product_name.equals(Utils.PD_0001_NAME)){
					event_free_point = Utils.PD_0001_EVENT_POINT;
				}else if (product_name.equals(Utils.PD_0002_NAME)){
					event_free_point = Utils.PD_0002_EVENT_POINT;
				}else if (product_name.equals(Utils.PD_0003_NAME)){
					event_free_point = Utils.PD_0003_EVENT_POINT;
				}else if (product_name.equals(Utils.PD_0004_NAME)){
					event_free_point = Utils.PD_0004_EVENT_POINT;
				}else if (product_name.equals(Utils.PD_0005_NAME)){
					event_free_point = Utils.PD_0005_EVENT_POINT;
				}else if (product_name.equals(Utils.PD_0006_NAME)){
					event_free_point = Utils.PD_0006_EVENT_POINT;
				}


				// 이벤트 포인트도 free point 컬럼에 추가하기
				dto.setFree_point(event_free_point);

				// "" 값 또는 null인 경우
				if (TextUtils.isEmpty(user_adid)){
					resultUpdate = payMapper.updatePayEventStatusInApp(dto);
				}else{
					resultUpdate = payMapper.updatePayEventStatusInApp(dto);
				}

				if (resultUpdate == 0) System.err.println("updatePayEventStatus 쿼리 실행 에러");
			}

			// 이벤트가 아닌 경우
		}else{

			// PAY_RESULT(모바일, 신용카드, iOS 결제) 테이블 업데이트(결제 상태 등)
			resultUpdate = payMapper.updatePayStatusInApp(dto);
			if (resultUpdate == 0) System.err.println("updatePayStatus 쿼리 실행 에러");
		}

		// pay_result 업데이트에 성공한 경우
		if(resultUpdate == 1) {

			// 결제에 실패한 경우 실패 코드를 사용자에게 응답한다.
			if (pay_code !=Utils.ANDROID_INAPP_BILLING_OK){
				System.err.println("인앱 결제 코드가 성공이 아님 (1 -> 사용자가 취소함, 6 -> 결제 에러 발생 )");
				try {
					if(fos != null)
						fos.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				return userPointDTO;
			}

			// 사용자에게 포인트를 넣어준다
			UserPointDTO pointdto = userMapper.getMyPoint(user_no);
			if (pointdto == null) System.err.println("getMyPoint 쿼리 실행 에러");

			UserPointDTO pDto = new UserPointDTO();
			pDto.setNo(user_no);
//			pDto.setUser_point(getMyPoint + point);
			pDto.setUser_pay_point(pointdto.getUser_pay_point() + pay_point);
			// 사용자 현재 포인트 + 결제 상품이 가진 기본 무료포인트 + 이벤트 무료 포인트
			pDto.setUser_free_point(pointdto.getUser_free_point() + free_point + event_free_point);

			int resultPoint = userMapper.setPoint(pDto);
			if (resultPoint == 0) System.err.println("setPoint 쿼리 실행 에러");

			// 사용자 포인트 추가에 성공한 경우
			if(resultPoint == 1) {
				UserInfoDTO udto = userMapper.getUserInfo(user_no);
				if (udto == null) System.err.println("getUserInfo 쿼리 실행 에러");

				String userServiceType = udto.getUser_service_type();
				String textHello = "안녕하세요! Let’s All night!\n올캠입니다.\n";
				if("onelive".equals(userServiceType)) {
					textHello = "안녕하세요! 원라이브입니다.\n";
				}else if("wowcam".equals(userServiceType)) {
					textHello = "안녕하세요! 와우캠입니다.\n";
				}else if("toptalk".equals(userServiceType)) {
					textHello = "안녕하세요! 탑톡입니다.\n";
				}

				// PAY_LIST에 결제 내역 추가함
				AddPayListDTO payListDto = new AddPayListDTO();
				payListDto.setUser_no(user_no);
				payListDto.setResult_no(pay_no);
				payListDto.setResult_payment_type(Utils.IN_APP_ANDROID);
				payListDto.setResult_product_name(product_name);
				payListDto.setResult_point(point);
				payListDto.setResult_pay(amount);
				payListDto.setPay_point(pay_point);
				payListDto.setFree_point(free_point+event_free_point);
				payListDto.setUser_service_type(userServiceType);

				int resultInsert = payMapper.insertPayList(payListDto);	//pay_list 등록
				if (resultInsert == 0) System.err.println("insertPayList 쿼리 실행 에러");

				UseHistoryDTO hDto = new UseHistoryDTO();
				hDto.setUser_no(user_no);
				hDto.setUse_etc(payListDto.getNo() + "");
				hDto.setUse_content(product_name + "구매 (" + Utils.IN_APP_ANDROID + ")");
				hDto.setUse_etc_user_no(-1);

				// 포인트 적립을 히스토리에 남기기
				int resultHistory = 0;
				if(pay_point > 0) { //유료포인트
					hDto.setUse_cnt(pay_point);
					hDto.setUse_type(0);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트
					resultHistory = liveCallMapper.insertUseHistory(hDto);
				}
				if(free_point > 0) { //무료포인트
					hDto.setUse_cnt(free_point);
					hDto.setUse_type(2);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트
					resultHistory = liveCallMapper.insertUseHistory(hDto);
				}

				if (event_free_point >0){
					hDto.setUse_cnt(event_free_point);
					hDto.setUse_type(2);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트
					hDto.setUse_content("첫 결제 이벤트 포인트 적립");
					resultHistory = liveCallMapper.insertUseHistory(hDto);
				}
				if (resultHistory == 0) System.err.println("insertUseHistory 쿼리 실행 에러");

				// 채팅 메세지 보내기 + 남기기
				ChatDTO cdto = new ChatDTO();
				cdto.setChat_text(
						textHello + udto.getUser_nick_name() + " 회원님, 포인트 구매해 주셔서 감사합니다.\n" +
								"\n" +
								"현재 무통장으로 충전 시 추가 포인트를 지급해 드리고 있으며, 더 자세한 내용은 충전 탭에서 확인 가능하오니 참고하시기 바랍니다.\n" +
								"\n" +
								"오늘도 행복한 하루 보내시기 바랍니다."
				);
				cdto.setFrom_user_no(1);	// 1 : 관리자
				cdto.setTo_user_no(dto.getUser_no());

				int resultChat = chatMapper.sendChat(cdto);
				if (resultChat == 0) System.err.println("sendChat 쿼리 실행 에러");

				if(!"".equals(user_adid)) {
					String pResult = directConnection("https://partner.allcam.co.kr/APP_acc/app_data.php?TYPE=P&UID=" +user_no
							+ "&MONEY=" + amount
							+ "&ADID=" + user_adid
							+ "&PAYID="+no
							+ "&PAYTYPE=0");

					System.err.println("partner데이터 전달 "+product_name);
				}
				UserInfoDTO _udto = userMapper.getUserInfo(user_no);
				userPointDTO.setResult(Utils.SUCCESS);
				userPointDTO.setUser_point(_udto.getUser_point());

			}
		}

		try {
			if(fos != null)
				fos.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

		return userPointDTO;
	}

	@Override
	public UserPointDTO payResultAndroidV2(int user_no, int pay_no, String pay_id, int pay_code, String product_id,String user_adid) {

		// 선택 : 토큰에 해당하는 결제 내역이 있는지 구글 서버에 요청해서 결과 확인
		// 1. TB_PAY_RESULT에 pay_no 해당하는 결제 시작 내역이 있는지 확인
		// 있다면 업데이트 하기 (status =1, pay_daoutrx에 결제 토큰 값 넣기)
		// 없으면 insert 해주기 (status =1, pay_daoutrx에 결제 토큰 값 넣기)
		// 2. TB_PAY_LIST에 insert하기
		// 3. 사용자 포인트 넣어주기 (TB_USER, TB_USE_HISTORY)

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
			System.err.println("API : payResultAndroidV2 -----------------------------------");
			System.err.println("[" + dateStr+ " "+timeStr+"] "+ip);  // 사용자 ip
			System.err.println("user_no: " + user_no);
			System.err.println("pay_no: " + pay_no);
			System.err.println("pay_id: " + pay_id);
			System.err.println("pay_code: " + pay_code);
			System.err.println("product_id: " + product_id);
			System.err.println("user_adid: " + user_adid);

		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		}

		UserPointDTO userPointDTO = new UserPointDTO();
		userPointDTO.setResult(Utils.FAIL);

		// 결제가 성공이 아닌 경우 결제 코드, 결제 아이디만 수정하고 실패 응답을 한다.
		if (pay_code !=Utils.ANDROID_INAPP_BILLING_OK){
			PayDTO errorPayDto = new PayDTO();
			errorPayDto.setUser_no(user_no);
			errorPayDto.setPay_status(0);
			errorPayDto.setPay_result_type(Utils.IN_APP_ANDROID);
			errorPayDto.setNo(pay_no);
			errorPayDto.setPay_daoutrx(pay_id);
			errorPayDto.setAndroid_inapp_code(pay_code);

			payMapper.updatePayStatusInApp(errorPayDto);
			return userPointDTO;
		}


		PayDTO paydto = new PayDTO();
		paydto.setPay_daoutrx(pay_id);
		paydto.setPay_result_type(Utils.IN_APP_ANDROID);

		// 결제가 성공한 경우
		if (pay_code==0){
			// 결제 디비에 사용자가 요청한 토큰이 저장되어 있는지 확인
			try {
				int cnt = payMapper.getPayIdInApp(paydto);
				// 데이터가 저장되어 있는 경우
				// 오류 메세지를 저장한다.
				if (cnt > 0){
					System.err.println("[오류] pay 토큰이 이미 저장되어 있음 ");
					try {
						if(fos != null)
							fos.close();
					} catch (IOException ioException) {
						ioException.printStackTrace();
					}
					// 사용자에게 에러 응답한다.
					return userPointDTO;
				}
			}catch (Exception e){
				// 오류 메세지를 저장한다.
				System.err.println("[오류] pay 토큰이 이미 저장되어 있음 ");
				try {
					if(fos != null)
						fos.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				// 사용자에게 에러 응답한다.
				return userPointDTO;
			}
		}

		int payResultPay = 0;
		int payPoint = 0;
		String productName = "";
		int freePoint = 0;
		if (product_id.equals(Utils.PD_0001)){
			payResultPay = Utils.PD_0001_PRICE;
			productName = Utils.PD_0001_NAME;
			payPoint = Utils.PD_0001_POINT;
			freePoint = Utils.PD_0001_FREE_POINT;
		}else if (product_id.equals(Utils.PD_0002)){
			payResultPay = Utils.PD_0002_PRICE;
			productName = Utils.PD_0002_NAME;
			payPoint = Utils.PD_0002_POINT;
			freePoint = Utils.PD_0002_FREE_POINT;
		}else if (product_id.equals(Utils.PD_0003)){
			payResultPay = Utils.PD_0003_PRICE;
			productName = Utils.PD_0003_NAME;
			payPoint = Utils.PD_0003_POINT;
			freePoint = Utils.PD_0003_FREE_POINT;
		}else if (product_id.equals(Utils.PD_0004)){
			payResultPay = Utils.PD_0004_PRICE;
			productName = Utils.PD_0004_NAME;
			payPoint = Utils.PD_0004_POINT;
			freePoint = Utils.PD_0004_FREE_POINT;
		}else if (product_id.equals(Utils.PD_0005)){
			payResultPay = Utils.PD_0005_PRICE;
			productName = Utils.PD_0005_NAME;
			payPoint = Utils.PD_0005_POINT;
			freePoint = Utils.PD_0005_FREE_POINT;
		}else if (product_id.equals(Utils.PD_0006)){
			payResultPay = Utils.PD_0006_PRICE;
			productName = Utils.PD_0006_NAME;
			payPoint = Utils.PD_0006_POINT;
			freePoint = Utils.PD_0006_FREE_POINT;
		}

		// s:다퍼줘 이벤트 종료
		if (!checkEventDay("2020-12-21", "2021-01-31")) {
			freePoint = 0;
		}
		// e:다퍼줘 이벤트 종료

		// 사용자가 요청한 결제 번호에 대한 결제 데이터가 있는지 확인
		PayResultDTO payresultdto = payMapper.getPayResult(pay_no);
		// pay_no 해당하는 데이터가 TB_PAY_RESULT테이블에 없는 경우
		if (payresultdto ==null) {
			System.err.println("getPayResult select 에러, pay_no에 해당하는 PAY_RESULT 데이터가 없음");
			PayDTO _dto = new PayDTO();

			_dto.setAndroid_inapp_code(pay_code);
			_dto.setUser_no(user_no);
			_dto.setPay_result_pay(payResultPay);
			_dto.setPay_status(0);
			_dto.setPay_result_point(payPoint+freePoint);
			_dto.setPay_result_product_name(productName);
			_dto.setPay_daoutrx(pay_id);
			_dto.setPay_result_type(Utils.IN_APP_ANDROID);
			_dto.setPay_point(payPoint);
			_dto.setFree_point(freePoint);

			int _result = payMapper.insertPayInApp(_dto);

			// 결제에 실패한 경우 실패 코드를 사용자에게 응답한다.
			if (pay_code !=Utils.ANDROID_INAPP_BILLING_OK){
				System.err.println("인앱 결제 코드가 성공이 아님 (1 -> 사용자가 취소함, 6 -> 결제 에러 발생 )");
				try {
					if(fos != null)
						fos.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				return userPointDTO;
			}
		}

		PayDTO dto = new PayDTO();
		dto.setUser_no(user_no);
		dto.setPay_status(1);
		dto.setPay_result_type(Utils.IN_APP_ANDROID);
		dto.setNo(pay_no);
		dto.setPay_daoutrx(pay_id);
		dto.setAndroid_inapp_code(pay_code);

		// pay_result 업데이트 결과 성공(1) 또는 실패(0) 값
		int resultUpdate = 0;
		// 이벤트 기간 무료 포인트
		int event_free_point = 0;
		// 상품에 포함된 기본 무료포인트
		int free_point = 0;

		int pay_point = payresultdto.getPay_point();
		free_point = payresultdto.getFree_point();

		int no = payresultdto.getNo();
		// 첫결제 이벤트 기간인 경우
		if (checkEventDay(Utils.EVENT_DATE_START, Utils.EVENT_DATE_END)) {

			// (모바일, 신용카드, iOS 결제) + 무통장 전체 포함한 결제 횟수 가져오기
			int payCount = payMapper.getPayListCount(user_no);

			// 첫 결제가 아닌 경우
			if (payCount > 0){
				// PAY_RESULT(모바일, 신용카드, iOS 결제) 테이블 업데이트(결제 상태 등)
				resultUpdate = payMapper.updatePayStatusInApp(dto);
				if (resultUpdate == 0) System.err.println("updatePayStatus 쿼리 실행 에러");

				// 첫 결제인 경우
			}else{
				if (productName.equals(Utils.PD_0001_NAME)){
					event_free_point = Utils.PD_0001_EVENT_POINT;
				}else if (productName.equals(Utils.PD_0002_NAME)){
					event_free_point = Utils.PD_0002_EVENT_POINT;
				}else if (productName.equals(Utils.PD_0003_NAME)){
					event_free_point = Utils.PD_0003_EVENT_POINT;
				}else if (productName.equals(Utils.PD_0004_NAME)){
					event_free_point = Utils.PD_0004_EVENT_POINT;
				}else if (productName.equals(Utils.PD_0005_NAME)){
					event_free_point = Utils.PD_0005_EVENT_POINT;
				}else if (productName.equals(Utils.PD_0006_NAME)){
					event_free_point = Utils.PD_0006_EVENT_POINT;
				}

				// 이벤트 포인트도 free point 컬럼에 추가하기
				dto.setFree_point(event_free_point);

				resultUpdate = payMapper.updatePayEventStatusInApp(dto);

				if (resultUpdate == 0) System.err.println("updatePayEventStatus 쿼리 실행 에러");
			}

			// 이벤트가 아닌 경우
		}else{

			// PAY_RESULT 테이블 업데이트(결제 상태 등)
			resultUpdate = payMapper.updatePayStatusInApp(dto);
			if (resultUpdate == 0) System.err.println("updatePayStatus 쿼리 실행 에러");
		}

		// pay_result 업데이트에 성공한 경우
		if(resultUpdate == 1) {

			// 결제에 실패한 경우 실패 코드를 사용자에게 응답한다.
			if (pay_code !=Utils.ANDROID_INAPP_BILLING_OK){
				System.err.println("인앱 결제 코드가 성공이 아님 (1 -> 사용자가 취소함, 6 -> 결제 에러 발생 )");
				try {
					if(fos != null)
						fos.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				return userPointDTO;
			}

			// 사용자에게 포인트를 넣어준다
			UserPointDTO pointdto = userMapper.getMyPoint(user_no);
			if (pointdto == null) System.err.println("getMyPoint 쿼리 실행 에러");

			UserPointDTO pDto = new UserPointDTO();
			pDto.setNo(user_no);
//			pDto.setUser_point(getMyPoint + point);
			pDto.setUser_pay_point(pointdto.getUser_pay_point() + pay_point);
			// 사용자 현재 포인트 + 결제 상품이 가진 기본 무료포인트 + 이벤트 무료 포인트
			pDto.setUser_free_point(pointdto.getUser_free_point() + free_point + event_free_point);

			int resultPoint = userMapper.setPoint(pDto);
			if (resultPoint == 0) System.err.println("setPoint 쿼리 실행 에러");

			// 사용자 포인트 추가에 성공한 경우
			if(resultPoint == 1) {
				UserInfoDTO udto = userMapper.getUserInfo(user_no);
				if (udto == null) System.err.println("getUserInfo 쿼리 실행 에러");

				String userServiceType = udto.getUser_service_type();
				String textHello = "안녕하세요! Let’s All night!\n올캠입니다.\n";
				if("onelive".equals(userServiceType)) {
					textHello = "안녕하세요! 원라이브입니다.\n";
				}else if("wowcam".equals(userServiceType)) {
					textHello = "안녕하세요! 와우캠입니다.\n";
				}else if("toptalk".equals(userServiceType)) {
					textHello = "안녕하세요! 탑톡입니다.\n";
				}

				// PAY_LIST에 결제 내역 추가함
				AddPayListDTO payListDto = new AddPayListDTO();
				payListDto.setUser_no(user_no);
				payListDto.setResult_no(pay_no);
				payListDto.setResult_payment_type(Utils.IN_APP_ANDROID);
				payListDto.setResult_product_name(productName);
				payListDto.setResult_point(payPoint+freePoint+event_free_point);
				payListDto.setResult_pay(payResultPay);
				payListDto.setPay_point(pay_point);
				payListDto.setFree_point(free_point+event_free_point);
				payListDto.setUser_service_type(userServiceType);

				int resultInsert = payMapper.insertPayList(payListDto);	//pay_list 등록
				if (resultInsert == 0) System.err.println("insertPayList 쿼리 실행 에러");

				UseHistoryDTO hDto = new UseHistoryDTO();
				hDto.setUser_no(user_no);
				hDto.setUse_etc(payListDto.getNo() + "");
				hDto.setUse_content(productName + "구매 (인앱 결제)");
				hDto.setUse_etc_user_no(-1);

				// 포인트 적립을 히스토리에 남기기
				int resultHistory = 0;
				if(pay_point > 0) { //유료포인트
					hDto.setUse_cnt(pay_point);
					hDto.setUse_type(0);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트
					resultHistory = liveCallMapper.insertUseHistory(hDto);
				}
				if(free_point > 0) { //무료포인트
					hDto.setUse_cnt(free_point);
					hDto.setUse_type(2);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트
					resultHistory = liveCallMapper.insertUseHistory(hDto);
				}

				if (event_free_point >0){
					hDto.setUse_cnt(event_free_point);
					hDto.setUse_type(2);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트
					hDto.setUse_content("첫 결제 이벤트 포인트 적립");
					resultHistory = liveCallMapper.insertUseHistory(hDto);
				}
				if (resultHistory == 0) System.err.println("insertUseHistory 쿼리 실행 에러");

				// 채팅 메세지 보내기 + 남기기
				ChatDTO cdto = new ChatDTO();
				cdto.setChat_text(
						textHello + udto.getUser_nick_name() + " 회원님, 포인트 구매해 주셔서 감사합니다.\n" +
								"\n" +
								"오늘도 행복한 하루 보내시기 바랍니다."
				);
				cdto.setFrom_user_no(1);	// 1 : 관리자
				cdto.setTo_user_no(dto.getUser_no());

				int resultChat = chatMapper.sendChat(cdto);
				if (resultChat == 0) System.err.println("sendChat 쿼리 실행 에러");

				if(!"".equals(user_adid)) {
					String pResult = directConnection("https://partner.allcam.co.kr/APP_acc/app_data.php?TYPE=P&UID=" +user_no
							+ "&MONEY=" + payResultPay
							+ "&ADID=" + user_adid
							+ "&PAYID="+no
							+ "&PAYTYPE=0");

					System.err.println("partner데이터 전달, 결제금액 : "+payResultPay);
				}
				UserInfoDTO _udto = userMapper.getUserInfo(user_no);
				userPointDTO.setResult(Utils.SUCCESS);
				userPointDTO.setUser_point(_udto.getUser_point());

			}
		}

		try {
			if(fos != null)
				fos.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

		return userPointDTO;
	}

	@Override
	public UserPointDTO payResultMCAndroid(int user_no, int pay_no, String pay_id, int pay_code, String product_id,String user_adid) {

		// 선택 : 토큰에 해당하는 결제 내역이 있는지 구글 서버에 요청해서 결과 확인
		// 1. TB_PAY_RESULT에 pay_no 해당하는 결제 시작 내역이 있는지 확인
		// 있다면 업데이트 하기 (status =1, pay_daoutrx에 결제 토큰 값 넣기)
		// 없으면 insert 해주기 (status =1, pay_daoutrx에 결제 토큰 값 넣기)
		// 2. TB_PAY_LIST에 insert하기
		// 3. 사용자 포인트 넣어주기 (TB_USER, TB_USE_HISTORY)

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
			System.err.println("API : payResultMCAndroid -----------------------------------");
			System.err.println("[" + dateStr+ " "+timeStr+"] "+ip);  // 사용자 ip
			System.err.println("user_no: " + user_no);
			System.err.println("pay_no: " + pay_no);
			System.err.println("pay_id: " + pay_id);
			System.err.println("pay_code: " + pay_code);
			System.err.println("product_id: " + product_id);
			System.err.println("user_adid: " + user_adid);

		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		}

		UserPointDTO userPointDTO = new UserPointDTO();
		userPointDTO.setResult(Utils.FAIL);

		// 결제가 성공이 아닌 경우 결제 코드, 결제 아이디만 수정하고 실패 응답을 한다.
		if (pay_code !=Utils.ANDROID_INAPP_BILLING_OK){
			PayDTO errorPayDto = new PayDTO();
			errorPayDto.setUser_no(user_no);
			errorPayDto.setPay_status(0);
			errorPayDto.setPay_result_type(Utils.IN_APP_ANDROID);
			errorPayDto.setNo(pay_no);
			errorPayDto.setPay_daoutrx(pay_id);
			errorPayDto.setAndroid_inapp_code(pay_code);

			payMapper.updatePayStatusInApp(errorPayDto);
			return userPointDTO;
		}


		PayDTO paydto = new PayDTO();
		paydto.setPay_daoutrx(pay_id);
		paydto.setPay_result_type(Utils.IN_APP_ANDROID);

		// 결제가 성공한 경우
		if (pay_code==0){
			// 결제 디비에 사용자가 요청한 토큰이 저장되어 있는지 확인
			try {
				int cnt = payMapper.getPayIdInApp(paydto);
				// 데이터가 저장되어 있는 경우
				// 오류 메세지를 저장한다.
				if (cnt > 0){
					System.err.println("[오류] pay 토큰이 이미 저장되어 있음 ");
					try {
						if(fos != null)
							fos.close();
					} catch (IOException ioException) {
						ioException.printStackTrace();
					}
					// 사용자에게 에러 응답한다.
					return userPointDTO;
				}
			}catch (Exception e){
				// 오류 메세지를 저장한다.
				System.err.println("[오류] pay 토큰이 이미 저장되어 있음 ");
				try {
					if(fos != null)
						fos.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				// 사용자에게 에러 응답한다.
				return userPointDTO;
			}
		}

		int payResultPay = 0;
		int payPoint = 0;
		String productName = "";
		int freePoint = 0;
		if (product_id.equals(Utils.MC_01)){
			payResultPay = Utils.MC_01_PRICE;
			productName = Utils.MC_01_NAME;
			payPoint = Utils.MC_01_POINT;
			freePoint = 0;
		}else if (product_id.equals(Utils.MC_02)){
			payResultPay = Utils.MC_02_PRICE;
			productName = Utils.MC_02_NAME;
			payPoint = Utils.MC_02_POINT;
			freePoint = 0;
		}else if (product_id.equals(Utils.MC_03)){
			payResultPay = Utils.MC_03_PRICE;
			productName = Utils.MC_03_NAME;
			payPoint = Utils.MC_03_POINT;
			freePoint = 0;
		}else if (product_id.equals(Utils.MC_04)){
			payResultPay = Utils.MC_04_PRICE;
			productName = Utils.MC_04_NAME;
			payPoint = Utils.MC_04_POINT;
			freePoint = 0;
		}else if (product_id.equals(Utils.MC_05)){
			payResultPay = Utils.MC_05_PRICE;
			productName = Utils.MC_05_NAME;
			payPoint = Utils.MC_05_POINT;
			freePoint = 0;
		}else if (product_id.equals(Utils.MC_06)){
			payResultPay = Utils.MC_06_PRICE;
			productName = Utils.MC_06_NAME;
			payPoint = Utils.MC_06_POINT;
			freePoint = 0;
		}

//		// s:다퍼줘 이벤트 종료
//		if (!checkEventDay("2020-12-21", "2021-01-31")) {
//			freePoint = 0;
//		}
//		// e:다퍼줘 이벤트 종료

		// 사용자가 요청한 결제 번호에 대한 결제 데이터가 있는지 확인
		PayResultDTO payresultdto = payMapper.getPayResult(pay_no);
		// pay_no 해당하는 데이터가 TB_PAY_RESULT테이블에 없는 경우
		if (payresultdto ==null) {
			System.err.println("getPayResult select 에러, pay_no에 해당하는 PAY_RESULT 데이터가 없음");
			PayDTO _dto = new PayDTO();

			_dto.setAndroid_inapp_code(pay_code);
			_dto.setUser_no(user_no);
			_dto.setPay_result_pay(payResultPay);
			_dto.setPay_status(0);
			_dto.setPay_result_point(payPoint+freePoint);
			_dto.setPay_result_product_name(productName);
			_dto.setPay_daoutrx(pay_id);
			_dto.setPay_result_type(Utils.IN_APP_ANDROID);
			_dto.setPay_point(payPoint);
			_dto.setFree_point(freePoint);

			int _result = payMapper.insertPayInApp(_dto);

			// 결제에 실패한 경우 실패 코드를 사용자에게 응답한다.
			if (pay_code !=Utils.ANDROID_INAPP_BILLING_OK){
				System.err.println("인앱 결제 코드가 성공이 아님 (1 -> 사용자가 취소함, 6 -> 결제 에러 발생 )");
				try {
					if(fos != null)
						fos.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				return userPointDTO;
			}
		}

		PayDTO dto = new PayDTO();
		dto.setUser_no(user_no);
		dto.setPay_status(1);
		dto.setPay_result_type(Utils.IN_APP_ANDROID);
		dto.setNo(pay_no);
		dto.setPay_daoutrx(pay_id);
		dto.setAndroid_inapp_code(pay_code);

		// pay_result 업데이트 결과 성공(1) 또는 실패(0) 값
		int resultUpdate = 0;
		// 이벤트 기간 무료 포인트
		int event_free_point = 0;
		// 상품에 포함된 기본 무료포인트
		int free_point = 0;

		int pay_point = payresultdto.getPay_point();
		free_point = payresultdto.getFree_point();

		int no = payresultdto.getNo();

		// PAY_RESULT 테이블 업데이트(결제 상태 등)
		resultUpdate = payMapper.updatePayStatusInApp(dto);

		// pay_result 업데이트에 성공한 경우
		if(resultUpdate == 1) {

			// 결제에 실패한 경우 실패 코드를 사용자에게 응답한다.
			if (pay_code !=Utils.ANDROID_INAPP_BILLING_OK){
				System.err.println("인앱 결제 코드가 성공이 아님 (1 -> 사용자가 취소함, 6 -> 결제 에러 발생 )");
				try {
					if(fos != null)
						fos.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				return userPointDTO;
			}

			// 사용자에게 포인트를 넣어준다
			UserPointDTO pointdto = userMapper.getMyPoint(user_no);
			if (pointdto == null) System.err.println("getMyPoint 쿼리 실행 에러");

			UserPointDTO pDto = new UserPointDTO();
			pDto.setNo(user_no);
//			pDto.setUser_point(getMyPoint + point);
			pDto.setUser_pay_point(pointdto.getUser_pay_point() + pay_point);
			// 사용자 현재 포인트 + 결제 상품이 가진 기본 무료포인트 + 이벤트 무료 포인트
			pDto.setUser_free_point(pointdto.getUser_free_point() + free_point + event_free_point);

			int resultPoint = userMapper.setPoint(pDto);
			if (resultPoint == 0) System.err.println("setPoint 쿼리 실행 에러");

			// 사용자 포인트 추가에 성공한 경우
			if(resultPoint == 1) {
				UserInfoDTO udto = userMapper.getUserInfo(user_no);
				if (udto == null) System.err.println("getUserInfo 쿼리 실행 에러");

				String userServiceType = udto.getUser_service_type();
				String textHello = "안녕하세요! Let’s All night!\n올캠입니다.\n";
				if("onelive".equals(userServiceType)) {
					textHello = "안녕하세요! 원라이브입니다.\n";
				}else if("wowcam".equals(userServiceType)) {
					textHello = "안녕하세요! 와우캠입니다.\n";
				}else if("toptalk".equals(userServiceType)) {
					textHello = "안녕하세요! 탑톡입니다.\n";
				}else if("meincam".equals(userServiceType)) {
					textHello = "안녕하세요! 미인캠입니다.\n";
				}

				// PAY_LIST에 결제 내역 추가함
				AddPayListDTO payListDto = new AddPayListDTO();
				payListDto.setUser_no(user_no);
				payListDto.setResult_no(pay_no);
				payListDto.setResult_payment_type(Utils.IN_APP_ANDROID);
				payListDto.setResult_product_name(productName);
				payListDto.setResult_point(payPoint+freePoint+event_free_point);
				payListDto.setResult_pay(payResultPay);
				payListDto.setPay_point(pay_point);
				payListDto.setFree_point(free_point+event_free_point);
				payListDto.setUser_service_type(userServiceType);

				int resultInsert = payMapper.insertPayList(payListDto);	//pay_list 등록
				if (resultInsert == 0) System.err.println("insertPayList 쿼리 실행 에러");

				UseHistoryDTO hDto = new UseHistoryDTO();
				hDto.setUser_no(user_no);
				hDto.setUse_etc(payListDto.getNo() + "");
				hDto.setUse_content(productName + "구매 (인앱 결제)");
				hDto.setUse_etc_user_no(-1);

				// 포인트 적립을 히스토리에 남기기
				int resultHistory = 0;
				if(pay_point > 0) { //유료포인트
					hDto.setUse_cnt(pay_point);
					hDto.setUse_type(0);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트
					resultHistory = liveCallMapper.insertUseHistory(hDto);
				}
				if(free_point > 0) { //무료포인트
					hDto.setUse_cnt(free_point);
					hDto.setUse_type(2);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트
					resultHistory = liveCallMapper.insertUseHistory(hDto);
				}

				if (event_free_point >0){
					hDto.setUse_cnt(event_free_point);
					hDto.setUse_type(2);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트
					hDto.setUse_content("첫 결제 이벤트 포인트 적립");
					resultHistory = liveCallMapper.insertUseHistory(hDto);
				}
				if (resultHistory == 0) System.err.println("insertUseHistory 쿼리 실행 에러");

				// 채팅 메세지 보내기 + 남기기
				ChatDTO cdto = new ChatDTO();
				cdto.setChat_text(
						textHello + udto.getUser_nick_name() + " 회원님, 포인트 구매해 주셔서 감사합니다.\n" +
								"\n" +
								"오늘도 행복한 하루 보내시기 바랍니다."
				);
				cdto.setFrom_user_no(1);	// 1 : 관리자
				cdto.setTo_user_no(dto.getUser_no());

				int resultChat = chatMapper.sendChat(cdto);
				if (resultChat == 0) System.err.println("sendChat 쿼리 실행 에러");

				if(!"".equals(user_adid)) {
					String pResult = directConnection("https://partner.allcam.co.kr/APP_acc/app_data.php?TYPE=P&UID=" +user_no
							+ "&MONEY=" + payResultPay
							+ "&ADID=" + user_adid
							+ "&PAYID="+no
							+ "&PAYTYPE=0");

					System.err.println("partner데이터 전달, 결제금액 : "+payResultPay);
				}
				UserInfoDTO _udto = userMapper.getUserInfo(user_no);
				userPointDTO.setResult(Utils.SUCCESS);
				userPointDTO.setUser_point(_udto.getUser_point());

			}
		}else{
			System.err.println("updatePayStatus 쿼리 실행 에러");
		}

		try {
			if(fos != null)
				fos.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

		return userPointDTO;
	}

	@Override
	public UserPointDTO payResultAndroidV3(int user_no, int pay_no, String pay_id, int pay_code, String product_id,String user_adid) {

		// 선택 : 토큰에 해당하는 결제 내역이 있는지 구글 서버에 요청해서 결과 확인
		// 1. TB_PAY_RESULT에 pay_no 해당하는 결제 시작 내역이 있는지 확인
		// 있다면 업데이트 하기 (status =1, pay_daoutrx에 결제 토큰 값 넣기)
		// 없으면 insert 해주기 (status =1, pay_daoutrx에 결제 토큰 값 넣기)
		// 2. TB_PAY_LIST에 insert하기
		// 3. 사용자 포인트 넣어주기 (TB_USER, TB_USE_HISTORY)

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
			System.err.println("API : payResultAndroidV3 -----------------------------------");
			System.err.println("[" + dateStr+ " "+timeStr+"] "+ip);  // 사용자 ip
			System.err.println("user_no: " + user_no);
			System.err.println("pay_no: " + pay_no);
			System.err.println("pay_id: " + pay_id);
			System.err.println("pay_code: " + pay_code);
			System.err.println("product_id: " + product_id);
			System.err.println("user_adid: " + user_adid);

		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		}

		UserPointDTO userPointDTO = new UserPointDTO();
		userPointDTO.setResult(Utils.FAIL);

		// 결제가 성공이 아닌 경우 결제 코드, 결제 아이디만 수정하고 실패 응답을 한다.
		if (pay_code !=Utils.ANDROID_INAPP_BILLING_OK){
			PayDTO errorPayDto = new PayDTO();
			errorPayDto.setUser_no(user_no);
			errorPayDto.setPay_status(0);
			errorPayDto.setPay_result_type(Utils.IN_APP_ANDROID);
			errorPayDto.setNo(pay_no);
			errorPayDto.setPay_daoutrx(pay_id);
			errorPayDto.setAndroid_inapp_code(pay_code);

			payMapper.updatePayStatusInApp(errorPayDto);
			return userPointDTO;
		}


		PayDTO paydto = new PayDTO();
		paydto.setPay_daoutrx(pay_id);
		paydto.setPay_result_type(Utils.IN_APP_ANDROID);

		// 결제가 성공한 경우
		if (pay_code==0){
			// 결제 디비에 사용자가 요청한 토큰이 저장되어 있는지 확인
			try {
				int cnt = payMapper.getPayIdInApp(paydto);
				// 데이터가 저장되어 있는 경우
				// 오류 메세지를 저장한다.
				if (cnt > 0){
					System.err.println("[오류] pay 토큰이 이미 저장되어 있음 ");
					try {
						if(fos != null)
							fos.close();
					} catch (IOException ioException) {
						ioException.printStackTrace();
					}
					// 사용자에게 에러 응답한다.
					return userPointDTO;
				}
			}catch (Exception e){
				// 오류 메세지를 저장한다.
				System.err.println("[오류] pay 토큰이 이미 저장되어 있음 ");
				try {
					if(fos != null)
						fos.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				// 사용자에게 에러 응답한다.
				return userPointDTO;
			}
		}

		int payResultPay = 0;
		int payPoint = 0;
		String productName = "";
		int freePoint = 0;


		for(int i=0;i<Utils.NMC_ID.length;i++){
			if(product_id.equals(Utils.NMC_ID[i])){
				productName = Utils.NMC_NAME[i];
				payResultPay =  Utils.NMC_PRICE[i];
				payPoint = Utils.NMC_POINT[i];
				freePoint = 0;
			}
		}


//		// s:다퍼줘 이벤트 종료
//		if (!checkEventDay("2020-12-21", "2021-01-31")) {
//			freePoint = 0;
//		}
//		// e:다퍼줘 이벤트 종료

		// 사용자가 요청한 결제 번호에 대한 결제 데이터가 있는지 확인
		PayResultDTO payresultdto = payMapper.getPayResult(pay_no);
		// pay_no 해당하는 데이터가 TB_PAY_RESULT테이블에 없는 경우
		if (payresultdto ==null) {
			System.err.println("getPayResult select 에러, pay_no에 해당하는 PAY_RESULT 데이터가 없음");
			PayDTO _dto = new PayDTO();

			_dto.setAndroid_inapp_code(pay_code);
			_dto.setUser_no(user_no);
			_dto.setPay_result_pay(payResultPay);
			_dto.setPay_status(0);
			_dto.setPay_result_point(payPoint+freePoint);
			_dto.setPay_result_product_name(productName);
			_dto.setPay_daoutrx(pay_id);
			_dto.setPay_result_type(Utils.IN_APP_ANDROID);
			_dto.setPay_point(payPoint);
			_dto.setFree_point(freePoint);

			int _result = payMapper.insertPayInApp(_dto);

			// 결제에 실패한 경우 실패 코드를 사용자에게 응답한다.
			if (pay_code !=Utils.ANDROID_INAPP_BILLING_OK){
				System.err.println("인앱 결제 코드가 성공이 아님 (1 -> 사용자가 취소함, 6 -> 결제 에러 발생 )");
				try {
					if(fos != null)
						fos.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				return userPointDTO;
			}
		}

		PayDTO dto = new PayDTO();
		dto.setUser_no(user_no);
		dto.setPay_status(1);
		dto.setPay_result_type(Utils.IN_APP_ANDROID);
		dto.setNo(pay_no);
		dto.setPay_daoutrx(pay_id);
		dto.setAndroid_inapp_code(pay_code);

		// pay_result 업데이트 결과 성공(1) 또는 실패(0) 값
		int resultUpdate = 0;
		// 이벤트 기간 무료 포인트
		int event_free_point = 0;
		// 상품에 포함된 기본 무료포인트
		int free_point = 0;

		int pay_point = payresultdto.getPay_point();
		free_point = payresultdto.getFree_point();

		int no = payresultdto.getNo();

		// PAY_RESULT 테이블 업데이트(결제 상태 등)
		resultUpdate = payMapper.updatePayStatusInApp(dto);

		// pay_result 업데이트에 성공한 경우
		if(resultUpdate == 1) {

			// 결제에 실패한 경우 실패 코드를 사용자에게 응답한다.
			if (pay_code !=Utils.ANDROID_INAPP_BILLING_OK){
				System.err.println("인앱 결제 코드가 성공이 아님 (1 -> 사용자가 취소함, 6 -> 결제 에러 발생 )");
				try {
					if(fos != null)
						fos.close();
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
				return userPointDTO;
			}

			// 사용자에게 포인트를 넣어준다
			UserPointDTO pointdto = userMapper.getMyPoint(user_no);
			if (pointdto == null) System.err.println("getMyPoint 쿼리 실행 에러");

			UserPointDTO pDto = new UserPointDTO();
			pDto.setNo(user_no);
//			pDto.setUser_point(getMyPoint + point);
			pDto.setUser_pay_point(pointdto.getUser_pay_point() + pay_point);
			// 사용자 현재 포인트 + 결제 상품이 가진 기본 무료포인트 + 이벤트 무료 포인트
			pDto.setUser_free_point(pointdto.getUser_free_point() + free_point + event_free_point);

			int resultPoint = userMapper.setPoint(pDto);
			if (resultPoint == 0) System.err.println("setPoint 쿼리 실행 에러");

			// 사용자 포인트 추가에 성공한 경우
			if(resultPoint == 1) {
				UserInfoDTO udto = userMapper.getUserInfo(user_no);
				if (udto == null) System.err.println("getUserInfo 쿼리 실행 에러");

				String userServiceType = udto.getUser_service_type();
				String textHello = "안녕하세요! Let’s All night!\n올캠입니다.\n";
				if("onelive".equals(userServiceType)) {
					textHello = "안녕하세요! 원라이브입니다.\n";
				}else if("wowcam".equals(userServiceType)) {
					textHello = "안녕하세요! 와우캠입니다.\n";
				}else if("toptalk".equals(userServiceType)) {
					textHello = "안녕하세요! 탑톡입니다.\n";
				}else if("meincam".equals(userServiceType)) {
					textHello = "안녕하세요! 미인캠입니다.\n";
				}

				// PAY_LIST에 결제 내역 추가함
				AddPayListDTO payListDto = new AddPayListDTO();
				payListDto.setUser_no(user_no);
				payListDto.setResult_no(pay_no);
				payListDto.setResult_payment_type(Utils.IN_APP_ANDROID);
				payListDto.setResult_product_name(productName);
				payListDto.setResult_point(payPoint+freePoint+event_free_point);
				payListDto.setResult_pay(payResultPay);
				payListDto.setPay_point(pay_point);
				payListDto.setFree_point(free_point+event_free_point);
				payListDto.setUser_service_type(userServiceType);

				int resultInsert = payMapper.insertPayList(payListDto);	//pay_list 등록
				if (resultInsert == 0) System.err.println("insertPayList 쿼리 실행 에러");

				UseHistoryDTO hDto = new UseHistoryDTO();
				hDto.setUser_no(user_no);
				hDto.setUse_etc(payListDto.getNo() + "");
				hDto.setUse_content(productName + "구매 (인앱 결제)");
				hDto.setUse_etc_user_no(-1);

				// 포인트 적립을 히스토리에 남기기
				int resultHistory = 0;
				if(pay_point > 0) { //유료포인트
					hDto.setUse_cnt(pay_point);
					hDto.setUse_type(0);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트
					resultHistory = liveCallMapper.insertUseHistory(hDto);
				}
				if(free_point > 0) { //무료포인트
					hDto.setUse_cnt(free_point);
					hDto.setUse_type(2);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트
					resultHistory = liveCallMapper.insertUseHistory(hDto);
				}

				if (event_free_point >0){
					hDto.setUse_cnt(event_free_point);
					hDto.setUse_type(2);	//0 : 유료포인트 / 1 : 캐쉬 / 2 : 무료포인트
					hDto.setUse_content("첫 결제 이벤트 포인트 적립");
					resultHistory = liveCallMapper.insertUseHistory(hDto);
				}
				if (resultHistory == 0) System.err.println("insertUseHistory 쿼리 실행 에러");

				// 채팅 메세지 보내기 + 남기기
				ChatDTO cdto = new ChatDTO();
				cdto.setChat_text(
						textHello + udto.getUser_nick_name() + " 회원님, 포인트 구매해 주셔서 감사합니다.\n" +
								"\n" +
								"오늘도 행복한 하루 보내시기 바랍니다."
				);
				cdto.setFrom_user_no(1);	// 1 : 관리자
				cdto.setTo_user_no(dto.getUser_no());

				int resultChat = chatMapper.sendChat(cdto);
				if (resultChat == 0) System.err.println("sendChat 쿼리 실행 에러");

				if(!"".equals(user_adid)) {
					String pResult = directConnection("https://partner.allcam.co.kr/APP_acc/app_data.php?TYPE=P&UID=" +user_no
							+ "&MONEY=" + payResultPay
							+ "&ADID=" + user_adid
							+ "&PAYID="+no
							+ "&PAYTYPE=0");

					System.err.println("partner데이터 전달, 결제금액 : "+payResultPay);
				}
				UserInfoDTO _udto = userMapper.getUserInfo(user_no);
				userPointDTO.setResult(Utils.SUCCESS);
				userPointDTO.setUser_point(_udto.getUser_point());

			}
		}else{
			System.err.println("updatePayStatus 쿼리 실행 에러");
		}

		try {
			if(fos != null)
				fos.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

		return userPointDTO;
	}


	@Override
	public String insertIosReceipt(String body) {
		String resultStr = Utils.FAIL;
		// 단순 입력 처리
		int resultIns = payMapper.insertIosReceipt(body);

		// s:환불 요청 에 대한 처리
		JSONObject jsonObject = new JSONObject(body);
		String noti_type = jsonObject.getString("notification_type");
		String transaction_id = Long.toString(jsonObject.getLong("original_transaction_id"));

		if(noti_type.equals("CONSUMPTION_REQUEST")){
			String result = payMapper.confirmRefund(transaction_id);
			System.out.println(result);
			String strResult = sendConsumptionIOS(transaction_id,result);
			System.out.println(strResult);
		}
		// e:환불 요청 에 대한 처리

		if(resultIns == 1) {
			resultStr =  Utils.SUCCESS;
		}

		return resultStr;
	}

	@Override
	public DefaultDTO payOnTheOneStore(int pay_no, String installerPackageName, String simOperator, String googleAdId) {	//원스토어에 결제 정보 등록
		PayResultDTO payResultDto = payMapper.getPayResult(pay_no);		
		
		JSONObject paramJson = new JSONObject();
		paramJson.put("adId", googleAdId);
		paramJson.put("developerOrderId", pay_no + "");	//구매건을 식별하기 위한 개발사의 구매 ID (Unique한 값이어야 하며 중복 저장 시 오류발생, Error Response참조)		
		
		JSONArray developerProductList = new JSONArray();
		JSONObject productData = new JSONObject();
		productData.put("developerProductId", payResultDto.getNo());
		productData.put("developerProductName", payResultDto.getPay_result_product_name());
		productData.put("developerProductPrice", payResultDto.getPay_result_pay());
		productData.put("developerProductQty", 1);
		developerProductList.add(productData);
		
		
		paramJson.put("developerProductList", developerProductList);
		paramJson.put("simOperator", simOperator);
		paramJson.put("installerPackageName", installerPackageName);
		
		JSONArray purchaseMethodList = new JSONArray();
		JSONObject purchaseData = new JSONObject();
		purchaseData.put("purchaseMethodCd", getOneStorePayType(payResultDto.getPay_result_type()));
		purchaseData.put("purchasePrice", payResultDto.getPay_result_pay());
		purchaseMethodList.add(purchaseData);
		
		paramJson.put("purchaseMethodList", purchaseMethodList);
		paramJson.put("totalPrice", payResultDto.getPay_result_pay());
		paramJson.put("purchaseTime", new Date().getTime());
		
		int result = 0;
		
//		String sendUrl = "https://sbpp.onestore.co.kr/v2/purchase/developer/kr.allcam/send";	//테스
		String sendUrl = "https://apis.onestore.co.kr/v2/purchase/developer/kr.allcam/send";	//상용
		
		String oneStorePayResult = connection(sendUrl, paramJson);
				
		JSONObject oneStoreResultJson = new JSONObject(oneStorePayResult);
		
		
		if(0 == oneStoreResultJson.getInt("responseCode")) {
			String developerOrderId = oneStoreResultJson.getString("developerOrderId");
			
			OneStoreDTO dto = new OneStoreDTO();
			dto.setPay_no(pay_no);
			dto.setOne_store_info(developerOrderId);
			
			result = payMapper.updatePayOneStore(dto);
		}

		return Utils.defaultResult(new DefaultDTO(), result);
	}


	
	private String getOneStorePayType(String pay_type) {
		String resultPayType = "";
		
		if("card".equals(pay_type)) {
			resultPayType = "TRD_CREDITCARD";
		}else if("mobile".equals(pay_type)) {
			resultPayType = "TRD_MOBILEBILLING";
		}		
//		TRD_11PAY	11pay	
//		TRD_NAVERPAY	네이버페이	
//		TRD_KAKAOPAY	카카오페이	
//		TRD_PAYCO	페이코	
//		TRD_SAMSUNGPAY	삼성페이	
//		TRD_SSGPAY	SSGPAY	
//		TRD_TOSS	TOSS	
//		TRD_BANKTRANSFER	계좌이체	무통장입금, 가상계좌이체 포함
//		TRD_TMONEY	모바일 티머니	
//		TRD_CASHBEE	모바일 캐시비	
//		TRD_OKCASHBAG	OK캐쉬백	
//		TRD_CULTURELAND	컬쳐캐시	컬쳐랜드 컬쳐캐쉬
//		TRD_HAPPYMONEY	해피머니	해피머니상품권
//		TRD_BOOKNLIFE	도서문화상품권	
//		TRD_CASHGATE	편의점 캐쉬	
//		TRD_PAYPAL	PayPal	
//		TRD_TMEMBERSHIP	T 멤버십	
//		TRD_KTMEMBERSHIP	KT 멤버십	
//		TRD_LGMEMBERSHIP	U+ 멤버십	
//		TRD_GOOGLEPLAY	Google Play	
//		TRD_BITCOIN	비트 코인	
//		TRD_SKINSCASH	Skins Cash	
//		TRD_AMAZONPAY	Amazon Pay	
//		TRD_PURCHASE_ETC	기타 결제수단	
		
		return resultPayType;
	}

	public static String directConnection(String urlString) { //get
        HttpURLConnection http = null;
        String result = "";

        try {
            URL url = new URL(urlString);
            http = (HttpURLConnection) url.openConnection();
            http.setDoOutput(true);

            result = getBufferedeReader(http.getInputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if(http != null) { http.disconnect(); }
        }
        return result.trim();
    }

	public String connection(String urlString, JSONObject postJson) {    //post
        HttpURLConnection http = null;
        String result = "";
        try {
            http = getHttpConnect(urlString);

            OutputStreamWriter wr= new OutputStreamWriter(http.getOutputStream());
            wr.write(postJson.toString());
            wr.close();

            result = getBufferedeReader(http.getInputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if(http != null) { http.disconnect(); }
        }
        return result.trim();
    }

	public HttpURLConnection getHttpConnect(String urlString){
        String authToken = getAccessToken();

        URL url = null;
        try {
            JSONObject authJo = new JSONObject(authToken);

            String ACCECE_TOKEN = authJo.getString("access_token");

            url = new URL(urlString);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setDoOutput(true);
            http.setRequestProperty("Authorization", " Bearer " + ACCECE_TOKEN);
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestMethod("POST");

            return http;
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

	public static String getBufferedeReader(InputStream ip){
        String result = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(ip));
            StringBuffer buffer = new StringBuffer();

            String c;
            while ((c = in.readLine()) != null) {
                buffer.append(c);
            }
            result = buffer.toString().trim();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }

	public String getAccessToken() {
        String result = "";

        URL url = null;
        try {
            //url = new URL("https://sbpp.onestore.co.kr/v2/oauth/token");	//테스트
            url = new URL("https://apis.onestore.co.kr/v2/oauth/token");	//상용
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setDoOutput(true);
            http.setRequestProperty("Accept", "application/json");
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            OutputStreamWriter out_stream = new OutputStreamWriter(http.getOutputStream());
            out_stream.write("grant_type=client_credentials&client_id=kr.allcam&client_secret=sqw4TACB0of6rd8QMsn9XEtiMJ6fA6yPX0r0CttiMOk=");
            out_stream.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
            StringBuffer buffer = new StringBuffer();

            String c;
            while ((c = in.readLine()) != null) {
                buffer.append(c);
            }
            result = buffer.toString().trim();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.trim();
    }

	private String getReceiptJson(String urlString, JSONObject receiptJson) {
		HttpURLConnection con = null;
		String result = "";
		URL url = null;
		try {
			url = new URL(urlString);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			con.setDoInput(true);

			OutputStream os = con.getOutputStream();
			byte[] input = receiptJson.toString().getBytes(StandardCharsets.UTF_8);
			os.write(input, 0, input.length);

			result = getBufferedeReader(con.getInputStream());

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(con != null) { con.disconnect(); }
		}
		return result.trim();
	}

	public String sendConsumptionIOS(String transactionId, String confirmRefund) {
		String result = "";

		URL url = null;
		try {
			UUID uuid = UUID.randomUUID();
			String jwt = JwtTokenUtil.createToken("ios_consumption_token");
			url = new URL("https://api.storekit.itunes.apple.com/inApps/v1/transactions/consumption/"+transactionId);	//상용
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setRequestMethod("PUT");
			http.setDoOutput(true);
			http.setRequestProperty("Content-Type", "application/json");
			http.setRequestProperty("Accept", "application/json");
			http.setRequestProperty("Authorization", "Bearer " + jwt);


			String consumReq = "{\"accountTenure\":0, \"consumptionStatus\":0,\"customerConsented\":false, \"lifetimeDollarsPurchased\":0,"
					+" \"lifetimeDollarsRefunded\":0, \"userStatus\":1,\"platform\":1, \"playTime\":0,"
					+" \"deliveryStatus\":0, \"sampleContentProvided\":true,\"appAccountToken\":\""+uuid+"\"}";

			if(confirmRefund.equals("ALLOW")){
				consumReq = "{\"accountTenure\":0, \"consumptionStatus\":1,\"customerConsented\":true, \"lifetimeDollarsPurchased\":0,"
						+" \"lifetimeDollarsRefunded\":0, \"userStatus\":1,\"platform\":1, \"playTime\":0,"
						+" \"deliveryStatus\":0, \"sampleContentProvided\":true,\"appAccountToken\":\""+uuid+"\"}";
			}

			byte[] out = consumReq.getBytes(StandardCharsets.UTF_8);

			OutputStream stream = http.getOutputStream();
			stream.write(out);


			int responseCode = http.getResponseCode();

			if(responseCode == 202){
				result = Utils.SUCCESS;//처리 완
			}else{
				//실패
				result = Utils.FAIL;
			}

			http.disconnect();

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

				fos = new FileOutputStream(API_LOG_PATH+"sendConsumptionIOS_"+dateStr+".log",true); // error.log파일에 출력 준비
				ps=new PrintStream(fos); // err의 출력을 화면이 아닌, error.log파일로 변경
				System.setErr(ps);
				System.err.println("API : sendConsumptionIOS -----------------------------------");
				System.err.println("[" + dateStr+ " "+timeStr+"] "+ip);  // 사용자 ip
				System.err.println("transactionId: " + transactionId);
				System.err.println("confirmRefund: " + confirmRefund);
				System.err.println("responseCode: " + responseCode);
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

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}