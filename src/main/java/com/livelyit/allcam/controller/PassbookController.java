package com.livelyit.allcam.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.DefaultDTO;
import com.livelyit.allcam.dto.PassBookDTO;
import com.livelyit.allcam.dto.PassBookResultDTO;
import com.livelyit.allcam.service.PassbookService;

import static com.livelyit.allcam.common.Utils.checkEventDay;

@RestController
public class PassbookController {
	@Autowired
	PassbookService passbookService;

	@RequestMapping("/passbookList")
	@ResponseBody
	public PassBookResultDTO selectPassbookList(){
		return passbookService.selectPassbookList();
	}

	@CrossOrigin(origins= {"http://localhost:8080", "http://allcam.co.kr", "http://pay.onelive.co.kr","http://192.168.10.17:8080"})
	@RequestMapping("/passbook")
	@ResponseBody
	public PassBookResultDTO insertPassBook(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no, 
			@RequestParam(value = "passbook_name", required = !Utils.Debug) String passbook_name, 
			@RequestParam(value = "passbook_phone_number", required = !Utils.Debug) String passbook_phone_number, 
			@RequestParam(value = "passbook_point", required = !Utils.Debug) int passbook_point, 
			@RequestParam(value = "passbook_payment", required = !Utils.Debug) int passbook_payment, 
			@RequestParam(value = "cash_receipts", required = !Utils.Debug) int cash_receipts, 
			@RequestParam(value = "cash_receipts_purpose", required = !Utils.Debug) String cash_receipts_purpose, 
			@RequestParam(value = "cash_receipts_name", required = !Utils.Debug) String cash_receipts_name, 
			@RequestParam(value = "cash_receipts_number", required = !Utils.Debug) String cash_receipts_number, 
			@RequestParam(value = "cash_receipts_phone_nember", required = !Utils.Debug) String cash_receipts_phone_nember) {
		
		PassBookDTO dto = new PassBookDTO();
		dto.setUser_no(user_no);
		dto.setPassbook_name(passbook_name);
		dto.setPassbook_phone_number(passbook_phone_number);
		dto.setPassbook_point(passbook_point);
		dto.setPassbook_payment(passbook_payment);
		dto.setCash_receipts(cash_receipts); //--------------------------------여기까진 무조건 받아야함
		dto.setCash_receipts_purpose(cash_receipts_purpose);
		dto.setCash_receipts_name(cash_receipts_name);
		dto.setCash_receipts_number(cash_receipts_number);
		dto.setCash_receipts_phone_nember(cash_receipts_phone_nember);
		
		if(passbook_point == 3000){
			dto.setPassbook_product("3,000 P");
			
			dto.setPay_point(3000);
			dto.setFree_point(0);
			dto.setPassbook_free_point(0);
		}else if(passbook_point == 6060) {
			dto.setPassbook_product("6,000 P");

			dto.setPay_point(6000);
			dto.setFree_point(60);
			dto.setPassbook_free_point(0);
		}else if(passbook_point == 18720) {
			dto.setPassbook_product("18,000 P");

			dto.setPay_point(18000);
			dto.setFree_point(540);
			dto.setPassbook_free_point(180);
		}else if(passbook_point == 32100) {
			dto.setPassbook_product("30,000 P");

			dto.setPay_point(30000);
			dto.setFree_point(1500);
			dto.setPassbook_free_point(600);
		}else if(passbook_point == 67800) {
			dto.setPassbook_product("60,000 P");
			
			dto.setPay_point(60000);
			dto.setFree_point(6000);
			dto.setPassbook_free_point(1800);
		}else if(passbook_point == 140400) {
			dto.setPassbook_product("120,000 P");

			dto.setPay_point(120000);
			dto.setFree_point(14400);
			dto.setPassbook_free_point(6000);
		}
		
		return passbookService.insertPassBook(dto);
	}

