package com.livelyit.allcam.dto;

import java.util.ArrayList;

import lombok.Data;

/**
 * 환전 내역 리스트
 * @author ㅇㄱ
 *
 */
@Data
public class UserRefundsHistoryDTO  extends DefaultDTO{
	ArrayList<UserRefundsHistoryListDTO> userRefundsHistoryList;
	
}
