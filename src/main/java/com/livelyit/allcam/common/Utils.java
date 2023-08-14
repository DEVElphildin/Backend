package com.livelyit.allcam.common;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletRequest;

import com.livelyit.allcam.dto.SMSCertNumDTO;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.HttpClients;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.livelyit.allcam.dto.BanTextDTO;
import com.livelyit.allcam.dto.DefaultDTO;

public class Utils {
	final public static boolean Debug = false;	//릴리즈
//	final public static boolean Debug = true;	//디버그
	
	final public static String SUCCESS = "SUCCESS";
	final public static String FAIL = "FAIL";
	final public static String REASON_DB = "DB";

	final public static String IMAGE_PATH = Debug ? "C:/source/apache-tomcat-9/webapps/imageServer/":"C:/livelyit/apache-tomcat-9/webapps/imageServer/";
	final public static String IMAGE_URL = "http://allcam.co.kr/imageServer";
	final public static int IMAGE_ORIGINAL = 0;
	final public static int IMAGE_THUMNAIL = 1;
	final public static int IMAGE_VIEW = 2;

	final public static String API_LOG_PATH = "C:/livelyit/log/allcam_server/";
	final public static int SQL_ALLCAM = 0;
	final public static int SQL_SMS = 1;
	final public static int ANDROID_INAPP_BILLING_OK = 0;
	final public static String IN_APP_ANDROID = "inapp_android";

	// final public static String SMS_ALIGOAPI_ID = "livelyit";
	// final public static String SMS_ALIGOAPI_KEY = "syhb8my0baqpzlabr6fvnmecefzocg07";
	// final public static String SMS_ALIGOAPI_SENDER = "0220850511"; //발신번호 등 2021.02.02	발랄한아이티

	final public static String SMS_ALIGOAPI_ID = "devmeincam";
	final public static String SMS_ALIGOAPI_KEY = "57vyi3lurqic49nqdexp789zhcpot9ur";
	final public static String SMS_ALIGOAPI_SENDER = "01023078280"; //발신번호 등 2022.11.22 엘필딘

	// 상품 고유 아이디
	final public static String PD_0001 = "pd_0001";
	final public static String PD_0002 = "pd_0002";
	final public static String PD_0003 = "pd_0003";
	final public static String PD_0004 = "pd_0004";
	final public static String PD_0005 = "pd_0005";
	final public static String PD_0006 = "pd_0006";

	// 상품 이름
	final public static String PD_0001_NAME = "3,000 P";
	final public static String PD_0002_NAME = "6,000 P";
	final public static String PD_0003_NAME = "18,000 P";
	final public static String PD_0004_NAME = "30,000 P";
	final public static String PD_0005_NAME = "60,000 P";
	final public static String PD_0006_NAME = "120,000 P";

	// 상품 가격 (원화-ios와 다름)
	final public static int PD_0001_PRICE = 5500;
	final public static int PD_0002_PRICE = 11000;
	final public static int PD_0003_PRICE = 33000;
	final public static int PD_0004_PRICE = 55000;
	final public static int PD_0005_PRICE = 110000;
	final public static int PD_0006_PRICE = 220000;

	// IOS 상품 가격
	final public static int PD_0001_PRICE_IOS = 5900;
	final public static int PD_0002_PRICE_IOS = 11000;
	final public static int PD_0003_PRICE_IOS = 33000;
	final public static int PD_0004_PRICE_IOS = 55000;
	final public static int PD_0005_PRICE_IOS = 109000;
	final public static int PD_0006_PRICE_IOS = 219000;

	// 상품 총 포인트
	final public static int PD_0001_POINT = 3000;
	final public static int PD_0002_POINT = 6000;
	final public static int PD_0003_POINT = 18000;
	final public static int PD_0004_POINT = 30000;
	final public static int PD_0005_POINT = 60000;
	final public static int PD_0006_POINT = 120000;

	// 상품 무료 포인트(다퍼줘 이벤트 2020-12-21~2021-01-31)
	final public static int PD_0001_FREE_POINT = 300;
	final public static int PD_0002_FREE_POINT = 600;
	final public static int PD_0003_FREE_POINT = 1800;
	final public static int PD_0004_FREE_POINT = 3000;
	final public static int PD_0005_FREE_POINT = 6000;
	final public static int PD_0006_FREE_POINT = 12000;

