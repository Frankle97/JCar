package com.jyeob.membercontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.SellerDao;

public class mypageBringSell implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		int no = Integer.parseInt(request.getParameter("no"));
		String id = request.getParameter("id");
		int res = new SellerDao().updateSellList(no, id);
		if (res > 0) {
			out.println("<script> location.href='"+request.getContextPath()+"/sellerMyPage.do?id="+id+"'; </script>");
		} else {
			out.println("<script>alert('에러가 발생하였습니다. 관리자에게 문의해주세요.); location.href='"+request.getContextPath()+"/sellerMyPage.do?id="+request.getParameter("id")+"';</script>");
		}
	}

}
