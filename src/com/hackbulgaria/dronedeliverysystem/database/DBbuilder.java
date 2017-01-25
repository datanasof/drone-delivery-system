package com.hackbulgaria.dronedeliverysystem.database;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;

public class DBbuilder {
	
	public static void start(){
		try{
			PreparedStatement stmt = null;			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DBdata.DB_URL,DBdata.USER,DBdata.PASS);
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
		BufferedReader br = new BufferedReader(new FileReader(DBdata.SQL));
		return br;
	}
}
