package com.zjy.web;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjy.service.OrderService;


@Controller
@RequestMapping("/operate/order")
public class OrderController{
	
	@Resource
	private OrderService orderService;
	
 
}

