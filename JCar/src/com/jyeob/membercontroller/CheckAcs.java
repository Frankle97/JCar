package com.jyeob.membercontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.MemberDao;
import com.jyeob.dto.MemberDto;

public class CheckAcs implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		MemberDto dto = new MemberDao().login(request.getParameter("id"), request.getParameter("password"));
		
		if (dto.getId() == null) {
			out.println("<script>alert('비밀번호가 일치하지 않습니다.'); "
					+ "location.href='" + request.getContextPath() + "/myPageView.do?id=" + request.getParameter("id") + "'; </script>");
		} else {
			out.println("<script>location.href='" + request.getContextPath() + "/changeMyInfo.do?id=" + dto.getId() + "'; </script>");
		}
	}
		
	
	
}
