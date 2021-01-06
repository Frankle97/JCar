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
 * Servlet implementation class CarListAjax
 */
@WebServlet("/SearchModel")
public class SearchModel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchModel() {
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
		
		String sql = "select * from carlist where detail like ? or maker like ?";
		
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+request.getParameter("detail")+"%");
			pstmt.setString(2, "%"+request.getParameter("detail")+"%");
			rset = pstmt.executeQuery();
			JsonArray list = new JsonArray();
			while (rset.next()) {
				JsonObject obj = new JsonObject();
				obj.addProperty("no", rset.getInt("no"));
				obj.addProperty("birth", rset.getInt("birth"));
				obj.addProperty("km", rset.getInt("km"));
				obj.addProperty("price", rset.getInt("price"));
				obj.addProperty("country", rset.getString("country"));
				obj.addProperty("maker", rset.getString("maker"));
				obj.addProperty("category", rset.getString("category"));
				obj.addProperty("model", rset.getString("model"));
				obj.addProperty("detail", rset.getString("detail"));
				obj.addProperty("color", rset.getString("color"));
				obj.addProperty("fuel", rset.getString("fuel"));
				obj.addProperty("mission", rset.getString("mission"));
				obj.addProperty("options", rset.getString("options"));
				obj.addProperty("accident", rset.getString("accident"));
				obj.addProperty("seater", rset.getString("seater"));
				obj.addProperty("city", rset.getString("city"));
				obj.addProperty("writer", rset.getString("writer"));
				obj.addProperty("ip", rset.getString("ip"));
				obj.addProperty("date", rset.getString("date"));
				obj.addProperty("image", rset.getString("image"));
				obj.addProperty("content", rset.getString("content"));
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
