package com.hackbulgaria.dronedeliverysystem.warehouse;
import java.util.HashMap;
import java.util.Map;

public class Warehouse {
	private Map<Integer, Product> inventory = new HashMap<Integer, Product>();

	public void addProduct(int id, Product product) {
		inventory.put(id, product);
	}
	
	public boolean checkProduct(int id, int quantity) {
		return inventory.get(id).getQuantity() >= quantity;
	}

	public void takeProduct(int id, int quantity) {
		Product product = inventory.get(id);
		if(product.getQuantity() >= quantity) {
			product.takeProduct(quantity);
		}
		// TODO Throw exception
	}
	
	public Product getProduct(int id){
		return inventory.get(id);
	}
}
