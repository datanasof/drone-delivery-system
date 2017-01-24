package com.hackbulgaria.dronedeliverysystem.ddsystem;
import java.util.HashMap;
import java.sql.*;

public class Main {

	public static void main(String[] args) {
		HashMap<Integer, Integer> order = new HashMap<>();
		Timestamp stamp = new Timestamp(117, 1, 19, 21, 20, 0, 0);
		DDSys sys = new DDSys();
		Request req = new Request(order, stamp, new Coordinates(5,5));
		System.out.println(sys.deliveryValidation(req));
		System.out.println(sys.validateProductRequest(req));
	}

}
