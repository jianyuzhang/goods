package com.zjy.service.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.zjy.daos.CartItemDao;
import com.zjy.models.CartItem;
import com.zjy.service.CartItemService;

@Service("cartItemService")
public class CartItemServiceImpl implements CartItemService {
	
	@Resource
	private CartItemDao cartItemDao;
   /*
    * 插入新的商品
    * @see com.zjy.service.CartItemService#createCart(com.zjy.models.CartItem)
    */
	@Override
	public int createCart(CartItem cart) {
		int  i = cartItemDao.insert(cart);
		return i;
	}
	/*
	 * 展示某人的购物车
	 * @see com.zjy.service.CartItemService#showAllcarts(java.lang.String[], java.lang.Object[])
	 */
	@Override
	public List<CartItem> showAllcarts(String[] properties,
			Object[] propertyValues) {
		
		List<CartItem> carts=cartItemDao.findByStatementPostfix(".selectAllCarts", properties, propertyValues, null, null);
		return carts;
	}
	/*
	 * 查询某用户的某个商品的数量
	 */
	@Override
	public List<CartItem> selectSomeOnesCartNUm(String[] properties,
			Object[] propertyValues) {
		List<CartItem> carts = cartItemDao.findByStatementPostfix(".selectSomeCartQunaty", properties, propertyValues, null, null);
		return carts;
	}
	/*
	 * 修改购物车
	 * @see com.zjy.service.CartItemService#updateCartItem(com.zjy.models.CartItem)
	 */
	@Override
	public int updateCartItem(CartItem cart) {
		// TODO Auto-generated method stub
		int i = cartItemDao.update(cart);
		return i;
	}
	/*
	 * 删除购物车内商品
	 * @see com.zjy.service.CartItemService#deleteCartItem(java.lang.String)
	 */
	@Override
	public void deleteCartItem(String cartItemId) {
		// TODO Auto-generated method stub
		cartItemDao.deleteById(cartItemId);
	}
	@Override
	public int countCart(String[] properties, Object[] propertyValues) {
		// TODO Auto-generated method stub
		int  i = cartItemDao.count(properties, propertyValues);
		return i;
	}
	/*
	 * 删除选中的商品
	 * @see com.zjy.service.CartItemService#deleteCartsBySelected(java.util.List)
	 */
	@Override
	public void deleteCartsBySelected(List<String> item) {
		// TODO Auto-generated method stub
		cartItemDao.deleteByIds(item);
	}
	

   
}
