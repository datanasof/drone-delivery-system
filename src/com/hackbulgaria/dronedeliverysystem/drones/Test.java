package com.hackbulgaria.dronedeliverysystem.drones;

import java.sql.SQLException;
import java.sql.Timestamp;

public class Test {
	
	public static void main(String[] args) throws SQLException {
		DroneManager manager = new DroneManager();
		@SuppressWarnings("deprecation")
		Timestamp time = new Timestamp(117, 1, 19, 21, 20, 0, 0);
		System.out.println(time);
		System.out.println(manager.droneAvailable(1.0, time));
		manager.droneSend(30);		
	}

}
