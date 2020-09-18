package com.jyeob.dto;

import java.util.ArrayList;

public class PagingDto_Carlist {
	private int pageTotal; 
	private int onepageLimit; 
	private int pageAll; 
	private int pstartno; 
	private int bottomList;
	private int bottom_current; 
	private int bottom_start; 
	private int bottom_end; 
	ArrayList<CarlistDto> list;
	public PagingDto_Carlist() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PagingDto_Carlist(int pageTotal, int onepageLimit, int pageAll, int pstartno, int bottomList, int bottom_current,
			int bottom_start, int bottom_end, ArrayList<CarlistDto> list) {
		super();
		this.pageTotal = pageTotal;
		this.onepageLimit = onepageLimit;
		this.pageAll = pageAll;
		this.pstartno = pstartno;
		this.bottomList = bottomList;
		this.bottom_current = bottom_current;
		this.bottom_start = bottom_start;
		this.bottom_end = bottom_end;
		this.list = list;
	}
	@Override
	public String toString() {
		return "PagingDto [pageTotal=" + pageTotal + ", onepageLimit=" + onepageLimit + ", pageAll=" + pageAll
				+ ", pstartno=" + pstartno + ", bottomList=" + bottomList + ", bottom_current=" + bottom_current
				+ ", bottom_start=" + bottom_start + ", bottom_end=" + bottom_end + ", list=" + list + "]";
	}
	public int getPageTotal() {
		return pageTotal;
	}
	public void setPageTotal(int pageTotal) {
		this.pageTotal = pageTotal;
	}
	public int getOnepageLimit() {
		return onepageLimit;
	}
	public void setOnepageLimit(int onepageLimit) {
		this.onepageLimit = onepageLimit;
	}
	public int getPageAll() {
		return pageAll;
	}
	public void setPageAll(int pageAll) {
		this.pageAll = pageAll;
	}
	public int getPstartno() {
		return pstartno;
	}
	public void setPstartno(int pstartno) {
		this.pstartno = pstartno;
	}
	public int getBottomList() {
		return bottomList;
	}
	public void setBottomList(int bottomList) {
		this.bottomList = bottomList;
	}
	public int getBottom_current() {
		return bottom_current;
	}
	public void setBottom_current(int bottom_current) {
		this.bottom_current = bottom_current;
	}
	public int getBottom_start() {
		return bottom_start;
	}
	public void setBottom_start(int bottom_start) {
		this.bottom_start = bottom_start;
	}
	public int getBottom_end() {
		return bottom_end;
	}
	public void setBottom_end(int bottom_end) {
		this.bottom_end = bottom_end;
	}
	public ArrayList<CarlistDto> getList() {
		return list;
	}
	public void setList(ArrayList<CarlistDto> list) {
		this.list = list;
	}
	
	
}
