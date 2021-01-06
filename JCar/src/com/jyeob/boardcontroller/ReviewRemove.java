package com.jyeob.boardcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.ReviewDao;
import com.jyeob.membercontroller.Action;

public class ReviewRemove implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String id = request.getParameter("id");
		int no = Integer.parseInt(request.getParameter("no"));
		ReviewDao dao = new ReviewDao();
		if (id.equals("admin")) {
			dao.RemoveReviewAdmin(no);
			out.println("<script>alert('삭제가 완료되었습니다.'); location.href='"+request.getContextPath()+"/showReview.do'; </script>");
		} else {
			if (dao.RemoveReview(id, no) > 0) {
				out.println("<script>alert('삭제가 완료되었습니다.'); location.href='"+request.getContextPath()+"/showReview.do'; </script>");
			} else {
				out.println("<script>alert('삭제권한이 없습니다.'); location.href='"+request.getContextPath()+"/showReview.do'; </script>");
			}
		}
		
		
	}

}
