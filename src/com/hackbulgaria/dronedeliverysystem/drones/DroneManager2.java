package com.hackbulgaria.dronedeliverysystem.drones;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DroneManager2 implements DroneManagerInterface{
	DBConnector connector;
	Statement stmt = null;
	List<Drone> dronesAvailable = new ArrayList<Drone>();
	List<Drone> droneToSend = new ArrayList<Drone>();
		
	public DroneManager2(){
		this.connector = new DBConnector();
		this.stmt = connector.createStatement();
	}

	@Override
	public boolean droneAvailable(float capacityWU, Timestamp time) {
		int droneCapacity = 0;
		dronesAvailable.clear();
		droneToSend.clear();
		
		for(Drone drone : droneList){
			if(drone.getTimestamp().before(time) || drone.getTimestamp().equals(time)){
				droneToSend.add(drone);
				droneCapacity += drone.getCapacity();
				if(droneCapacity >= capacityWU) return true;
			}
			
		}
		return false;
	}
	
	private List<Drone> getAvailableDrones(Timestamp time) throws SQLException{
		String sql;
		sql = "select * from Drones where available<?";
		ResultSet rs = (ResultSet) connector.query(sql);
	}

	@Override
	public void droneSend(int miles) {
		// TODO Auto-generated method stub
		for(Drone drone : droneToSend){
			int ID = drone.getID();
			droneList.get(ID).travel(miles);
			editTimeStamp(droneList.get(ID),calculateTravelTime(miles)+drone.getChargingTime());
			droneList.get(ID).charge();
		}
	}
	
	private int calculateTravelTime(int miles){
		return miles*2;
	}
	
	
	private void populateList(int numberOfDrones, Timestamp time){
		for(int i=0; i < numberOfDrones; i++){
			droneList.add(new Drone(i,2000,500,5,time));
		}
	}
	
	public void editTimeStamp(Drone drone, int minutes){
		Timestamp time = drone.getTimestamp();
		time.setTime(time.getTime() + (minutes * 60 * 1000));
		drone.setTimestamp(time);
	}
	
	/*public static void main(String[] args) {
		Timestamp time = new Timestamp(117, 0, 19, 19, 0, 0, 0);
		DroneManager2 manager = new DroneManager2(10);
		System.out.println(manager.droneList.get(1).getTimestamp());
		System.out.println(manager.droneAvailable(500, time));
		manager.droneSend(15);
		
		for (Drone drone:manager.droneList){
			System.out.println(drone.getTimestamp());
		}
	}*/


}


