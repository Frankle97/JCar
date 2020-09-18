package com.jyeob.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.jyeob.dbmanager.DBManager;
import com.jyeob.dto.CarlistDto;
import com.jyeob.dto.StoreDto;

public class CarDao {
	public ArrayList<CarlistDto> listCarSearch(String search){
		ArrayList<CarlistDto> list = new ArrayList<>();
		String sql = "select * from carlist where detail like ?";
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+search+"%");
			rset = pstmt.executeQuery();
			while (rset.next()) {
				list.add(new CarlistDto(rset.getInt("no"), rset.getInt("birth"), rset.getInt("km"), rset.getInt("price"), rset.getString("country"), rset.getString("maker"), rset.getString("category"), rset.getString("model"), rset.getString("detail"), rset.getString("color"), rset.getString("fuel"), rset.getString("mission"), rset.getString("options"), rset.getString("accident"), rset.getString("seater"), rset.getString("city"), rset.getString("writer"), rset.getString("ip"), rset.getString("date"), rset.getString("image"), rset.getString("content")));
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {try{conn.close();}catch(Exception e) {e.printStackTrace();}}
			if (pstmt != null) {try{pstmt.close();}catch(Exception e) {e.printStackTrace();}}
			if (rset != null) {try{rset.close();}catch(Exception e) {e.printStackTrace();}}
		}
		
