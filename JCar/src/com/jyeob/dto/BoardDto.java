package com.jyeob.dto;

public class BoardDto {
	private String category, title, date, content, ip;
	private int no, hits;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCategory() {
		
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	@Override
	public String toString() {
		return "BoardDto [category=" + category + ", title=" + title + ", date=" + date + ", no=" + no + ", hits="
				+ hits + "]";
	}
	public BoardDto() {
		
	}
	public BoardDto(String category, String title, String date, int no, int hits) {
		super();
		this.category = category;
		this.title = title;
		this.date = date;
		this.no = no;
		this.hits = hits;
	}
	public BoardDto(String category, String title, String date, String content, String ip, int no, int hits) {
		super();
		this.category = category;
		this.title = title;
		this.date = date;
		this.content = content;
		this.ip = ip;
		this.no = no;
		this.hits = hits;
	}
	
	
}
