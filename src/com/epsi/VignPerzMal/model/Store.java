package com.epsi.VignPerzMal.model;

public class Store {
	
	private int id;
	private String codeMag;
	private String name;
	private String address;
	private String zipCode;
	private String city;
	private String phone;
	private String schedule;
	private String fax;
	private double latitude;
	private double longitude;

	public Store() {
		super();
	}
	
	public Store(int id, String codeMag, String name, String address, String zipCode,
			String city, String phone, String schedule, String fax,
			double latitude, double longitude) {
		this();
		
		this.id = id;
		this.codeMag = codeMag;
		this.name = name;
		this.address = address;
		this.zipCode = zipCode;
		this.city = city;
		this.phone = phone;
		this.schedule = schedule;
		this.fax = fax;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public Store(String codeMag, String name, String address, String zipCode,
			String city, String phone, String schedule, String fax,
			double latitude, double longitude) {
		this(0, codeMag, name, address, zipCode, city, phone, schedule, fax, latitude, longitude);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodeMag() { return codeMag; }
	public void setCodeMag(String codeMag) { this.codeMag = codeMag; }

	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getAddress() { return address; }
	public void setAddress(String address) { this.address = address; }

	public String getZipCode() { return zipCode; }
	public void setZipCode(String zipCode) { this.zipCode = zipCode; }

	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }

	public String getPhone() { return phone; }
	public void setPhone(String telephone) { this.phone = telephone; }

	public String getSchedule() { return schedule; }
	public void setSchedule(String schedule) { this.schedule = schedule; }

	public String getFax() { return fax; }
	public void setFax(String fax) { this.fax = fax; }

	public double getLatitude() { return latitude; }
	public void setLatitude(double latitude) { this.latitude = latitude; }

	public double getLongitude() { return longitude; }
	public void setLongitude(double longitude) { this.longitude = longitude; }

	@Override
	public String toString() {
		return this.name;
	}
}