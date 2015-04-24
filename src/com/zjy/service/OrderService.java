package com.zjy.service;

import java.util.List;

import com.zjy.models.Order;


public interface OrderService {
	  public int addOrder(Order order);
	  public List<Order> showSomeOneOrders(String[] properties, Object[] propertyValues);
	  public void updateOrder(Order order);
}