package com.jyeob.carcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.CarDao;
import com.jyeob.dto.CarlistDto;
import com.jyeob.dto.StoreDto;
import com.jyeob.membercontroller.Action;

public class CarDetail implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		CarlistDto dt = new CarDao().detailCar(Integer.parseInt(request.getParameter("no")));
		StoreDto dto = new CarDao().storeInfo(dt.getCity());
		
		request.setAttribute("dto", dt);
		request.setAttribute("x", dto.getX());
		request.setAttribute("y", dto.getY());
		request.getRequestDispatcher("/buyCar/carDetail.jsp").forward(request, response);
	}

}
