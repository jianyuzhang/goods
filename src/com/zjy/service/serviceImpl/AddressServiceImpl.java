package com.zjy.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zjy.daos.AddressDao;
import com.zjy.models.Address;
import com.zjy.service.AddressService;

@Service("addressService")
public class AddressServiceImpl implements AddressService {
	
	@Resource
	private AddressDao addressDao;

	@Override
	public List<Address> selectSomesAddresses(String[] properties,
			Object[] propertyValues) {
		List<Address> addresses = addressDao.findByStatementPostfix(".selectAllAddresses", properties, propertyValues, null, null);
		return addresses;
	}

	@Override
	public int insertAddress(Address address) {
		int i = addressDao.insert(address);
		return i;
	}

	@Override
	public int updateAddress(Address address) {
		int i = addressDao.update(address);
		return i;
	}

	@Override
	public void deleteById(String aid) {
		// TODO Auto-generated method stub
		addressDao.deleteById(aid);
	}
	
}
