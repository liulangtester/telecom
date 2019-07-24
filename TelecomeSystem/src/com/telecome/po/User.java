package com.telecome.po;

public class User {
	// 电话
	private String user_phone;
	// 地址
	private String user_address;
	// 银行账户（付款）
	private String user_bank;
	// 运营商
	private String user_operator;
	// 密码
	private String user_password;
	// 余额
	private float user_money;
	// 用户类型
	private String user_type;
	//	id
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public float getUser_money() {
		return user_money;
	}

	public User(String user_phone, String user_address, String user_bank, String user_operator, String user_password,
			float user_money) {
		super();
		this.user_phone = user_phone;
		this.user_address = user_address;
		this.user_bank = user_bank;
		this.user_operator = user_operator;
		this.user_password = user_password;
		this.user_money = user_money;
	}

	public void setUser_money(float user_money) {
		this.user_money = user_money;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_address() {
		return user_address;
	}

	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}

	public String getUser_bank() {
		return user_bank;
	}

	public void setUser_bank(String user_bank) {
		this.user_bank = user_bank;
	}

	public String getUser_operator() {
		return user_operator;
	}

	public void setUser_operator(String user_operator) {
		this.user_operator = user_operator;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public User() {
		super();
	}

}