	@CrossOrigin(origins= {"http://localhost:8080", "http://allcam.co.kr", "http://pay.onelive.co.kr","http://192.168.10.17:8080"})
	@RequestMapping("/v2/passbook")
	@ResponseBody
	public PassBookResultDTO insertPassBookV2(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
											@RequestParam(value = "passbook_name", required = !Utils.Debug) String passbook_name,
											@RequestParam(value = "passbook_phone_number", required = !Utils.Debug) String passbook_phone_number,
											@RequestParam(value = "passbook_point", required = !Utils.Debug) int passbook_point,
											@RequestParam(value = "passbook_payment", required = !Utils.Debug) int passbook_payment,
											@RequestParam(value = "cash_receipts", required = !Utils.Debug) int cash_receipts,
											@RequestParam(value = "cash_receipts_purpose", required = !Utils.Debug) String cash_receipts_purpose,
											@RequestParam(value = "cash_receipts_name", required = !Utils.Debug) String cash_receipts_name,
											@RequestParam(value = "cash_receipts_number", required = !Utils.Debug) String cash_receipts_number,
											@RequestParam(value = "cash_receipts_phone_nember", required = !Utils.Debug) String cash_receipts_phone_nember) {

		PassBookDTO dto = new PassBookDTO();
		dto.setUser_no(user_no);
		dto.setPassbook_name(passbook_name);
		dto.setPassbook_phone_number(passbook_phone_number);
		dto.setPassbook_point(passbook_point);
		dto.setPassbook_payment(passbook_payment);
		dto.setCash_receipts(cash_receipts); //--------------------------------여기까진 무조건 받아야함
		dto.setCash_receipts_purpose(cash_receipts_purpose);
		dto.setCash_receipts_name(cash_receipts_name);
		dto.setCash_receipts_number(cash_receipts_number);
		dto.setCash_receipts_phone_nember(cash_receipts_phone_nember);

		if(passbook_point == 3000){
			dto.setPassbook_product("3,000 P");

			dto.setPay_point(3000);
			dto.setFree_point(0);
			dto.setPassbook_free_point(0);
		}else if(passbook_point == 6060) {
			dto.setPassbook_product("6,000 P");

			dto.setPay_point(6000);
			dto.setFree_point(0);
			dto.setPassbook_free_point(60);
		}else if(passbook_point == 18720) {
			dto.setPassbook_product("18,000 P");

			dto.setPay_point(18000);
			dto.setFree_point(180);
			dto.setPassbook_free_point(540);
		}else if(passbook_point == 32100) {
			dto.setPassbook_product("30,000 P");

			dto.setPay_point(30000);
			dto.setFree_point(600);
			dto.setPassbook_free_point(1500);
		}else if(passbook_point == 67800) {
			dto.setPassbook_product("60,000 P");

			dto.setPay_point(60000);
			dto.setFree_point(1800);
			dto.setPassbook_free_point(6000);
		}else if(passbook_point == 140400) {
			dto.setPassbook_product("120,000 P");

			dto.setPay_point(120000);
			dto.setFree_point(6000);
			dto.setPassbook_free_point(14400);
		}

		return passbookService.insertPassBookV2(dto);
	}

