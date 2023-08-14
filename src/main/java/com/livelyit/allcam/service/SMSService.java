package com.livelyit.allcam.service;

import com.livelyit.allcam.dto.DefaultDTO;

public interface SMSService {

    public DefaultDTO sendCertNum(String receiverHp);
    public DefaultDTO sendCertNum(String receiverHp, String appHash);
    public DefaultDTO checkCertNum(String receiverHp, String certNum);
}
