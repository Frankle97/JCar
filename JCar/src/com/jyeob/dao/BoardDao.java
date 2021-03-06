package com.jyeob.dao;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.jyeob.dbmanager.DBManager;
import com.jyeob.dto.BoardDto;

public class BoardDao {
	public int addNotice(BoardDto dto) {
		int res = 0;
		String sql = "insert into noticeboard (category, title, content, ip) values (?,?,?,?)";
		Connection conn = null; 
		PreparedStatement pstmt = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getCategory());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, InetAddress.getLocalHost().getHostAddress());
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null || pstmt != null) {
				try{
					conn.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return res;
	}
	public int editNotice(BoardDto dto) {
		int res = 0;
		String sql = "update noticeboard set category=?, title=?, content=? where no=?";
		Connection conn = null; PreparedStatement pstmt = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getCategory());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getNo());
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (conn != null || pstmt != null) {
				try{
					conn.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return res;
	}
	public ArrayList<BoardDto> showNoticeAll(){
		ArrayList<BoardDto> list = new ArrayList<>();
		String sql = "select * from noticeboard order by no desc";
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			
			while(rset.next()) {
				list.add(new BoardDto(rset.getString("category"), rset.getString("title"), rset.getString("date"), rset.getInt("no"), rset.getInt("hits")));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null || pstmt != null || rset != null) {
				try {
					conn.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public BoardDto showNotice(int no) {
		BoardDto dto = new BoardDto();
		String sql = "select * from noticeboard where no=?";
		Connection conn = null; 
		PreparedStatement pstmt = null;
		PreparedStatement hitup = null; 
		ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			hitup = conn.prepareStatement("update noticeboard set hits=hits+1 where no=?");
			hitup.setInt(1, no);
			hitup.executeUpdate();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				dto.setTitle(rset.getString("title"));
				dto.setDate(rset.getString("date"));
				dto.setContent(rset.getString("content"));
				dto.setHits(rset.getInt("hits"));
				dto.setCategory(rset.getString("category"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null || pstmt != null || rset != null || hitup != null) {
				try {
					conn.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return dto;
	} 
	
	public int deleteNotice(int no) {
		int res = 0;
		String sql = "delete from noticeboard where no=?";
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		Statement stmt1 = null;
		Statement stmt2 = null;
		Statement stmt3 = null;
		try {
			conn = new DBManager().getConnection();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
			stmt3 = conn.createStatement();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			res = pstmt.executeUpdate();
			stmt1.executeUpdate("set @cnt = 0");
			stmt2.executeUpdate("update noticeboard set no = @cnt:=@cnt+1");
			stmt3.executeUpdate("alter table noticeboard auto_increment=1");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null || pstmt != null || stmt1 != null || stmt2 != null || stmt3 != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace(); 
				} 
			}
		}
		return res;
	}
	
	public int listCnt() {
		String sql = "select count(*) from noticeboard";
		int result = 0;
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null || pstmt != null || rset != null) {
				try {
					conn.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public int listCntSearch(String category) {
		String sql = "select count(*) from noticeboard where category like ?";
		int result = 0;
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null || pstmt != null || rset != null) {
				try {
					conn.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public int listCntSearchTitle(String category, String title) {
		String sql = "select count(*) from noticeboard where category like ? and title like ?";
		int result = 0;
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setString(2, "%" + title + "%");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null || pstmt != null || rset != null) { 
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public int listCntSearchContent(String category, String content) {
		String sql = "select count(*) from noticeboard where category like ? and content like ?";
		int result = 0;
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setString(2, "%" + content + "%");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null || pstmt != null || rset != null) { 
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	public int listCntSearchAnyTitle(String search) {
		String sql = "select count(*) from noticeboard where title like ?";
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null || pstmt != null || rset != null) { 
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	public int listCntSearchAnyContent(String search) {
		String sql = "select count(*) from noticeboard where content like ?";
		int result = 0;
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null || pstmt != null || rset != null) { 
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	public int listCntSearchAnyAll(String search) {
		String sql = "select count(*) from noticeboard where title like ? or content like ?";
		int result = 0;
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			pstmt.setString(2, "%" + search + "%");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null || pstmt != null || rset != null) { 
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public int listCntSearchAll(String category, String search) {
		String sql = "select distinct (select count(*) from noticeboard where category=?) from noticeboard where title like ? or content like ?";
		int result = 0;
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setString(2, "%" + search + "%");
			pstmt.setString(3, "%" + search + "%");
			rset = pstmt.executeQuery();
			while(rset.next()) {
				result = rset.getInt(1);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null || pstmt != null || rset != null) { 
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	public ArrayList<BoardDto> showNoticeListPaging(int pstartno){
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		String sql = "select * from noticeboard order by no desc limit ?, 10";
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pstartno);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new BoardDto(rset.getString("category"), 
						rset.getString("title"), 
						rset.getString("date"), 
						rset.getInt("no"), 
						rset.getInt("hits")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null || pstmt != null || rset != null) { 
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	
	public ArrayList<BoardDto> showNoticeListPagingSearch(int pstartno, String category){
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		Connection conn = null; 
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = "select * from noticeboard where category like ? order by no desc limit ?, 10";
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, pstartno);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new BoardDto(rset.getString("category"), 
						rset.getString("title"), 
						rset.getString("date"), 
						rset.getInt("no"), 
						rset.getInt("hits")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null || pstmt != null || rset != null) { 
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	
	public ArrayList<BoardDto> showNoticeListPagingSearchTitle(int pstartno, String category, String search){
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		Connection conn = null;
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		String sql = "select * from noticeboard where category like ? and title like ? order by no desc limit ?, 10";
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setString(2, "%" + search + "%");
			pstmt.setInt(3, pstartno);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new BoardDto(rset.getString("category"), 
						rset.getString("title"), 
						rset.getString("date"), 
						rset.getInt("no"), 
						rset.getInt("hits")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null || pstmt != null || rset != null) { 
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	public ArrayList<BoardDto> showNoticeListPagingSearchContent(int pstartno, String category, String search){
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		String sql = "select * from noticeboard where category like ? and content like ? order by no desc limit ?, 10";
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setString(2, "%" + search + "%");
			pstmt.setInt(3, pstartno);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new BoardDto(rset.getString("category"), 
						rset.getString("title"), 
						rset.getString("date"), 
						rset.getInt("no"), 
						rset.getInt("hits")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null || pstmt != null || rset != null) { 
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return list;
	}
	public ArrayList<BoardDto> showNoticeListPagingSearchAnyTitle(int pstartno, String search){
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		String sql = "select * from noticeboard where title like ? order by no desc limit ?, 10";
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			pstmt.setInt(2, pstartno);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new BoardDto(rset.getString("category"), 
						rset.getString("title"), 
						rset.getString("date"), 
						rset.getInt("no"), 
						rset.getInt("hits")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null || pstmt != null || rset != null) { 
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public ArrayList<BoardDto> showNoticeListPagingSearchAnyContent(int pstartno, String search){
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		String sql = "select * from noticeboard where content like ? order by no desc limit ?, 10";
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			pstmt.setInt(2, pstartno);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new BoardDto(rset.getString("category"), 
						rset.getString("title"), 
						rset.getString("date"), 
						rset.getInt("no"), 
						rset.getInt("hits")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null || pstmt != null || rset != null) { 
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public ArrayList<BoardDto> showNoticeListPagingSearchAnyAll(int pstartno, String search){
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		String sql = "select * from noticeboard where content like ? or title like ? order by no desc limit ?, 10";
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + search + "%");
			pstmt.setString(2, "%" + search + "%");
			pstmt.setInt(3, pstartno);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new BoardDto(rset.getString("category"), 
						rset.getString("title"), 
						rset.getString("date"), 
						rset.getInt("no"), 
						rset.getInt("hits")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null || pstmt != null || rset != null) { 
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
	public ArrayList<BoardDto> showNoticeListPagingSearchAll(int pstartno, String category, String search){
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		String sql = "select * from noticeboard where category like ? and (content like ? or title like ?) order by no desc limit ?, 10;";
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + category + "%");
			pstmt.setString(2, "%" + search + "%");
			pstmt.setString(3, "%" + search + "%");
			pstmt.setInt(4, pstartno);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new BoardDto(rset.getString("category"), 
						rset.getString("title"), 
						rset.getString("date"), 
						rset.getInt("no"), 
						rset.getInt("hits")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null || pstmt != null || rset != null) { 
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
}
