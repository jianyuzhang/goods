package com.zjy.service;

import java.util.List;

import com.zjy.models.Address;


public interface AddressService {
/**
 * 查找某用户的所有收获地址
 */
	public List<Address> selectSomesAddresses(String[] properties, Object[] propertyValues);
	
	/**
	 * 添加收货地址
	 */
	public int insertAddress(Address address);
	
	/**
	 * 修改某个收货信息
	 */
	public  int updateAddress(Address address);
	
	/**
	 * 删除某条收货信息
	 */
	public void deleteById(String aid);
}