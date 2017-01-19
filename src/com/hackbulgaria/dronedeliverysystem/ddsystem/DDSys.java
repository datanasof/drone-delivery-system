package com.hackbulgaria.dronedeliverysystem.ddsystem;

import java.util.ArrayList;
import java.util.Map;
import com.hackbulgaria.dronedeliverysystem.drones.*;
import com.hackbulgaria.dronedeliverysystem.warehouse.*;

public class DDSys {
	private DroneManager droneManager;
	private Warehouse warehouse;
	
	public boolean validateProductRequest(Request request) {
		for(Map.Entry<Integer, Integer> productQuery : request.getWantedProducts().entrySet()) {
			if(!warehouse.checkProduct(productQuery.getKey(), productQuery.getValue())) {
				return false;
			}
		}
		return true;
	}
}
