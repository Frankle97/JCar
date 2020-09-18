package com.jyeob.membercontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jyeob.dao.MemberDao;
import com.jyeob.dto.MemberDto;

public class JoinAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");
	MemberDto dto = new MemberDto();
	PrintWriter out = response.getWriter();
	String birth = request.getParameter("birth");
	HttpSession session = request.getSession();
	
	if (request.getParameter("name") == null) {
		dto.setName((String)session.getAttribute("kakao_name"));
	} else {
		dto.setName(request.getParameter("name"));
	}
	dto.setBirth(birth.substring(0,4) + "-" + birth.substring(4,6) + "-" + birth.substring(6));
	dto.setGender(request.getParameter("gender"));
	dto.setId(request.getParameter("id"));
	dto.setPassword(request.getParameter("password"));
	if (request.getParameter("email") == null) {
		dto.setEmail((String)session.getAttribute("email"));
	} else {
		dto.setEmail(request.getParameter("email"));
	}
	dto.setContact(request.getParameter("contact"));
	dto.setAddress(request.getParameter("address") + "/" + request.getParameter("address2"));
	dto.setIp(InetAddress.getLocalHost().getHostAddress());
	dto.setCategory(request.getParameter("category"));
	int res = new MemberDao().joinMember(dto);
	if (res>0) {
		out.println("<script>location.href='"+request.getContextPath()+"/joinCom.do?id="+request.getParameter("id")+"'; </script>");
	} else {
		out.println("<script>alert('가입에 실패하였습니다. 관리자에게 문의해주세요.'); history.go(-1); </script>");
	}
	
	}

}
