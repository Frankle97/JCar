package com.jyeob.membercontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.CarDao;
import com.jyeob.dto.CarlistDto;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class AddListFinal implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			CarDao dao = new CarDao();
			
			String path = "/upload/";
			path = request.getServletContext().getRealPath(path);	// 호스팅시 ##반드시## 주석풀기##
			MultipartRequest multi = new MultipartRequest(request, path, 1024*1024*5, "UTF-8", new DefaultFileRenamePolicy());
			String image = multi.getFilesystemName("image");
			
			if (image==null) {
				image="no.jpg";
			}
			
			String country = multi.getParameter("country");
			String maker = multi.getParameter("maker");
			String category = multi.getParameter("category");
			String model = multi.getParameter("model");
			String detail = multi.getParameter("detail");
			int birth = Integer.parseInt(multi.getParameter("birth"));
			int km = Integer.parseInt(multi.getParameter("km"));
			int price = Integer.parseInt(multi.getParameter("price"));
			String color = multi.getParameter("color");
			String fuel = multi.getParameter("fuel");
			String mission = multi.getParameter("mission");
			String[] options = multi.getParameterValues("options");
			String accident = multi.getParameter("accident");
			String seater = multi.getParameter("seater");
			String city = multi.getParameter("city");
			String writer = multi.getParameter("writer");
			String ip = InetAddress.getLocalHost().getHostAddress();
			String content = multi.getParameter("content").replaceAll("<p>", "").replaceAll("</p>", "<br/>");
			URLEncoder.encode(content, "UTF-8");
			String option = "";
			
			for (int i=0; i<options.length; i++) {
				if (i==options.length-1) {
					option += options[i];
				} else {
					option += options[i]+",";
				}
			}
			CarlistDto dto = new CarlistDto(country,maker,category,model,detail,birth,km,price,color,fuel,mission,option,accident,seater,city,writer,ip,image,content);
			
			int res = dao.addListCar(dto);
			dao.updateStatusSellList(Integer.parseInt(multi.getParameter("nom")));
			if (res>0) {	
				out.println("<script> alert('전산 등록 처리가 완료되었습니다.'); location.href='"+request.getContextPath()+"/sellerMyPage.do?id="+writer+"'; </script>");
			} else {
				out.println("<script> alert('모든 항목을 입력해주셔야합니다.'); history.go(-1); </script>");
			}
		} catch (Exception e) {
			out.println("<script> alert('모든 항목을 입력해주셔야합니다.'); history.go(-1); </script>");
		}
		
		
		
	}
}
