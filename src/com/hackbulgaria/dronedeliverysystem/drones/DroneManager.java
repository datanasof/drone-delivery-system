package com.hackbulgaria.dronedeliverysystem.drones;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class DroneManager implements DroneManagerInterface{
	List<Drone> droneList = new ArrayList<Drone>();
	List<Drone> droneToSend = new ArrayList<Drone>();
		
	public DroneManager(int numberOfDrones){
		@SuppressWarnings("deprecation")
		Timestamp startingTime = new Timestamp(117, 0, 19, 19, 0, 0, 0);
		populateList(numberOfDrones,startingTime);
	}

	@Override
	public boolean droneAvailable(int capacityWU, Timestamp time) {
		int droneCapacity = 0;
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
		DroneManager manager = new DroneManager(10);
		System.out.println(manager.droneList.get(1).getTimestamp());
		System.out.println(manager.droneAvailable(500, time));
		manager.droneSend(15);
		
		for (Drone drone:manager.droneList){
			System.out.println(drone.getTimestamp());
		}
	}*/


}

