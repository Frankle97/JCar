package com.jyeob.membercontroller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.MemberDao;
import com.jyeob.dto.MemberDto;

public class UserList implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		ArrayList<MemberDto> list = new MemberDao().userList();
		request.setAttribute("list", list);
		request.setAttribute("cnt", new MemberDao().cntUsers());
		request.getRequestDispatcher("/admin/adminPage.jsp").forward(request, response);
	}

}
