package com.hackbulgaria.dronedeliverysystem.drones;

import java.sql.SQLException;
import java.sql.Timestamp;

import com.hackbulgaria.dronedeliverysystem.database.DBbuilder;
import com.hackbulgaria.dronedeliverysystem.database.DBmanager;
import com.hackbulgaria.dronedeliverysystem.warehouse.Warehouse;

public class Test {
	
	public static void main(String[] args) throws SQLException {
		DroneManager manager = new DroneManager();
		@SuppressWarnings("deprecation")
		Timestamp time = new Timestamp(117, 1, 19, 21, 20, 0, 0);
		//System.out.println(time);
		//System.out.println(manager.droneAvailable(1.5, time));
		//manager.droneSend(40);	
		//System.out.println(DBmanager.droneListAvailable(3, time) == null);
		//DBbuilder.start();
		Warehouse.productSend(6, 10);
		System.out.println(Warehouse.productAvailable(2, 6));
	}

}
