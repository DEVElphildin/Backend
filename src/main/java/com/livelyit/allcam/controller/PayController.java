package com.livelyit.allcam.controller;

import com.livelyit.allcam.dto.ProductListDTO;
import com.livelyit.allcam.dto.UserPointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.DefaultDTO;
import com.livelyit.allcam.service.PayService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.rmi.CORBA.Util;
import javax.servlet.http.HttpServletRequest;

@RestController
public class PayController {
	@Autowired
	PayService payService;
	
//	@CrossOrigin(origins= {"http://localhost:8080", "http://allcam.co.kr", "http://pay.onelive.co.kr", "http://192.168.10.17:8080"})
//	@RequestMapping("/startPay")
//	@ResponseBody
//	public DefaultDTO startPay(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
//			@RequestParam(value = "pay_result_pay", required = !Utils.Debug) int pay_result_pay,
//			@RequestParam(value = "pay_result_type", required = !Utils.Debug) String pay_result_type,
//			@RequestParam(value = "pay_result_point", required = !Utils.Debug) int pay_result_point,
//			@RequestParam(value = "pay_result_product_name", required = !Utils.Debug) String pay_result_product_name) {
//		return payService.startPay(user_no, pay_result_pay, pay_result_type, pay_result_point, pay_result_product_name);
//	}
//
//	@CrossOrigin(origins= {"http://localhost:8080", "http://allcam.co.kr", "http://pay.onelive.co.kr", "http://192.168.10.17:8080"})
//	@RequestMapping("/v2/startPay")
//	@ResponseBody
//	public DefaultDTO startPayV2(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
//							   @RequestParam(value = "pay_result_pay", required = !Utils.Debug) int pay_result_pay,
//							   @RequestParam(value = "pay_result_type", required = !Utils.Debug) String pay_result_type,
//							   @RequestParam(value = "pay_result_point", required = !Utils.Debug) int pay_result_point,
//							   @RequestParam(value = "pay_result_product_name", required = !Utils.Debug) String pay_result_product_name) {
//		return payService.startPayV2(user_no, pay_result_pay, pay_result_type, pay_result_point, pay_result_product_name);
//	}

	@CrossOrigin(origins= {"http://localhost:8080", "http://allcam.co.kr", "http://pay.onelive.co.kr", "http://192.168.10.17:8080"})
	@RequestMapping("/v3/startPay")
	@ResponseBody
	public DefaultDTO startPayV3(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
								 @RequestParam(value = "pay_result_pay", required = !Utils.Debug) int pay_result_pay,
								 @RequestParam(value = "pay_result_type", required = !Utils.Debug) String pay_result_type,
								 @RequestParam(value = "pay_result_point", required = !Utils.Debug) int pay_result_point,
								 @RequestParam(value = "pay_result_product_name", required = !Utils.Debug) String pay_result_product_name) {
		return payService.startPayV3(user_no, pay_result_pay, pay_result_type, pay_result_point, pay_result_product_name);
	}

	@CrossOrigin(origins= {"http://localhost:8080", "http://allcam.co.kr", "http://pay.onelive.co.kr", "http://192.168.10.17:8080"})
	@RequestMapping("/v4/startPay")
	@ResponseBody
	public DefaultDTO startPayV4(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
								@RequestParam(value = "pay_result_type", required = !Utils.Debug) String pay_result_type,
								@RequestParam(value = "product_id", required = !Utils.Debug) String product_id) {
		return payService.startPayV4(user_no, pay_result_type, product_id);
	}

	@CrossOrigin(origins= {"http://localhost:8080", "http://allcam.co.kr", "http://pay.onelive.co.kr", "http://192.168.10.17:8080"})
	@RequestMapping("/startPayMC")
	@ResponseBody
	public DefaultDTO startPayMC(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
								 @RequestParam(value = "pay_result_type", required = !Utils.Debug) String pay_result_type,
								 @RequestParam(value = "product_id", required = !Utils.Debug) String product_id) {
		return payService.startPayMC(user_no, pay_result_type, product_id);
	}

