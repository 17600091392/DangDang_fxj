package com.baizhi.DAO;

import java.util.List;

import com.baizhi.entity.Item;
import com.baizhi.entity.Order;

public interface OrderDAO {
	void addOrder(Order order);
	List<Order> selectAll();
	Order selectOne(String id);
	List<Item> selectItem(String id);
}
