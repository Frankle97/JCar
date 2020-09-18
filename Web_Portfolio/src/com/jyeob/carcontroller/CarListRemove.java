package com.jyeob.carcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.CarDao;
import com.jyeob.membercontroller.Action;

public class CarListRemove implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		CarDao dao = new CarDao();
		int res = dao.removeCar(Integer.parseInt(request.getParameter("no")));
		if (res>0) {
			out.println("<script>alert('삭제가 완료되었습니다.'); location.href='"+request.getContextPath()+"/carList.do'; </script>");
		} else {
			out.println("<script>alert('삭제에 실패하였습니다. DAO를 확인해주세요.'); location.href='"+request.getContextPath()+"/carList.do'; </script>");
		}
	}

}
