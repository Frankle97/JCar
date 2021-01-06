package com.jyeob.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.jyeob.dbmanager.DBManager;
import com.jyeob.dto.MemberDto;

public class MemberDao {
	public int joinMember(MemberDto dto) {
		int res = 0;
		boolean same = false;
		String sql = "insert into member (name,birth,gender,id,password,contact,email,ip,address, category) values (?,?,?,?,?,?,?,?,?,?)";
		Connection conn = null; PreparedStatement pstmt = null, forchk = null; ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getName());
			pstmt.setString(2, dto.getBirth());
			pstmt.setString(3, dto.getGender());
			pstmt.setString(4, dto.getId());
			pstmt.setString(5, dto.getPassword());
			pstmt.setString(6, dto.getContact());
			pstmt.setString(7, dto.getEmail());
			pstmt.setString(8, dto.getIp());
			pstmt.setString(9, dto.getAddress());
			pstmt.setString(10, dto.getCategory());
			forchk = conn.prepareStatement("select id from member");
			rset = forchk.executeQuery();
			while(rset.next()) {
				if (rset.getString("id").equals(dto.getId())) {
					same=true;
				}
			}
			if (!same) {
				res = pstmt.executeUpdate();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null) {try{conn.close();}catch(Exception e) {e.printStackTrace();}}
			if (pstmt!=null) {try{pstmt.close();}catch(Exception e) {e.printStackTrace();}}
			if (forchk!=null) {try{forchk.close();}catch(Exception e) {e.printStackTrace();}}
			if (rset!=null) {try{rset.close();}catch(Exception e) {e.printStackTrace();}}
		}
		return res;
	}
	public int cntUsers() {
		int res = 0;
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		String sql = "select count(*) from member";
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if (rset.next()) { res = rset.getInt(1); }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null) {try{conn.close();}catch(Exception e) {e.printStackTrace();}}
			if (pstmt!=null) {try{pstmt.close();}catch(Exception e) {e.printStackTrace();}}
			if (rset!=null) {try{rset.close();}catch(Exception e) {e.printStackTrace();}}
		}
		return res;
	}
	
	
	public ArrayList<MemberDto> userList() {
		ArrayList<MemberDto> list = new ArrayList<>();
		String sql = "select * from member";
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				list.add(new MemberDto(rset.getInt("no"),rset.getString("name"),rset.getString("birth"),rset.getString("gender"),rset.getString("id"),rset.getString("password"),rset.getString("contact"),rset.getString("email"),rset.getString("ip"),rset.getString("date"), rset.getString("address"), rset.getString("category")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null) {try{conn.close();}catch(Exception e) {e.printStackTrace();}}
			if (pstmt!=null) {try{pstmt.close();}catch(Exception e) {e.printStackTrace();}}
			if (rset!=null) {try{rset.close();}catch(Exception e) {e.printStackTrace();}}
		}
		return list;
	}
	
	public MemberDto findAccount(String id) {
		MemberDto dto = new MemberDto();
		String sql = "select * from member where id=?";
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				dto.setNo(rset.getInt("no"));
				dto.setName(rset.getString("name"));
				dto.setBirth(rset.getString("birth"));
				dto.setGender(rset.getString("gender"));
				dto.setId(rset.getString("id"));
				dto.setPassword(rset.getString("password"));
				dto.setContact(rset.getString("contact"));
				dto.setEmail(rset.getString("email"));
				dto.setIp(rset.getString("ip"));
				dto.setDate(rset.getString("date"));
				dto.setAddress(rset.getString("address"));
				dto.setCategory(rset.getString("category"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null) {try{conn.close();}catch(Exception e) {e.printStackTrace();}}
			if (pstmt!=null) {try{pstmt.close();}catch(Exception e) {e.printStackTrace();}}
			if (rset!=null) {try{rset.close();}catch(Exception e) {e.printStackTrace();}}
		}
		return dto;
	}
	public MemberDto findAccountEmail(String email) {
		MemberDto dto = new MemberDto();
		String sql = "select * from member where email=?";
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, email);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				dto.setNo(rset.getInt("no"));
				dto.setName(rset.getString("name"));
				dto.setBirth(rset.getString("birth"));
				dto.setGender(rset.getString("gender"));
				dto.setId(rset.getString("id"));
				dto.setPassword(rset.getString("password"));
				dto.setContact(rset.getString("contact"));
				dto.setEmail(rset.getString("email"));
				dto.setIp(rset.getString("ip"));
				dto.setDate(rset.getString("date"));
				dto.setAddress(rset.getString("address"));
				dto.setCategory(rset.getString("category"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null) {try{conn.close();}catch(Exception e) {e.printStackTrace();}}
			if (pstmt!=null) {try{pstmt.close();}catch(Exception e) {e.printStackTrace();}}
			if (rset!=null) {try{rset.close();}catch(Exception e) {e.printStackTrace();}}
		}
		return dto;
	}
	public MemberDto findMyID(String name, String email) {
		MemberDto dto = new MemberDto();
		String sql = "select * from member where name=? and email=?";
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				dto.setId(rset.getString("id"));
				dto.setDate(rset.getString("date"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null) {try{conn.close();}catch(Exception e) {e.printStackTrace();}}
			if (pstmt!=null) {try{pstmt.close();}catch(Exception e) {e.printStackTrace();}}
			if (rset!=null) {try{rset.close();}catch(Exception e) {e.printStackTrace();}}
		}
		
		return dto;
	}
	public int findMyPW(String id, String name) {
		int res = 0;
		String sql = "select no from member where id=? and name=?";
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
		
			rset = pstmt.executeQuery();
			if (rset.next()) {
				res = rset.getInt("no");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null) {try{conn.close();}catch(Exception e) {e.printStackTrace();}}
			if (pstmt!=null) {try{pstmt.close();}catch(Exception e) {e.printStackTrace();}}
			if (rset!=null) {try{rset.close();}catch(Exception e) {e.printStackTrace();}}
		}
		
		return res;
	}
	public int changePW(String password, String no) {
		int res = 0;
		String sql = "update member set password=? where no=?";
		Connection conn = null; PreparedStatement pstmt = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, no);
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null) {try{conn.close();}catch(Exception e) {e.printStackTrace();}}
			if (pstmt!=null) {try{pstmt.close();}catch(Exception e) {e.printStackTrace();}}
		}
		
		return res;
	}
	
	public MemberDto login(String id, String pass) {
		MemberDto dto = new MemberDto();
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		String sql = "select * from member where id=? and password=?";
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				dto.setNo(rset.getInt("no"));
				dto.setName(rset.getString("name"));
				dto.setBirth(rset.getString("birth"));
				dto.setGender(rset.getString("gender"));
				dto.setId(rset.getString("id"));
				dto.setPassword(rset.getString("password"));
				dto.setContact(rset.getString("contact"));
				dto.setEmail(rset.getString("email"));
				dto.setIp(rset.getString("ip"));
				dto.setDate(rset.getString("date"));
				dto.setAddress(rset.getString("address"));
				dto.setCategory(rset.getString("category"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null) {try{conn.close();}catch(Exception e) {e.printStackTrace();}}
			if (pstmt!=null) {try{pstmt.close();}catch(Exception e) {e.printStackTrace();}}
			if (rset!=null) {try{rset.close();}catch(Exception e) {e.printStackTrace();}}
		}
		return dto;
	}
	public int changeInfo(String id, String password, String email, String contact, String address) {
		int res = 0;
		Connection conn = null; PreparedStatement pstmt = null;
		String sql = "update member set password=?, email=?, contact=?, address=? where id=?";
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, password);
			pstmt.setString(2, email);
			pstmt.setString(3, contact);
			pstmt.setString(4, address);
			pstmt.setString(5, id);
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null) {try{conn.close();}catch(Exception e) {e.printStackTrace();}}
			if (pstmt!=null) {try{pstmt.close();}catch(Exception e) {e.printStackTrace();}}
			
		}
		return res;
	}
	public int removeAccount(String id, String pass) {
		int res = 0;
		Connection conn = null; PreparedStatement pstmt = null; 
		String sql = "delete from member where id=? and password=?";
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null) {try{conn.close();}catch(Exception e) {e.printStackTrace();}}
			if (pstmt!=null) {try{pstmt.close();}catch(Exception e) {e.printStackTrace();}}
			
		}
		
		
		return res;
	}
	public int removeAccountForAdmin(String id) {
		int res = 0;
		Connection conn = null; PreparedStatement pstmt = null; 
		String sql = "delete from member where id=?";
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn!=null) {try{conn.close();}catch(Exception e) {e.printStackTrace();}}
			if (pstmt!=null) {try{pstmt.close();}catch(Exception e) {e.printStackTrace();}}
			
		}

		return res;
	}
}
