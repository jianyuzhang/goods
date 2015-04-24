package com.zjy.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zjy.daos.CDInOrderDao;
import com.zjy.models.CDInOrder;
import com.zjy.service.CDInOrderService;
@Service("cdInOrderService")
public class CDInOrderServiceImpl implements CDInOrderService{
 
	@Resource
	private CDInOrderDao cdInOrderDao;

	@Override
	public int addGoods(CDInOrder cdInorder) {
		// TODO Auto-generated method stub
		int i = cdInOrderDao.insert(cdInorder);
		return i;
	}

	@Override
	public List<CDInOrder> showCDInSomeOrder(String[] properties,
			Object[] propertyValues) {
		// TODO Auto-generated method stub
		List<CDInOrder> goods = cdInOrderDao.findByStatementPostfix(".selectCDInSomeOrder", properties, propertyValues, null, null);
		return goods;
	}
	
}
