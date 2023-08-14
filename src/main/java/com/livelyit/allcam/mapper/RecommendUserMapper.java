package com.livelyit.allcam.mapper;

import java.util.ArrayList;

import com.livelyit.allcam.dto.MainLiveCallDTO;
import org.springframework.stereotype.Repository;

import com.livelyit.allcam.common.AbstractDAO;
import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.RecommendUserDTO;

/**
 * 추천목록 mapper
 * @author ㅇㄱ
 *
 */
@Repository("recommendMapper")
public class RecommendUserMapper extends AbstractDAO{	

	// 추천목록 리스트
	public ArrayList<RecommendUserDTO> selectRecommendList(int user_no) {
		return (ArrayList<RecommendUserDTO>) selectList(Utils.SQL_ALLCAM, "recommendMapper.selectRecommendList", user_no);
	}

	public ArrayList<RecommendUserDTO> selectRecommendListV2(MainLiveCallDTO dto) {
		return (ArrayList<RecommendUserDTO>) selectList(Utils.SQL_ALLCAM, "recommendMapper.selectRecommendListV2", dto);
	}

	public ArrayList<RecommendUserDTO> recommendListNonMember() {
		return (ArrayList<RecommendUserDTO>) selectList(Utils.SQL_ALLCAM, "recommendMapper.recommendListNonMember");
	}

	public ArrayList<RecommendUserDTO> selectRecommendListV3(MainLiveCallDTO dto) {
		return (ArrayList<RecommendUserDTO>) selectList(Utils.SQL_ALLCAM, "recommendMapper.selectRecommendListV3", dto);
	}

	// 비회원을 위한 추천 목록
	public ArrayList<RecommendUserDTO> selectNonmenberRecommendList() {
		return (ArrayList<RecommendUserDTO>) selectList(Utils.SQL_ALLCAM, "recommendMapper.selectNonmenberRecommendList");
	}

	public ArrayList<RecommendUserDTO> selectRecommendListMC(MainLiveCallDTO dto) {
		return (ArrayList<RecommendUserDTO>) selectList(Utils.SQL_ALLCAM, "recommendMapper.selectRecommendListMC", dto);
	}
}