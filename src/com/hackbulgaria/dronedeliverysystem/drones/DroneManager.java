package com.hackbulgaria.dronedeliverysystem.drones;

import java.sql.*;
import java.util.*;

public class DroneManager implements DroneManagerInterface{
	static final String DB_URL = "jdbc:mysql://localhost/ddsdb";
	static final String USER = "root";
	static final String PASS = "160983St";
	
	private List<Drone> droneToSend = new ArrayList<Drone>();
		
	public DroneManager(){			
	}
	
	private int calculateTravelTime(int miles){
		return miles*2;
	}
	
	private void editTimeStamp(Drone drone, int minutes){
		Timestamp time = drone.getTimestamp();
		time.setTime(time.getTime() + (minutes * 60 * 1000));
		drone.setTimestamp(time);
	}
	
	private void setTimeStamp(Drone drone){
		int ID = drone.getID();
		Timestamp time = drone.getTimestamp();
		
		try{
			PreparedStatement stmt = null;			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);

			String sql;
			sql = "Update Drones set available = ? where id= ?";
			stmt = conn.prepareStatement(sql);
			stmt.setTimestamp(1, time);
			stmt.setInt(2, ID);
			stmt.execute();		
		} catch(Exception se) {
			se.printStackTrace();
		}				
	}

	@Override
	public boolean droneAvailable(double capacityWU, Timestamp time){
		float droneCapacity = 0;
		droneToSend.clear();
						
		try{
			PreparedStatement stmt = null;
			ResultSet rs = null;			
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
						
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
		         droneToSend.add(drone);
		         droneCapacity += capacity;
		         if(droneCapacity >= capacityWU) break;		         
			}   
			
		} catch(Exception se) {
			se.printStackTrace();
		}
		if(droneCapacity >= capacityWU){			
			return true;
		}
		else return false;		
	}
	
	@Override
	public void droneSend(int miles) throws SQLException {
		for(Drone drone : droneToSend){
			drone.travel(miles);
			editTimeStamp(drone,calculateTravelTime(miles)+drone.getChargingTime());
			drone.charge();
			setTimeStamp(drone);
		}
	}		
}


