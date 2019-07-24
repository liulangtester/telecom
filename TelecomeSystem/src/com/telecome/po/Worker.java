package com.telecome.po;

public class Worker {
	private String worker_id;
	private String worker_username;
	private String worker_password;
	private String worker_name;
	private String worker_sex;
	private int post_id;
	private int state;

	public String getWorker_id() {
		return worker_id;
	}

	public void setWorker_id(String worker_id) {
		this.worker_id = worker_id;
	}

	public String getWorker_username() {
		return worker_username;
	}

	public void setWorker_username(String worker_username) {
		this.worker_username = worker_username;
	}

	public String getWorker_password() {
		return worker_password;
	}

	public void setWorker_password(String worker_password) {
		this.worker_password = worker_password;
	}

	public String getWorker_name() {
		return worker_name;
	}

	public void setWorker_name(String worker_name) {
		this.worker_name = worker_name;
	}

	public String getWorker_sex() {
		return worker_sex;
	}

	public void setWorker_sex(String worker_sex) {
		this.worker_sex = worker_sex;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Worker(String worker_id, String worker_username, String worker_password, String worker_name,
			String worker_sex, int post_id, int state) {
		super();
		this.worker_id = worker_id;
		this.worker_username = worker_username;
		this.worker_password = worker_password;
		this.worker_name = worker_name;
		this.worker_sex = worker_sex;
		this.post_id = post_id;
		this.state = state;
	}

	public Worker() {
		super();
	}

}
