package com.jyeob.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.jyeob.dbmanager.DBManager;
import com.jyeob.dto.SellerDto;

public class SellerDao {
	DBManager db = new DBManager();
	
	public int addSellerList(SellerDto dto) {
		int res = 0;
		String sql = "insert into sellerlist (id,name,phone,country,model,birth) values (?,?,?,?,?,?)";
		Connection conn = null; 
		PreparedStatement pstmt = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPhone());
			pstmt.setString(4, dto.getCountry());
			pstmt.setString(5, dto.getModel().toUpperCase());
			pstmt.setInt(6, dto.getBirth());
			res = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	
	public int addSellerListAdmin(SellerDto dto) {
		int res = 0;
		String sql = "insert into sellerlist (id,name,phone,country,model,birth,status) values (?,?,?,?,?,?,'판매중')";
		Connection conn = null; 
		PreparedStatement pstmt = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getPhone());
			pstmt.setString(4, dto.getCountry());
			pstmt.setString(5, dto.getModel().toUpperCase());
			pstmt.setInt(6, dto.getBirth());
			res = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	public SellerDto findMyOrder(String id) {
		SellerDto dto = new SellerDto();
		String sql = "select * from sellerlist where id like ?";
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			while(rset.next()) {
			dto.setNo(rset.getInt("no"));	
			dto.setName(rset.getString("name"));
			dto.setModel(rset.getString("model"));
			dto.setDate(rset.getString("date"));
			dto.setStatus(rset.getString("status"));
			dto.setBirth(Integer.parseInt(rset.getString("birth")));
			dto.setId(rset.getString("id"));
			dto.setPhone(rset.getString("phone"));
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
		return dto;
	}
	
	public int removeMyOrder(String id) {
		int res = 0;
		String sql = "delete from sellerlist where id like ?";
		Connection conn = null; 
		PreparedStatement pstmt = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	
	public int removeOrder(int no) {
		int res = 0;
		String sql = "delete from sellerlist where no=?";
		Connection conn = null; 
		PreparedStatement pstmt = null;
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	
	public ArrayList<SellerDto> allSellList(String chk){
		ArrayList<SellerDto> list = new ArrayList<>();
		String sql = "select * from sellerlist where status like ?";
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, chk);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new SellerDto(rset.getInt("no"), 
						rset.getInt("birth"), 
						rset.getString("country"), 
						rset.getString("model"), 
						rset.getString("writer"), 
						rset.getString("status"), 
						rset.getString("name"),
						rset.getString("phone"), 
						rset.getString("id"), 
						rset.getString("date")));
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
	
	public ArrayList<SellerDto> allSellListAdmin(){
		ArrayList<SellerDto> list = new ArrayList<>();
		String sql = "select * from sellerlist";
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new SellerDto(rset.getInt("no"), 
						rset.getInt("birth"), 
						rset.getString("country"), 
						rset.getString("model"), 
						rset.getString("writer"), 
						rset.getString("status"), 
						rset.getString("name"),
						rset.getString("phone"), 
						rset.getString("id"), 
						rset.getString("date")));
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
	
	public int updateSellList(int no, String id) {
		int res = 0;
		String sql = "update sellerlist set status='승인완료', writer=? where no=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, no);
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	public int releaseSellList(int no) {
		int res = 0;
		String sql = "update sellerlist set status='승인대기', writer=null where no=?";
		Connection conn = null; 
		PreparedStatement pstmt = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	public ArrayList<SellerDto> SellListMyPosition(String name){
		ArrayList<SellerDto> list = new ArrayList<>();
		String sql = "select * from sellerlist where status in ('승인완료', '판매중') and writer like ? order by status desc";
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new SellerDto(rset.getInt("no"), 
						rset.getInt("birth"), 
						rset.getString("country"), 
						rset.getString("model"), 
						rset.getString("writer"), 
						rset.getString("status"), 
						rset.getString("name"),
						rset.getString("phone"), 
						rset.getString("id"), 
						rset.getString("date")));
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
	
	public SellerDto findMyOrder2(String id) {
		SellerDto dto = new SellerDto();
		String sql = "select * from sellerlist where writer like ?";
		Connection conn = null; 
		PreparedStatement pstmt = null; 
		ResultSet rset = null;
		
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			while(rset.next()) {
			dto.setNo(rset.getInt("no"));	
			dto.setName(rset.getString("name"));
			dto.setModel(rset.getString("model"));
			dto.setDate(rset.getString("date"));
			dto.setStatus(rset.getString("status"));
			dto.setBirth(rset.getInt("birth"));
			dto.setId(rset.getString("id"));
			dto.setPhone(rset.getString("phone"));
			dto.setCountry(rset.getString("country"));
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
		return dto;
	}
}
