package com.jyeob.dto;

public class StoreDto {
	private String location, number, x, y, address;

	public StoreDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StoreDto(String location, String number, String x, String y, String address) {
		super();
		this.location = location;
		this.number = number;
		this.x = x;
		this.y = y;
		this.address = address;
	}

	@Override
	public String toString() {
		return "StoreDto [location=" + location + ", number=" + number + ", x=" + x + ", y=" + y + ", address="
				+ address + "]";
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
}
