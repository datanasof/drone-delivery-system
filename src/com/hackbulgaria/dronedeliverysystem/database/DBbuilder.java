package com.hackbulgaria.dronedeliverysystem.database;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;

public class DBbuilder {
	static final String DB_URL = "jdbc:mysql://localhost/ddsdb";
	static final String USER = "root";
	static final String PASS = "160983St";
	static final String SQL = "resources/ddsdb.sql";
	
	public static void start(){
		try{
			PreparedStatement stmt = null;			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
			BufferedReader br = getBR();
			
			String sql = br.readLine();
			while (sql != null) {
				if (sql.trim().length() > 0){
					stmt = conn.prepareStatement(sql);
					stmt.execute();
				}			
		        sql = br.readLine();
		    }			
			br.close();
			stmt.close();
			conn.close();
		} catch(Exception se) {
			se.printStackTrace();
		}				
	}
	
	private static BufferedReader getBR() throws FileNotFoundException{
		BufferedReader br = new BufferedReader(new FileReader(SQL));
		return br;
	}
}
