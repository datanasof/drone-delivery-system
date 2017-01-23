package com.hackbulgaria.dronedeliverysystem.drones;
import java.sql.*;

public class DBConnector {
	static final String DB_URL = "jdbc:mysql://localhost/dds";
	static final String USER = "root";
	static final String PASS = "160983St";
	Connection conn = null;
	Statement stmt = null;
	
	public DBConnector() {
		try {Class.forName("com.mysql.jdbc.Driver");
			
			System.out.println("Connecting to database...");
			this.conn = DriverManager.getConnection(DB_URL,USER,PASS);
			this.stmt = conn.createStatement();
		}
		
		catch(Exception se) {
			se.printStackTrace();
		}
	}
	
	public Connection 
	
	public Object query(String sql) throws SQLException{
		ResultSet rs;
		rs = stmt.executeQuery(sql);
		return rs;		
	}
	
	public void close() throws SQLException{
		stmt.close();
	    conn.close();
	}
	
	/*public static void main(String[] args) throws SQLException {
		DBConnector connector = new DBConnector();
		ResultSet rs2 = (ResultSet) connector.query("SELECT id, available FROM Drones");
		
		while(rs2.next()){
	         //Retrieve by column name
	         int id  = rs2.getInt("id");
	         Timestamp stamp = rs2.getTimestamp("available");
	        

	         //Display values
	         System.out.print("ID: " + id);
	         System.out.print(", Available at: " + stamp);
	         System.out.println();
	    }
	}*/

}
