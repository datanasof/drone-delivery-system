package com.hackbulgaria.dronedeliverysystem.drones;

import java.sql.*;
import java.util.*;

import com.hackbulgaria.dronedeliverysystem.database.DBdata;
import com.hackbulgaria.dronedeliverysystem.database.DBmanager;
import com.hackbulgaria.dronedeliverysystem.ddsystem.Coordinates;
import com.hackbulgaria.dronedeliverysystem.ddsystem.Request;

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
	
	private double getDistance(Coordinates coord){
		return Math.sqrt((DBdata.WH_coordinates.getX() - coord.getX())^2 + 
				(DBdata.WH_coordinates.getY() - coord.getY())^2);
		
	}
	
	public void droneSend(Coordinates coord){
		int miles = (int) getDistance(coord);
		for(Drone drone : droneToSend){
			drone.travel(miles);
			editTimeStamp(drone,calculateTravelTime(miles)+drone.getChargingTime());
			drone.charge();
			DBmanager.setTimeStamp(drone);
		}
	}		
}


