package com.baizhi.entity;

import java.io.Serializable;


public class Admin implements Serializable{
	private int id;
	private String admin_name;
	private String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", admin_name=" + admin_name + ", password="
				+ password + "]";
	}
	
}
