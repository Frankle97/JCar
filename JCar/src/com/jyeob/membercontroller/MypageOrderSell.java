package com.jyeob.membercontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.SellerDao;
import com.jyeob.dto.SellerDto;

public class MypageOrderSell implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("id");
		SellerDto dto = new SellerDao().findMyOrder(id);
		request.setAttribute("info", dto);
		request.getRequestDispatcher("/member/userMypage_orderMySell.jsp").forward(request, response);
	}

}
