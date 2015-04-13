package com.zjy.service;

import java.util.List;

import com.zjy.models.CartItem;


public interface CartItemService {
	/*
	 * 添加购物车
	 */
	  public int createCart(CartItem cart);
	  /*
	   * 查询商品数量
	   */
	  public  int countCart(String[] properties, Object[] propertyValues);
	  /*
	   * 展示对应用户的购物车所有的商品
	   */
	  public List<CartItem> showAllcarts(String[] properties, Object[] propertyValues);
	  
	  /*
	   * 修改
	   */
	  public int updateCartItem(CartItem cart);
	  /*
	   * 删除购物车里商品
	   */
	  public void deleteCartItem(String cartItemId);
	  /*
	   * 查询某个用户某件商品的数量
	   */
	  public  List<CartItem> selectSomeOnesCartNUm(String[] properties, Object[] propertyValues);
	  
	  /*
	   * 删除选中的商品
	   */
	  public void deleteCartsBySelected(List<String> ids);
}