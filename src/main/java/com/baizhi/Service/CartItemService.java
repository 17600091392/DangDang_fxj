package com.baizhi.Service;

public interface CartItemService {
	void addCart(String id);
	void updateCart(String id,int count);
	void deleteCart(String id);
}
