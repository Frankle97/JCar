package com.jyeob.boardcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.ReviewDao;
import com.jyeob.dto.ReviewDto;
import com.jyeob.membercontroller.Action;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ReviewEdit implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ReviewDto dto = new ReviewDto();
		String path = "/upload/";
		path = request.getServletContext().getRealPath(path);
		MultipartRequest multi = new MultipartRequest(request, path, 1024*1024*5, "UTF-8", new DefaultFileRenamePolicy());
		dto.setTitle(multi.getParameter("title"));
		dto.setContent(multi.getParameter("content"));
		dto.setNo(Integer.parseInt(multi.getParameter("no")));
		dto.setImage(multi.getFilesystemName("image"));
		
		if (new ReviewDao().proveUser(multi.getParameter("id"), Integer.parseInt(multi.getParameter("no"))) > 0) {
			new ReviewDao().EditReview(dto);
			out.println("<script>alert('수정이 완료되었습니다.'); location.href='"+request.getContextPath()+"/detailReview.do?no="+multi.getParameter("no")+"'; </script>");
		} else {
			out.println("<script>alert('수정권한이 없습니다.'); location.href='"+request.getContextPath()+"/showReview.do'; </script>");
		}
		
		
		
		
	}

}
