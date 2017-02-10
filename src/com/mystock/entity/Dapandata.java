package com.mystock.entity;

public class Dapandata {
	private String dot;
	private String name;
	private String nowPic;
	private String rate;
	private String traAmount;
	private String traNumber;
	public Dapandata() {
		
	}
	public Dapandata(String dot, String name, String nowPic, String rate, String traAmount, String traNumber) {
		super();
		this.dot = dot;
		this.name = name;
		this.nowPic = nowPic;
		this.rate = rate;
		this.traAmount = traAmount;
		this.traNumber = traNumber;
	}
	public String getDot() {
		return dot;
	}
	public void setDot(String dot) {
		this.dot = dot;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNowPic() {
		return nowPic;
	}
	public void setNowPic(String nowPic) {
		this.nowPic = nowPic;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getTraAmount() {
		return traAmount;
	}
	public void setTraAmount(String traAmount) {
		this.traAmount = traAmount;
	}
	public String getTraNumber() {
		return traNumber;
	}
	public void setTraNumber(String traNumber) {
		this.traNumber = traNumber;
	}
	
}
