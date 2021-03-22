package com.jyeob.boardcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.BoardDao;
import com.jyeob.dto.BoardDto;
import com.jyeob.membercontroller.Action;

public class NoticeWrite implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		BoardDto dto = new BoardDto();
		dto.setCategory(request.getParameter("category"));
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		if (new BoardDao().addNotice(dto) > 0) {
			out.println("<script>alert('등록이 완료되었습니다.'); location.href='"+request.getContextPath()+"/showNotice.do'; </script>");	
		} else {
			out.println("<script>alert('등록에 실패하였습니다.'); location.href='"+request.getContextPath()+"/showNotice.do'; </script>");
		}
		
	
	}

}