	@CrossOrigin(origins= {"http://localhost:8080", "http://allcam.co.kr", "http://pay.onelive.co.kr","http://192.168.10.17:8080"})
	@RequestMapping("/v3/passbook")
	@ResponseBody
	public PassBookResultDTO insertPassBookV3(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
											  @RequestParam(value = "passbook_name", required = !Utils.Debug) String passbook_name,
											  @RequestParam(value = "passbook_phone_number", required = !Utils.Debug) String passbook_phone_number,
											  @RequestParam(value = "passbook_point", required = !Utils.Debug) int passbook_point,
											  @RequestParam(value = "passbook_payment", required = !Utils.Debug) int passbook_payment,
											  @RequestParam(value = "cash_receipts", required = !Utils.Debug) int cash_receipts,
											  @RequestParam(value = "cash_receipts_purpose", required = !Utils.Debug) String cash_receipts_purpose,
											  @RequestParam(value = "cash_receipts_name", required = !Utils.Debug) String cash_receipts_name,
											  @RequestParam(value = "cash_receipts_number", required = !Utils.Debug) String cash_receipts_number,
											  @RequestParam(value = "cash_receipts_phone_nember", required = !Utils.Debug) String cash_receipts_phone_nember,
											  @RequestParam(value = "user_adid", required = !Utils.Debug) String user_adid) {

		PassBookDTO dto = new PassBookDTO();
		dto.setUser_no(user_no);
		dto.setPassbook_name(passbook_name);
		dto.setPassbook_phone_number(passbook_phone_number);
		dto.setPassbook_point(passbook_point);
		dto.setPassbook_payment(passbook_payment);
		dto.setCash_receipts(cash_receipts); //--------------------------------여기까진 무조건 받아야함
		dto.setCash_receipts_purpose(cash_receipts_purpose);
		dto.setCash_receipts_name(cash_receipts_name);
		dto.setCash_receipts_number(cash_receipts_number);
		dto.setCash_receipts_phone_nember(cash_receipts_phone_nember);
		dto.setUser_adid(user_adid);

		if(passbook_point == 3000){
			dto.setPassbook_product("3,000 P");

			dto.setPay_point(3000);
			dto.setFree_point(0);
			dto.setPassbook_free_point(0);
		}else if(passbook_point == 6060) {
			dto.setPassbook_product("6,000 P");

			dto.setPay_point(6000);
			dto.setFree_point(0);
			dto.setPassbook_free_point(60);
		}else if(passbook_point == 18720) {
			dto.setPassbook_product("18,000 P");

			dto.setPay_point(18000);
			dto.setFree_point(180);
			dto.setPassbook_free_point(540);
		}else if(passbook_point == 32100) {
			dto.setPassbook_product("30,000 P");

			dto.setPay_point(30000);
			dto.setFree_point(600);
			dto.setPassbook_free_point(1500);
		}else if(passbook_point == 67800) {
			dto.setPassbook_product("60,000 P");

			dto.setPay_point(60000);
			dto.setFree_point(1800);
			dto.setPassbook_free_point(6000);
		}else if(passbook_point == 140400) {
			dto.setPassbook_product("120,000 P");

			dto.setPay_point(120000);
			dto.setFree_point(6000);
			dto.setPassbook_free_point(14400);
		}

		return passbookService.insertPassBookV3(dto);
	}

	@CrossOrigin(origins= {"http://localhost:8080", "http://allcam.co.kr", "http://pay.onelive.co.kr","http://192.168.10.17:8080"})
	@RequestMapping("/v4/passbook")
	@ResponseBody
	public PassBookResultDTO insertPassBookV4(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
											  @RequestParam(value = "passbook_name", required = !Utils.Debug) String passbook_name,
											  @RequestParam(value = "passbook_phone_number", required = !Utils.Debug) String passbook_phone_number,
											  @RequestParam(value = "passbook_point", required = !Utils.Debug) int passbook_point,
											  @RequestParam(value = "passbook_payment", required = !Utils.Debug) int passbook_payment,
											  @RequestParam(value = "cash_receipts", required = !Utils.Debug) int cash_receipts,
											  @RequestParam(value = "cash_receipts_purpose", required = !Utils.Debug) String cash_receipts_purpose,
											  @RequestParam(value = "cash_receipts_name", required = !Utils.Debug) String cash_receipts_name,
											  @RequestParam(value = "cash_receipts_number", required = !Utils.Debug) String cash_receipts_number,
											  @RequestParam(value = "cash_receipts_phone_nember", required = !Utils.Debug) String cash_receipts_phone_nember,
											  @RequestParam(value = "user_adid", required = !Utils.Debug) String user_adid) {

		PassBookDTO dto = new PassBookDTO();
		dto.setUser_no(user_no);
		dto.setPassbook_name(passbook_name);
		dto.setPassbook_phone_number(passbook_phone_number);
		dto.setPassbook_point(passbook_point);
		dto.setPassbook_payment(passbook_payment);
		dto.setCash_receipts(cash_receipts); //--------------------------------여기까진 무조건 받아야함
		dto.setCash_receipts_purpose(cash_receipts_purpose);
		dto.setCash_receipts_name(cash_receipts_name);
		dto.setCash_receipts_number(cash_receipts_number);
		dto.setCash_receipts_phone_nember(cash_receipts_phone_nember);
		dto.setUser_adid(user_adid);

		if(passbook_point == 3000){
			dto.setPassbook_product("3,000 P");
			dto.setPay_point(3000);
			dto.setFree_point(300);
			dto.setPassbook_free_point(0);
		}else if(passbook_point == 6000) {
			dto.setPassbook_product("6,000 P");

			dto.setPay_point(6000);
			dto.setFree_point(600);
			dto.setPassbook_free_point(0);
		}else if(passbook_point == 18000) {
			dto.setPassbook_product("18,000 P");

			dto.setPay_point(18000);
			dto.setFree_point(1800);
			dto.setPassbook_free_point(0);
		}else if(passbook_point == 30000) {
			dto.setPassbook_product("30,000 P");

			dto.setPay_point(30000);
			dto.setFree_point(3000);
			dto.setPassbook_free_point(0);
		}else if(passbook_point == 60000) {
			dto.setPassbook_product("60,000 P");

			dto.setPay_point(60000);
			dto.setFree_point(6000);
			dto.setPassbook_free_point(0);
		}else if(passbook_point == 120000) {
			dto.setPassbook_product("120,000 P");

			dto.setPay_point(120000);
			dto.setFree_point(12000);
			dto.setPassbook_free_point(0);
		}

		// 다퍼줘 이벤트 종료(2021-01-31)
		if (!checkEventDay("2020-12-21", "2021-01-31")) {
			dto.setFree_point(0);
		}

		return passbookService.insertPassBookV4(dto);
	}

