package com.zjy.web;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.itcast.commons.CommonUtils;

import com.zjy.models.Wuliu;
import com.zjy.service.WuliuService;

@Controller
@RequestMapping("/operate/wuliu")
public class WuliuController {

	@Resource
	private WuliuService wuliuService;

	@RequestMapping("/showWuliu.do")
	public void selectAll(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String[] properties = {"oid"};
		Object[] values = {request.getParameter("oid")};
	Wuliu wuliu= wuliuService.selectByOid(properties, values);
		response.getWriter().print(JSONArray.fromObject(wuliu));
	}

	@RequestMapping("/addWuliu.do")
	public void addWuliu(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Wuliu wuliu = new Wuliu();
		wuliu.setWid(CommonUtils.uuid().substring(0,10));
		wuliu.setOid(request.getParameter("oid"));
		wuliu.setFromwhere(request.getParameter("fromwhere"));
		wuliu.setFromwho(request.getParameter("fromwho"));
		wuliu.setCity(request.getParameter("city"));
		wuliu.setTowhere(request.getParameter("towhere"));
		wuliu.setTowho(request.getParameter("towho"));
		wuliu.setPhonenumber(request.getParameter("phonenumber"));
		wuliuService.insert(wuliu);
	}
}
