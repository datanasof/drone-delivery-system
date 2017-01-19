package com.hackbulgaria.dronedeliverysystem.ddsystem;
import com.hackbulgaria.dronedeliverysystem.warehouse.*;
import java.util.Date;
import java.util.HashMap;

public class Request {
	private class Coordinates {
		private int x;
		private int y;
		
		public Coordinates(int x, int y){
			this.x = x;
			this.y = y;
		}
		public boolean isValid(){
			return (this.x >= 0 && this.x < 1000) && (this.y >= 0 && this.y < 1000);
		}
	}
	private final HashMap<Product, Integer> merch;
	private  final Date stamp;
	private  final Coordinates coords;
	
	public Request(HashMap<Product, Integer> merch, Date requestStamp, Coordinates requestCoords){
		this.coords = requestCoords;
		this.stamp = requestStamp;
		this.merch = merch;
	}
}
