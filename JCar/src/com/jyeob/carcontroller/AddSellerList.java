package com.jyeob.carcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.SellerDao;
import com.jyeob.dto.SellerDto;
import com.jyeob.membercontroller.Action;

public class AddSellerList implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		SellerDto dto = new SellerDto(request.getParameter("id"), request.getParameter("name"), request.getParameter("phone"), request.getParameter("country"), request.getParameter("model"), Integer.parseInt(request.getParameter("birth")));
		int res = new SellerDao().addSellerList(dto);
		
		if (res>0) {
			out.println("<script>alert('신청이 완료되었습니다. 등록결과는 마이페이지에서 확인이 가능합니다.'); location.href='"+request.getContextPath()+"/car.do'; </script>");
		} else {
			out.println("<script>alert('신청이 실패하였습니다. 관리자에게 문의해주세요.'); location.href='"+request.getContextPath()+"/car.do'; </script>");
		}
	}

}
