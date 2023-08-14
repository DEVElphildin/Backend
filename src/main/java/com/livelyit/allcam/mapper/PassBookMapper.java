package com.livelyit.allcam.mapper;

import java.util.ArrayList;

import com.livelyit.allcam.dto.DefaultDTO;
import org.springframework.stereotype.Repository;

import com.livelyit.allcam.common.AbstractDAO;
import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.AutoPassBookDTO;
import com.livelyit.allcam.dto.AutoPassNoDTO;
import com.livelyit.allcam.dto.PassBookDTO;

@Repository("passbookMapper")
public class PassBookMapper extends AbstractDAO{
	
	public int insertPassBook(PassBookDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "passbookMapper.insertPassBook", dto);
	}
	
	
	public int insertPassBookCashReceipts(PassBookDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "passbookMapper.insertPassBookCashReceipts", dto);
	}

	public int insertPassBookV3(PassBookDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "passbookMapper.insertPassBookV3", dto);
	}

	public int insertPassBookCashReceiptsV3(PassBookDTO dto) {
		return (int) insert(Utils.SQL_ALLCAM, "passbookMapper.insertPassBookCashReceiptsV3", dto);
	}
	
	public ArrayList<PassBookDTO> selectPassbookList() {
		return (ArrayList<PassBookDTO>) selectList(Utils.SQL_ALLCAM, "passbookMapper.selectPassbookList");
	}
	
	public int autoPassbookConfirm(int passNo) {
		return (int) update(Utils.SQL_ALLCAM, "passbookMapper.autoPassbookConfirm", passNo);
	}

	public AutoPassBookDTO autoPassbookConfirmV2(AutoPassBookDTO autoDto) {
		return (AutoPassBookDTO) selectOne(Utils.SQL_ALLCAM, "passbookMapper.autoPassbookConfirmV2", autoDto);	}

	public AutoPassBookDTO autoEventPassbookConfirmV2(AutoPassBookDTO autoDto) {
		return (AutoPassBookDTO) selectOne(Utils.SQL_ALLCAM, "passbookMapper.autoEventPassbookConfirmV2", autoDto);	}

	public AutoPassNoDTO autoPassbookChk(AutoPassBookDTO dto) {
		return (AutoPassNoDTO) selectOne(Utils.SQL_ALLCAM, "passbookMapper.autoPassbookChk", dto);
	}

	public int getUserNoPassBook(AutoPassBookDTO dto) {
		return (int) selectOne(Utils.SQL_ALLCAM, "passbookMapper.getUserNoPassBook", dto);
	}

}