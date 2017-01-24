package com.hackbulgaria.dronedeliverysystem.ddsystem;
import java.util.HashMap;
import java.sql.*;

public class Request {
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
