package com.baizhi.Test;

import java.util.List;

import com.baizhi.DAO.AddressDAO;
import com.baizhi.Util.MybatisUtil;
import com.baizhi.entity.Address;

public class test {
	public static void main(String[] args) {
		AddressDAO ad = (AddressDAO)MybatisUtil.getMapper(AddressDAO.class);
		List<Address> list = ad.selectAll("1");
		Address address = ad.selectOne("1");
		System.out.println(list);
		System.out.println(address);
	}
}
