package com.hackbulgaria.dronedeliverysystem.ddsystem;
import java.util.HashMap;
import java.util.Map;
import java.sql.*;

public class Request {
	private final Map<Integer, Integer> wantedProducts;
	private final Timestamp stamp;
	private final Coordinates coords;
	
	public Request(Map<Integer, Integer> order, Timestamp requestStamp, Coordinates requestCoords){
		this.coords = requestCoords;
		this.stamp = requestStamp;
		this.wantedProducts = order;
	}
	
	public Map<Integer, Integer> getWantedProducts() {
		return wantedProducts;
	}
	public Timestamp getTimeStamp(){
		return this.stamp;
	}
	
	public Coordinates getCoordinates(){
		return this.coords;
	}
}
