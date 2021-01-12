package com.jyeob.dto;

public class MemberDto {
	private int no;
	private String name;
	private String birth;
	private String gender;
	private String id;
	private String password;
	private String contact;
	private String email;
	private String ip;
	private String date;
	private String address;
	private String category;
	private String cart;
	
	public MemberDto() {
		super();
	}
	
	public MemberDto(int no, String name, String birth, String gender, String id, String password, String contact,
			String email, String ip, String date, String address, String category) {
		super();
		this.no = no;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.id = id;
		this.password = password;
		this.contact = contact;
		this.email = email;
		this.ip = ip;
		this.date = date;
		this.address = address;
		this.category=category;
	}
	
	public MemberDto(int no, String name, String birth, String gender, String id, String password, String contact,
			String email, String ip, String date, String address, String category, String cart) {
		super();
		this.no = no;
		this.name = name;
		this.birth = birth;
		this.gender = gender;
		this.id = id;
		this.password = password;
		this.contact = contact;
		this.email = email;
		this.ip = ip;
		this.date = date;
		this.address = address;
		this.category=category;
		this.cart = cart;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getAddress() {
		return address;
	}
	
	public String getCart() {
		return cart;
	}
	public void setCart(String cart) {
		this.cart = cart;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setAddress(String Address) {
		this.address = Address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		return "MemberDto [no=" + no + ", name=" + name + ", birth=" + birth + ", gender=" + gender + ", id=" + id
				+ ", password=" + password + ", contact=" + contact + ", email=" + email + ", ip=" + ip + ", date="
				+ date + ", address=" + address + category + cart + "]";
	}

	
	
	
	
}
