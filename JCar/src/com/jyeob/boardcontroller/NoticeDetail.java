package com.jyeob.boardcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.BoardDao;
import com.jyeob.dto.BoardDto;
import com.jyeob.membercontroller.Action;

public class NoticeDetail implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		BoardDto dto = new BoardDao().showNotice(Integer.parseInt(request.getParameter("no")));
		request.setAttribute("dto", dto);
		request.getRequestDispatcher("/boardNotice/noticeBoard_Detail.jsp").forward(request, response);
	}

}
