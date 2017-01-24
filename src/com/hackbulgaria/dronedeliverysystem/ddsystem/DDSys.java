package com.hackbulgaria.dronedeliverysystem.ddsystem;

import java.util.ArrayList;
import java.util.Map;
import com.hackbulgaria.dronedeliverysystem.drones.*;
import com.hackbulgaria.dronedeliverysystem.warehouse.*;
import java.util.Iterator;
import java.util.Map;

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

	public boolean deliveryValidation(Request request){
		double weight = 0;
		Iterator it = request.getWantedProducts().entrySet().iterator();
		
		while(it.hasNext()){
			Map.Entry<Integer, Integer> pair = (Map.Entry<Integer, Integer>)it.next();
			weight += warehouse.getProduct(pair.getKey()).getWeight();
		    it.remove();
		}
		return droneManager.droneAvailable(weight, request.getTimeStamp());
	}
}
