package com.jyeob.membercontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.MemberDao;

public class userChangeInfoAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String address = request.getParameter("address") + " " + request.getParameter("address2");
		int res = new MemberDao().changeInfo(request.getParameter("id"), request.getParameter("password"), request.getParameter("email"), request.getParameter("contact"), address);
		
		if (res > 0) {
			out.println("<script>alert('회원님의 정보 수정이 완료되었습니다.'); location.href='"+request.getContextPath()+"/myPageView.do?id="+request.getParameter("id")+"'; </script>");
		} else {
			out.println("<script>alert('회원님의 정보 수정에 실패하였습니다. 관리자에게 문의해주세요.'); location.href='"+request.getContextPath()+"/changeMyInfo.do?id="+request.getParameter("id")+"'; </script>");
		}
	}

}
