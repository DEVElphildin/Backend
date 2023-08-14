package com.livelyit.allcam.controller;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.DefaultDTO;
import com.livelyit.allcam.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/sms")
public class SMSController {
    @Autowired
    SMSService smsService;

    // sms 발송 - 올캠 appHash 사용으로 변경후 삭제 예정
    @RequestMapping("/sendCertNum")
    @ResponseBody
    public DefaultDTO sendCertNum(@RequestParam(value = "receiverHp", required = !Utils.Debug) String receiverHp) {
        return smsService.sendCertNum(receiverHp);
    }

    // sms 발송
    @RequestMapping(value = "/sendCertNum", params = {"receiverHp", "appHash"})
    @ResponseBody
    public DefaultDTO sendCertNum(@RequestParam(value = "receiverHp", required = !Utils.Debug) String receiverHp,
                                  @RequestParam(value = "appHash", required = !Utils.Debug) String appHash) {
        return smsService.sendCertNum(receiverHp,appHash);
    }

    // sms 인증번호 일치여부 확인
    @RequestMapping("/checkCertNum")
    @ResponseBody
    public DefaultDTO checkCertNum(@RequestParam(value = "receiverHp", required = !Utils.Debug) String receiverHp,
                                      @RequestParam(value = "certNum", required = !Utils.Debug) String certNum) {
        return smsService.checkCertNum(receiverHp, certNum);
    }
}
