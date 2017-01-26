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
	public DDSys(){};
	
	public boolean validateProductRequest(Request request) {
		for(Map.Entry<Integer, Integer> productQuery : request.getWantedProducts().entrySet()) {
			if(!Warehouse.productAvailable(productQuery.getKey(), productQuery.getValue())) {
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
			weight += Warehouse.productGetWeight(pair.getKey());
		    it.remove();
		}
		return droneManager.droneAvailable(weight, request.getTimeStamp());
	}
	
	public void executeOrder(Request request){
		droneManager.droneSend(request.getCoordinates());
		for(Map.Entry<Integer, Integer> productQuery : request.getWantedProducts().entrySet()) {
			Warehouse.productSend(productQuery.getKey(), productQuery.getValue());
			}
	}
		
		
	
}
