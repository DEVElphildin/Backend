package com.livelyit.allcam.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.dto.DefaultDTO;
import com.livelyit.allcam.service.LiveCallService;

// import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;


@RestController
public class TestController {
    @Autowired
    LiveCallService liveCallService;

    @RequestMapping("/")
    @ResponseBody
    public String index(){
        //String plainCredentials = "0513032ff759420ea313ac54911d7d17:5afff687768147fabba63ce24f0fc96c";
        //String base64Credentials = new String(Base64.encodeBase64(plainCredentials.getBytes()));
        return "{'result':'FAIL'}";
    }


   /*
    @RequestMapping("/test/cashTest")
    @ResponseBody
    public DefaultDTO cashTest(){

        return liveCallService.getCashByCall(97, "37_1591063245957", 110, 97);

    }

    @RequestMapping("/test/pointTest")
    @ResponseBody
    public DefaultDTO pointTest(){

        return liveCallService.payPointByCall(37, "97_1591953887318", 0,1800, 57);

    }

    */


    /*@RequestMapping("/test/ttt")
	@ResponseBody
	public DefaultDTO getUserInfo(){
//		System.out.println(getAccessToken());
		return Utils.defaultResult(new DefaultDTO(), 1);
	}
	
	public String getAccessToken() {
        String result = "";

        URL url = null;
        try {
//            url = new URL("https://apis.onestore.co.kr/v2/oauth/token");	//상용
            url = new URL("https://sbpp.onestore.co.kr/v2/oauth/token");	//테스트
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setDoOutput(true);
            http.setRequestProperty("Accept", "application/json");
            http.setRequestMethod("POST");
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            OutputStreamWriter out_stream = new OutputStreamWriter(http.getOutputStream());
            out_stream.write("grant_type=client_credentials&client_id=kr.allcam&client_secret=sqw4TACB0of6rd8QMsn9XEtiMJ6fA6yPX0r0CttiMOk=");
            out_stream.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(http.getInputStream()));
            StringBuffer buffer = new StringBuffer();

            String c;
            while ((c = in.readLine()) != null) {
                buffer.append(c);
            }
            result = buffer.toString().trim();
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result.trim();
    }*/
}