	@CrossOrigin(origins= {"http://localhost:8080", "http://allcam.co.kr", "http://pay.onelive.co.kr", "http://192.168.10.17:8080"})
	@RequestMapping("/v3/startPayIOS")
	@ResponseBody
	public DefaultDTO startPayIOSV3(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
								 @RequestParam(value = "pay_result_product_name", required = !Utils.Debug) String pay_result_product_name) {
		return payService.startPayIOSV3(user_no, pay_result_product_name);
	}

	@CrossOrigin(origins= {"http://localhost:8080", "http://allcam.co.kr", "http://pay.onelive.co.kr", "http://192.168.10.17:8080"})
	@RequestMapping("/startPayMCIOS")
	@ResponseBody
	public DefaultDTO startPayMCIOS(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
									@RequestParam(value = "product_id", required = !Utils.Debug) String product_id) {
		return payService.startPayMCIOS(user_no, product_id);
	}

	@CrossOrigin(origins= {"http://localhost:8080", "http://allcam.co.kr", "http://pay.onelive.co.kr", "http://192.168.10.17:8080"})
	@RequestMapping("/v4/startPayIOS")
	@ResponseBody
	public DefaultDTO startPayIOSV4(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
									@RequestParam(value = "product_id", required = !Utils.Debug) String product_id) {
		return payService.startPayIOSV4(user_no, product_id);
	}

	@CrossOrigin(origins= {"http://localhost:8080", "http://allcam.co.kr", "http://pay.onelive.co.kr", "http://192.168.10.17:8080"})
	@RequestMapping("/v2/startPayAndroidInApp")
	@ResponseBody
	public DefaultDTO startPayAndroidInAppV2(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
								 @RequestParam(value = "pay_result_pay", required = !Utils.Debug) int pay_result_pay,
								 @RequestParam(value = "pay_result_type", required = !Utils.Debug) String pay_result_type,
								 @RequestParam(value = "pay_result_point", required = !Utils.Debug) int pay_result_point,
								 @RequestParam(value = "pay_result_product_name", required = !Utils.Debug) String pay_result_product_name) {
		return payService.startPayAndroidInApp(user_no, pay_result_pay, pay_result_type, pay_result_point, pay_result_product_name);
	}

	@CrossOrigin(origins= {"http://localhost:8080", "http://allcam.co.kr", "http://pay.onelive.co.kr", "http://192.168.10.17:8080"})
	@RequestMapping("/v3/startPayAndroidInApp")
	@ResponseBody
	public DefaultDTO startPayAndroidInAppV3(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
											 @RequestParam(value = "product_id", required = !Utils.Debug) String product_id) {
		return payService.startPayAndroidInAppV3(user_no, product_id);
	}

	@CrossOrigin(origins= {"http://localhost:8080", "http://allcam.co.kr", "http://pay.onelive.co.kr", "http://192.168.10.17:8080"})
	@RequestMapping("/startPayMCAndroidInApp")
	@ResponseBody
	public DefaultDTO startPayMCAndroidInApp(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
											 @RequestParam(value = "product_id", required = !Utils.Debug) String product_id) {
		return payService.startPayMCAndroidInApp(user_no, product_id);
	}

	@CrossOrigin(origins= {"http://localhost:8080", "http://allcam.co.kr", "http://pay.onelive.co.kr", "http://192.168.10.17:8080"})
	@RequestMapping("/v4/startPayAndroidInApp")
	@ResponseBody
	public DefaultDTO startPayAndroidInAppV4(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
											 @RequestParam(value = "product_id", required = !Utils.Debug) String product_id) {
		return payService.startPayAndroidInAppV4(user_no, product_id);
	}

