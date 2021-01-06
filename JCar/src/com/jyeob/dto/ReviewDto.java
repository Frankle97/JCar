package com.jyeob.dto;

public class ReviewDto {
	private String writer,title,content,date,image,model;
	private int no,hits;
	
	
	@Override
	public String toString() {
		return "ReviewDto [writer=" + writer + ", title=" + title + ", content=" + content + ", date=" + date
				+ ", image=" + image + ", model=" + model + ", hits=" + hits + "]";
	}
	
	
	public ReviewDto(String writer, String title, String content, String date, String image, String model, int no,
			int hits) {
		super();
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.date = date;
		this.image = image;
		this.model = model;
		this.no = no;
		this.hits = hits;
	}


	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public ReviewDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	
	
	
	
}
