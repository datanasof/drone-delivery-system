package com.hackbulgaria.dronedeliverysystem.drones;

import java.sql.*;

public class Main {
	
	static final String DB_URL = "jdbc:mysql://localhost/ddsdb";
	static final String USER = "root";
	static final String PASS = "160983St";

	public static void main(String[] args) {
		try {
			PreparedStatement stmt = null;
			ResultSet rs = null;
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			String sql;
			sql = "SELECT * FROM Products WHERE weight > ?";
			stmt = conn.prepareStatement(sql);
			stmt.setFloat(1, 1);
						
			rs = stmt.executeQuery();
			
			//STEP 5: Extract data from result set
		      while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("id");
		         float weight = rs.getFloat("weight");
		         String name = rs.getString("name");

		         //Display values
		         System.out.print("ID: " + id);
		         System.out.print(", Weight: " + weight);
		         System.out.print(", Name: " + name);
		         System.out.println();
		      }
		     
			/*sql = "SELECT id, available FROM Drones";
			ResultSet rs2 = stmt.executeQuery(sql);
			
			while(rs2.next()){
		         //Retrieve by column name
		         int id  = rs2.getInt("id");
		         Timestamp stamp = rs2.getTimestamp("available");
		        

		         //Display values
		         System.out.print("ID: " + id);
		         System.out.print(", Available at: " + stamp);
		         System.out.println();
		    }*/
			
			
			
		      //STEP 6: Clean-up environment
		      rs.close();
		      stmt.close();
		      conn.close();
		} catch(Exception se) {
			se.printStackTrace();
		}
	}

}