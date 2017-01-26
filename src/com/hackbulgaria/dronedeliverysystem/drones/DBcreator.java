package com.hackbulgaria.dronedeliverysystem.drones;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class DBcreator {
	static final String DB_URL = "jdbc:mysql://localhost/dds";
	static final String USER = "root";
	static final String PASS = "160983St";
	
	public static void start(){
		try{
			PreparedStatement stmt = null;					
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
			BufferedReader br = new BufferedReader(new FileReader("resources/ddsdb.txt"));			
			
			String sql = br.readLine();
			
			while (sql != null) {
		        System.out.println(sql);
		        stmt = conn.prepareStatement(sql);
		        stmt.execute();		
		        sql = br.readLine();
			}
			br.close();
		} catch(Exception se) {
			se.printStackTrace();
		}			
	}
	
	public static void main(String[] args) {
		DBcreator.start();
	}
}
