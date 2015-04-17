package com.zjy.daos.daoImpls;

import org.springframework.stereotype.Repository;

import com.zjy.baseDao.BaseDao;
import com.zjy.daos.AddressDao;
import com.zjy.models.Address;

@Repository("addressDao")
public class AddressDaoImpl extends BaseDao<Address,String>implements AddressDao{

}
