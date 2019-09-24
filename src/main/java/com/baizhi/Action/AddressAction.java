package com.baizhi.Action;

import java.util.List;

import com.baizhi.Service.AddressService;
import com.baizhi.Service.Impl.AddressServiceImpl;
import com.baizhi.entity.Address;
import com.opensymphony.xwork2.ActionSupport;

public class AddressAction extends ActionSupport{
	private List<Address> list;
	private String addressId;
	private Address address;
	
	public List<Address> getList() {
		return list;
	}
	public void setList(List<Address> list) {
		this.list = list;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String selectAddress(){
		AddressService as=new AddressServiceImpl();
		list = as.selectAll();
		address = as.selectOne(addressId);
		return "address";
	}
}
