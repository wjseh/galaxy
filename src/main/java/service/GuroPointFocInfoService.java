package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class GuroPointFocInfoService {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		// 인증키(Decoding)
		String serviceKey = "UX4TupNSvzLyfYYyKIikJ/LL6HIfPGS49aaX8vMI71wz8a2A5O3S1tL0XzuUg4lEboLrCcHrToPBnd2igi3LWA==";
		
		// API 주소
		StringBuilder sb = new StringBuilder();
		try {
			sb.append("http://apis.data.go.kr/3160000/guroPointFocInfoSvc/getGuro10PointFocInfoSvc");
			sb.append("?serviceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
			sb.append("&numOfRows=").append(URLEncoder.encode("10", "UTF-8"));
			sb.append("&pageNo=").append(URLEncoder.encode("1", "UTF-8"));
			sb.append("&returnType=").append(URLEncoder.encode("xml", "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String apiURL = sb.toString();
		
		// API 주소 연결
		URL url = null;
		HttpURLConnection con = null;
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/xml; chearset=UTF-8");
		} catch (MalformedURLException e) {  // apiURL이 잘못되었다.
			e.printStackTrace();
		} catch (IOException e) {  // API 연결이 실패하였다.
			e.printStackTrace();
		}
		
		// 연결된 API에서 xml받아오기
		// 응답 스트림 만들기(입력 스트림)
		BufferedReader br = null;
		StringBuilder sb2 = new StringBuilder();
		try {
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			// xml 읽어드리기
			String line = null;
			while((line = br.readLine()) != null) {
				sb2.append(line + "\n");
			}
			// 스트림 연결 종료
			if(br != null) {
				br.close();
			}
			if(con != null) {
				con.disconnect();
			}
		} catch (IOException e) {
			e.printStackTrace();  // API 응답이 실패하셨다.
		}
		
		// API로부터 받은 xml을 guro.jsp로 보내기
		// guro.jsp로 응답하기
		response.setContentType("application/xml; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(sb2.toString());  // ajax의 success로 넘어간다.
		out.flush();
		out.close();
	}
}
