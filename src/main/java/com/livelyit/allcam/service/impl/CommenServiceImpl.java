package com.livelyit.allcam.service.impl;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import com.livelyit.allcam.dto.DefaultDTO;
import com.livelyit.allcam.dto.LoginDTO;
import com.livelyit.allcam.mapper.UserMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.livelyit.allcam.common.Utils;
import com.livelyit.allcam.service.CommonService;

import static com.livelyit.allcam.common.Utils.API_LOG_PATH;

@Service
public class CommenServiceImpl implements CommonService {
	@Autowired
	UserMapper userMapper;

	@Override
	public String imgUpload(String upType, String originalFilename, byte[] bytes) {
		String savePath = Utils.IMAGE_PATH + "temp/"+upType+"/";
		
		File creDirectory = new File(savePath);
		if(!creDirectory.exists()){
			creDirectory.mkdirs();
		}

		JSONObject resultJson = new JSONObject();
		JSONArray resultArray = new JSONArray();
		
		JSONObject imgJson = new JSONObject();
		imgJson.put("upType", upType);
		
		try {
            Path path = Paths.get(savePath + originalFilename);
            Files.write(path, bytes);
            
            File imgFile = new File(savePath + originalFilename);
            
            if(imgFile != null){
				String fileName = imgFile.getName();
				long fileSize = imgFile.length();
				
				String mimeType = fileName.substring(fileName.lastIndexOf("."),fileName.length());
				
				Date date = new Date();
				String changeFileName = date.getTime()+mimeType;
				
				File oriFile = new File(savePath+"/"+fileName);			
				File renameFile = new File(savePath+"/"+changeFileName);
				oriFile.renameTo(renameFile);
				
				BufferedImage rImg = null;

				if(mimeType.equals(".jpg") || mimeType.equals(".jpeg")){
				    Iterator readers = ImageIO.getImageReadersByFormatName("JPEG");			    
				    ImageReader reader = null;
				    while(readers.hasNext()) {
				        reader = (ImageReader)readers.next();
				        if(reader.canReadRaster()) {
				            break;
				        }
				    }
				    
				    ImageInputStream input = ImageIO.createImageInputStream(renameFile); 
				    reader.setInput(input); 
				    
				    Raster raster = reader.readRaster(0, null);			    
				    rImg = new BufferedImage(raster.getWidth(), raster.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);
				    
				    reader.dispose();
				    input.close();
				}else{
					rImg = ImageIO.read(renameFile);
				}
				imgJson.put("oriFileName", fileName);
				imgJson.put("changeFileName", changeFileName);
				imgJson.put("fileSize", fileSize);
				imgJson.put("imgWidth", rImg.getWidth());
				imgJson.put("imgHeight", rImg.getHeight());
				
				rImg.flush();
			}
			resultArray.add(imgJson);
			resultJson.put("result", "success");
			resultJson.put("imgArray", resultArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
		return resultJson.toString();
	}

	@Override
	public String imgUploadV2(String upType, String originalFilename, byte[] bytes, int user_no) {
		String savePath = Utils.IMAGE_PATH + "temp/"+upType+"/";

		/* 프로필 사진 업로드 에러 로그를 파일로 저장하는 코드 */
		PrintStream ps = null;
		FileOutputStream fos=null;
		Date today = new Date();

		SimpleDateFormat _date = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss a");
		String dateStr = _date.format(today);

		try {
			fos = new FileOutputStream(API_LOG_PATH+"image_upload_"+dateStr+".log",true); // error.log파일에 출력 준비
			ps=new PrintStream(fos); // err의 출력을 화면이 아닌, error.log파일로 변경
			System.setErr(ps);

			System.err.println("-----------------------------------");
			System.err.println("user_no  : " + user_no);
		} catch (FileNotFoundException fileNotFoundException) {
			fileNotFoundException.printStackTrace();
		}


		// 이미지를 저장할 임시 폴더 만든다.
		File creDirectory = new File(savePath);
		if(!creDirectory.exists()){
			creDirectory.mkdirs();
		}

		JSONObject resultJson = new JSONObject();
		JSONArray resultArray = new JSONArray();

		JSONObject imgJson = new JSONObject();
		imgJson.put("upType", upType);

		String changeFileName = "";
		try {
			Path path = Paths.get(savePath + originalFilename);
			Files.write(path, bytes);

			File imgFile = new File(savePath + originalFilename);

			if(imgFile != null){
				String fileName = imgFile.getName();
				long fileSize = imgFile.length();

				String mimeType = fileName.substring(fileName.lastIndexOf("."),fileName.length());

				Date date = new Date();
				changeFileName = date.getTime()+mimeType;

				File oriFile = new File(savePath+"/"+fileName);
				File renameFile = new File(savePath+"/"+changeFileName);
				oriFile.renameTo(renameFile);

				BufferedImage rImg = null;

				if(mimeType.equals(".jpg") || mimeType.equals(".jpeg")){
					Iterator readers = ImageIO.getImageReadersByFormatName("JPEG");
					ImageReader reader = null;
					while(readers.hasNext()) {
						reader = (ImageReader)readers.next();
						if(reader.canReadRaster()) {
							break;
						}
					}

					ImageInputStream input = ImageIO.createImageInputStream(renameFile);
					reader.setInput(input);

					Raster raster = reader.readRaster(0, null);
					rImg = new BufferedImage(raster.getWidth(), raster.getHeight(),BufferedImage.TYPE_4BYTE_ABGR);

					reader.dispose();
					input.close();
				}else{
					rImg = ImageIO.read(renameFile);
				}
				imgJson.put("oriFileName", fileName);
				imgJson.put("changeFileName", changeFileName);
				imgJson.put("fileSize", fileSize);
				imgJson.put("imgWidth", rImg.getWidth());
				imgJson.put("imgHeight", rImg.getHeight());

				rImg.flush();
			}
			resultArray.add(imgJson);
			resultJson.put("result", Utils.SUCCESS);
			resultJson.put("imgArray", resultArray);
		} catch (IOException e) {
			System.err.println("파일 생성 에러");
			System.err.println("파일 생성 에러 : "+e.getMessage());
			System.err.println("파일 생성 에러 : "+e.getStackTrace());
			e.printStackTrace();
		}

		//프로필 승인 대기중인 이미지가 있는지 확인
		int cnt = userMapper.selectUserProfileImg(user_no);

		if(cnt > 0) {
			userMapper.deleteProfileImg(user_no);
		}

		try {
			if(fos != null)
				fos.close();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}

		LoginDTO ldto = new LoginDTO();
		ldto.setUser_thumnail(changeFileName);
		ldto.setNo(user_no);
		userMapper.userThumnailInsert(ldto);

		return resultJson.toString();
	}


}