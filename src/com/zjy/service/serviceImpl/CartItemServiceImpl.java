package com.zjy.service.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zjy.daos.CartItemDao;
import com.zjy.service.CartItemService;

@Service("cartItemService")
public class CartItemServiceImpl implements CartItemService {
	
	@Resource
	private CartItemDao cartItemDao;

   
}
