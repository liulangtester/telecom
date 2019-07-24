package com.telecome.po;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Bill {
	//账单号
	private String id;
	//主叫号码
	private String callNumber;
	//被叫号码
	private String beCalledNumber;
	//通话时长
	private String time;
	//话费
	private float cost;
	//通话结束时间
	private Date date;

	public String getId() {	
		return id;
	}
//	//账单自动生成：当前日期+6位随机数
//	Date d = new Date();
//	SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//	String s = sdf.format(d);
//	s = s + (int)((Math.random()*9+1)*100000);
	public void setId(String id) {
		this.id = id;
	}

	public String getCallNumber() {
		return callNumber;
	}

	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}

	public String getBeCalledNumber() {
		return beCalledNumber;
	}

	public void setBeCalledNumber(String beCalledNumber) {
		this.beCalledNumber = beCalledNumber;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float f) {
		this.cost = f;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Bill(String id, String callNumber, String beCalledNumber, String time, float cost, Date date) {
		super();
		this.id = id;
		this.callNumber = callNumber;
		this.beCalledNumber = beCalledNumber;
		this.time = time;
		this.cost = cost;
		this.date = date;
	}

	public Bill() {
		super();
	}

}