	@CrossOrigin(origins= {"http://localhost:8080", "http://allcam.co.kr", "http://pay.onelive.co.kr", "http://192.168.10.17:8080"})
	@RequestMapping("/v4/startPayMCAndroidInApp")
	@ResponseBody
	public DefaultDTO startPayAndroidInAppV4Wowcam(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
											 @RequestParam(value = "product_id", required = !Utils.Debug) String product_id) {
		return payService.startPayAndroidInAppV4(user_no, product_id);
	}

	@RequestMapping("/getInAppProductList")
	@ResponseBody
	public ProductListDTO getInAppProductList(){
		return payService.getInAppProductList();
	}

	@RequestMapping("/getInAppProductListMC")
	@ResponseBody
	public ProductListDTO getInAppProductListMC(){
		return payService.getInAppProductListMC();
	}

	@RequestMapping("/getInAppProductListNMC")
	@ResponseBody
	public ProductListDTO getInAppProductListNMC(){
		return payService.getInAppProductListNMC();
	}


//	@RequestMapping(value = "/resultPay", params={"user_no", "pay_no", "amount", "point", "product_name", "pay_type"})
//	@ResponseBody
//	public String resultPay(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
//			@RequestParam(value = "pay_no", required = !Utils.Debug) int pay_no,
//			@RequestParam(value = "amount", required = !Utils.Debug) int amount,
//			@RequestParam(value = "point", required = !Utils.Debug) int point,
//			@RequestParam(value = "product_name", required = !Utils.Debug) String product_name,
//			@RequestParam(value = "pay_type", required = !Utils.Debug) String pay_type) {
//		return payService.resultPay(user_no, pay_no, pay_type, product_name, amount, point, "", "", "");
//	}
//
//
//
//
//	//올앳페이 완료처리
//	@CrossOrigin(origins= {"http://allcam.co.kr", "http://pay.onelive.co.kr", "http://211.110.65.131"})
//	@RequestMapping(value = "/resultPay", params={"user_no", "pay_no", "amount", "point", "product_name", "pay_type", "pg_pay_no", "user_adid" })
//	@ResponseBody
//	public String resultPay(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
//							@RequestParam(value = "pay_no", required = !Utils.Debug) int pay_no,
//							@RequestParam(value = "amount", required = !Utils.Debug) int amount,
//							@RequestParam(value = "point", required = !Utils.Debug) int point,
//							@RequestParam(value = "product_name", required = !Utils.Debug) String product_name,
//							@RequestParam(value = "pay_type", required = !Utils.Debug) String pay_type,
//							@RequestParam(value = "pg_pay_no", required = !Utils.Debug) String pg_pay_no,
//							@RequestParam(value = "user_adid", required = !Utils.Debug) String user_adid) {
//		return payService.resultPay(user_no, pay_no, pay_type, product_name, amount, point, pg_pay_no, user_adid, "");
//	}

	//올앳페이 완료처리
	@CrossOrigin(origins= {"http://allcam.co.kr", "http://pay.onelive.co.kr", "http://211.110.65.131"})
	@RequestMapping(value = "/v2/resultPay", params={"user_no", "pay_no", "amount", "point", "product_name", "pay_type", "pg_pay_no", "user_adid" })
	@ResponseBody
	public String resultPayV2(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
							@RequestParam(value = "pay_no", required = !Utils.Debug) int pay_no,
							@RequestParam(value = "amount", required = !Utils.Debug) int amount,
							@RequestParam(value = "point", required = !Utils.Debug) int point,
							@RequestParam(value = "product_name", required = !Utils.Debug) String product_name,
							@RequestParam(value = "pay_type", required = !Utils.Debug) String pay_type,
							@RequestParam(value = "pg_pay_no", required = !Utils.Debug) String pg_pay_no,
							@RequestParam(value = "user_adid", required = !Utils.Debug) String user_adid) {
		return payService.resultPayV2(user_no, pay_no, pay_type, product_name, amount, point, pg_pay_no, user_adid, "");
	}

