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

public class Covid19InfStateService {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String serviceKey = "UX4TupNSvzLyfYYyKIikJ/LL6HIfPGS49aaX8vMI71wz8a2A5O3S1tL0XzuUg4lEboLrCcHrToPBnd2igi3LWA==";
		
		StringBuilder sb = new StringBuilder();
		
		try {
			sb.append("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson");
			sb.append("?serviceKey=").append(URLEncoder.encode(serviceKey, "UTF-8"));
			sb.append("&pageNo=").append(URLEncoder.encode("1", "UTF-8"));
			sb.append("&numOfRows=").append(URLEncoder.encode("10", "UTF-8"));
			sb.append("&startCreateDt=").append(URLEncoder.encode("20200310", "UTF-8"));
			sb.append("&endCreateDt=").append(URLEncoder.encode("20200315", "UTF-8"));
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		String apiURL = sb.toString();
		
		URL url = null;
		HttpURLConnection con = null;
		try {
			url = new URL(apiURL);
			con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/xml; chearset=UTF-8");
		} catch (MalformedURLException e) {  
			e.printStackTrace();
		} catch (IOException e) { 
			e.printStackTrace();
		}
		BufferedReader br = null;
		StringBuilder sb2 = new StringBuilder();
		try {
			if(con.getResponseCode() == HttpURLConnection.HTTP_OK) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			}else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String line = null;
			while((line = br.readLine()) != null) {
				sb2.append(line + "\n");
			}
			if(br != null) {
				br.close();
			}
			if(con != null) {
				con.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		response.setContentType("application/xml; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(sb2.toString());
		out.flush();
		out.close();
			
	}
}
		
	
	

