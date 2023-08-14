package com.livelyit.allcam.service;

import com.livelyit.allcam.dto.DefaultDTO;
import com.livelyit.allcam.dto.ProductListDTO;
import com.livelyit.allcam.dto.UserPointDTO;

public interface PayService {
//	DefaultDTO startPay(int user_no, int pay_result_pay, String pay_result_type, int pay_result_point, String pay_result_product_name);	//결제 시작 등록
//	DefaultDTO startPayV2(int user_no, int pay_result_pay, String pay_result_type, int pay_result_point, String pay_result_product_name);	//결제 시작 등록
	DefaultDTO startPayV3(int user_no, int pay_result_pay, String pay_result_type, int pay_result_point, String pay_result_product_name);	//결제 시작 등록
	DefaultDTO startPayV4(int user_no, String pay_result_type, String product_id);	//결제 시작 등록

	DefaultDTO startPayMC(int user_no, String pay_result_type, String product_id);	//미인캠 결제 시작 등록

	DefaultDTO startPayIOSV3(int user_no, String pay_result_product_name);	//결제 시작 등록
	DefaultDTO startPayMCIOS(int user_no, String product_id);	//미인캠 IOS 인앱 결제 시작 등록
	DefaultDTO startPayIOSV4(int user_no, String product_id);	//202 nmc IOS 인앱 결제 시작 등록

	DefaultDTO startPayAndroidInApp(int user_no, int pay_result_pay, String pay_result_type, int pay_result_point, String pay_result_product_name);	//결제 시작 등록
	DefaultDTO startPayAndroidInAppV3(int user_no, String product_id);	//결제 시작 등록
	DefaultDTO startPayMCAndroidInApp(int user_no, String product_id);	//미인캠 안드로이앱 인앱 결제 시작 등록
	DefaultDTO startPayAndroidInAppV4(int user_no, String product_id);	//미인캠 안드로이앱 인앱 결제 시작 등록
	ProductListDTO getInAppProductList();
	ProductListDTO getInAppProductListMC();
	ProductListDTO getInAppProductListNMC();
//	String resultPay(int user_no, int pay_no, String pay_type, String product_name, int amount, int point, String DaoPayNum, String user_adid, String user_id);	//결제 완료 등록
	String resultPayIOS(int user_no, int pay_no, String pay_type, String product_name, int amount, int point, String DaoPayNum, String user_adid, String user_id, String receipt_data);	//ios 결제 완료 등록
	String resultPayV2(int user_no, int pay_no, String pay_type, String product_name, int amount, int point, String DaoPayNum, String user_adid, String user_id);	//결제 완료 등록
	String resultPayMC(int user_no, int pay_no, String pay_type, String product_name, int amount, int point, String DaoPayNum, String user_adid, String user_id);	//미인캠 결제 완료 등록
	String resultPayV3(int user_no, int pay_no, String pay_type, String product_name, int amount, int point, String DaoPayNum, String user_adid, String user_id);	//2022 통합 결제 완료 등록

	String resultPayIOSV2(int user_no, int pay_no, String pay_type, String product_name, int amount, int point, String DaoPayNum, String user_adid, String user_id, String receipt_data);	//ios 결제 완료 등록
	String resultPayMCIOS(int user_no, int pay_no, String pay_type, String product_name, int amount, int point, String DaoPayNum, String user_adid, String user_id, String receipt_data);	//미인캠 ios 결제 완료 등록
	String resultPayIOSV3(int user_no, int pay_no, String pay_type, String product_name, int amount, int point, String DaoPayNum, String user_adid, String user_id, String receipt_data);	//미인캠 ios 결제 완료 등록



	UserPointDTO payResultAndroid(int user_no, int pay_no, String pay_token, int pay_code, int amount, int point, String product_name, String user_adid);
	UserPointDTO payResultAndroidV2(int user_no, int pay_no, String pay_id, int pay_code, String product_id, String user_adid);
	UserPointDTO payResultAndroidV3(int user_no, int pay_no, String pay_id, int pay_code, String product_id, String user_adid);
	UserPointDTO payResultMCAndroid(int user_no, int pay_no, String pay_id, int pay_code, String product_id, String user_adid);


	DefaultDTO payOnTheOneStore(int pay_no, String installerPackageName, String simOperator, String googleAdId);
	String insertIosReceipt(String body);

}