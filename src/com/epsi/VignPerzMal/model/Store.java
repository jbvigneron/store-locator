package com.epsi.VignPerzMal.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Store implements Parcelable {
	
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
	
	public Store(Parcel in) {
		
        this.id = in.readInt();
        this.codeMag = in.readString();
        this.name = in.readString();
        this.address = in.readString();
        this.zipCode = in.readString();
        this.city = in.readString();
        this.phone = in.readString();
        this.schedule = in.readString();
        this.fax = in.readString();
        this.latitude = in.readFloat();
        this.longitude = in.readFloat();
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
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		
		dest.writeInt(id);
		dest.writeString(codeMag);
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(zipCode);
        dest.writeString(city);
        dest.writeString(phone);
        dest.writeString(schedule);
        dest.writeString(fax);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
	}
	
	public static final Parcelable.Creator<Store> CREATOR
    = new Parcelable.Creator<Store>() {
        public Store createFromParcel(Parcel in) {
            return new Store(in);
        }

        public Store[] newArray(int size) {
            return new Store[size];
        }
    };
	
	@Override
	public String toString() {
		return this.name;
	}
}