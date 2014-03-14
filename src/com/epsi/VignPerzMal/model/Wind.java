package com.epsi.VignPerzMal.model;

public class Wind {
	
    private String dir;
    private int speed;
    private String wind_unit;
    
	public Wind(String dir, int speed, String wind_unit) {
		super();
		
		this.dir = dir;
		this.speed = speed;
		this.wind_unit = wind_unit;
	}

	public String getDir() {
		return dir;
	}
	
	public void setDir(String dir) {
		this.dir = dir;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public String getWindUnit() {
		return wind_unit;
	}
	
	public void setWindUnit(String wind_unit) {
		this.wind_unit = wind_unit;
	}
}