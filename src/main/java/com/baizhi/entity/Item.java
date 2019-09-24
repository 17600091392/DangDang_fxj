package com.baizhi.entity;

import java.io.Serializable;
import java.util.Date;

public class Item implements Serializable{
	private String id;
	private String book_id;
	private int count;
	private Date create_date;
	private String order_id;
	private Book book;
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Date getCreate_date() {
		return create_date;
	}
	public void setCreate_date(Date create_date) {
		this.create_date = create_date;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", book_id=" + book_id + ", count=" + count
				+ ", create_date=" + create_date + ", order_id=" + order_id
				+ "]";
	}
	
}
