package com.hackbulgaria.dronedeliverysystem.warehouse;

public class Product {
	private final String name;
	private final double weight;
	private int quantity;
	
	public Product(String name, double weight, int quantity) {
		this.name = name;
		this.weight = weight;
		this.quantity = quantity;
	}
	
	public String getName() {
		return name;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	// returns actually taken quantity
	public int takeProduct(int quantity) {		
		int takenQuantity = Math.min(this.quantity, quantity);
		this.quantity -= takenQuantity;
		return takenQuantity;
	}
}
