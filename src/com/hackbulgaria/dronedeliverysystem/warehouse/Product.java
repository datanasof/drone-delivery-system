package com.hackbulgaria.dronedeliverysystem.warehouse;

public class Product {
	private int id;
	private String name;
	private final double weight;
	private int quantity;
		
	public Product(int id, double weight, int quantity) {
		this.weight = weight;
		this.id = id;
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
	
	public double getProductTotalWeight(){
		return weight*quantity;
	}
	// returns actually taken quantity
	public int takeProduct(int quantity) {		
		int takenQuantity = Math.min(this.quantity, quantity);
		this.quantity -= takenQuantity;
		return takenQuantity;
	}
}
