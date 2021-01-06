package com.jyeob.boardcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.ReviewDao;
import com.jyeob.dto.ReviewDto;
import com.jyeob.membercontroller.Action;

public class ReviewDetail implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		ReviewDto dto = new ReviewDao().DetailReview(Integer.parseInt(request.getParameter("no")));
		request.setAttribute("dto", dto);
		request.getRequestDispatcher("/boardReview/reviewBoard_Detail.jsp").forward(request, response);
	}

}
