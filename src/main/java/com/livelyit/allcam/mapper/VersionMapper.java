package com.livelyit.allcam.mapper;

import java.util.ArrayList;

import com.livelyit.allcam.dto.LoginDTO;
import org.springframework.stereotype.Repository;

import com.livelyit.allcam.common.AbstractDAO;
import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.BanTextDTO;
import com.livelyit.allcam.dto.MainBannerDTO;
import com.livelyit.allcam.dto.VersionDTO;

@Repository("versionMapper")
public class VersionMapper extends AbstractDAO{
	public  VersionDTO VersionCheckV2(VersionDTO dto) {
		return (VersionDTO) selectOne(Utils.SQL_ALLCAM, "versionMapper.versionCheckV2", dto ) ;
	}

	public ArrayList<BanTextDTO> getBanText(String ban_type) {
		return (ArrayList<BanTextDTO>) selectList(Utils.SQL_ALLCAM, "versionMapper.getBanText", ban_type);
	}

	public ArrayList<MainBannerDTO> getMainBannerV2() {
		return (ArrayList<MainBannerDTO>) selectList(Utils.SQL_ALLCAM, "versionMapper.getMainBannerV2");
	}

	public ArrayList<MainBannerDTO> getMainBannerV3(LoginDTO loginDTO) {
		return (ArrayList<MainBannerDTO>) selectList(Utils.SQL_ALLCAM, "versionMapper.getMainBannerV3", loginDTO);
	}

	public String getMainBanner() {
		return (String) selectOne(Utils.SQL_ALLCAM, "versionMapper.getMainBanner");
	}

}