package com.baizhi.Service;

import java.util.List;

import com.baizhi.entity.User;

public interface UserService {
	void login(User user);
	void logout();
	void regist(User user);
	void activeUser(String activeCode);
	List<User> selectAll();
	void updateStatus(String id);
}
