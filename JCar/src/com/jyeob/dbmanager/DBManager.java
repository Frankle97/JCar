package com.jyeob.dbmanager;

import java.sql.*;
import javax.naming.*;
import javax.sql.*;

public class DBManager {
	private static Connection conn;
	public DBManager() {conn = null;}
	
	public static Connection getConn() {
		return conn;
	}
	
	public Connection getConnection() throws Exception{
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			DataSource db = (DataSource)envContext.lookup("jdbc/tieotdsf1324");
			conn = db.getConnection();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	
}
