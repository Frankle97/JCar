package com.jyeob.membercontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String cookie = request.getHeader("Cookie");
		if (cookie != null) {
		Cookie[] cookies = request.getCookies();	
		for (int i=0; i<cookies.length; i++) {
			if (cookies[i].getName().equals("id")) {
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
			}
		}
		out.println("<script>alert('로그아웃 되었습니다.'); location.href='"+request.getContextPath()+"/car.do'; </script>");
		}
	}

}
