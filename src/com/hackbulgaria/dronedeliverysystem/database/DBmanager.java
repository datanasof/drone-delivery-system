package com.hackbulgaria.dronedeliverysystem.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hackbulgaria.dronedeliverysystem.drones.Drone;
import com.hackbulgaria.dronedeliverysystem.warehouse.Product;

public class DBmanager {
	
	public static void setTimeStamp(Drone drone){
		int ID = drone.getID();
		Timestamp time = drone.getTimestamp();
		
		try{
			PreparedStatement stmt = null;			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DBdata.DB_URL,DBdata.USER,DBdata.PASS);

			String sql;
			sql = "Update Drones set available = ? where id= ?";
			stmt = conn.prepareStatement(sql);
			stmt.setTimestamp(1, time);
			stmt.setInt(2, ID);
			stmt.execute();		
			
			stmt.close();
			conn.close();
		} catch(Exception se) {
			se.printStackTrace();
		}				
	}
	
	public static List<Drone> droneListAvailable(double capacityWU, Timestamp time){
		float droneCapacity = 0;
		List<Drone> droneToSend = new ArrayList<Drone>();
		droneToSend = null;
		List<Drone> droneList = new ArrayList<Drone>();
						
		try{
			PreparedStatement stmt = null;
			ResultSet rs = null;			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DBdata.DB_URL,DBdata.USER,DBdata.PASS);
						
			String sql;
			sql = "SELECT * FROM Drones where available <= ? order by available asc";
			stmt = conn.prepareStatement(sql);
			stmt.setTimestamp(1, time);
			rs = stmt.executeQuery();
						
			while(rs.next()){
		         //Retrieve by column name
		         int id  = rs.getInt("id");
		         int battery  = rs.getInt("battery");
		         float capacity = rs.getFloat("capacity");
		         int chargeRate = rs.getInt("chargerate");
		         Timestamp available = rs.getTimestamp("available");
		         Drone drone = new Drone(id,battery,capacity,chargeRate,available);
		         droneList.add(drone);
		         droneCapacity += capacity;
		         if(droneCapacity >= capacityWU) break;
			}   
			rs.close();
	        stmt.close();
	        conn.close();
		} catch(Exception se) {
			se.printStackTrace();
		}
		if(droneCapacity >= capacityWU) droneToSend = droneList;
		return droneToSend;			
	}
	
	public static void addNewProducts(String name, double weight){
		int quantity = 0;
		try{
			PreparedStatement stmt = null;			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DBdata.DB_URL,DBdata.USER,DBdata.PASS);

			String sql;
			sql = "INSERT INTO Products (name, weight, quantity) VALUES (?,?,?);";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setDouble(2, weight);
			stmt.setInt(3, quantity);
			stmt.execute();		
			
			stmt.close();
			conn.close();
		} catch(Exception se) {
			se.printStackTrace();
		}				
	}
	
	public static void deliverProducts(int id, int quantity){
		try{
			PreparedStatement stmt = null;			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DBdata.DB_URL,DBdata.USER,DBdata.PASS);

			String sql;
			sql = "UPDATE Products SET quantity = quantity + ? WHERE id = ?;";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, quantity);
			stmt.setInt(2, id);
			stmt.execute();		
			
			stmt.close();
			conn.close();
		} catch(Exception se) {
			se.printStackTrace();
		}				
	}
	
	public static int productsAvailability(int id){
		int quantity = 0;
		try{
			PreparedStatement stmt = null;
			ResultSet rs = null;			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DBdata.DB_URL,DBdata.USER,DBdata.PASS);
						
			String sql;
			sql = "SELECT * FROM Products where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			while(rs.next()){		         
				quantity  = rs.getInt("quantity");
			}   
			
			rs.close();
	        stmt.close();
	        conn.close();
		} catch(Exception se) {
			se.printStackTrace();
		}
		return quantity;
	}

	public static Map<Integer, Timestamp> getWhenAvailable(int id) {
		int quantity = 0;
		Timestamp time = null;
		try{
			PreparedStatement stmt = null;
			ResultSet rs = null;			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DBdata.DB_URL,DBdata.USER,DBdata.PASS);
						
			String sql;
			sql = "SELECT * FROM WH_deliveries where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			while(rs.next()){		         
				quantity  = rs.getInt("quantity");
				time = rs.getTimestamp("available");
			}   
			
			rs.close();
	        stmt.close();
	        conn.close();
		} catch(Exception se) {
			se.printStackTrace();
		}
		Map<Integer, Timestamp> availability = new HashMap<>();
		if(quantity != 0){
			availability.put(quantity, time);
		}
				
		return availability;
	}

	public static double getProductWeight(int id) {
		double weight = 0;
		try{
			PreparedStatement stmt = null;
			ResultSet rs = null;			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DBdata.DB_URL,DBdata.USER,DBdata.PASS);
						
			String sql;
			sql = "SELECT * FROM Products where id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			
			while(rs.next()){		         
				weight  = rs.getInt("weight");
			}   
			
			rs.close();
	        stmt.close();
	        conn.close();
		} catch(Exception se) {
			se.printStackTrace();
		}
		return weight;
	}

}
