package com.livelyit.allcam.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.springframework.stereotype.Repository;

import com.livelyit.allcam.common.AbstractDAO;
import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.ConversationDTO;

/**
 *  회원가입 시 필요한 대화주제 리스트
 * 
 * @author ㅇㄱ
 *
 */
@Repository("conversationMapper")
public class ConversationMapper extends AbstractDAO{
	
	// 대화주제 리스트 불러오기
	public ArrayList<ConversationDTO> conversationList() {
		return (ArrayList<ConversationDTO>) selectList(Utils.SQL_ALLCAM, "conversationMapper.conversationList");
	}

	// 대화주제 리스트 불러오기 - 직접입력 제외
	public ArrayList<ConversationDTO> conversationListV2(String language_code) {
		return (ArrayList<ConversationDTO>) selectList(Utils.SQL_ALLCAM, "conversationMapper.conversationListV2", language_code);
	}

	// 대화주제 리스트 불러오기 - 직접입력 포함
	public ArrayList<ConversationDTO> conversationListV3(String language_code) {
		return (ArrayList<ConversationDTO>) selectList(Utils.SQL_ALLCAM, "conversationMapper.conversationListV3", language_code);
	}
	
}