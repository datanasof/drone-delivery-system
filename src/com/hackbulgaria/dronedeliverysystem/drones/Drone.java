package com.hackbulgaria.dronedeliverysystem.drones;

import java.sql.Timestamp;

public class Drone implements DroneInterface{
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
	
	@Override
	public int getID() {
		// TODO Auto-generated method stub
		return id;
	}

	@Override
	public void setID(int id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	@Override
	public int getChargingRate() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setChargingRate(int chargingRate) {
		// TODO Auto-generated method stub
		this.chargingRate = chargingRate;
	}
	
	@Override
	public float getCapacity() {
		// TODO Auto-generated method stub
		return capacity;
	}
	
	@Override
	public void setCapacity(float capacity) {
		// TODO Auto-generated method stub
		this.capacity = capacity;
	}

	@Override
	public int getBatteryLevel() {
		// TODO Auto-generated method stub
		return battery;
	}
	
	@Override
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

	@Override
	public int getChargingTime() {
		float chargingTime = (float) (batteryMaxCapacity - battery) / chargingRate;
		return (int)Math.ceil(chargingTime);
	}
	
	@Override
	public void charge() {
		battery = batteryMaxCapacity;
	}

	@Override
	public void travel(int miles) {
		battery -= miles * 2 * dischargePerMile;		
	}
	
		
}
