package com.hackbulgaria.dronedeliverysystem.drones;

import java.sql.*;
import java.util.*;


public class DroneManager implements DroneManagerInterface{
	static final String DB_URL = "jdbc:mysql://localhost/ddsdb";
	static final String USER = "root";
	static final String PASS = "160983St";
	
	List<Drone> droneToSend = new ArrayList<Drone>();
		
	public DroneManager(){
			
	}

	@Override
	public boolean droneAvailable(double capacityWU, Timestamp time){
		float droneCapacity = 0;
		droneToSend.clear();
						
		try{
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
			sql = "SELECT * FROM Drones where available <= ?";
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
		         droneToSend.add(drone);
		         droneCapacity += capacity;
		         if(droneCapacity >= capacityWU) break;
		         
			}   
			
			
		} catch(Exception se) {
			se.printStackTrace();
		}
		if(droneCapacity >= capacityWU){
			System.out.println(droneToSend);
			return true;
		}
		else return false;
		
	}
	
	@Override
	public void droneSend(int miles) throws SQLException {
		// TODO Auto-generated method stub
		for(Drone drone : droneToSend){
			drone.travel(miles);
			editTimeStamp(drone,calculateTravelTime(miles)+drone.getChargingTime());
			drone.charge();
			setTimeStamp(drone);
		}
	}
	
	private int calculateTravelTime(int miles){
		return miles*2;
	}
	
	public void editTimeStamp(Drone drone, int minutes){
		Timestamp time = drone.getTimestamp();
		time.setTime(time.getTime() + (minutes * 60 * 1000));
		drone.setTimestamp(time);
	}
	
	public void setTimeStamp(Drone drone){
		int ID = drone.getID();
		Timestamp time = drone.getTimestamp();
		
		try{
			PreparedStatement stmt = null;
			
			//STEP 2: Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//STEP 3: Open a connection
			System.out.println("Connecting to database...");
			Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
			//STEP 4: Execute a query
			System.out.println("Creating statement...");
			String sql;
			sql = "Update Drones set available = ? where id= ?";
			stmt = conn.prepareStatement(sql);
			stmt.setTimestamp(1, time);
			stmt.setInt(2, ID);
			stmt.executeQuery();		
		} catch(Exception se) {
			se.printStackTrace();
		}
		
				
	}
	
	
}


