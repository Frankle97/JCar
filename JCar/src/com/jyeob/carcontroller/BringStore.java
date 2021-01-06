package com.jyeob.carcontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jyeob.dao.CarDao;
import com.jyeob.dto.StoreDto;

/**
 * Servlet implementation class BringStore
 */
@WebServlet("/BringStore")
public class BringStore extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BringStore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		String store = request.getParameter("loc");
		StoreDto dto = new CarDao().storeInfo(store);
		int cnt = new CarDao().storeCnt(store);
		JsonObject json = new JsonObject();
		json.addProperty("location", dto.getLocation());
		json.addProperty("number", dto.getNumber());
		json.addProperty("x", dto.getX());
		json.addProperty("y", dto.getY());
		json.addProperty("address", dto.getAddress());
		json.addProperty("cnt", cnt);
		Gson gson = new Gson();
		out.println(gson.toJson(json));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
