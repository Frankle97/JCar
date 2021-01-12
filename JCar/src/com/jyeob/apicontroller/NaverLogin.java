package com.jyeob.apicontroller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

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
 * Servlet implementation class NaverLogin
 */
@WebServlet("/NaverLogin")
public class NaverLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NaverLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	HttpSession session = request.getSession();
	PrintWriter out = response.getWriter();
	
		String clientId = "sUEFL28oA9odjIYGEQkL";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "eZmA3ieS9u";//애플리케이션 클라이언트 시크릿값";
	    String code = request.getParameter("code");
	    String state = request.getParameter("state");
	    String redirectURI = URLEncoder.encode("http://tieotdsf1324.cafe24.com/port/NaverLogin", "UTF-8");
	    String apiURL = null;
	    String access_token = null;
	    String inputLine = null;
	 
	    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
	    apiURL += "client_id=" + clientId;
	    apiURL += "&client_secret=" + clientSecret;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&code=" + code;
	    apiURL += "&state=" + state;
	    
	    
	    
	    try {
	      URL url = new URL(apiURL);
	      HttpURLConnection con = (HttpURLConnection)url.openConnection();
	      con.setRequestMethod("GET");
	      int responseCode = con.getResponseCode();
	      BufferedReader br;
	      if(responseCode == 200) { 
	        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	      } else {  
	        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	      }
	      StringBuffer res = new StringBuffer();
	      while ((inputLine = br.readLine()) != null) {
	        res.append(inputLine);
	      }
	      br.close();
	      if(responseCode == 200) {
	      }
	      	JsonParser jsonParser = new JsonParser();
		    JsonObject job = (JsonObject)jsonParser.parse(res.toString());
		    access_token = job.get("access_token").getAsString();
	    } catch (Exception e) {
	      System.out.println(e);
	    }
	    
	    

        String apiURL2 = "https://openapi.naver.com/v1/nid/me";    			

        URL url = new URL(apiURL2);
        HttpURLConnection conn =  (HttpURLConnection)url.openConnection();
        
        conn.setRequestMethod("GET");
        conn.setRequestProperty("X-Naver-Client-Id", clientId);
        conn.setRequestProperty("X-Naver-Client-Secret", clientSecret);
        conn.setRequestProperty("Authorization", "Bearer " + access_token);
    
        
        JsonParser jsonParser = new JsonParser();
        StringBuffer sb = new StringBuffer();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        while (true) {
        	String line = br.readLine();
        	
        	if (line == null) break;
        	
        	sb.append(line);
        }
        br.close(); conn.disconnect();
        
        JsonObject tmpt = (JsonObject)jsonParser.parse(sb.toString());
        JsonObject data = (JsonObject)tmpt.get("response");
        try {
			String nickname = data.get("name").getAsString();
			String  email = data.get("email").getAsString();
			MemberDto dto = new MemberDao().findMyID(nickname, email);
			if (dto.getId() == null) {
				out.println("<script>alert('회원정보가 존재하지 않습니다. 회원가입 페이지로 이동합니다.'); location.href='"+request.getContextPath()+"/joinSelect.do'; </script>");
				session.setAttribute("kakao_name", nickname);
				session.setAttribute("email", email);
				session.setAttribute("kakao", true);
			} else {
				session.setAttribute("id", dto.getId());
				Cookie cookie = new Cookie("id", dto.getId());
				cookie.setMaxAge(60 * 30);
				response.addCookie(cookie);
				out.println("<script>location.href='"+request.getContextPath()+"/car.do'; </script>");
				
			}
			
		} catch(Exception e) {
			out.println("<script>alert('선택 제공 항목의 네이버계정(이메일) 체크를 허용해주셔야합니다.'); location.href='"+request.getContextPath()+"/loginView.do';</script>");
		}
        
        String apiURL3 = "https://nid.naver.com/oauth2.0/token?grant_type=delete&client_id=sUEFL28oA9odjIYGEQkL&client_secret=eZmA3ieS9u&access_token="+access_token+"&service_provider=NAVER";
        try {
  	      URL url3 = new URL(apiURL3);
  	      HttpURLConnection con = (HttpURLConnection)url3.openConnection();
  	      con.setRequestMethod("GET");
  	      int responseCode = con.getResponseCode();
  	      BufferedReader br3;
  	      if(responseCode == 200) { 		
  	        br3 = new BufferedReader(new InputStreamReader(con.getInputStream()));		// 정상 호출
  	      } else { 						 
  	        br3 = new BufferedReader(new InputStreamReader(con.getErrorStream()));		// 에러 발생
  	      }
  	      
  	      StringBuffer res = new StringBuffer();
  	      while ((inputLine = br3.readLine()) != null) {
  	        res.append(inputLine);
  	      }
  	      br.close();
  	    } catch (Exception e) {
  	      System.out.println(e);
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
