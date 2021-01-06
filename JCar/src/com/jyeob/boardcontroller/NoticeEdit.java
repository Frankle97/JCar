package com.jyeob.boardcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.BoardDao;
import com.jyeob.dto.BoardDto;
import com.jyeob.membercontroller.Action;

public class NoticeEdit implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		BoardDto dto = new BoardDto();
		dto.setCategory(request.getParameter("category"));
		dto.setTitle(request.getParameter("title"));
		dto.setContent(request.getParameter("content"));
		dto.setNo(Integer.parseInt(request.getParameter("no")));
		
		if (new BoardDao().EditNotice(dto) > 0) {
		out.println("<script>alert('수정이 완료되었습니다.'); location.href='"+request.getContextPath()+"/detailNotice.do?no="+request.getParameter("no")+"'; </script>");	
		} else {
			out.println("<script>alert('수정에 실패하였습니다.'); location.href='"+request.getContextPath()+"/showNotice.do'; </script>");
		}
	}

}