	// 첫결제 이벤트 관련(2021-02-01~2021-02-28)
	final public static String EVENT_DATE_START = "2021-02-01";
	final public static String EVENT_DATE_END = "2021-02-28";
	final public static int PD_0001_EVENT_POINT = 600;
	final public static int PD_0002_EVENT_POINT = 1200;
	final public static int PD_0003_EVENT_POINT = 3600;
	final public static int PD_0004_EVENT_POINT = 6000;
	final public static int PD_0005_EVENT_POINT = 12000;
	final public static int PD_0006_EVENT_POINT = 24000;

	final public static int MINIMUM_PAYBACK = 30000;

	final public static int CASH_PER_SECONDS = 10;
	final public static int POINT_PER_SECONDS = 30;
	final public static int POINT_PER_MIN = POINT_PER_SECONDS*60;

	final public static int VOICE_CASH_PER_SECONDS = 8;
	final public static int VOICE_POINT_PER_SECONDS = 10;
	final public static int VOICE_POINT_PER_MIN = VOICE_POINT_PER_SECONDS *60;

	// ------------- s:미인캠 관련 상수
	final public static double MC_PRESENT_TO_CASH_RATE = 0.3; //선물 (포인트->캐시 환전 비율)
	final public static int MC_FIRST_CHAT_POINT = 50; //첫 채팅 발송 비용
	final public static int MC_CASH_PER_SECONDS = 10;	//통화 적립 캐시 단위
	final public static int MC_POINT_PER_SECONDS = 30;  //통화 차감 포인트 단위
	final public static int MC_POINT_PER_MIN = MC_POINT_PER_SECONDS*60; //통화 차감 포인트 분당 단

	// 상품 고유 아이디
	final public static String MC_01 = "mc_01";
	final public static String MC_02 = "mc_02";
	final public static String MC_03 = "mc_03";
	final public static String MC_04 = "mc_04";
	final public static String MC_05 = "mc_05";
	final public static String MC_06 = "mc_06";

	// 상품 총 포인트
	final public static int MC_01_POINT = 3000;
	final public static int MC_02_POINT = 6000;
	final public static int MC_03_POINT = 18000;
	final public static int MC_04_POINT = 30000;
	final public static int MC_05_POINT = 60000;
	final public static int MC_06_POINT = 150000;

	// 상품 이름
	final public static String MC_01_NAME = "3,000 P";
	final public static String MC_02_NAME = "6,000 P";
	final public static String MC_03_NAME = "18,000 P";
	final public static String MC_04_NAME = "30,000 P";
	final public static String MC_05_NAME = "60,000 P";
	final public static String MC_06_NAME = "150,000 P";

	// 상품 가격 (원화-ios와 다름)
	final public static int MC_01_PRICE = 5000;
	final public static int MC_02_PRICE = 10000;
	final public static int MC_03_PRICE = 30000;
	final public static int MC_04_PRICE = 48000;
	final public static int MC_05_PRICE = 95000;
	final public static int MC_06_PRICE = 230000;

	// IOS 상품 가격
	final public static int MC_01_PRICE_IOS = 4900;
	final public static int MC_02_PRICE_IOS = 11000;
	final public static int MC_03_PRICE_IOS = 30000;
	final public static int MC_04_PRICE_IOS = 48000;
	final public static int MC_05_PRICE_IOS = 95000;
	final public static int MC_06_PRICE_IOS = 229000;
	// ------------- e:미인캠 관련 상수


	// ------------- s:2022 리뉴얼 상수
	final public  static int NMC_NICK_EDIT_POINT = 10;
	final public static double NMC_PRESENT_TO_CASH_RATE = 0.3; //선물 (포인트->캐시 환전 비율)
	final public static int NMC_FIRST_CHAT_POINT = 30; //첫 채팅 발송 비용
	final public static int NMC_CASH_PER_SECONDS = 10;	//통화 적립 캐시 단위
	final public static int NMC_POINT_PER_SECONDS = 30;  //통화 차감 포인트 단위
	final public static int NMC_POINT_PER_MIN = NMC_POINT_PER_SECONDS*60; //통화 차감 포인트 분당 포인트

