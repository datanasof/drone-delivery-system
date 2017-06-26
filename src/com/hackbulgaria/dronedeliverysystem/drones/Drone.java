package com.hackbulgaria.dronedeliverysystem.drones;

import java.sql.Timestamp;

public class Drone{
	private int id;
	private int battery;
	private float capacity;
	private int chargingRate;
	private int batteryMaxCapacity;
	private int dischargePerMile = 1;
	private Timestamp time;
	
	public Drone(int id, int battery, float capacity, int chargingRate, Timestamp time){
		this.id = id;
		this.battery = battery;
		this.batteryMaxCapacity = battery;
		this.chargingRate = chargingRate;
		this.capacity = capacity;
		this.time = time;
	
	}
	
	public Drone(){
			
	}	
	
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}
	
	public void setID(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	public int getChargingRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void setChargingRate(int chargingRate) {
		// TODO Auto-generated method stub
		this.chargingRate = chargingRate;
	}
	
	public float getCapacity() {
		// TODO Auto-generated method stub
		return capacity;
	}
	
	public void setCapacity(float capacity) {
		// TODO Auto-generated method stub
		this.capacity = capacity;
	}

	public int getBatteryLevel() {
		// TODO Auto-generated method stub
		return battery;
	}
	
	public void setBatteryLevel(int battery) {
		// TODO Auto-generated method stub
		this.battery = battery;
	}
	
	public Timestamp getTimestamp(){
		return time;
	}
	
	public void setTimestamp(Timestamp time){
		this.time = time;
	}

	public int getChargingTime() {
		float chargingTime = (float) (batteryMaxCapacity - battery) / chargingRate;
		return (int)Math.ceil(chargingTime);
	}
	
	public void charge() {
		battery = batteryMaxCapacity;
	}

	public void travel(int miles) {
		battery -= miles * 2 * dischargePerMile;		
	}
	
		
}
