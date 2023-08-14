package com.livelyit.allcam.mapper;

import com.livelyit.allcam.dto.SMSCertNumDTO;
import org.springframework.stereotype.Repository;

import com.livelyit.allcam.common.AbstractDAO;
import com.livelyit.allcam.common.Utils;

@Repository("smsMapper")
public class SMSMapper extends AbstractDAO{	
	public int insertCertNum(SMSCertNumDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "smsMapper.insertCertNum", dto);
	}

	public int checkCertNum(SMSCertNumDTO dto) {
		return (int) selectOne(Utils.SQL_ALLCAM, "smsMapper.checkCertNum", dto);
	}
}