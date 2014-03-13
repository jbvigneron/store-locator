package com.epsi.VignPerzMal.models;

public class Wind {
	
    private String dir;
    private String dir_degree;
    private String speed;
    private String wind_unit;
    
	public Wind(String dir, String dir_degree, String speed, String wind_unit) {
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
	
	public String getDir_degree() {
		return dir_degree;
	}
	
	public void setDir_degree(String dir_degree) {
		this.dir_degree = dir_degree;
	}
	
	public String getSpeed() {
		return speed;
	}
	
	public void setSpeed(String speed) {
		this.speed = speed;
	}
	
	public String getWind_Unit() {
		return wind_unit;
	}
	
	public void setWindUnit(String wind_unit) {
		this.wind_unit = wind_unit;
	}
}