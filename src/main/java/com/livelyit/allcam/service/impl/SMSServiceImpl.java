package com.livelyit.allcam.service.impl;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.DefaultDTO;
import com.livelyit.allcam.dto.SMSCertNumDTO;
import com.livelyit.allcam.mapper.SMSMapper;
import com.livelyit.allcam.service.SMSService;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.HttpClient;
import org.apache.http.HttpEntity;

import java.net.URLEncoder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.content.FileBody;
import java.io.File;
import java.util.*;
import java.nio.charset.Charset;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

@Service
public class SMSServiceImpl  implements SMSService {
    @Autowired
    SMSMapper smsMapper;

    @Override
    public DefaultDTO sendCertNum(String receiverHp) {
        DefaultDTO returnDto = new DefaultDTO();
        returnDto.setResult(Utils.FAIL);
        returnDto.setReason("발송 실패");
        try{
            final String encodingType = "utf-8";
            final String boundary = "____boundary____";
            String sms_url = "https://apis.aligo.in/send/"; // 전송요청 URL

            Map<String, String> sms = new HashMap<String, String>();

            sms.put("user_id", Utils.SMS_ALIGOAPI_ID); // SMS 아이디
            sms.put("key", Utils.SMS_ALIGOAPI_KEY); //인증키

            /******************** 인증정보 ********************/
            String certNum = RandomStringUtils.randomNumeric(6);
            SMSCertNumDTO smsDto = new SMSCertNumDTO();
            receiverHp = receiverHp.replaceAll("[^0-9]", "");
            smsDto.setReceiver_hp(receiverHp);
            smsDto.setCert_num(certNum);

            //db 저장
            smsMapper.insertCertNum(smsDto);

            /******************** 전송정보 ********************/
            sms.put("msg", "[#][엘필딘] 본인인증번호\n["+certNum+"]를 입력해주세요.\n9Y+GLgUSX3N"); // 메세지 내용
            sms.put("receiver", receiverHp+""); // 수신번호
            sms.put("destination", ""); // 수신인 %고객명% 치환
            sms.put("sender", Utils.SMS_ALIGOAPI_SENDER+""); // 발신번호
            sms.put("rdate", ""); // 예약일자 - 20161004 : 2016-10-04일기준
            sms.put("rtime", ""); // 예약시간 - 1930 : 오후 7시30분
            sms.put("testmode_yn", "N"); // Y 인경우 실제문자 전송X , 자동취소(환불) 처리
            sms.put("title", ""); //  LMS, MMS 제목 (미입력시 본문중 44Byte 또는 엔터 구분자 첫라인)

            String image = "";
            //image = "/tmp/pic_57f358af08cf7_sms_.jpg"; // MMS 이미지 파일 위치

            /******************** 전송정보 ********************/

            MultipartEntityBuilder builder = MultipartEntityBuilder.create();

            builder.setBoundary(boundary);
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
            builder.setCharset(Charset.forName(encodingType));

            for(Iterator<String> i = sms.keySet().iterator(); i.hasNext();){
                String key = i.next();
                builder.addTextBody(key, sms.get(key)
                        , ContentType.create("Multipart/related", encodingType));
            }

            File imageFile = new File(image);
            if(image!=null && image.length()>0 && imageFile.exists()){

                builder.addPart("image",
                        new FileBody(imageFile, ContentType.create("application/octet-stream"),
                                URLEncoder.encode(imageFile.getName(), encodingType)));
            }

            HttpEntity entity = builder.build();

            HttpClient client = HttpClients.createDefault();
            HttpPost post = new HttpPost(sms_url);
            post.setEntity(entity);

            HttpResponse res = client.execute(post);

            String result = "";
            if(res != null){
                BufferedReader in = new BufferedReader(new InputStreamReader(res.getEntity().getContent(), encodingType));
                String buffer = null;
                while((buffer = in.readLine())!=null){
                    result += buffer;
                }
                in.close();
            }

            System.out.print(result);
            org.json.JSONObject sendSMSResultJson = new org.json.JSONObject(result);
            if(1 == sendSMSResultJson.getInt("result_code")) {

                returnDto.setResult(Utils.SUCCESS);
                returnDto.setReason("발송 성공");

            }
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
        return returnDto;
    }

    @Override
    public DefaultDTO sendCertNum(String receiverHp, String appHash) {
        DefaultDTO returnDto = new DefaultDTO();
        returnDto.setResult(Utils.FAIL);
        returnDto.setReason("발송 실패");
        if(receiverHp.equals("010-8255-8796") 
            || receiverHp.equals("010-5038-6314")
            || receiverHp.equals("010-2239-2647")
            || receiverHp.equals("010-9916-4447")){//블랙리스트
            return returnDto;
        }

        List<String> appHashList = new ArrayList<>();
        appHashList.add("9Y+GLgUSX3N"); //올캠(구글)
        appHashList.add("Ys0sSk9uMFA"); //와우캠
        appHashList.add("NWC77H2S4Tl"); //탑톡
        appHashList.add("BNpArGe2ARE"); //원라이
        appHashList.add("uzL0PJB8PqD"); //올캠(워스토어)

        if (appHashList.contains(appHash) || !appHashList.contains(appHash)) {
            try {
                final String encodingType = "utf-8";
                final String boundary = "____boundary____";
                String sms_url = "https://apis.aligo.in/send/"; // 전송요청 URL

                Map<String, String> sms = new HashMap<String, String>();

                sms.put("user_id", Utils.SMS_ALIGOAPI_ID); // SMS 아이디
                sms.put("key", Utils.SMS_ALIGOAPI_KEY); //인증키

                /******************** 인증정보 ********************/
                String certNum = RandomStringUtils.randomNumeric(6);
                SMSCertNumDTO smsDto = new SMSCertNumDTO();
                receiverHp = receiverHp.replaceAll("[^0-9]", "");
                smsDto.setReceiver_hp(receiverHp);
                smsDto.setCert_num(certNum);

                //db 저장
                smsMapper.insertCertNum(smsDto);

                /******************** 전송정보 ********************/
                sms.put("msg", "[#][엘필딘] 본인인증번호\n[" + certNum + "]를 입력해주세요.\n" + appHash); // 메세지 내용
                sms.put("receiver", receiverHp + ""); // 수신번호
                sms.put("destination", ""); // 수신인 %고객명% 치환
                sms.put("sender", Utils.SMS_ALIGOAPI_SENDER + ""); // 발신번호
                sms.put("rdate", ""); // 예약일자 - 20161004 : 2016-10-04일기준
                sms.put("rtime", ""); // 예약시간 - 1930 : 오후 7시30분
                sms.put("testmode_yn", "N"); // Y 인경우 실제문자 전송X , 자동취소(환불) 처리
                sms.put("title", ""); //  LMS, MMS 제목 (미입력시 본문중 44Byte 또는 엔터 구분자 첫라인)

                String image = "";
                //image = "/tmp/pic_57f358af08cf7_sms_.jpg"; // MMS 이미지 파일 위치

                /******************** 전송정보 ********************/

                MultipartEntityBuilder builder = MultipartEntityBuilder.create();

                builder.setBoundary(boundary);
                builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
                builder.setCharset(Charset.forName(encodingType));

                for (Iterator<String> i = sms.keySet().iterator(); i.hasNext(); ) {
                    String key = i.next();
                    builder.addTextBody(key, sms.get(key)
                            , ContentType.create("Multipart/related", encodingType));
                }

                File imageFile = new File(image);
                if (image != null && image.length() > 0 && imageFile.exists()) {

                    builder.addPart("image",
                            new FileBody(imageFile, ContentType.create("application/octet-stream"),
                                    URLEncoder.encode(imageFile.getName(), encodingType)));
                }

                HttpEntity entity = builder.build();

                HttpClient client = HttpClients.createDefault();
                HttpPost post = new HttpPost(sms_url);
                post.setEntity(entity);

                HttpResponse res = client.execute(post);

                String result = "";
                if (res != null) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(res.getEntity().getContent(), encodingType));
                    String buffer = null;
                    while ((buffer = in.readLine()) != null) {
                        result += buffer;
                    }
                    in.close();
                }

                System.out.print(result);
                org.json.JSONObject sendSMSResultJson = new org.json.JSONObject(result);
                if (1 == sendSMSResultJson.getInt("result_code")) {

                    returnDto.setResult(Utils.SUCCESS);
                    returnDto.setReason("발송 성공");

                }
            } catch (Exception e) {
                System.out.print(e.getMessage());
            }
        }else{
            returnDto.setReason("해시 오류"+appHash);
        }
        return returnDto;
    }

    @Override
    public DefaultDTO checkCertNum(String receiverHp, String certNum) {
        DefaultDTO returnDto = new DefaultDTO();
        returnDto.setResult(Utils.FAIL);
        returnDto.setReason("인증 실패");

        SMSCertNumDTO smsDto = new SMSCertNumDTO();
        receiverHp = receiverHp.replaceAll("[^0-9]", "");
        smsDto.setReceiver_hp(receiverHp);
        smsDto.setCert_num(certNum);

        int result = smsMapper.checkCertNum(smsDto);

        if (result > 0) {
            returnDto.setResult(Utils.SUCCESS);
            returnDto.setNo(result);
            returnDto.setReason("인증 성공");
        }
        return returnDto;
    }
}
