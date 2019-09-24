package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable{
	private String id;
	private String order_no;
	private double total;
	private Date create_date;
	private String status;
	private String address_id;
	private String user_id;
	private Address address;
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAddress_id() {
		return address_id;
	}
	public void setAddress_id(String address_id) {
		this.address_id = address_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	@Override
	public String toString() {
		return "Order [id=" + id + ", order_no=" + order_no + ", total="
				+ total + ", create_date=" + create_date + ", status=" + status
				+ ", address_id=" + address_id + ", user_id=" + user_id
				+ ", address=" + address + "]";
	}

}
