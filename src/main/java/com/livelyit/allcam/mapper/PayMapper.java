package com.livelyit.allcam.mapper;

import com.livelyit.allcam.dto.*;
import org.springframework.stereotype.Repository;

import com.livelyit.allcam.common.AbstractDAO;
import com.livelyit.allcam.common.Utils;

import java.util.ArrayList;

@Repository("payMapper")
public class PayMapper extends AbstractDAO {
	public int insertPay(PayDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "payMapper.insertPay", dto);
	}

	public int insertPayInApp(PayDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "payMapper.insertPayInApp", dto);
	}


	public int updatePayStatus(PayDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "payMapper.updatePayStatus", dto);
	}

	public int updatePayStatusInApp(PayDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "payMapper.updatePayStatusInApp", dto);
	}

	public int updatePayEventStatus(PayDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "payMapper.updatePayEventStatus", dto);
	}

	public int updatePayEventStatusInApp(PayDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "payMapper.updatePayEventStatusInApp", dto);
	}

	public int updatePayNotAdIdInApp(PayDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "payMapper.updatePayNotAdIdInApp", dto);
	}

	public int getPayListCount(int user_no){
		return (int) selectOne(Utils.SQL_ALLCAM, "payMapper.getPayListCount", user_no);
	}
	
	public int updatePayOneStore(OneStoreDTO dto) {
		return (int) update(Utils.SQL_ALLCAM, "payMapper.updatePayOneStore", dto);
	}
	
	public PayResultDTO getPayResult(int pay_no) {
		return (PayResultDTO) selectOne(Utils.SQL_ALLCAM, "payMapper.getPayResult", pay_no);
	}

	public int getPayIdInApp(PayDTO payDTO) {
		return  (int)  selectOne(Utils.SQL_ALLCAM, "payMapper.getPayIdInApp", payDTO);
	}

	public ArrayList<ProductDTO> getInAppProductList() {
		return (ArrayList<ProductDTO>)  selectList(Utils.SQL_ALLCAM, "payMapper.getInAppProductList");
	}

	public ArrayList<ProductDTO> getInAppProductListMC() {
		return (ArrayList<ProductDTO>)  selectList(Utils.SQL_ALLCAM, "payMapper.getInAppProductListMC");
	}

	public ArrayList<ProductDTO> getInAppProductListNMC() {
		return (ArrayList<ProductDTO>)  selectList(Utils.SQL_ALLCAM, "payMapper.getInAppProductListNMC");
	}

	public int insertPayList(AddPayListDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "payMapper.insertPayList", dto);
	}

	public int insertIosReceipt(String body) {
		return (int) insert(Utils.SQL_ALLCAM, "payMapper.insertIosReceipt", body);
	}

	public String confirmRefund(String trans_id){
		return (String) selectOne(Utils.SQL_ALLCAM, "payMapper.confirmRefund", trans_id);
	}

}