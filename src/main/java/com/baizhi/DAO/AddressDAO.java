package com.baizhi.DAO;

import java.util.List;

import com.baizhi.entity.Address;

public interface AddressDAO {
	List<Address> selectAll(String user_id);
	Address selectOne(String id);
	void addAddress(Address address);
	void updateAddress(Address address);
}
