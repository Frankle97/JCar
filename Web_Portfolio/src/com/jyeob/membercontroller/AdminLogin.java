package com.jyeob.membercontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jyeob.dao.MemberDao;
import com.jyeob.dto.MemberDto;

/**
 * Servlet implementation class adminLogin
 */
@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		MemberDto dto = new MemberDao().login("admin", "admin1234");
		if (request.getParameter("remember_id") != null) {
			session.setAttribute("id", "admin1234");
		} else {
			session.invalidate();
		}
		if (dto.getId() != null) {
			Cookie cookie = new Cookie("id", "admin");
			cookie.setMaxAge(60*30);
			response.addCookie(cookie);
		} 
			out.println("<script> alert('관리자로그인 성공');");
			out.println("location.href='"+request.getContextPath()+"/car.do'; </script>");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
