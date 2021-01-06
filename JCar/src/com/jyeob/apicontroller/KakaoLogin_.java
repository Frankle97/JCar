package com.jyeob.apicontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jyeob.dao.MemberDao;
import com.jyeob.dto.MemberDto;

/**
 * Servlet implementation class KakaoLogin
 */
@WebServlet("/kakao")
public class KakaoLogin_ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KakaoLogin_() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		
		
		PrintWriter out = response.getWriter();
		String code = request.getParameter("code");
		String client_id = "276c3f616086bbc0130bdbbb701b41a0";
		String redirect_uri = "http://tieotdsf1324.cafe24.com/port/kakao";
		String url = "https://kauth.kakao.com/oauth/token?";
		url += "grant_type=authorization_code&";
		url += "client_id=" + client_id + "&";
		url += "redirect_uri="+redirect_uri+"&";
		url += "code="+code;
		
		URL kakaourl = new URL(url);
		HttpURLConnection conn = (HttpURLConnection)kakaourl.openConnection();
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-type", "application/json; charset=utf-8"); 
		
		BufferedReader br = null;
		String line = null;
		StringBuffer sb = new StringBuffer();
		if (conn.getResponseCode() == 200) {
			br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		//1. json 파일에서 access token 추출
		JsonParser jsonParser = new JsonParser();
		JsonObject job = (JsonObject)jsonParser.parse(sb.toString());
		String access_token = job.get("access_token").getAsString();
		
		String testurl = "https://kapi.kakao.com/v2/user/me";
		URL kakaourl2 = new URL(testurl);
		HttpURLConnection conn2 = (HttpURLConnection)kakaourl2.openConnection();
		conn2.setDoInput(true);
		conn2.setDoOutput(true);
		conn2.setRequestMethod("POST");
		conn2.setRequestProperty("Content-type", "application/json; charset=utf-8"); 
		conn2.setRequestProperty("Authorization", "Bearer "+access_token);
		BufferedReader br2 = null;
		String line2 = null;
		StringBuffer sb2 = new StringBuffer();
		if (conn2.getResponseCode() == 200) {
			br2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
		} else {
			br2 = new BufferedReader(new InputStreamReader(conn2.getErrorStream()));
		}
		
		while ((line2 = br2.readLine()) != null) {
			sb2.append(line2);
		}

		
		br.close();br2.close();conn.disconnect();conn2.disconnect();
		
		JsonObject data = (JsonObject)jsonParser.parse(sb2.toString());
		JsonObject profile = (JsonObject)data.get("properties");
		JsonObject kakao_account = (JsonObject)data.get("kakao_account");
		try {
			String nickname = profile.get("nickname").getAsString();
			String  email = kakao_account.get("email").getAsString();
			MemberDto dto = new MemberDao().findMyID(nickname, email);
			if (dto.getId() == null) {
				out.println("<script>alert('회원정보가 존재하지 않습니다. 회원가입 페이지로 이동합니다.'); location.href='"+request.getContextPath()+"/joinSelect.do'; </script>");
				session.setAttribute("kakao_name", nickname);
				session.setAttribute("email", email);
				session.setAttribute("kakao", true);
			} else {
				session.setAttribute("id", dto.getId());
				Cookie cookie = new Cookie("id", dto.getId());
				cookie.setMaxAge(60*30);
				response.addCookie(cookie);
				out.println("<script>location.href='"+request.getContextPath()+"/car.do'; </script>");
				
			}
			
		} catch(Exception e) {
			out.println("<script>alert('선택 제공 항목의 카카오계정(이메일) 체크를 허용해주셔야합니다.'); location.href='"+request.getContextPath()+"/loginView.do';</script>");
		}
		
		url="https://kapi.kakao.com/v1/user/unlink";
		URL unlink = new URL(url);
		HttpURLConnection conn3 = (HttpURLConnection)unlink.openConnection();
		conn3.setDoInput(true);
		conn3.setDoOutput(true);
		conn3.setRequestMethod("POST");
		conn3.setRequestProperty("Authorization", "Bearer "+access_token); 
		
		BufferedReader br3 = null;
		String line3 = null;
		StringBuffer sb3 = new StringBuffer();
		if (conn3.getResponseCode() == 200) {
			br3 = new BufferedReader(new InputStreamReader(conn3.getInputStream()));
		} else {
			br3 = new BufferedReader(new InputStreamReader(conn3.getErrorStream()));
		}
		while ((line3 = br3.readLine()) != null) {
			sb3.append(line3);
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
