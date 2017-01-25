package com.hackbulgaria.dronedeliverysystem.warehouse;
import java.util.HashMap;
import java.util.Map;

import com.hackbulgaria.dronedeliverysystem.database.DBmanager;

public class Warehouse {
	private Map<Integer, Product> inventory = new HashMap<Integer, Product>();

	//new methods, using DBmanager for DB connection
	public static void productAddNew(String name, double weight){
		DBmanager.addNewProducts(name, weight);
	}
	
	public static void productLoad(int id, int quantity){
		DBmanager.deliverProducts(id, quantity);
	}
	
	public static void productSend(int id, int quantity){
		DBmanager.deliverProducts(id, -quantity);
	}
	
	public static boolean productAvailable(int id, int quantity){
		return getProductQuantity(id) >= quantity;
	}
	
	public static int getProductQuantity(int id){
		return DBmanager.productsAvailability(id);
	}
	//new methods, using DBmanager for DB connection
	
	
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
