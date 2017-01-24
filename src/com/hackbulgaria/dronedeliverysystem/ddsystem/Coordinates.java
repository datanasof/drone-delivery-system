package com.hackbulgaria.dronedeliverysystem.ddsystem;

public class Coordinates {
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
}
