package com.hackbulgaria.dronedeliverysystem.drones;

import java.sql.*;
import java.util.*;

import com.hackbulgaria.dronedeliverysystem.database.DBmanager;

public class DroneManager implements DroneManagerInterface{
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
		
	@Override
	public boolean droneAvailable(double capacityWU, Timestamp time){
		droneToSend = DBmanager.droneListAvailable(capacityWU, time);		
		if(droneToSend != null){			
			return true;
		}
		else return false;		
	}
	
	@Override
	public void droneSend(int miles){
		for(Drone drone : droneToSend){
			drone.travel(miles);
			editTimeStamp(drone,calculateTravelTime(miles)+drone.getChargingTime());
			drone.charge();
			DBmanager.setTimeStamp(drone);
		}
	}		
}


