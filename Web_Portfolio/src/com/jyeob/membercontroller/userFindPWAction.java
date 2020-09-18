package com.jyeob.membercontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.MemberDao;

public class userFindPWAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		int res = new MemberDao().findMyPW(request.getParameter("id"), request.getParameter("name"));
		
		if (res > 0) {
			out.println("<script>location.href='"+request.getContextPath()+"/findPW_suc.do?no="+res+"'; </script>");
		} else {
			out.println("<script>alert('입력하신 정보와 일치하는 정보를 찾을 수 없습니다.다시 입력해주세요.'); history.go(-1); </script>");
		}
		
	}

}
