package com.zjy.web;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.commons.CommonUtils;

import com.zjy.models.Address;
import com.zjy.service.AddressService;


@Controller
@RequestMapping("/operate/address")
public class AddressController{
	
	@Resource
	private AddressService addressService;
	
	@RequestMapping("/addAddress.do")
	public void addAddress(HttpServletRequest request ,HttpServletResponse response){
        Address add = new Address();
        String uid = request.getParameter("uid");
        String uname = request.getParameter("uname");
        String address=request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        int status=Integer.parseInt(request.getParameter("status"));
        add.setAddress(address);
        add.setAid(CommonUtils.uuid());
        add.setStatus(status);
        add.setUid(uid);
        add.setUname(uname);
        add.setPhoneNumber(phoneNumber);
        System.out.println(add.toString());
		addressService.insertAddress(add);
	}
	@RequestMapping("/updateAddress.do")
	public void updateAddress(HttpServletRequest request ,HttpServletResponse response){
	  Address address = new Address();
	  addressService.updateAddress(address);
	}
	
	@RequestMapping("/showSomeAddress.do")
	public void showSomeAddress(HttpServletRequest request ,HttpServletResponse response) throws Exception{
		String[] properties = {"uid"};
		Object[] propertyValues={request.getParameter("uid")};
		List<Address> addresses=addressService.selectSomesAddresses(properties, propertyValues);
		response.getWriter().print(JSONArray.fromObject(addresses));
	}
	@RequestMapping("/deleteAddress.do")
	public void deleteAddress(HttpServletRequest request ,HttpServletResponse response) throws Exception{
		String aid =  request.getParameter("aid");
	    addressService.deleteById(aid);
	}
}

