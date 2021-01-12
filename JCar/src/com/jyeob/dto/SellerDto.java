package com.jyeob.dto;

public class SellerDto {
	private int no;
	private int birth;
	private String country;
	private String model;
	private String writer;
	private String status;
	private String name;
	private String phone;
	private String id;
	private String date;
		
	public SellerDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SellerDto(int no, int birth, String country, String model, String writer, String status, String name,
			String phone, String id, String date) {
		super();
		this.no = no;
		this.birth = birth;
		this.country = country;
		this.model = model;
		this.writer = writer;
		this.status = status;
		this.name = name;
		this.phone = phone;
		this.id = id;
		this.date = date;
	}
	
	public SellerDto(String id, String name, String phone, String country, String model, int birth) {
		this.id=id;
		this.name=name;
		this.phone=phone;
		this.country=country;
		this.model=model;
		this.birth=birth;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public int getNo() {
		return no;
	}
	
	public void setNo(int no) {
		this.no = no;
	}
	
	public int getBirth() {
		return birth;
	}
	
	public void setBirth(int birth) {
		this.birth = birth;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "SellerDto [no=" + no + ", birth=" + birth + ", country=" + country + ", model=" + model + ", writer="
				+ writer + ", status=" + status + ", name=" + name + ", phone=" + phone + ", id=" + id + ", date="
				+ date + "]";
	}

}
