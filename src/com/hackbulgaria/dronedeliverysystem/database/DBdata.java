package com.hackbulgaria.dronedeliverysystem.database;

import com.hackbulgaria.dronedeliverysystem.ddsystem.Coordinates;

public class DBdata {
	static final String DB_URL = "jdbc:mysql://localhost/ddsdb";
	static final String USER = "root";
	static final String PASS = "160983St";
	static final String SQL = "resources/ddsdb.sql";
	public static final Coordinates WH_coordinates = new Coordinates(42,42);
}
