package com.hackbulgaria.dronedeliverysystem.warehouse;
import java.util.HashMap;
import java.util.Map;

public class Warehouse {
	Map<Integer, Product> inventory = new HashMap<Integer, Product>();
	
	public void addProduct(int id, Product product) {
		inventory.put(id, product);
	}

	public boolean takeProduct(int id, int quantity) {
		Product product = inventory.get(id);
		if(product.getQuantity() >= quantity) {
			product.takeProduct(quantity);
			return true;
		}
		return false;
	}
}
