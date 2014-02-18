package com.epsi.VignPerzMal.models;

public class Store {
	
	private String codeMag;
	private String name;
	private String address;
	private String zipCode;
	private String city;
	private String phone;
	private String schedule;
	private String fax;
	private String latitude;
	private String longitude;

	public Store() {
	}
	
	public Store(String codeMag, String name, String address, String zipCode,
			String city, String phone, String schedule, String fax,
			String latitude, String longitude) {
		super();
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

	public String getLatitude() { return latitude; }
	public void setLatitude(String latitude) { this.latitude = latitude; }

	public String getLongitude() { return longitude; }
	public void setLongitude(String longitude) { this.longitude = longitude; }

	@Override
	public String toString() {
		return this.name;
	}
}