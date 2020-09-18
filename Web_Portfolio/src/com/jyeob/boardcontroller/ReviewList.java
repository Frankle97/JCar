package com.jyeob.boardcontroller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.ReviewDao;
import com.jyeob.dto.PagingDto_Rev;
import com.jyeob.dto.ReviewDto;
import com.jyeob.membercontroller.Action;

public class ReviewList implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		ReviewDao dao = new ReviewDao();
		ArrayList<ReviewDto> list = null;
		
		int pageTotal = dao.listCnt();
		int onepageLimit = 10;
		int pageAll = (int) Math.ceil(pageTotal/(float)onepageLimit);
		int pstartno = 0;
		if (request.getParameter("no") != null) {
			pstartno = Integer.parseInt(request.getParameter("no"));
		}
		list = dao.list10(pstartno);
		int bottomList = 10;
		int bottom_current = (int)Math.ceil((pstartno+1)/(float)onepageLimit);
		int bottom_start=(int)Math.floor(((bottom_current-1)/(float)bottomList)) * bottomList + 1;

		int bottom_end=bottom_start+bottomList-1;
		if (pageAll < bottom_end) {bottom_end = pageAll;} 
		
		request.setAttribute("cnt", pageTotal);
		request.setAttribute("list", new PagingDto_Rev(pageTotal, onepageLimit, pageAll, pstartno, bottomList, bottom_current, bottom_start, bottom_end, list));
		request.getRequestDispatcher("boardReview/reviewBoard_List.jsp").forward(request, response);
		
	}
}
