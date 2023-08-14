package com.livelyit.allcam.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.VersionDTO;
import com.livelyit.allcam.dto.VersionResultDTO;
import com.livelyit.allcam.dto.VersionResultV2DTO;
import com.livelyit.allcam.mapper.VersionMapper;
import com.livelyit.allcam.service.VersionService;

@Service
public class VersionServiceImpl implements VersionService {
    @Autowired
    VersionMapper versionMapper;

    // 저장된 최신 업데이트 버전을 가져온다.
    @Override
    public VersionResultV2DTO versionCheckV2(VersionDTO dto) {
        VersionResultV2DTO resultdto = new VersionResultV2DTO();
        resultdto.setVersionInfo(versionMapper.VersionCheckV2(dto));
        resultdto.setBanner_url(versionMapper.getMainBannerV2());
        resultdto.setPay_url_mobile("https://allcam.co.kr/AllcamPay/welPayMobile_onestore.jsp");
        resultdto.setPay_url_card("https://allcam.co.kr/AllcamPay/approval_onestore.jsp");
        resultdto.setResult(Utils.SUCCESS);

        return resultdto;
    }

}
