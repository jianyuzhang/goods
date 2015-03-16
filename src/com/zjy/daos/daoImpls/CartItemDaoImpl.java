package com.zjy.daos.daoImpls;

import org.springframework.stereotype.Repository;

import com.zjy.baseDao.BaseDao;
import com.zjy.daos.CartItemDao;
import com.zjy.models.CartItem;

@Repository("cartItemDao")
public class CartItemDaoImpl extends BaseDao<CartItem,String>implements CartItemDao{

}
