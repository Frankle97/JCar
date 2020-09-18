package com.jyeob.carcontroller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.CarDao;
import com.jyeob.dto.CarlistDto;
import com.jyeob.membercontroller.Action;

public class HotModels implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		CarDao dao = new CarDao();
		ArrayList<CarlistDto> list = dao.hotList7();
		request.setAttribute("hotlist", list);
	}

}
