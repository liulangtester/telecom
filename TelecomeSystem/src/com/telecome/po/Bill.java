package com.telecome.po;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Bill {
	//�˵���
	private String id;
	//���к���
	private String callNumber;
	//���к���
	private String beCalledNumber;
	//ͨ��ʱ��
	private String time;
	//����
	private float cost;
	//ͨ������ʱ��
	private Date date;

	public String getId() {	
		return id;
	}
//	//�˵��Զ����ɣ���ǰ����+6λ�����
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
