package com.hackbulgaria.dronedeliverysystem.drones;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.hackbulgaria.dronedeliverysystem.database.DBbuilder;
import com.hackbulgaria.dronedeliverysystem.database.DBmanager;
import com.hackbulgaria.dronedeliverysystem.warehouse.Warehouse;

public class Test {
	
	public static void main(String[] args) throws SQLException {
		DroneManager manager = new DroneManager();
		Warehouse wh = new Warehouse();
		@SuppressWarnings("deprecation")
		Timestamp time = new Timestamp(117, 1, 18, 21, 20, 0, 0);
		System.out.println(time);
		//System.out.println(manager.droneAvailable(1.5, time));
		//manager.droneSend(40);	
		//System.out.println(DBmanager.droneListAvailable(3, time) == null);
		//DBbuilder.start();
		Map<Integer, Integer> order = new HashMap<Integer, Integer>();
		order.put(1,3);
		order.put(2,3);
		order.put(4,3);
		
		//System.out.println(wh.productAvailable(3, 5));
		//wh.productAddToBasket(3, 5);
		
		System.out.println(Warehouse.orderAvailable(order));
	}

}
