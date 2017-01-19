package com.hackbulgaria.dronedeliverysystem.drones;

import java.sql.Timestamp;

public interface DroneInterface {
	
	public int getID();
	
	public void setID(int id);
		
	public int getCapacity();
	
	public void setCapacity(int capacity);
	
	public int getBatteryLevel();
	
	public void setBatteryLevel(int battery);
	
	public int getChargingRate();
	
	public void setChargingRate(int chargingRate);
	
	public int getChargingTime();
	
	public Timestamp getTimestamp();
	
	public void setTimestamp(Timestamp time);
	
	public void charge();
	
	public void travel(int miles);
	
		

}

