package com.jyeob.membercontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.MemberDao;

public class UserChangePWAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");	
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		int res = new MemberDao().changePW(request.getParameter("password"), request.getParameter("no"));
		
		if (res > 0) {
			out.println("<script>alert('비밀번호가 수정되었습니다.'); location.href='" + request.getContextPath() + "/loginView.do'; </script>");
		} else {
			out.println("<script>alert('비밀번호 수정 실패. 관리자에게 문의해주세요.'); location.href='" + request.getContextPath() + "/loginView.do'; </script>");
		}
	}

}
