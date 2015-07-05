package com.zjy.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zjy.daos.OrderDao;
import com.zjy.models.Order;
import com.zjy.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	@Resource
	private OrderDao orderDao;

	@Override
	public int addOrder(Order order) {
		// TODO Auto-generated method stub
		int i = orderDao.insert(order);
		return i;
	}

	@Override
	public List<Order> showSomeOneOrders(String[] properties, Object[] propertyValues) {
		// TODO Auto-generated method stub
		List<Order> orders = orderDao.findByStatementPostfix(".selectOrders", properties, propertyValues, null, null);
		return orders;
	}

	@Override
	public void updateOrder(Order order) {
		// TODO Auto-generated method stub
		orderDao.update(order);
	}

   
}
