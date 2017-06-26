package com.hackbulgaria.dronedeliverysystem.warehouse;
import java.sql.*;
import java.util.*;


import com.hackbulgaria.dronedeliverysystem.database.DBmanager;

public class Warehouse {
		
	//new methods, using DBmanager for DB connection
	
	public static double productGetWeight(int id){
		//get product's weight from DB
		return DBmanager.getProductWeight(id);
	}
	
	public static void productAddNew(String name, double weight){
		//adds new product to DB with 0 quntity
		DBmanager.addNewProducts(name, weight);
	}
	
	public static void productLoad(int id, int quantity){
		//connects to DB and adds quantity to a certain product
		DBmanager.deliverProducts(id, quantity);
	}
	
	public static void productSend(int id, int quantity){
		//adds a Product instance to the shopping basket, decreases availability in DB
		DBmanager.deliverProducts(id, -quantity);
		
	}
	
	public static boolean productAvailable(int id, int quantity){
		//connects to DB and checks if the asked quantity from a certain product is available
		return getProductQuantity(id) >= quantity;
	}
	
	public static int getProductQuantity(int id){
		//connects to DB and returns the quantity for a certain product
		return DBmanager.productsAvailability(id);	
	}
	
	public static Map<Integer, Timestamp> productWhenAvailable(int id){
		//connects to DB and returns a HashMap with key:quantity & value:timestamp when product should be available
		return DBmanager.getWhenAvailable(id);
	}	

	public static boolean orderAvailable(Map order){
		//connects to DB and returns if an order HashMap with key:product_id & value:qty is available
		Iterator it = order.entrySet().iterator();
		while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        	        
	        int id = (int) pair.getKey();
	        int quantity = (int) pair.getValue();
	        if(!productAvailable(id, quantity)){
	        	return false;	        	
	        }
	       
	        it.remove();
	    }
		return true;
	}
	
	/*public static void productAvailablePrint(int id, int quantity){
		 System.out.print("Product ID:" + id + " is ");
	        if(productAvailable(id, quantity)){
	        	System.out.println("available.");
	        } else{
	        	System.out.println("NOT available.");
	        	Map productDeliveryStatus = productWhenAvailable(id);
	        	if(!productDeliveryStatus.isEmpty()){
	        		Iterator it2 = productDeliveryStatus.entrySet().iterator();
	        		Map.Entry pair = (Map.Entry)it2.next();
	        		int qty = (int) pair.getKey();
	    	        Timestamp time = (Timestamp) pair.getValue();
	    	        System.out.println(qty+" units from this product should be delivered on "+time);
	    	        it2.remove();
	        	}
	        }		
	}*/
	//new methods, using DBmanager for DB connection
	
	
	
}
