package com.hackbulgaria.dronedeliverysystem.ddsystem;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.hackbulgaria.dronedeliverysystem.database.DBbuilder;
import com.hackbulgaria.dronedeliverysystem.warehouse.Warehouse;

import java.sql.*;

public class Main {
	
	public static void main(String[] args) {
		DBbuilder.start(); //creates database
		Timestamp stamp = new Timestamp(117, 1, 19, 21, 20, 0, 0);
		DDSys sys = new DDSys();
		
		Map order = new HashMap();
		order.put(1,7);
		order.put(2,3);
		order.put(4,6);
		
		Request req = new Request(order, stamp, new Coordinates(5,5));
		System.out.println(sys.deliveryValidation(req));
		System.out.println(sys.validateProductRequest(req));
		//sys.executeOrder(req);
		//System.out.println(Warehouse.orderAvailable(order));
		Iterator it = order.entrySet().iterator();
		while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        	        
	        int id = (int) pair.getKey();
	        int quantity = (int) pair.getValue();
	       System.out.println(Warehouse.productAvailable(id, quantity));
	       
	        it.remove();
	    }
		
	}

}
