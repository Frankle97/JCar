package com.jyeob.membercontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jyeob.dao.MemberDao;
import com.jyeob.dto.MemberDto;

public class LoginAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		MemberDto dto = new MemberDao().login(request.getParameter("id"), request.getParameter("password"));
		if (request.getParameter("remember_id") != null) {
			session.setAttribute("id", request.getParameter("id"));
		} else {
			session.invalidate();
		}
		if (dto.getId() != null) {
			Cookie cookie = new Cookie("id", request.getParameter("id"));
			cookie.setMaxAge(60 * 30);
			response.addCookie(cookie);
		} 
		if (dto.getId() != null) {
			out.println("<script>location.href='" + request.getContextPath() + "/car.do'; </script>");
		} else {
			out.println("<script>alert('등록되지 않은 아이디이거나 비밀번호가 일치하지 않습니다.'); location.href='" + request.getContextPath() + "/loginView.do'; </script>");
		}
	}

}
