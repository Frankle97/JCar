package com.jyeob.membercontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.jyeob.dao.MemberDao;
import com.jyeob.dto.MemberDto;

public class UserFindIDAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		MemberDto dto = new MemberDao().findMyID(request.getParameter("name"), request.getParameter("email"));
	
		if (dto.getId() != null) {
			out.println("<script>location.href='"+request.getContextPath()+"/findID_suc.do?id="+dto.getId()+"&date="+dto.getDate()+"'; </script>");
		} else {
			out.println("<script>alert('일치하는 정보를 찾을 수 없습니다.'); history.go(-1); </script>");
		}
	}
}
