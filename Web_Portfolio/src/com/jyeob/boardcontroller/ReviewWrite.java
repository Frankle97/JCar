package com.jyeob.boardcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.CarDao;
import com.jyeob.dao.ReviewDao;
import com.jyeob.dao.SellerDao;
import com.jyeob.dto.ReviewDto;
import com.jyeob.membercontroller.Action;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ReviewWrite implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		ReviewDto dto = new ReviewDto();
		int no = Integer.parseInt(request.getParameter("no"));
		String path = "/upload/";
		path = request.getServletContext().getRealPath(path);	// 호스팅시 ##반드시## 주석풀기##
		MultipartRequest multi = new MultipartRequest(request, path, 1024*1024*5, "UTF-8", new DefaultFileRenamePolicy());
		String image = multi.getFilesystemName("image");
		if (image==null) {
			image="no.jpg";
		}
		
		dto.setWriter(multi.getParameter("writer"));
		dto.setModel(multi.getParameter("model"));
		dto.setTitle(multi.getParameter("title"));
		dto.setContent(multi.getParameter("content").replaceAll("<p>", "").replaceAll("</p>", "<br/>"));
		dto.setImage(image);
		ReviewDao dao = new ReviewDao();
		int res = dao.writeReview(dto);
		if (res>0) {
			new CarDao().removeCar(no);
			new SellerDao().removeOrder(no);
			out.println("<script> alert('리뷰 작성이 완료되었습니다.'); location.href='"+request.getContextPath()+"/showReview.do'; </script>");
		} else {
			out.println("<script> alert('작성에 실패하였습니다. 관리자에게 문의해주세요'); history.go(-1); </script>");
		}
	}

}
