package com.jyeob.membercontroller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.CarDao;
import com.jyeob.dto.CarlistDto;

public class Main implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		
		ArrayList<CarlistDto> list = new CarDao().listCar();
		request.setAttribute("list", list);
		request.setAttribute("cntall", new CarDao().cntCar());
		request.getRequestDispatcher("/main/mainForm.jsp").forward(request, response);
	}

}
