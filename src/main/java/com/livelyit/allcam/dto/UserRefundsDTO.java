package com.livelyit.allcam.dto;

import lombok.Data;

/**
 * 환전 신청 시 들어올 정보
 * @author ㅇㄱ
 *
 */
@Data
public class UserRefundsDTO  extends DefaultDTO{
	UserRefundsUpdateDTO dto;	//환급정보 업데이트
	UserRefundsHistoryListDTO hisDto;	//히스토리 등록	
}
