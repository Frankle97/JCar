package com.jyeob.membercontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.MemberDao;

public class UserRemove implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		int res = new MemberDao().removeAccount(request.getParameter("id"), request.getParameter("password"));
		
		if (res > 0) {
			String cookie = request.getHeader("Cookie");
			if (cookie != null) {
				Cookie[] cookies = request.getCookies();	
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("id")) {
						cookies[i].setMaxAge(0);
						response.addCookie(cookies[i]);
						break;
					}
				}
			}
			out.println("<script>alert('탈퇴가 완료되었습니다.'); location.href='" + request.getContextPath() + "/car.do'; </script>");	
		} else {
			out.println("<script>alert('비밀번호가 일치하지 않습니다.'); location.href='" + request.getContextPath() + "/myPageView.do"
					+ "?id=" + request.getParameter("id") + "'; </script>");
		}
		
	}

}
