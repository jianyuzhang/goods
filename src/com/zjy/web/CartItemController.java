package com.zjy.web;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjy.service.CartItemService;


@Controller
@RequestMapping("/operate/cartItem")
public class CartItemController{
	
	@Resource
	private CartItemService cartItemService;
	
 
}

