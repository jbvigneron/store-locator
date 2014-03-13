package com.epsi.VignPerzMal.model;

public class Wind {
	
    private String dir;
    private int dir_degree;
    private int speed;
    private String wind_unit;
    
	public Wind(String dir, int dir_degree, int speed, String wind_unit) {
		super();
		
		this.dir = dir;
		this.dir_degree = dir_degree;
		this.speed = speed;
		this.wind_unit = wind_unit;
	}

	public String getDir() {
		return dir;
	}
	
	public void setDir(String dir) {
		this.dir = dir;
	}
	
	public int getDir_degree() {
		return dir_degree;
	}
	
	public void setDir_degree(int dir_degree) {
		this.dir_degree = dir_degree;
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