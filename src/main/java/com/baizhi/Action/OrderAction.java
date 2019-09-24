package com.baizhi.Action;

import java.util.List;

import com.baizhi.Service.OrderService;
import com.baizhi.Service.Impl.OrderServiceImpl;
import com.baizhi.entity.Address;
import com.baizhi.entity.Item;
import com.baizhi.entity.Order;
import com.opensymphony.xwork2.ActionSupport;

public class OrderAction extends ActionSupport{
	private Address address;
	private List<Order> list;
	private String id;
	private Order order;
	private List<Item> item;
	
	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<Order> getList() {
		return list;
	}

	public void setList(List<Order> list) {
		this.list = list;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String addOrder(){
		OrderService os=new OrderServiceImpl();
		os.addOrder(address);
		return "orderOk";	
	}
	
	public String selectAll(){
		OrderService os=new OrderServiceImpl();
		list = os.selectAll();
		return "showAll";
	}
	
	public String selectOne(){
		OrderService os=new OrderServiceImpl();
		order = os.selectOne(id);
		item = os.selectItem(id);
		return "showOne";
	}
}
