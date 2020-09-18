package com.jyeob.boardcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.BoardDao;
import com.jyeob.membercontroller.Action;

public class NoticeDelete implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		int res = new BoardDao().DeleteNotice(Integer.parseInt(request.getParameter("no")));
		
		if (res > 0) {
			out.println("<script>alert('삭제가 완료되었습니다.'); location.href='"+request.getContextPath()+"/showNotice.do'; </script>");
		} else {
			out.println("<script>alert('삭제 실패. 에러를 확인해주세요.'); history.go(-1); </script>");
		}
		
	}

}