	//미인캠 웹 결제 완료
	@CrossOrigin(origins= {"http://allcam.co.kr", "http://pay.onelive.co.kr", "http://211.110.65.131"})
	@RequestMapping(value = "/resultPayMC", params={"user_no", "pay_no", "amount", "point", "product_name", "pay_type", "pg_pay_no", "user_adid" })
	@ResponseBody
	public String resultPayMC(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
							  @RequestParam(value = "pay_no", required = !Utils.Debug) int pay_no,
							  @RequestParam(value = "amount", required = !Utils.Debug) int amount,
							  @RequestParam(value = "point", required = !Utils.Debug) int point,
							  @RequestParam(value = "product_name", required = !Utils.Debug) String product_name,
							  @RequestParam(value = "pay_type", required = !Utils.Debug) String pay_type,
							  @RequestParam(value = "pg_pay_no", required = !Utils.Debug) String pg_pay_no,
							  @RequestParam(value = "user_adid", required = !Utils.Debug) String user_adid) {
		return payService.resultPayMC(user_no, pay_no, pay_type, product_name, amount, point, pg_pay_no, user_adid, "");
	}



	//2022 nmc 웹 결제 완료
	@CrossOrigin(origins= {"http://allcam.co.kr", "http://pay.onelive.co.kr", "http://211.110.65.131"})
	@RequestMapping(value = "/v3/resultPay", params={"user_no", "pay_no", "amount", "point", "product_name", "pay_type", "pg_pay_no", "user_adid" })
	@ResponseBody
	public String resultPayV3(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
							  @RequestParam(value = "pay_no", required = !Utils.Debug) int pay_no,
							  @RequestParam(value = "amount", required = !Utils.Debug) int amount,
							  @RequestParam(value = "point", required = !Utils.Debug) int point,
							  @RequestParam(value = "product_name", required = !Utils.Debug) String product_name,
							  @RequestParam(value = "pay_type", required = !Utils.Debug) String pay_type,
							  @RequestParam(value = "pg_pay_no", required = !Utils.Debug) String pg_pay_no,
							  @RequestParam(value = "user_adid", required = !Utils.Debug) String user_adid) {
		return payService.resultPayV3(user_no, pay_no, pay_type, product_name, amount, point, pg_pay_no, user_adid, "");
	}


	@RequestMapping("/payResult")
	@ResponseBody
	public String resultPay(@RequestParam(value = "PAYMETHOD", required = !Utils.Debug) String PAYMETHOD,
			@RequestParam(value = "CPID", required = !Utils.Debug) String CPID,
			@RequestParam(value = "DAOUTRX", required = !Utils.Debug) String DAOUTRX,
			@RequestParam(value = "ORDERNO", required = !Utils.Debug) String ORDERNO,	//pay_no
			@RequestParam(value = "AMOUNT", required = !Utils.Debug) String AMOUNT,
			@RequestParam(value = "PRODUCTNAME", required = !Utils.Debug) String PRODUCTNAME,
			@RequestParam(value = "SETTDATE", required = !Utils.Debug) String SETTDATE,
			@RequestParam(value = "USERID", required = !Utils.Debug) String USERID,
			@RequestParam(value = "USERNAME", required = !Utils.Debug) String USERNAME,
			@RequestParam(value = "PRODUCTCODE", required = false) String PRODUCTCODE,
			@RequestParam(value = "RESERVEDINDEX1", required = false) String RESERVEDINDEX1,
			@RequestParam(value = "RESERVEDINDEX2", required = false) String RESERVEDINDEX2,
			@RequestParam(value = "RESERVEDSTRING", required = false) String RESERVEDSTRING) {
		
//		System.out.println("PAYMETHOD : ============================================1 : " + PAYMETHOD.toLowerCase());

		payService.resultPayV2(Integer.parseInt(USERNAME), Integer.parseInt(ORDERNO), PAYMETHOD.toLowerCase(), PRODUCTNAME, Integer.parseInt(AMOUNT), Integer.parseInt(RESERVEDINDEX1), DAOUTRX, RESERVEDSTRING, USERID);
		
		return "SUCCESS";
	}

