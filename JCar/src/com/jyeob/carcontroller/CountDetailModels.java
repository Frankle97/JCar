package com.jyeob.carcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jyeob.dbmanager.DBManager;

/**
 * Servlet implementation class CountDetailModels
 */
@WebServlet("/CountDetailModels")
public class CountDetailModels extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountDetailModels() {
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
		String sql = "select detail, count(detail) from carlist where model like ? group by detail";
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, request.getParameter("model"));
			rset = pstmt.executeQuery();
			JsonArray list = new JsonArray();
			while(rset.next()) {
				JsonObject obj = new JsonObject();
				obj.addProperty("detail", rset.getString(1));
				obj.addProperty("cnt", rset.getInt(2));
				list.add(obj);
			}
			Gson gson = new Gson();
			JsonObject menu = new JsonObject();
			menu.add("menu", list);
			out.println(gson.toJson(menu));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {try{conn.close();}catch(Exception e) {e.printStackTrace();}}
			if (pstmt != null) {try{pstmt.close();}catch(Exception e) {e.printStackTrace();}}
			if (rset != null) {try{rset.close();}catch(Exception e) {e.printStackTrace();}}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
