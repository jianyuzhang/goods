package com.zjy.web;


import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zjy.models.CD;
import com.zjy.service.CDService;


@Controller
@RequestMapping("/operate/CD")
public class CDController{
	
	@Resource
	private CDService cdService;
	
	public static String mid=null;
	/*
	 * 展示全部的CD
	 */
	@RequestMapping("/showAllCDs.do")
    public void showAllCDs(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<CD> cds=cdService.showAllCDs();
		response.getWriter().print(JSONArray.fromObject(cds));
	}
	/*
	 * 展示点击菜单显示对应的菜单
	 */
	@RequestMapping("/showSomeCDs.do")
	public void showSomeCDs(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String[] properties={"mid"};
		mid=request.getParameter("mid");
		Object[] propertyValues={mid};
		List<CD> cds=cdService.showSomeCDs(properties, propertyValues);
		response.getWriter().print(JSONArray.fromObject(cds));
	}
	
	/*
	 * 获取总数
	 */
	@RequestMapping("/count.do")
	public void count(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String propertyName="mid";
		Object propertyValue=mid;
		int i = cdService.countCDs(propertyName, propertyValue);
		response.getWriter().print(JSONArray.fromObject(i));
	}
	/*
	 * 分页展示
	 */
	@RequestMapping("/showListByPage.do")
	public void showCDsByPage(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String[] properties={"mid"};
		Object[] propertyValues={mid};
		int pageNo= Integer.parseInt(request.getParameter("pageNo"));
		List<CD> cdListPages=cdService.showSomeCDsByPage(properties, propertyValues, null, null, 18, pageNo);
		response.getWriter().print(JSONArray.fromObject(cdListPages));
	}
}
