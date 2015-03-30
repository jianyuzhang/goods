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
	
	@RequestMapping("/showAllCDs.do")
    public void showAllCDs(HttpServletRequest request,HttpServletResponse response) throws IOException{
		List<CD> cds=cdService.showAllCDs();
		response.getWriter().print(JSONArray.fromObject(cds));
	}
	
	@RequestMapping("/showSomeCDs.do")
	public void showSomeCDs(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String[] properties={"mid"};
		Object[] propertyValues={request.getParameter("mid")};
		List<CD> cds=cdService.showSomeCDs(properties, propertyValues);
		response.getWriter().print(JSONArray.fromObject(cds));
	}
}