	@RequestMapping("/payResultIOS")
	@ResponseBody
	public String resultPayIOS(@RequestParam(value = "PAYMETHOD", required = !Utils.Debug) String PAYMETHOD,
							@RequestParam(value = "CPID", required = !Utils.Debug) String CPID,
							@RequestParam(value = "DAOUTRX", required = !Utils.Debug) String DAOUTRX,
							@RequestParam(value = "ORDERNO", required = !Utils.Debug) String ORDERNO,	//pay_no
							@RequestParam(value = "AMOUNT", required = !Utils.Debug) String AMOUNT,
							@RequestParam(value = "PRODUCTNAME", required = !Utils.Debug) String PRODUCTNAME,
							@RequestParam(value = "SETTDATE", required = !Utils.Debug) String SETTDATE,
							@RequestParam(value = "USERID", required = !Utils.Debug) String USERID,
							@RequestParam(value = "USERNAME", required = !Utils.Debug) String USERNAME,
							@RequestParam(value = "RECEIPTDATA", required = !Utils.Debug) String RECEIPTDATA,
							@RequestParam(value = "RESERVEDINDEX1", required = false) String RESERVEDINDEX1,
							@RequestParam(value = "RESERVEDINDEX2", required = false) String RESERVEDINDEX2,
							@RequestParam(value = "RESERVEDSTRING", required = false) String RESERVEDSTRING) {
		return payService.resultPayIOS(Integer.parseInt(USERNAME), Integer.parseInt(ORDERNO), PAYMETHOD.toLowerCase(), PRODUCTNAME, Integer.parseInt(AMOUNT), Integer.parseInt(RESERVEDINDEX1), DAOUTRX, RESERVEDSTRING, USERID, RECEIPTDATA);
	}

	@RequestMapping("/v2/payResultIOS")
	@ResponseBody
	public String resultPayIOSV2(@RequestParam(value = "PAYMETHOD", required = !Utils.Debug) String PAYMETHOD,
							   @RequestParam(value = "CPID", required = !Utils.Debug) String CPID,
							   @RequestParam(value = "DAOUTRX", required = !Utils.Debug) String DAOUTRX,
							   @RequestParam(value = "ORDERNO", required = !Utils.Debug) String ORDERNO,	//pay_no
							   @RequestParam(value = "AMOUNT", required = !Utils.Debug) String AMOUNT,
							   @RequestParam(value = "PRODUCTNAME", required = !Utils.Debug) String PRODUCTNAME,
							   @RequestParam(value = "SETTDATE", required = !Utils.Debug) String SETTDATE,
							   @RequestParam(value = "USERID", required = !Utils.Debug) String USERID,
							   @RequestParam(value = "USERNAME", required = !Utils.Debug) String USERNAME,
							   @RequestParam(value = "RECEIPTDATA", required = !Utils.Debug) String RECEIPTDATA,
							   @RequestParam(value = "RESERVEDINDEX1", required = false) String RESERVEDINDEX1,
							   @RequestParam(value = "RESERVEDINDEX2", required = false) String RESERVEDINDEX2,
							   @RequestParam(value = "RESERVEDSTRING", required = false) String RESERVEDSTRING) {
		return payService.resultPayIOSV2(Integer.parseInt(USERNAME), Integer.parseInt(ORDERNO), PAYMETHOD.toLowerCase(), PRODUCTNAME, Integer.parseInt(AMOUNT), Integer.parseInt(RESERVEDINDEX1), DAOUTRX, RESERVEDSTRING, USERID, RECEIPTDATA);
	}

