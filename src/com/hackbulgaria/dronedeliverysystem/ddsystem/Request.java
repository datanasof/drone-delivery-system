package com.hackbulgaria.dronedeliverysystem.ddsystem;
import java.util.HashMap;
import java.sql.*;

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
	private final HashMap<Integer, Integer> wantedProducts;
	private final Timestamp stamp;
	private final Coordinates coords;
	
	public Request(HashMap<Integer, Integer> wantedProducts, Timestamp requestStamp, Coordinates requestCoords){
		this.coords = requestCoords;
		this.stamp = requestStamp;
		this.wantedProducts = wantedProducts;
	}
	
	public HashMap<Integer, Integer> getWantedProducts() {
		return wantedProducts;
	}
	public Timestamp getTimeStamp(){
		return this.stamp;
	}
}
