package com.zjy.web;


import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.commons.CommonUtils;

import com.zjy.models.CartItem;
import com.zjy.service.CartItemService;


@Controller
@RequestMapping("/operate/cartItem")
public class CartItemController{
	
	@Resource
	private CartItemService cartItemService;
	
	@RequestMapping("/addCart.do")
	public void addCartItem(HttpServletRequest request ,HttpServletResponse response){
		CartItem cart =new CartItem();
		String uid = request.getParameter("uid");
		String cid = request.getParameter("cid");
		int quantity = Integer.parseInt(request.getParameter("num"));
		cart.setCartItemId(CommonUtils.uuid());
		cart.setCid(cid);
		cart.setUid(uid);
		cart.setQuantity(quantity);
		System.out.println(cart.toString());
		cartItemService.createCart(cart);
	}
	@RequestMapping("/updateCart.do")
	public void updateCart(HttpServletRequest request ,HttpServletResponse response){
		CartItem cart =new CartItem();
		String uid = request.getParameter("uid");
		String cid = request.getParameter("cid");
		int quantity = Integer.parseInt(request.getParameter("num"));
		cart.setCid(cid);
		cart.setUid(uid);
		cart.setQuantity(quantity);
		cartItemService.updateCartItem(cart);
	}
	@RequestMapping("/sameCart.do")
	public void IsSameCart(HttpServletRequest request ,HttpServletResponse response) throws Exception{
		String[] propertyNames={"cid","uid"};
		Object[] propertyValues={request.getParameter("cid"),request.getParameter("uid")};
		 int  i =cartItemService.countCart(propertyNames, propertyValues);
		 response.getWriter().print(JSONObject.fromObject(i>0));
	}
	@RequestMapping("/countCart.do")
	public  void countCart(HttpServletRequest request ,HttpServletResponse response) throws Exception{
		String[] propertyNames={"cid","uid"};
		Object[] propertyValues={request.getParameter("cid"),request.getParameter("uid")};
		List<CartItem> carts= cartItemService.selectSomeOnesCartNUm(propertyNames, propertyValues);
		int sum= carts.get(0).getQuantity();
		response.getWriter().print(JSONObject.fromObject(sum));
	}
}

