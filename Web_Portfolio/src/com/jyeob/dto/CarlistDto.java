package com.jyeob.dto;

public class CarlistDto {
	private int no, birth, km, price;
	private String country, maker, category, model, detail, color, fuel, mission, options, accident, seater, 
			city, writer, ip, date, image, content, status;

	public CarlistDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CarlistDto(int no, int birth, int km, int price, String country, String maker, String category, String model,
			String detail, String color, String fuel, String mission, String options, String accident, String seater, String city, String writer, String ip, String date, String image, String content, String status) {
		super();
		this.no = no;
		this.birth = birth;
		this.km = km;
		this.price = price;
		this.country = country;
		this.maker = maker;
		this.category = category;
		this.model = model;
		this.detail = detail;
		this.color = color;
		this.fuel = fuel;
		this.mission = mission;
		this.options = options;
		this.accident = accident;
		this.seater = seater;
		this.city = city;
		this.writer = writer;
		this.ip = ip;
		this.date = date;
		this.image = image;
		this.content = content;
		this.status = status;
	}
	public CarlistDto(int no, int birth, int km, int price, String country, String maker, String category, String model,
			String detail, String color, String fuel, String mission, String options, String accident, String seater, String city, String writer, String ip, String date, String image, String content) {
		super();
		this.no = no;
		this.birth = birth;
		this.km = km;
		this.price = price;
		this.country = country;
		this.maker = maker;
		this.category = category;
		this.model = model;
		this.detail = detail;
		this.color = color;
		this.fuel = fuel;
		this.mission = mission;
		this.options = options;
		this.accident = accident;
		this.seater = seater;
		this.city = city;
		this.writer = writer;
		this.ip = ip;
		this.date = date;
		this.image = image;
		this.content = content;
	}
	public CarlistDto(String country, String maker, String category, String model, String detail, int birth, int km,
			int price, String color, String fuel, String mission, String options, String accident, String seater,
			 String city, String writer, String ip, String image, String content) {
		this.birth = birth;
		this.km = km;
		this.price = price;
		this.country = country;
		this.maker = maker;
		this.category = category;
		this.model = model;
		this.detail = detail;
		this.color = color;
		this.fuel = fuel;
		this.mission = mission;
		this.options = options;
		this.accident = accident;
		this.seater = seater;
		this.city = city;
		this.writer = writer;
		this.ip = ip;
		this.image = image;
		this.content = content;
	}

	@Override
	public String toString() {
		return "CarlistDto [no=" + no + ", birth=" + birth + ", km=" + km + ", price=" + price + ", country=" + country
				+ ", maker=" + maker + ", category=" + category + ", model=" + model + ", detail=" + detail + ", color="
				+ color + ", fuel=" + fuel + ", mission=" + mission + ", options=" + options + ", accident=" + accident
				+ ", seater=" + seater +  ", city=" + city + ", writer=" + writer + ", ip=" + ip
				+ ", date=" + date + ", image=" + image + ", content=" + content + "]";
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

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFuel() {
		return fuel;
	}

	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	public String getMission() {
		return mission;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}

	public String getAccident() {
		return accident;
	}

	public void setAccident(String accident) {
		this.accident = accident;
	}

	public String getSeater() {
		return seater;
	}

	public void setSeater(String seater) {
		this.seater = seater;
	}

	

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
