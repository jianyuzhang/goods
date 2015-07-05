package com.zjy.web;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.commons.CommonUtils;

import com.zjy.models.CDInOrder;
import com.zjy.models.Order;
import com.zjy.models.OrderDTO;
import com.zjy.service.CDInOrderService;
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
    @RequestMapping("/showOrder.do")
    public void showOrder(HttpServletRequest request ,HttpServletResponse response) throws IOException{
    	List<OrderDTO> orderDTOs = new ArrayList<OrderDTO>();
    	List<Order> orders = new ArrayList<Order>();
    	String[]  properties= {"uid","status"};
    	Object[]  propertyValues = {request.getParameter("uid"),request.getParameter("status")};
    	orders=orderService.showSomeOneOrders(properties, propertyValues);
        for(Order order : orders){
        	OrderDTO orderDTO= new OrderDTO();
        	orderDTO.setAddress(order.getAddress());
        	orderDTO.setOid(order.getOid());
        	orderDTO.setPhoneNumber(order.getPhoneNumber());
        	SimpleDateFormat dateformat2=new SimpleDateFormat("yyyy年MM月dd日 HH时 ");   
        	orderDTO.setOrdertime(dateformat2.format(order.getOrdertime()));
        	orderDTO.setStatus(order.getStatus());
        	orderDTO.setTotal(order.getTotal());
        	orderDTO.setUname(order.getUname());
        	orderDTOs.add(orderDTO);
        }
        response.getWriter().print(JSONArray.fromObject(orderDTOs));
        	 
        }
    @RequestMapping("/updateOrder.do")
    public void cancelOrder(HttpServletRequest request,HttpServletResponse response){
     Order order= 	new Order();
     order.setStatus(Integer.parseInt(request.getParameter("status")));
     order.setOid(request.getParameter("oid"));
     orderService.updateOrder(order);
    }
}

