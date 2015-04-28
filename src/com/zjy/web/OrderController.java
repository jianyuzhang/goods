package com.zjy.web;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONString;
import net.sf.json.util.JSONStringer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.commons.CommonUtils;

import com.zjy.models.Order;
import com.zjy.service.OrderService;


@Controller
@RequestMapping("/operate/order")
public class OrderController{
	
	@Resource
	private OrderService orderService;
	
	@RequestMapping("/addOrder.do")
	public void addOrder(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		Order order = new Order();
		order.setOid(CommonUtils.uuid());
		order.setUid(request.getParameter("uid"));
		order.setAddress(request.getParameter("address"));
		order.setStatus(0);
		order.setTotal(Double.parseDouble(request.getParameter("total")));
		order.setUname(request.getParameter("uname"));
		order.setPhoneNumber(request.getParameter("phoneNumber"));
		orderService.addOrder(order);
		response.getWriter().print(JSONArray.fromObject(order));
	}
 
}

