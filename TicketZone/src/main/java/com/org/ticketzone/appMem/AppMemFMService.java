package com.org.ticketzone.appMem;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.org.ticketzone.service.AppMemService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Controller
public class AppMemFMService {
	@Autowired AppMemService memberService;
	
	/* save FCM token */
	@ResponseBody
	@RequestMapping(value = "/mem_set_fcm_token", method = RequestMethod.POST)
	public String setFCM(HttpServletRequest request) {
        String token = request.getParameter("Token");
        // 설치 후 최초 실행시만 접속한 유저 token반환
        // 최초 실행시 외에는 null반환
		System.out.println(token);
	    return "jsonView";
	}

	@ResponseBody
	@RequestMapping(value = "/mem_send_fcm", method = RequestMethod.POST)
	// 구글 인증 서버
	public String pushFCMNotification() throws Exception {
		String API_KEY = "AIzaSyAfXJUsLsL-Kf8KTq7WB9yXpn7PiOUYqGs";
		String token = "fiSOH8FIm4g:APA91bEWhjN29G2ZmfFfh-5-zM1ZeBsbX86hW7onF6kf1Qftwn8LXAvmbupXg120Jra1ttjQt-Lg4Sr2-L_ta1fAif3_QAH186_6sfBqLcPstgbZimHtF6-BQLmBPeHrUyJkJyKjqc1X";

		// if(pushKeyAuth()){
		URL url = new URL("https://fcm.googleapis.com/fcm/send");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setUseCaches(false);
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Authorization", "key=" + API_KEY);
		conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		// 일반 텍스트 전달시 Content-Type , application/x-www-form-urlencoded;charset=UTF-8

		// 알림 + 데이터 메세지 형태의 전달
		System.out.println("1818181881818");
		JSONObject notification = new JSONObject(); 
		JSONObject message = new JSONObject();
		notification.put("body", "alert body");
		notification.put("title", "alert title");

		message.put("to", "/topics/all");
		message.put("notification", notification);
		System.out.println(message.toString());
//		OutputStreamWriter owr = new OutputStreamWriter(conn.getOutputStream(), "utf-8");
//		owr.write(message.toString());
//		owr.flush();
//		owr.close();
		
		 OutputStream os = conn.getOutputStream();
        // 서버에서 날려서 한글 깨지는 사람은 아래처럼  UTF-8로 인코딩해서 날려주자
        os.write(message.toString().getBytes("UTF-8"));
        os.flush();
        os.close();
        
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			System.out.println("Output from Server : " + conn.getResponseCode());
		} else {
			// 400, 401, 500 등
			System.out.println("Failed : HTTP error code : " + conn.getResponseCode());			
		}
        conn.disconnect();
        
        return message.toString();
	}
	
//	/* send FCM */
//	@ResponseBody
//	@RequestMapping(value = "/mem_send_fcm2", method = RequestMethod.POST)
//	public void fcmTest() throws Exception {
//        try {    
//        	System.out.println("18");
//        	
//        	String token = "fiSOH8FIm4g:APA91bEWhjN29G2ZmfFfh-5-zM1ZeBsbX86hW7onF6kf1Qftwn8LXAvmbupXg120Jra1ttjQt-Lg4Sr2-L_ta1fAif3_QAH186_6sfBqLcPstgbZimHtF6-BQLmBPeHrUyJkJyKjqc1X";
//            String path = "C:\\Users\\bon300-12\\Desktop\\comma\\TicketZone\\src\\main\\webapp\\resources\\json\\smartwait-3cb6a-firebase-adminsdk-qql5m-4e06f12d08.json";
////            String MESSAGING_SCOPE = "https://www.googleapis.com/auth/firebase.messaging";
//            String MESSAGING_SCOPE = "smartwait-3cb6a";
//            String[] SCOPES = { MESSAGING_SCOPE };
//            
//            GoogleCredential googleCredential = GoogleCredential
//                                .fromStream(new FileInputStream(path))
//                                .createScoped(Arrays.asList(SCOPES));
//            googleCredential.refreshToken();
//                                
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("content-type" , MediaType.APPLICATION_JSON_VALUE);
//            headers.add("Authorization", "Bearer " + googleCredential.getAccessToken());
//            
//            JSONObject notification, message, jsonParams;
//            notification = new JSONObject();
//            notification.put("body", "alert body");
//            notification.put("title", "alert title");
//            
//            message = new JSONObject();
//            message.put("token", token);
//            message.put("notification", notification);
//            
//            jsonParams = new JSONObject();
//            jsonParams.put("message", message);
//            
//            HttpEntity<JSONObject> httpEntity = new HttpEntity<JSONObject>(jsonParams, headers);
//            RestTemplate rt = new RestTemplate();            
//            
//            ResponseEntity<String> res = rt.exchange("https://fcm.googleapis.com/v1/projects/TicketZone/messages:send"
//                    , HttpMethod.POST
//                    , httpEntity
//                    , String.class);
//        
////            if (res.getStatusCode() != HttpStatus.OK) {
////                log.debug("FCM-Exception");
////                log.debug(res.getStatusCode().toString());
////                log.debug(res.getHeaders().toString());
////                log.debug(res.getBody().toString());
////                
////            } else {
////                log.debug(res.getStatusCode().toString());
////                log.debug(res.getHeaders().toString());
////                log.debug(res.getBody().toLowerCase());
////                
////            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


}
