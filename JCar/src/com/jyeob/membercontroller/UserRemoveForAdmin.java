package com.jyeob.membercontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.MemberDao;

public class UserRemoveForAdmin implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int res = new MemberDao().removeAccountForAdmin(request.getParameter("id"));
		if (res > 0) {
			out.println("<script>alert('유저 삭제 완료.'); location.href='" + request.getContextPath() + "/adminPage.do'; </script>");	
		} else {
			out.println("<script>alert('에러 발생.'); location.href='" + request.getContextPath() + "/adminPage.do'; </script>");
		}
		
	}

}
