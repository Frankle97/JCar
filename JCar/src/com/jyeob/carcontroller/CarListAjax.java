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
import com.jyeob.dao.CarDao;
import com.jyeob.dbmanager.DBManager;

/**
 * Servlet implementation class CarListAjax
 */
@WebServlet("/CarListAjax")
public class CarListAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarListAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, NullPointerException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		CarDao dao = new CarDao();
		PrintWriter out = response.getWriter();
		String country = request.getParameter("country");
		String qq= "";
		int cnt = 0;
		String[] list3 = null;
		
		if (request.getParameter("list") != null) {
			cnt=0;
			list3 = request.getParameter("list").split("/");
			for (int i=0; i<list3.length; i++) {
				cnt++;				
				if (i!=list3.length-1) {
					qq+="?,";
				} else {
					qq+="?";
				}	
			}
		}
		
		
		String sql = "";
		String sql2 = "";
		
		int pstartno = 0;
		if (request.getParameter("no") != null) {
			pstartno = Integer.parseInt(request.getParameter("no"));
		}
		if (country.equals("국산")) {
			sql = "select * from carlist where country like '국산' limit ?, 10";
			sql2 = "select * from carlist where country like '국산'";
			cnt = dao.calcCnt("select count(*) from carlist where country like '국산'");
			if (request.getParameter("list") != null) { 
				sql = "select * from carlist where country like '국산' and no in ("+qq+") limit ?, 10";
				sql2 += " and no in ("+qq+")";
			}
			
		} else if (country.equals("수입")) {
			sql = "select * from carlist where country like '수입' limit ?, 10";
			sql2 = "select * from carlist where country like '수입'";
			if (request.getParameter("list") != null) { 
				sql = "select * from carlist where country like '수입' and no in ("+qq+") limit ?, 10";
				sql2 += " and no in ("+qq+")"; }
			cnt = dao.calcCnt("select count(*) from carlist where country like '수입'"); 
		} else if (country.equals("전체")) {
			sql = "select * from carlist limit ?, 10";
			sql2 = "select * from carlist";
			if (request.getParameter("list") != null) { 
				sql = "select * from carlist where no in ("+qq+") limit ?, 10";
				sql2 += " where no in ("+qq+")"; }
			cnt = dao.calcCnt("select count(*) from carlist");
		}
		if (request.getParameter("list") != null) {
			cnt=0;
			for (int i=0; i<list3.length; i++) {
				cnt++;				
			}
		}
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		Connection conn2 = null; PreparedStatement pstmt2 = null; ResultSet rset2 = null;
		try {
			conn2 = new DBManager().getConnection();
			pstmt2 = conn2.prepareStatement(sql2);
			if (list3 != null) {
				for (int i=0; i<list3.length; i++) {
					pstmt2.setString(i+1, list3[i]);
				}
			}
			
			rset2 = pstmt2.executeQuery();
			JsonArray list2 = new JsonArray();
			
			while (rset2.next()) {
				JsonObject obj2 = new JsonObject();
				obj2.addProperty("no", rset2.getInt("no"));
				list2.add(obj2);
				}
			
			
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			
			
			if (list3 != null) {
				for (int i=0; i<list3.length; i++) {
					pstmt.setString(i+1, list3[i]);
				}
				pstmt.setInt(list3.length+1, pstartno);
			} else {
				pstmt.setInt(1, pstartno);
			}
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
		
			int pageTotal = cnt;
			int onepageLimit = 10;
			int pageAll = (int)Math.ceil(pageTotal/(float)onepageLimit);
			int bottomList = 10;
			int bottom_current = (int)Math.ceil((pstartno+1)/(float)onepageLimit);
			int bottom_start=(int)Math.floor(((bottom_current-1)/(float)bottomList)) * bottomList + 1;

			int bottom_end=bottom_start+bottomList-1;
			if (pageAll < bottom_end) {bottom_end = pageAll;} 
			JsonObject obj = new JsonObject();
			obj.addProperty("pageTotal", pageTotal);
			obj.addProperty("onepageLimit", onepageLimit);
			obj.addProperty("pageAll", pageAll);
			obj.addProperty("pstartno", pstartno);
			obj.addProperty("bottomList", bottomList);
			obj.addProperty("bottom_current", bottom_current);
			obj.addProperty("bottom_start", bottom_start);
			obj.addProperty("bottom_end", bottom_end);
			Gson gson = new Gson();
			JsonObject menu = new JsonObject();
			menu.add("menu", list);
			menu.add("menu2", list2);
			menu.add("pag", obj);
			out.println(gson.toJson(menu));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {try{conn.close();}catch(Exception e) {e.printStackTrace();}}
			if (pstmt != null) {try{pstmt.close();}catch(Exception e) {e.printStackTrace();}}
			if (rset != null) {try{rset.close();}catch(Exception e) {e.printStackTrace();}}
			if (conn2 != null) {try{conn2.close();}catch(Exception e) {e.printStackTrace();}}
			if (pstmt2 != null) {try{pstmt2.close();}catch(Exception e) {e.printStackTrace();}}
			if (rset2 != null) {try{rset2.close();}catch(Exception e) {e.printStackTrace();}}
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String[] list = request.getParameter("list").split("/");
		int start = Integer.parseInt(request.getParameter("minyear").concat("00").substring(2));
		int end = Integer.parseInt(request.getParameter("maxyear").concat("12").substring(2));
		String qq= "";
		for (int i=0; i<list.length; i++) {
			if (i!=list.length-1) {
				qq+="?,";
			} else {
				qq+="?";
			}	
		}
		String sql = "select * from carlist where no in ("+qq+") and birth between ? and ?";
		if (list[0] == "") {
			sql = "select * from carlist where birth between ? and ?";
		}
		
		
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			if (list[0] != "") {
				for (int i=0; i<list.length; i++) {
					pstmt.setInt((i+1), Integer.parseInt(list[i]));
					if (i==list.length-1) {
						pstmt.setInt(i+2, start);
						pstmt.setInt(i+3, end);
					}
				}
			} else {
				pstmt.setInt(1, start);
				pstmt.setInt(2, end);
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

}