		return list;
	}
	public int calcCnt(String sql) {
		int res = 0;
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				res = rset.getInt(1);
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {try{conn.close();}catch(Exception e) {e.printStackTrace();}}
			if (pstmt != null) {try{pstmt.close();}catch(Exception e) {e.printStackTrace();}}
			if (rset != null) {try{rset.close();}catch(Exception e) {e.printStackTrace();}}
		}
		return res;
	}
	
	public ArrayList<CarlistDto> listCarSearchPrice(String maker, int min, int max){
		ArrayList<CarlistDto> list = new ArrayList<>();
		String sql = "select * from carlist where maker like ? and price between ? and ?";
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, maker);
			pstmt.setInt(2, min);
			pstmt.setInt(3, max);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				list.add(new CarlistDto(rset.getInt("no"), rset.getInt("birth"), rset.getInt("km"), rset.getInt("price"), rset.getString("country"), rset.getString("maker"), rset.getString("category"), rset.getString("model"), rset.getString("detail"), rset.getString("color"), rset.getString("fuel"), rset.getString("mission"), rset.getString("options"), rset.getString("accident"), rset.getString("seater"), rset.getString("city"), rset.getString("writer"), rset.getString("ip"), rset.getString("date"), rset.getString("image"), rset.getString("content")));
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {try{conn.close();}catch(Exception e) {e.printStackTrace();}}
			if (pstmt != null) {try{pstmt.close();}catch(Exception e) {e.printStackTrace();}}
			if (rset != null) {try{rset.close();}catch(Exception e) {e.printStackTrace();}}
		}
		
		return list;
	}
	public ArrayList<CarlistDto> listCar(){
		ArrayList<CarlistDto> list = new ArrayList<>();
		String sql = "select * from carlist";
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				list.add(new CarlistDto(rset.getInt("no"), rset.getInt("birth"), rset.getInt("km"), rset.getInt("price"), rset.getString("country"), rset.getString("maker"), rset.getString("category"), rset.getString("model"), rset.getString("detail"), rset.getString("color"), rset.getString("fuel"), rset.getString("mission"), rset.getString("options"), rset.getString("accident"), rset.getString("seater"),  rset.getString("city"), rset.getString("writer"), rset.getString("ip"), rset.getString("date"), rset.getString("image"), rset.getString("content")));
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {try{conn.close();}catch(Exception e) {e.printStackTrace();}}
			if (pstmt != null) {try{pstmt.close();}catch(Exception e) {e.printStackTrace();}}
			if (rset != null) {try{rset.close();}catch(Exception e) {e.printStackTrace();}}
		}
		
		return list;
	}
	public int cntCar() {
		int res = 0;
		DBManager db = new DBManager();
		String sql = "select count(*) from carlist";
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = db.getConnection();
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				res = rset.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (conn != null) {try{conn.close();}catch(Exception e) {e.printStackTrace();}}
			if (pstmt != null) {try{pstmt.close();}catch(Exception e) {e.printStackTrace();}}
			if (rset != null) {try{rset.close();}catch(Exception e) {e.printStackTrace();}}
		}
		return res;
	}
	
	
	
	
	///////////////페이징/////////////////////

	public ArrayList<CarlistDto> list10(int pstartno) {
		ArrayList<CarlistDto> list = new ArrayList<>();
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		String sql = "select * from carlist order by price desc limit ?, 10";
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, pstartno);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new CarlistDto(rset.getInt("no"), rset.getInt("birth"), rset.getInt("km"), rset.getInt("price"), rset.getString("country"), rset.getString("maker"), rset.getString("category"), rset.getString("model"), rset.getString("detail"), rset.getString("color"), rset.getString("fuel"), rset.getString("mission"), rset.getString("options"), rset.getString("accident"), rset.getString("seater"),  rset.getString("city"), rset.getString("writer"), rset.getString("ip"), rset.getString("date"), rset.getString("image"), rset.getString("content")));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {try {conn.close();} catch(Exception e) {e.printStackTrace();}}
			if (pstmt != null) {try {pstmt.close();} catch(Exception e) {e.printStackTrace();}}
			if (rset != null) {try {rset.close();} catch(Exception e) {e.printStackTrace();}}
		}
		return list;
	}
	
	public int listCnt() {	
		int res = 0;
//		String qq= "";
//		for (int i=0; i<list.length; i++) {
//			if (i!=list.length-1) {
//				qq+="?,";
//			} else {
//				qq+="?";
//			}	
//		}
//		String sql = "select count(*) from carlist where no in ("+qq+")";
		String sql = "select count(*) from carlist";
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
//			for (int i=0; i<list.length; i++) {
//				pstmt.setString(i+1, list[i]);
//			}
			rset = pstmt.executeQuery();
			if (rset.next()) {
				res = rset.getInt(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {try {conn.close();} catch(Exception e) {e.printStackTrace();}}
			if (pstmt != null) {try {pstmt.close();} catch(Exception e) {e.printStackTrace();}}
			if (rset != null) {try {rset.close();} catch(Exception e) {e.printStackTrace();}}
		}
		
		return res;
	}
	
	
	
	public int addListCar(CarlistDto dto) {
		int res = 0;
		String sql = "insert into carlist (birth, km, price, country, model, detail, color, fuel, mission, options, accident, seater, city, writer, ip, image, content, maker, category) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		Connection conn = null; PreparedStatement pstmt = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getBirth());
			pstmt.setInt(2, dto.getKm());
			pstmt.setInt(3, dto.getPrice());
			pstmt.setString(4, dto.getCountry());
			pstmt.setString(5, dto.getModel().toUpperCase());
			pstmt.setString(6, dto.getDetail().toUpperCase());
			pstmt.setString(7, dto.getColor());
			pstmt.setString(8, dto.getFuel());
			pstmt.setString(9, dto.getMission());
			pstmt.setString(10, dto.getOptions());
			pstmt.setString(11, dto.getAccident());
			pstmt.setString(12, dto.getSeater());
			pstmt.setString(13, dto.getCity());
			pstmt.setString(14, dto.getWriter());
			pstmt.setString(15, dto.getIp());
			pstmt.setString(16, dto.getImage());
			pstmt.setString(17, dto.getContent());
			pstmt.setString(18, dto.getMaker());
			pstmt.setString(19, dto.getCategory());
			
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {try {conn.close();} catch(Exception e) {e.printStackTrace();}}
			if (pstmt != null) {try {pstmt.close();} catch(Exception e) {e.printStackTrace();}}
		}
		return res;
	}
	public void updateStatusSellList(int no) {
		String sql = "update sellerlist set status='판매중' where no=?";
		Connection conn = null; PreparedStatement pstmt = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {try {conn.close();} catch(Exception e) {e.printStackTrace();}}
			if (pstmt != null) {try {pstmt.close();} catch(Exception e) {e.printStackTrace();}}
		}
	}
	
	public CarlistDto detailCar(int no) {
		CarlistDto dto = new CarlistDto();
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		String sql = "select * from carlist where no=?";
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				dto = new CarlistDto(rset.getInt("no"), rset.getInt("birth"), rset.getInt("km"), rset.getInt("price"), rset.getString("country"), rset.getString("maker"), rset.getString("category"), rset.getString("model"), rset.getString("detail"), rset.getString("color"), rset.getString("fuel"), rset.getString("mission"), rset.getString("options"), rset.getString("accident"), rset.getString("seater"),rset.getString("city"), rset.getString("writer"), rset.getString("ip"), rset.getString("date"), rset.getString("image"), rset.getString("content"), rset.getString("status"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {try {conn.close();} catch(Exception e) {e.printStackTrace();}}
			if (pstmt != null) {try {pstmt.close();} catch(Exception e) {e.printStackTrace();}}
			if (rset != null) {try {rset.close();} catch(Exception e) {e.printStackTrace();}}
		}
		
		return dto;
	}
	
	public String carOwn(String id) {
		String list = "";
		String sql = "select cart from member where id=?";
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				list += rset.getString(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {try {conn.close();} catch(Exception e) {e.printStackTrace();}}
			if (pstmt != null) {try {pstmt.close();} catch(Exception e) {e.printStackTrace();}}
			if (rset != null) {try {rset.close();} catch(Exception e) {e.printStackTrace();}}
		}
		return list;
	}
	
	public int carBuy(String list, String id) {
		int res = 0;
		Connection conn = null; PreparedStatement pstmt = null; 
		String sql = "update member set cart=? where id=?";
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, list);
			pstmt.setString(2, id);
			res = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {try {conn.close();} catch(Exception e) {e.printStackTrace();}}
			if (pstmt != null) {try {pstmt.close();} catch(Exception e) {e.printStackTrace();}}
		}
		
		return res;
	}
	public void updateCar(int no) {
		String sql = "update carlist set status='판매완료' where no=?";
		Connection conn = null; PreparedStatement pstmt = null; 
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {try {conn.close();} catch(Exception e) {e.printStackTrace();}}
			if (pstmt != null) {try {pstmt.close();} catch(Exception e) {e.printStackTrace();}}
		}
	}
	
	public int removeCar(int no) {
		int res = 0;
		String sql = "delete from carlist where no=?";
		String sql2 = "delete from sellerlist where no=?";
		Connection conn = null; PreparedStatement pstmt = null; PreparedStatement pstmt2 = null; 
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.executeUpdate();
			
			pstmt2 = conn.prepareStatement(sql2);
			pstmt2.setInt(1, no);
			res = pstmt2.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {try {conn.close();} catch(Exception e) {e.printStackTrace();}}
			if (pstmt != null) {try {pstmt.close();} catch(Exception e) {e.printStackTrace();}}
			if (pstmt2 != null) {try {pstmt2.close();} catch(Exception e) {e.printStackTrace();}}
		}
		return res;
	}
	public ArrayList<CarlistDto> hotList7(){
		ArrayList<CarlistDto> list = new ArrayList<>();
		String sql = "select * from carlist where status!='판매완료';";
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				list.add(new CarlistDto(rset.getInt("no"), rset.getInt("birth"), rset.getInt("km"), rset.getInt("price"), rset.getString("country"), rset.getString("maker"), rset.getString("category"), rset.getString("model"), rset.getString("detail"), rset.getString("color"), rset.getString("fuel"), rset.getString("mission"), rset.getString("options"), rset.getString("accident"), rset.getString("seater"),  rset.getString("city"), rset.getString("writer"), rset.getString("ip"), rset.getString("date"), rset.getString("image"), rset.getString("content")));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {try {conn.close();} catch(Exception e) {e.printStackTrace();}}
			if (pstmt != null) {try {pstmt.close();} catch(Exception e) {e.printStackTrace();}}
			if (rset != null) {try {rset.close();} catch(Exception e) {e.printStackTrace();}}
		}
		return list;
	}
	
	public StoreDto storeInfo(String store) {
		StoreDto dto = new StoreDto();
		//public StoreDto(String location, String number, String x, String y, String address) {
		String sql = "select * from store where location=?";
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, store);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				dto.setAddress(rset.getString("address"));
				dto.setLocation(rset.getString("location"));
				dto.setNumber(rset.getString("number"));
				dto.setX(rset.getString("x"));
				dto.setY(rset.getString("y"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {try {conn.close();} catch(Exception e) {e.printStackTrace();}}
			if (pstmt != null) {try {pstmt.close();} catch(Exception e) {e.printStackTrace();}}
			if (rset != null) {try {rset.close();} catch(Exception e) {e.printStackTrace();}}
		}
		return dto;
	}
	
	public int storeCnt(String store) {
		int res = 0;
		String sql = "select count(*) from carlist where city=?";
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, store);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				res = rset.getInt(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {try {conn.close();} catch(Exception e) {e.printStackTrace();}}
			if (pstmt != null) {try {pstmt.close();} catch(Exception e) {e.printStackTrace();}}
			if (rset != null) {try {rset.close();} catch(Exception e) {e.printStackTrace();}}
		}
		
		return res;
		
	}
	public String[] showMyCart(String id) {
		String res = "";
		String[] arr = null;
		String sql = "select cart from member where id=?";
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rset = pstmt.executeQuery();
			if (rset.next()) {
				res += rset.getString(1);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {try{conn.close();}catch(Exception e) {e.printStackTrace();}}
			if (pstmt != null) {try{pstmt.close();}catch(Exception e) {e.printStackTrace();}}
			if (rset != null) {try{rset.close();}catch(Exception e) {e.printStackTrace();}}
		}
		arr = res.split("/");
		return arr;
	}
	public ArrayList<CarlistDto> showMyList(String[] arr){
		ArrayList<CarlistDto> list = new ArrayList<>();
		String qq = "";
		for (int i=0; i<arr.length; i++) {
			if (i==arr.length-1) {
				qq+="?";
			} else {
				qq+="?,";
			}
		}
		String sql = "select * from carlist where no in ("+qq+")";
		Connection conn = null; PreparedStatement pstmt = null; ResultSet rset = null;
		try {
			conn = new DBManager().getConnection();
			pstmt = conn.prepareStatement(sql);
			for (int i=0; i<arr.length; i++) {
				pstmt.setString(i+1, arr[i]);
			}
			rset = pstmt.executeQuery();
			while (rset.next()) {
				list.add(new CarlistDto(rset.getInt("no"), rset.getInt("birth"), rset.getInt("km"), rset.getInt("price"), rset.getString("country"), rset.getString("maker"), rset.getString("category"), rset.getString("model"), rset.getString("detail"), rset.getString("color"), rset.getString("fuel"), rset.getString("mission"), rset.getString("options"), rset.getString("accident"), rset.getString("seater"), rset.getString("city"), rset.getString("writer"), rset.getString("ip"), rset.getString("date"), rset.getString("image"), rset.getString("content")));
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {try{conn.close();}catch(Exception e) {e.printStackTrace();}}
			if (pstmt != null) {try{pstmt.close();}catch(Exception e) {e.printStackTrace();}}
			if (rset != null) {try{rset.close();}catch(Exception e) {e.printStackTrace();}}
		}
		
		return list;
	}
	
}
