package com.zjy.daos.daoImpls;

import org.springframework.stereotype.Repository;

import com.zjy.baseDao.BaseDao;
import com.zjy.daos.OrderDao;
import com.zjy.models.Order;

@Repository("orderDao")
public class OrderDaoImpl extends BaseDao<Order,String>implements OrderDao{

}
