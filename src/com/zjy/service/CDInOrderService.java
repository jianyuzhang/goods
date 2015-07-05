package com.zjy.service;

import java.util.List;

import com.zjy.models.CDInOrder;

public interface CDInOrderService {
	/*
	 * 向某个订单中添加商品信息
	 */
	public int addGoods(CDInOrder cdInorder);
	/*
	 * 查询某个订单的所有商品
	 */
	public List<CDInOrder> showCDInSomeOrder(String[] properties, Object[] propertyValues);
}