	@RequestMapping("/payResultMCIOS")
	@ResponseBody
	public String resultPayMCIOS(@RequestParam(value = "PAYMETHOD", required = !Utils.Debug) String PAYMETHOD,
								 @RequestParam(value = "CPID", required = !Utils.Debug) String CPID,
								 @RequestParam(value = "DAOUTRX", required = !Utils.Debug) String DAOUTRX,
								 @RequestParam(value = "ORDERNO", required = !Utils.Debug) String ORDERNO,	//pay_no
								 @RequestParam(value = "AMOUNT", required = !Utils.Debug) String AMOUNT,
								 @RequestParam(value = "PRODUCTNAME", required = !Utils.Debug) String PRODUCTNAME,
								 @RequestParam(value = "SETTDATE", required = !Utils.Debug) String SETTDATE,
								 @RequestParam(value = "USERID", required = !Utils.Debug) String USERID,
								 @RequestParam(value = "USERNAME", required = !Utils.Debug) String USERNAME,
								 @RequestParam(value = "RECEIPTDATA", required = !Utils.Debug) String RECEIPTDATA,
								 @RequestParam(value = "RESERVEDINDEX1", required = false) String RESERVEDINDEX1,
								 @RequestParam(value = "RESERVEDINDEX2", required = false) String RESERVEDINDEX2,
								 @RequestParam(value = "RESERVEDSTRING", required = false) String RESERVEDSTRING) {
		return payService.resultPayMCIOS(Integer.parseInt(USERNAME), Integer.parseInt(ORDERNO), PAYMETHOD.toLowerCase(), PRODUCTNAME, Integer.parseInt(AMOUNT), Integer.parseInt(RESERVEDINDEX1), DAOUTRX, RESERVEDSTRING, USERID, RECEIPTDATA);
	}

	@RequestMapping("/v3/payResultIOS")
	@ResponseBody
	public String resultPayIOSV3(@RequestParam(value = "PAYMETHOD", required = !Utils.Debug) String PAYMETHOD,
								 @RequestParam(value = "CPID", required = !Utils.Debug) String CPID,
								 @RequestParam(value = "DAOUTRX", required = !Utils.Debug) String DAOUTRX,
								 @RequestParam(value = "ORDERNO", required = !Utils.Debug) String ORDERNO,	//pay_no
								 @RequestParam(value = "AMOUNT", required = !Utils.Debug) String AMOUNT,
								 @RequestParam(value = "PRODUCTNAME", required = !Utils.Debug) String PRODUCTNAME,
								 @RequestParam(value = "SETTDATE", required = !Utils.Debug) String SETTDATE,
								 @RequestParam(value = "USERID", required = !Utils.Debug) String USERID,
								 @RequestParam(value = "USERNAME", required = !Utils.Debug) String USERNAME,
								 @RequestParam(value = "RECEIPTDATA", required = !Utils.Debug) String RECEIPTDATA,
								 @RequestParam(value = "RESERVEDINDEX1", required = false) String RESERVEDINDEX1,
								 @RequestParam(value = "RESERVEDINDEX2", required = false) String RESERVEDINDEX2,
								 @RequestParam(value = "RESERVEDSTRING", required = false) String RESERVEDSTRING) {
		return payService.resultPayIOSV3(Integer.parseInt(USERNAME), Integer.parseInt(ORDERNO), PAYMETHOD.toLowerCase(), PRODUCTNAME, Integer.parseInt(AMOUNT), Integer.parseInt(RESERVEDINDEX1), DAOUTRX, RESERVEDSTRING, USERID, RECEIPTDATA);
	}

	@RequestMapping("/payResultAndroid")
	@ResponseBody
	public UserPointDTO payResultAndroid(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
										 @RequestParam(value = "pay_no", required = !Utils.Debug) int pay_no,
										 @RequestParam(value = "pay_token", required = !Utils.Debug) String pay_token,
										 @RequestParam(value = "pay_code", required = !Utils.Debug) int pay_code,
										 @RequestParam(value = "amount", required = !Utils.Debug) int amount,
										 @RequestParam(value = "point", required = !Utils.Debug) int point,
										 @RequestParam(value = "product_name", required = !Utils.Debug) String product_name,
										 @RequestParam(value = "user_adid", required = !Utils.Debug) String user_adid) {
		return payService.payResultAndroid(user_no, pay_no, pay_token, pay_code, amount, point, product_name,user_adid);
	}

