package com.baizhi.Service;

import java.util.List;

import com.baizhi.entity.Address;

public interface AddressService {
	List<Address> selectAll();
	Address selectOne(String addressId);
}
