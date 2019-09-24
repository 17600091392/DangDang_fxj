package com.baizhi.Action;

import com.baizhi.Service.CartItemService;
import com.baizhi.Service.Impl.CartItemServiceImpl;
import com.opensymphony.xwork2.ActionSupport;

public class CartItemAction extends ActionSupport{
	private String id;
	private int count;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String addCart(){
		CartItemService cs=new CartItemServiceImpl();
		cs.addCart(id);
		return "cart";
	}
	
	public String updateCart(){
		CartItemService cs=new CartItemServiceImpl();
		cs.updateCart(id, count);
		return "cart";
	}
	
	public String deleteCart(){
		CartItemService cs=new CartItemServiceImpl();
		cs.deleteCart(id);
		return "cart";
	}
}