	@CrossOrigin(origins= {"http://localhost:8080", "http://allcam.co.kr", "http://pay.onelive.co.kr","http://192.168.10.17:8080"})
	@RequestMapping("/passbookMC")
	@ResponseBody
	public PassBookResultDTO insertPassBookMC(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
											  @RequestParam(value = "passbook_name", required = !Utils.Debug) String passbook_name,
											  @RequestParam(value = "passbook_phone_number", required = !Utils.Debug) String passbook_phone_number,
											  @RequestParam(value = "product_id", required = !Utils.Debug) String product_id,
											  @RequestParam(value = "cash_receipts", required = !Utils.Debug) int cash_receipts,
											  @RequestParam(value = "cash_receipts_purpose", required = !Utils.Debug) String cash_receipts_purpose,
											  @RequestParam(value = "cash_receipts_name", required = !Utils.Debug) String cash_receipts_name,
											  @RequestParam(value = "cash_receipts_number", required = !Utils.Debug) String cash_receipts_number,
											  @RequestParam(value = "cash_receipts_phone_nember", required = !Utils.Debug) String cash_receipts_phone_nember,
											  @RequestParam(value = "user_adid", required = !Utils.Debug) String user_adid) {

		PassBookDTO dto = new PassBookDTO();
		dto.setUser_no(user_no);
		dto.setPassbook_name(passbook_name);
		dto.setPassbook_phone_number(passbook_phone_number);
		//dto.setPassbook_point(passbook_point);
		//dto.setPassbook_payment(passbook_payment);
		dto.setCash_receipts(cash_receipts); //--------------------------------여기까진 무조건 받아야함
		dto.setCash_receipts_purpose(cash_receipts_purpose);
		dto.setCash_receipts_name(cash_receipts_name);
		dto.setCash_receipts_number(cash_receipts_number);
		dto.setCash_receipts_phone_nember(cash_receipts_phone_nember);
		dto.setUser_adid(user_adid);

		if(product_id.equals(Utils.MC_01)){
			dto.setPassbook_product(Utils.MC_01_NAME);
			dto.setPay_point(Utils.MC_01_POINT);
			dto.setFree_point(0);
			dto.setPassbook_free_point(0);
			dto.setPassbook_point(Utils.MC_01_POINT);
			dto.setPassbook_payment(Utils.MC_01_PRICE);
		}else if(product_id.equals(Utils.MC_02)){
			dto.setPassbook_product(Utils.MC_02_NAME);
			dto.setPay_point(Utils.MC_02_POINT);
			dto.setFree_point(0);
			dto.setPassbook_free_point(0);
			dto.setPassbook_point(Utils.MC_02_POINT);
			dto.setPassbook_payment(Utils.MC_02_PRICE);
		}else if(product_id.equals(Utils.MC_03)){
			dto.setPassbook_product(Utils.MC_03_NAME);
			dto.setPay_point(Utils.MC_03_POINT);
			dto.setFree_point(0);
			dto.setPassbook_free_point(0);
			dto.setPassbook_point(Utils.MC_03_POINT);
			dto.setPassbook_payment(Utils.MC_03_PRICE);
		}else if(product_id.equals(Utils.MC_04)){
			dto.setPassbook_product(Utils.MC_04_NAME);
			dto.setPay_point(Utils.MC_04_POINT);
			dto.setFree_point(0);
			dto.setPassbook_free_point(0);
			dto.setPassbook_point(Utils.MC_04_POINT);
			dto.setPassbook_payment(Utils.MC_04_PRICE);
		}else if(product_id.equals(Utils.MC_05)){
			dto.setPassbook_product(Utils.MC_05_NAME);
			dto.setPay_point(Utils.MC_05_POINT);
			dto.setFree_point(0);
			dto.setPassbook_free_point(0);
			dto.setPassbook_point(Utils.MC_05_POINT);
			dto.setPassbook_payment(Utils.MC_05_PRICE);
		}else if(product_id.equals(Utils.MC_06)){
			dto.setPassbook_product(Utils.MC_06_NAME);
			dto.setPay_point(Utils.MC_06_POINT);
			dto.setFree_point(0);
			dto.setPassbook_free_point(0);
			dto.setPassbook_point(Utils.MC_06_POINT);
			dto.setPassbook_payment(Utils.MC_06_PRICE);
		}

		return passbookService.insertPassBookMC(dto);
	}

