package com.baizhi.Service.Impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.baizhi.DAO.AddressDAO;
import com.baizhi.Service.AddressService;
import com.baizhi.Util.MybatisUtil;
import com.baizhi.entity.Address;
import com.baizhi.entity.User;

public class AddressServiceImpl implements AddressService{

	@Override
	public List<Address> selectAll() {
		AddressDAO ad = (AddressDAO)MybatisUtil.getMapper(AddressDAO.class);
		HttpSession session = ServletActionContext.getRequest().getSession();
		User user = (User)session.getAttribute("user");
		List<Address> list = ad.selectAll(user.getId());
		MybatisUtil.close();
		return list;
	}

	@Override
	public Address selectOne(String addressId) {
		AddressDAO ad = (AddressDAO)MybatisUtil.getMapper(AddressDAO.class);
		Address address = ad.selectOne(addressId);
		MybatisUtil.close();
		return address;
	}

}
