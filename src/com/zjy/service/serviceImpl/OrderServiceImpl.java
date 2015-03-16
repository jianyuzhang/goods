package com.zjy.service.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zjy.daos.OrderDao;
import com.zjy.service.OrderService;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
	
	@Resource
	private OrderDao orderDao;

   
}