	@CrossOrigin(origins= {"http://localhost:8080", "http://allcam.co.kr", "http://pay.onelive.co.kr","http://192.168.10.17:8080"})
	@RequestMapping("/v5/passbook")
	@ResponseBody
	public PassBookResultDTO insertPassBookV5(@RequestParam(value = "user_no", required = !Utils.Debug) int user_no,
											  @RequestParam(value = "passbook_name", required = !Utils.Debug) String passbook_name,
											  @RequestParam(value = "passbook_phone_number", required = !Utils.Debug) String passbook_phone_number,
											  @RequestParam(value = "product_id", required = !Utils.Debug) String product_id,
											  @RequestParam(value = "cash_receipts", required = !Utils.Debug) int cash_receipts,
											  @RequestParam(value = "cash_receipts_purpose", required = !Utils.Debug) String cash_receipts_purpose,
											  @RequestParam(value = "cash_receipts_name", required = !Utils.Debug) String cash_receipts_name,
											  @RequestParam(value = "cash_receipts_number", required = !Utils.Debug) String cash_receipts_number,
											  @RequestParam(value = "cash_receipts_phone_nember", required = !Utils.Debug) String cash_receipts_phone_nember,
											  @RequestParam(value = "user_adid", required = !Utils.Debug) String user_adid) {

		PassBookDTO dto = new PassBookDTO();
		dto.setUser_no(user_no);
		dto.setPassbook_name(passbook_name);
		dto.setPassbook_phone_number(passbook_phone_number);
		//dto.setPassbook_point(passbook_point);
		//dto.setPassbook_payment(passbook_payment);
		dto.setCash_receipts(cash_receipts); //--------------------------------여기까진 무조건 받아야함
		dto.setCash_receipts_purpose(cash_receipts_purpose);
		dto.setCash_receipts_name(cash_receipts_name);
		dto.setCash_receipts_number(cash_receipts_number);
		dto.setCash_receipts_phone_nember(cash_receipts_phone_nember);
		dto.setUser_adid(user_adid);

		for(int i=0;i<Utils.NMC_ID.length;i++){
			if(product_id.equals(Utils.NMC_ID[i])){
				dto.setPassbook_product(Utils.NMC_NAME[i]);
				dto.setPay_point(Utils.NMC_POINT[i]);
				dto.setFree_point(Utils.NMC_FREE_POINT[i]);
				dto.setPassbook_free_point(Utils.NMC_PASSBOOK_FREE_POINT[i]);
				dto.setPassbook_point(Utils.NMC_POINT[i]);
				dto.setPassbook_payment(Utils.NMC_PRICE[i]);
			}
		}

		return passbookService.insertPassBookV5(dto);
	}

	@RequestMapping("/autoPassbookConfirm")
	@ResponseBody
	public DefaultDTO autoPassbookConfirm(@RequestParam(value = "user_name", required = !Utils.Debug) String user_name,
			@RequestParam(value = "user_pay", required = !Utils.Debug) int user_pay) {
		return passbookService.autoPassbookConfirm(user_name, user_pay);
	}

}
