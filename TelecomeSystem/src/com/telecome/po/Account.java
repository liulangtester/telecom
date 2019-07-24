package com.telecome.po;

public class Account {
	private String user_phone;
	private String user_name;
	private String bank_account;
	private String open_bank;
	private String sub_bank;
	private float remainder_money;
	private float cost_money;
	private float month_money;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getBank_account() {
		return bank_account;
	}

	public void setBank_account(String bank_account) {
		this.bank_account = bank_account;
	}

	public String getOpen_bank() {
		return open_bank;
	}

	public void setOpen_bank(String open_bank) {
		this.open_bank = open_bank;
	}

	public String getSub_bank() {
		return sub_bank;
	}

	public void setSub_bank(String sub_bank) {
		this.sub_bank = sub_bank;
	}

	public float getRemainder_money() {
		return remainder_money;
	}

	public void setRemainder_money(float remainder_money) {
		this.remainder_money = remainder_money;
	}

	public float getCost_money() {
		return cost_money;
	}

	public void setCost_money(float cost_money) {
		this.cost_money = cost_money;
	}

	public float getMonth_money() {
		return month_money;
	}

	public void setMonth_money(float month_money) {
		this.month_money = month_money;
	}

}