	final public static String[] NMC_ID = {"nmc_01", "nmc_02", "nmc_03", "nmc_04", "nmc_05", "nmc_06"};
	final public static String[] NMC_NAME = {"6,500P", "19,500P+500P", "32,500P+1,500P","58,500P+4,500P", "116,000P+12,000P", "177,000P+23,000P"};
	final public static String[] NMC_NAME_IOS = {"6,500P", "20,000P", "34,000P","63,000P", "128,000P", "200,000P"};
	final public static int[] NMC_PRICE = {11000, 33000, 55000, 99000, 199000, 299000};
	final public static int[] NMC_PRICE_IOS = {12000, 39000, 66000, 110000, 229000, 349000};//20230703

	final public static int[] NMC_POINT = {6500, 20000, 34000, 63000, 128000, 200000};
	final public static int[] NMC_FREE_POINT = {0, 0, 0, 0, 0, 0};
	final public static int[] NMC_PASSBOOK_FREE_POINT = {0, 0, 0, 0, 0, 0};
	// ------------- e:2022 리뉴얼 상수


	public static FirebaseApp getFBA() {
		FirebaseApp fa = null;
		try {
			fa = FirebaseApp.getInstance();
		} catch (IllegalStateException ex) {
			FileInputStream serviceAccount;
			try {
				serviceAccount = new FileInputStream(ResourceUtils.getFile("classpath:allcam-268607-firebase-adminsdk-darpt-c6b38715fb.json"));
				
				FirebaseOptions options = new FirebaseOptions.Builder()
					    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					    .setDatabaseUrl("https://allcam-268607.firebaseio.com")
					    .build();
				
			    FirebaseApp.initializeApp(options);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		return fa;
	}
	
	public static String setCntComma(int cnt) {
        DecimalFormat df = new DecimalFormat("###,###");
        return df.format(cnt);
    }

	public static String getClientIP(HttpServletRequest request) {
		String ip = request.getHeader("X-FORWARDED-FOR");

		if (ip == null || ip.length() == 0) {
			ip = request.getHeader("Proxy-Client-IP");
		}

		if (ip == null || ip.length() == 0) {
			ip = request.getHeader("WL-Proxy-Client-IP");  // 웹로직
		}

		if (ip == null || ip.length() == 0) {
			ip = request.getRemoteAddr() ;
		}

		return ip;

	}
	
	public static String getImgType(JSONArray imgArray, String type) {
        try {
            for(int i=0; i<imgArray.length(); i++) {
                JSONObject o = imgArray.getJSONObject(i);

                if(type.equals(o.getString("img_type"))) {
                    return o.getString("img_url");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return "NONE";
        }
        return "NONE";
    }
	
	public static int dateDiffSec(String startDate) {		
		try {
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
			Date d1 = f.parse(startDate);
			Date d2 = f.parse(getNowTime());
			long diff = d1.getTime() - d2.getTime();
			
			return -(int) (diff/1000);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static int timeDiffSec(String startDate, String endDate) {		
		try {
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
			Date d1 = f.parse(startDate);
			Date d2 = f.parse(endDate);
			long diff = d1.getTime() - d2.getTime();
			
			return -(int) (diff/1000);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}
	
	public static String getNowTime() {		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		return format1.format(time);
	}

	public static boolean checkEventDay(String startDayStt, String endDayStt) {
		Date today = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startDay = null;
		Date endDay = null;
		try {

			// TODO 부재중 리워드 이벤트 기간 설정해줘야 함
			startDay = dateFormat.parse(startDayStt);
			endDay = dateFormat.parse(endDayStt);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		if (startDay != null && endDay != null) {

			// 이벤트 기간인 경우
			if (today.compareTo(startDay) >= 0 && today.compareTo(endDay) <= 0) {
				return true;
			}else{
				return false;
			}

		}else {
			return false;
		}
	}
	
	public static String tempPassword() {
		char[] characterTable = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 
                'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 
                'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
		
		String pass = "";
		for(int i=0; i<8; i++) {
		      int n = (int) (Math.random() * 35) + 1;
		      pass += characterTable[n];		      
		}
		return pass;
	}
	
	public static boolean saveFile(String savePath, String fileName, MultipartFile file) {
		boolean result = true;
		
		File imageDirectory = createDir(savePath);
		
		try {
			file.transferTo(new File(imageDirectory + "/" + fileName));
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = false;
		}
		
		return result;
	}
	
	public static File createDir(String savePath) {
		File imageDirectory = new File(savePath);
		if(!imageDirectory.exists()){ 
			imageDirectory.mkdirs();
		}
		return imageDirectory;
	}
	
	public static String urlDecode(String str) {
		String result = "";
		
		try {
			if(str != null) {
				result = URLDecoder.decode(str, "UTF-8");				
			}			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public static boolean resizeImage(String imgReadFile, String imgWriteFile, int type) {
		boolean result = true;
		try {
			FileInputStream fis = new FileInputStream(imgReadFile);
			byte[] data = new byte[fis.available()];
			fis.read(data);
			fis.close();

			Image image = Toolkit.getDefaultToolkit().createImage(data);
			Image rtnImage = null;
			BufferedImage getWidthBi = ImageIO.read(new File(imgReadFile));
			
			int user_width = 0;
			int change_width = getWidthBi.getWidth();
			
			if(type == IMAGE_THUMNAIL){		//섬네일 이미지 / 0 : 원본
				change_width = 540;
			}else if(type == IMAGE_VIEW){	//뷰사이즈
				change_width = 1080;
			}
			
			if(type == IMAGE_ORIGINAL){
				rtnImage = image;
			}else{
				if(getWidthBi.getWidth() > change_width){
					user_width = getWidthBi.getWidth()-change_width;
					double persent = (double) user_width / (double) getWidthBi.getWidth();
					rtnImage = resizing(image, change_width, getWidthBi.getHeight()-(int) (getWidthBi.getHeight()*persent));
				}else{
					rtnImage = image;
				}
			}
			
			MediaTracker tracker = new MediaTracker(new Frame());
			tracker.addImage(rtnImage, 0);
			tracker.waitForAll();

			BufferedImage bi = new BufferedImage(rtnImage.getWidth(null),
					rtnImage.getHeight(null), BufferedImage.TYPE_INT_RGB);
			Graphics g = bi.getGraphics();
			g.drawImage(rtnImage, 0, 0, null);
			g.dispose();
			ByteArrayOutputStream bas = new ByteArrayOutputStream();
			ImageIO.write(bi, "jpeg", bas);
			byte[] writeData = bas.toByteArray();

			DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(imgWriteFile)));
			dos.write(writeData);
			dos.close();

		} catch (Exception e) {
			e.printStackTrace();
			result = false;
		}
		
		return result;
	}
	
	public static Image resizing(Image soruce, int width, int height) throws Exception {
		int newW = width;
		int newH = height;

		return soruce.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
	}
	
	public static void sendMail(String type, String sendData) {
		String host = "smtp.worksmobile.com";
		final String sendEmail = "";
		final String passwd = "";
		int port = 465; // 포트번호

		String receiveEmail = "";	//받는 메일 주소
		String receiveName = "";	//받는 사람 이름
		String subject = "";	//제목
		String body = "";	//내용
				
//		Gson gson = new Gson();
		//타입에 따라 데이터 제작 ------------------------------------------------------------
		if("join".equals(type)) {
//			UserInfoDTO dto = gson.fromJson(sendData, UserInfoDTO.class);
//	        
//	        receiveEmail = dto.getEmail();	//받는 메일 주소
//			receiveName = dto.getNick_name();	//받는 사람 이름
//			subject = "핀모리 이메일 인증";	//제목
//			body = "<a href=\"http://ec2-13-125-207-16.ap-northeast-2.compute.amazonaws.com:8080/pinmory_server/emailAuth?userNo=" + dto.getNo()+"\">이메일 인증 완료</a>";	//내용
		}
		//타입에 따라 데이터 제작 끝-----------------------------------------------------------
		
		Properties props = System.getProperties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.trust", host);

		Session session = Session.getDefaultInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sendEmail, passwd);
			}
		});
		session.setDebug(true);

//		InternetAddress[] toAddr = new InternetAddress[10];	//여러 사람일 시 보내는 방법 
//		toAddr[0] = new InternetAddress ("PPPPPPPPPP@mail.com");
//		imeMessage.setRecipients(Message.RecipientType.TO, toAddr );
		
		try {
			Message mimeMessage = new MimeMessage(session);			
			mimeMessage.setFrom(new InternetAddress(sendEmail, "올캠", "UTF-8"));
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveEmail, receiveName, "UTF-8")); // .TO 외에 .CC(참조) .BCC(숨은참조)
			mimeMessage.setSubject(MimeUtility.encodeText(subject, "UTF-8", "B")); //제목
			mimeMessage.setContent(body, "text/html; charset=UTF-8");	//내용
//			mimeMessage.setText(body); //내용
			Transport.send(mimeMessage);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getMd5(String src) throws Exception {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            return "";
        }

        String eip;
        byte[] bip;
        String temp = "";
        String tst = src;

        bip = md5.digest(tst.getBytes());
        for (int i = 0; i < bip.length; i++) {
            eip = "" + Integer.toHexString((int) bip[i] & 0x000000ff);
            if (eip.length() < 2)
                eip = "0" + eip;
            temp = temp + eip;
        }
        return temp;
    }
	
	public static DefaultDTO defaultResult(DefaultDTO dto, int result) {
		if(result <= 0) {
			dto.setResult(FAIL);
		} else {
			dto.setResult(SUCCESS);
		}
		
		return dto;
	}
		
	public static boolean filterBanText(String msg, ArrayList<BanTextDTO> banTextList) {	//검토 메시지 / 금지어
		boolean banFlag = false;		
		
		for(BanTextDTO dto : banTextList) {
			Pattern p = Pattern.compile(dto.getBan_text(), Pattern.CASE_INSENSITIVE);

			Matcher m = p.matcher(msg);
			while (m.find()) {
				banFlag = true;
				break;
			}			
		}
		
		return banFlag;
	}
	
	public static ArrayList<String> getExcelText(String dir) {
		ArrayList<String> resultArrayList = new ArrayList<String>();
		
		try {
            FileInputStream file = new FileInputStream(dir);
            XSSFWorkbook workbook = new XSSFWorkbook(file);
 
            int rowindex=0;
            int columnindex=0;
            //시트 수 (첫번째에만 존재하므로 0을 준다)
            //만약 각 시트를 읽기위해서는 FOR문을 한번더 돌려준다
            XSSFSheet sheet = workbook.getSheetAt(0);
            //행의 수
            int rows = sheet.getPhysicalNumberOfRows();
            
            for(rowindex = 0; rowindex<rows; rowindex++){
                //행을읽는다
                XSSFRow row=sheet.getRow(rowindex);
                if(row !=null){
                    //셀의 수
                    int cells=row.getPhysicalNumberOfCells();
                    for(columnindex=0; columnindex<=cells; columnindex++){
                        //셀값을 읽는다
                        XSSFCell cell = row.getCell(columnindex);
                        String value = "";
                        
                        //셀이 빈값일경우를 위한 널체크
                        if(cell==null){
                            continue;
                        }else{
                            //타입별로 내용 읽기
                            switch (cell.getCellType()){
	                            case STRING :
	                                value = cell.getStringCellValue();
	                                resultArrayList.add(value);
	                                break;
//	                            case XSSFCell.CELL_TYPE_NUMERIC :
//	                                value=cell.getNumericCellValue()+"";
//	                                break;
//	                            case XSSFCell.CELL_TYPE_STRING :
//	                                value=cell.getStringCellValue()+"";
//	                                break;
//	                            case XSSFCell.CELL_TYPE_BLANK :
//	                                value=cell.getBooleanCellValue()+"";
//	                                break;
//	                            case XSSFCell.CELL_TYPE_ERROR :
//	                                value=cell.getErrorCellValue()+"";
//	                                break;
                            }
                        }
                    }
 
                }
            }
 
        }catch(Exception e) {
            e.printStackTrace();
        }
		return resultArrayList;
	}

	public static void sendSMS(String content, String receiverHp) {
		try{
			final String encodingType = "utf-8";
			final String boundary = "____boundary____";
			String sms_url = "https://apis.aligo.in/send/"; // 전송요청 URL

			Map<String, String> sms = new HashMap<String, String>();

			sms.put("user_id", Utils.SMS_ALIGOAPI_ID); // SMS 아이디
			sms.put("key", Utils.SMS_ALIGOAPI_KEY); //인증키

			/******************** 전송정보 ********************/
			sms.put("msg", content); // 메세지 내용
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

		}catch(Exception e){
			System.out.print(e.getMessage());
		}
	}

}