	@RequestMapping("/v2/payResultAndroid")
	@ResponseBody
	public UserPointDTO payResultAndroidV2(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
										 @RequestParam(value = "pay_no", required = !Utils.Debug) int pay_no,
										 @RequestParam(value = "pay_id", required = !Utils.Debug) String pay_id,
										 @RequestParam(value = "pay_code", required = !Utils.Debug) int pay_code,
										 @RequestParam(value = "product_id", required = !Utils.Debug) String product_id,
										 @RequestParam(value = "user_adid", required = !Utils.Debug) String user_adid) {
		return payService.payResultAndroidV2(user_no, pay_no, pay_id, pay_code, product_id, user_adid);
	}

	@RequestMapping("/payResultMCAndroid")
	@ResponseBody
	public UserPointDTO payResultMCAndroid(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
										   @RequestParam(value = "pay_no", required = !Utils.Debug) int pay_no,
										   @RequestParam(value = "pay_id", required = !Utils.Debug) String pay_id,
										   @RequestParam(value = "pay_code", required = !Utils.Debug) int pay_code,
										   @RequestParam(value = "product_id", required = !Utils.Debug) String product_id,
										   @RequestParam(value = "user_adid", required = !Utils.Debug) String user_adid) {
		return payService.payResultMCAndroid(user_no, pay_no, pay_id, pay_code, product_id, user_adid);
	}

	@RequestMapping("/v3/payResultAndroid")
	@ResponseBody
	public UserPointDTO payResultAndroidV3(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
										   @RequestParam(value = "pay_no", required = !Utils.Debug) int pay_no,
										   @RequestParam(value = "pay_id", required = !Utils.Debug) String pay_id,
										   @RequestParam(value = "pay_code", required = !Utils.Debug) int pay_code,
										   @RequestParam(value = "product_id", required = !Utils.Debug) String product_id,
										   @RequestParam(value = "user_adid", required = !Utils.Debug) String user_adid) {
		return payService.payResultAndroidV3(user_no, pay_no, pay_id, pay_code, product_id, user_adid);
	}
	
	@RequestMapping("/payOnTheOneStore")
	@ResponseBody
	public DefaultDTO payOnTheOneStore(@RequestParam(value = "pay_no", required = !Utils.Debug) int pay_no,
			@RequestParam(value = "installerPackageName", required = !Utils.Debug) String installerPackageName,
			@RequestParam(value = "simOperator", required = !Utils.Debug) String simOperator,
			@RequestParam(value = "googleAdId", required = !Utils.Debug) String googleAdId) {
		return payService.payOnTheOneStore(pay_no, installerPackageName, simOperator, googleAdId);
	}

	@RequestMapping("/iosAppStoreServerNotification")
	@ResponseBody
	public String iosAppStoreNotification(@RequestBody String body)  {

		String result = payService.insertIosReceipt(body);
		return "{'result':'SUCCESS'}";
	}



//	@RequestMapping("/testPayResult")
//	@ResponseBody
//	public String testPayResult() {
//		String result = "";
//		result += "<script type=\"text/javascript\">";
////		result += "location.href='http://allcam.co.kr/AllcamPay/pay_result.jsp?user_no=" +user_no+ "&pay_no=" +pay_no+ "&amount=" +amount+ "&point=" +point+ "&product_name=" +product_name+ "&pay_type=" +pay_type+ "'";
//		result += "location.replace('http://localhost:8080/AllcamPay/pay_result.jsp?user_no=46&pay_no=176&amount=30000&point=500&product_name=dddddddddddddd&pay_type=card');";
//		result += "</script>";
//		return result;
//	}	
}