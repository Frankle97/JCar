package com.jyeob.membercontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.SellerDao;

public class mypageOrderSellCancel implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		if (new SellerDao().removeMyOrder(request.getParameter("id")) > 0) {
			out.println("<script>alert('주문이 취소되었습니다.'); location.href='"+request.getContextPath()+"/myPageOrderSell.do?id="+request.getParameter("id")+"'; </script>");
		} else {
			out.println("<script>alert('주문 취소에 실패하였습니다. 관리자에게 문의해주세요.); location.href='\"+request.getContextPath()+\"/myPageOrderSell.do?id=\"+request.getParameter(\"id\")+\"';</script>");
		}
	}

}
