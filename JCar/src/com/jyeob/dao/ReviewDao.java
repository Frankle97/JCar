package com.jyeob.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.jyeob.dbmanager.DBManager;
import com.jyeob.dto.ReviewDto;

public class ReviewDao {
	
	public ArrayList<ReviewDto> showAll(){
		ArrayList<ReviewDto> list = new ArrayList<>();
		String sql = "select * from reviewboard";
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
			list.add(new ReviewDto(rset.getString("writer"), 
					rset.getString("title"), 
					rset.getString("content"), 
					rset.getString("date"), 
					rset.getString("image"), 
					rset.getString("model"), 
					rset.getInt("no"), 
					rset.getInt("hits")));	
			}
		
		} catch (Exception e) {
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
	
	public int proveUser(String id, int no) {
		int res = 0;
		String sql = "select * from reviewboard where writer=? and no=?";
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, no);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				res = 1;
			}
		
		} catch (Exception e) {
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
		return res;
	}
	
	public int RemoveReview(String id, int no) {
		int res = 0;
		String sql = "delete from reviewboard where writer=? and no=?";
		Connection conn = null; 
		PreparedStatement pstmt = null;
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, no);
			res = pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn!=null || pstmt != null) { 
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}	
		return res;
	}
	
	public int RemoveReviewAdmin(int no) {
		int res = 0;
		String sql = "delete from reviewboard where no=?";
		Connection conn = null; 
		PreparedStatement pstmt = null;
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			res = pstmt.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn!=null || pstmt != null) { 
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
		int res = 0;
		String sql = "select count(*) from reviewboard";
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				res = rset.getInt(1);
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
		return res;
	}

	public ArrayList<ReviewDto> list10(int pstartno) {
		ArrayList<ReviewDto> list = new ArrayList<>();
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		String sql = "select * from reviewboard order by date desc limit ?, 10";
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pstartno);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new ReviewDto(rset.getString("writer"), 
						rset.getString("title"), 
						rset.getString("content"), 
						rset.getString("date"), 
						rset.getString("image"), 
						rset.getString("model"), 
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
	
	public ReviewDto DetailReview(int no) {
		ReviewDto dto = new ReviewDto();
		String sql = "select * from reviewboard where no=?";
		Connection conn = null; 
		PreparedStatement pstmt = null;
		PreparedStatement hitup = null; 
		ResultSet rset = null;
		
		try {
			conn = new DBManager().getConnection();
			hitup = conn.prepareStatement("update reviewboard set hits=hits+1 where no=?");
			hitup.setInt(1, no);
			hitup.executeUpdate();
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				dto.setNo(no);
				dto.setWriter(rset.getString("writer"));
				dto.setTitle(rset.getString("title"));
				dto.setDate(rset.getString("date"));
				dto.setContent(rset.getString("content"));
				dto.setHits(rset.getInt("hits"));
				dto.setImage(rset.getString("image"));
				dto.setModel(rset.getString("model"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null || pstmt != null || rset != null || hitup != null) { 
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return dto;
	}

	public int EditReview(ReviewDto dto) {
		int res = 0;
		String sql = "update reviewboard set title=?, content=?, image=? where no=?";
		Connection conn = null; 
		PreparedStatement pstmt = null;
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setString(3, dto.getImage());
			pstmt.setInt(4, dto.getNo());
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (conn!=null || pstmt != null) { 
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return res;
	} 
	
	public int writeReview(ReviewDto dto) {
		int res = 0;
		String sql = "insert into reviewboard (writer, title, content, image, model) values (?,?,?,?,?)";
		Connection conn = null; 
		PreparedStatement pstmt = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getWriter());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getImage());
			pstmt.setString(5, dto.getModel());
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (conn!=null || pstmt != null) { 
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return res;
	}
}
