package com.baizhi.Service;

import java.util.List;

import com.baizhi.entity.Address;
import com.baizhi.entity.Item;
import com.baizhi.entity.Order;

public interface OrderService {
	void addOrder(Address address);
	List<Order> selectAll();
	Order selectOne(String id);
	List<Item> selectItem(String id);
}
