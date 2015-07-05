package com.zjy.web;


import java.io.IOException;
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
import com.zjy.service.CDInOrderService;


@Controller
@RequestMapping("/operate/cdInOrder")
public class CDInOrderController{
	
	@Resource
	private CDInOrderService cdInorderService;
	
	@RequestMapping("/addCdInOrder.do")
	public void addCdInOrder(HttpServletRequest request ,HttpServletResponse response) throws IOException{
		String oid = request.getParameter("oid");
		CDInOrder cd  = new CDInOrder();
		cd.setOid(oid);
		cd.setCoid(CommonUtils.uuid());
		cd.setCname(request.getParameter("cname"));
		cd.setCurrPrice(Double.parseDouble(request.getParameter("currPrice")));
		cd.setImage_b(request.getParameter("imge_b"));
		cd.setPrice(Double.parseDouble(request.getParameter("price")));
		cd.setSinger(request.getParameter("singer"));
		cd.setSum(Integer.parseInt(request.getParameter("sum")));
		cdInorderService.addGoods(cd);
	}
    @RequestMapping("/showGoods.do")
    public void showGoods(HttpServletRequest request,HttpServletResponse response) throws IOException{
    	String oid = request.getParameter("oid");
    	List<CDInOrder> cds= new ArrayList<CDInOrder>();
    	String[] properties = {"oid"};
    	Object[] propertyValues  = {oid};
    	cds = cdInorderService.showCDInSomeOrder(properties, propertyValues);
    	response.getWriter().print(JSONArray.fromObject(cds));
    }
}

