package com.baizhi.DAO;

import java.util.List;

import com.baizhi.entity.User;

public interface UserDAO {
	User selectUser(String email);
	void addUser(User user);
	void addActive(User user);
	List<User> selectAll();
	void updateStatus(User user);
	User selectById(String id);
}
