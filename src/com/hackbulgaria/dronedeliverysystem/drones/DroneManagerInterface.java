package com.hackbulgaria.dronedeliverysystem.drones;

import java.sql.SQLException;
import java.sql.Timestamp;

public interface DroneManagerInterface {
	public boolean droneAvailable(double capacityWU, Timestamp time) throws SQLException;
	/*
	Constructor:
	Timestamp(int year, int month, int date, int hour, int minute, int second, int nano)
	
	Parameters:
	year - the year minus 1900
	month - 0 to 11
	date - 1 to 31
	hour - 0 to 23
	minute - 0 to 59
	second - 0 to 59
	nano - 0 to 999,999,999
	*/
	public void droneSend(int miles) throws SQLException;
	
}