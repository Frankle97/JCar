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
 * Servlet implementation class List
 */
@WebServlet("/CountMakers")
public class CountMakers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountMakers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String country = request.getParameter("country");
	
		if (!(country.equals("전체"))) {
			PrintWriter out = response.getWriter();
			DBManager db = new DBManager();
			String sql = "select maker, count(*) from carlist where country like ? group by maker order by count(*) desc";
			Connection conn = null; 
			PreparedStatement pstmt = null; 
			ResultSet rset = null;
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, request.getParameter("country"));
				rset = pstmt.executeQuery();
				JsonArray list = new JsonArray();
				while(rset.next()) {
					JsonObject obj = new JsonObject();
					obj.addProperty("maker", rset.getString(1));
					obj.addProperty("cnt", rset.getInt(2));
					list.add(obj);
				}
				Gson gson = new Gson();
				JsonObject menu = new JsonObject();
				menu.add("menu", list);
				out.println(gson.toJson(menu));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try{
						conn.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (pstmt != null) {
					try{
						pstmt.close();
					} catch(Exception e) {
						e.printStackTrace();}
					}
				if (rset != null) {
					try{
						rset.close();
					} catch (Exception e) {
						e.printStackTrace();}
				}
			}
		} else {
			PrintWriter out = response.getWriter();
			DBManager db = new DBManager();
			String sql = "select maker, count(*) from carlist group by maker order by count(*) desc";
			Connection conn = null; 
			PreparedStatement pstmt = null; 
			ResultSet rset = null;
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				rset = pstmt.executeQuery();
				JsonArray list = new JsonArray();
				while(rset.next()) {
					JsonObject obj = new JsonObject();
					obj.addProperty("maker", rset.getString(1));
					obj.addProperty("cnt", rset.getInt(2));
					list.add(obj);
				}
				Gson gson = new Gson();
				JsonObject menu = new JsonObject();
				menu.add("menu", list);
				out.println(gson.toJson(menu));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try{
						conn.close();
					} catch (Exception e) {
						e.printStackTrace();}
					}
				if (pstmt != null) {
					try{
						pstmt.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (rset != null) {
					try{
						rset.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}	
		}
	}
			

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String country = request.getParameter("country");
		PrintWriter out = response.getWriter();
		
		String[] arr = request.getParameter("category").split("/");
		String qq = "";
		for (int i = 0; i < arr.length; i++) {
			if (i != arr.length - 1) {
				qq += "?,";
			} else {
				qq += "?";
			}	
		}
		
		if (!(country.equals("전체"))) {
			
			DBManager db = new DBManager();
			String sql = "select maker, count(*) from carlist where category in (" + qq + ") and country like ? group by maker order by count(*) desc";
			Connection conn = null; 
			PreparedStatement pstmt = null; 
			ResultSet rset = null;
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				for (int i=0; i < arr.length; i++) {
					pstmt.setString((i + 1), arr[i]);
					if (i == arr.length - 1) {
						pstmt.setString((i + 2), country);
					}
				}
				rset = pstmt.executeQuery();
				JsonArray list = new JsonArray();
				while(rset.next()) {
					JsonObject obj = new JsonObject();
					obj.addProperty("maker", rset.getString(1));
					obj.addProperty("cnt", rset.getInt(2));
					list.add(obj);
				}
				Gson gson = new Gson();
				JsonObject menu = new JsonObject();				
				menu.add("menu", list);
				out.println(gson.toJson(menu));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try{conn.close();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				if (pstmt != null) {
					try{
						pstmt.close();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
				if (rset != null) {
					try{
						rset.close();
					} catch(Exception e) {
						e.printStackTrace();
					}
				}
			}
		} else {
			DBManager db = new DBManager();
			String sql = "select maker, count(*) from carlist where category in (" + qq + ") group by maker order by count(*) desc";
			Connection conn = null; 
			PreparedStatement pstmt = null; 
			ResultSet rset = null;
			try {
				conn = db.getConnection();
				pstmt = conn.prepareStatement(sql);
				for (int i = 0; i < arr.length; i++) {
					pstmt.setString((i + 1), arr[i]);
				}
				rset = pstmt.executeQuery();
				JsonArray list = new JsonArray();
				while(rset.next()) {
					JsonObject obj = new JsonObject();
					obj.addProperty("maker", rset.getString(1));
					obj.addProperty("cnt", rset.getInt(2));
					list.add(obj);
				}
				Gson gson = new Gson();
				JsonObject menu = new JsonObject();
				menu.add("menu", list);
				out.println(gson.toJson(menu));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try{
						conn.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (pstmt != null) {
					try{
						pstmt.close();
					}
					catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (rset != null) {
					try{
						rset.close();
					}
					catch (Exception e) {
					e.printStackTrace();
					}
				}
			}
		}
	}
}
