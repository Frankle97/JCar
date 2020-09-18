package com.jyeob.carcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.CarDao;
import com.jyeob.membercontroller.Action;

public class BuyCar implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String no = request.getParameter("no");
		String id = request.getParameter("id");
		CarDao dao = new CarDao();
		String list = dao.carOwn(id);
		
		if (list!=null) {
			list+=no+"/";
		} else {
			list="";
			list+=no+"/";
		}
		int res = dao.carBuy(list, id);
		if (res > 0) {
			dao.updateCar(Integer.parseInt(no));
			out.println("<script>alert('구입이 완료되었습니다. 마이페이지에서 구매후기를 작성하실 수 있습니다.'); location.href='"+request.getContextPath()+"/car.do';</script>");
		} else {
			out.println("<script>alert('구입에 실패하였습니다. 관리자에게 문의해주세요'); location.href='"+request.getContextPath()+"/car.do'; </script>");
		}
	}
}
