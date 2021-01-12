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
@WebServlet("/CarListColor")
public class CarListColor extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarListColor() {
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
		
		String[] list = request.getParameter("list").split("/");
		String[] colors = request.getParameter("colors").split("/");
		String qq = "";
		for (int i = 0; i < list.length; i++) {
			if (i != list.length - 1) {
				qq += "?,";
			} else {
				qq += "?";
			}	
		}
		String qq2 = "";
		for (int i = 0; i < colors.length; i++) {
			if (i != colors.length - 1) {
				qq2 += "?,";
			} else {
				qq2 += "?";
			}	
		}
		String sql = "select * from carlist where no in (" + qq + ") and color in (" + qq2 + ")";
		if (list[0] == "") {
			sql = "select * from carlist where color in (" + qq2 + ") ";
		}
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			int aa = 0;
			if (list[0] != "") {
				for (int i = 0; i < list.length; i++) {
					pstmt.setString((i + 1), list[i]);
					aa = i;
				}
				for (int i = 0, j = aa+2; i < colors.length; i++, j++) {
					pstmt.setString(j, colors[i]);
				}
			} else {
				for (int i = 0; i < colors.length; i++) {
					pstmt.setString(i + 1, colors[i]);
				}
			} 
			rset = pstmt.executeQuery();
			JsonArray list2 = new JsonArray();
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
				list2.add(obj);
				}
			Gson gson = new Gson();
			JsonObject menu = new JsonObject();
			menu.add("menu", list2);
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
		}

}
